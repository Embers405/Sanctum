package io.ruin.model.inter;

import io.ruin.model.achievements.listeners.medium.GuideBook;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.actions.SlotAction;

public class GuideBookInter {

    public static int INTER = 1021;

    static {
        InterfaceHandler.register(INTER, h -> {
            h.actions[294] = (SlotAction) (player, slot)-> displayAboutOspk(player);
            h.actions[295] = (SlotAction) (player, slot)-> displayMoneyMaking(player);
            h.actions[296] = (SlotAction) (player, slot)-> displayConquest(player);
            h.actions[297] = (SlotAction) (player, slot)-> displayArenasBgs(player);
            h.actions[298] = (SlotAction) (player, slot)-> displayWorldEvents(player);
            h.actions[299] = (SlotAction) (player, slot)-> displayEnchanting(player);
            h.actions[300] = (SlotAction) (player, slot)-> displayFreeDonator(player);
            h.actions[301] = (SlotAction) (player, slot)-> displayGreatVault(player);
            h.actions[302] = (SlotAction) (player, slot)-> displayLectern(player);
            h.actions[303] = (SlotAction) (player, slot)-> displayAchievements(player);
            h.actions[304] = (SlotAction) (player, slot)-> displayCombatTasks(player);
            h.actions[305] = (SlotAction) (player, slot)-> displayBootyBay(player);
            h.actions[306] = (SlotAction) (player, slot)-> displayKnightsGuild(player);
            h.actions[307] = (SlotAction) (player, slot)-> displayCox(player);
            h.actions[308] = (SlotAction) (player, slot)-> displayBossing(player);
            h.actions[309] = (SlotAction) (player, slot)-> displaySkilling(player);
            h.actions[310] = (SlotAction) (player, slot)-> displayCommands(player);
        });
    }
    public static void sendTitles(Player p) {
        p.getPacketSender().sendString(INTER, 294, "About OSPK");
        p.getPacketSender().sendString(INTER, 295, "Money Making");
        p.getPacketSender().sendString(INTER, 296, "Conquest");
        p.getPacketSender().sendString(INTER, 297, "Arenas/Battlegrounds");
        p.getPacketSender().sendString(INTER, 298, "World Events");
        p.getPacketSender().sendString(INTER, 299, "Enchanting");
        p.getPacketSender().sendString(INTER, 300, "Free Donator Ranks");
        p.getPacketSender().sendString(INTER, 301, "The Great Vault");
        p.getPacketSender().sendString(INTER, 302, "Lectern of Imbuement");
        p.getPacketSender().sendString(INTER, 303, "Achievements");
        p.getPacketSender().sendString(INTER, 304, "Combat Tasks");
        p.getPacketSender().sendString(INTER, 305, "Booty Bay");
        p.getPacketSender().sendString(INTER, 306, "Knights Guild");
        p.getPacketSender().sendString(INTER, 307, "Chambers of Xeric");
        p.getPacketSender().sendString(INTER, 308, "Bossing");
        p.getPacketSender().sendString(INTER, 309, "Skilling");
        p.getPacketSender().sendString(INTER, 310, "Commands");
    }
    public static void openInter(Player p) {
        p.openInterface(InterfaceType.MAIN, INTER);
        GuideBook.complete(p);
        p.getPacketSender().sendString(INTER, 79, "Select a category to view more info");
        p.getPacketSender().sendString(INTER, 293, "");
        sendTitles(p);
    }
    public static void sendTitle(Player p, String title) {
        p.getPacketSender().sendString(INTER, 294, "<col=ffffff>"+ title);
        p.getPacketSender().sendString(INTER, 79, ""+ title);
    }

