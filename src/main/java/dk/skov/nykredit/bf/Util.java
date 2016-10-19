package dk.skov.nykredit.bf;

import dk.skov.nykredit.bf.Model.Game;
import dk.skov.nykredit.bf.Model.Player;
import dk.skov.nykredit.bf.Model.Score;
import dk.skov.nykredit.bf.Model.TotalScore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by aogj on 10-07-15.
 *
 * prettymuch only used by newGameDiv.jsp
 *
 * We need to make sure the playersReadyListList is updated before presenting any data.
 */
public class Util {

    public static int INTENSE_LEVEL = 5;

    public static List<List<String>> playersReadyListList = new ArrayList<>();


    public static String t1Intense;
    public static String t1TeamDiff;

    public static String t2Intense;
    public static String t2TeamDiff;

    public static int t1r1;
    public static int t1r2;
    public static int t1b1;
    public static int t1b2;

    public static int t2r1;
    public static int t2r2;
    public static int t2b1;
    public static int t2b2;

    public static Game tableOne;
    public static Game tableTwo;

    private static ScoreBoardGenerator scoreBoardGenerator = ScoreBoardGenerator.getSingleton();

    public static void updateModel(HttpServletRequest request){


        if (request.getParameter("newGame") != null) {
            LinkedList<Player> players = new LinkedList<>(scoreBoardGenerator.getAllPlayers().values());
            Collections.sort(players);

            List<Player> choosenPlayers = new LinkedList<>();
            for (Player player : players) {
                if (player.isReady()) {
                    choosenPlayers.add(player);
                }

                if (choosenPlayers.size() >= 8) {
                    break;
                }
            }

            for (int i = choosenPlayers.size(); i < 8; i++) {
                choosenPlayers.add(null);
            }

            tableOne = new Game(choosenPlayers.get(0), choosenPlayers.get(2), choosenPlayers.get(4), choosenPlayers.get(6));
            tableTwo = new Game(choosenPlayers.get(1), choosenPlayers.get(3), choosenPlayers.get(5), choosenPlayers.get(7));
        }


        //----------

        if (request.getParameter("t1BlueWinner") != null) {
            tableOne.setRedWinner(false);
            SimpleDBHandler.addGame(tableOne);
            scoreBoardGenerator.refreshAll();
        }

        if (request.getParameter("t1RedWinner") != null) {
            tableOne.setRedWinner(true);
            SimpleDBHandler.addGame(tableOne);
            scoreBoardGenerator.refreshAll();
        }

        if (request.getParameter("t2BlueWinner") != null) {
            tableTwo.setRedWinner(false);
            SimpleDBHandler.addGame(tableTwo);
            scoreBoardGenerator.refreshAll();
        }

        if (request.getParameter("t2RedWinner") != null) {
            tableTwo.setRedWinner(true);
            SimpleDBHandler.addGame(tableTwo);
            scoreBoardGenerator.refreshAll();
        }


        //-----------


//        //table 1
//        t1r1 = DBHandler.getTablePlayerPoints(0);
//        t1r2 = DBHandler.getTablePlayerPoints(2);
//        t1b1 = DBHandler.getTablePlayerPoints(1);
//        t1b2 = DBHandler.getTablePlayerPoints(3);
//        int t1TeamDiffInt = (t1r1 + t1r2) - (t1b1 + t1b2);
//        t1TeamDiff = String.valueOf(t1TeamDiffInt).replaceAll("-", "");
//
//        String intenseStrRed = " <font color=red>INTENSE!!!!! " + Util.INTENSE_LEVEL + " points diff exceeded!!!!! Winning red team takes 2 points!</font>";
//        String intenseStrBlue = " <font color=blue>INTENSE!!!!! " + Util.INTENSE_LEVEL + " points diff exceeded!!!!! Winning blue team takes 2 points!</font>";
//        t1Intense = "";
//        if (t1TeamDiffInt >= Util.INTENSE_LEVEL) {
//            t1TeamDiff += intenseStrBlue;
//            t1Intense = "t1b";
//        } else if (t1TeamDiffInt <= Util.INTENSE_LEVEL * -1) {
//            t1TeamDiff += intenseStrRed;
//            t1Intense = "t1r";
//        }
//
//
//        //table 2
//        t2r1 = DBHandler.getTablePlayerPoints(4);
//        t2r2 = DBHandler.getTablePlayerPoints(6);
//        t2b1 = DBHandler.getTablePlayerPoints(5);
//        t2b2 = DBHandler.getTablePlayerPoints(7);
//        int t2TeamDiffInt = (t2r1 + t2r2) - (t2b1 + t2b2);
//        t2TeamDiff = String.valueOf(t2TeamDiffInt).replaceAll("-", "");
//
//        t2Intense = "";
//        if (t2TeamDiffInt >= Util.INTENSE_LEVEL) {
//            t2TeamDiff += intenseStrBlue;
//            t2Intense = "t2b";
//        } else if (t2TeamDiffInt <= Util.INTENSE_LEVEL * -1) {
//            t2TeamDiff += intenseStrRed;
//            t2Intense = "t2r";
//        }
    }

    //TODO
    public static String getTableBGColor(String tableTeam) {
        return "";
    }

    public static String generateScoreboard() {
        List<TotalScore> allScores = scoreBoardGenerator.getAllScores();
        StringBuffer result = new StringBuffer();

        for (TotalScore allScore : allScores) {
            result.append("<td valign=\"top\">");
            result.append("<table align=\"left\" style=\"border:2px solid black;border-collapse:collapse\">\n");

            //Header
            result.append("                <tr>\n").
                    append("                    <th style=\"border:1px solid black;\">#</th>\n").
                    append("                    <th style=\"border:1px solid black;\">Name</th>\n").
                    append("                    <th style=\"border:1px solid black;\">points</th>\n").
                    append("                    <th style=\"border:1px solid black;\">games played</th>\n").
                    append("                </tr>\n");

            int i = 0;
            List<Score> values = new LinkedList<>(allScore.getAllScores().values());
            Collections.sort(values);
            for (Score score : values) {
                result.append("<tr>\n").
                        append("                    <td style=\"border:1px solid black;\">").
                        append(++i).append("\n").append("                    </td>\n").
                        append("                    <td style=\"border:1px solid black;\">").
                        append(score.getPlayer().getName()).append("\n").
                        append("                    </td>\n").
                        append("                    <td style=\"border:1px solid black;\">").
                        append(score.getScore()).append("\n").
                        append("                    </td>\n").
                        append("                    <td style=\"border:1px solid black;\">").
                        append(score.getGamesPlayed()).append("\n").
                        append("                    </td>\n").
                        append("</tr>\n");
            }

            result.append("</table>");
            result.append("</td>");
        }

        return result.toString();
    }

}
