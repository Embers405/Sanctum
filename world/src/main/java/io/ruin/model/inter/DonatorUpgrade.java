package io.ruin.model.inter;

import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.actions.SlotAction;

public class DonatorUpgrade {

    static {
        InterfaceHandler.register(830, h ->{//Main interface
            h.actions[21] = (SlotAction) (player, slot)-> openDonator(player);
            h.actions[25] = (SlotAction) (player, slot)-> openSuperDonator(player);
            h.actions[29] = (SlotAction) (player, slot)-> openExtremeDonator(player);
            h.actions[33] = (SlotAction) (player, slot)-> openLegendaryDonator(player);
            h.actions[37] = (SlotAction) (player, slot)-> openRespectedDonator(player);
            h.actions[41] = (SlotAction) (player, slot)-> openPlatinumDonator(player);
            h.actions[53] = (SlotAction) (player, slot)-> openStore(player);
            h.actions[271] = (SlotAction) (player, slot)-> purchaseDonator(player);
            h.actions[292] = (SlotAction) (player, slot)-> donateOsrs(player);
        });
        InterfaceHandler.register(949, h ->{//Main interface
            h.actions[21] = (SlotAction) (player, slot)-> openDonator(player);
            h.actions[25] = (SlotAction) (player, slot)-> openSuperDonator(player);
            h.actions[29] = (SlotAction) (player, slot)-> openExtremeDonator(player);
            h.actions[33] = (SlotAction) (player, slot)-> openLegendaryDonator(player);
            h.actions[37] = (SlotAction) (player, slot)-> openRespectedDonator(player);
            h.actions[41] = (SlotAction) (player, slot)-> openPlatinumDonator(player);
            h.actions[53] = (SlotAction) (player, slot)-> openStore(player);
            h.actions[271] = (SlotAction) (player, slot)-> purchaseSuperDonator(player);
            h.actions[292] = (SlotAction) (player, slot)-> donateOsrs(player);
        });
        InterfaceHandler.register(950, h ->{//Main interface
            h.actions[21] = (SlotAction) (player, slot)-> openDonator(player);
            h.actions[25] = (SlotAction) (player, slot)-> openSuperDonator(player);
            h.actions[29] = (SlotAction) (player, slot)-> openExtremeDonator(player);
            h.actions[33] = (SlotAction) (player, slot)-> openLegendaryDonator(player);
            h.actions[37] = (SlotAction) (player, slot)-> openRespectedDonator(player);
            h.actions[41] = (SlotAction) (player, slot)-> openPlatinumDonator(player);
            h.actions[53] = (SlotAction) (player, slot)-> openStore(player);
            h.actions[271] = (SlotAction) (player, slot)-> purchaseExtremeDonator(player);
            h.actions[292] = (SlotAction) (player, slot)-> donateOsrs(player);
        });
        InterfaceHandler.register(951, h ->{//Main interface
            h.actions[21] = (SlotAction) (player, slot)-> openDonator(player);
            h.actions[25] = (SlotAction) (player, slot)-> openSuperDonator(player);
            h.actions[29] = (SlotAction) (player, slot)-> openExtremeDonator(player);
            h.actions[33] = (SlotAction) (player, slot)-> openLegendaryDonator(player);
            h.actions[37] = (SlotAction) (player, slot)-> openRespectedDonator(player);
            h.actions[41] = (SlotAction) (player, slot)-> openPlatinumDonator(player);
            h.actions[53] = (SlotAction) (player, slot)-> openStore(player);
            h.actions[271] = (SlotAction) (player, slot)-> purchaseLegendaryDonator(player);
            h.actions[292] = (SlotAction) (player, slot)-> donateOsrs(player);
        });
        InterfaceHandler.register(952, h ->{//Main interface
            h.actions[21] = (SlotAction) (player, slot)-> openDonator(player);
            h.actions[25] = (SlotAction) (player, slot)-> openSuperDonator(player);
            h.actions[29] = (SlotAction) (player, slot)-> openExtremeDonator(player);
            h.actions[33] = (SlotAction) (player, slot)-> openLegendaryDonator(player);
            h.actions[37] = (SlotAction) (player, slot)-> openRespectedDonator(player);
            h.actions[41] = (SlotAction) (player, slot)-> openPlatinumDonator(player);
            h.actions[53] = (SlotAction) (player, slot)-> openStore(player);
            h.actions[271] = (SlotAction) (player, slot)-> purchaseRespectedDonator(player);
            h.actions[292] = (SlotAction) (player, slot)-> donateOsrs(player);
        });
        InterfaceHandler.register(953, h ->{//Main interface
            h.actions[21] = (SlotAction) (player, slot)-> openDonator(player);
            h.actions[25] = (SlotAction) (player, slot)-> openSuperDonator(player);
            h.actions[29] = (SlotAction) (player, slot)-> openExtremeDonator(player);
            h.actions[33] = (SlotAction) (player, slot)-> openLegendaryDonator(player);
            h.actions[37] = (SlotAction) (player, slot)-> openRespectedDonator(player);
            h.actions[41] = (SlotAction) (player, slot)-> openPlatinumDonator(player);
            h.actions[53] = (SlotAction) (player, slot)-> openStore(player);
            h.actions[271] = (SlotAction) (player, slot)-> purchasePlatinumDonator(player);
            h.actions[292] = (SlotAction) (player, slot)-> donateOsrs(player);
        });
    }

