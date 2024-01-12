package io.ruin.model.entity.player.ai.scripts;

import io.ruin.Server;
import io.ruin.api.utils.Random;
import io.ruin.cache.ItemDef;
import io.ruin.model.combat.Hit;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.ai.AIPlayer;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.Item;
import io.ruin.model.item.containers.Equipment;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Position;
import io.ruin.model.map.ground.GroundItem;
import io.ruin.model.skills.magic.SpellBook;
import io.ruin.model.skills.magic.spells.lunar.Vengeance;
import io.ruin.model.stat.Stat;
import io.ruin.model.stat.StatType;
import io.ruin.utility.Misc;

import static io.ruin.model.item.actions.impl.Consumable.animEat;

/**
 * Represents a melee bot script.
 */
public class MeleeBot extends AIScript {

    Position p = null;

    private long lastVeng = 0;

    public boolean seeking = true;
    public boolean fighting = false;
    public Player target;

    /**
     * Constructs a new melee bot.
     * @param player
     */
    public MeleeBot(AIPlayer player) {
        super(player);
    }

    @Override
    public void start() {
        int xp = Stat.xpForLevel(99);
        for (int i = 0; i < StatType.values().length; i ++) {
            Stat stat = player.getStats().get(i);
            stat.currentLevel = stat.fixedLevel = 99;
            stat.experience = xp;
            stat.updated = true;
        }

        Config.AUTO_RETALIATE.set(player, 1);

        SpellBook.LUNAR.setActive(player);

        player.getInventory().add(557, 50_000);
        player.getInventory().add(9075, 50_000);
        player.getInventory().add(560, 50_000);
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


//        player.getInventory().add(13441, 5);

        player.getCombat().updateLevel();
        player.getAppearance().update();

        player.getMovement().toggleRunning();
        player.handleDialogue(0);
//        crossWildernessDitch();
        setDelay(3);

        player.deathStartListener = (DeathListener.SimpleKiller) killer -> {
            if (Random.rollPercent(50)) {
                player.forceText("gf fag.");
            } else {
                player.forceText("you fucking goonie!");
            }
            player.startEvent(e -> {
                e.delay(6);
                player.logoutStage = -1;
                Position pos = BOTBOUNDS.randomPosition();
                AIPlayer aip = new AIPlayer(generateUsername(), new Position(pos.getX(), pos.getY(), 0));
                aip.init();
                MeleeBot script = new MeleeBot(aip);
                aip.runScript(script);
            });

        };

        player.hitListener = new HitListener().preDamage(hit -> {
           inCombat(hit.attacker.player);// Bot's don't attack back anymore -.-
        });

        player.dropListener = (killer, item) -> {
            item.remove();
            new GroundItem(995, 2000000).owner(killer.player).position(killer.player.getPosition()).spawn();
        };
    }

    private static final Bounds BOTBOUNDS = new Bounds(3079, 3527, 3096, 3540, 0);

    @Override
    public void run() {
        player.startEvent(e -> {
            while (player.getCombat().getTarget() == null){
                seeking = true;
                e.delay(2);
                SeekTarget();
            }
        });
    }

    public void SeekTarget() {
        player.startEvent(e -> {
            e.delay(2);
            while (seeking) {
                e.delay(4);
                if (player.getInventory().freeSlot() >= 1){
                    player.getInventory().add(13441, player.getInventory().getFreeSlots());
                }
                int xAdd = Random.get(-4,4);
                int yAdd = Random.get(-4,4);
                p = new Position(player.getPosition().getX() + xAdd, player.getPosition().getY() + yAdd, 0);
                player.move(p, false);
                System.out.println("Walking -> " + p.toString());
                for (Player localPlayer : player.localPlayers()) {
                    if (localPlayer.getPosition().isWithinDistance(player.getPosition(), 5)) {
                        if (localPlayer.getCombat().getTarget() == null && localPlayer.wildernessLevel > 0 && localPlayer.getUserId() != player.getUserId() && !localPlayer.isHidden()) {
                            Fight(localPlayer);
                            fighting = true;
                            seeking = false;
                        }
                    }
                }
            }
        });
    }

