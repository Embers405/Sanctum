package io.ruin.model.entity.npc.actions.edgeville;

import io.ruin.cache.Color;
import io.ruin.cache.NPCDef;
import io.ruin.data.impl.Help;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.npc.NPCAction;
import io.ruin.model.entity.player.GameMode;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerAction;
import io.ruin.model.entity.player.XpMode;
import io.ruin.model.entity.shared.LockType;
import io.ruin.model.entity.shared.listeners.LoginListener;
import io.ruin.model.entity.shared.listeners.LogoutListener;
import io.ruin.model.entity.shared.listeners.SpawnListener;
import io.ruin.model.inter.AccessMasks;
import io.ruin.model.inter.Interface;
import io.ruin.model.inter.InterfaceHandler;
import io.ruin.model.inter.InterfaceType;
import io.ruin.model.inter.actions.SimpleAction;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.handlers.XpCounter;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.inter.utils.Option;
import io.ruin.model.map.Direction;
import io.ruin.model.stat.StatType;
import io.ruin.network.central.CentralClient;
import io.ruin.utility.Broadcast;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

import static io.ruin.cache.ItemID.COINS_995;

@Slf4j
public class StarterGuide {

    private static final NPC GUIDE = SpawnListener.first(306);

    static {
        InterfaceHandler.register(1020, h -> {

            h.actions[2] = (SimpleAction) p -> p.closeInterface(InterfaceType.MAIN);

            h.actions[22] = (SimpleAction) p -> { // EASY
                setXpMode(p, XpMode.EASY);
                getInfo(p);
            };

            h.actions[23] = (SimpleAction) p -> { // MEDIUM
                setXpMode(p, XpMode.MEDIUM);
                getInfo(p);
            };

            h.actions[24] = (SimpleAction) p -> { // HARD
                setXpMode(p, XpMode.HARD);
                getInfo(p);
            };

            h.actions[25] = (SimpleAction) p -> { // HARD
                setXpMode(p, XpMode.ELITE);
                getInfo(p);
            };

            h.actions[26] = (SimpleAction) p -> { // zezima
                setXpMode(p, XpMode.ZEZIMA);
                getInfo(p);
            };

            h.actions[34] = (SimpleAction) p -> { // Regular
                Config.IRONMAN_MODE.set(p, 0);
                getInfo(p);
            };

            h.actions[35] = (SimpleAction) p -> { // Ironman
                Config.IRONMAN_MODE.set(p, 1);
                getInfo(p);
            };

            h.actions[36] = (SimpleAction) p -> { // Hardcore Ironman
                Config.IRONMAN_MODE.set(p, 3);
                getInfo(p);
            };

            h.actions[37] = (SimpleAction) p -> { // group iron man
                Config.IRONMAN_MODE.set(p, 4);
                getInfo(p);
            };
            h.actions[38] = (SimpleAction) p -> { // HC group iron man
                Config.IRONMAN_MODE.set(p, 5);
                getInfo(p);
            };





        });

        NPCDef.get(306).ignoreOccupiedTiles = true;
        NPCAction.register(GUIDE, "view-help", (player, npc) -> Help.open(player));
        NPCAction.register(GUIDE, "view-guide", (player, npc) -> player.dialogue(
                new OptionsDialogue("Watch the guide?",
                        new Option("Yes", () -> tutorial(player)),
                        new Option("No", player::closeDialogue))
        ));
        NPCAction.register(GUIDE, "talk-to", StarterGuide::optionsDialogue);

        LoginListener.register(player -> {
            for (Player p : World.players) {
                if (p.newPlayer && !p.inTutorial) {
                    player.startEvent(e -> {
                        Config.IRONMAN_MODE.set(player, 0);
                        setXpMode(player, XpMode.MEDIUM);
                        player.lock(LockType.FULL_ALLOW_LOGOUT);
                        player.openInterface(InterfaceType.MAIN, Interface.STARTER_INTERFACE);
                        getInfo(player);
                        while (player.isVisibleInterface(Interface.STARTER_INTERFACE)) {
                            e.delay(1);
                        }
                        player.unlock();
                        tutorial(player);
                    });
                }
            }
            if (!player.ipAddress.equals(player.LastipAddress) && player.LastipAddress != null) {
                if (player.isStaff()) {
                    player.lock();
                    player.forceLogout();
                }
                for (Player p : World.players) {
                    if (p.isStaff()) {
                        p.sendMessage(Color.RED,player.getName() + " has logged in from an ip address that doesn't match there last ip!");
                    }
                }
                player.LastipAddress = player.ipAddress;
            } else {
                player.LastipAddress = player.ipAddress;
            }
        });
    }

