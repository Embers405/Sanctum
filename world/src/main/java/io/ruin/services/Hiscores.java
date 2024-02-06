package io.ruin.services;

import io.ruin.Server;
import io.ruin.api.database.DatabaseStatement;
import io.ruin.api.database.DatabaseUtils;
import io.ruin.model.World;
import io.ruin.model.entity.player.GameMode;
import io.ruin.model.entity.player.Player;
import io.ruin.model.inter.utils.Config;
import io.ruin.model.stat.StatType;
import io.ruin.utility.OfflineMode;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hiscores {

    public static void save(Player player) {
        if (player.isAdmin())
            return;
        saveHiscores(player);
    }

    /**
     * ECO
     */

    private static void saveHiscores(Player player) {

        Server.hsDb.execute(new DatabaseStatement() {
            @Override
            public void execute(Connection connection) throws SQLException {
                PreparedStatement statement = null;
                ResultSet resultSet = null;
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    statement = connection.prepareStatement("SELECT * FROM hs_users WHERE username = ? LIMIT 1", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                    statement.setString(1, player.getName());
                    resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        updateECO(player, resultSet);
                        resultSet.updateRow();
                    } else {
                        resultSet.moveToInsertRow();
                        updateECO(player, resultSet);
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
                System.out.println("FAILED TO UPDATE HISCORES FOR: " + player.getName());
                System.out.println(sw.getBuffer().toString());
                /* do nothing */
            }
        });
    }

    private static void updateECO(Player player, ResultSet resultSet) throws SQLException {
        resultSet.updateString("username", player.getName());
        resultSet.updateInt("rights", player.isStaff() ? player.getPrimaryGroup().id : player.getSecondaryGroup().id);
        resultSet.updateInt("mode", !player.getGameMode().isIronMan() ? 0 : (player.getGameMode().groupId+1));
      //  resultSet.updateInt("combatrate", player.xpMode.getCombatRate());
       // resultSet.updateInt("skillrate", player.xpMode.getSkillRate());
        resultSet.updateInt("total_level", player.getStats().totalLevel);
        resultSet.updateInt("overall_xp", (int) player.getStats().totalXp);
        resultSet.updateInt("attack_xp", (int) player.getStats().get(StatType.Attack).experience);
        resultSet.updateInt("defence_xp", (int) player.getStats().get(StatType.Defence).experience);
        resultSet.updateInt("strength_xp", (int) player.getStats().get(StatType.Strength).experience);
        resultSet.updateInt("constitution_xp", (int) player.getStats().get(StatType.Hitpoints).experience);
        resultSet.updateInt("ranged_xp", (int) player.getStats().get(StatType.Ranged).experience);
        resultSet.updateInt("prayer_xp", (int) player.getStats().get(StatType.Prayer).experience);
        resultSet.updateInt("magic_xp", (int) player.getStats().get(StatType.Magic).experience);
        resultSet.updateInt("cooking_xp", (int) player.getStats().get(StatType.Cooking).experience);
        resultSet.updateInt("woodcutting_xp", (int) player.getStats().get(StatType.Woodcutting).experience);
        resultSet.updateInt("fletching_xp", (int) player.getStats().get(StatType.Fletching).experience);
        resultSet.updateInt("fishing_xp", (int) player.getStats().get(StatType.Fishing).experience);
        resultSet.updateInt("firemaking_xp", (int) player.getStats().get(StatType.Firemaking).experience);
        resultSet.updateInt("crafting_xp", (int) player.getStats().get(StatType.Crafting).experience);
        resultSet.updateInt("smithing_xp", (int) player.getStats().get(StatType.Smithing).experience);
        resultSet.updateInt("mining_xp", (int) player.getStats().get(StatType.Mining).experience);
        resultSet.updateInt("herblore_xp", (int) player.getStats().get(StatType.Herblore).experience);
        resultSet.updateInt("agility_xp", (int) player.getStats().get(StatType.Agility).experience);
        resultSet.updateInt("thieving_xp", (int) player.getStats().get(StatType.Thieving).experience);
        resultSet.updateInt("slayer_xp", (int) player.getStats().get(StatType.Slayer).experience);
        resultSet.updateInt("farming_xp", (int) player.getStats().get(StatType.Farming).experience);
        resultSet.updateInt("runecrafting_xp", (int) player.getStats().get(StatType.Runecrafting).experience);
        resultSet.updateInt("hunter_xp", (int) player.getStats().get(StatType.Hunter).experience);
        resultSet.updateInt("construction_xp", (int) player.getStats().get(StatType.Construction).experience);
/*        resultSet.updateInt("kills", Config.PVP_KILLS.get(player));
        resultSet.updateInt("deaths", Config.PVP_DEATHS.get(player));
        resultSet.updateInt("pk_rating", player.pkRating);
        resultSet.updateInt("highest_shutdown", player.highestShutdown);
        resultSet.updateInt("highest_killspress", player.highestKillSpree);
        resultSet.updateInt("last_man_standing",player.lmsWins);
        resultSet.updateInt("abyssal_sire", player.abyssalSireKills.getKills());
        resultSet.updateInt("alchemical_hydra", player.alchemicalHydraKills.getKills());
        resultSet.updateInt("cerberus",  player.cerberusKills.getKills());
        resultSet.updateInt("giant_mole",  player.giantMoleKills.getKills());
        resultSet.updateInt("kalphite_queen", player.kalphiteQueenKills.getKills());
        resultSet.updateInt("kraken", player.krakenKills.getKills());
        resultSet.updateInt("skotizo", player.skotizoKills.getKills());
        resultSet.updateInt("thermonuclear_smoke_devil", player.thermonuclearSmokeDevilKills.getKills());
        resultSet.updateInt("barrelchest",0);//TODO add BarrelChest KC
        resultSet.updateInt("elvarg", player.elvargKills.getKills());
        resultSet.updateInt("demonic", player.demonicGorillaKills.getKills());
        resultSet.updateInt("giant_roc",0);//TODO add Giant Roc KC
        resultSet.updateInt("vorkath", player.vorkathKills.getKills());
        resultSet.updateInt("zulrah", player.zulrahKills.getKills());
        resultSet.updateInt("king_black_dragon", player.kingBlackDragonKills.getKills());
        resultSet.updateInt("dagannoth_prime", player.dagannothPrimeKills.getKills());
        resultSet.updateInt("dagannoth_rex", player.dagannothRexKills.getKills());
        resultSet.updateInt("dagannoth_supreme", player.dagannothSupremeKills.getKills());
        resultSet.updateInt("callisto", player.callistoKills.getKills());
        resultSet.updateInt("chaos_elemental", player.chaosElementalKills.getKills());
        resultSet.updateInt("chaos_fanatic", player.chaosFanaticKills.getKills());
        resultSet.updateInt("corporeal_beast", player.corporealBeastKills.getKills());
        resultSet.updateInt("crazy_archaeologist", player.crazyArchaeologistKills.getKills());
        resultSet.updateInt("scorpia", player.scorpiaKills.getKills());
        resultSet.updateInt("venenatis", player.venenatisKills.getKills());
        resultSet.updateInt("vetion", player.vetionKills.getKills());
        resultSet.updateInt("barrows_chests", player.barrowsChestsLooted.getKills());
        resultSet.updateInt("wintertodt", player.wintertodtKills.getKills());
        resultSet.updateInt("tztok_jad", player.jadCounter.getKills());
        resultSet.updateInt("tzkal_zuk", player.zukKills.getKills());
        resultSet.updateInt("commander_zilyana", player.commanderZilyanaKills.getKills());
        resultSet.updateInt("general_graardor", player.generalGraardorKills.getKills());
        resultSet.updateInt("kreearra", player.kreeArraKills.getKills());
        resultSet.updateInt("kril_tsutsaroth", player.krilTsutsarothKills.getKills());
        resultSet.updateInt("chambers_of_xeric", player.chambersofXericKills.getKills());
        resultSet.updateInt("theatre_of_blood", player.theatreOfBloodKills.getKills());
        resultSet.updateInt("nightmare",player.nightmareofAshihamaKills.getKills());
        resultSet.updateInt("clue_scroll_beginner",player.beginnerClueCount);
        resultSet.updateInt("clue_scroll_easy",player.easyClueCount);
        resultSet.updateInt("clue_scroll_medium",player.medClueCount);
        resultSet.updateInt("clue_scroll_hard",player.hardClueCount);
        resultSet.updateInt("clue_scroll_elite",player.eliteClueCount);
        resultSet.updateInt("clue_scroll_master",   player.masterClueCount); */
    }

}