    public static void openStore(Player p) {
        p.openUrl(World.type.getWorldName() + " Store", World.type.getWebsiteUrl() + "/shop");
    }

    public static void donateOsrs(Player p) {
        p.sendMessage("Contact Jaxx on discord if you would like to donate with OSRS gp!");
    }

    public static boolean purchasedDonator;
    public static boolean purchasedSuperDonator;
    public static boolean purchasedExtremeDonator;
    public static boolean purchasedLegendaryDonator;
    public static boolean purchasedRespectedDonator;
    public static boolean purchasedPlatinumDonator;

    public static void openDonator(Player p) {
        p.openInterface(InterfaceType.MAIN, 830);
        p.getPacketSender().sendString(830, 22, "<img=19> Sapphire Donator");
        p.getPacketSender().sendString(830, 26, "<img=20> Emerald Donator");
        p.getPacketSender().sendString(830, 30, "<img=21> Ruby Donator");
        p.getPacketSender().sendString(830, 34, "<img=22> Diamond Donator");
        p.getPacketSender().sendString(830, 38, "<img=24> Onyx Donator");
        p.getPacketSender().sendString(830, 42, "<img=25> Zenyte Donator");
        p.getPacketSender().sendString(830, 277, "100m GP");
        p.getPacketSender().sendString(830, 295, "Amount Donated: <col=27ae60>$"+p.storeAmountSpent);
        p.updateRank1();
        p.getPacketSender().sendString(830, 273, "$15 Sapphire Donator Rank");
        p.getPacketSender().sendString(830, 280, "<img=32> Access to the Donator Zone (::dz)");
        p.getPacketSender().sendString(830, 281, "<img=32> Cancelling slayer task costs 27 points, rather than 30");
        p.getPacketSender().sendString(830, 282, "<img=32> Godwars killcount reduced to 20");
        p.getPacketSender().sendString(830, 283, "<img=32> Can use the Ornate Rejuve Pool every 2 minutes");
        p.getPacketSender().sendString(830, 284, "<img=32> Player can use the ::yell command every 60 seconds");
        p.getPacketSender().sendString(830, 285, "<img=32> No increased drop rate boost");
        p.getPacketSender().sendString(830, 286, "<img=32> No increase in gp from artifacts sold to emblem trader");
        p.getPacketSender().sendString(830, 287, "<img=32> No prayer drain decrease");
        p.getPacketSender().sendString(830, 288, "<img=32> Run energy drains normally in wilderness");
        if (p.storeAmountSpent <= 0) {
            p.storeAmountSpent = 0;
            p.getPacketSender().sendString(830, 297, "Current Rank: <col=27ae60>None");
        }
        if (p.storeAmountSpent >= 15 && p.storeAmountSpent < 50) {//SAPPHIRE
            p. getPacketSender().sendString(830, 297, "Current Rank: <img=19> <col=2c51e2>Sapphire");
        }
        if (p.storeAmountSpent >= 50 && p.storeAmountSpent < 100) {//EMERALD
            p.getPacketSender().sendString(830, 297, "Current Rank: <img=20> <col=27a127>Emerald");
        }
        if (p.storeAmountSpent >= 100 && p.storeAmountSpent < 250) {//RUBY
            p.getPacketSender().sendString(830, 297, "Current Rank: <img=21> <col=bd0f0f>Ruby");
        }
        if (p.storeAmountSpent >= 250 && p.storeAmountSpent < 500) {//DIAMOND
            p.getPacketSender().sendString(830, 297, "Current Rank: <img=22> <col=ffffff>Diamond");
        }
        if (p.storeAmountSpent >= 500 && p.storeAmountSpent < 1000) {//ONYX
            p.getPacketSender().sendString(830, 297, "Current Rank: <img=24> <col=616060>Onyx");
        }
        if (p.storeAmountSpent >= 1000) {//ZENYTE
            p.getPacketSender().sendString(830, 297, "Current Rank: <img=25> <col=e37e1a>Zenyte");
        }
    }
    public static void purchaseDonator(Player p) {
        if (p.storeAmountSpent >= 15) {
            p.sendMessage("You already possess this Donator Rank!");
            purchasedDonator = true;
        }
        if (p.storeAmountSpent < 15 && p.getInventory().contains(995, 100000000)) {
            p.storeAmountSpent = 15;
            p.getInventory().remove(995, 100000000);
            purchasedDonator = true;
            p.updateRank();
            p.sendMessage("You have purchased the <img=19> <col=2c51e2>Sapphire Donator</col> rank!");
        }
        if (p.storeAmountSpent < 15 && !p.getInventory().contains(995, 100000000)) {
            p.sendMessage("You do not have the required amount of GP to purchase this rank.");
        }
    }

