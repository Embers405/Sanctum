package io.ruin.services;

import com.google.common.collect.Lists;
import io.ruin.Server;
import io.ruin.api.database.DatabaseStatement;
import io.ruin.api.database.DatabaseUtils;
import io.ruin.api.utils.XenPost;
import io.ruin.cache.*;
import io.ruin.model.World;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.entity.player.PlayerAction;
import io.ruin.model.entity.player.PlayerGroup;
import io.ruin.model.entity.player.SecondaryGroup;
import io.ruin.model.inter.dialogue.ItemDialogue;
import io.ruin.model.inter.dialogue.MessageDialogue;
import io.ruin.model.inter.dialogue.NPCDialogue;
import io.ruin.model.inter.dialogue.YesNoDialogue;
import io.ruin.model.item.Item;
import io.ruin.model.item.actions.ItemAction;
import io.ruin.model.map.Direction;
import io.ruin.services.discord.DiscordConnection;
import io.ruin.utility.Broadcast;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import static io.ruin.model.entity.player.SecondaryGroup.getGroup;

@Slf4j
public class Store implements Runnable{


    public static final String HOST = "185.2.168.125"; // website ip address
    public static final String USER = "obsidia1_juice";
    public static final String PASS = "kresls112!";
    public static final String DATABASE = "obsidia1_store";

    private Player player;
    private Connection conn;
    private Statement stmt;

    /**
     * The constructor
     * @param player
     */
    public Store(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if (!connect(HOST, DATABASE, USER, PASS)) {
                return;
            }

            String name = player.getName().replace("_", " ");
            ResultSet rs = executeQuery("SELECT * FROM payments WHERE player_name='"+name+"' AND status='Completed' AND claimed=0");

            while (rs.next()) {
                int item_number = rs.getInt("item_number");
                int quantity = rs.getInt("quantity");
                int paid = rs.getInt("paid");
                switch (item_number) {// add products according to their ID in the ACP
                    case 1065: // 10 bond
                        player.getInventory().add(30250, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased " + quantity + "x 10$ Bond!");
                        break;
                    case 1066: // 25 bond
                        player.getInventory().add(30249, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased " + quantity + "x 25$ Bond!");
                        break;
                    case 1067: // 50 bond
                        player.getInventory().add(30251, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased " + quantity + "x 50$ Bond!");
                        break;
                    case 1068: // 100 bond
                        player.getInventory().add(30252, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased " + quantity + "x 100$ Bond!");
                        break;
                    case 1069: // 250 bond
                        player.getInventory().add(30253, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased " + quantity + "x 250$ Bond!");
                        break;
                    case 1070: // 500 bond
                        player.getInventory().add(30254, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased " + quantity + "x 500$ Bond!");
                        break;
                    case 1075: // Battle Pass
                        player.getInventory().add(757, 1);
                        player.sendMessage("Thank you for donating, Your Battle Pass has been activated!");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has purchased The Battle Pass!");
                        player.storeAmountSpent += 75;
                        break;
                    case 1078: // Battle Pass
                        player.getInventory().add(30306, quantity);
                        player.sendMessage("Thank you for donating, Your items have been placed in your Inventory");
                        Broadcast.WORLD.sendNews("[News]" + player.getName() + " has just brought " + ItemDef.get(30306).name + " x " + quantity);
                        player.storeAmountSpent += 10;
                        break;
                }
                if (paid >= 100) {
                    World.startEvent(e -> {
                        for (int i = 0; i < (paid / 100); i++) {
                            new NPC(NpcID.AVATAR_OF_DESTRUCTION_10532).spawn(1777, 3572, 0, Direction.WEST, 1).getCombat().setAllowRespawn(false);
                            Broadcast.GLOBAL.sendNews("<shad=000000>" + Color.BABY_BLUE.wrap("[DONATION BOSS] ") + "Avatar of destruction Has just spawned! use ::donboss to get there!</shad>");
                            Broadcast.GLOBAL.sendNews("<shad=000000>" + Color.BABY_BLUE.wrap("[DONATION BOSS] ") + "Multiple donation bosses pending! 5 minutes until the next one spawns!</shad>");
                            amount = 100;
                            EmbedBuilder eb = new EmbedBuilder();
                            eb.setTitle("An Donation Boss Has Spawned!");
                            eb.setImage("https://drakops.com/Drako/image/donation.png");
                            eb.addField("Notification!", "<@&984907826679939153>", true);
                            eb.addField("Notification!", "<@&984907826679939153>", true);
                            eb.setColor(new java.awt.Color(0xB00D03));
                            DiscordConnection.jda.getTextChannelById("984906544929984512").sendMessageEmbeds(eb.build()).queue();
                            e.delay(500);
                        }
                    });
                } else {
                    amount -= paid;
                    if (amount <= 0) {
                        new NPC(NpcID.AVATAR_OF_DESTRUCTION_10532).spawn(1777, 3572, 0, Direction.WEST, 1).getCombat().setAllowRespawn(false);
                        Broadcast.GLOBAL.sendNews("<shad=000000>"+ Color.BABY_BLUE.wrap("[DONATION BOSS] ")+"Avatar of destruction Has just spawned! use ::donboss to get there!</shad>");
                        amount = 100;
                        EmbedBuilder eb = new EmbedBuilder();
                        eb.setTitle("An Donation Boss Has Spawned!");
                        eb.setImage("https://drakops.com/Drako/image/donation.png");
                        eb.addField("Notification!", "<@&984907826679939153>", true);
                        eb.addField("Notification!", "<@&984907826679939153>", true);
                        eb.setColor(new java.awt.Color(0xB00D03));
                        DiscordConnection.jda.getTextChannelById("984906544929984512").sendMessageEmbeds(eb.build()).queue();
                    }
                }

                getGroup(player);
                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
            }

            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     *
     * @param host the host ip address or url
     * @param database the name of the database
     * @param user the user attached to the database
     * @param pass the users password
     * @return true if connected
     */
    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://"+host+":3306/"+database, user, pass);
            return true;
        } catch (SQLException e) {
            System.out.println("Failing connecting to database!");
            return false;
        }
    }

    /**
     * Disconnects from the MySQL server and destroy the connection
     * and statement instances
     */
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

    /**
     * Executes an update query on the database
     * @param query
     * @see {@link Statement#executeUpdate}
     */
    public int executeUpdate(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    /**
     * Executres a query on the database
     * @param query
     * @see {@link Statement#executeQuery(String)}
     * @return the results, never null
     */
    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static int amount = 100;

/*    static {
        World.startEvent(e -> {
            while(true) {
                e.delay(1500); //10 minutes
                Broadcast.WORLD.sendNews(Icon.ANNOUNCEMENT, "Announcements", "Currently there is $" + Store.amount + " left until the donation boss spawns!");
            }
        });
    }*/

}
