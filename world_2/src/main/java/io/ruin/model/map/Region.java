package io.ruin.model.map;

import com.google.common.collect.Maps;
import io.ruin.Server;
import io.ruin.api.buffer.InBuffer;
import io.ruin.api.filestore.IndexFile;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.player.Player;
import io.ruin.model.item.containers.bank.BankActions;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.route.RouteFinder;
import io.ruin.model.skills.construction.House;

import java.util.*;

public class Region {


    public static final int[][][] SIZES = {
            { { 0, 0 } },
            { { 0, 0 } }, // 1
            { { 0, 1 }, { 1, 0 }, { 1, 1 } }, // 2
            { { 2, 0 }, { 2, 1 }, { 2, 2 }, { 1, 2 }, { 0, 2 } }, // 3
            { { 3, 0 }, { 3, 1 }, { 3, 2 }, { 3, 3 }, { 2, 3 }, { 1, 3 }, { 0, 3 } }, // 4
            { { 4, 0 }, { 4, 1 }, { 4, 2 }, { 4, 3 }, { 4, 4 }, { 3, 4 }, { 2, 4 }, { 1, 4 }, { 0, 4 } }, // 5
            { { 5, 0 }, { 5, 1 }, { 5, 2 }, { 5, 3 }, { 5, 4 }, { 5, 5 }, { 4, 5 }, { 3, 5 }, { 2, 5 }, { 1, 5 }, { 0, 5 } }, // 6
            { { 6, 0 }, { 6, 1 }, { 6, 2 }, { 6, 3 }, { 6, 4 }, { 6, 5 }, { 6, 6 }, { 5, 6 }, { 4, 6 }, { 3, 6 }, { 2, 6 }, { 1, 6 }, { 0, 6 } }, // 7
            { { 7, 0 }, { 7, 1 }, { 7, 2 }, { 7, 3 }, { 7, 4 }, { 7, 5 }, { 7, 6 }, { 7, 7 }, { 6, 7 }, { 5, 7 }, { 4, 7 }, { 3, 7 }, { 2, 7 }, { 1, 7 }, { 0, 7 } }, // 8
    };

    public static final int CLIENT_SIZE = 104;

    public static final Region[] LOADED = new Region[Short.MAX_VALUE + 1];

    /**
     * Separator
     */

    public final int id;

    public final int baseX, baseY;

    public final Bounds bounds;

    public final ArrayList<Player> players;

    public final ArrayList<Tile> activeTiles;

    private House house; // house instance that this region currently belongs to

    public Region(int id) {
        this.id = id;
        this.baseX = (id >> 8) * 64;
        this.baseY = (id & 0xff) * 64;
        this.bounds = Bounds.fromRegion(id);
        this.players = new ArrayList<>();
        this.activeTiles = new ArrayList<>();
    }

    /**
     * Data
     */

    public int[] keys;

    public Tile[][][] tiles;

    public int[][][][] dynamicData;

    public int dynamicIndex = -1;

    public int dynamicRegionBaseX, dynamicRegionBaseY;

    public boolean empty;