    public void Fight(Player p) {
        player.startEvent(e -> {
            e.delay(3);
            while (fighting && !seeking) {
                e.delay(4);
                if (p.getCombat().getTarget() == null) {
                    player.getCombat().setTarget(p);
                    if (player.getCombat().getTarget().player == player || p.getCombat().getTarget() != player) { // WTF?
                        fighting = false;
                        seeking = true;
                        SeekTarget();
                    } else {
                        inCombat(p);
                    }
                }
            }
        });
    }

    public void SpecialAttack() {
        player.getCombat().toggleSpecial();
    }

    public void inCombat(Player p) {
        player.startEvent(eve -> {
            eve.delay(4);
        while (player.getCombat().getTarget() == p || p.getHp() >= 1 || player.getHp() >= 1 || p.getCombat().getTarget() == player) {
            player.attack(p);
            if (p.getHp() <= 0) {
                player.forceText("Sit you fucking rat bastard!");
                player.startEvent(e -> {
                    player.move(p.getPosition(), true);
                    e.delay(4);
                    while (player.getPosition().getTile().groundItems != null) {
                        while (player.getInventory().freeSlot() <= 1) {
                            e.delay(4);
                            for (Item item : player.getInventory().getItems()) {
                                e.delay(2);
                                player.getBank().add(item.getId(), item.getAmount());
                                player.getInventory().remove(item.getId(), item.getAmount());
                                System.out.println("Storing " + item.getDef().name + " into my bank for later!");
                            }
                        }
                        e.delay(4);
                        for (GroundItem item : player.getPosition().getTile().groundItems) {
                            e.delay(2);
                            if (item != null) {
                                item.pickup(player, 1);
                            }
                            System.out.println("Picking up " + ItemDef.get(item.id).name);
                        }
                    }
                    fighting = false;
                    seeking = true;
                    SeekTarget();
                });

            }

            player.getCombat().setTarget(p);
            player.face(p);

            player.startEvent(e -> {
                e.delay(3);
                player.attack(p);
            });

            player.hitListener.postDamage(hit -> {
                if (player.vengeanceActive) {
                    checkVeng(hit);
                }
            });

            if (Random.rollPercent(25) && canVeng()) {
                castVengeance();
            }

            if (p.getHp() <= 60) {
                SpecialAttack();
            }

            player.getMovement().following = p.getMovement().following;

//            while (player.getHp() < (player.getMaxHp() / Misc.randomDouble(1.1, 5)) && player.getInventory().contains(13441)) {
//                player.getInventory().remove(13441, 1);
//                animEat(player);
//                int hp = player.getHp();
//                int maxHp = player.getMaxHp();
//                int c;
//                if (maxHp <= 24)
//                    c = 2;
//                else if (maxHp <= 49)
//                    c = 4;
//                else if (maxHp <= 74)
//                    c = 6;
//                else if (maxHp <= 92)
//                    c = 8;
//                else
//                    c = 13;
//                int restore = (maxHp / 10) + c;
//                int newHp = Math.min(hp + restore, maxHp + restore);
//                player.setHp(newHp);
//                player.eatDelay.delay(6);
//                player.getCombat().delayAttack(3);
//                player.attack(hit.attacker);
//            }

        }
    });
    }

