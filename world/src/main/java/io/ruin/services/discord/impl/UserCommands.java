package io.ruin.services.discord.impl;
/*
 * @project Kronos
 * @author Patrity - https://github.com/Patrity
 * Created on - 1/14/2020
 */

import io.ruin.Server;
import io.ruin.api.utils.TimeUtils;
import io.ruin.model.World;
import io.ruin.model.activities.wilderness.Hotspot;
import io.ruin.model.activities.wilderness.Wilderness;
import io.ruin.model.entity.player.Player;
import io.ruin.services.discord.DiscordConnection;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserCommands extends ListenerAdapter {

    private String[] onlineAliases = {"::online", "::players", "::info", "::server", "::hs", "::stats", "::player", "::hiscores"};


    public static Player player;
    @SneakyThrows
    public void onGuildMessageReceived(MessageReceivedEvent e) {

        if (e.getAuthor().isBot())
            return;
        if(World.isEco())
            return;


        String message = e.getMessage().getContentRaw().toLowerCase();

        for (int x = 0; x < onlineAliases.length; x++) {
            if (e.getMessage().getContentRaw().equalsIgnoreCase(onlineAliases[x])) {
                if (!e.getChannel().getId().equalsIgnoreCase("984912596031377439")) {
                    e.getChannel().sendMessage("Please only use this command in #bot-controls!").queue();
                    return;
                }


                EmbedBuilder embed = new EmbedBuilder()
                        .setTitle("Nightmare Server Status", "https://nightmareps.uk")
                        .setColor(new Color(0xB00D03))
                        .addField("Players Online:", String.valueOf(World.players.count()), true)
                        .addField("Uptime:", TimeUtils.fromMs(Server.currentTick() * Server.tickMs(), false), true)
                        .addField("Players in Wild:", String.valueOf(Wilderness.players.size()), true)
                        .addField("**Active Bonuses:**", "", false)
                        .addField("XP Boost:", World.xpMultiplier+"x", true)
                        .addField("Weekend XP:", World.weekendExpBoost ? "Enabled" : "Disabled", true)
                        .addField("Double Wintertodt:", World.doubleWintertodt ? "Enabled" : "Disabled", true)
                        .addField("Double Slayer:", World.doubleSlayer ? "Enabled" : "Disabled", true)
                        .addField("Double PestControl:", World.doublePest ? "Enabled" : "Disabled", true)
                        .addField("Double BloodMoney:", World.doublePkp ? "Enabled" : "Disabled", true)
                        .addField("Hotspot:", Hotspot.ACTIVE.name, true);
                e.getChannel().sendMessageEmbeds(embed.build()).queue();
            }
        }
        if(e.getChannel().getId().equalsIgnoreCase("984912596031377439")){
            if (e.getMessage().getContentRaw().contains("::doublewinter")) {
                World.toggleDoubleWintertodt();
            }
            if (e.getMessage().getContentRaw().contains("::doubleslayer")) {
                World.toggleDoubleSlayer();
            }
            if (e.getMessage().getContentRaw().contains("::doublexp")) {
                World.toggleWeekendExpBoost();
            }
            if (e.getMessage().getContentRaw().contains("::doublepc")) {
                World.toggleDoublePest();
            }
            if (e.getMessage().getContentRaw().contains("::doublepk")) {
                World.toggleDoublePkp();
            }
        }

    }
    private ArrayList<String> split(String subjectString) {
        ArrayList<String> matchList = new ArrayList<>();
        Pattern regex = Pattern.compile("\"([^\"\\\\]*(?:\\\\.[^\"\\\\]*)*)\"|'([^'\\\\]*(?:\\\\.[^'\\\\]*)*)'|[^\\s]+");
        Matcher regexMatcher = regex.matcher(subjectString);
        while (regexMatcher.find()) {
            if (regexMatcher.group(1) != null) {
                dbconnect(regexMatcher.group(1));
                matchList.add(regexMatcher.group(1));
            }
        }
        return matchList;
    }


    public static void dbconnect(String user) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection dbConnection = DriverManager.getConnection("jdbc:mysql://185.2.168.125:3306/obsidia1_hiscore",
                    "obsidia1_juice","kresls112!");

            String query = "SELECT * FROM `hs_users` WHERE `username`="+ "'" + user + "'";

            Statement st = dbConnection.createStatement();

            ResultSet rs = st.executeQuery(query);
            System.err.println(query);

            while (rs.next())
            {
                TotalLevel = rs.getInt("total_level");
                Attack = rs.getInt("attack");
                Defence = rs.getInt("defence");
                Strength = rs.getInt("strength");
                Constitution = rs.getInt("hitpoints");
                Range = rs.getInt("ranged");
                Prayer = rs.getInt("prayer");
                Magic = rs.getInt("magic");
                Cooking = rs.getInt("cooking");
                Woodcutting = rs.getInt("woodcutting");
                Fletching = rs.getInt("fletching");
                Fishing = rs.getInt("fishing");
                Firemaking = rs.getInt("firemaking");
                Crating = rs.getInt("crafting");
                Smithing = rs.getInt("smithing");
                Mining = rs.getInt("mining");
                Herblore = rs.getInt("herblore");
                Agility = rs.getInt("agility");
                Thieving = rs.getInt("thieving");
                Slayer = rs.getInt("slayer");
                Farming = rs.getInt("farming");
                Runecrafting = rs.getInt("runecrafting");
                Hunter = rs.getInt("hunter");
                Construction = rs.getInt("construction");
            }

            EmbedBuilder eb = new EmbedBuilder();
            eb.setTitle("Showing recent hiscores information for user: " + user);
            eb.addField("TotalLevel: ", String.valueOf(TotalLevel),true);
            eb.addField("Attack: ", String.valueOf(Attack),true);
            eb.addField("Defence: ", String.valueOf(Defence),true);
            eb.addField("Strength: ", String.valueOf(Strength),true);
            eb.addField("Constitution: ", String.valueOf(Constitution),true);
            eb.addField("Range: ", String.valueOf(Range),true);
            eb.addField("Prayer: ", String.valueOf(Prayer),true);
            eb.addField("Magic: ", String.valueOf(Magic),true);
            eb.addField("Cooking: ", String.valueOf(Cooking),true);
            eb.addField("Woodcutting: ", String.valueOf(Woodcutting),true);
            eb.addField("Fletching: ", String.valueOf(Fletching),true);
            eb.addField("Fishing: ", String.valueOf(Fishing),true);
            eb.addField("Firemaking: ", String.valueOf(Firemaking),true);
            eb.addField("Crating: ", String.valueOf(Crating),true);
            eb.addField("Smithing: ", String.valueOf(Smithing),true);
            eb.addField("Mining: ", String.valueOf(Mining),true);
            eb.addField("Herblore: ", String.valueOf(Herblore),true);
            eb.addField("Agility: ", String.valueOf(Agility),true);
            eb.addField("Thieving: ", String.valueOf(Thieving),true);
            eb.addField("Slayer: ", String.valueOf(Slayer),true);
            eb.addField("Farming: ", String.valueOf(Farming),true);
            eb.addField("Runecrafting: ", String.valueOf(Runecrafting),true);
            eb.addField("Hunter: ", String.valueOf(Hunter),true);
            eb.addField("Construction: ", String.valueOf(Construction),true);
            eb.setColor(new java.awt.Color(0xB00D03));
            DiscordConnection.jda.getTextChannelById("984905901611827260").sendMessageEmbeds(eb.build()).queue();

            st.close();
        }
        catch (Exception e)
        {
            nullUser(user);
            //return user doesn't exist in our database
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }



    public static void nullUser(String user) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("That user: "+user+", Does not exist!");
        eb.setColor(new java.awt.Color(0xB00D03));
        DiscordConnection.jda.getTextChannelById("984905901611827260").sendMessageEmbeds(eb.build()).queue();
    }

    public static int TotalLevel;
    public static int Attack;
    public static int Defence;
    public static int Strength;
    public static int Constitution;
    public static int Range;
    public static int Prayer;
    public static int Magic;
    public static int Cooking;
    public static int Woodcutting;
    public static int Fletching;
    public static int Fishing;
    public static int Firemaking;
    public static int Crating;
    public static int Smithing;
    public static int Mining;
    public static int Herblore;
    public static int Agility;
    public static int Thieving;
    public static int Slayer;
    public static int Farming;
    public static int Runecrafting;
    public static int Hunter;
    public static int Construction;




}
