package dk.skov.nykredit.bf;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aogj on 09-07-15.
 */
public class DBHandler {

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws Exception {
        System.out.println("hello");
        //DBHandler dbHandler = new DBHandler();

        //dbHandler.insertPlayer("from main");
//        List<List<String>> results = DBHandler.genericSelect("SELECT `id`, `name`, `oprettet` FROM `tbl_players`");
//
//        for (List<String> result : results) {
//            System.out.println(result.get(0));
//            System.out.println(result.get(1));
//            System.out.println(result.get(2));
//        }

        getNumberOfGamlesPlayed("alsk");

        System.out.println("bye");
    }

    @Deprecated
    public static int getTablePlayerPointsOLD(int playerId) {
        String points = DBHandler.genericSelect("SELECT sum(points) FROM `tbl_points` WHERE name = '" + Util.playersReadyListList.get(playerId).get(0) + "'").get(0).get(0);
        if (points == null) {
            return 0;
        }
        return Integer.valueOf(points);
    }

    public static List<String> getPlayers() {
        return getPlayers(999999999);
    }

    public static List<String> getPlayers(int daysBackHistory) {
        String sql = "SELECT DISTINCT(player_red_1) FROM `tbl_fights` \n" +
                "where (DATEDIFF(NOW(), `timestamp`) < 9999)\n" +
                "UNION\n" +
                "SELECT DISTINCT(player_red_2) FROM `tbl_fights` \n" +
                "where (DATEDIFF(NOW(), `timestamp`) < 9999)\n" +
                "UNION\n" +
                "SELECT DISTINCT(player_blue_1) FROM `tbl_fights` \n" +
                "where (DATEDIFF(NOW(), `timestamp`) < 9999)\n" +
                "UNION\n" +
                "SELECT DISTINCT(player_blue_2) FROM `tbl_fights` \n" +
                "where (DATEDIFF(NOW(), `timestamp`) < 9999)";
        List<List<String>> genericSelect = genericSelect(sql);

        List<String> players = new ArrayList<>();
        for (List<String> s : genericSelect) {
            players.add(s.get(0));
        }

        return players;
    }

    public static int getGamesPlayed(String playerName) {
        return getGamesPlayed(playerName, 9999999);
    }

    public static int getGamesPlayed(String playerName, int daysBackHistory) {
        String sql = "SELECT count(*) FROM `tbl_fights` \n" +
                "WHERE player_red_1 = '" + playerName + "'\n" +
                "or player_red_2 = '" + playerName + "'\n" +
                "or player_blue_1 = '" + playerName + "'\n" +
                "or player_blue_2 = '" + playerName + "'\n" +
                "and (DATEDIFF(NOW(), `timestamp`) < " + daysBackHistory + ")";
        String gamesPlayed = genericSelect(sql).get(0).get(0);
        if (gamesPlayed == null) {
            return 0;
        }


        return Integer.valueOf(gamesPlayed);
    }

    public static int getTablePlayerPoints(int playerId) {
        return getTablePlayerPoints(playerId, 999999999);
    }

    public static int getTablePlayerPoints(int playerId, int daysBackHistory) {
        String player = Util.playersReadyListList.get(playerId).get(0);
        return getTablePlayerPoints(player, daysBackHistory);
    }

    public static int getTablePlayerPoints(String playerName) {
        return getTablePlayerPoints(playerName, 999999999);
    }

    public static int getTablePlayerPoints(String playerName, int daysBackHistory) {
        System.out.println("playerName=" + playerName);
        if (playerName == null || "".equalsIgnoreCase(playerName)) {
            return 0;
        }
        String points = DBHandler.genericSelect("" +
                "select sum(\n" +
                "(    \n" +
                "SELECT IFNULL(sum(points_at_steake),0) FROM `tbl_fights` \n" +
                "WHERE (player_red_1 = '" + playerName + "' or player_red_2 = '" + playerName + "')\n" +
                "and match_winner = \"red\"\n" +
                "and (DATEDIFF(NOW(), `timestamp`) < " + daysBackHistory + ")\n" +
                ")\n" +
                "+\n" +
                "(\n" +
                "SELECT IFNULL(sum(points_at_steake),0) FROM `tbl_fights` \n" +
                "WHERE (player_blue_1 = '" + playerName + "' or player_blue_2 = '" + playerName + "')\n" +
                "and match_winner = \"blue\"\n" +
                "and (DATEDIFF(NOW(), `timestamp`) < " + daysBackHistory + ")\n" +
                ")\n" +
                "-\n" +
                "(    \n" +
                "SELECT IFNULL(sum(points_at_steake),0) FROM `tbl_fights` \n" +
                "WHERE (player_red_1 = '" + playerName + "' or player_red_2 = '" + playerName + "')\n" +
                "and match_winner = \"blue\"\n" +
                "and (DATEDIFF(NOW(), `timestamp`) < " + daysBackHistory + ")\n" +
                ")\n" +
                "-\n" +
                "(\n" +
                "SELECT IFNULL(sum(points_at_steake),0) FROM `tbl_fights` \n" +
                "WHERE (player_blue_1 = '" + playerName + "' or player_blue_2 = '" + playerName + "')\n" +
                "and match_winner = \"red\"\n" +
                "and (DATEDIFF(NOW(), `timestamp`) < " + daysBackHistory + ")\n" +
                ")\n" +
                ")" +
                "").get(0).get(0);
        if (points == null) {
            return 0;
        }
        return Integer.valueOf(points);
    }