    private String generateUsername() {
        String[] names = {"420", "Bane", "Bear", "Behemoth", "Big", "Black", "broskii", "rocky", "NuLL", "Greco", "Bleed", "Blood", "Blow", "Boar", "Boi", "Bolt", "Boulder", "Boy", "Break", "Brow", "Challenger", "Chaser", "Colossal", "Colossus", "Corrupter", "Crow", "Danger", "Dark", "Dead", "pelt", "Death", "Deceiver", "Die", "Dire", "Doom", "Dragon", "Dwarf", "Dwarven", "Fang", "Fierce", "Fist", "Flurry", "Freak", "Fury", "Fuse", "Giant", "Girl", "Gold", "Great", "Grim", "Grotesque", "Guthix", "Hallow", "Helm", "Hit", "Hollow", "Homie", "Hunter", "Insane", "Invincible", "Iron", "Ironfist", "Ironman", "Kill", "Killa", "Lion", "Lone", "Mammoth", "Man", "Mane", "Me", "Might", "Mighty", "Mithril", "Molten", "Myth", "Mythic", "Night", "Night", "owl", "One", "PK", "Paragon", "Pelt", "Poison", "Proud", "Pur3", "Pure", "Rage", "Raven", "Rebel", "Rock", "Rumble", "Savage", "Scar", "Sexy", "Shade", "Shield", "Shout", "Silent", "Silver", "Sk1ll", "Skill", "Skull", "Slayer", "Spirit", "Spook", "Steel", "Storm", "Stout", "Strong", "Swift", "Tempest", "The", "Thirst", "Thunder", "Tower", "True", "Voidmane", "White", "Wild", "Wildfist", "Wolf", "Zam", "Zammy", "Zero", "_", "axe", "bolt", "bow", "bronze", "brow", "chaser", "cleaver", "cold", "earth", "fang", "fierce", "fire", "flayer", "gaze", "hero", "hot", "ice", "killa", "knight", "mage", "man", "metal", "might", "rage", "scar", "snarl", "song", "sorrow", "stride", "strike", "strong", "sword", "sworn", "thorn", "throw", "tongue", "warrior", "wind", "wizard", "xX", "xox", "xxx"};
        int nameCount = Random.get(2, 3), nameLen = Random.get(8, 12);
        String name = "";
        for (int i = 0; i < nameCount; i++) {
            String tmpName = names[Random.get(0, names.length - 1)];
            switch (Random.get(0, 1)) {
                case 0:
                    tmpName = tmpName.toLowerCase();
                    break;
                case 1:
                    tmpName = tmpName.toUpperCase();
                    break;
            }
            if (name.length() + tmpName.length() <= nameLen) {
                name += tmpName;
            } else {
                String tmprName;
                if (Random.get(0, 1) == 1) {
                    tmprName = (name + tmpName).substring(0, nameLen);
                } else {
                    int r = Random.get(9, nameLen);
                    tmprName = (name + tmpName).substring(0, r);
                    for (int ri = r; ri < 12; ri++) {
                        if (Random.get(0, 1) == 1) {
                            tmprName += Random.get(0, 9);
                        } else {
                            tmprName = Random.get(0, 9) + tmprName;
                        }
                    }
                }
                name = tmprName;
            }
        }
        return name;
    }


    @Override
    public void finish() {
    System.out.println("Finishing...");
    }

    /**
     * Casts vengeance for the melee bot.
     */
    public void castVengeance() {
        Vengeance.cast(player, 1);
        lastVeng = Server.currentTick() + 50;
    }

    public void checkVeng(Hit hit) {
        if(!player.vengeanceActive)
            return;
        if(hit.attacker == null || hit.attackStyle == null)
            return;
        if(player.getStats().get(StatType.Defence).fixedLevel < 40) {
            return;
        }
        int vengDamage = (int) Math.ceil(hit.damage * 0.75);
        if(vengDamage <= 0)
            return;
        player.vengeanceActive = false;
        player.forceText("Taste Vengeance!");
        hit.attacker.hit(new Hit(player).fixedDamage(vengDamage));
    }

    /**
     * Crosses the wilderness ditch.
     */
    public void crossWildernessDitch() {
        player.handleObjectInteraction(1, 23271, 3087, 3521, false);
    }

    public boolean canVeng() {
        return Server.currentTick() > lastVeng;
    }
}
