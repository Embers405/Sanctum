package io.ruin.model.activities.tasks;

import io.ruin.model.activities.tasks.DailyTask;
import io.ruin.model.activities.tasks.DailyTask.PossibleTasksEasy;
import io.ruin.model.activities.tasks.DailyTask.PossibleTasksMedium;
import io.ruin.model.activities.tasks.DailyTask.PossibleTasksHard;
import io.ruin.model.entity.player.Player;


public class DailyTaskKills {

    public static void kills(Player player, int npcId) {
        switch (npcId) {
            case 100:
            case 102:
                DailyTask.increase(player, PossibleTasksEasy.ROCK_CRABS);
                break;
            case 970:
            case 975:
                DailyTask.increase(player, PossibleTasksEasy.DAGANNOTHS);
                break;
            case 2084:
                DailyTask.increase(player, PossibleTasksEasy.FIRE_GIANTS);
                break;
            case 2098:
                DailyTask.increase(player, PossibleTasksEasy.HILL_GIANTS);
                break;
            case 2241:
                DailyTask.increase(player, PossibleTasksEasy.HOBGOBLINS);
                break;
            case 241:
                DailyTask.increase(player, PossibleTasksEasy.BABY_BLUE_DRAGONS);
                break;
            case 891:
                DailyTask.increase(player, PossibleTasksEasy.MOSS_GIANTS);
                break;
            case 2006:
                DailyTask.increase(player, PossibleTasksEasy.LESSER_DEMONS);
                break;
            case 6593:
                DailyTask.increaseMedium(player, PossibleTasksMedium.LAVA_DRAGONS);
                break;
            case 5779:
                DailyTask.increaseMedium(player, PossibleTasksMedium.GIANT_MOLE);
                break;
            case 8031:
                DailyTask.increaseMedium(player, PossibleTasksMedium.RUNE_DRAGON);
                break;
            case 1047:
                DailyTask.increaseMedium(player, PossibleTasksMedium.CAVE_HORROR);
                break;
            case 239:
                DailyTask.increaseHard(player, PossibleTasksHard.KING_BLACK_DRAGON);
                //Achievements.Achievement.increase(player, AchievementType._13, 1);
                break;
            case 7881:
            case 7931:
            case 7932:
            case 7933:
            case 7934:
            case 7935:
            case 7936:
            case 7937:
            case 7938:
            case 7939:
            case 7940:
                //Achievements.Achievement.increase(player, AchievementType._18, 1);
                break;
            case 8061:
                //Achievements.Achievement.increase(player, AchievementType._22, 1);
                break;
            case 8615:
            case 8616:
            case 8617:
            case 8618:
            case 8619:
            case 8620:
                //Achievements.Achievement.increase(player, AchievementType._21, 1);
                break;
            case 6611:
                DailyTask.increaseHard(player, PossibleTasksHard.VETION);
                break;
            case 5862:
            case 5863:
                //Achievements.Achievement.increase(player, AchievementType._30, 1);
                break;
            case 2042:
            case 2043:
            case 2044:
                //Achievements.Achievement.increase(player, AchievementType._29, 1);
                DailyTask.increaseHard(player, PossibleTasksHard.ZULRAH);
                break;
            case 7144:
                DailyTask.increaseHard(player, PossibleTasksHard.DEMONIC_GORILLAS);
                break;
            case 7286: //skot
                DailyTask.increaseHard(player, PossibleTasksHard.SKOTIZO);
                break;
            case 259: // black drag
                DailyTask.increaseMedium(player, PossibleTasksMedium.BLACK_DRAGONS);
                break;
            case 268: //blue drag
                DailyTask.increaseMedium(player, PossibleTasksMedium.BLUE_DRAGONS);
                break;
            case 415: //abyssal demon
                DailyTask.increaseHard(player, PossibleTasksHard.ABYSSAL_DEMONS);
                break;
            case 4005: //dark beast
                DailyTask.increaseHard(player, PossibleTasksHard.DARK_BESTS);
                break;
            case 2215: //bandos
                DailyTask.increaseHard(player, PossibleTasksHard.GENERAL_GRAARDOR);
                //Achievements.Achievement.increase(player, AchievementType._26, 1);
                break;
            case 3162: //arma
                DailyTask.increaseHard(player, PossibleTasksHard.KREE_ARRA);
                break;
            case 3129: //zamorak
                DailyTask.increaseHard(player, PossibleTasksHard.TSUTSAROTH);
                break;
            case 2205: //saradomin
                DailyTask.increaseHard(player, PossibleTasksHard.ZILYANA);
                break;
            case 2919: //mith dragon
                DailyTask.increaseMedium(player, PossibleTasksMedium.MITHRIL_DRAGONS);
                break;
        }
    }
}
