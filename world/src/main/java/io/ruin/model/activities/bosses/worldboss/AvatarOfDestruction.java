package io.ruin.model.activities.bosses.worldboss;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.World;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.combat.Hit;
import io.ruin.model.combat.HitType;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.item.Item;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.map.Position;
import io.ruin.model.map.Projectile;
import io.ruin.model.map.ground.GroundItem;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.route.routes.ProjectileRoute;
import io.ruin.model.stat.StatType;
import io.ruin.utility.Broadcast;
import io.ruin.utility.TickDelay;

import java.util.ArrayList;
import java.util.function.Consumer;

public class AvatarOfDestruction extends NPCCombat {

    private static StatType[] DRAIN = { StatType.Attack, StatType.Strength, StatType.Defence, StatType.Ranged, StatType.Magic };
    private static final Projectile MAGIC_PROJECTILE = new Projectile(1714, 100, 10, 25, 70, 10, 12, 64);
    private static final Projectile RANGE_ATTACK = new Projectile(1713,100,10, 50, 70, 10, 12, 64);
    private static final Projectile CRYSTAL_BOMB_PROJECTILE = new Projectile(1357, 90, 0, 30, 100, 0, 16, 0);

    @Override
    public void init() {
        npc.setIgnoreMulti(true);

        npc.setHp((int) (500 * (World.players.count() / 2 / 5 * 2.50)));

        npc.deathEndListener = (DeathListener.Simple) () -> {
            for (Player player : npc.localPlayers()) {
                for (int i = 0; i < 2; i++) {
                    Item rolled = rollRegular();
                    int amount = rolled.getAmount();
                    if (amount > 1 && !rolled.getDef().stackable && !rolled.getDef().isNote())
                        rolled.setId(rolled.getDef().notedId);
                    new GroundItem(rolled).owner(player).position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()).spawn();
                    player.sendMessage("<shad=000000>" + Color.COOL_BLUE.wrap("You got " + rolled.getDef().name + " X " + rolled.getAmount()) + "</shad>");
                    if (rolled.getId() == 6199 || rolled.getId() == 290 || rolled.getId() == 30248 || rolled.getId() == 30250 || rolled.getId() == 30249 || rolled.getId() == 30257 || rolled.getId() == 11944) {
                        Broadcast.GLOBAL.sendNews("<shad=000000>" + Color.RAID_PURPLE.wrap("[RARE DROP] ") + player.getName() + " has just received " + Color.DARK_RED.wrap(rolled.getDef().name) + " from The Donator Boss!" + "</shad>");
                    }
                }
            }
            npc.remove();
        };
    }

    @Override
    public void follow() {
        follow(8);
    }

    private void forAllTargets(Consumer<Player> action) {
        npc.getPosition().getRegion().players.stream()
                .filter(p -> p.getHeight() == npc.getPosition().getZ()) // on olm floor, past the barrier
                .forEach(action);
    }

/*    public void meteorAttack() {
        npc.forceText("May the crystals bomb you!");
        npc.addEvent(event -> {
            Position bombPos = target.getPosition().copy();
            CRYSTAL_BOMB_PROJECTILE.send(npc, bombPos);
            event.delay(3);
            GameObject bomb = GameObject.spawn(29766, bombPos, 10, 0);
            event.delay(8);
            bomb.remove();
            World.sendGraphics(40, 0, 0, bombPos);
            forAllTargets(p -> {
                int distance = p.getPosition().distance(bombPos);
                if (distance > 5)
                    return;
                p.hit(new Hit(npc).fixedDamage(p.getMaxHp() - (distance * 10)));
            });
            if(npc.getPosition().distance(bombPos) <= 1) {
                npc.hit(new Hit(npc).fixedDamage(73));
            }
        });

    };*/

    @Override
    public boolean attack() {
        if (!withinDistance(8)) {
            return false;
        }
/*        boolean healed = false;
        if (!HealDelay.isDelayed()) {
            healed = true;
            heal();
        }*/
        if (withinDistance(1)) {
            int random = Random.get(1, 10);
            switch (random) {
                case 1:
                case 2:
                case 3:
                case 4:
                    RangeAttack();
                    break;
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    basicAttack();
                    break;
            }
            return true;
        }
        int random = Random.get(1, 10);
        switch (random) {
            case 1:
            case 2:
            case 3:
            case 4:
                RangeAttack();
                break;
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
                magicAttack();
                break;
        }

        return true;
    }
    private void magicAttack() {
        npc.animate(8840);
        npc.localPlayers().forEach( p -> {
            if (ProjectileRoute.allow(npc, p)) {
                int delay = MAGIC_PROJECTILE.send(npc, p);
                Hit hit = new Hit(npc, AttackStyle.MAGIC)
                        .randDamage((int) (info.max_damage))
                        .clientDelay(delay).ignoreDefence().ignorePrayer();
                hit.postDamage(t -> {
                    if(hit.damage > 0) {
                        t.graphics(1716, 124, 0);
                    } else {
                        t.graphics(85, 124, 0);
                    }
                });
                p.hit(hit);
            }
        });
    }

    private void RangeAttack() {
        npc.animate(8840);
        npc.localPlayers().forEach( p -> {

            if (ProjectileRoute.allow(npc, p)) {
                int delay = RANGE_ATTACK.send(npc, p);
                Hit hit = new Hit(npc, AttackStyle.RANGED)
                        .randDamage((int) (info.max_damage * 0.25))
                        .clientDelay(delay).ignoreDefence().ignorePrayer();
                hit.postDamage(t -> {
                    if(hit.damage > 0) {
                        t.graphics(1715, 124, 0);
                    } else {
                        t.graphics(85, 124, 0);
                    }
                });
                p.hit(hit);
            }
        });
    }

    public static LootTable regularTable = new LootTable()

            .addTable(20,
                    new LootItem(995, Random.get(50_000, 250_000), 20), // Coins
                    new LootItem(1276, Random.get(2, 5), 3), //Rune pickaxe
                    new LootItem(1304, Random.get(2, 5), 2), //Rune longsword
                    new LootItem(1290, Random.get(2, 5), 2), //Rune sword
                    new LootItem(5316, Random.get(3, 5), 3), //magic seed

                    new LootItem(2, 3000, 3), //cannonballs x3000
                    new LootItem(6686, 50, 3), //saradomin brew noted x50
                    new LootItem(12696, 25, 2), //super combat pooition noted x25
                    new LootItem(5730, 1, 3) //dragon spear
            )
            .addTable(20,
                    new LootItem(1128, 5, 3),
                    new LootItem(4088, Random.get(2, 3), 3),
                    new LootItem(22804, Random.get(50, 125), 3),
                    new LootItem(23648, Random.get(50, 125), 3)
            )
            .addTable(20,
                    new LootItem(995, Random.get(50_000, 1_000_000), 3),
                    new LootItem(5300, 1, 3),
                    new LootItem(1514, Random.get(15, 20), 3),
                    new LootItem(3024, 3, 2),
                    new LootItem(3052, 3, 2),
                    new LootItem(12073, 1, 2)
            )
            .addTable(5,
                    new LootItem(30248, 1, 1), // $5 bond
                    new LootItem(30250, 1, 1), // $10 Bond
                    new LootItem(30249, 1, 1), // $25 Bond
                    new LootItem(25350,1,1),
//                    new LootItem(30257, 1, 1), // karen's key
                    new LootItem(11944, 10, 8) // lava dragon bones
            );

    private static Item rollRegular() {
        return regularTable.rollItem();
    }
}
