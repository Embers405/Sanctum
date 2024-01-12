package io.ruin.model.item.actions.impl.sigils;

import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.actions.ItemAction;

public class SigilOfConsistency {

    //Sigil of Consistency
    public static final int attuned = 25993;
    public static final int unattuned = 25994;

    static {
        ItemAction.registerInventory(attuned, "unattune",(player, item) -> {
            item.setId(unattuned);
            player.SOFC = false;
            player.sendMessage("You unattune your sigil.");
            Config.SIGIL_SLOT_ONE.set(player, 0);
            player.animate(712);
            player.graphics(1970, 80, 0);
        });

        ItemAction.registerInventory(unattuned, 1, (player, item) -> {
            item.setId(attuned);
            player.SOFC = true;
            player.sendMessage("you attune your sigil.");
            Config.SIGIL_SLOT_ONE.set(player, 1);
            player.animate(712);
            player.graphics(1970, 80, 0);
        });

    }
}