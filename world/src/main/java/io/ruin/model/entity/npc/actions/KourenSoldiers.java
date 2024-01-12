package io.ruin.model.entity.npc.actions;

import io.ruin.api.utils.Random;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.shared.listeners.SpawnListener;
import io.ruin.model.map.Bounds;

public class KourenSoldiers  {

    static {
        SpawnListener.register(6885, npc -> {
            npc.addEvent(e -> {
                while (true) {
                    e.delay(Random.get(2, 4));
                    npc.animate(2763);
                }
            });
        });
    }
}
