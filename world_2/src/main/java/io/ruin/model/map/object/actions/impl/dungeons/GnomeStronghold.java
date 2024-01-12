package io.ruin.model.map.object.actions.impl.dungeons;

import io.ruin.model.map.object.actions.ObjectAction;

public class GnomeStronghold {

    static {
        /**
         * Entrance/exit
         */
        ObjectAction.register(26709, 2428, 3423, 0, "enter", (player, obj) -> player.startEvent(event -> {
            player.lock();
            player.animate(2796);
            event.delay(2);
            player.resetAnimation();
            player.getMovement().teleport(2429, 9824, 0);
            player.unlock();
        }));

        ObjectAction.register(17209, 2403, 3418, 0, "enter", (player, obj) -> player.startEvent(event -> {
            player.lock();
            player.animate(2796);
            event.delay(2);
            player.resetAnimation();
            player.getMovement().teleport(2408, 9812, 0);
            player.unlock();
        }));

        ObjectAction.register(17222, 2408, 9811, 0, "enter", (player, obj) -> player.startEvent(event -> {
            player.animate(2796);
            event.delay(2);
            player.resetAnimation();
            player.getMovement().teleport(2402, 3419, 0);
            player.unlock();
        }));

        ObjectAction.register(27257, 2430, 9824, 0, "use", (player, obj) -> player.startEvent(event -> {
            player.lock();
            player.animate(2796);
            event.delay(2);
            player.resetAnimation();
            player.getMovement().teleport(2430, 3424, 0);
            player.unlock();
        }));
        ObjectAction.register(27258, 2430, 9825, 0, "use", (player, obj) -> player.startEvent(event -> {
            player.lock();
            player.animate(2796);
            event.delay(2);
            player.resetAnimation();
            player.getMovement().teleport(2430, 3424, 0);
            player.unlock();
        }));

    }
}