    public void init() { //not my favorite design, let's come back to this one day..
        byte[][][] tileData = new byte[4][64][64];
        byte[] mapData = getMapData();
        if(mapData != null) {
            InBuffer mapIn = new InBuffer(mapData);
            for(int z = 0; z < 4; z++) {
                for(int x = 0; x < 64; x++) {
                    for(int y = 0; y < 64; y++) {
                        for(; ; ) {
                            int i = mapIn.readByteUnsafe() & 0xFF;
                            if(i == 0)
                                break;
                            if(i == 1) {
                                mapIn.skipByte();
                                break;
                            }
                            if(i <= 49)
                                mapIn.skipByte();
                            else if(i <= 81)
                                tileData[z][x][y] = (byte) (i - 49);
                        }
                    }
                }
            }
            for(int z = 0; z < 4; z++) {
                for(int x = 0; x < 64; x++) {
                    for(int y = 0; y < 64; y++) {
                        if((tileData[z][x][y] & 0x1) == 1) {
                            int height = z;
                            if((tileData[1][x][y] & 0x2) == 2)
                                height--;
                            if(height >= 0) {
                                int absX = baseX + x;
                                int absY = baseY + y;
                                Tile tile = getTile(absX, absY, z, true);
                                tile.flagUnmovable();
                                tile.defaultClipping = tile.clipping;
                            }
                        }
                        if((tileData[z][x][y] & 0x4) != 0) {
                            int absX = baseX + x;
                            int absY = baseY + y;
                            Tile tile = getTile(absX, absY, z, true);
                            tile.roofExists = true;
                        }
                    }
                }
            }
        }
        byte[] landscapeData = null;
        boolean invalidKeys = false;
        try {
            landscapeData = getLandscapeData();
        } catch(Throwable t) {
            //System.err.println("Invalid Map Keys for Region (" + id + "): base=(" + baseX + ", " + baseY + ") keys=" + Arrays.toString(keys));
            invalidKeys = true;
        }
        if(landscapeData != null) {
            InBuffer landIn = new InBuffer(landscapeData);
            int objectId = -1;
            for(; ; ) {
                int idOffset = landIn.readUnsignedIntSmartShortCompat();
                if(idOffset == 0)
                    break;
                objectId += idOffset;
                int position = 0;
                for(; ; ) {
                    int positionOffset = landIn.readUnsignedShortSmart();
                    if(positionOffset == 0)
                        break;
                    position += positionOffset - 1;
                    int localY = position & 0x3f;
                    int localX = (position >> 6) & 0x3f;
                    int height = position >> 12;
                    
                    int attributes = landIn.readUnsignedByte();
                    int type = attributes >> 2;
                    int direction = attributes & 0x3;
    
                    if((tileData[1][localX][localY] & 0x2) == 2)
                        height--;
                    if(height >= 0) {
                        int absX = baseX + localX;
                        int absY = baseY + localY;
                        GameObject obj = new GameObject(objectId, absX, absY, height, type, direction);
                        getTile(absX, absY, height, true).addObject(obj);
                        BankActions.markTiles(obj);
                    }
                }
            }
        }
        empty = !invalidKeys && mapData == null && landscapeData == null;
    }

    public Tile getTile(int x, int y, int z, boolean create) {
        int localX = x - baseX;
        int localY = y - baseY;
        if(tiles == null) {
            if(!create)
                return null;
            tiles = new Tile[4][64][64];
        }
        Tile tile = tiles[z][localX][localY];
        if(tile == null && create)
            tile = tiles[z][localX][localY] = new Tile(this);
        return tile;
    }

    private byte[] getMapData() {
        IndexFile index = Server.fileStore.get(5);
        int mapArchiveId = index.getArchiveId("m" + ((baseX >> 3) / 8) + "_" + ((baseY >> 3) / 8));
        return mapArchiveId == -1 ? null : index.getFile(mapArchiveId, 0);
    }

    public byte[] getLandscapeData() {
        if(keys == null)
            return null;
        IndexFile index = Server.fileStore.get(5);
        int landArchiveId = index.getArchiveId("l" + ((baseX >> 3) / 8) + "_" + ((baseY >> 3) / 8));
        return landArchiveId == -1 ? null : index.getFile(landArchiveId, 0, keys[0] == 0 && keys[1] == 0 && keys[2] == 0 && keys[3] == 0 ? null : keys);
    }

    public static Region get(int regionId) {
        return LOADED[regionId];
    }

    public static Region get(int absX, int absY) {
        return get(getId(absX, absY));
    }

    public static int getId(int absX, int absY) {
        return ((absX >> 6) << 8) | absY >> 6;
    }

    public static int getClipping(int x, int y, int z) {
        Region region = Region.get(x, y);
        if(region.empty)
            return RouteFinder.UNMOVABLE_MASK;
        Tile tile = region.getTile(x, y, z, false);
        return tile == null ? 0 : tile.clipping;
    }

    /**
     * Updating
     */

    public static void update(Player player) {
        player.getPacketSender().clearChunks();
        for(Region region : player.getRegions()) {
            for(Tile tile : region.activeTiles)
                tile.update(player);
        }
    }

    /**
     * Destroy
     */

