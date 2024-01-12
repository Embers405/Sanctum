package io.ruin.model.entity.player;

import io.ruin.api.utils.ListUtils;
import io.ruin.api.utils.StringUtils;
//import io.ruin.api.utils.XenPost;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Descending order from highest priority group
 */
public enum SecondaryGroup {
    DRAGON(17,17, 49, 10),
    RUNE(16, 16, 48,8),
    ADAMANT(15, 15,47, 7),
    MITHRIL(14, 14,46, 6),
    GOLD(13, 13, 45,5),
    IRON(12, 12, 44,4),
    BRONZE(11,11, 43, 2),
    NONE(2, 0, 0, 0);

    public final int id;

    public final int clientId;

    public final int clientImgId;

    public String title;
    @Getter
    public int doubleDropChance;

    public int dropBonus;

    SecondaryGroup(int id, int clientId, int clientImgId, String title) {
        this.id = id;
        this.clientId = clientId;
        this.clientImgId = clientImgId;
        this.title = title;
    }
    SecondaryGroup(int id, int clientId, int clientImgId, double dropBonus) {
        this.id = id;
        this.clientId = clientId;
        this.clientImgId = clientImgId;
        this.dropBonus = (int) dropBonus;
        this.title = StringUtils.getFormattedEnumName(name());
    }

    SecondaryGroup(int id, int clientId, int clientImgId) {
        this(id, clientId, clientImgId, "");
    }

    public void sync(Player player, String type) {
        sync(player, type, null);
    }

    public void sync(Player player, String type, Runnable successAction) {
        CompletableFuture.runAsync(() -> {
            Map<Object, Object> map = new HashMap<>();
            map.put("userId", player.getUserId());
            map.put("type", type);
            map.put("groupId", id);
//            String result = XenPost.post("add_group", map);
//            if(successAction != null && "1".equals(result))
//                successAction.run();
        });
    }

    public void removePKMode(Player player, String type) {
        removePKMode(player, type, null);
    }

    public void removePKMode(Player player, String type, Runnable successAction) {
        CompletableFuture.runAsync(() -> {
            Map<Object, Object> map = new HashMap<>();
            map.put("userId", player.getUserId());
            map.put("type", type);
//            String result = XenPost.post("remove_group", map);
//            if(successAction != null && "1".equals(result))
//                successAction.run();
        });
    }

    public String tag() {
        return "<img=" + clientImgId + ">";
    }

    public static final SecondaryGroup[] GROUPS_BY_ID;

    static {
        int highestGroupId = 0;
        for(SecondaryGroup group : values()) {
            if(group.id > highestGroupId)
                highestGroupId = group.id;
        }
        GROUPS_BY_ID = new SecondaryGroup[highestGroupId + 1];
        for(SecondaryGroup group : values())
            GROUPS_BY_ID[group.id] = group;
    }

    public static void getGroup(Player player) {
        if(player.storeAmountSpent >= 1000) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.DRAGON.id));
        } else if(player.storeAmountSpent >= 500) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.RUNE.id));
        } else if(player.storeAmountSpent >= 250) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.ADAMANT.id));
        } else if(player.storeAmountSpent >= 150) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.MITHRIL.id));
        } else if(player.storeAmountSpent >= 100) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.GOLD.id));
        } else if(player.storeAmountSpent >= 50) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.IRON.id));
        } else if(player.storeAmountSpent >= 10) {
            player.setSecondaryGroups(ListUtils.toList(SecondaryGroup.BRONZE.id));
        }
    }

}