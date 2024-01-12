/*
package io.ruin.model.item.actions.impl.chargable;

import io.ruin.api.utils.NumberUtils;
import io.ruin.cache.ItemDef;
import io.ruin.model.combat.RangedData;
import io.ruin.model.combat.RangedWeapon;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.YesNoDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.actions.ItemItemAction;
import io.ruin.model.item.attributes.AttributeExtensions;
import io.ruin.model.item.attributes.AttributeTypes;
import io.ruin.model.skills.Tool;
import io.ruin.model.stat.StatType;

import static io.ruin.cache.ItemID.MAGMA_MUTAGEN;
import static io.ruin.model.skills.Tool.CHISEL;

public class magma {

    private static final int SCALES = 12934;

    private static final int MAX_AMOUNT = 0x3fff;
    private static final int TAZANITE_FANG = 12922;
    private static final int UNCHARGED = 30048, CHARGED = 30338;

    public enum Dart {

        NONE(-1, null),
        BRONZE(806, RangedWeapon.BRONZE_DART.data),
        IRON(807, RangedWeapon.IRON_DART.data),
        STEEL(808, RangedWeapon.STEEL_DART.data),
        BLACK(3093, RangedWeapon.BLACK_DART.data),
        MITHRIL(809, RangedWeapon.MITHRIL_DART.data),
        ADAMANT(810, RangedWeapon.ADAMANT_DART.data),
        RUNE(811, RangedWeapon.RUNE_DART.data),
        DRAGON(11230, RangedWeapon.DRAGON_DART.data),
        AMETHYST(25849, RangedWeapon.AMETHYST_DART.data);

        public final int id;

        public final RangedData rangedData;

        Dart(int id, RangedData rangedData) {
            this.id = id;
            this.rangedData = rangedData;
        }

    }

    */
/**
     * Dismantle (Uncharged)
     *//*


    private static void dismantle(Player player, Item magma) {
        player.dialogue(
                new OptionsDialogue(
                        "Dismantle Toxic magma (empty)",
                        new Option("Yes", () -> {
                            if(!player.getInventory().contains(magma))
                                return;
                            magma.remove();
                            player.getInventory().add(SCALES, 20000);
                            player.closeDialogue();
                        }),
                        new Option("No", player::closeDialogue)
                )
        );
    }

    */
/**
     * Check
     *//*


    private static void check(Player player, Item magma) {
        String darts;
        Dart dart = getDart(magma);
        if(dart == Dart.NONE)
            darts = "None";
        else
            darts = ItemDef.get(dart.id).name + " x " + getDartAmount(magma);
        String scales;
        int scalesAmount = getScalesAmount(magma);
        System.out.println("Scales: " + scalesAmount); //prints out 16380 here
        if(scalesAmount == 0)
            scales = "0.0%, 0 scales";
        else
            scales = NumberUtils.formatOnePlace(((double) scalesAmount / MAX_AMOUNT) * 100D) + "%, " + scalesAmount + " scales";
        player.sendMessage("Darts: <col=007f00>" + darts + "</col> Scales: <col=007f00>" + scales + "</col>");
    }

    */
/**
     * Load/Unload
     *//*


    private static void load(Player player, Item magma, Item dartItem, Dart dart) {
        Dart loadedDart = getDart(magma);
        if(loadedDart != Dart.NONE && loadedDart != dart) {
            player.sendMessage("The magma currently contains a different sort of dart.");
            return;
        }
        int dartAmount = getDartAmount(magma);
        int allowedAmount = MAX_AMOUNT - dartAmount;
        if(allowedAmount == 0) {
            player.sendMessage("The magma can't hold any more darts.");
            return;
        }
        int addAmount = Math.min(allowedAmount, dartItem.getAmount());
        dartItem.incrementAmount(-addAmount);
        update(magma, dart, dartAmount + addAmount, getScalesAmount(magma));
        check(player, magma);
    }

    private static void unload(Player player, Item magma) {
        if(player.isLocked())
            return;
        Dart dart = getDart(magma);
        if(dart == Dart.NONE) {
            player.sendMessage("The magma has no darts in it.");
            return;
        }
        if(player.getInventory().add(dart.id, getDartAmount(magma)) == 0) {
            player.sendMessage("You don't have space to do that.");
            return;
        }
        update(magma, dart, 0, getScalesAmount(magma));
        player.closeDialogue();
    }

    */
