package io.ruin.model.entity.npc.actions.zanik;

import io.ruin.Server;
import io.ruin.api.database.DatabaseStatement;
import io.ruin.api.database.DatabaseUtils;
import io.ruin.api.utils.Random;
import io.ruin.cache.Color;
import io.ruin.cache.NpcID;
import io.ruin.model.entity.npc.NPC;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.map.Direction;
import io.ruin.model.stat.StatType;
import io.ruin.services.discord.DiscordConnection;
import io.ruin.utility.Broadcast;
import net.dv8tion.jda.api.EmbedBuilder;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

public class Zanik implements Runnable {

    public static final String HOST = "185.2.168.125";
    public static final String USER = "obsidia1_juice";
    public static final String PASS = "kresls112!";
    public static final String DATABASE = "obsidia1_drakodb";

    private Player player;
    private Connection conn;
    private Statement stmt;

    @Override
    public void run() {
        try {
            if (!connect(HOST, DATABASE, USER, PASS)) {
                return;
            }

            String name = player.getName();
            ResultSet rs = executeQuery("SELECT * FROM votes WHERE username='"+name+"' AND claimed=0 AND voted_on != -1");

            while (rs.next()) {

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
}
