package io.ruin.services;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.ruin.model.entity.player.Player;

import java.io.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Referral implements Runnable {

    private Player player;

    public static final String HOST = "185.2.168.125"; // database host
    public static final String USER = "obsidia1_juice"; // database username
    public static final String PASS = "kresls112!"; // database password
    public static final String DATABASE = "obsidia1_referal"; // database name

    public Referral(Player player,String referral) {
        this.player = player;
        this.referral = referral;
    }

    public static List<String> ReferralipsClaimed = new ArrayList<>();

    public static boolean alreadyClaimed(Player player) {
        return ReferralipsClaimed.contains(player.getIp());
    }

    public static void addIpsClaimed(String ip) {
        if(ReferralipsClaimed.contains(ip))
            return;
        ReferralipsClaimed.add(ip);
        saveIps();
    }
    public static void saveIps(){
        new Thread(() -> {
            File file = new File(System.getProperty("user.home") + "/Desktop/Obsidian/referral_claimed_ips.json");
            if(!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileWriter fileWriter = new FileWriter(file);
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                String toJson = gson.toJson(ReferralipsClaimed);
                fileWriter.write(toJson);
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void loadIps() {
        File file = new File(System.getProperty("user.home") + "/Desktop/Obsidian/referral_claimed_ips.json");
        if(!file.exists()) {
            try {
                file.createNewFile();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try {
            ArrayList<String> temp = gson.fromJson(new FileReader(file), type);
            if(temp != null)
                ReferralipsClaimed = temp;
            System.out.println("Loaded " + ReferralipsClaimed.size() + " referral claimed ids.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public String referral;

    public void update() {
        try
        {
            // create our mysql database connection
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE, USER, PASS);

            // create the java mysql update preparedstatement
            String query = "UPDATE referral SET Used = Used + 1 WHERE user =" + "'" + referral.toLowerCase() + "'";
            PreparedStatement preparedStmt = conn.prepareStatement(query);

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            conn.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
    }

    public void run()
    {
        boolean alreadyClaimed = alreadyClaimed(player);
        if(!alreadyClaimed)
            try
            {
                // create our mysql database connection
                Connection conn = DriverManager.getConnection("jdbc:mysql://" + HOST + ":3306/" + DATABASE, USER, PASS);

                // our SQL SELECT query.
                // if you only need a few columns, specify them by name instead of using "*"
                String query = "SELECT * FROM referral WHERE str ="+ "'" + referral.toLowerCase() + "'";

                // create the java statement
                Statement st = conn.createStatement();

                // execute the query, and get a java resultset
                ResultSet rs = st.executeQuery(query);

                // iterate through the java resultset
                while (rs.next())
                {
                    String name = rs.getString("str");
                    if (name != null) {
                        player.referral = true;
                        player.getInventory().addOrDrop(6199,1);
                        addIpsClaimed(player.getIp());
                        update();
                    }
                }
                st.close();
            }
            catch (Exception e)
            {
                player.sendMessage("This referral code doesn't exist!");
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
    }
}
