package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.api.utils.NumberUtils;
import io.ruin.api.utils.Random;
import io.ruin.api.utils.XenPost;
import io.ruin.cache.Color;
import io.ruin.cache.Icon;
import io.ruin.cache.ItemDef;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerGroup;
import io.ruin.model.entity.player.SecondaryGroup;
import io.ruin.model.inter.dialogue.Dialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.shop.ShopManager;
import io.ruin.services.Store;
import io.ruin.utility.Broadcast;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class CreditManager {

    static {
        NPCAction.register(2108, "Donator-Store", (player, npc) -> {
            if (player.getGameMode().isIronMan() || player.getGameMode().isHardcoreIronman() || player.getGameMode().isUltimateIronman() ||
                    player.getGameMode().isHardcoreGroupIronman() || player.getGameMode().isGroupIronman()) {
                ShopManager.openIfExists(player, "IRONMANDONATOR");
            } else {
                ShopManager.openIfExists(player, "1woleAXnpl2ZwTVj7hmvcHvgmSRiYGX3FHtr");
            }
        });
    }

    /**
     * Misc
     */
    public static SecondaryGroup getGroup(Player player) {
        if(player.storeAmountSpent >= 1000)
            return  SecondaryGroup.DRAGON;
        if(player.storeAmountSpent >= 500)
            return SecondaryGroup.RUNE;
        if(player.storeAmountSpent >= 250)
            return SecondaryGroup.ADAMANT;
        if(player.storeAmountSpent >= 150)
            return SecondaryGroup.MITHRIL;
        if(player.storeAmountSpent >= 100)
            return SecondaryGroup.GOLD;
        if(player.storeAmountSpent >= 50)
            return SecondaryGroup.IRON;
        if(player.storeAmountSpent >= 10)
            return SecondaryGroup.BRONZE;
        return null;
    }

}