    public void destroy() {
        players.clear();
        if(!activeTiles.isEmpty()) {
            for(Tile tile : activeTiles)
                tile.destroy();
            activeTiles.clear();
        }
        tiles = null;
        dynamicIndex = -1;
        dynamicData = null;
        empty = true;
        house = null;
    }


    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    private static HashMap<Integer, ArrayList<GameObject>> worldObjects = new HashMap<>();
    public static boolean solidObjectExists(int x, int y, int height) {
        Region region = getRegion(x, y);
        if (region == null) {
            return false;
        }
        Collection<GameObject> regionObjects = worldObjects.get(region.id);
        if (regionObjects == null) {
            return false;
        }
        Optional<GameObject> exists = regionObjects.stream().filter(object -> object.type != 22 && object.x == x && object.y == y && object.z == height).findFirst();
        if(!exists.isPresent())
            exists = regionObjects.stream().filter(object ->  object.type != 22 && object.x == x && object.y == y && object.z == (height & 3)).findFirst();

        return exists.isPresent();
    }

    private static Map<Integer, Region> regions = Maps.newConcurrentMap();
    public static Region getRegion(int x, int y) {
        int regionX = x >> 3;
        int regionY = y >> 3;
        int regionId = (regionX / 8 << 8) + regionY / 8;
        Region region = regions.get(regionId);
        if(region == null) {
            region = new Region(regionId);
        }
        return region;
    }


    public static boolean isProjectilePathClear(int x0, int y0,
                                                int z, int x1, int y1) {
        int deltaX = x1 - x0;
        int deltaY = y1 - y0;

        double error = 0;
        double deltaError = Math.abs(
                (deltaY) / (deltaX == 0
                        ? ((double) deltaY)
                        : ((double) deltaX)));

        int x = x0;
        int y = y0;

        int pX = x;
        int pY = y;

        boolean incrX = x0 < x1;
        boolean incrY = y0 < y1;

        while (true) {
            if (x != x1) {
                x += (incrX ? 1 : -1);
            }

            if (y != y1) {
                error += deltaError;

                if (error >= 0.5) {
                    y += (incrY ? 1 : -1);
                    error -= 1;
                }
            }

            if (!shootable(x, y, z, pX, pY)) {
                return false;
            }

            if (incrX && incrY
                    && x >= x1 && y >= y1) {
                break;
            } else if (!incrX && !incrY
                    && x <= x1 && y <= y1) {
                break;
            } else if (!incrX && incrY
                    && x <= x1 && y >= y1) {
                break;
            } else if (incrX && !incrY
                    && x >= x1 && y <= y1) {
                break;
            }

            pX = x;
            pY = y;
        }

        return true;
    }

    private static boolean shootable(int x, int y, int z, int pX, int pY) {
        if (x == pX && y == pY) {
            return true;
        }

        int dir = NPCCombat.getDirection(x, y, pX, pY);
        int dir2 = NPCCombat.getDirection(pX, pY, x, y);

        if (dir == -1 || dir2 == -1) {
            System.out.println("NEGATIVE DIRECTION PROJECTILE ERROR");
            return false;
        }

        return Region.canMove(x, y, z, dir)
                && Region.canMove(pX, pY, z, dir2) || Region.canShoot(x, y, z, dir) && Region.canShoot(pX, pY, z, dir2);

    }

    public static boolean canMove(int x, int y, int z, int direction) {
        if (direction == 0) {
            return !blockedNorthWest(x, y, z) && !blockedNorth(x, y, z)
                    && !blockedWest(x, y, z);
        } else if (direction == 1) {
            return !blockedNorth(x, y, z);
        } else if (direction == 2) {
            return !blockedNorthEast(x, y, z) && !blockedNorth(x, y, z)
                    && !blockedEast(x, y, z);
        } else if (direction == 3) {
            return !blockedWest(x, y, z);
        } else if (direction == 4) {
            return !blockedEast(x, y, z);
        } else if (direction == 5) {
            return !blockedSouthWest(x, y, z) && !blockedSouth(x, y, z)
                    && !blockedWest(x, y, z);
        } else if (direction == 6) {
            return !blockedSouth(x, y, z);
        } else if (direction == 7) {
            return !blockedSouthEast(x, y, z) && !blockedSouth(x, y, z)
                    && !blockedEast(x, y, z);
        }
        return false;
    }

