package io.ruin.model.inter.battlepass;

import io.ruin.Server;
import io.ruin.api.database.DatabaseStatement;
import io.ruin.api.database.DatabaseUtils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.*;

public class LevelReadWrite {
/*
    public static void writeLevels(int level, int itemid, int itemamount, int itemstate) {
        Server.siteDb.execute(new DatabaseStatement() {
            @Override
            public void execute(Connection connection) throws SQLException {
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    statement = connection.prepareStatement("SELECT * FROM battle_pass WHERE level = ? LIMIT 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    statement.setInt(1, level);
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        battlepasslevels(itemid,itemamount,itemstate, resultSet);
                        resultSet.updateRow();
                    } else {
                        resultSet.moveToInsertRow();
                        battlepasslevels(itemid,itemamount,itemstate, resultSet);
                        resultSet.insertRow();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    DatabaseUtils.close(statement, resultSet);
                }
            }

            @Override
            public void failed(Throwable t) {
                final StringWriter sw = new StringWriter();
                final PrintWriter pw = new PrintWriter(sw, true);
                t.printStackTrace(pw);
                System.out.println("[BattlePass] Failed to write levels!");
                System.out.println(sw.getBuffer().toString());
            }
        });
    }

    public static void readLevels() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection dbConnection = DriverManager.getConnection("jdbc:mysql://mysql.drakops.com:3306/drakodb",
                        "drakocomm", "T8aW9r5sM2oTJT");

                String query = "SELECT * FROM `battle_pass`";

                Statement st = dbConnection.createStatement();

                ResultSet rs = st.executeQuery(query);
                while (rs.next()) {
                    BattlePass.levels.add(new Level(rs.getInt("level"),rs.getInt("item_id"),rs.getInt("item_amount"),0));
                }
                st.close();
            } catch (Exception e) {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
    }

    private static void battlepasslevels(int itemid, int itemamount, int itemstate, ResultSet resultSet) throws SQLException {
        resultSet.updateInt("item_id", itemid);
        resultSet.updateInt("item_amount", itemamount);
        resultSet.updateInt("item_state", itemstate);
    }*/
}