    public static void openSuperDonator(Player p) {
        p.openInterface(InterfaceType.MAIN, 949);
        p.getPacketSender().sendString(949, 22, "<img=19> Sapphire Donator");
        p.getPacketSender().sendString(949, 26, "<img=20> Emerald Donator");
        p.getPacketSender().sendString(949, 30, "<img=21> Ruby Donator");
        p.getPacketSender().sendString(949, 34, "<img=22> Diamond Donator");
        p.getPacketSender().sendString(949, 38, "<img=24> Onyx Donator");
        p.getPacketSender().sendString(949, 42, "<img=25> Zenyte Donator");
        p.getPacketSender().sendString(949, 277, "500m GP");
        p.getPacketSender().sendString(949, 295, "Amount Donated: <col=27ae60>$"+p.storeAmountSpent);
        p.getPacketSender().sendString(949, 273, "$50 Emerald Donator Rank");
        p.getPacketSender().sendString(949, 280, "<img=32> Arenas and Tournaments give +5 more PkP");
        p.getPacketSender().sendString(949, 281, "<img=32> Barrows Chests gives x2 runes");
        p.getPacketSender().sendString(949, 282, "<img=32> Harvesting herb patches will give +3 more herbs");
        p.getPacketSender().sendString(949, 283, "<img=32> Access to the Donator Zone (::dz)");
        p.getPacketSender().sendString(949, 284, "<img=32> Cancelling slayer task costs 24 points, rather than 30");
        p.getPacketSender().sendString(949, 285, "<img=32> Godwars Killcount reduced to 10");
        p.getPacketSender().sendString(949, 286, "<img=32> Can use the Ornate Rejuve Pool every 2 minutes instead of 5");
        p.getPacketSender().sendString(949, 287, "<img=32> Can ::yell every 45 seconds");
        p.getPacketSender().sendString(949, 288, "<img=32> No increased drop rate");
        p.getPacketSender().sendString(949, 300, "<img=32> Artifacts sold to Emblem Trader give 10% more coins/pkp");
        p.getPacketSender().sendString(949, 301, "<img=32> No prayer drain decrease");
        p.getPacketSender().sendString(949, 302, "<img=32> Run energy does not drain inside the wilderness");
        if (p.storeAmountSpent <= 0) {
            p.storeAmountSpent = 0;
            p.getPacketSender().sendString(949, 297, "Current Rank: <col=27ae60>None");
        }
        if (p.storeAmountSpent >= 15 && p.storeAmountSpent < 50) {//SAPPHIRE
            p. getPacketSender().sendString(949, 297, "Current Rank: <img=19> <col=2c51e2>Sapphire");
        }
        if (p.storeAmountSpent >= 50 && p.storeAmountSpent < 100) {//EMERALD
            p.getPacketSender().sendString(949, 297, "Current Rank: <img=20> <col=27a127>Emerald");
        }
        if (p.storeAmountSpent >= 100 && p.storeAmountSpent < 250) {//RUBY
            p.getPacketSender().sendString(949, 297, "Current Rank: <img=21> <col=bd0f0f>Ruby");
        }
        if (p.storeAmountSpent >= 250 && p.storeAmountSpent < 500) {//DIAMOND
            p.getPacketSender().sendString(949, 297, "Current Rank: <img=22> <col=ffffff>Diamond");
        }
        if (p.storeAmountSpent >= 500 && p.storeAmountSpent < 1000) {//ONYX
            p.getPacketSender().sendString(949, 297, "Current Rank: <img=24> <col=616060>Onyx");
        }
        if (p.storeAmountSpent >= 1000) {//ZENYTE
            p.getPacketSender().sendString(949, 297, "Current Rank: <img=25> <col=e37e1a>Zenyte");
        }
    }
    public static void purchaseSuperDonator(Player p) {
        if (!purchasedDonator) {
            p.sendMessage("You must first purchase the previous ranks before purchasing this one.");
            return;
        }
        if (p.storeAmountSpent >= 50) {
            purchasedSuperDonator = true;
            p.sendMessage("You already possess this Donator Rank!");
        }
        if (p.storeAmountSpent < 50 && p.getInventory().contains(995, 500000000)) {
            p.storeAmountSpent = 50;
            purchasedSuperDonator = true;
            p.updateRank();
            p.getInventory().remove(995, 500000000);
            p.sendMessage("You have purchased the <img=20> <col=27a127>Emerald Donator</col> rank!");
        }
        if (p.storeAmountSpent < 50 && !p.getInventory().contains(995, 500000000)) {
            p.sendMessage("You do not have the required amount of GP to purchase this rank.");
        }
    }

