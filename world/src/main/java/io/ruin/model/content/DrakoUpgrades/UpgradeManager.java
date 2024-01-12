package io.ruin.model.content.DrakoUpgrades;

import com.google.common.collect.Lists;
import com.google.gson.annotations.Expose;
import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.cache.ItemDef;
import io.ruin.model.activities.duelarena.Duel;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.AccessMasks;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.item.Item;
import io.ruin.model.map.object.actions.ObjectAction;

import io.ruin.utility.Broadcast;
import io.ruin.utility.Misc;
import lombok.Getter;
import lombok.Setter;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class UpgradeManager {


    @Expose
    @Setter
    @Getter
    private static Category lastCategory;

    @Expose
    @Getter
    @Setter
    private static UpgradableItems selectedItem;

    @Expose
    @Getter
    @Setter
    private UpgradableItems item;

    @Setter
    @Getter
    private transient Player player;

    private static final int[] ITEM_COMPONENTS = {
            55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94
    };

    private static final int[] REQUIRED_COMPONENTS = {
            112, 116, 120
    };

    private static final int UPGRADE_BUTTON = 107;

    public enum UpgradableItems {




// Weapon
        W0(0, Category.WEAPON, "Obsidian Spear", 30326, 75, new Item[]{new Item(11824, 2), new Item(12833), new Item(30331)}),

        W1(1, Category.WEAPON, "Armadyl Godsword (or)", 30310, 50, new Item[]{new Item(11802, 2), new Item(995, 25000000)}),

        W2(2, Category.WEAPON, "Bandos Godsword (or)", 30311, 50, new Item[]{new Item(11804, 2), new Item(995, 25000000)}),

        W3(3, Category.WEAPON, "Saradomin Godsword (or)", 30312, 50, new Item[]{new Item(11806, 2), new Item(995, 25000000)}),

        W4(4, Category.WEAPON, "Zamorak Godsword (or)", 30313, 50, new Item[]{new Item(11808, 2), new Item(995, 25000000)}),

        W5(5, Category.WEAPON, "Bloodline Chainmace", 30266, 75, new Item[]{new Item(22542), new Item(21820, 10000), new Item(24777)}),

        W6(6, Category.WEAPON, "Obsidian Whip", 30276, 75, new Item[]{new Item(4151, 2), new Item(12004, 2), new Item(19677, 250)}),

        W7(7, Category.WEAPON, "Abyssal Blood Whip", 30325, 25, new Item[]{new Item(4151), new Item(13307, 1000)}),

        W8(8, Category.WEAPON, "Holy Hammer", 30315, 75, new Item[]{new Item(13576), new Item(23620), new Item(12833)}),

        W9(9, Category.WEAPON, "infernal scythe", 30383, 100, new Item[]{new Item(22486, 2), new Item(21295, 2), new Item(30337, 50)}),

        W10(10, Category.WEAPON, "Bloodline Bow", 30265, 75, new Item[]{new Item(22547), new Item(21820, 10000), new Item(24777)}),

        W11(11, Category.WEAPON, "Magic Shortbow (i)", 12788, 100, new Item[]{new Item(861), new Item(995, 5000000)}),

        W12(12, Category.WEAPON, "Corrupted Shortbow", 30340, 50, new Item[]{new Item(12788), new Item(11235), new Item(892, 10000)}),

        W13(13, Category.WEAPON, "Zaryte Bow", 30289, 100, new Item[]{new Item(30340), new Item(565, 50000), new Item(20615)}),

        W14(14, Category.WEAPON, "Obsidian Bow", 30351, 75, new Item[]{new Item(30289), new Item(20997), new Item(19677, 250)}),

        W15(15, Category.WEAPON, "Magma Wand", 30341, 75, new Item[]{new Item(21006, 2), new Item(554, 250000), new Item(30337)}),

        W16(16, Category.WEAPON, "Corrupted Trident", 30335, 75, new Item[]{new Item(12900, 2), new Item(566, 50000), new Item(30331)}),



        // Armour
        A1(0, Category.ARMOUR, "Torva Full Helm", 30000, 100, new Item[]{new Item(12929), new Item(20615)}),

        A2(1, Category.ARMOUR, "Torva Platebody", 30001, 100, new Item[]{new Item(11832), new Item(20615)}),

        A3(2, Category.ARMOUR, "Torva Platelegs", 30002, 100, new Item[]{new Item(11834), new Item(20615)}),

        A4(3, Category.ARMOUR, "Vampyric Faceguard", 30275, 50, new Item[]{new Item(24271), new Item(24777)}),

        A5(4, Category.ARMOUR, "Lava Coif", 30261, 50, new Item[]{new Item(11826), new Item(554, 250000), new Item(30352)}),

        A6(5, Category.ARMOUR, "Obsidian Full Helm", 30278, 75, new Item[]{new Item(30000), new Item(6572, 10), new Item(19677, 250)}),

        A7(6, Category.ARMOUR, "Obsidian Platebody", 30279, 75, new Item[]{new Item(30001), new Item(6572, 10), new Item(19677, 250)}),

        A8(7, Category.ARMOUR, "Obsidian Platelegs", 30280, 75, new Item[]{new Item(30002), new Item(6572, 10), new Item(19677, 250)}),

        A9(8, Category.ARMOUR, "Fighter Torso (e)", 30317, 50, new Item[]{new Item(10551), new Item(995, 10000000)}),

        A10(9, Category.ARMOUR, "Lava D'hide Body", 30262, 50, new Item[]{new Item(11828), new Item(554, 250000), new Item(30352)}),

        A11(10, Category.ARMOUR, "Pernix Cowl", 30003, 100, new Item[]{new Item(11826), new Item(20615)}),

        A12(11, Category.ARMOUR, "Pernix Body", 30004, 100, new Item[]{new Item(11828), new Item(20615)}),

        A13(12, Category.ARMOUR, "Pernix Chaps", 30005, 100, new Item[]{new Item(11830), new Item(20615)}),

        A14(13, Category.ARMOUR, "Dragon Defender (t)", 30308, 50, new Item[]{new Item(12954), new Item(995, 10000000)}),

        A15(14, Category.ARMOUR, "Lava Chaps", 30263, 50, new Item[]{new Item(11830), new Item(554, 250000), new Item(30352)}),

        A16(15, Category.ARMOUR, "Placeholder Coif", 2581, 75, new Item[]{new Item(30003), new Item(1000), new Item(19677, 250)}),

        A17(16, Category.ARMOUR, "Placeholder Body", 12596, 75, new Item[]{new Item(30004), new Item(1000), new Item(19677, 250)}),

        A18(17, Category.ARMOUR, "Placeholder Chaps", 23249, 75, new Item[]{new Item(30005), new Item(1000), new Item(19677, 250)}),

        A19(18, Category.ARMOUR, "Corrupted Boots", 30343, 100, new Item[]{new Item(30336), new Item(30329), new Item(30330)}),

        A20(19, Category.ARMOUR, "Pegasian Boots (or)", 30329, 75, new Item[]{new Item(13237, 2), new Item(995, 10000000)}),

        A21(20, Category.ARMOUR, "Virtus Mask", 30006, 100, new Item[]{new Item(21018), new Item(20615)}),

        A22(21, Category.ARMOUR, "Virtus Robe Top", 30007, 100, new Item[]{new Item(21021), new Item(20615)}),

        A23(22, Category.ARMOUR, "Virtus Robe Legs", 30008, 100, new Item[]{new Item(21024), new Item(20615)}),

        A24(23, Category.ARMOUR, "Primordial Boots (or)", 30336, 75, new Item[]{new Item(13239, 2), new Item(995, 10000000)}),

        A25(24, Category.ARMOUR, "Eternal Boots (or)", 30330, 75, new Item[]{new Item(13235, 2), new Item(995, 10000000)}),

        A26(25, Category.ARMOUR, "Necromancer Hat", 30332, 75, new Item[]{new Item(30006), new Item(30331), new Item(19677, 250)}),

        A27(26, Category.ARMOUR, "Necromancer Robe Top", 30333, 75, new Item[]{new Item(30007), new Item(30331), new Item(19677, 250)}),

        A28(27, Category.ARMOUR, "Necromancer Robe Bottoms", 30334, 75, new Item[]{new Item(30008), new Item(30331), new Item(19677, 250)}),

        A29(28, Category.ARMOUR, "Necromancy Tome", 30345, 75, new Item[]{new Item(12825), new Item(6889, 5), new Item(30331)}),

        A30(29, Category.ARMOUR, "Obsidian Boots", 30285, 75, new Item[]{new Item(30343), new Item(30320), new Item(995, 50000000)}),

        A31(30, Category.ARMOUR, "Elite Void Top", 13072, 100, new Item[]{new Item(8839), new Item(995, 10000000)}),

        A32(31, Category.ARMOUR, "Elite Void Robe", 13073, 100, new Item[]{new Item(8840), new Item(995, 10000000)}),

        A33(32, Category.ARMOUR, "Ancestral hat (i)", 30368, 65, new Item[]{new Item(24664), new Item(24777, 3)}),

        A34(33, Category.ARMOUR, "Ancestral robe top (i)", 30369, 65, new Item[]{new Item(24666), new Item(24777, 3)}),

        A35(34, Category.ARMOUR, "Ancestral robe botten (i)", 30370, 65, new Item[]{new Item(24668), new Item(24777, 3)}),




        // Jewellery
        J1(0, Category.JEWELLERY, "Ring of Suffering (i)", 19710, 100, new Item[]{new Item(19550), new Item(995, 15000000),}),

        J2(1, Category.JEWELLERY, "Tormented Bracelet (or)", 23444, 75, new Item[]{new Item(19544, 2), new Item(995, 25000000)}),

        J3(2, Category.JEWELLERY, "Necklace of Anguish (or)", 22249, 75, new Item[]{new Item(19547, 2), new Item(995, 25000000)}),

        J4(3, Category.JEWELLERY, "Amulet of Torture (or)", 30309, 75, new Item[]{new Item(19553, 2), new Item(995, 25000000)}),

        J5(4, Category.JEWELLERY, "Occult Necklace (or)", 30307, 75, new Item[]{new Item(12002, 2), new Item(995, 25000000)}),

        J6(5, Category.JEWELLERY, "Ring of Wealth (i)", 12785, 50, new Item[]{new Item(2572), new Item(995, 50000000)}),

        J7(6, Category.JEWELLERY, "Brimstone Ring (i)", 30328, 50, new Item[]{new Item(22975), new Item(12785), new Item(995, 100000000)}),

        J8(7, Category.JEWELLERY, "Ring of the Undead", 30322, 75, new Item[]{new Item(13202), new Item(995, 25000000)}),

        J9(8, Category.JEWELLERY, "Ring of the Arachnids", 30324, 75, new Item[]{new Item(12692), new Item(995, 25000000)}),

        J10(9, Category.JEWELLERY, "Ring of the Beasts", 30323, 75, new Item[]{new Item(12691), new Item(995, 25000000)}),

        J11(10, Category.JEWELLERY, "Salve Amulet (i)", 12017, 100, new Item[]{new Item(4081), new Item(995, 5000000)}),

        J12(11, Category.JEWELLERY, "Salve Amulet (ei)", 12018, 100, new Item[]{new Item(12017), new Item(995, 10000000)}),

        J13(12, Category.JEWELLERY, "Ring of the Gods (i)", 13202, 100, new Item[]{new Item(12601), new Item(995, 5000000)}),

        J14(13, Category.JEWELLERY, "Treasonous Ring (i)", 12692, 100, new Item[]{new Item(12605), new Item(995, 5000000)}),

        J15(14, Category.JEWELLERY, "Tyrannical Ring (i)", 12691, 100, new Item[]{new Item(12603), new Item(995, 5000000)}),

        J16(15, Category.JEWELLERY, "Berserker Ring (i)", 11773, 100, new Item[]{new Item(6737), new Item(995, 5000000)}),

        J17(16, Category.JEWELLERY, "Archers Ring (i)", 11771, 100, new Item[]{new Item(6733), new Item(995, 5000000)}),

        J18(17, Category.JEWELLERY, "Seers Ring (i)", 11770, 100, new Item[]{new Item(6731), new Item(995, 5000000)}),

        J19(18, Category.JEWELLERY, "Warrior Ring (i)", 11772, 100, new Item[]{new Item(6735), new Item(995, 5000000)}),



// Pets



        // OTHER
        M1(0, Category.MISC, "Black Mask (i)", 11784, 100, new Item[]{new Item(8921), new Item(995, 10000000)}),

        M2(1, Category.MISC, "Slayer Helmet (i)", 11865, 100, new Item[]{new Item(11864), new Item(995, 10000000)}),

        M3(2, Category.MISC, "Rangers' Heart", 30350, 100, new Item[]{new Item(20724), new Item(2445, 1000), new Item(995, 25000000)}),

        M4(3, Category.MISC, "Combatants' Heart", 30349, 100, new Item[]{new Item(20724), new Item(12696, 1000), new Item(995, 25000000)}),

        M5(4, Category.MISC, "Overload Heart", 30321, 50, new Item[]{new Item(20724), new Item(30350), new Item(30349)}),

        M6(5, Category.MISC, "Corrupted Rune Pouch", 30346, 100, new Item[]{new Item(12791), new Item(995, 50000000), new Item(30331)}),

        M7(6, Category.MISC, "Obsidian Slayer Helmet", 25185, 75, new Item[]{new Item(25191), new Item(25183), new Item(25908)}),

        M8(7, Category.MISC, "Rangers' Slayer Helmet", 25191, 100, new Item[]{new Item(11865), new Item(30350), new Item(995, 10000000)}),

        M9(8, Category.MISC, "Berserkers' Slayer Helmet", 25183, 100, new Item[]{new Item(11865), new Item(30349), new Item(995, 10000000)}),

        M10(9, Category.MISC, "Wizards' Slayer Helmet", 25908, 100, new Item[]{new Item(11865), new Item(20724), new Item(995, 10000000)}),

        M11(10, Category.MISC, "Imbued Guthix Cape", 21793, 100, new Item[]{new Item(2413), new Item(995, 5000000)}),

        M12(11, Category.MISC, "Imbued Zamorak Cape", 21795, 100, new Item[]{new Item(2414), new Item(995, 5000000)}),

        M13(12, Category.MISC, "Imbued Saradomin Cape", 21791, 100, new Item[]{new Item(2412), new Item(995, 5000000)}),

        M14(13, Category.MISC, "Luxury Box", 290, 100, new Item[]{new Item(6199), new Item(30255, 20)}),

        M15(14, Category.MISC, "Mega Box", 6828, 100, new Item[]{new Item(290), new Item(30255, 25)}),

        M16(15, Category.MISC, "Golden Flippers", 30320, 50, new Item[]{new Item(6666), new Item(995, 25000000)}),

        M17(16, Category.MISC, "Bound Looting Bag", 30347, 100, new Item[]{new Item(11941), new Item(13307, 1000), new Item(995, 25000000)}),





        ;

        private final int component;
        private final Category catagory;
        private final String name;
        private final int itemid;
        private final int chance;
        private final Item[] required;

        UpgradableItems(int component, Category category, String name, int itemid, int chance, Item[] required) {
            this.component = component;
            this.catagory = category;
            this.name = name;
            this.itemid = itemid;
            this.chance = chance;
            this.required = required;
        }


        public int getComponent() {
            return component;
        }

        public Category getCategory() {
            return catagory;
        }

        public String getName() {
            return name;
        }

        public int getItemid() {
            return itemid;
        }

        public int getChance() {
            return chance;
        }

        public Item[] getRequired() {
            return required;
        }

        public static ArrayList<UpgradableItems> forCategory(Category category) {
            ArrayList<UpgradableItems> items = Lists.newArrayList();
            for (UpgradableItems item : UpgradableItems.values()) {
                if (item.catagory.equals(category)) {
                    items.add(item);
                }
            }
            return items;
        }
    }

    public enum Category {
        WEAPON,
        ARMOUR,
        JEWELLERY,
        PETS,
        MISC
    }

    public void sendInterface(Category category, Player player) {
        player.openInterface(InterfaceType.MAIN, Interface.UPGRADE_INTERFACE);
        UpgradeManager.setLastCategory(category);
        UpgradeManager.setSelectedItem(null);
        sendItems(player);
        sendInfo(true, player);
        clearRequired(player);
    }

    public static void sendInterface(Player player) {
        player.openInterface(InterfaceType.MAIN, Interface.UPGRADE_INTERFACE);
        UpgradeManager.setLastCategory(lastCategory != null ? lastCategory : Category.WEAPON);
        UpgradeManager.setSelectedItem(null);
        sendItems(player);
        sendInfo(true, player);
        clearRequired(player);
    }

    private static void sendItems(Player player) {
        List<UpgradableItems> items = UpgradableItems.forCategory(UpgradeManager.getLastCategory());
        IntStream.of(ITEM_COMPONENTS).forEach((i -> {
            boolean hide = items.stream().noneMatch(item -> i == ITEM_COMPONENTS[item.getComponent()]);
            if (hide) {
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, i, 0, new Item(-1));
            }
        }));
        items.forEach(i -> {
            player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, ITEM_COMPONENTS[i.getComponent()], 0, new Item(i.itemid));
        });
    }

    private static void selectCategory(int index, Player player) {
        Category category = Category.values()[index];
        UpgradeManager.setLastCategory(category);
        UpgradeManager.setSelectedItem(null);
        sendItems(player);
        sendInfo(true, player);
        clearRequired(player);
    }

    private static void sendInfo(boolean clear, Player player) {
        player.getPacketSender().sendString(Interface.UPGRADE_INTERFACE, 106, clear ? "0%" : UpgradeManager.getSelectedItem().getChance() + "%");
    }

    private static void clearRequired(Player player) {
        player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 112, 0, new Item(-1));
        player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 116, 0, new Item(-1));
        player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 120, 0, new Item(-1));

        player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 104, 0, new Item(-1));
    }

    private static void sendRequired(Player player) {
        int component = 0;
        Item[] requiredIds = selectedItem.getRequired();


/*        IntStream.of(ITEM_COMPONENTS).forEach((i -> {
            player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, i, 0, new Item(-1));
        }));*/

        player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 104, 0, new Item(selectedItem.itemid));

            if (requiredIds.length == 1) {
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 112, 0, requiredIds[0]);
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 116, 0, new Item(-1));
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 120, 0, new Item(-1));
            } else if (requiredIds.length == 2) {
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 112, 0, requiredIds[0]);
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 116, 0, requiredIds[1]);
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 120, 0, new Item(-1));
            } else if (requiredIds.length == 3) {
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 112, 0, requiredIds[0]);
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 116, 0, requiredIds[1]);
                player.getPacketSender().sendItems(Interface.UPGRADE_INTERFACE, 120, 0, requiredIds[2]);
            } else {
                System.out.println("Ben fucked up...");
            }
    }

    private static void clickItem(int component, Player player) {
        List<UpgradableItems> itemList = UpgradableItems.forCategory(UpgradeManager.getLastCategory());
        for (UpgradableItems item : itemList) {
            int button = ITEM_COMPONENTS[item.getComponent()];
            if (button == component) {
                UpgradeManager.setSelectedItem(item);
                sendInfo(false, player);
                sendRequired(player);
            }
        }
    }

    static boolean upgrading = false;

    private static void upgrade(Player player) {
        if (upgrading) {
            player.sendMessage("You are already trying to upgrade an item!");
            return;
        }

        if (selectedItem == null) {
            return;
        }

        Item[] requiredIds = selectedItem.getRequired();
        if (player.getInventory().containsAll(true, requiredIds)) {
            player.getInventory().removeAll(true, requiredIds);
            upgrading = true;
            player.lock();
            if (Random.rollPercent(selectedItem.chance)) {
                if (!player.SuccessfulUpgrades.containsKey(selectedItem.itemid)) {
                    player.SuccessfulUpgrades.put(selectedItem.itemid, 1);
                } else {
                    player.SuccessfulUpgrades.put(selectedItem.itemid, player.SuccessfulUpgrades.get(selectedItem.itemid) + 1);
                }

                player.getInventory().add(getSelectedItem().itemid);
                player.sendMessage(Color.DARK_GREEN.wrap("You have successfully upgraded a " + getSelectedItem().name + "."));
                Broadcast.WORLD.sendNews(player.getName() + " has just successfully upgraded a " + getSelectedItem().name + "!");




                player.unlock();
            } else {
                if (!player.FailedUpgrades.containsKey(selectedItem.itemid)) {
                    player.FailedUpgrades.put(selectedItem.itemid, 1);
                } else {
                    player.FailedUpgrades.put(selectedItem.itemid, player.FailedUpgrades.get(selectedItem.itemid) + 1);
                }
                player.unlock();
                player.sendMessage(Color.RED.wrap("You have failed to upgrade a " + getSelectedItem().name + "."));
            }
            upgrading = false;
            player.unlock();
        } else {
            player.sendMessage(Color.RED.wrap("You do not have the required items to do this."));
        }
    }

    public static void checkInterface(Player player, int itemID) {
        List<UpgradableItems> itemList = UpgradableItems.forCategory(UpgradeManager.getLastCategory());
        for (UpgradableItems item : itemList) {
            if (itemID == item.itemid) {
                UpgradeManager.setSelectedItem(item);
                sendInfo(false, player);
                sendRequired(player);
                return;
            }
        }
        ItemDef def = ItemDef.get(itemID);
        if (def == null)
            return;
        player.sendMessage(def.examine == null ? "This item has no examine" : def.examine + "<br> <col=6f0000> Low Alchemy Value: " + Duel.formatPrice(def.lowAlchValue) + ", High Alchemy Value: " + Duel.formatPrice(def.highAlchValue) + ".");
    }

        static {

            InterfaceHandler.register(Interface.UPGRADE_INTERFACE, interfaceHandler -> {

                interfaceHandler.actions[23] = (SimpleAction) p -> selectCategory(0, p);
                interfaceHandler.actions[28] = (SimpleAction) p -> selectCategory(1, p);
                interfaceHandler.actions[33] = (SimpleAction) p -> selectCategory(2, p);
                interfaceHandler.actions[38] = (SimpleAction) p -> selectCategory(3, p);
                interfaceHandler.actions[43] = (SimpleAction) p -> selectCategory(4, p);

                interfaceHandler.actions[UPGRADE_BUTTON] = (SimpleAction) UpgradeManager::upgrade;
            });
        }

}
