package io.ruin.model.entity.player.ai.scripts;

import io.ruin.cache.ItemDef;
import io.ruin.cache.NPCDef;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.ai.AIPlayer;
import io.ruin.model.item.Item;
import io.ruin.model.item.containers.Equipment;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Position;
import io.ruin.model.map.Tile;
import io.ruin.model.map.ground.GroundItem;
import kilim.Pausable;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class GoblinKiller extends AIScript {


    NPC target = null;

    public GoblinKiller(AIPlayer player) {
        super(player);
    }

    public static String botowner = "";

    public static int botownerid = 0;

    public boolean searching = true;

    public int kills = 0;


    @Override
    public void start() {
        player.max();
        player.getMovement().toggleRunning();

        player.getEquipment().set(Equipment.SLOT_HAT,new Item(1149)); // Helm Of Neitiznot
        player.getEquipment().set(Equipment.SLOT_CAPE,new Item(21295)); // Infernal Cape
        player.getEquipment().set(Equipment.SLOT_AMULET,new Item(19707)); // Amulet of eternal glory
        player.getEquipment().set(Equipment.SLOT_WEAPON,new Item(4151)); // Whip
        player.getEquipment().set(Equipment.SLOT_CHEST,new Item(10551)); // Fighter Torso
        player.getEquipment().set(Equipment.SLOT_SHIELD,new Item(12954)); // Dragon Defender
        player.getEquipment().set(Equipment.SLOT_LEGS,new Item(1079)); // Rune Platelegs

        player.getEquipment().set(Equipment.SLOT_HANDS,new Item(22981)); // Ferocious Gloves
        player.getEquipment().set(Equipment.SLOT_FEET,new Item(11840)); // Dragon Boots
        player.getEquipment().set(Equipment.SLOT_RING,new Item(2550)); // Ring of recoil

//        player.getInventory().add(13441, 28);

    }

    public boolean limit = false;

    public void simulateDrop(int npc){
        NPCDef def = NPCDef.get(npc);
        LootTable t = def.lootTable;
        List<Item> items = t.rollItems(true);
        for (Item i : items) {
            player.getBank().add(i.getId(), i.getAmount());
        }
    }

    public ArrayList<GroundItem> groundItems;

    public ArrayList<Tile> tiles;

    @Override
    public void run() {
        player.hitListener.preTargetDamage((hit, entity) -> {
//            player.startEvent(event -> {
//                chatMessages();
//                event.delay(4);
//            });
            player.addEvent(e -> {
                System.out.println("I'm fighting something fucker.");
                while (player.getCombat().getTarget() != null) {
                    e.delay(2);
                    if (entity.dead()) {
                        entity.dropListener = (killer, item) -> {
                            for (NPC localNpc : player.localNpcs()) {
                                tiles.add(localNpc.getPosition().getTile());
                            }

                            for (Tile tile : tiles) {
                                groundItems.addAll(tile.groundItems);
                            }
                            for (GroundItem groundItem : entity.getPosition().getTile().groundItems) {
                                if (groundItem != null)
                                groundItems.add(groundItem);
                            }
                        };
                        e.delay(4);
                        if (limit) {
                            kills = 0;
                            limit = false;
                        }
                        if (kills >= 5) {
                            for (int i = 0; i < 5000; i++){
                                simulateDrop(entity.npc.getId());
                            }
                            limit = true;
                        }
                        kills += 1;
                        System.out.println(kills);
                        killGoblin();
                    }
                }
            });
        });

        if (target != null && target.getHp() > 0 || player.getCombat().isAttacking(3)) {
            return;
        }
        System.out.println("Owner of this bot is -> " + botowner);
        player.startEvent(e -> {
            if (searching) {
                e.delay(4);
                player.localNpcs().forEach(npc -> {
                    if (!npc.getDef().name.equalsIgnoreCase("goblin")) {
                        teletoGoblin();
                    }
                    killGoblin();
                });
            }
        });
    }
    private static String message;

    public static Player owner;

    public static void setMessage(String message, Player owner) {
        GoblinKiller.message = message;
        GoblinKiller.owner = owner;
    }


    public void chatMessages() {
        if (owner.getName().equalsIgnoreCase(botowner)) {
            if (message != null && message.contains("bank")) {
                for (Player p : World.players) {
                    if (p.getName().equalsIgnoreCase(botowner)) {
                        for (Item i : player.getBank().getItems()){
                         p.getBank().add(i.getId(), i.getAmount());
                         player.getBank().remove(i);
                         message = "waiting";
                        }
                    }
                }
            } else {
                System.out.println(message + " Message not for me obviously.");
                message = "waiting";
            }
        }
    }

    public void teletoGoblin() {
        player.getMovement().startTeleport(e -> {
        player.animate(3864);
        player.graphics(1039);
        player.privateSound(200, 0, 10);
        e.delay(2);
        player.getMovement().teleport(2955,3504,0);
        e.delay(4);
        killGoblin();
        });
    }

    public void killGoblin() {
        player.startEvent(e -> {
            while (player.getCombat().getTarget() == null) {
                e.delay(5);
                for (NPC npc : player.localNpcs()) {
                    if (npc.getDef().name.equalsIgnoreCase("goblin")) {
                        if (npc.getPosition().isWithinDistance(player.getPosition(), 10)) {
                            target = npc;
                            player.getCombat().setTarget(npc);
                            player.attack(npc);
                            searching = false;
                        }
                    }
                }
            }
        });
    }

    public Bounds bound = new Bounds(2945,3476,2969,3520,0);

    @Override
    public void finish() {

    }

    @Override
    public int getDelay() {
        return 1;
    }
}
