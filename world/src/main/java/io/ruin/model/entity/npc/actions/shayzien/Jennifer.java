package io.ruin.model.entity.npc.actions.shayzien;

import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.shop.ShopManager;

public class Jennifer {
    static {
        NPCAction.register(305, "talk-to", (player, npc) -> player.dialogue(
                new NPCDialogue(npc, "What're yer looking for, me luv'?"),
                new OptionsDialogue(
                        new Option("Let's trade.", () -> {
                            player.dialogue(
                                    new NPCDialogue(npc, "Well me luv', I've got some special rations for the soldiers in the fields ou'there; courtesy of the mainland Void Knights. Take a looksie."),
                                    new OptionsDialogue(
                                            new Option("Take a look (Open shop)", () -> ShopManager.openIfExists(player, "130e716b-eb95-479b-ab1e-2a3dbdc52c6f")),
                                            new Option("I don't need anything right now.", () -> player.dialogue(new PlayerDialogue("I don't need anything right now.")))
                                    )
                            );
                        })
                )
        ));

        NPCAction.register(305, "trade", (player, npc) -> {
            ShopManager.openIfExists(player, "TkM8uJwxziv95RI4khtS6DMFgGuqrJ");
        });
    }
}
