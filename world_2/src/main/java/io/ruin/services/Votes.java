package io.ruin.services;

import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.cache.Icon;
import io.ruin.cache.NpcID;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.map.Direction;
import io.ruin.services.discord.DiscordConnection;
import io.ruin.utility.Broadcast;
import net.dv8tion.jda.api.EmbedBuilder;

import java.sql.*;

public class Votes implements Runnable{

    public static final String HOST = "185.2.168.125";
    public static final String USER = "obsidia1_juice";
    public static final String PASS = "kresls112!";
    public static final String DATABASE = "obsidia1_vote";

    private Player player;
    private Connection conn;
    private Statement stmt;

    public Votes(Player player) {
        this.player = player;
    }

    static {
        World.startEvent(e -> {
            while(true) {
                e.delay(1500); //5 minutes
                Broadcast.GLOBAL.sendNews(Icon.ANNOUNCEMENT, "Announcements", "Currently there are " + Votes.amount + " votes left until the vote boss spawns!");
            }
        });
    }
    @Override
    public void run() {
        try {
            if (!connect(HOST, DATABASE, USER, PASS)) {
                return;
            }

            String name = player.getName();
            ResultSet rs = executeQuery("SELECT * FROM votes WHERE username='"+name+"' AND claimed=0 AND voted_on != -1");

            while (rs.next()) {
                String ipAddress = rs.getString("ip_address");
                int siteId = rs.getInt("site_id");

                // Vote boss -1 each vote, when 0 Spawn
                amount--;
                player.getInventory().addOrDrop(30256, 1);
                player.getInventory().addOrDrop(995, 125_000);
                if (Random.rollDie(10,1)) {
                    player.getInventory().addOrDrop(6829,1);
                    player.sendMessage("Congratulations upon voting, the vote master awarded you with a vote box!");
                    Broadcast.GLOBAL.sendNews(player.getName() + " Just voted for Obsidian!, & won a vote box!");
                }

//                Broadcast.WORLD.sendNews(player.getName() + " Just voted for Drako!, " + amount + " votes left until Vote Boss Spawns!");

                System.out.println("[Vote] Vote claimed by "+name+". (sid: "+siteId+", ip: "+ipAddress+")");
                if (amount <= 0) {
                    new NPC(NpcID.AVATAR_OF_CREATION_10531).spawn(1777, 3572, 0, Direction.WEST, 1).getCombat().setAllowRespawn(false);
                    Broadcast.GLOBAL.sendNews("<shad=000000>"+ Color.BABY_BLUE.wrap("[VOTE BOSS] ")+"Avatar of creation Has just spawned! use ::voteboss to get there!</shad>");
                    amount = 50;
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("An Vote Boss Has Spawned!");
                    eb.setImage("https://drakops.com/Drako/image/vote.png");
                    eb.addField("Notification!", "<@&984907826679939153>", true);
                    eb.addField("Notification!", "<@&984907826679939153>", true);
                    eb.setColor(new java.awt.Color(0xB00D03));
                    DiscordConnection.jda.getTextChannelById("984906544929984512").sendMessageEmbeds(eb.build()).queue();
                }
                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
            }

            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
            return true;
        } catch (SQLException e) {
            System.out.println("Failing connecting to database!");
            return false;
        }
    }

    public void destroy() {
        try {
            conn.close();
            conn = null;
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String query) {
        try {
            this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return stmt.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            return stmt.executeQuery(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int amount = 50;

}
