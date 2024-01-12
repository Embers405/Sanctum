package io.ruin.model.activities.jail;

import io.ruin.api.utils.Random;
import io.ruin.api.utils.TimeUtils;
import io.ruin.cache.Color;
import io.ruin.cache.ItemID;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerAction;
import io.ruin.model.entity.shared.listeners.DeathListener;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.model.entity.shared.listeners.LogoutListener;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemNPCAction;
import io.ruin.model.item.containers.bank.Bank;
import io.ruin.model.item.containers.bank.BankItem;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Direction;
import io.ruin.model.map.Tile;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.utility.Misc;
import io.ruin.utility.Utils;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Jail {

    private static final int GUARD_ID = 5442, ROCK = 968;

    private static final Bounds BOUNDS = new Bounds(3118, 3481, 3127, 3483, 0);

    static {
        /**
         * Listener
         */
        LoginListener.register(player -> {
            if (Config.XP_COUNTER_SHOWN.get(player) == 0) {
                Config.XP_COUNTER_SHOWN.set(player, 1);
            }
            if (player.Shift_Drop) {
                Config.SHIFT_DROP.set(player, 1);
            }
            if (player.ESC_Close) {
                Config.ESCAPE_CLOSES.set(player, 1);
            }

            player.startEvent(e ->{
                e.delay(7); // Delay on login before event start else the account gets kicked.
            if(player.jailerName != null)
                startEvent(player);
            });
        });
    }


    public static void startEvent(Player player) {
        if(!player.getPosition().inBounds(BOUNDS)) {
                player.getMovement().teleport(BOUNDS.randomPosition());
        }
        player.teleportListener = p -> {
            p.sendMessage("You must finish your jail sentence before you can leave.");
            return false;
        };
        player.deathEndListener = (DeathListener.Simple) () -> {}; //no escaping lol
        player.addEvent(e -> {
            while(player.jailTime > System.currentTimeMillis())
                e.delay(10);
            player.jailerName = null;
            player.jailTime = 0;
            player.offense = "";
            player.jailed = false;
            player.teleportListener = null;
            player.deathEndListener = null;
            player.getMovement().teleport(3092, 3492, 0);
            player.sendMessage(Color.GREEN, "Your jail sentence is up.");
        });
    }
}