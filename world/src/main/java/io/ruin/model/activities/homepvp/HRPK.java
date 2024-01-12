package io.ruin.model.activities.homepvp;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerAction;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.MapListener;

public class HRPK {

    private static final Bounds HIGHRISK_BOUNDS = new Bounds(1716, 3719, 1727, 3735, 0);

    private static boolean allowAttack(Player player, Player pTarget, boolean message) {
        if (!player.getPosition().inBounds(HIGHRISK_BOUNDS)) {
            player.sendMessage("You can't attack players from where you're standing.");
            return false;
        }
        if (!pTarget.getPosition().inBounds(HIGHRISK_BOUNDS)) {
            player.sendMessage("You can't attack players who aren't who haven't stepped over the line.");
            return false;
        }
        return true;
    }

    static {

    MapListener.registerBounds(HIGHRISK_BOUNDS).onEnter(player -> {
        player.setAction(1, PlayerAction.ATTACK);
        player.attackPlayerListener = HRPK::allowAttack;
        player.getCombat().skullHighRisk();
        Config.IN_PVP_AREA.set(player, 1);
        player.getPacketSender().sendVarp(20003, 0);
        player.openInterface(InterfaceType.WILDERNESS_OVERLAY, Interface.WILDERNESS_OVERLAY);
        player.pvpAttackZone = false;
        player.getPacketSender().setHidden(Interface.WILDERNESS_OVERLAY, 47, true); //hide safe area sprite
        player.getPacketSender().setHidden(Interface.WILDERNESS_OVERLAY, 53, false); //show wilderness level
    }).onExit((player, logout) -> {
        if (!logout) {
            player.setAction(1, null);
            player.getCombat().resetTb();
            player.getCombat().resetKillers();
            player.closeInterface(InterfaceType.SECONDARY_OVERLAY);
            player.closeInterface(InterfaceType.WILDERNESS_OVERLAY);
            Config.IN_PVP_AREA.set(player, 0);
            player.clearHits();
            player.attackPlayerListener = null;
        }
    });
}

}
