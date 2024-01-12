package io.ruin.model.activities.homepvp;

import io.ruin.api.utils.Random;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerAction;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.MapListener;
import io.ruin.model.map.object.actions.ObjectAction;

public class FunPK {

    public static final Bounds FFA_FIGHT_BOUNDS = new Bounds(1714, 3757, 1727, 3775, 0);
    private static final Bounds FFA_DEATH_BOUNDS = new Bounds(1702, 3763, 1710, 3769, 0);

    private static boolean allowAttack(Player player, Player pTarget, boolean message) {
        if (!player.getPosition().inBounds(FFA_FIGHT_BOUNDS)) {
            player.sendMessage("You can't attack players from where you're standing.");
            return false;
        }
        if (!pTarget.getPosition().inBounds(FFA_FIGHT_BOUNDS)) {
            player.sendMessage("You can't attack players who aren't who haven't stepped over the line.");
            return false;
        }
        return true;
    }

    static {
//          Enter/exit bounds

        MapListener.registerBounds(FFA_FIGHT_BOUNDS)
                .onEnter(player -> {
                    player.setAction(1, PlayerAction.ATTACK);
                    player.attackPlayerListener = FunPK::allowAttack;
                    player.deathEndListener = (DeathListener.SimpleKiller) (killer) -> {
                        int points = Random.get(1,5);
                        killer.player.sendMessage("You killed "+ player.getName() + " and earned " + points + " FunPkPoints!");
                        player.setFunPkPoints(player.getFunPkPoints() + points);
                        player.getMovement().teleport(FFA_DEATH_BOUNDS.randomPosition());
                        player.sendMessage("Oh dear, you have died!");
                    };
                    Config.IN_PVP_AREA.set(player, 1);
                    player.getPacketSender().sendVarp(20003, 0);
                    player.openInterface(InterfaceType.WILDERNESS_OVERLAY, Interface.WILDERNESS_OVERLAY);
                })
                .onExit((player, logout) -> {
                    if (!logout) {
                        player.setAction(1, null);
                        player.getCombat().resetTb();
                        player.getCombat().resetKillers();
                        player.closeInterface(InterfaceType.SECONDARY_OVERLAY);
                        player.closeInterface(InterfaceType.WILDERNESS_OVERLAY);
                        Config.IN_PVP_AREA.set(player, 0);
                        player.clearHits();
                        player.pvpAttackZone = false;
                        player.attackPlayerListener = null;
                        player.deathEndListener = null;
                    }
                });
    }

}
