package dk.skov.nykredit.bf;

import java.util.*;

/**
 * Created by aogj on 21-08-2016.
 */
public class ScoreBoardGenerator {

    public static ArrayList<ArrayList<String>> getScore() {
        return getScore(999999999);
    }

    public static ArrayList<ArrayList<String>> getScore(int daysBackHistory) {

        TreeMap<Double, ArrayList<String>> resMap = new TreeMap<>();

        Random random = new Random();

        List<String> players = DBHandler.getPlayers(daysBackHistory);
        for (String player : players) {
            int pointsInt = DBHandler.getTablePlayerPoints(player, daysBackHistory);
            int gamesPlayed = DBHandler.getGamesPlayed(player, daysBackHistory);
            ArrayList<String> info = new ArrayList<>();
            info.add(player);
            info.add(String.valueOf(pointsInt));
            info.add(String.valueOf(gamesPlayed));
            double d = random.nextDouble();
            double pointsD = pointsInt + d;
            resMap.put(pointsD, info);
        }

        ArrayList<ArrayList<String>> resList = new ArrayList<ArrayList<String>>();

        for (double d : resMap.keySet()) {
            ArrayList<String> s = resMap.get(d);
            resList.add(s);
        }

        Collections.reverse(resList);
        return resList;
    }

    public static void main(String[] args) {
        ArrayList<ArrayList<String>> score = getScore();
        System.out.println(score);
    }

}