    public static void displayAboutOspk(Player p) {
        sendTitles(p);
        sendTitle(p, "About OSPK");
        p.getPacketSender().sendString(INTER, 293, "OSPK is a PvP focused server, made for all player types." +
                " Meaning, while the focus is aimed towards PvP, skillers and PvM'ers alike still play a very important role" +
                " in the success of the economy! PvP'ers will require the best of the best equipment to be on top of their" +
                " game, and the best PvP equipment comes from Enchanting (which you can learn more about by viewing the " +
                "Enchanting guide)." +
                "<br>" +
                "<br>" +
                " Enchanting items requires rare materials, that can be obtained from many various activities in OSPK that" +
                " require zero PvP'ing! This is where the non-pvp'ers shine, by aquiring these enchanting materials and" +
                " selling them to PvP'ers who need them for their BiS items. " +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "Aside from Enchanting, OSPK also offers the most unique, and engaging World Events, such as" +
                " Hold The Fort, Capture The Flag, Pirate Ship, and more! You can learn more about these by viewing the" +
                " World Events guide." +
                "<br>" +
                "<br>" +
                "These World Events offer some of the best rewards in the game, including top PvP/PvM gear, the rarest" +
                " Enchanting materials, and much more." +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To view more about what OSPK has to offer, view the rest of the guides!");
    }
    public static void displayMoneyMaking(Player p) {
        sendTitles(p);
        sendTitle(p, "Money Making");
        p.getPacketSender().sendString(INTER, 293, "There are endless methods of earning wealth in OSPK! From" +
                " PvP, to bossing and skilling, and participating in world events, you create your own journey of earning!" +
                "<br>" +
                "<br>" +
                "For beginners, starting at the thieving stalls in Edgeville will get your bank roll going. Not only will" +
                " you earn consistent gold, but you also have the chance at receiving a Blood Diamond, one of the rarest" +
                " and most expensive Enchanting materials in OSPK! Additionally, it is recommended to start completing " +
                "the beginner and medium achievements, as these can also help start building your bank up!" +
                "<br>" +
                "<br>" +
                "Another great method for earning as a beginner is to participate in the Pirate Ship world event. There are" +
                " no combat requirements for this event, as it only requires you to supply the pirates with items you can" +
                " get from around the game and from skilling." +
                "<br>" +
                "<br>" +
                "Once you make it into mid-game gear, you will be able to start completing other world events that also" +
                " offer some of the best rewards! Additionally, completing hard, elite, and master achievements will" +
                " greatly reward you. At this point, you will also be able to start completing Combat Tasks that also" +
                " prove fruitful.");
    }
    public static void displayConquest(Player p) {
        sendTitles(p);
        sendTitle(p, "Conquest");
        p.getPacketSender().sendString(INTER, 293, "Conquest Points are the main source of currency earned from" +
                " competing in Arenas/Battlegrounds, Tournaments, Pk'ing and most other PvP activities. The points you earn " +
                "can be spent in the Conquest Shop (by trading the Arena Master) and can be used to purchase PvP gear and " +
                "other top-end items!" +
                "<br>" +
                "<br>" +
                "The best way to start building up your Conquest Points is to try and make a habit of competing in " +
                "Tournaments and Arenas/Battlegrounds daily. Although winning in these events will give a higher amount" +
                " of Conquest, you can still earn the points simply by competing in these events! Meaning, you don't have" +
                " to be a top-notch Pker to get the Conquest Points needed to purchase your item upgrades." +
                "<br>" +
                "<br>" +
                "<br>" +
                "To spend your points, travel to the PvP Hall located in Edgeville, the building just south of the Trading Post." +
                "Speak to the Arena Master inside the Hall to view the Conquest shop and spend away!");
    }
    public static void displayArenasBgs(Player p) {
        sendTitles(p);
        sendTitle(p, "Arenas/Battlegrounds");
        p.getPacketSender().sendString(INTER, 293, "Arenas & Battlegrounds are a unique, safe PvP activity that" +
                " all players can participate in! Players have the option between participating in either Unrated, or Rated" +
                " Arenas/Battlegrounds. Competing in Rated will grow your Seasonal PvP Rating with every win you achieve." +
                " Earning a Seasonal PvP Rating of 5,500 Conquest will reward you with a Seasonal player title, as well as" +
                " a unique pet and very fruitful rewards! Each Season will last 3 months, with the rewards changing with" +
                " each new Season." +
                "<br>" +
                "<br>" +
                "For Arenas, players have the option of competing in either 2v2, or 3v3 matches. And for Battlegrounds, " +
                "currently there is only the option of 10v10 matches. Players are required to bring their own items, however, " +
                "these activities are safe, and players will not lose any items upon death inside of the Arena/Battleground." +
                " To enter one of these matches, players must be atleast a minimum combat level of 115. " +
                "<br>" +
                "<br>" +
                "The strategy for Arenas is simple; kill all of the players of the opposing team to win. Battlegrounds, however, " +
                "will have different objectives. Currently, players have the chance at either a Capture The Flag Battleground, " +
                "or a Team Deathmatch Battleground. " +
                "<br>" +
                "<br>" +
                "<br>" +
                "To enter an Arena or Battleground, speak to the Arena Master, located in the PvP Hall in Edgeville.");
    }
    public static void displayWorldEvents(Player p) {
        sendTitles(p);
        sendTitle(p, "World Events");
        p.getPacketSender().sendString(INTER, 293, "OSPK is home to many unique, rewarding and engaging world events." +
                " There are currently 6 world events, 4 of which are unsafe wilderness events. " +
                "The world events can be found by going to your quest tab and selecting the orange Server Events icon." +
                " Here, you will find all 6 events listed and displaying whether they are currently active or not. " +
                "By clicking one of these events, you can view more information about them, and also view the possible rare" +
                " rewards for each one." +
                "<br>" +
                "<br>" +
                "The unsafe wilderness events include Capture The Flag, Hold The Fort, Deadmans Chest, and Glod. The two" +
                " safe events include Pirates Ship and the Fragment of Seren boss. The world events are all on different timers," +
                " however, there will always be one available to participate in each hour of the day!");
    }
    public static void displayEnchanting(Player p) {
        sendTitles(p);
        sendTitle(p, "Enchanting");
        p.getPacketSender().sendString(INTER, 293, "Enchanting is one of the most important aspects of OSPK." +
                " For PvP'ers, the only way to obtain all of your BiS items is having them Enchanted. Players are not " +
                "forced to enchant their own items if they do not wish to; all Enchanted items are tradeable." +
                "<br>" +
                "<br>" +
                "To enchant an item, players will need the base item, and 3 secondary materials. To view which items" +
                " can be enchanted, which materials are needed, and the effects of the Enchantment, head over to the " +
                "PvP Hall located in Edgeville and use the Altar of Enchantment, then select your desired item." +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "Most item Enchantments are very unique, and more than just a stat or % increase. The majority of them offer" +
                " unique abilities such as a heal over time effect, spawning an npc to fight alongside you for a period of" +
                " time, special ability procs, and more! ");
    }
    public static void displayFreeDonator(Player p) {
        sendTitles(p);
        sendTitle(p, "Free Donator Ranks");
        p.getPacketSender().sendString(INTER, 293, "Free donator ranks?! What do you mean?? That's right. In OSPK, " +
                "we believe that players should not be forced to spend real money in order to feel like they are keeping up" +
                " with other players, or make them feel like they are missing out simply because they didn't spend real money. " +
                " That is why we are making it possible to level the playing field, at a cost to your in-game bank, " +
                "rather than your real-life bank." +
                "<br>" +
                "<br>" +
                "For certain amounts of GP, players are able to purchase every donator rank available. Players will need to" +
                " purchase each rank in order, you cannot simply buy the highest rank before buying the previous ranks. " +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To view the list of donator ranks, their costs, and their benefits, speak to the OSPK Guide, located inside of the" +
                " bank in Edgeville!");
    }
    public static void displayGreatVault(Player p) {
        sendTitles(p);
        sendTitle(p, "The Great Vault");
        p.getPacketSender().sendString(INTER, 293, "The Great Vault, located in the PvP Hall in Edgeville, is" +
                " home to many, many great rewards. By completing world events, as well as completing a certain number of" +
                " Arenas/Battlegrounds per day, players will receive a Great Vault key and be able to claim their prize." +
                "<br>" +
                "<br>" +
                "Every world event in OSPK will drop a Great Vault key to those who participated in the event. Additionaly," +
                " completing a minimum of 10 either Arenas or Battlegrounds per day will allow you to open the Great Vault" +
                " and claim the Daily Arenas/Battlrgrounds reward, once a day. To view the possible rewards you can receive," +
                " simply go to your quest tab, click the orange Server Events button, choose the world event and then view" +
                " the rewards for that event." +
                "<br>" +
                "<br>" +
                "To view The Great Vault, travel to the PvP Hall in Edgeville, or travel to Booty Bay and enter the western" +
                " building.");
    }
    public static void displayLectern(Player p) {
        sendTitles(p);
        sendTitle(p, "Lectern of Imbuement");
        p.getPacketSender().sendString(INTER, 293, "The Lectern of Imbuement is currently the only available method" +
                " for imbueing items in OSPK. The lectern can be found in the building just east of the Trading Post in Edgeville." +
                " Imbueing an item only requires the item and a certain amount of GP. Very straight forward.");
    }
    public static void displayAchievements(Player p) {
        sendTitles(p);
        sendTitle(p, "Achievements");
        p.getPacketSender().sendString(INTER, 293, "Achievements can be a great money making method to begin your" +
                " journey in OSPK, and can also provide useful items to you. The achievements have all been tailored to fit" +
                " your experience on the server, offering a variety of challenges from PvP to PvM to skilling." +
                "<br>" +
                "<br>" +
                "Most importantly, the beginner achievements are aimed to assist the player in showing them their way around" +
                " the custom home area, and all of its unique features. Rather than sit through a boring tutorial that nobody" +
                " pays attention to and just skips through, you will instead be able to find your way around by completing" +
                " the beginner achievements, and be rewarded for it!" +
                "<br>" +
                "<br>" +
                "Completing the master achievements will not be easy, however it will be the ultimate flex to friends and" +
                " foes alike!" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "There are two ways to view achievements. The first, by opening your quest tab and clicking on the black square" +
                " labled Achievements. Secondly, you can go to your quest tab and click the green Achievements icon to view" +
                " your progress, and then click the category you wish to view to see all of the achievements, and their rewards.");
    }
    public static void displayCombatTasks(Player p) {
        sendTitles(p);
        sendTitle(p, "Combat Tasks");
        p.getPacketSender().sendString(INTER, 293, "Combat Tasks in OSPK work very similar to how they work in" +
                " OSRS. The tasks themselves are all the same, with the same categories, however, the rewards are tailored to" +
                " fit in OSPK. Completing these tasks is a great way to begin building your wealth, and to also start" +
                " earning Enchanting materials and other very useful items!" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "For those unfamiliar with what Combat Tasks are, let me explain; They are like achievements on steroids." +
                " Combat tasks are much more specific in their requirements. For example: Complete a solo Chambers of Xeric " +
                "in less than 17 minutes. Most easy, medium, and hard tasks are simple enough to complete. Finishing elite, " +
                "master, and grandmaster tasks are what separates the boys from the men." +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To view the Combat Tasks, open the quest tab and click the black square labeled Combat tasks." +
                " When you first open the Combat Tasks interface, you will be shown the tasks overview of your account." +
                " Here, you will see how many total tasks you've completed, the boss you kill the most, and more." +
                " From here, you can select a category on the left to view all of the available tasks for that category.");
    }
    public static void displayBootyBay(Player p) {
        sendTitles(p);
        sendTitle(p, "Booty Bay");
        p.getPacketSender().sendString(INTER, 293, "Booty Bay is a small version of a larger Wilderness Expansion coming" +
                " to OSPK. Located in level 37 eastern wilderness, this area servers a similar purpose as Ferox Enclave." +
                " However, Booty Bay is much more unique. It offers the Pirate Ship world event, an additional entrance to the" +
                " wilderness slayer dungeon, and an Enchanting Materials shop. Additionally, the ship itself servers as a method" +
                " of transportatin to the Knights Guild, the Galvek boss, and eventually to the larger wilderness expansion." +
                "<br>" +
                "<br>" +
                "Similar to Ferox, Booty Bay also contains a bank and a pool of rejuvenation, as well as The Great Vault and" +
                " a general store. As for the lore behind the name Booty Bay, this area was once a booming Pirate outpost." +
                " The survivors need the help of the players to rebuild their ship to continue sailing! Players will be able to" +
                " assist them during the Pirate Ship world event." +
                "<br>" +
                "<br>" +
                "Completing the Pirates Ship event will rewardthe players with a new currency to spend in the Booty Bay enchanting" +
                " materials shop, as well as a Great Vault key. Players can travel to Booty Bay quickly by opening the Teleport" +
                " interface, selecting Wildy, and clicking Booty Bay.");
    }
    public static void displayKnightsGuild(Player p) {
        sendTitles(p);
        sendTitle(p, "Knights Guild");
        p.getPacketSender().sendString(INTER, 293, "The Knights Guild is a unique area for the best PvP'ers" +
                " and PvM'ers. This area offers many convenient features that can only be found at the guild, such as" +
                " a brand new slayer dungeon, PvP portals and amenities, and much more. Around the time that Season 2" +
                " begins, the Knights Guild will be receiving an expansion, as well as it's own world event!" +
                "<br>" +
                "<br>" +
                "The only method of transportation to the Knights Guild is by using the ship in Booty Bay. First, you" +
                " must complete the Pirates Ship event in Booty Bay one time. Second, you must either have a PvP Rating " +
                "of 2,500+, or you must have completed all of the Easy & Medium Combat Tasks available." +
                "<br>" +
                "");
    }
    public static void displayCox(Player p) {
        sendTitles(p);
        sendTitle(p, "Chambers of Xeric");
        p.getPacketSender().sendString(INTER, 293, "Aside from not currently having a challenge mode, the Chambers" +
                " of Xeric works the same as it does in OSRS. It is a full raid, with all of the same boss mechanics which will" +
                " certainly make it easier for players who have not raided before, to learn the fights." +
                "<br>" +
                "<br>" +
                "There are many Combat Tasks that need to be completed in CoX, so you may be spending quite a lot of time here" +
                " if you are attempting to complete your Combat Task log! All of the tasks associated with CoX are also the same" +
                " as they are in OSRS, as well as allof the rewards." +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To get to the Chambers of Xeric, use the Teleporter, select minigames, then select Chambers of Xeric!");
    }
    public static void displayBossing(Player p) {
        sendTitles(p);
        sendTitle(p, "Bossing");
        p.getPacketSender().sendString(INTER, 293, "Currently, OSPK has nearly all of the bosses you would" +
                " find in OSRS, with the exception of Nex. Additionally, all of the bosses you will find here have" +
                " the same mechanics as they doin OSRS as well. For players new to bossing, this certainly makes learning" +
                " the fights easier as they are consistent with the real game!" +
                "<br>" +
                "<br>" +
                "All bosses in OSPK have the chance at dropping the rarest Enchanting materials, which make bossing" +
                " even more viable than it already was!" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To travel to any of the bosses, simply use the Teleporter, select Bosses, and choose which one you" +
                " want to go and fight!");
    }
    public static void displaySkilling(Player p) {
        sendTitles(p);
        sendTitle(p, "Skilling");
        p.getPacketSender().sendString(INTER, 293, "Similar to bossing, skilling mechanics in OSPK work the" +
                " same as in OSRS! Players will be able to use all of the same skilling areas that they use in the real" +
                " game. Additionally, all of the skilling teleports, such as the skilling necklace, fairy ring, and mothers" +
                " all work properly and will take you to any location you require for skilling!" +
                "<br>" +
                "<br>" +
                "While doing any skilling activity, players have a chance at receiving rare enchanting materials at random," +
                " no matter the skilling activity they are doing! Meaning, the more you raise your total level, the higher" +
                " chance you have at hitting the jackpot and receiving a rare enchanting material!" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To use the general skilling teleports, use the Teleporter, select Skilling, and choose a destination.");
    }
    public static void displayCommands(Player p) {
        sendTitles(p);
        sendTitle(p, "Commands");
        p.getPacketSender().sendString(INTER, 293, "Players have a wide range of useful commands available" +
                " to them! From many different teleport options, to world event information, to getting a skull." +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "To view a complete list of commands available to you, simply type ::commands");
    }


}