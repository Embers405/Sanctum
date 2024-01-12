package io.ruin.model.activities.bosses.nex;

import io.ruin.api.utils.Random;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.combat.AttackType;
import io.ruin.model.combat.Hit;
import io.ruin.model.combat.HitType;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.map.Projectile;
import io.ruin.model.skills.prayer.Prayer;

public class FollowerCombat extends NPCCombat {

    @Override
    public void init() {
        Nex.FumusDead = false;
        Nex.Fumus = false;
        Nex.UmbraDead = false;
        Nex.Umbra = false;
        Nex.CruorDead = false;
        Nex.Cruor = false;
        Nex.GlaciesDead = false;
        Nex.Glacies = false;

        npc.hitListener= new HitListener().preDefend(this::postDamage);
        npc.deathEndListener = (DeathListener.Simple) () -> {
            if (npc.getId() == 14651) {
                Nex.FumusDead = true;
                Nex.Fumus = false;
            }
            if (npc.getId() == 14652) {
                Nex.UmbraDead = true;
                Nex.Umbra = false;
            }
            if (npc.getId() == 14653) {
                Nex.CruorDead = true;
                Nex.Cruor = false;
            }
            if (npc.getId() == 14654) {
                Nex.GlaciesDead = true;
                Nex.Glacies = false;
            }
            npc.startEvent(e -> {
                npc.animate(836);
                e.delay(3);
                npc.remove();
            });
        };
    }

    private void postDamage(Hit hit) {
        if (npc.getId() == 14651) {
            if (!Nex.Fumus) {
                hit.attacker.player.sendMessage("Fumus deflects all incoming attacks!");
                hit.block();
            }
        } else if (npc.getId() == 14652) {
            if (!Nex.Umbra) {
                hit.attacker.player.sendMessage("Umbra deflects all incoming attacks!");
                hit.block();
            }
        } else if (npc.getId() == 14653) {
            if (!Nex.Cruor) {
                hit.attacker.player.sendMessage("Cruor deflects all incoming attacks!");
                hit.block();
            }
        } else if (npc.getId() == 14654) {
            if (!Nex.Glacies) {
                hit.attacker.player.sendMessage("Glacies deflects all incoming attacks!");
                hit.block();
            }
        }
    }


    @Override
    public void follow() {

    }
    //Fumus NPC 14651 Cannot be attacked until nex's HP <= 80% (Smoke)
    //Umbra NPC 14652 Cannot be attacked until nex's HP <= 60% (Shadow)
    //Cruor NPC 14653 Cannot be attacked until nex's HP <= 40% (Blood)
    //Glacies NPC 14654 Cannot be attacked until nex's HP <= 20% (Ice)

    @Override
    public boolean attack() {
        if (npc.getId() == 14651 && !npc.dead()) {
            castSmoke();
        } else if (npc.getId() == 14652 && !npc.dead()) {
            castShadow();
        } else if (npc.getId() == 14653 && !npc.dead()) {
            castBlood();
        } else if (npc.getId() == 14654 && !npc.dead()) {
            castIce();
        }
        return true;
    }
    private static final Projectile SMOKE_BLITZ = new Projectile(390, 43, 31, 51, 56, 10, 16, 64);
    private static final Projectile ICE_BLITZ = new Projectile(368, 43, 0, 51, 56, 10, 16, 64);
    private static final Projectile BLOOD_BLITZ = new Projectile(374, 43, 0, 51, 56, 10, 16, 64);
    private static final Projectile SHADOW_BLITZ = new Projectile(380, 43, 0, 51, 56, 10, 16, 64);

    public void castSmoke() {
        npc.animate(1979);
//        npc.graphics(386);
        npc.publicSound(183, 1, 0);
        for (Player p : npc.localPlayers()) {
            boolean protecting_magic = p.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC);
            int max_hit = info.max_damage;
            if (protecting_magic) {
                max_hit = (max_hit / 4);
            }
            int clientDelay = SMOKE_BLITZ.send(npc, p);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    p.graphics(387, 124, 51);
                    p.publicSound(181);
                    p.poison(20);
                }
            });
            p.hit(hit);
            prayerDrain();
        }
    }

    public void castIce() {
        npc.animate(1979);
//        npc.graphics(366);
        npc.publicSound(183, 1, 0);
        for (Player p : npc.localPlayers()) {
            boolean protecting_magic = p.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC);
            int max_hit = info.max_damage;
            if (protecting_magic) {
                max_hit = (max_hit / 4);
            }
            int clientDelay = ICE_BLITZ.send(npc, p);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    p.graphics(367, 0, 51);
                    p.publicSound(181);
                    p.freeze(5, npc);
                }
            });
            p.hit(hit);
            prayerDrain();
        }
    }

    public void castBlood() {
        npc.animate(1979);
//        npc.graphics(386);
        npc.publicSound(106, 1, 0);
        for (Player p : npc.localPlayers()) {
            boolean protecting_magic = p.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC);
            int max_hit = info.max_damage;
            if (protecting_magic) {
                max_hit = (max_hit / 4);
            }
            int clientDelay = BLOOD_BLITZ.send(npc, p);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    p.graphics(375, 124, 51);
                    p.publicSound(104);
                }
            });
            p.hit(hit);
            prayerDrain();
        }
    }

    public void castShadow() {
        npc.animate(1979);
//        npc.graphics(386);
        npc.publicSound(178, 1, 0);
        for (Player p : npc.localPlayers()) {
            boolean protecting_magic = p.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC);
            int max_hit = info.max_damage;
            if (protecting_magic) {
                max_hit = (max_hit / 4);
            }
            int clientDelay = SHADOW_BLITZ.send(npc, p);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    p.graphics(381, 0, 51);
                    p.publicSound(176);
                }
            });
            p.hit(hit);
            prayerDrain();
        }
    }

    public void prayerDrain() {
        if (Random.get() < 0.30) {
            npc.graphics(811);
            for (Player localPlayer : npc.localPlayers()) {
                if (localPlayer.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC)) {
                    localPlayer.graphics(409, 0, 4);
                    localPlayer.getPrayer().deactivate(Prayer.PROTECT_FROM_MAGIC);
                    localPlayer.getPrayer().drain(2);
                }
            }
        }
    }
}
