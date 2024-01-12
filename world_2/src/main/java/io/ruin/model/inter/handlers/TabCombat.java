package io.ruin.model.inter.handlers;

import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.AccessMasks;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.actions.SlotAction;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.item.Item;
import io.ruin.model.item.attributes.AttributeExtensions;
import io.ruin.model.item.attributes.AttributeTypes;
import io.ruin.model.item.containers.Equipment;
import io.ruin.model.skills.magic.SpellBook;
import io.ruin.model.skills.magic.spells.TargetSpell;

public class TabCombat {

    static {
        InterfaceHandler.register(Interface.COMBAT_OPTIONS, h -> {
            h.actions[4] = (SimpleAction) p -> p.getCombat().changeAttackSet(0);
            h.actions[8] = (SimpleAction) p -> p.getCombat().changeAttackSet(1);
            h.actions[12] = (SimpleAction) p -> p.getCombat().changeAttackSet(2);
            h.actions[16] = (SimpleAction) p -> p.getCombat().changeAttackSet(3);
            h.actions[21] = (SimpleAction) p -> openAutocast(p, true);
            h.actions[26] = (SimpleAction) p -> openAutocast(p, false);
            h.actions[30] = (SimpleAction) Config.AUTO_RETALIATE::toggle;
            h.actions[36] = (SimpleAction) p -> p.getCombat().toggleSpecial();
        });
        InterfaceHandler.register(Interface.AUTOCAST_SELECTION, h -> {
            h.actions[1] = (SlotAction) TabCombat::selectAutocast;
        });
    }

    private static void open(Player player, int interfaceId) {//meehhhh (Todo better interface positioning system..)
        final int parentId = player.getGameFrameId();
        final int childId = parentId == Interface.FIXED_SCREEN ? 75 : 79;
        player.getPacketSender().sendInterface(interfaceId, parentId, childId, 1);
    }

    private static void openAutocast(Player player, boolean defensive) {
        Integer autocastId = getAutocastId(player);
        if(autocastId == null) {
            player.sendMessage("Your staff can't autocast with that spellbook.");
            return;
        }
        open(player, Interface.AUTOCAST_SELECTION);
        player.getPacketSender().sendAccessMask(Interface.AUTOCAST_SELECTION, 1, 0, 52, 2);
        Config.AUTOCAST_SET.set(player, autocastId);
        Config.DEFENSIVE_CAST.set(player, defensive ? 1 : 0);
    }

    private static final int[] staff = {1379 , 1381 , 1383 , 1385 , 1387 , 1389 , 1391 , 1393 , 1395 ,
            1397 , 1399 , 1401 , 1403 , 1405 , 1407 , 1409 , 1410 , 2415 ,
            2416 , 2417 , 3053 , 3054 , 4170 , 9084 , 11787 , 11789 , 11998 ,
            12000 , 12658 , 12795 , 12796 , 20730 , 20733 , 20736 , 20739 , 21198 , 21200};

    public static void updateAutocast(Player player, boolean login) {
        Item item = player.getEquipment().get(Equipment.SLOT_WEAPON);
        for (int staff : staff) {
            if (item != null && item.getId() == staff && SpellBook.ANCIENT.isActive(player)) {
                player.targetSpellStore = player.getCombat().autocastSpell;
                player.AutoCastStore = Config.AUTOCAST.get(player);
                player.getCombat().autocastSpell = null;
                Config.AUTOCAST.set(player, 0);
                player.getCombat().updateCombatLevel();
                return;
            }
        }


        if (player.AutoCastStorer) {
            player.getCombat().autocastSpell = TargetSpell.AUTOCASTS[player.AutoCastStore];
            Config.AUTOCAST.set(player, player.AutoCastStore);
            return;
        }
        if(login) {
            int index = Config.AUTOCAST.get(player);
            player.getCombat().autocastSpell = TargetSpell.AUTOCASTS[index];
        } else {
            if(player.isVisibleInterface(Interface.AUTOCAST_SELECTION))
                open(player, Interface.COMBAT_OPTIONS);
            resetAutocast(player);
        }
    }

