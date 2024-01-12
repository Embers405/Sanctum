package io.ruin.model.activities.afkzone;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.handlers.TabStats;
import io.ruin.model.item.Item;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.stat.StatType;

public class afkHerbs {

    private static int herbpatch = 8143;

    static {
        ObjectAction.register(herbpatch, "pick", (player, obj) -> herbPatch(player));
        ObjectAction.register(herbpatch, "inspect", (player, obj) -> {
            player.dialogue(new MessageDialogue("A wonderful AFK Herb patch."));
        });
        ObjectAction.register(herbpatch, "guide", ((player, obj) -> {
            TabStats.openGuide(player, StatType.Farming, 6);
        }));
    }

    private static void herbPatch(Player p) {
        p.startEvent(e -> {
            while (true) {
                p.animate(2282);
                p.getStats().addXp(StatType.Farming, 3.0, true);
                p.getStats().addXp(StatType.Herblore, 3.0, true);
                if (Random.rollPercent(2)) {
                    Item rolled = rollRegular();
                    p.getInventory().add(rolled);
                } else {
                    int point = Random.get(5,10);
                    p.afkPoints += point;
                }
                e.delay(3);
            }
        });
    }
    private static LootTable regularTable = new LootTable()
            .addTable(20,
                    new LootItem(250, 1, 5), // Guam leaf
                    new LootItem(252, 1, 5), // Marrentill leaf
                    new LootItem(254, 1, 4), // Tarromin leaf
                    new LootItem(256, 1, 3), // Harralander leaf
                    new LootItem(258, 1, 1), // Ranarr leaf
                    new LootItem(264, 1, 1) // Kwuarm leaf
            );

    private static Item rollRegular() {
        return regularTable.rollItem();
    }

}
