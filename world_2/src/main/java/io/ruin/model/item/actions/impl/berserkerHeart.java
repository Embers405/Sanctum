package io.ruin.model.item.actions.impl;

import io.ruin.model.activities.duelarena.DuelRule;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.stat.StatType;

public class berserkerHeart {

    static {
        ItemAction.registerInventory(30349, 1, (player, item) -> {
            if(DuelRule.NO_DRINKS.isToggled(player)) {
                player.sendMessage("You cannot use an berserker Heart with drinks disabled.");
                return;
            }
            if(player.berserkerHeartCooldown.isDelayed()) {
                int delay = player.berserkerHeartCooldown.remaining();
                if(delay >= 100) {
                    int minutes = delay / 100;
                    player.sendMessage("The heart is still drained of its power. Judging by how it feels, it will be ready in around " + minutes + " minutes.");
                } else {
                    int seconds = delay / 10 * 6;
                    player.sendMessage("The heart is still drained of its power. Judging by how it feels, it will be ready in around " + seconds + " seconds.");
                }
            } else {
                player.graphics(1316);
                player.berserkerHeartCooldown.delay(200);
                player.getStats().get(StatType.Magic).boost(2, 0.18); //TODO TEST THIS OUT
                player.getStats().get(StatType.Defence).boost(2, 0.18);

                player.sendMessage("<col=FF0000>Your overload heart has regained its magical power.");
            }
        });
    }

}
