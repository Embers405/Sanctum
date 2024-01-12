package io.ruin.model.activities.afkzone;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.data.impl.teleports;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.teleports.TeleportList;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;

public class afkzone {

    private static int Hespori = 11192;
    private static int Herbi = 7785;
    private static int Zalcano = 9050;
    private static int SpiritPool = 10571;
    private static int CrystalTree = 34918;
    private static int Dummy = 2038;

    static {
        ObjectAction.register(34499,"enter",(player, obj) -> {

        });
        ObjectAction.register(CrystalTree, 1,(player, obj) -> CrystalTree(player));
        NPCAction.register(Hespori,2,(player, npc) -> Hespori(player));
        NPCAction.register(Herbi,1,(player, npc) -> Herbi(player));
        NPCAction.register(Zalcano,1,(player, npc) -> Zalcano(player));
        NPCAction.register(SpiritPool,1,(player, npc) -> SpiritPool(player));
        ObjectAction.register(Dummy, 1, (player, obj) -> Dummy(player));
    }

    private static void Herbi(Player p) {
        //Hunter & herblore
        p.startEvent(e -> {
            while (true) {
                p.animate(2282);
                int point = Random.get(5,10);
                p.afkPoints += point;
                p.getStats().addXp(StatType.Herblore,3.0, true);
                p.getStats().addXp(StatType.Hunter,3.0, true);
                e.delay(3);
            }
        });
    }

    private static void Hespori(Player p) {
        //Farming
        p.startEvent(e -> {
            while (true) {
                int secateurs = 0;
                if (p.getEquipment().hasId(7409) || p.getInventory().hasId(7409)) {
                    secateurs = 1;
                } else if (p.getInventory().hasId(5329)) {
                    secateurs = 0;
                }
                int anim = secateurs == 1 ? 3342 : 2279;
                p.animate(anim);
                int point = Random.get(5,10);
                p.afkPoints += point;
                p.getStats().addXp(StatType.Farming,3.0, true);
                e.delay(3);
            }
        });
    }

    private static void Zalcano(Player p) {
        // Mining & Runecrafting
        p.startEvent(e -> {
            while (true) {
                p.animate(624);
                int point = Random.get(5,10);
                p.afkPoints += point;
                p.getStats().addXp(StatType.Mining,2.0, true);
                p.getStats().addXp(StatType.Runecrafting,3.0, true);
                e.delay(3);
            }
        });
    }

    private static void SpiritPool(Player p) {
        // Fishing
        p.startEvent(e -> {
            while (true) {
                p.animate(618);
                int point = Random.get(5,10);
                p.afkPoints += point;
                p.getStats().addXp(StatType.Fishing,3.0, true);
                e.delay(3);
            }
        });
    }

    private static void CrystalTree(Player p) {
        // Woodcutting
        p.startEvent(e -> {
            while (true) {
                p.animate(867);
                int point = Random.get(5,10);
                p.afkPoints += point;
                p.getStats().addXp(StatType.Woodcutting,3.0, true);
                e.delay(3);
            }
        });
    }

    private static void Dummy(Player p) {
        //Attack Str Def Training
        p.startEvent(e -> {
            while (true) {
                p.animate(422);
                int point = Random.get(5,10);
                p.afkPoints += point;
                e.delay(3);
            }
        });
    }

}
