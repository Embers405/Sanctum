package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.cache.ItemDef;
import io.ruin.model.diaries.varrock.VarrockDiaryEntry;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.dialogue.ActionDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.dialogue.PlayerDialogue;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemNPCAction;
import io.ruin.model.map.object.actions.impl.dungeons.Varrock;
import io.ruin.model.shop.ShopManager;

import static io.ruin.cache.ItemID.COINS_995;

public class SawmillOperator {
    public enum Plank {
        WOOD(100, 1511, 960),
        OAK(250, 1521, 8778),
        TEAK(500, 6333, 8780),
        MAHOGANY(1500, 6332, 8782);

        public final int cost, woodId, plankId;

        Plank(int cost, int woodId, int plankId) {
            this.cost = cost;
            this.woodId = woodId;
            this.plankId = plankId;
        }

        public static Plank getFromLog(int logId) {
            for (Plank p : values())
                if (p.woodId == logId)
                    return p;
            return null;
        }


        private static void convertPlank(Player player, Plank plank, int amount, boolean noted) {
            player.closeInterface(InterfaceType.MAIN);
            if (noted) {
                player.startEvent(event -> {
                    int amt = amount;
                    boolean isFree = isFree(player);

                    while (amt-- > 0) {
                        Item coins = player.getInventory().findItem(COINS_995);

                        if (!isFree && (coins == null || coins.getAmount() < plank.cost)) {
                            player.dialogue(new NPCDialogue(5422, "You'll need to bring me some more coins."));
                            return;
                        }

                        Item wood = player.getInventory().findItem(ItemDef.get(plank.woodId).notedId);
                        if (wood == null) {
                            player.dialogue(new NPCDialogue(5422, "You'll need to bring me some more logs."));
                            return;
                        }

                        if (!isFree)
                            player.getInventory().remove(COINS_995, plank.cost);

                        player.getInventory().remove(ItemDef.get(plank.woodId).notedId, 1);
                        player.getInventory().add(ItemDef.get(plank.plankId).notedId, 1);
                    }
                });
            } else {
                player.startEvent(event -> {
                    int amt = amount;
                    boolean isFree = isFree(player);

                    while (amt-- > 0) {
                        Item coins = player.getInventory().findItem(COINS_995);

                        if (!isFree && (coins == null || coins.getAmount() < plank.cost)) {
                            player.dialogue(new NPCDialogue(5422, "You'll need to bring me some more coins."));
                            return;
                        }

                        Item wood = player.getInventory().findItem(plank.woodId);
                        if (wood == null) {
                            player.dialogue(new NPCDialogue(5422, "You'll need to bring me some more logs."));
                            return;
                        }

                        if (!isFree)
                            player.getInventory().remove(COINS_995, plank.cost);

                        player.getInventory().remove(plank.woodId, 1);
                        player.getInventory().add(plank.plankId, 1);
                    }
                });
            }
            player.getDiaryManager().getVarrockDiary().progress(VarrockDiaryEntry.MAKE_PLANK);

        }

        private static boolean isFree(Player player) {
            return player.getPosition().getRegion().id == 15148;
        }

