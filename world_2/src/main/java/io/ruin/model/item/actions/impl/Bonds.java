package io.ruin.model.item.actions.impl;

import io.ruin.api.utils.SkipLoad;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.SecondaryGroup;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;

/*
 * @project Kronos
 * @author Patrity - https://github.com/Patrity
 * Created on - 7/20/2020
 */
@SkipLoad
public class Bonds {

    private static void redeem(Player player, int amount, Item item) {
        player.dialogue(new ItemDialogue().one(item.getId(), "You are about to redeem this bond<br>" +
                "adding $"+amount+" to your amount donated.<br>" +
                "This will consume the bond forever."),
        new OptionsDialogue(
                new Option("Yes!", (player1 -> {
                    player.getInventory().remove(item);
                    player.storeAmountSpent += amount;
                    player.getInventory().add(30255, amount);
                    SecondaryGroup.getGroup(player);
                    player.sendMessage("You have redeemed a $"+amount+" Bond. Your new total is: $"+player.storeAmountSpent);
                })),
                new Option("I'll keep it for now.", player::closeDialogue)
        ));
    }

    static {
            ItemAction.registerInventory(30248, 1, ((player, item) -> redeem(player, 5, item)));
            ItemAction.registerInventory(30250, 1, ((player, item) -> redeem(player, 10, item)));
            ItemAction.registerInventory(30249, 1, ((player, item) -> redeem(player, 25, item)));
            ItemAction.registerInventory(30251, 1, ((player, item) -> redeem(player, 50, item)));
            ItemAction.registerInventory(30252, 1, ((player, item) -> redeem(player, 100, item)));
            ItemAction.registerInventory(30253, 1, ((player, item) -> redeem(player, 250, item)));
            ItemAction.registerInventory(30254, 1, ((player, item) -> redeem(player, 500, item)));
    }

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
