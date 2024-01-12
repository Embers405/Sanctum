package io.ruin.model.map.object.actions.impl.dungeons;

import io.ruin.model.activities.motherlodemine.MotherlodeMine;
import io.ruin.model.entity.shared.listeners.SpawnListener;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.map.object.actions.ObjectAction;
import io.ruin.model.skills.slayer.Slayer;

public class KalphiteCave {

    static {
        ObjectAction.register(30180, 1, (player, obj) -> {
            MotherlodeMine.tunnel(player, 3305, 9497);
        });

        ObjectAction.register(26712, 1, (player, obj) -> player.getMovement().teleport(3321, 3122, 0));

    }

}
