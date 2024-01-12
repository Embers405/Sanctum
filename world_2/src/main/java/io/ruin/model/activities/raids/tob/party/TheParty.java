package io.ruin.model.activities.raids.tob.party;

import io.ruin.model.entity.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

public class TheParty {
    @Getter
    @Setter
    private Player leader;
    @Getter
    @Setter
    private LinkedList<Player> members;

    public TheParty(Player leader) {
        this.leader = leader;
        members = new LinkedList<>();
        members.add(leader);
    }

    public int getSize() {
        return members.size();
    }

    public boolean addMember(Player player) {
        return members.add(player);
    }

    public void removeMember(Player player) {
        members.remove(player);
        if (members.size() > 0 && player == leader) {
            leader = members.getFirst();
        }
    }
}
