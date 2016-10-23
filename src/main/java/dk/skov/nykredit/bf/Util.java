package dk.skov.nykredit.bf;

import dk.skov.nykredit.bf.Model.Game;
import dk.skov.nykredit.bf.Model.Player;
import dk.skov.nykredit.bf.Model.Score;
import dk.skov.nykredit.bf.Model.TotalScore;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by aogj on 10-07-15.
 * <p>
 * prettymuch only used by newGameDiv.jsp
 * <p>
 * We need to make sure the playersReadyListList is updated before presenting any data.
 */
public class Util {

    public static Game tableOne;
    public static Game tableTwo;

    private static ScoreBoardGenerator scoreBoardGenerator = ScoreBoardGenerator.getSingleton();

    public static void updateModel(HttpServletRequest request) {
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
            tableOne.setTimestamp(GregorianCalendar.getInstance().getTime());
            SimpleDBHandler.addGame(tableOne);
            scoreBoardGenerator.updateGame(tableOne, true);
        }

        if (request.getParameter("t1RedWinner") != null) {
            tableOne.setRedWinner(true);
            tableOne.setTimestamp(GregorianCalendar.getInstance().getTime());
            SimpleDBHandler.addGame(tableOne);
            scoreBoardGenerator.updateGame(tableOne, true);
        }

        if (request.getParameter("t2BlueWinner") != null) {
            tableTwo.setRedWinner(false);
            tableTwo.setTimestamp(GregorianCalendar.getInstance().getTime());
            SimpleDBHandler.addGame(tableTwo);
            scoreBoardGenerator.updateGame(tableTwo, true);
        }

        if (request.getParameter("t2RedWinner") != null) {
            tableTwo.setRedWinner(true);
            tableTwo.setTimestamp(GregorianCalendar.getInstance().getTime());
            SimpleDBHandler.addGame(tableTwo);
            scoreBoardGenerator.updateGame(tableTwo, true);
        }
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

    public static int getBlueSumTableOne() {
        return scoreBoardGenerator.getBlueSum(tableOne);
    }

    public static int getRedSumTableOne() {
        return scoreBoardGenerator.getRedSum(tableOne);
    }

    public static int getBlueSumTableTwo() {
        return scoreBoardGenerator.getBlueSum(tableTwo);
    }

    public static int getRedSumTableTwo() {
        return scoreBoardGenerator.getRedSum(tableTwo);
    }

}
