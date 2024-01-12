package io.ruin.model.inter.journal.toggles;

import io.ruin.model.entity.player.Player;

import static io.ruin.model.inter.journal.JournalTab.TextField;
import static io.ruin.model.inter.journal.JournalTab.TabComponent;

public abstract class JournalToggle {

    public abstract void handle(Player player);

    public abstract TextField getText();

    public abstract TabComponent getComponent();

    public void send(Player player) {
        getComponent().send(player);
        onSend(player);
    }

    public void onSend(Player player) { }

}
