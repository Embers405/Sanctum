package io.ruin.model.item.actions.impl.jewellery;

import io.ruin.data.impl.teleports;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.KillCounter;
import io.ruin.model.inter.teleports.TeleportInterface;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.map.Bounds;
import io.ruin.model.map.Position;
import io.ruin.model.skills.slayer.KonarTaskLocations;
import io.ruin.model.skills.slayer.Slayer;
import io.ruin.model.skills.slayer.SlayerTask;

public enum SlayerRing {

    /**
     * Regular
     */
    EIGHT(11866, 8, 11867),
    SEVEN(11867, 7, 11868),
    SIX(11868, 6, 11869),
    FIVE(11869, 5, 11870),
    FOUR(11870, 4, 11871),
    THREE(11871, 3, 11872),
    TWO(11872, 2, 11873),
    ONE(11873, 1, -1);

    private final int id, charges, replacementId;

    SlayerRing(int id, int charges, int replacementId) {
        this.id = id;
        this.charges = charges;
        this.replacementId = replacementId;
    }

    static {
        JeweleryTeleports teleports = new JeweleryTeleports("ring", false,
                new JeweleryTeleports.Teleport("Slayer Tower", new Bounds(3428, 3545, 3429, 3535, 0)),
                new JeweleryTeleports.Teleport("Fremennik Slayer Dungeon", 2794, 3615, 0),
                new JeweleryTeleports.Teleport("Stronghold Slayer Cave", new Bounds(2433, 3421, 2435, 3423, 0)),
                new JeweleryTeleports.Teleport("Dark Beasts", new Bounds(2025, 4635, 2027, 4637, 0))
        );
        for(SlayerRing ring : values()) {
            teleports.register(ring.id, ring.charges, ring.replacementId);
            ItemAction.registerEquipment(ring.id, "check", (player, item) -> Slayer.check(player));
            ItemAction.registerInventory(ring.id, "log", (player, item) -> KillCounter.openOwnSlayer(player));

        }

        ItemAction.registerInventory(21268, "rub", (player, item) -> {
            if (player.currentSlayerMaster == 8623) {
                Bounds[] location = KonarTaskLocations.TaskLocation.valueOf(String.valueOf(player.taskLocation)).getBounds();
                for (Bounds bounds : location) {
                    if (player.getPosition().inBounds(bounds))
                        return;
                    TeleportInterface.teleport(player, bounds.randomPosition());
                }
            } else {
                for (NPC npc : World.npcs) {
                    String taskName = player.slayerTaskName.replaceAll("s$","");
                    if (npc.getDef().name.contains(taskName)) {
                        if (player.getPosition().equals(npc.getPosition()))
                            return;
                        TeleportInterface.teleport(player, npc.getPosition());
                    }
                }
            }
        });
        ItemAction.registerEquipment(21268, "check", (player, item) -> Slayer.check(player));
        ItemAction.registerInventory(21268, "check", (player, item) -> Slayer.check(player));
        ItemAction.registerEquipment(21268, "log", (player, item) -> KillCounter.openOwnSlayer(player));
    }

}
