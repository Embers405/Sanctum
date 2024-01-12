package io.ruin.model.inter.battlepass;

import com.google.gson.annotations.Expose;

public class Level {

    @Expose private int rewardLevel;
    @Expose private int rewardItemId;
    @Expose private int rewardItemAmount;
    @Expose private int rewardState;

    public Level(int rewardLevel, int rewardItemId, int rewardItemAmount, int rewardState) {
        this.rewardLevel = rewardLevel;
        this.rewardItemId = rewardItemId;
        this.rewardItemAmount = rewardItemAmount;
        this.rewardState = rewardState;
    }

    public int getRewardItemAmount() {
        return rewardItemAmount;
    }

    public int getRewardItemId() {
        return rewardItemId;
    }

    public int getRewardLevel() {
        return rewardLevel;
    }

    public int getRewardState() {
        return rewardState;
    }

    public void setRewardState(int rewardState) {
        this.rewardState = rewardState;
    }
}