    public static boolean blockedNorth(int x, int y, int z) {
        return (getClipping(x, y + 1, z) & 0x1280120) != 0;
    }
    public static boolean blockedEast(int x, int y, int z) {
        return (getClipping(x + 1, y, z) & 0x1280180) != 0;
    }
    public static boolean blockedSouth(int x, int y, int z) {
        return (getClipping(x, y - 1, z) & 0x1280102) != 0;
    }
    public static boolean blockedWest(int x, int y, int z) {
        return (getClipping(x - 1, y, z) & 0x1280108) != 0;
    }
    public static boolean blockedNorthEast(int x, int y, int z) {
        return (getClipping(x + 1, y + 1, z) & 0x12801e0) != 0;
    }
    public static boolean blockedNorthWest(int x, int y, int z) {
        return (getClipping(x - 1, y + 1, z) & 0x1280138) != 0;
    }
    public static boolean blockedSouthEast(int x, int y, int z) {
        return (getClipping(x + 1, y - 1, z) & 0x1280183) != 0;
    }
    public static boolean blockedSouthWest(int x, int y, int z) {
        return (getClipping(x - 1, y - 1, z) & 0x128010e) != 0;
    }

    public static boolean canShoot(int x, int y, int z, int direction) {
        if (direction == 0) {
            return !projectileBlockedNorthWest(x, y, z) && !projectileBlockedNorth(x, y, z)
                    && !projectileBlockedWest(x, y, z);
        } else if (direction == 1) {
            return !projectileBlockedNorth(x, y, z);
        } else if (direction == 2) {
            return !projectileBlockedNorthEast(x, y, z) && !projectileBlockedNorth(x, y, z)
                    && !projectileBlockedEast(x, y, z);
        } else if (direction == 3) {
            return !projectileBlockedWest(x, y, z);
        } else if (direction == 4) {
            return !projectileBlockedEast(x, y, z);
        } else if (direction == 5) {
            return !projectileBlockedSouthWest(x, y, z) && !projectileBlockedSouth(x, y, z)
                    && !projectileBlockedWest(x, y, z);
        } else if (direction == 6) {
            return !projectileBlockedSouth(x, y, z);
        } else if (direction == 7) {
            return !projectileBlockedSouthEast(x, y, z) && !projectileBlockedSouth(x, y, z)
                    && !projectileBlockedEast(x, y, z);
        }
        return false;
    }

    public static boolean projectileBlockedNorth(int x, int y, int z) {
        return (getProjectileClipping(x, y + 1, z) & 0x1280120) != 0;
    }

    public static boolean projectileBlockedEast(int x, int y, int z) {
        return (getProjectileClipping(x + 1, y, z) & 0x1280180) != 0;
    }

    public static boolean projectileBlockedSouth(int x, int y, int z) {
        return (getProjectileClipping(x, y - 1, z) & 0x1280102) != 0;
    }

    public static boolean projectileBlockedWest(int x, int y, int z) {
        return (getProjectileClipping(x - 1, y, z) & 0x1280108) != 0;
    }

    public static boolean projectileBlockedNorthEast(int x, int y, int z) {
        return (getProjectileClipping(x + 1, y + 1, z) & 0x12801e0) != 0;
    }

    public static boolean projectileBlockedNorthWest(int x, int y, int z) {
        return (getProjectileClipping(x - 1, y + 1, z) & 0x1280138) != 0;
    }

    public static boolean projectileBlockedSouthEast(int x, int y, int z) {
        return (getProjectileClipping(x + 1, y - 1, z) & 0x1280183) != 0;
    }

    public static boolean projectileBlockedSouthWest(int x, int y, int z) {
        return (getProjectileClipping(x - 1, y - 1, z) & 0x128010e) != 0;
    }

    public static int getProjectileClipping(int x, int y, int height) {
        return getRegion(x, y).getProjectileClip(x, y, height);
    }

    private Map<Integer, int[][]> shootable = Maps.newConcurrentMap();
    private int getProjectileClip(int x, int y, int height) {
        int regionAbsX = (id >> 8) * 64;
        int regionAbsY = (id & 0xff) * 64;
        if(height > 3) {
            if (shootable.get(height) != null) {
                int flag = shootable.get(height)[x - regionAbsX][y - regionAbsY];
                if(shootable.get(height & 3) != null)
                    flag |= shootable.get(height & 3)[x - regionAbsX][y - regionAbsY];
                return flag;
            } else {
                height &= 3;
            }
        }
        if (shootable.get(height) == null) {
            return 0;
        }
        return shootable.get(height)[x - regionAbsX][y - regionAbsY];
    }

}