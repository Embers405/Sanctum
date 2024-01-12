package io.ruin.model.activities.homepvp;

import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.shared.StepType;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.object.GameObject;
import io.ruin.model.map.object.actions.ObjectAction;

public class PVPObjectHandler {

    static {
        ObjectAction.register(11005, 1, (player, obj) -> {
            if (!player.getFunpkWarning() && (obj.getPosition().getX() == 1705 && obj.getPosition().getY() == 3761 || obj.getPosition().getX() == 1706 && obj.getPosition().getY() == 3761)) {
                player.dialogue(
                        new NPCDialogue(7943, "You are entering SpawnFunPK, No item's are allowed within these bounds from your personal storage!"),
                        new OptionsDialogue(
                                new Option("I would like to enter.", () -> enterFunPK(player, obj)),
                                new Option("Nevermind."),
                                new Option("Enter (Don't warn me again.)", () -> {
                                    enterFunPK(player, obj);
                                    player.setFunpkWarning(true);
                                })));
                return;
            }

            enterFunPK(player, obj);

            enterFunPKGrounds(player, obj);

            enterHighRiskPK(player, obj);

        });
    }

    private static void enterFunPK(Player player, GameObject obj) {
        if (obj.getPosition().getX() == 1705 && obj.getPosition().getY() == 3761) {
            //left
            if (player.getPosition().getY() < obj.getPosition().getY()) {
                player.step(0, 2, StepType.FORCE_WALK);
                if (!player.getEquipment().isEmpty()) {
                    player.dialogue(new NPCDialogue(7943, "I've just told you, you can't bring any items into this area!"));
                    return;
                }
                if (!player.getInventory().isEmpty()) {
                    player.dialogue(new NPCDialogue(7943, "I've just told you, you can't bring any items into this area!"));
                    return;
                }
                player.teleportListener = player1 -> {
                    player.dialogue(new NPCDialogue(7943, "Nice try leave the exact same way you came in."));
                    return false;
                };
                player.setFunpk(true);
            } else {
                player.step(0, -2, StepType.FORCE_WALK);
                player.teleportListener = null;
                player.setFunpk(false);
                player.getInventory().clear();
                player.getEquipment().clear();
            }
        }
        if (obj.getPosition().getX() == 1706 && obj.getPosition().getY() == 3761) {
            //right
            if (player.getPosition().getY() < obj.getPosition().getY()) {
                player.step(0, 2, StepType.FORCE_WALK);
                if (!player.getEquipment().isEmpty()) {
                    player.dialogue(new NPCDialogue(7943, "I've just told you, you can't bring any items into this area!"));
                    return;
                }
                if (!player.getInventory().isEmpty()) {
                    player.dialogue(new NPCDialogue(7943, "I've just told you, you can't bring any items into this area!"));
                    return;
                }
                player.teleportListener = player1 -> {
                    player.dialogue(new NPCDialogue(7943, "Nice try leave the exact same way you came in."));
                    return false;
                };
                player.setFunpk(true);
            } else {
                player.step(0, -2, StepType.FORCE_WALK);
                player.teleportListener = null;
                player.getInventory().clear();
                player.getEquipment().clear();
                player.setFunpk(false);
            }
        }
    }

    private static void enterFunPKGrounds(Player player, GameObject obj) {
        if (obj.getPosition().getX() == 1713 && obj.getPosition().getY() == 3762) {
            if (player.getFunpk()) {
                if (player.getPosition().getX() < obj.getPosition().getX()) {
                    player.step(2, 0, StepType.FORCE_WALK);
                } else {
                    player.step(-2, 0, StepType.FORCE_WALK);
                }
            }
        }
        if (obj.getPosition().getX() == 1713 && obj.getPosition().getY() == 3763) {
            if (player.getFunpk()) {
                if (player.getPosition().getX() < obj.getPosition().getX()) {
                    player.step(2, 0, StepType.FORCE_WALK);
                } else {
                    player.step(-2, 0, StepType.FORCE_WALK);
                }
            }
        }
        if (obj.getPosition().getX() == 1713 && obj.getPosition().getY() == 3764) {
            if (player.getFunpk()) {
                if (player.getPosition().getX() < obj.getPosition().getX()) {
                    player.step(2, 0, StepType.FORCE_WALK);
                } else {
                    player.step(-2, 0, StepType.FORCE_WALK);
                }
            }
        }
    }

    private static void enterHighRiskPK(Player player, GameObject obj) {
        if (obj.getPosition().getX() == 1722 && obj.getPosition().getY() == 3736) {
            if (player.getPosition().getY() < obj.getPosition().getY()) {
                player.step(0, 2, StepType.FORCE_WALK);
            } else {
                player.step(0, -2, StepType.FORCE_WALK);
            }
        }
        if (obj.getPosition().getX() == 1721 && obj.getPosition().getY() == 3736) {
            if (player.getPosition().getY() < obj.getPosition().getY()) {
                player.step(0, 2, StepType.FORCE_WALK);
            } else {
                player.step(0, -2, StepType.FORCE_WALK);
            }
        }
    }


}
