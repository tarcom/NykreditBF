package dk.skov.nykredit.bf;

import dk.skov.nykredit.bf.Model.Game;
import dk.skov.nykredit.bf.Model.Player;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by super on 26/09/2016.
 */
public class SimpleDBHandler {


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

    public static List<Player> getAllPlayers() {
        List<Player> result = new LinkedList<>();

        Statement stmt = null;
        Connection connection = null;
        ResultSet rs;

        try {
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tbl_players");

            while (rs.next()) {
                List<String> list = new ArrayList<>();
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    list.add(rs.getString(i));
                }
                result.add(new Player(Integer.valueOf(list.get(0)), list.get(1), list.get(2).equals("1")));
            }

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

        return result;
    }

    public static List<Game> getAllGames(Map<String, Player> players) {
        List<Game> result = new LinkedList<>();

        Statement stmt = null;
        Connection connection = null;
        ResultSet rs;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            connection = getConnection();
            stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM tbl_fights ORDER BY timestamp DESC");

            while (rs.next()) {
                List<String> list = new ArrayList<>();
                for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                    list.add(rs.getString(i));
                }
                result.add(new Game(
                        Integer.valueOf(list.get(0)),
                        players.get(list.get(1)),
                        players.get(list.get(2)),
                        players.get(list.get(3)),
                        players.get(list.get(4)),
                        format.parse(list.get(5)),
                        list.get(6).equals("red")));
            }

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

        return result;
    }

    public static void addGame(Game game) {
        String sql = "INSERT INTO tbl_fights( " +
                "player_red_1, " +
                "player_red_2, " +
                "player_blue_1, " +
                "player_blue_2, " +
                "points_at_steake, " +
                "winning_table, " +
                "match_winner) " +
                "VALUES ('" +
                game.getPlayer_red_1().getName() + "' , '" +
                game.getPlayer_red_2().getName() + "' , '" +
                game.getPlayer_blue_1().getName() + "' , '" +
                game.getPlayer_blue_2().getName() + "' , " +
                " 1, " +
                " 1, " +
                "'" + game.getWinner() + "')";
        executeUpdateDb(sql);
    }

    public static void addPlayer(String name) {
        String sql = "INSERT INTO `tbl_players` (`name`, `playerReady`) VALUES ('" + name + "', 1)";
        executeUpdateDb(sql);
    }


    public static void switchPlayerReady(String name) {
        String sql = "UPDATE  `tbl_players` SET  `playerReady` = !`playerReady` WHERE name = '" + name + "'";
        executeUpdateDb(sql);
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

}
