package io.ruin.model.item.actions.impl.boxes.mystery;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.cache.Icon;
import io.ruin.model.World;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.YesNoDialogue;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.item.loot.LootItem;
import io.ruin.model.item.loot.LootTable;
import io.ruin.utility.Broadcast;

public class LuxuryBox {

    public static final LootTable MYSTERY_BOX_TABLE = new LootTable().addTable(1,
            new LootItem(12696, 75, 23), //75 Super combat potions
            new LootItem(13442, 200, 23), //200 Anglerfish
            new LootItem(384, 300, 20), //Raw Sharks
            new LootItem(1514, 400, 20), //Magic Logs
            new LootItem(2364, 250, 20), //Rune Bars
            new LootItem(2362, 500, 20), //Adamant Bars
            new LootItem(1618, 300, 20), //Uncut Diamonds
            new LootItem(6922, 1, 15), //Infinity gloves
            new LootItem(6918, 1, 15), //Infinity hat
            new LootItem(6916, 1, 15), //Infinity top
            new LootItem(6924, 1, 15), //Infinity bottoms
            new LootItem(4151, 1, 10), //Abyssal whip
            new LootItem(2581, 1, 10), //Robin hood hat
            new LootItem(6585, 1, 10), //Amulet of fury
            new LootItem(11283, 1, 9), //Dragonfire shield
            new LootItem(11235, 1, 9), //Dark bow
            new LootItem(2577, 1, 8), //Ranger boots
            new LootItem(6920, 1, 8), //Infinity boots
            new LootItem(12873, 1, 8), //Guthan's Set
            new LootItem(12875, 1, 8), //Verac's Set
            new LootItem(12877, 1, 8), //DH Set
            new LootItem(12879, 1, 8), //Torag's Set
            new LootItem(12881, 1, 8), //Ahrim's Set
            new LootItem(12883, 1, 8), //Karil's Set
            new LootItem(11926, 1, 8), //Odium ward
            new LootItem(11924, 1, 8), //Malediction ward
            new LootItem(6737, 1, 7).broadcast(Broadcast.WORLD), //Berserker ring
            new LootItem(6735, 1, 7).broadcast(Broadcast.WORLD), //Warriors ring
            new LootItem(6733, 1, 7).broadcast(Broadcast.WORLD), //Archers ring
            new LootItem(6731, 1, 7).broadcast(Broadcast.WORLD), //Seers ring
            new LootItem(11808, 1, 5).broadcast(Broadcast.WORLD), //Zamorak godsword
            new LootItem(11806, 1, 5).broadcast(Broadcast.WORLD), //Saradomin godsword
            new LootItem(11802, 1, 5).broadcast(Broadcast.WORLD), //Armadyl godsword
            new LootItem(11804, 1, 5).broadcast(Broadcast.WORLD), //Bandos godsword
            new LootItem(12931, 1, 5).broadcast(Broadcast.WORLD), //Serpentine helm
            new LootItem(13235, 1, 5).broadcast(Broadcast.WORLD), //Eternal boots
            new LootItem(13237, 1, 5).broadcast(Broadcast.WORLD), //Pegasian boots
            new LootItem(13239, 1, 5).broadcast(Broadcast.WORLD), //Primordial boots
            new LootItem(11785, 1, 3).broadcast(Broadcast.WORLD), //Armadyl crossbow
            new LootItem(13576, 1, 3).broadcast(Broadcast.WORLD), //Dragon warhammer
            new LootItem(12422, 1, 1).broadcast(Broadcast.WORLD), // 3rd age wand
            new LootItem(12424, 1, 1).broadcast(Broadcast.WORLD), // 3rd age bow
            new LootItem(12426, 1, 1).broadcast(Broadcast.WORLD), // 3rd age sword
            new LootItem(12437, 1, 1).broadcast(Broadcast.WORLD), // 3rd age cloak
            new LootItem(10330, 1, 1).broadcast(Broadcast.WORLD), // 3rd age range top
            new LootItem(10332, 1, 1).broadcast(Broadcast.WORLD), // 3rd age range legs
            new LootItem(10334, 1, 1).broadcast(Broadcast.WORLD), // 3rd age range coif
            new LootItem(10336, 1, 1).broadcast(Broadcast.WORLD), // 3rd age range vanbraces
            new LootItem(10338, 1, 1).broadcast(Broadcast.WORLD), // 3rd age robe top
            new LootItem(10340, 1, 1).broadcast(Broadcast.WORLD), // 3rd age robe
            new LootItem(10342, 1, 1).broadcast(Broadcast.WORLD), // 3rd age mage hat
            new LootItem(10344, 1, 1).broadcast(Broadcast.WORLD), // 3rd age amulet
            new LootItem(10346, 1, 1).broadcast(Broadcast.WORLD), // 3rd age platelegs
            new LootItem(10348, 1, 1).broadcast(Broadcast.WORLD), // 3rd age platebody
            new LootItem(10350, 1, 1).broadcast(Broadcast.WORLD), // 3rd age fullhelm
            new LootItem(10352, 1, 1).broadcast(Broadcast.WORLD), // 3rd age kiteshield
            new LootItem(1037, 1, 1).broadcast(Broadcast.WORLD) // Bunny ears

    );
    private static void gift(Player player, Item box) {
        int boxId = box.getId();
        player.stringInput("Enter player's display name:", name -> {
            if(!player.getInventory().hasId(boxId))
                return;
            name = name.replaceAll("[^a-zA-Z0-9\\s]", "");
            name = name.substring(0, Math.min(name.length(), 12));
            if (name.isEmpty()) {
                player.retryStringInput("Invalid username, try again:");
                return;
            }
            if (name.equalsIgnoreCase(player.getName())) {
                player.retryStringInput("Cannot gift yourself, try again:");
                return;
            }
            Player target = World.getPlayer(name);
            if (target == null) {
                player.retryStringInput("Player cannot be found, try again:");
                return;
            }
            if(target.getGameMode().isIronMan()) {
                player.retryStringInput("That player is an ironman and can't receive gifts!");
                return;
            }
            player.stringInput("Enter a message for " + target.getName() + ":", message -> {
                player.dialogue(new YesNoDialogue("Are you sure you want to do this?", "Gift your " + box.getDef().name + " to " + target.getName() + "?", box, () -> {
                    if(!player.getInventory().hasId(boxId))
                        return;
                    player.getInventory().remove(boxId, 1);
                    if (!target.getInventory().isFull())
                        target.getInventory().add(boxId, 1);
                    else
                        target.getBank().add(boxId, 1);
                    target.sendMessage("<img=91> " + Color.DARK_RED.wrap(player.getName() + " has just gifted you " + box.getDef().descriptiveName + "!"));
                    player.sendMessage("<img=91> " + Color.DARK_RED.wrap("You have successfully gifted your " + box.getDef().name + " to " + target.getName() + "."));
                    if (!message.isEmpty())
                        target.sendMessage("<img=91> " + Color.DARK_RED.wrap("[NOTE] " + message));
                }));
            });
        });
    }

    static {
        ItemAction.registerInventory(290, "open", (player, item) -> {
            player.lock();
            player.closeDialogue();
            Item reward;
            reward = MYSTERY_BOX_TABLE.rollItem();
            item.remove(1);
            player.getInventory().add(reward);
            if (reward.lootBroadcast != null)
                Broadcast.WORLD.sendNews(Icon.MYSTERY_BOX, "Luxury Box", "" + player.getName() + " just received " + reward.getDef().descriptiveName + "!");
            player.unlock();
        });

        /*
         * Mystery box gifting
         */
        ItemAction.registerInventory(290, "gift", LuxuryBox::gift);
    }
}

