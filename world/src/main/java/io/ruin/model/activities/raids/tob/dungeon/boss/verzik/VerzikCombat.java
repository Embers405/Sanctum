package io.ruin.model.activities.raids.tob.dungeon.boss.verzik;

import io.ruin.api.utils.Random;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.combat.Hit;
import io.ruin.model.combat.Killer;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Position;
import io.ruin.model.map.Projectile;
import io.ruin.model.map.dynamic.DynamicMap;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.route.routes.ProjectileRoute;

public class VerzikCombat extends NPCCombat {

    public static DynamicMap map = new DynamicMap().build(12611, 1);
    public static Bounds arenaBounds = new Bounds(map.convertX(3155),map.convertY(4304),map.convertX(3181),map.convertY(4322), 0);
    
    private static final Projectile ELECTRIC = new Projectile(1580, 100, 0, 0, 220, 0, 50, 0);
    private static final Projectile SPIDER_PROJ = new Projectile(1596, 100, 30, 50, 130, 0, 15, 255);
    private static final Projectile ACID_POOL_PROJECTILE = new Projectile(1354, 90, 0, 30, 100, 0, 16, 0);
    private static final Projectile LAVA_PROJECTILE = new Projectile(1247, 65, 0, 25, 86, 0, 15, 220);
    private VerzikNPC.Form currentForm = VerzikNPC.Form.RANGED;

    private static int special = 0;

    @Override
    public void init() {
        npc.hitListener = new HitListener().postDamage((hit -> {
            for (Killer k : npc.getCombat().killers.values()) {
                k.player.tobDamage++;
            }
        }));
        npc.deathStartListener = (entity, killer, killHit) -> verzikStartEnd();
        npc.setMaxHp((int) ((double) npc.getMaxHp() * 0.75));
    }

    private void verzikStartEnd() {
        npc.startEvent(event -> {
            npc.animate(8128);
            npc.transform(8375);
            event.delay(6);
            npc.remove();
            System.out.println("woohoo we made it ?");
        });
    }

    @Override
    public void follow() {
        follow(50);
    }

    @Override
    public boolean attack() {
		 if (special >= 100) {
            /*lavaspread();*/
            acidPoolsAttack();
            special = 0;
            return true;
        }
        if (Random.rollDie(15, 1) && target.getPosition().isWithinDistance(npc.getPosition(), 10))
            electricBall(target, npc);
        else if (target.getPosition().isWithinDistance(npc.getPosition(), 10))
            spiderBall(target, npc);
        return true;
    }

    private VerzikNPC asVerzik() {
        return (VerzikNPC) npc;
    }

    private void spiderBall(Entity target, NPC npc) {
        npc.animate(8125);
		special += 5;
        npc.localPlayers().forEach(p -> {
            if (ProjectileRoute.allow(npc, p)) {
                int delay = SPIDER_PROJ.send(npc, p);
                Hit hit = new Hit(npc, AttackStyle.MAGIC)
                        .randDamage(25, 55)
                        .clientDelay(delay);
                hit.postDamage(t -> {
                    if (hit.damage > 0) {
                        t.graphics(1599, 124, 0);
                    }
                });
                p.hit(hit);
            }
        });
    }


    private void acidPoolsAttack() {
        npc.animate(8126);
        int poisonPools = (Random.get(10,40) * npc.localPlayers().size());
        npc.localPlayers().forEach(p -> {
            npc.startEvent(e -> {
                GameObject pool = GameObject.spawn(32000, p.getPosition(), 10, 0);
                e.delay(6);
                pool.remove();
            });
        });

        for (int i = 0; i < poisonPools; i++) {
            Position pos = arenaBounds.randomPosition();
            ACID_POOL_PROJECTILE.send(npc, pos);
            npc.addEvent(event -> {
                event.delay(3);
                GameObject pool = GameObject.spawn(32000, pos, 10, 0);
                event.delay(6);
                pool.remove();
            });
        }
    }
	
    private void lavaspread() {
        int poisonPools = (Random.get(10,40) * npc.localPlayers().size());
        for (int i = 0; i < poisonPools; i++) {
            Position pos = arenaBounds.randomPosition();
            LAVA_PROJECTILE.send(npc, pos);
            npc.addEvent(event -> {
                event.delay(3);
                GameObject pool = GameObject.spawn(51783, pos, 10, 0);
                event.delay(6);
                pool.remove();
            });
        }
    }	

    private void electricBall(Entity target, NPC npc) {
        npc.animate(8123);
		special += 5;
        npc.localPlayers().forEach(p -> {
            if (ProjectileRoute.allow(npc, p)) {
                int delay = ELECTRIC.send(npc, p);
                Hit hit = new Hit(npc, AttackStyle.MAGIC)
                        .randDamage(45, 75)
                        .clientDelay(delay);
                hit.postDamage(t -> {
                    if (hit.damage > 0) {
                        t.graphics(1581, 0, 0);
                    }
                });
                p.hit(hit);
            }
        });
    }
}