/**
     * Charge/Uncharge
     *//*


    private static void charge(Player player, Item magma, Item scalesItem) {
        int scalesAmount = getScalesAmount(magma);
        int allowedAmount = MAX_AMOUNT - scalesAmount;
        if (allowedAmount == 0) {
            player.sendMessage("The magma can't hold any more scales.");
            return;
        }
        int addAmount = Math.min(allowedAmount, scalesItem.getAmount());
        scalesItem.incrementAmount(-addAmount);
        update(magma, getDart(magma), getDartAmount(magma), scalesAmount + addAmount);
        check(player, magma);
    }

    private static void uncharge(Player player, Item magma) {
        int reqSlots = 0;
        Dart dart = getDart(magma);
        int dartAmount = getDartAmount(magma);
        if(dartAmount > 0) {
            if(!player.getInventory().hasId(dart.id))
                reqSlots++;
        }
        int scalesAmount = getScalesAmount(magma);
        if(scalesAmount > 0) {
            if(!player.getInventory().hasId(SCALES))
                reqSlots++;
        }
        if(player.getInventory().getFreeSlots() < reqSlots) {
            player.sendMessage("You don't have enough inventory space to uncharge the magma.");
            return;
        }
        player.dialogue(new YesNoDialogue("Are you sure you want to uncharge it?", "If you uncharge the magma, all scales and darts will fall out.", magma, () -> {
            if(player.isLocked())
                return;
            player.lock();
            if(dartAmount > 0)
                player.getInventory().add(dart.id, dartAmount);
            if(scalesAmount > 0)
                player.getInventory().add(SCALES, scalesAmount);
            update(magma, dart, 0, 0);
            player.unlock();
        }));
    }

    public static void update(Item magma, Dart dart, int dartAmount, int scalesAmount) {
        if (dartAmount == 0)
            dart = Dart.NONE;
        int id = (dart != Dart.NONE || scalesAmount > 0) ? CHARGED : UNCHARGED;
        if(magma.getId() != id)
            magma.setId(id);
        AttributeExtensions.putAttribute(magma, AttributeTypes.AMMO_ID, dart.ordinal());
        AttributeExtensions.putAttribute(magma, AttributeTypes.AMMO_AMOUNT, dartAmount);
        AttributeExtensions.setCharges(magma, scalesAmount);
    }

    public static Dart getDart(Item magma) {
        return Dart.values()[magma.getAttributeInt(AttributeTypes.AMMO_ID, 0)];
    }

    public static int getDartAmount(Item magma) {
        return magma.getAttributeInt(AttributeTypes.AMMO_AMOUNT, 0);
    }

    public static int getScalesAmount(Item magma) {
        return AttributeExtensions.getCharges(magma);
    }

    */
/**
     * Registering
     *//*


    static {


        ItemAction.registerInventory(UNCHARGED, "dismantle", magma::dismantle);

        ItemAction.registerInventory(CHARGED, "check", magma::check);
        ItemAction.registerEquipment(CHARGED, "check", magma::check);
        ItemAction.registerInventory(CHARGED, "unload", magma::unload);
        ItemAction.registerInventory(CHARGED, "uncharge", magma::uncharge);

        ItemItemAction.register(Tool.CHISEL, TAZANITE_FANG, (player, primary, secondary) -> {
            if(!player.getStats().check(StatType.Fletching, 53, CHISEL, TAZANITE_FANG, "do that"))
                return;
            player.startEvent(event -> {
                secondary.setId(UNCHARGED);
                player.animate(3015);
                player.getStats().addXp(StatType.Fletching, 120.0, true);
                player.dialogue(new ItemDialogue().one(UNCHARGED, "You carve the fang and turn it into a powerful blowpipe."));
            });
        });

        for(Dart dart : Dart.values()) {
            if(dart != Dart.NONE) {
                ItemItemAction loadAction = (player, blowpipe, dartItem) ->
                        magma.load(player, blowpipe, dartItem, dart);
                ItemItemAction.register(UNCHARGED, dart.id, loadAction);
                ItemItemAction.register(CHARGED, dart.id, loadAction);
            }
        }

        for(Dart dart : Dart.values()) {
            if(dart != Dart.NONE) {
                ItemItemAction loadAction = (player, blowpipe, dartItem) ->
                        magma.load(player, blowpipe, dartItem, dart);
                ItemItemAction.register(UNCHARGED, dart.id, loadAction);
                ItemItemAction.register(CHARGED, dart.id, loadAction);
            }
        }


        ItemItemAction.register(UNCHARGED, SCALES, magma::charge);
        ItemItemAction.register(CHARGED, SCALES, magma::charge);

        ItemItemAction loadPoisonedAction = (player, primary, secondary) ->
                player.sendMessage("You can't use that kind of dart - the venom doesn't mix with other poisons.");
        ItemDef.forEach(def -> {
            if(def.name.toLowerCase().contains("dart(p")) {

                ItemItemAction.register(UNCHARGED, def.id, loadPoisonedAction);
                ItemItemAction.register(CHARGED, def.id, loadPoisonedAction);
            }
        });

         ItemItemAction.register(UNCHARGED, MAGMA_MUTAGEN, magma::dye);
    }

    private static void dye(Player player, Item item, Item dye) {
        player.dialogue(new YesNoDialogue("Are you sure you want to combine these?", "If you combine these items, you will NOT be able to undo it!", item, () -> {
            boolean charged = item.getId() == CHARGED;
            int id = charged ? CHARGED : UNCHARGED;
            item.setId(id);
            dye.remove();
            player.dialogue(new ItemDialogue().one(id, "You apply the mutagen to your blowpipe."));
        }));
    }

}*/
