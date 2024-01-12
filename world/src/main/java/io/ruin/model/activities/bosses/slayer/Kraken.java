package io.ruin.model.activities.bosses.slayer;

import io.ruin.cache.NPCDef;
import io.ruin.model.activities.miscpvm.slayer.CaveKraken;
import io.ruin.model.combat.Hit;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.shared.listeners.RespawnListener;
import io.ruin.model.item.actions.ItemNPCAction;
import io.ruin.model.map.Projectile;
import io.ruin.model.skills.slayer.Slayer;
import io.ruin.model.skills.slayer.SlayerTask;

import java.util.ArrayList;
import java.util.List;

public class Kraken extends CaveKraken {

    static {
        NPCDef def = NPCDef.get(496);
        def.attackOption = def.getOption("disturb");
        def.swimClipping = true;
        def = NPCDef.get(494);
        def.swimClipping = true;
        ItemNPCAction.register(6664, 496, (player, item, npc1) -> {
            if (!Slayer.isTask(player, npc1)) {
                if (player.slayerTask != null && !player.slayerTask.name.contains("Kraken"))
                    player.sendMessage("You cannot attack monsters in this cave unless they are assigned to you by a slayer master.");
                return;
            }
            npc1.startEvent(e -> {
                item.remove(1);
                e.delay(2);
                npc1.animate(7135);
                npc1.transform(494);
                npc1.targetPlayer(player, true);
                npc1.attackTargetPlayer();
                for (NPC localNpc : npc1.localNpcs()) {
                    if (localNpc.getId() == 5534) {
                        localNpc.animate(3860);
                        localNpc.transform(5535);
                    }
                }
            });
        });
    }

    private static final Projectile PROJECTILE = new Projectile(156, 60, 31, 25, 56, 10, 15, 128);
    private List<NPC> tentacles = new ArrayList<>(4);

    @Override
    public void init() {
        super.init();
        tentacles.add(new NPC(5534).spawn(npc.spawnPosition.copy().translate(-3, 0, 0)));
        tentacles.add(new NPC(5534).spawn(npc.spawnPosition.copy().translate(-3, 4, 0)));
        tentacles.add(new NPC(5534).spawn(npc.spawnPosition.copy().translate(6, 0, 0)));
        tentacles.add(new NPC(5534).spawn(npc.spawnPosition.copy().translate(6, 4, 0)));
        npc.deathStartListener = (entity, killer, killHit) -> {
          tentacles.forEach(t -> {
              if (!t.getCombat().isDead())
                  t.getCombat().startDeath(killHit);
          });
        };
        RespawnListener superListener = npc.respawnListener;
        npc.respawnListener = n -> {
            superListener.onRespawn(npc);
            tentacles.forEach(t -> t.getCombat().respawn());
        };
        npc.hitListener.preDefend(this::preDefend);
    }

    private void preDefend(Hit hit) {
        hit.ignorePrayer();
        if (npc.getId() == getWhirlpoolId() && tentacles.stream().anyMatch(t -> t.getId() == 5534)) {
            hit.block();
        }
    }

    @Override
    public boolean attack() {
        tentacles.forEach(t -> {
            if (t.getCombat().getTarget() != target) {
                t.getCombat().setTarget(target);
                t.face(target);
            }
        });
        return super.attack();
    }

    @Override
    protected void preTargetDefend(Hit hit, Entity entity) {
        super.preTargetDefend(hit, entity);
        hit.ignorePrayer();
    }

    @Override
    protected int getWhirlpoolId() {
        return 496;
    }

    @Override
    protected int getSurfaceId() {
        return 494;
    }

    @Override
    protected Projectile getProjectile() {
        return PROJECTILE;
    }

    @Override
    protected int getHitGfx() {
        return 157;
    }
}