        static {
            NPCAction.register(5422, "talk-to", (player, npc) -> player.dialogue(
                    new NPCDialogue(npc, "Do you want me to make some planks for you? Or would you be interested in some other housing supplies?"),
                    new OptionsDialogue(
                            new Option("Planks please!", () -> player.dialogue(
                                    new PlayerDialogue("Planks please!"),
                                    new NPCDialogue(npc, "What kind of planks do you want?"),
                                    new OptionsDialogue(new Option("Wood", () -> {
                                        player.integerInput("How many would you like to make?", amt -> convertPlank(player, WOOD, amt, false));
                                    }),new Option("Oak", () -> {player.integerInput("How many would you like to make?", amt -> convertPlank(player, OAK, amt, false));}),
                                            new Option("Teak", () -> {player.integerInput("How many would you like to make?", amt -> convertPlank(player, TEAK, amt, false));}),
                                            new Option("Mahogany", () -> {player.integerInput("How many would you like to make?", amt -> convertPlank(player, MAHOGANY, amt, false));}))
                            )),
                            new Option("What kind of planks can you make?", () -> player.dialogue(
                                    new PlayerDialogue("What kind of planks can you make?"),
                                    new NPCDialogue(npc, "I can make planks from wood, oak, teak and mahogany. I don't make planks from other woods " +
                                            "as they're no good for making furniture."),
                                    new NPCDialogue(npc, "Wood and oak are all over the place, but teak and mahogany can only be found in a few places " +
                                            "like Karamja and Etceteria.")
                            )),
                            new Option("Can I buy some housing supplies?", () -> ShopManager.openIfExists(player, "ObsidianCONSUPPLIES")),//TODO Fill this in
                            new Option("Nothing, thanks.", () -> player.dialogue(
                                    new PlayerDialogue("Nothing, thanks."),
                                    new NPCDialogue(npc, "Well come back when you want some. You can't get good quality planks anywhere but here!")))
                    )
            ));
            NPCAction.register(3101, "talk-to", (player, npc) -> player.dialogue(
                    new NPCDialogue(npc, "Do you want me to make some planks for you? Or would you be interested in some other housing supplies?"),
                    new OptionsDialogue(
                            new Option("Planks please!", () -> player.dialogue(
                                    new PlayerDialogue("Planks please!"),
                                    new NPCDialogue(npc, "What kind of planks do you want?"),
                                    new OptionsDialogue(new Option("Wood", () -> {
                                        player.integerInput("How many would you like to make?", amt -> convertPlank(player, WOOD, amt, false));
                                    }),new Option("Oak", () -> {player.integerInput("How many would you like to make?", amt -> convertPlank(player, OAK, amt, false));}),
                                            new Option("Teak", () -> {player.integerInput("How many would you like to make?", amt -> convertPlank(player, TEAK, amt, false));}),
                                            new Option("Mahogany", () -> {player.integerInput("How many would you like to make?", amt -> convertPlank(player, MAHOGANY, amt, false));}))
                            )),
                            new Option("What kind of planks can you make?", () -> player.dialogue(
                                    new PlayerDialogue("What kind of planks can you make?"),
                                    new NPCDialogue(npc, "I can make planks from wood, oak, teak and mahogany. I don't make planks from other woods " +
                                            "as they're no good for making furniture."),
                                    new NPCDialogue(npc, "Wood and oak are all over the place, but teak and mahogany can only be found in a few places " +
                                            "like Karamja and Etceteria.")
                            )),
                            new Option("Can I buy some housing supplies?", () -> ShopManager.openIfExists(player, "ObsidianCONSUPPLIES")),//TODO Fill this in
                            new Option("Nothing, thanks.", () -> player.dialogue(
                                    new PlayerDialogue("Nothing, thanks."),
                                    new NPCDialogue(npc, "Well come back when you want some. You can't get good quality planks anywhere but here!")))
                    )
            ));
            for (Plank value : Plank.values()) {
                ItemNPCAction.register(value.woodId, 5422, (player, item, npc) -> {
                    if (item.getId() == 1511) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, WOOD, amt, false);
                        });
                    } else if (item.getId() == 1521) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, OAK, amt, false);
                        });
                    } else if (item.getId() == 6333) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, TEAK, amt, false);
                        });
                    } else if (item.getId() == 6332) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, MAHOGANY, amt, false);
                        });
                    } else {
                        player.dialogue(new NPCDialogue(5422, "That's not a log if it where I'd change it into a plank for a fee"));
                    }
                });

                ItemNPCAction.register(ItemDef.get(value.woodId).notedId, 5422, (player, item, npc) -> {
                    if (item.getId() == ItemDef.get(1511).notedId) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, WOOD, amt, true);
                        });
                    } else if (item.getId() == ItemDef.get(1521).notedId) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, OAK, amt, true);
                        });
                    } else if (item.getId() == ItemDef.get(6333).notedId) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, TEAK, amt, true);
                        });
                    } else if (item.getId() == ItemDef.get(6332).notedId) {
                        player.integerInput("How many planks would you like to make?", amt -> {
                            convertPlank(player, MAHOGANY, amt, true);
                        });
                    } else {
                        player.dialogue(new NPCDialogue(5422, "That's not a noted log if it where I'd change it into a plank for a fee"));
                    }
                });
            }
        }
    }
}
