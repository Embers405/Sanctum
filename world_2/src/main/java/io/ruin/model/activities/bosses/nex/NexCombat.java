package io.ruin.model.activities.bosses.nex;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.combat.AttackStyle;
import io.ruin.model.combat.Hit;
import io.ruin.model.combat.HitType;
import io.ruin.model.entity.Entity;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.listeners.HitListener;
import io.ruin.model.item.Item;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.map.Position;
import io.ruin.model.map.Projectile;
import io.ruin.model.map.ground.GroundItem;
import io.ruin.model.map.route.routes.ProjectileRoute;
import io.ruin.model.skills.prayer.Prayer;
import io.ruin.utility.Broadcast;
import io.ruin.utility.Misc;
import io.ruin.utility.TickDelay;
import kilim.tools.P;

import java.util.HashMap;

public class NexCombat extends NPCCombat {

    //Region is 11593
    //Animation's start at 10840 upto 10849

    @Override
    public void init() {
        NPC Fumusc = new NPC(14651).spawn(2901,4714,1);
        NPC Umbrac = new NPC(14652).spawn(2901,4698,1);
        NPC Cruorc = new NPC(14653).spawn(2917,4698,1);
        NPC Glaciesc = new NPC(14654).spawn(2917,4714,1);
        npc.hitListener = new HitListener().preDefend(this::postDamage);
        npc.deathEndListener = (entity, killer, killHit) -> {
            for (Player player : npc.localPlayers()) {
                for (int i = 0; i < 2; i++) {
                    Item rolled = rollRegular();
                    int amount = rolled.getAmount();
                    if (amount > 1 && !rolled.getDef().stackable && !rolled.getDef().isNote())
                        rolled.setId(rolled.getDef().notedId);
                    new GroundItem(rolled).owner(player).position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()).spawn();
                    player.sendMessage("<shad=000000>" + Color.COOL_BLUE.wrap("You got " + rolled.getDef().name + " X " + rolled.getAmount()) + "</shad>");
                    player.getCollectionLog().collect(rolled);
                    if (rolled.getId() == 24420 || rolled.getId() == 24421 || rolled.getId() == 24419 || rolled.getId() == 24417 || rolled.getId() == 24514 || rolled.getId() == 24517 || rolled.getId() == 24511 || rolled.getId() == 24422) {
                        Broadcast.GLOBAL.sendNews("<shad=000000>" + Color.RAID_PURPLE.wrap("[RARE DROP] ") + player.getName() + " has just received " + Color.DARK_RED.wrap(rolled.getDef().name) + " from nex!" + "</shad>");
                    }
                }
                new GroundItem(592, 1).owner(player).position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()).spawn();
                new GroundItem(995, Random.get(100000, 150000)).owner(player).position(player.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()).spawn();
            }
            killer.player.sendMessage("You have defeated nex!");
        };
    }

    private void postDamage(Hit hit) {
        if (npc.getHp() <= 2720 && !Nex.Fumus && !Nex.FumusDead) {
            npc.forceText("Fumus, don't fail me!");
            Nex.Fumus = true;
        }
        if (npc.getHp() <= 2040 && !Nex.Umbra && !Nex.UmbraDead) {
            npc.forceText("Umbra, don't fail me!");
            Nex.Umbra = true;
        }
        if (npc.getHp() <= 1360 && !Nex.Cruor && !Nex.CruorDead) {
            npc.forceText("Cruor, don't fail me!");
            Nex.Cruor = true;
            NPC reaver1 = new NPC(14658).spawn(2910, 4695, 1);
            reaver1.getCombat().setAllowRespawn(false);
            reaver1.getCombat().setTarget(npc.getCombat().getTarget());
            NPC reaver2 = new NPC(14658).spawn(2921, 4706, 1);
            reaver2.getCombat().setAllowRespawn(false);
            reaver2.getCombat().setTarget(npc.getCombat().getTarget());
        }
        if (npc.getHp() <= 680 && !Nex.Glacies && !Nex.GlaciesDead) {
            npc.forceText("Glacies, don't fail me!");
            Nex.Glacies = true;
        }
        if (Nex.Fumus && !Nex.FumusDead) {
            hit.attacker.player.sendMessage(Color.DARK_RED, "Nex is immune to your attack while Fumus is your target!");
            hit.block();
        }
        if (Nex.Umbra && !Nex.UmbraDead) {
            hit.attacker.player.sendMessage(Color.DARK_RED, "Nex is immune to your attack while Umbra is your target!");
            hit.block();
        }
        if (Nex.Cruor && !Nex.CruorDead) {
            hit.attacker.player.sendMessage(Color.DARK_RED, "Nex is immune to your attack while Cruor is your target!");
            hit.block();
        }
        if (Nex.Glacies && !Nex.GlaciesDead) {
            hit.attacker.player.sendMessage(Color.DARK_RED, "Nex is immune to your attack while Glacies is your target!");
            hit.block();
        }
    }

    @Override
    public void follow() {
        follow(1);
    }

    @Override
    public void setTarget(Entity target) {
        super.setTarget(target);
    }

    @Override
    public boolean attack() {
        /*
         * Melee attack.
         */

        if (Random.get() < 0.5 && target.getPosition().isWithinDistance(npc.getPosition(), 2)) {
            basicAttack();
            return true;
        }

        int rand = Random.get(16);
        switch (rand) {
            case 1:
            case 2:
            case 3:
            case 4:
                castSmoke();
                return true;
            case 5:
            case 6:
            case 7:
            case 8:
                castIce();
                return true;
            case 9:
            case 10:
            case 11:
            case 12:
                castBlood();
                return true;
            case 13:
            case 14:
            case 15:
            case 16:
                castShadow();
                return true;
        }
        return true;
    }

    public static LootTable regularTable = new LootTable()
            .addTable(1,
                    new LootItem(995, Random.get(100000, 250000), 0), // Coins
                    new LootItem(6686, Random.get(10, 30), 100), // Saradomin brew (4)
                    new LootItem(3025, Random.get(10, 30), 100), // Super restore (4)
                    new LootItem(1514, 375, 100), // Magic logs
                    new LootItem(1754, 400, 100), // Green dragonhide
                    new LootItem(1632, 20, 100), // Uncut dragonstone
                    new LootItem(1080, Random.get(2, 8), 100), // Rune Platelegs
                    new LootItem(1128, Random.get(2, 8), 100), // Rune Platebody
                    new LootItem(989, 2, 40), // Crystal Key
                    new LootItem(9245, 375, 40), // Onyx Bolts (e)
                    new LootItem(20615, 1, 15), // zarosian crystal
                    new LootItem(30331, 1, 15), // necromancer silk
                    new LootItem(212, 75, 40), // Grimy Avantoe
                    new LootItem(218, 75, 40), // Grimy Dwarf Weed
                    new LootItem(220, 40, 40), // Grimy Torstol
                    new LootItem(5304, 12, 40), // Torstol seed
                    new LootItem(5316, 5, 40), // magic seed
                    new LootItem(12073, 1, 11), // Elite clue scroll
                    new LootItem(6199, 1, 6), // mystery box
                    new LootItem(290, 1, 5).broadcast(Broadcast.GLOBAL), // Luxury mystery box
                    new LootItem(30000, 1, 3).broadcast(Broadcast.GLOBAL), // Torva Helm
                    new LootItem(30001, 1, 3).broadcast(Broadcast.GLOBAL), // Torva Plate
                    new LootItem(30002, 1, 3).broadcast(Broadcast.GLOBAL), // Torva legs
                    new LootItem(30006, 1, 3).broadcast(Broadcast.GLOBAL), // Virtus helm
                    new LootItem(30007, 1, 3).broadcast(Broadcast.GLOBAL), // Virtus body
                    new LootItem(30008, 1, 3).broadcast(Broadcast.GLOBAL), // Virtus Legs
                    new LootItem(30003, 1, 3).broadcast(Broadcast.GLOBAL), // Pernix Helm
                    new LootItem(30004, 1, 3).broadcast(Broadcast.GLOBAL), // Pernix Body
                    new LootItem(30005, 1, 3).broadcast(Broadcast.GLOBAL), // Pernix Legs
                    new LootItem(30289, 1, 2).broadcast(Broadcast.GLOBAL), // Zaryte Bow
                    new LootItem(30367, 1, 2).broadcast(Broadcast.GLOBAL),//NEX PET
                    new LootItem(30386,1,1).broadcast(Broadcast.GLOBAL),//ancient hilt
                    new LootItem(30388, 1,1).broadcast(Broadcast.GLOBAL)//nihil horn
            );

    private static Item rollRegular() {
        return regularTable.rollItem();
    }

    private static final Projectile SMOKE_BLITZ = new Projectile(386, 43, 31, 51, 56, 10, 16, 64);
    private static final Projectile ICE_BLITZ = new Projectile(368, 43, 0, 51, 56, 10, 16, 64);
    private static final Projectile BLOOD_BLITZ = new Projectile(374, 43, 0, 51, 56, 10, 16, 64);
    private static final Projectile SHADOW_BLITZ = new Projectile(380, 43, 0, 51, 56, 10, 16, 64);

    public void castSmoke() {
        for (Player player : npc.localPlayers()) {
            int max_hit = info.max_damage;
            npc.animate(10850);
//            npc.graphics(386);
            npc.publicSound(183, 1, 0);
            int clientDelay = SMOKE_BLITZ.send(npc, player);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(player.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC) ? (max_hit/4) : max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    player.graphics(387, 124, 51);
                    player.publicSound(181);
                    player.poison(20);
                }
            });
            player.hit(hit);
            prayerDrain();
        }
    }
    public void castIce() {
        for (Player player : npc.localPlayers()) {
            int max_hit = info.max_damage;
            npc.animate(10848);
//            npc.graphics(366);
            npc.publicSound(183, 1, 0);
            int clientDelay = ICE_BLITZ.send(npc, player);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(player.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC) ? (max_hit/4) : max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    player.graphics(367, 0, 51);
                    player.publicSound(181);
                    player.freeze(5, npc);
                }
            });
            player.hit(hit);
            prayerDrain();
        }
    }
    public void castBlood() {
        for (Player player : npc.localPlayers()) {
            int max_hit = info.max_damage;
            npc.animate(10842);
//            npc.graphics(386);
            npc.publicSound(106, 1, 0);
            int clientDelay = BLOOD_BLITZ.send(npc, player);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(player.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC) ? (max_hit/4) : max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    player.graphics(375, 124, 51);
                }
            });
            player.hit(hit);
            prayerDrain();
        }
    }
    public void castShadow() {
        for (Player player : npc.localPlayers()) {
            int max_hit = info.max_damage;
            npc.animate(10850);
//            npc.graphics(386);
            npc.publicSound(178, 1, 0);
            int clientDelay = SHADOW_BLITZ.send(npc, player);
            Hit hit = new Hit(npc, AttackStyle.MAGIC).randDamage(player.getPrayer().isActive(Prayer.PROTECT_FROM_MAGIC) ? (max_hit/4) : max_hit).clientDelay(clientDelay).ignorePrayer();
            hit.postDamage(e -> {
                if (!hit.isBlocked()) {
                    player.graphics(381, 0, 51);
                    player.publicSound(176);
                }
            });
            player.hit(hit);
            prayerDrain();
        }
    }

    public void prayerDrain() {
        if (Random.get() < 0.10) {
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