    public static void openExtremeDonator(Player p) {
        p.openInterface(InterfaceType.MAIN, 950);
        p.getPacketSender().sendString(950, 22, "<img=19> Sapphire Donator");
        p.getPacketSender().sendString(950, 26, "<img=20> Emerald Donator");
        p.getPacketSender().sendString(950, 30, "<img=21> Ruby Donator");
        p.getPacketSender().sendString(950, 34, "<img=22> Diamond Donator");
        p.getPacketSender().sendString(950, 38, "<img=24> Onyx Donator");
        p.getPacketSender().sendString(950, 42, "<img=25> Zenyte Donator");
        p.getPacketSender().sendString(950, 277, "1.5b GP");
        p.getPacketSender().sendString(950, 295, "Amount Donated: <col=27ae60>$"+p.storeAmountSpent);
        p.getPacketSender().sendString(950, 273, "$100 Ruby Donator Rank");
        p.getPacketSender().sendString(950, 280, "<img=32> Access to the Donator Dungeon in ::dz");
        p.getPacketSender().sendString(950, 281, "<img=32> Ability to set obelisk destinations");
        p.getPacketSender().sendString(950, 282, "<img=32> 25% chance of keeping Enhanced C-Key on chest");
        p.getPacketSender().sendString(950, 283, "<img=32> Arenas and Tournaments give +10 more PkP");
        p.getPacketSender().sendString(950, 284, "<img=32> Barrows Chests gives x3 runes");
        p.getPacketSender().sendString(950, 285, "<img=32> Voting gives 1 extra vote ticket");
        p.getPacketSender().sendString(950, 286, "<img=32> Boot protection inside Mount Karuulm unneeded");
        p.getPacketSender().sendString(950, 287, "<img=32> Harvesting herb patches will give +5 more herbs");
        p.getPacketSender().sendString(950, 288, "<img=32> Access to the Donator Zone (::dz)");
        p.getPacketSender().sendString(950, 300, "<img=32> Cancelling slayer task costs 21 points, rather than 30");
        p.getPacketSender().sendString(950, 301, "<img=32> Godwars Killcount reduced to 5");
        p.getPacketSender().sendString(950, 302, "<img=32> Can use the Ornate Rejuve Pool every 2 minutes instead of 5");
        p.getPacketSender().sendString(950, 303, "<img=32> Can ::yell every 30 seconds");
        p.getPacketSender().sendString(950, 304, "<img=32> Larran's/Enhanced Larran's Keys drop 10% more often");
        p.getPacketSender().sendString(950, 305, "<img=32> No increased drop rate");
        p.getPacketSender().sendString(950, 306, "<img=32> Start Fight Caves on last wave");
        p.getPacketSender().sendString(950, 307, "<img=32> Artifacts sold to Emblem Trader give 20% more coins/pkp");
        p.getPacketSender().sendString(950, 308, "<img=32> Prayer drains 5% slower outside of wilderness");
        p.getPacketSender().sendString(950, 309, "<img=32> Run energy does not drain inside the wilderness");
        if (p.storeAmountSpent <= 0) {
            p.storeAmountSpent = 0;
            p.getPacketSender().sendString(950, 297, "Current Rank: <col=27ae60>None");
        }
        if (p.storeAmountSpent >= 15 && p.storeAmountSpent < 50) {//SAPPHIRE
            p. getPacketSender().sendString(950, 297, "Current Rank: <img=19> <col=2c51e2>Sapphire");
        }
        if (p.storeAmountSpent >= 50 && p.storeAmountSpent < 100) {//EMERALD
            p.getPacketSender().sendString(950, 297, "Current Rank: <img=20> <col=27a127>Emerald");
        }
        if (p.storeAmountSpent >= 100 && p.storeAmountSpent < 250) {//RUBY
            p.getPacketSender().sendString(950, 297, "Current Rank: <img=21> <col=bd0f0f>Ruby");
        }
        if (p.storeAmountSpent >= 250 && p.storeAmountSpent < 500) {//DIAMOND
            p.getPacketSender().sendString(950, 297, "Current Rank: <img=22> <col=ffffff>Diamond");
        }
        if (p.storeAmountSpent >= 500 && p.storeAmountSpent < 1000) {//ONYX
            p.getPacketSender().sendString(950, 297, "Current Rank: <img=24> <col=616060>Onyx");
        }
        if (p.storeAmountSpent >= 1000) {//ZENYTE
            p.getPacketSender().sendString(950, 297, "Current Rank: <img=25> <col=e37e1a>Zenyte");
        }
    }
    public static void purchaseExtremeDonator(Player p) {
        if (!purchasedSuperDonator) {
            p.sendMessage("You must first purchase the previous ranks before purchasing this one.");
            return;
        }
        if (p.storeAmountSpent >= 100) {
            purchasedExtremeDonator = true;
            p.sendMessage("You already possess this Donator Rank!");
        }
        if (p.storeAmountSpent < 100 && p.getInventory().contains(995, 1500000000)) {
            p.storeAmountSpent = 100;
            purchasedExtremeDonator = true;
            p.updateRank();
            p.getInventory().remove(995, 1500000000);
            p.sendMessage("You have purchased the <img=21> <col=bd0f0f>Ruby Donator</col> rank!");
        }
        if (p.storeAmountSpent < 100 && !p.getInventory().contains(995, 1500000000)) {
            p.sendMessage("You do not have the required amount of GP to purchase this rank.");
        }
    }

