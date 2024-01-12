package io.ruin.model.skills.slayer;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.dialogue.OptionsDialogue;
import io.ruin.model.inter.utils.Option;

public class TaskSelector {
    // normal 50 - 150
    // boss 10 - 35
    public static void TaskSelect(Player player){
        //Easy:
        //  - Spiders
        //  - Skeletons
        //  - Hill Giants
        //  - Ghosts
        //  - Bats
        player.dialogue(new OptionsDialogue("Select your TASK Difficulty!",
                new Option("Easy Slayer Task!", () -> {
                    player.dialogue(new OptionsDialogue("Select your TASK!",
                            new Option("Spiders!", () -> {
                                player.slayerTaskName = "Spiders";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.TURAEL;
                                player.slayerTaskType = SlayerTask.Type.EASY;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Skeletons!", () -> {
                                player.slayerTaskName = "Skeletons";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.TURAEL;
                                player.slayerTaskType = SlayerTask.Type.EASY;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Hill Giants!", () -> {
                                player.slayerTaskName = "Hill Giants";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.TURAEL;
                                player.slayerTaskType = SlayerTask.Type.EASY;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Ghosts!", () -> {
                                player.slayerTaskName = "Ghosts";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.TURAEL;
                                player.slayerTaskType = SlayerTask.Type.EASY;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Bats!", () -> {
                                player.slayerTaskName = "Bats";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.TURAEL;
                                player.slayerTaskType = SlayerTask.Type.EASY;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            })
                    ));
                }),
                //
                //Medium:
                //  - Fire Giants
                //  - Bloodvelds
                //  - Dust Devils
                //  - Cave Horrors
                //  - Blue Dragons
                //VANNAKA
                new Option("Medium Slayer Task!", () -> {
                    player.dialogue(new OptionsDialogue("Select your TASK!",
                            new Option("Fire Giants!", () -> {
                                player.slayerTaskName = "Fire Giants";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.VANNAKA;
                                player.slayerTaskType = SlayerTask.Type.MEDIUM;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Bloodveld!", () -> {
                                player.slayerTaskName = "Bloodveld";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.VANNAKA;
                                player.slayerTaskType = SlayerTask.Type.MEDIUM;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Dust Devils!", () -> {
                                player.slayerTaskName = "Dust Devils";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.VANNAKA;
                                player.slayerTaskType = SlayerTask.Type.MEDIUM;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Cave Horrors!", () -> {
                                player.slayerTaskName = "Cave Horrors";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.VANNAKA;
                                player.slayerTaskType = SlayerTask.Type.MEDIUM;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Blue Dragons!", () -> {
                                player.slayerTaskName = "Blue Dragons";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.VANNAKA;
                                player.slayerTaskType = SlayerTask.Type.MEDIUM;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            })
                    ));
                }),
                //Hard:
                //  - Black Demons
                //  - Hellhounds
                //  - Cave Krakens
                //  - Abyssal Demons
                //  - Smoke Devils
                //NIEVE
                new Option("Hard Slayer Task!", () -> {
                    player.dialogue(new OptionsDialogue("Select your TASK!",
                            new Option("Black Demons!", () -> {
                                player.slayerTaskName = "Black Demons";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.NIEVE;
                                player.slayerTaskType = SlayerTask.Type.HARD;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Hellhounds!", () -> {
                                player.slayerTaskName = "Hellhounds";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.NIEVE;
                                player.slayerTaskType = SlayerTask.Type.HARD;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Cave Kraken!", () -> {
                                player.slayerTaskName = "Cave Kraken";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.NIEVE;
                                player.slayerTaskType = SlayerTask.Type.HARD;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Abyssal Demons!", () -> {
                                player.slayerTaskName = "Abyssal Demons";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.NIEVE;
                                player.slayerTaskType = SlayerTask.Type.HARD;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Smoke Devils!", () -> {
                                player.slayerTaskName = "Smoke Devils";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.NIEVE;
                                player.slayerTaskType = SlayerTask.Type.HARD;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            })
                    ));
                }),

                //CRYSTALL:
                //  - Black Demons
                //  - Hellhounds
                //  - Cave Krakens
                //  - Abyssal Demons
                //  - Smoke Devils
                //NIEVE
                new Option("crystal Slayer Task!", () -> {
                    player.dialogue(new OptionsDialogue("Select your TASK!",
                            new Option("rystalline Bear!", () -> {
                                player.slayerTaskName = "Crystalline Bear";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.CRYSTALLINE;
                                player.slayerTaskType = SlayerTask.Type.CRYSTAL;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Crystalline Rat!", () -> {
                                player.slayerTaskName = "Crystalline Rat";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.CRYSTALLINE;
                                player.slayerTaskType = SlayerTask.Type.CRYSTAL;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("crystalline Spider", () -> {
                                player.slayerTaskName = "crystalline Spider";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.CRYSTALLINE;
                                player.slayerTaskType = SlayerTask.Type.CRYSTAL;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Crystalline Unicorn", () -> {
                                player.slayerTaskName = "Crystalline Unicorn";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.CRYSTALLINE;
                                player.slayerTaskType = SlayerTask.Type.CRYSTAL;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Crystalline Dark Beast", () -> {
                                player.slayerTaskName = "Crystalline Dark Beast";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.CRYSTALLINE;
                                player.slayerTaskType = SlayerTask.Type.CRYSTAL;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Crystalline Dragon", () -> {
                                player.slayerTaskName = "Crystalline Dragon";
                                player.slayerTaskRemaining = Random.get(50,150);
                                player.currentSlayerMaster = SlayerMaster.CRYSTALLINE;
                                player.slayerTaskType = SlayerTask.Type.CRYSTAL;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            })
                    ));
                }),
                //Boss:
                //  - Zulrah
                //  - Dagg Kings
                //  - Shamans
                //  - Aby sire
                //  - GWD   - Kree'arra
                //          - General Graardor
                //          - Kril Tsustaroth
                //          - Commander Zilyanna
                //DURADEL
                new Option("Boss Slayer Task!", () -> {
                    player.dialogue(new OptionsDialogue("Select your TASK!",
                            new Option("Zulrah!", () -> {
                                player.slayerTaskName = "Zulrah";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.DURADEL;
                                player.slayerTaskType = SlayerTask.Type.BOSS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Dagannoth Kings!", () -> {
                                player.slayerTaskName = "Dagannoth Kings";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.DURADEL;
                                player.slayerTaskType = SlayerTask.Type.BOSS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("The Abyssal Sire!", () -> {
                                player.slayerTaskName = "The Abyssal Sire";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.DURADEL;
                                player.slayerTaskType = SlayerTask.Type.BOSS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("The Alchemical Hydra!", () -> {
                                player.slayerTaskName = "The Alchemical Hydra";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.DURADEL;
                                player.slayerTaskType = SlayerTask.Type.BOSS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("GWD Bosses!", () -> {
                                player.dialogue(new OptionsDialogue("Select your TASK!",
                                        new Option("Kree'arra!", () -> {
                                            player.slayerTaskName = "Kree'arra";
                                            player.slayerTaskRemaining = Random.get(10,35);
                                            player.currentSlayerMaster = SlayerMaster.DURADEL;
                                            player.slayerTaskType = SlayerTask.Type.BOSS;
                                            player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                            if (player.Dslayertask == 2){
                                                player.slayertasktimer = System.currentTimeMillis();
                                                player.Dslayertask = 3;
                                            }
                                            player.Dslayertask += 1;
                                        }),
                                        new Option("Commander Zilyana!", () -> {
                                            player.slayerTaskName = "Commander Zilyana";
                                            player.slayerTaskRemaining = Random.get(10,35);
                                            player.currentSlayerMaster = SlayerMaster.DURADEL;
                                            player.slayerTaskType = SlayerTask.Type.BOSS;
                                            player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                            if (player.Dslayertask == 2){
                                                player.slayertasktimer = System.currentTimeMillis();
                                                player.Dslayertask = 3;
                                            }
                                            player.Dslayertask += 1;
                                        }),
                                        new Option("General Graardor!", () -> {
                                            player.slayerTaskName = "General Graardor";
                                            player.slayerTaskRemaining = Random.get(10,35);
                                            player.currentSlayerMaster = SlayerMaster.DURADEL;
                                            player.slayerTaskType = SlayerTask.Type.BOSS;
                                            player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                            if (player.Dslayertask == 2){
                                                player.slayertasktimer = System.currentTimeMillis();
                                                player.Dslayertask = 3;
                                            }
                                            player.Dslayertask += 1;
                                        }),
                                        new Option("K'ril Tsutsaroth!", () -> {
                                            player.slayerTaskName = "K'ril Tsutsaroth";
                                            player.slayerTaskRemaining = Random.get(10,35);
                                            player.currentSlayerMaster = SlayerMaster.DURADEL;
                                            player.slayerTaskType = SlayerTask.Type.BOSS;
                                            player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                            if (player.Dslayertask == 2){
                                                player.slayertasktimer = System.currentTimeMillis();
                                                player.Dslayertask = 3;
                                            }
                                            player.Dslayertask += 1;
                                        })
                                ));
                            })
                    ));
                }),
                //Wilderness:
                //  - Spiders
                //  - Bears
                //  - Revenants
                //  - Green Dragons
                //  - Scorpions
                //KRYSTILIA
                new Option("Wilderness Slayer Task!", () -> {
                    player.dialogue(new OptionsDialogue("Select your TASK!",
                            new Option("Bears!", () -> {
                                player.slayerTaskName = "Bears";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.KRYSTILIA;
                                player.slayerTaskType = SlayerTask.Type.WILDERNESS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Revenants!", () -> {
                                player.slayerTaskName = "Revenants";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.KRYSTILIA;
                                player.slayerTaskType = SlayerTask.Type.WILDERNESS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Green Dragons!", () -> {
                                player.slayerTaskName = "Green Dragons";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.KRYSTILIA;
                                player.slayerTaskType = SlayerTask.Type.WILDERNESS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Scorpions!", () -> {
                                player.slayerTaskName = "Scorpions!";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.KRYSTILIA;
                                player.slayerTaskType = SlayerTask.Type.WILDERNESS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            }),
                            new Option("Venenatis!", () -> {
                                player.slayerTaskName = "Venenatis";
                                player.slayerTaskRemaining = Random.get(10,35);
                                player.currentSlayerMaster = SlayerMaster.KRYSTILIA;
                                player.slayerTaskType = SlayerTask.Type.WILDERNESS;
                                player.sendMessage("Your new task is to kill " + (Color.RED.wrap(String.valueOf(player.slayerTaskRemaining)) + " X " + (Color.RED.wrap(String.valueOf(player.slayerTaskName)) + ".<br>Good luck!")));
                                if (player.Dslayertask == 2){
                                    player.slayertasktimer = System.currentTimeMillis();
                                    player.Dslayertask = 3;
                                }
                                player.Dslayertask += 1;
                            })
                    ));
                })
        ));
    }
}