    private static void optionsDialogue(Player player, NPC npc) {
        player.dialogue(new NPCDialogue(npc, "Hello " + player.getName() + ", is there something I could assist you with?"),
                new OptionsDialogue(
                        new Option("View help pages", () -> Help.open(player)),
                        new Option("Replay tutorial", () -> ecoTutorial(player)),
                        new Option("Change home point", () -> {
                            npc.startEvent(event -> {
                                if (!player.edgeHome) {
                                    player.dialogue(new NPCDialogue(npc, "I can move your spawn point and <br>" +
                                                    "home teleport location to Edgeville.<br>" +
                                                    "it will cost 5,000,000 GP.<br>" +
                                                    "Would you like to do this?"),
                                            new OptionsDialogue(
                                                    new Option("No.. I like this home.", player::closeDialogue),
                                                    new Option("Yes! I want to respawn in Edgeville!", () -> {
                                                        if (player.getInventory().hasItem(995, 5000000)) {
                                                            player.getInventory().remove(995, 5000000);
                                                            player.edgeHome = true;
                                                            player.dialogue(new NPCDialogue(npc, "Your spawn point has been changed<br>" +
                                                                    "to Edgeville! If you'd like to change<br>" +
                                                                    "it back, just speak to me again."));
                                                        } else {
                                                            player.dialogue(new NPCDialogue(npc, "I'm sorry but it doesn't look like<br>" +
                                                                    "you can afford this.."));
                                                        }
                                                    })));
                                } else {
                                    player.dialogue(
                                            new NPCDialogue(npc, "Are you wanting to move your<br>" +
                                                    "spawn point back to home? It will cost<br>" +
                                                    "another 5,000,000 GP."),
                                            new OptionsDialogue(
                                                    new Option("No thanks.", player::closeDialogue),
                                                    new Option("Yes please!", () -> {
                                                        if (player.getInventory().hasItem(995, 5000000)) {
                                                            player.getInventory().remove(995, 5000000);
                                                            player.edgeHome = false;
                                                            player.dialogue(new NPCDialogue(npc, "Your spawn point has been changed<br>" +
                                                                    "back to home. If you'd like it changed,<br>" +
                                                                    "just speak to me again!"));
                                                        } else {
                                                            player.dialogue(new NPCDialogue(npc,"I'm sorry but it doesn't look like<br>" +
                                                                    "you can afford this.."));
                                                        }
                                                    })
                                            ));
                                }
                            });
                        })));
    }

