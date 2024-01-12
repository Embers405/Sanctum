package io.ruin.model.activities.barrows;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.ItemContainer;
import io.ruin.model.item.actions.impl.Spade;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.MapListener;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.services.discord.util.Message;

public class Barrows {

    /**
     * Main area
     */

    private static final Bounds MAIN_BOUNDS = new Bounds(3546, 3267, 3583, 3308, -1);

    private static void enteredMain(Player player) {
        player.openInterface(InterfaceType.PRIMARY_OVERLAY, Interface.BARROWS);
    }

    private static void exitedMain(Player player, boolean logout) {
        if(!logout)
            player.closeInterface(InterfaceType.PRIMARY_OVERLAY);
    }

    static {
        MapListener.registerBounds(MAIN_BOUNDS)
                .onEnter(Barrows::enteredMain)
                .onExit(Barrows::exitedMain);
    }

    /**
     * Crypts area
     */

    private static final Bounds CRYPT_BOUNDS = new Bounds(3520, 9664, 3583, 9727, -1);

    private static void enteredCrypts(Player player) {
        player.openInterface(InterfaceType.PRIMARY_OVERLAY, Interface.BARROWS);
        player.getPacketSender().sendMapState(2);
        player.addEvent(e -> {
            while(player.getPosition().inBounds(CRYPT_BOUNDS)) {
                player.getPrayer().drain(1);
                e.delay(Random.get(5, 10));
            }
        });
    }

    private static void exitedCrypts(Player player, boolean logout) {
        if(!logout) {
            player.getPacketSender().resetCamera();
            player.closeInterface(InterfaceType.PRIMARY_OVERLAY);
            player.getPacketSender().sendMapState(0);
        }
    }

    static {
        MapListener.registerBounds(CRYPT_BOUNDS)
                .onEnter(Barrows::enteredCrypts)
                .onExit(Barrows::exitedCrypts);
    }

    /**
     * Actions
     */

    private static void search(Player player, BarrowsBrother brother) {
        if(player.barrowsChestBrother == null)
            player.barrowsChestBrother = Random.get(BarrowsBrother.values());
        if (!player.BarrowsMessage) {
            player.sendMessage(Color.RED, player.barrowsChestBrother.name().toLowerCase() + " is your final victim, search the chest to kill them!");
            player.BarrowsMessage = true;
        }
        if(player.barrowsChestBrother == brother) {
            return;
        }
        if(brother.isKilled(player)) {
            player.sendMessage("You've already killed this one, you need to progress!");
            return;
        }
        brother.spawn(player);
    }

    private static void loot(Player player) {
        if(player.barrowsChestBrother == null) {
            player.sendMessage("You need to kill the barrows brothers before claiming loot!");
            return;
        }
        if(!player.barrowsChestBrother.isKilled(player)) {
            player.barrowsChestBrother.spawn(player);
            return;
        }
        for(BarrowsBrother brother : BarrowsBrother.values())
            if (brother.config.get(player) != 1) {
                player.sendMessage("You need to kill all the barrows brothers because you can collect the rewards!");
                return;
            }
        player.startEvent(e -> {
            player.lock();
            player.animate(535);
            Config.BARROWS_CHEST.set(player, 1);
            ItemContainer loot = BarrowsRewards.loot(player);
            player.getPacketSender().sendClientScript(917, "ii", -1, -1);
            player.openInterface(InterfaceType.MAIN, Interface.BARROWS_LOOT);
            player.getPacketSender().sendAccessMask(Interface.BARROWS_LOOT, 3, 0, 8, 1024);
            loot.sendUpdates();
            for(Item item : loot.getItems()) {
                if(item != null) {
                    player.getInventory().addOrDrop(item.getId(), item.getAmount());
                    player.getCollectionLog().collect(item);
                }
            }
            player.sendMessage("Your Barrows chest count is: <col=FF0000>" + (++player.barrowsChestsOpened) + "</col>.");
            for(BarrowsBrother brother : BarrowsBrother.values())
                brother.config.set(player, 0);
            e.delay(2);
            player.animate(535);
            Config.BARROWS_CHEST.set(player, 0);
            player.barrowsChestBrother = null;
            player.BarrowsMessage = false;
            player.unlock();
        });
    }

    private static final Bounds AHRIM = new Bounds(3561,3285,3568,3292,0);
    private static final Bounds DHAROK = new Bounds(3571,3294,3579,3302,0);
    private static final Bounds GUTHAN = new Bounds(3573,3279,3580,3286,0);
    private static final Bounds KARIL = new Bounds(3562,3272,3568,3280,0);
    private static final Bounds TORAG = new Bounds(3550,3278,3557,3285,0);
    private static final Bounds VERAC = new Bounds(3553,3294,3560,3301,0);

    static {
        MapListener.registerBounds(AHRIM).onEnter(player -> {
            search(player, BarrowsBrother.AHRIM);
        });
        MapListener.registerBounds(DHAROK).onEnter(player -> {
            search(player, BarrowsBrother.DHAROK);
        });
        MapListener.registerBounds(GUTHAN).onEnter(player -> {
            search(player, BarrowsBrother.GUTHAN);
        });
        MapListener.registerBounds(KARIL).onEnter(player -> {
            search(player, BarrowsBrother.KARIL);
        });
        MapListener.registerBounds(TORAG).onEnter(player -> {
            search(player, BarrowsBrother.TORAG);
        });
        MapListener.registerBounds(VERAC).onEnter(player -> {
            search(player, BarrowsBrother.VERAC);
        });
        ObjectAction.register(20973, 1, (p, obj) -> {
            loot(p);
        });
    }

}