    public static void resetAutocast(Player player) {
        if(player.getCombat().autocastSpell != null) {
            player.targetSpellStore = player.getCombat().autocastSpell;
            player.AutoCastStore = Config.AUTOCAST.get(player);
            player.getCombat().autocastSpell = null;
            Config.AUTOCAST.set(player, 0);
            player.getCombat().updateCombatLevel();
        }
    }

    private static void selectAutocast(Player player, int slot) {
        if(slot < 0 || slot >= TargetSpell.AUTOCASTS.length)
            return;
        if(slot != 0) {
            player.getCombat().autocastSpell = TargetSpell.AUTOCASTS[slot];
            Config.AUTOCAST.set(player, slot);
        }
        open(player, Interface.COMBAT_OPTIONS);
        player.getCombat().updateWeapon(true);
        player.getCombat().updateCombatLevel();
    }

    private static Integer getAutocastId(Player player) {
        Item item = player.getEquipment().get(Equipment.SLOT_WEAPON);
        int staffId = item == null ? -1 : player.getEquipment().getId(Equipment.SLOT_WEAPON);
        int amuletId = item == null ? -1 : player.getEquipment().getId(Equipment.SLOT_AMULET);
        if (staffId == -1) //shouldn't happen
            return null;
        if (staffId == 4675) //ancient staff
            return SpellBook.ANCIENT.isActive(player) ? 4675 : null;
        if (amuletId == 12853 && staffId == 4710) //ahrims ancients
            return SpellBook.ANCIENT.isActive(player) ? 4675 : staffId;
        if (staffId == 6914 || staffId ==  20560) { //master wand
            if (SpellBook.MODERN.isActive(player)) {
                return 11791;
            } else if (SpellBook.ANCIENT.isActive(player)) {
                return 4675;
            }
            return null;
        }
        if (staffId == 22647) { //Zuriel's Staff
            if (SpellBook.MODERN.isActive(player)) {
                return 11791;
            } else if (SpellBook.ANCIENT.isActive(player)) {
                return 4675;
            }
            return null;
        }
        if (staffId == 22555) { //Thammaron's Sceptre
            if (SpellBook.MODERN.isActive(player)) {
                return 11791;
            } else if (SpellBook.ANCIENT.isActive(player)) {
                return 4675;
            }
            return null;
        }
        if (staffId == 22552) { //Thammaron's Sceptre Uncharged
            if (SpellBook.MODERN.isActive(player)) {
                return 11791;
            } else if (SpellBook.ANCIENT.isActive(player)) {
                return 4675;
            }
            return null;
        }
        //
        if (staffId == 11791 || staffId == 12904) { //staff of the dead
            boolean augmented = AttributeExtensions.hasAttribute(item, AttributeTypes.AUGMENTED);
            if (SpellBook.MODERN.isActive(player)) {
                return 11791;
            } else if (SpellBook.ANCIENT.isActive(player) && augmented) {
                return 4675;
            }
            return null;
        }
        if (staffId == 2415 || staffId == 2416 || staffId == 2417) { //God Staffs
            if (SpellBook.MODERN.isActive(player)) {
                return 11791;
            } else if (SpellBook.ANCIENT.isActive(player)) {
                return 4675;
            }
            return null;
        }
        if((staffId == 21006 || staffId == 30181) && SpellBook.ANCIENT.isActive(player)) //kodai wand
            return 4675;
        if (staffId == 1409 || staffId == 12658) //ibans staff
            return SpellBook.MODERN.isActive(player) ? 1409 : null;
        if(staffId == 4170)
            return SpellBook.MODERN.isActive(player) ? 4170 : null;
        if(staffId == 22296) { //staff of light
            if (SpellBook.MODERN.isActive(player)) {
                return 22296;
            } else if (SpellBook.ANCIENT.isActive(player)) {
                return 4675;
            }
        }
        return SpellBook.MODERN.isActive(player) ? -1 : null;
    }

}