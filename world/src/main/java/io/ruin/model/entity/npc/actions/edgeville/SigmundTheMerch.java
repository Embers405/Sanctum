package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.handlers.shopinterface.CustomShop;
import io.ruin.model.inter.handlers.shopinterface.CustomShopInterface;
import io.ruin.model.item.Item;

public class SigmundTheMerch {

    private final static int Sigmund = 3894;

    static {
        NPCAction.register(Sigmund, "trade", (player, npc) -> {
            player.getTrade().tradeSigmund();

        });
    }

}