    public static void openLegendaryDonator(Player p) {
        p.openInterface(InterfaceType.MAIN, 951);
        p.getPacketSender().sendString(951, 22, "<img=19> Sapphire Donator");
        p.getPacketSender().sendString(951, 26, "<img=20> Emerald Donator");
        p.getPacketSender().sendString(951, 30, "<img=21> Ruby Donator");
        p.getPacketSender().sendString(951, 34, "<img=22> Diamond Donator");
        p.getPacketSender().sendString(951, 38, "<img=24> Onyx Donator");
        p.getPacketSender().sendString(951, 42, "<img=25> Zenyte Donator");
        p.getPacketSender().sendString(951, 277, "3b GP");
        p.getPacketSender().sendString(951, 295, "Amount Donated: <col=27ae60>$"+p.storeAmountSpent);
        p.getPacketSender().sendString(951, 273, "$250 Diamond Donator Rank");
        p.getPacketSender().sendString(951, 280, "<img=32> Access to the Donator Dungeon in ::dz");
        p.getPacketSender().sendString(951, 281, "<img=32> Ancient Keys drop 10% more often");
        p.getPacketSender().sendString(951, 282, "<img=32> Ability to use the ::bank command");
        p.getPacketSender().sendString(951, 283, "<img=32> Increased chance of receiving OSRS voucher from pvp");
        p.getPacketSender().sendString(951, 284, "<img=32> Ability to set obelisk destinations");
        p.getPacketSender().sendString(951, 285, "<img=32> 30% chance of keeping Enhanced C-Key on chest");
        p.getPacketSender().sendString(951, 286, "<img=32> Arenas and Tournaments give +15 more PkP");
        p.getPacketSender().sendString(951, 287, "<img=32> Barrows Chests gives x3 runes");
        p.getPacketSender().sendString(951, 288, "<img=32> Voting gives 2 extra vote ticket");
        p.getPacketSender().sendString(951, 300, "<img=32> Boot protection inside Mount Karuulm unneeded");
        p.getPacketSender().sendString(951, 301, "<img=32> Harvesting herb patches will give +7 more herbs");
        p.getPacketSender().sendString(951, 302, "<img=32> Access to the Donator Zone (::dz)");
        p.getPacketSender().sendString(951, 303, "<img=32> Cancelling slayer task costs 18 points, rather than 30");
        p.getPacketSender().sendString(951, 304, "<img=32> Godwars no longer requires killcounts");
        p.getPacketSender().sendString(951, 305, "<img=32> Can use the Ornate Rejuve Pool every 2 minutes instead of 5");
        p.getPacketSender().sendString(951, 306, "<img=32> Can ::yell every 20 seconds");
        p.getPacketSender().sendString(951, 307, "<img=32> 25% less points lost in Raids upon dying");
        p.getPacketSender().sendString(951, 308, "<img=32> Larran's/Enhanced Larran's Keys drop 15% more often");
        p.getPacketSender().sendString(951, 309, "<img=32> 3% drop rate increase");
        p.getPacketSender().sendString(951, 310, "<img=32> Start Fight Caves on last wave");
        p.getPacketSender().sendString(951, 311, "<img=32> Artifacts sold to Emblem Trader give 30% more coins/pkp");
        p.getPacketSender().sendString(951, 312, "<img=32> Prayer drains 7% slower outside of wilderness");
        p.getPacketSender().sendString(951, 313, "<img=32> Run energy does not drain inside the wilderness");
        if (p.storeAmountSpent <= 0) {
            p.storeAmountSpent = 0;
            p.getPacketSender().sendString(951, 297, "Current Rank: <col=27ae60>None");
        }
        if (p.storeAmountSpent >= 15 && p.storeAmountSpent < 50) {//SAPPHIRE
            p. getPacketSender().sendString(951, 297, "Current Rank: <img=19> <col=2c51e2>Sapphire");
        }
        if (p.storeAmountSpent >= 50 && p.storeAmountSpent < 100) {//EMERALD
            p.getPacketSender().sendString(951, 297, "Current Rank: <img=20> <col=27a127>Emerald");
        }
        if (p.storeAmountSpent >= 100 && p.storeAmountSpent < 250) {//RUBY
            p.getPacketSender().sendString(951, 297, "Current Rank: <img=21> <col=bd0f0f>Ruby");
        }
        if (p.storeAmountSpent >= 250 && p.storeAmountSpent < 500) {//DIAMOND
            p.getPacketSender().sendString(951, 297, "Current Rank: <img=22> <col=ffffff>Diamond");
        }
        if (p.storeAmountSpent >= 500 && p.storeAmountSpent < 1000) {//ONYX
            p.getPacketSender().sendString(951, 297, "Current Rank: <img=24> <col=616060>Onyx");
        }
        if (p.storeAmountSpent >= 1000) {//ZENYTE
            p.getPacketSender().sendString(951, 297, "Current Rank: <img=25> <col=e37e1a>Zenyte");
        }
    }
    public static void purchaseLegendaryDonator(Player p) {
        if (!purchasedExtremeDonator) {
            p.sendMessage("You must first purchase the previous ranks before purchasing this one.");
            return;
        }
        if (p.storeAmountSpent >= 250) {
            purchasedLegendaryDonator = true;
            p.sendMessage("You already possess this Donator Rank!");
        }
        if (p.storeAmountSpent < 250 && p.getInventory().contains(13204, 3000000)) {
            p.storeAmountSpent = 250;
            purchasedLegendaryDonator = true;
            p.updateRank();
            p.getInventory().remove(13204, 3000000);
            p.sendMessage("You have purchased the <img=22> <col=ffffff>Diamond Donator</col> rank!");
        }
        if (p.storeAmountSpent < 250 && !p.getInventory().contains(13204, 3000000)) {
            p.sendMessage("You do not have the required amount of GP to purchase this rank.");
        }
    }

