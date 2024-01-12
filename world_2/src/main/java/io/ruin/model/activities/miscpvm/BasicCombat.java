package io.ruin.model.activities.miscpvm;

import io.ruin.model.entity.npc.NPCCombat;
import io.ruin.model.skills.slayer.Slayer;

public class BasicCombat extends NPCCombat {

    @Override
    public void init() {
        npc.attackNpcListener = ((player, npc, message) -> {
            if (!Slayer.isTask(player, npc)) {
                if (player.slayerTaskName != null && !player.slayerTaskName.contains("Crystalline") && npc.getId() == 9030   ||  npc.getId() == 9028   || npc.getId() == 9032   || npc.getId() == 9034   || npc.getId() == 9033   || npc.getId() == 9026   || npc.getId() == 9027   || npc.getId() == 9029   || npc.getId() == 9031) {
                    player.sendMessage("You cannot attack monsters in this cave unless they are assigned to you by a slayer master.");
                    return false;
                }
            }
            return true;
        });
    }

    @Override
    public void follow() {
        follow(1);
    }



    @Override
    public boolean attack() {
        if(withinDistance(1)) {
            basicAttack(info.attack_animation, info.attack_style, info.max_damage);
            return true;
        }
        return false;
    }

}