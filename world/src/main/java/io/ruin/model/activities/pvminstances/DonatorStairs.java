package io.ruin.model.activities.pvminstances;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerGroup;
import io.ruin.model.entity.player.SecondaryGroup;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

public class DonatorStairs {

	private static void climbMiddle(Player player, GameObject object) {
		if (player.isGroups(SecondaryGroup.BRONZE)) {
			player.dialogue(new NPCDialogue(2108, "Hold on, that's not yours to enter."),
					new PlayerDialogue("What do you mean..?"),
					new NPCDialogue(2108, "The upper level of the island, the VIP layer, is only available to Super Donators or higher. It looks like you don't qualify yet, unfortunately."),
					new PlayerDialogue("So what do I do?"),
					new NPCDialogue(2108, "Simply put, purchasing additional things in the store for a total of 50$, minus what you've spent thus far, lets you enter!"),
					new PlayerDialogue("I can enter everywhere? Even upstairs?"),
					new NPCDialogue(2108, "Precisely! TUpstairs, a large VIP zone awaits your entry! Would you like to view the store?"),
					new OptionsDialogue("Open the store?", new Option("Yes please, I'll take a look.", DonatorSteppingStones::openStore), new Option("No thank you.")));
			return;
		}

		player.getMovement().teleport(3810, 2845, 1);
	}

	static {
		ObjectAction.register(32543, "Climb-up", DonatorStairs::climbMiddle);
	}

}
