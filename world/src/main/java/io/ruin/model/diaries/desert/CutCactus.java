package io.ruin.model.diaries.desert;

import io.ruin.api.utils.Random;
import io.ruin.model.map.object.actions.ObjectAction;

public class CutCactus {

    static {
        ObjectAction.register(2670, 1, (player, obj) -> {
            if (Random.get(2) == 1) {
                if (player.getInventory().contains(1831)) {
                    player.getInventory().remove(1831, 1);
                    player.getInventory().add(1823, 1);
                    player.sendMessage("You top up your skin with water from the cactus");
                    player.getDiaryManager().getDesertDiary().progress(DesertDiaryEntry.CUT_CACTUS);
                }
            } else {
                player.sendMessage("You fail to cut the cactus correctly and it gives no water this time");
            }
        });
    }

}