package io.ruin.model.map.object.actions.impl.prifddinas.amlodddistrict;

import io.ruin.model.activities.raids.xeric.party.RecruitingBoard;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Direction;
import io.ruin.model.map.Position;
import io.ruin.model.map.dynamic.DynamicMap;
import io.ruin.model.map.object.actions.ObjectAction;

public class TheGauntletObjects {

    public static final Bounds GAUNTLET_BOUNDS = new Bounds(new Position(1975, 5682, 1), 24);

    static {
        ObjectAction.register(36081, "enter", (player, obj) -> toGauntlet(player));
        ObjectAction.register(36082, "channel", (player, obj) -> leaveGauntlet(player));
        ObjectAction.register(37340, 1, (player, obj) -> enterGauntlet(player));
    }

    public static void toGauntlet(Player player) {
        player.lock();
       player.getPacketSender().priffadeIn();
        player.dialogue(
                new MessageDialogue("<col=880000>Welcome to The Gauntlet.")
        );
        player.getMovement().teleport(3036,6124, 1);
               player.getPacketSender().priffadeOut();
        player.unlock();
    }

    public static void leaveGauntlet(Player player) {
        player.lock();
        player.getPacketSender().priffadeIn();
        player.dialogue(
                new MessageDialogue("Welcome to the Almodd District")
        );
        player.getMovement().teleport(3228, 6116, 0);
               player.getPacketSender().priffadeOut();
        player.unlock();
    }

    public static void enterGauntlet(Player player) {
        if (player.getInventory().isNotEmpty() || player.getEquipment().isNotEmpty()) {
            player.dialogue(
                    new MessageDialogue("You can't bring your own items into The Gauntlet.")
            );
            return;
        }
    }

}
