package io.ruin.model.entity.player;

import com.google.common.collect.Lists;
import io.ruin.model.World;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.map.Position;
import io.ruin.utility.Misc;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.stream.Stream;

/**
 * 
 * @author C.T
 * @date October 30, 2022
 */
public class Boundary {

	int minX, minY, highX, highY;
	int height;

	/**
	 * 
	 * @param minX The south-west x coordinate
	 * @param minY The south-west y coordinate
	 * @param highX The north-east x coordinate
	 * @param highY The north-east y coordinate
	 */
	public Boundary(int minX, int minY, int highX, int highY) {
		this.minX = minX;
		this.minY = minY;
		this.highX = highX;
		this.highY = highY;
		height = -1;
	}
	
	public Boundary(Rectangle rect) {
		this.minX = rect.x;
		this.minY = rect.y;
		this.highX = rect.x + rect.width;
		this.highY = rect.y + rect.height;
		this.height = -1;
	}
	
	public Boundary expand(int x, int y) {
		return new Boundary(this.minX - x, this.minY - y, this.highX + x, this.highY + y);
	}
	
	public Boundary translate(int x, int y, int height) {
		return new Boundary(this.minX + x, this.minY + y, this.highX + x, this.highY + y, this.height - height);
	}
	
	public static boolean isInExclusive(Entity entity, Boundary... boundaries) {
		for (Boundary b : boundaries) {
			if (b.height >= 0) {
				if (entity.getHeight() != b.height) {
					continue;
				}
			}
			if (entity.getX() >= b.minX && entity.getX() < b.highX && entity.getY() >= b.minY && entity.getY() < b.highY) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * @param size
	 * @return
	 */
	public Boundary inset(int size) {
		return new Boundary(this.minX + size, this.minY + size, this.highX - size, this.highY - size);
	}
	
	public Stream<Position> insideBorderStream(int size) {
		List<Position> list = Lists.newArrayList();
		for(int x = minX;x<=highX;x++) {
			for(int y = minY;y<=highY;y++) {
				if(x >= minX + size && x <= highX - size && y >= minY + size && y <= highY - size)
					continue;
				list.add(new Position(x, y));
			}
		}
		return list.stream();
	}
	
	public Stream<Position> stream() {
		List<Position> list = Lists.newArrayList();
		for(int x = minX;x<highX;x++) {
			for(int y = minY;y<highY;y++) {
				list.add(new Position(x, y));
			}
		}
		return list.stream();
	}
	
	public Position getMinLocation(){
	    return Position.of(minX, minY);
	}
	
	public Position getMaxLocation(){
	    return Position.of(highX, highY);
	}
	
	public Stream<Position> streamWithHeight() {
		List<Position> list = Lists.newArrayList();
		for(int z = 0;z < 4; z++)
		for(int x = minX;x<highX;x++) {
			for(int y = minY;y<highY;y++) {
				list.add(new Position(x, y, z));
			}
		}
		return list.stream();
	}

	public static boolean isIn(Position location, Boundary... boundaries) {
		for (Boundary b : boundaries) {
			if (b.height >= 0) {
				if (location.getZ() != b.height) {
					continue;
				}
			}
			if (location.getX() >= b.minX && location.getX() <= b.highX && location.getY() >= b.minY && location.getY() <= b.highY) {
				return true;
			}
		}
		return false;
	}
	
	public List<Position> getRandomLocations(int count, int height) {
		List<Position> locations = new ArrayList<>();
		int lock = 50; //prevents deadlocks
		while (locations.size() < count && lock-- > 0) {
			int x = Misc.random(minX, highX);
			int y = Misc.random(minY, highY);
			Position location = new Position(x, y, height);
			if (!location.getRegion().solidObjectExists(x, y, height) && !locations.contains(location)) {
				locations.add(location);
			}
		}	
		return locations;
	}
	
	/**
	 * 
	 * @param entity The player object
	 * @param boundaries The array of Boundary objects
	 * @return
	 */
	public static boolean isIn(Entity entity, Boundary... boundaries) {
		for (Boundary b : boundaries) {
			if (b.height >= 0) {
				if (entity.getHeight() != b.height) {
					continue;
				}
			}
			if (entity.getX() >= b.minX && entity.getX() <= b.highX && entity.getY() >= b.minY && entity.getY() <= b.highY) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param minX The south-west x coordinate
	 * @param minY The south-west y coordinate
	 * @param highX The north-east x coordinate
	 * @param highY The north-east y coordinate
	 * @param height The height of the boundary
	 */
	public Boundary(int minX, int minY, int highX, int highY, int height) {
		this.minX = minX;
		this.minY = minY;
		this.highX = highX;
		this.highY = highY;
		this.height = height;
	}

	public int getMinimumX() {
		return minX;
	}

	public int getMinimumY() {
		return minY;
	}

	public int getMaximumX() {
		return highX;
	}

	public int getMaximumY() {
		return highY;
	}
	
	public boolean contains(Position location) {
		return location.getX() >= minX && location.getX() < highX && location.getY() >= minY && location.getY() < highY;
	}

	/**
	 * 
	 * @param player The player object
	 * @param boundaries The array of Boundary objects
	 * @return
	 */
	public static boolean isIn(Player player, Boundary[] boundaries) {
		for (Boundary b : boundaries) {
			if (b.height >= 0) {
				if (player.getHeight() != b.height) {
					continue;
				}
			}
			if (player.getX() >= b.minX && player.getX() <= b.highX && player.getY() >= b.minY && player.getY() <= b.highY) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param player The player object
	 * @param boundaries The boundary object
	 * @return
	 */
	public static boolean isIn(Player player, Boundary boundaries) {
		if (boundaries.height >= 0) {
			if (player.getHeight() != boundaries.height) {
				return false;
			}
		}
		return player.getAbsX() >= boundaries.minX && player.getAbsX() <= boundaries.highX && player.getAbsY() >= boundaries.minY && player.getAbsY() <= boundaries.highY;
	}

	/**
	 * 
	 * @param npc The npc object
	 * @param boundaries The boundary object
	 * @return
	 */
	public static boolean isIn(NPC npc, Boundary boundaries) {
		if (boundaries.height >= 0) {
			if (npc.getHeight() != boundaries.height) {
				return false;
			}
		}
		return npc.getX() >= boundaries.minX && npc.getX() <= boundaries.highX && npc.getY() >= boundaries.minY && npc.getY() <= boundaries.highY;
	}

	public static boolean isIn(NPC npc, Boundary[] boundaries) {
		for (Boundary boundary : boundaries) {
			if (boundary.height >= 0) {
				if (npc.getHeight() != boundary.height) {
					return false;
				}
			}
			if (npc.getX() >= boundary.minX && npc.getX() <= boundary.highX && npc.getY() >= boundary.minY && npc.getY() <= boundary.highY) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInSameBoundary(Player player1, Player player2, Boundary[] boundaries) {
		Optional<Boundary> boundary1 = Arrays.asList(boundaries).stream().filter(b -> isIn(player1, b)).findFirst();
		Optional<Boundary> boundary2 = Arrays.asList(boundaries).stream().filter(b -> isIn(player2, b)).findFirst();
		return boundary1.isPresent() && boundary2.isPresent() && Objects.equals(boundary1.get(), boundary2.get());
	}
	
	public static boolean isWithRaids(Entity entity) {
		return isWithin(entity, 3259, 5145, 3361, 5474) 
				|| isWithin(entity, 3220, 5710, 3246, 5755);
	}
	public static int entitiesInArea(Boundary boundary) {
		int i = 0;
		for (Player player : World.players)
			if (player != null)
				if (isIn(player, boundary))
					i++;
		return i;
	}

	/**
	 * Returns the centre point of a boundary as a
	 * 
	 * @param boundary The boundary of which we want the centre.
	 * @return The centre point of the boundary, represented as
	 */
	public static Position centre(Boundary boundary) {
		int x = (boundary.minX + boundary.highX) / 2;
		int y = (boundary.minY + boundary.highY) / 2;
		if (boundary.height >= 0) {
			return new Position(x, y, boundary.height);
		} else {
			return new Position(x, y, 0);
		}
	}
	
	public static boolean isInBounds(Player player, Boundary boundaries) {
		return player.getX() >= boundaries.minX && player.getX() <= boundaries.highX && player.getY() >= boundaries.minY && player.getY() <= boundaries.highY;
	}
	
	public static boolean isWithin(Entity entity, int lowX, int lowY, int highX, int highY) {
		return entity != null && entity.getX() >= lowX && entity.getY() >= lowY && entity.getX() <= highX && entity.getY() <= highY;
	}
	
	public static final Boundary EMPTY = new Boundary(0, 0, 0, 0);

	public static final Boundary BLOAT_ARENA = new Boundary(3287, 4439, 3304, 4456);

	public static final Boundary THEATRE = new Boundary (3127, 4229, 3329, 4489);
	public static final Boundary MAIDEN = new Boundary (3149, 4418, 3228, 4467);
	public static final Boundary BLOAT = new Boundary (3265, 4428, 3327, 4465);
	public static final Boundary NYLOCAS = new Boundary (3276, 4231, 3315, 4285);
	public static final Boundary SOTETSEG = new Boundary (3264, 4291, 3295, 4336);
	public static final Boundary XARPUS = new Boundary (3149, 4365, 3193, 4410);
	public static final Boundary VERZIK = new Boundary (3147, 4291, 3192, 4336);
	public static final Boundary LOOT = new Boundary (3222, 4301, 3253, 4337);
	public static final Boundary[] THEATRE_ROOMS = { THEATRE, MAIDEN, BLOAT, NYLOCAS, SOTETSEG, XARPUS, LOOT};


	public static int getPlayersInBoundary(Boundary boundary) {
		int i = 0;
		for (Player player : World.players)
			if (player != null)
				if (isIn(player, boundary))
					i++;
		return i;
	}

	public boolean in(Entity entity) {
		return isIn(entity, this);
	}

	public static Boundary calculateBoundary(Position a, Position b, int height) {
		return calculateBoundary(a.getX(), a.getY(), b.getX(), b.getY(), height);
	}

	public static Boundary calculateBoundary(int x1, int y1, int x2, int y2, int height) {
		return new Boundary(Math.min(x1, x2), Math.min(y1, y2), Math.max(x1, x2), Math.max(y1, y2), height);
	}
}