    @SneakyThrows
    private static void ecoTutorial(Player player) {
        boolean actuallyNew = player.newPlayer;
        player.inTutorial = true;
        player.startEvent(event -> {
            player.lock(LockType.FULL_ALLOW_LOGOUT);

            if (actuallyNew) {
                player.openInterface(InterfaceType.MAIN, Interface.MAKE_OVER_MAGE);
                while (player.isVisibleInterface(Interface.MAKE_OVER_MAGE)) {
                    event.delay(1);
                }
            }
            NPC guide = new NPC(306).spawn(1497,3587,0, Direction.NORTH, 0); // 307 is a copy of 306 without options so it doesnt get in other people's way
            player.logoutListener = new LogoutListener().onLogout(p -> guide.remove());
            player.getPacketSender().sendHintIcon(guide);
            guide.face(player);
            player.face(guide);
            boolean startTutorial = false;
            if (actuallyNew) {
/*                player.dialogue(
                        new NPCDialogue(guide, "Please select an experience mode.<br>" +
                                "You can change this later, but only to an easier<br>" +
                                "experience mode, not harder."));*/

                /*                event.waitForDialogue(player);*/
                String text = "You want to be a part of the economy, then? Great!";
                if (player.getGameMode() == GameMode.IRONMAN) {
                    text = "Iron Man, huh? Self-sufficiency is quite a challenge, good luck!";
                } else if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
                    text = "Hardcore?! You only live once... make it count!";
                } else if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
                    text = "Ultimate Iron Man... Up for quite the challenge, aren't you?";
                } else if (player.getGameMode() == GameMode.GROUP_IRONMAN) {
                    player.checkGIM = true;
                    player.newPlayer = false;
                    player.setAction(1, PlayerAction.GIM_Invite);
                    text = "Group Iron Man... You enjoy the team challenge, Don't you?";
                } else if (player.getGameMode() == GameMode.HARDCORE_GROUP_IRONMAN) {
                    player.checkGIM = true;
                    player.newPlayer = false;
                    player.setAction(1, PlayerAction.GIM_Invite);
                    text = "Hardcore Group Iron Man?! You only live once... make it count! Upon death you will became standard group ironman.";
                }
                if (player.getGameMode().isIronMan()) {
                    player.dialogue(new NPCDialogue(guide, text),
                            new NPCDialogue(guide, "I'll give you a few items to help get you started..."),
                            new NPCDialogue(guide, "There you go, some basic stuff. If you need anything else, remember to check the shops.") {
                                @Override
                                public void open(Player player) {
                                    giveEcoStarter(player);
                                    player.newPlayer = false;
                                    super.open(player);
                                }
                            });
                } else {
                    player.dialogue(new NPCDialogue(guide, "Excellent.. I'll give you a few items to help get you started and fill your bank with various starter items"),
                            new NPCDialogue(guide, "There you go, some basic stuff. If you need anything else, remember to check Sigmund's shop.") {
                                @Override
                                public void open(Player player) {
                                    giveEcoStarter(player);
                                    player.newPlayer = false;
                                    super.open(player);
                                }
                            });
                }
                event.waitForDialogue(player);
                Broadcast.WORLD.sendNews(player.getName() + " has just joined " + World.type.getWorldName() + "!");
                player.dialogue(new NPCDialogue(guide,
                        "Greetings, " + player.getName() + "! Welcome to " + World.type.getWorldName() + ".<br>"));
                player.closeDialogue();
                player.inTutorial = false;
                player.logoutListener = null;
                player.newPlayer = false;
                player.setTutorialStage(0);
                player.unlock();
                guide.addEvent(evt -> {
                    evt.delay(2);
                    World.sendGraphics(86, 50, 0, guide.getPosition());
                    guide.remove();
                });
            }
        });
    }



    private static void giveEcoStarter(Player player) {
        player.getInventory().add(COINS_995, 10000); // gp
        player.getInventory().add(558, 500); // Mind Rune
        player.getInventory().add(556, 1500); // Air Rune
        player.getInventory().add(554, 1000); // Fire Rune
        player.getInventory().add(555, 1000); // Water Rune
        player.getInventory().add(557, 1000); // Earth Rune
        player.getInventory().add(562, 1000); // Chaos Rune
        player.getInventory().add(560, 500); // Death Rune
        player.getInventory().add(1381, 1); // Air Staff
        player.getInventory().add(362, 50); // Tuna
        player.getInventory().add(863, 300); // Iron Knives
        player.getInventory().add(867, 150); // Adamant Knives
        player.getInventory().add(1169, 1); // Coif
        player.getInventory().add(1129, 1); // Leather body
        player.getInventory().add(1095, 1); // Leather Chaps
        player.getInventory().add(13385, 1); // Xeric Hat
        player.getInventory().add(12867, 1); // Blue d hide set
        player.getInventory().add(13024, 1); // Rune set
        player.getInventory().add(11978, 1); // Glory 6
        player.getInventory().add(13387, 1); // Xerican Top
        player.getInventory().add(1323, 1); // Iron scim
        player.getInventory().add(1333, 1); // Rune scim
        player.getInventory().add(4587, 1); // Dragon Scim
        switch (player.getGameMode()) {
            case IRONMAN:
                player.getInventory().add(12810, 1);
                player.getInventory().add(12811, 1);
                player.getInventory().add(12812, 1);
                break;
            case ULTIMATE_IRONMAN:
                player.getInventory().add(12813, 1);
                player.getInventory().add(12814, 1);
                player.getInventory().add(12815, 1);
                break;
            case HARDCORE_IRONMAN:
                player.getInventory().add(20792, 1);
                player.getInventory().add(20794, 1);
                player.getInventory().add(20796, 1);
                break;
            case GROUP_IRONMAN:
                player.getInventory().add(26156, 1);
                player.getInventory().add(26158, 1);
                player.getInventory().add(26166, 1);
                player.getInventory().add(26168, 1);
                player.getMovement().teleport(3105, 3028, 0);
                break;
            case HARDCORE_GROUP_IRONMAN:
                player.getInventory().add(26170, 1);
                player.getInventory().add(26172, 1);
                player.getInventory().add(26180, 1);
                player.getInventory().add(26182, 1);
                player.getMovement().teleport(3105, 3028, 0);
                break;
            case STANDARD:
                player.getInventory().add(COINS_995, 115000);
                break;
        }
    }

    private static NPC find(Player player, int id) {
        for (NPC n : player.localNpcs()) {
            if (n.getId() == id)
                return n;
        }
        throw new IllegalArgumentException();
    }

    private static void setDrag(Player player) {
        player.dialogue(
                new OptionsDialogue("What drag setting would you like to use?",
                        new Option("5 (OSRS) (2007) Drag", () -> setDrag(player, 5)),
                        new Option("10 (Pre-EoC) (2011) Drag", () -> setDrag(player, 10))
                )
        );
    }

    private static void setDrag(Player player, int drag) {
        player.dragSetting = drag;
    }

    private static void tutorial(Player player) {
        ecoTutorial(player);
    }


    private static String getCombatRate(Player player) {
        if (player.xpMode == XpMode.EASY) {
            return "Combat XP Rate: 200x";
        } else if (player.xpMode == XpMode.MEDIUM) {
            return "Combat XP Rate: 100x";
        } else if (player.xpMode == XpMode.HARD) {
            return "Combat XP Rate: 50x";
        } else if (player.xpMode == XpMode.ELITE) {
            return "Combat XP Rate: 25x";
        } else if (player.xpMode == XpMode.ZEZIMA) {
            return "Combat XP Rate: 3x";
        } else {
            return "Combat XP Rate: ";
        }
    }

    private static String getSkillRate(Player player) {
        if (player.xpMode == XpMode.EASY) {
            return "Skill XP Rate: 100x";
        } else if (player.xpMode == XpMode.MEDIUM) {
            return "Skill XP Rate: 50x";
        } else if (player.xpMode == XpMode.HARD) {
            return "Skill XP Rate: 25x";
        } else if (player.xpMode == XpMode.ELITE) {
            return "Skill XP Rate: 12x";
        } else if (player.xpMode == XpMode.ZEZIMA) {
            return "Skill XP Rate: 3x";
        } else {
            return "Skill XP Rate: ";
        }
    }

    private static String getDropRate(Player player) {
        if (player.xpMode == XpMode.MEDIUM) {
            return "Base Drop Rate: 0%";
        } else if (player.xpMode == XpMode.HARD) {
            return "Base Drop Rate: 1%";
        } else if (player.xpMode == XpMode.ELITE) {
            return "Base Drop Rate: 5%";
        } else if (player.xpMode == XpMode.ZEZIMA) {
            return "Base Drop Rate: 25%";
        } else {
            return "Base Drop Rate: 0%";
        }
    }

    private static String getBonusDropRate(Player player) {
        if (player.getGameMode() == GameMode.IRONMAN) {
            return "Bonus Drop Rate: 5%";
        } else if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
            return "Bonus Drop Rate: 10%";
        } else {
            return "Bonus Drop Rate: 0%";
        }
    }
    private static String getRestrictions(Player player) {
        if (player.getGameMode() == GameMode.IRONMAN) {
            return "Ironman Standalone";
        } else if (player.getGameMode() == GameMode.HARDCORE_IRONMAN) {
            return "One life, Standing alone";
        } else if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
            return "No Trading/Banking";
        } else if (player.getGameMode() == GameMode.GROUP_IRONMAN) {
            return "Trading within Group only";
        } else {
            return "No Restrictions.";
        }
    }

    private static void getInfo(Player player) {
        player.getPacketSender().sendString(Interface.STARTER_INTERFACE, 15, getCombatRate(player));
        player.getPacketSender().sendString(Interface.STARTER_INTERFACE, 16, getSkillRate(player));
        player.getPacketSender().sendString(Interface.STARTER_INTERFACE, 17, getDropRate(player));
        player.getPacketSender().sendString(Interface.STARTER_INTERFACE, 18, getBonusDropRate(player));
        player.getPacketSender().sendString(Interface.STARTER_INTERFACE, 19, getRestrictions(player));
    }

    public static void setXpMode(Player player, XpMode xpMode) {
        player.xpMode = xpMode;
    }

}
