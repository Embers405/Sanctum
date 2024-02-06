package io.ruin.model.entity.player;

import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import lombok.Getter;

public enum XpMode {

    EASY("Easy", 200, 100, 150, 0),
    MEDIUM("Normal", 100, 50, 75, 0),
    HARD("Hard", 50, 25, 33, 1),
    ELITE("Elite", 25, 12, 17, 5),
    ZEZIMA("ZEZIMA", 3, 3, 3, 25)
    ;

    @Getter
    private final String name;

    @Getter
    private final int combatRate, skillRate, after99Rate, dropBonus;

    XpMode(String name, int combatRate, int skillRate, int after99Rate, int dropBonus) {
        this.name = name;
        this.combatRate = combatRate;
        this.skillRate = skillRate;
        this.after99Rate = after99Rate;
        this.dropBonus = dropBonus;
    }

    public static boolean isXpMode(Player player, XpMode mode) {
        return player.xpMode == mode;
    }

    public static void changeMode(Player player, NPC npc) {
        /*if (player.xpMode.ordinal() < 2) {
            XpMode newMode = EASY;

            if (isXpMode(player, HARD))
                newMode = MEDIUM;

            player.dialogue(
                    new NPCDialogue(npc, "You are about to go from<br>" +
                            "<col=ff0000>"+player.xpMode.getName()+" mode</col> to<col=ff0000>"+newMode.getName()+" mode</col>.<br>" +
                            "Combat XP: "+newMode.getCombatRate()+"x. Skilling XP: "+newMode.getSkillRate()+"x."),
                    new OptionsDialogue("<col=ff0000>THIS CANNOT BE UNDONE! ARE YOU SURE?<col>",
                            new Option("YES!", () -> {
                                XpMode newMode1 = EASY;
                                if (isXpMode(player, HARD))
                                    newMode1 = MEDIUM;
                                player.xpMode = newMode1;
                                player.sendMessage("Your XP Mode has been set to "+player.xpMode.getName());
                            }),
                            new Option("On second thought...", player::closeDialogue))
            );
        } else {*/
            player.dialogue(
                    new NPCDialogue(npc, "You're already on the Easiest XP Mode.<br>" +
                            "It doesn't get any easier than that...")
            );
   //     }
    }

    public static void setXpMode(Player player, XpMode xpMode) {
        player.xpMode = xpMode;
    }


}