    public static void openRespectedDonator(Player p) {
        p.openInterface(InterfaceType.MAIN, 952);
        p.getPacketSender().sendString(952, 22, "<img=19> Sapphire Donator");
        p.getPacketSender().sendString(952, 26, "<img=20> Emerald Donator");
        p.getPacketSender().sendString(952, 30, "<img=21> Ruby Donator");
        p.getPacketSender().sendString(952, 34, "<img=22> Diamond Donator");
        p.getPacketSender().sendString(952, 38, "<img=24> Onyx Donator");
        p.getPacketSender().sendString(952, 42, "<img=25> Zenyte Donator");
        p.getPacketSender().sendString(952, 277, "8b GP");
        p.getPacketSender().sendString(952, 295, "Amount Donated: <col=27ae60>$"+p.storeAmountSpent);
        p.getPacketSender().sendString(952, 273, "$500 Onyx Donator Rank");
        p.getPacketSender().sendString(952, 280, "<img=32> Access to the Donator Dungeon in ::dz");
        p.getPacketSender().sendString(952, 281, "<img=32> Imbueing items costs 25% less gold");
        p.getPacketSender().sendString(952, 282, "<img=32> Ancient Keys drop 15% more often");
        p.getPacketSender().sendString(952, 283, "<img=32> Ability to use the ::bank command");
        p.getPacketSender().sendString(952, 284, "<img=32> Cannon holds an extra 6 cannonballs");
        p.getPacketSender().sendString(952, 285, "<img=32> Increased chance of receiving OSRS voucher from pvp");
        p.getPacketSender().sendString(952, 286, "<img=32> Ability to set obelisk destinations");
        p.getPacketSender().sendString(952, 287, "<img=32> 35% chance of keeping Enhanced C-Key on chest");
        p.getPacketSender().sendString(952, 288, "<img=32> Arenas and Tournaments give +20 more PkP");
        p.getPacketSender().sendString(952, 300, "<img=32> Boot protection inside Mount Karuulm unneeded");
        p.getPacketSender().sendString(952, 301, "<img=32> Barrows Chests gives x5 runes");
        p.getPacketSender().sendString(952, 302, "<img=32> Voting gives 3 extra vote tickets");
        p.getPacketSender().sendString(952, 303, "<img=32> Harvesting herb patches will give +9 more herbs");
        p.getPacketSender().sendString(952, 304, "<img=32> Access to the Donator Zone (::dz)");
        p.getPacketSender().sendString(952, 305, "<img=32> Cancelling slayer task costs 15 points, rather than 30");
        p.getPacketSender().sendString(952, 306, "<img=32> Godwars no longer requires killcounts");
        p.getPacketSender().sendString(952, 307, "<img=32> Can use the Ornate Rejuve Pool at any time");
        p.getPacketSender().sendString(952, 308, "<img=32> Can ::yell every 20 seconds");
        p.getPacketSender().sendString(952, 309, "<img=32> 30% less points lost in Raids upon dying");
        p.getPacketSender().sendString(952, 310, "<img=32> Larran's/Enhanced Larran's Keys drop 20% more often");
        p.getPacketSender().sendString(952, 311, "<img=32> 3% drop rate increase");
        p.getPacketSender().sendString(952, 312, "<img=32> Start Fight Caves on last wave");
        p.getPacketSender().sendString(952, 313, "<img=32> Start Inferno on the last wave");
        p.getPacketSender().sendString(952, 314, "<img=32> Artifacts sold to Emblem Trader give 30% more coins/pkp");
        p.getPacketSender().sendString(952, 315, "<img=32> Prayer drains 10% slower outside of wilderness");
        p.getPacketSender().sendString(952, 316, "<img=32> Run energy does not drain inside the wilderness");
        if (p.storeAmountSpent <= 0) {
            p.storeAmountSpent = 0;
            p.getPacketSender().sendString(952, 297, "Current Rank: <col=27ae60>None");
        }
        if (p.storeAmountSpent >= 15 && p.storeAmountSpent < 50) {//SAPPHIRE
            p. getPacketSender().sendString(952, 297, "Current Rank: <img=19> <col=2c51e2>Sapphire");
        }
        if (p.storeAmountSpent >= 50 && p.storeAmountSpent < 100) {//EMERALD
            p.getPacketSender().sendString(952, 297, "Current Rank: <img=20> <col=27a127>Emerald");
        }
        if (p.storeAmountSpent >= 100 && p.storeAmountSpent < 250) {//RUBY
            p.getPacketSender().sendString(952, 297, "Current Rank: <img=21> <col=bd0f0f>Ruby");
        }
        if (p.storeAmountSpent >= 250 && p.storeAmountSpent < 500) {//DIAMOND
            p.getPacketSender().sendString(952, 297, "Current Rank: <img=22> <col=ffffff>Diamond");
        }
        if (p.storeAmountSpent >= 500 && p.storeAmountSpent < 1000) {//ONYX
            p.getPacketSender().sendString(952, 297, "Current Rank: <img=24> <col=616060>Onyx");
        }
        if (p.storeAmountSpent >= 1000) {//ZENYTE
            p.getPacketSender().sendString(952, 297, "Current Rank: <img=25> <col=e37e1a>Zenyte");
        }
    }
    public static void purchaseRespectedDonator(Player p) {
        if (!purchasedLegendaryDonator) {
            p.sendMessage("You must first purchase the previous ranks before purchasing this one.");
            return;
        }
        if (p.storeAmountSpent >= 500) {
            purchasedRespectedDonator = true;
            p.sendMessage("You already possess this Donator Rank!");
        }
        if (p.storeAmountSpent < 500 && p.getInventory().contains(13204, 8000000)) {
            p.storeAmountSpent = 500;
            purchasedRespectedDonator = true;
            p.updateRank();
            p.getInventory().remove(13204, 8000000);
            p.sendMessage("You have purchased the <img=24> <col=616060>Onyx Donator</col> rank!");
        }
        if (p.storeAmountSpent < 500 && !p.getInventory().contains(13204, 8000000)) {
            p.sendMessage("You do not have the required amount of GP to purchase this rank.");
        }
    }