    public static List<List<String>> genericSelect(String sqlStr) {

        System.out.println("genericSelect enter. sql=" + sqlStr);
        Statement stmt = null;
        Connection connection = null;
        ResultSet rs;

        List<List<String>> results = new ArrayList<>();

        try {
            connection = getConnection();
            stmt = connection.createStatement();

            //System.out.println("About to fire sqlStr=" + sqlStr);

            rs = stmt.executeQuery(sqlStr);


            //System.out.println("columncount=" + rs.getMetaData().getColumnCount());
            while (rs.next()) {
                List<String> list = new ArrayList<>();
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    list.add(rs.getString(i));
                }
                results.add(list);
            }

            //System.out.println("Found " + list.size() + " entries in db.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Cannot close statement or connection." + e);
            }
        }

        return results;
    }

    private static Connection getConnection() {
        Connection con = null;
        try {
            String url = "jdbc:mysql://localhost:3306/NykreditBF";
            String username = "root";
            String passwd = "";
            con = DriverManager.getConnection(url, username, passwd);
        } catch (Exception e) {
            System.out.println("FATAL. Cannot get a db connection!" + e);
        }
        return con;
    }

    public static void fightWon(String r1, String r2, String b1, String b2, String winColor, int pointsAtSteake, int winningTable) {
        String sql = "INSERT INTO tbl_fights( " +
                "player_red_1, " +
                "player_red_2, " +
                "player_blue_1, " +
                "player_blue_2, " +
                "match_winner, " +
                "points_at_steake, " +
                "winning_table ) " +
                "VALUES ('" +
                r1 + "' , '" +
                r2 + "' , '" +
                b1 + "' , '" +
                b2 + "' , " +
                "'" + winColor + "' , " +
                "'" + pointsAtSteake + "' , " +
                winningTable + ")";
        //System.out.println(sql);
        DBHandler.executeUpdateDb(sql);

        System.out.println(pointsAtSteake * -1);
        System.out.println(pointsAtSteake);
        System.out.println(-pointsAtSteake);

        int sign = (winColor.equalsIgnoreCase("red") ? 1 : -1);

        DBHandler.executeUpdateDb("INSERT INTO `tbl_points`(`name`, `points`, `winning_table`) VALUES ('" + r1 + "', " + (pointsAtSteake * sign) + ", " + winningTable + ")");
        if (!r2.isEmpty()) {
            DBHandler.executeUpdateDb("INSERT INTO `tbl_points`(`name`, `points`, `winning_table`) VALUES ('" + r2 + "', " + (pointsAtSteake * sign) + ", " + winningTable + ")");
        }

        DBHandler.executeUpdateDb("INSERT INTO `tbl_points`(`name`, `points`, `winning_table`) VALUES ('" + b1 + "', " + (pointsAtSteake * -sign) + ", " + winningTable + ")");
        if (!b2.isEmpty()) { //normal case. two against two.
            DBHandler.executeUpdateDb("INSERT INTO `tbl_points`(`name`, `points`, `winning_table`) VALUES ('" + b2 + "', " + (pointsAtSteake * -sign) + ", " + winningTable + ")");
        } else if (b2.isEmpty() && !r2.isEmpty()) { //double points to blue side, as one player won against two players
            DBHandler.executeUpdateDb("INSERT INTO `tbl_points`(`name`, `points`, `winning_table`) VALUES ('" + b1 + "', " + (pointsAtSteake * -sign) + ", " + winningTable + ")");
        }

    }

    public static int executeUpdateDb(String sqlStr) {
        System.out.println("executeUpdateDb Enter. sqlStr=" + sqlStr);
        Statement stmt = null;
        Connection connection = null;
        try {
            connection = getConnection();
            stmt = connection.createStatement();
            return stmt.executeUpdate(sqlStr);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        } finally {
            try {
                if (!stmt.isClosed()) {
                    stmt.close();
                }
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Cannot close statement or connection." + e);
            }
        }

    }

    public static int getNumberOfGamlesPlayed(String playerName) {
        Connection dbConnection = null;
        PreparedStatement preparedStatement = null;

        int count = -1;

        String selectSQL = "SELECT *\n" +
                "FROM tbl_fights\n" +
                "where player_red_1 or player_red_2 or player_blue_1 or player_blue_2 = ?";

        try {
            dbConnection = getConnection();
            preparedStatement = dbConnection.prepareStatement(selectSQL);
            preparedStatement.setString(1, playerName);

            // execute select SQL stetement
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
                System.out.println("count=" + count);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (dbConnection != null) {
                    dbConnection.close();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return count;
    }

}
