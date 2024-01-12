package io.ruin.model.skills.events;

import io.ruin.model.entity.player.Player;

public abstract class QuestTabEntry {
    public abstract void send(Player player);

    public abstract void select(Player player);
}