    public static void openPlatinumDonator(Player p) {
        p.openInterface(InterfaceType.MAIN, 953);
        p.getPacketSender().sendString(953, 22, "<img=19> Sapphire Donator");
        p.getPacketSender().sendString(953, 26, "<img=20> Emerald Donator");
        p.getPacketSender().sendString(953, 30, "<img=21> Ruby Donator");
        p.getPacketSender().sendString(953, 34, "<img=22> Diamond Donator");
        p.getPacketSender().sendString(953, 38, "<img=24> Onyx Donator");
        p.getPacketSender().sendString(953, 42, "<img=25> Zenyte Donator");
        p.getPacketSender().sendString(953, 277, "15b GP");
        p.getPacketSender().sendString(953, 295, "Amount Donated: <col=27ae60>$"+p.storeAmountSpent);
        p.getPacketSender().sendString(953, 273, "$1,000 Zenyte Donator Rank");
        p.getPacketSender().sendString(953, 280, "<img=32> Access to the Donator Dungeon in ::dz");
        p.getPacketSender().sendString(953, 281, "<img=32> Imbueing items costs 50% less gold");
        p.getPacketSender().sendString(953, 282, "<img=32> Superior slayer npc's spawn 10% more often");
        p.getPacketSender().sendString(953, 283, "<img=32> Ancient Keys drop 20% more often");
        p.getPacketSender().sendString(953, 284, "<img=32> Ability to use the ::bank command");
        p.getPacketSender().sendString(953, 285, "<img=32> Cannon holds an extra 12 cannonballs");
        p.getPacketSender().sendString(953, 286, "<img=32> Increased chance of receiving OSRS voucher from pvp");
        p.getPacketSender().sendString(953, 287, "<img=32> Ability to set obelisk destinations");
        p.getPacketSender().sendString(953, 288, "<img=32> 40% chance of keeping Enhanced C-Key on chest");
        p.getPacketSender().sendString(953, 300, "<img=32> Arenas and Tournaments give +25 more PkP");
        p.getPacketSender().sendString(953, 301, "<img=32> Boot protection inside Mount Karuulm unneeded");
        p.getPacketSender().sendString(953, 302, "<img=32> Barrows Chests gives x5 runes");
        p.getPacketSender().sendString(953, 303, "<img=32> Voting gives 4 extra vote ticket");
        p.getPacketSender().sendString(953, 304, "<img=32> Harvesting herb patches will give +9 more herbs");
        p.getPacketSender().sendString(953, 305, "<img=32> Access to the Donator Zone (::dz)");
        p.getPacketSender().sendString(953, 306, "<img=32> Cancelling slayer task costs 12 points, rather than 30");
        p.getPacketSender().sendString(953, 307, "<img=32> Godwars no longer requires killcounts");
        p.getPacketSender().sendString(953, 308, "<img=32> Can use the Ornate Rejuve Pool at any time");
        p.getPacketSender().sendString(953, 309, "<img=32> Can ::yell every 10 seconds");
        p.getPacketSender().sendString(953, 310, "<img=32> 35% less points lost in Raids upon dying");
        p.getPacketSender().sendString(953, 311, "<img=32> Larran's/Enhanced Larran's Keys drop 25% more often");
        p.getPacketSender().sendString(953, 312, "<img=32> 5% drop rate increase");
        p.getPacketSender().sendString(953, 313, "<img=32> Start Fight Caves on last wave");
        p.getPacketSender().sendString(953, 314, "<img=32> Start Inferno on the last wave");
        p.getPacketSender().sendString(953, 315, "<img=32> Artifacts sold to Emblem Trader give 40% more coins/pkp");
        p.getPacketSender().sendString(953, 316, "<img=32> Prayer drains 15% slower outside of wilderness");
        p.getPacketSender().sendString(953, 317, "<img=32> Run energy does not drain inside the wilderness");
        if (p.storeAmountSpent <= 0) {
            p.storeAmountSpent = 0;
            p.getPacketSender().sendString(953, 297, "Current Rank: <col=27ae60>None");
        }
        if (p.storeAmountSpent >= 15 && p.storeAmountSpent < 50) {//SAPPHIRE
            p. getPacketSender().sendString(953, 297, "Current Rank: <img=19> <col=2c51e2>Sapphire");
        }
        if (p.storeAmountSpent >= 50 && p.storeAmountSpent < 100) {//EMERALD
            p.getPacketSender().sendString(953, 297, "Current Rank: <img=20> <col=27a127>Emerald");
        }
        if (p.storeAmountSpent >= 100 && p.storeAmountSpent < 250) {//RUBY
            p.getPacketSender().sendString(953, 297, "Current Rank: <img=21> <col=bd0f0f>Ruby");
        }
        if (p.storeAmountSpent >= 250 && p.storeAmountSpent < 500) {//DIAMOND
            p.getPacketSender().sendString(953, 297, "Current Rank: <img=22> <col=ffffff>Diamond");
        }
        if (p.storeAmountSpent >= 500 && p.storeAmountSpent < 1000) {//ONYX
            p.getPacketSender().sendString(953, 297, "Current Rank: <img=24> <col=616060>Onyx");
        }
        if (p.storeAmountSpent >= 1000) {//ZENYTE
            p.getPacketSender().sendString(953, 297, "Current Rank: <img=25> <col=e37e1a>Zenyte");
        }
    }
    public static void purchasePlatinumDonator(Player p) {
        if (!purchasedRespectedDonator) {
            p.sendMessage("You must first purchase the previous ranks before purchasing this one.");
            return;
        }
        if (p.storeAmountSpent >= 1000) {
            purchasedPlatinumDonator = true;
            p.sendMessage("You already possess this Donator Rank!");
        }
        if (p.storeAmountSpent < 1000 && p.getInventory().contains(13204, 15000000)) {
            p.storeAmountSpent = 1000;
            purchasedPlatinumDonator = true;
            p.updateRank();
            p.getInventory().remove(13204, 15000000);
            p.sendMessage("You have purchased the <img=25> <col=e37e1a>Zenyte Donator</col> rank!");
        }
        if (p.storeAmountSpent < 1000 && !p.getInventory().contains(13204, 15000000)) {
            p.sendMessage("You do not have the required amount of platinum tokens to purchase this rank.");
        }
    }
}