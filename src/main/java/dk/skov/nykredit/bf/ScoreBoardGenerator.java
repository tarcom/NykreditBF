package dk.skov.nykredit.bf;

import dk.skov.nykredit.bf.Model.Game;
import dk.skov.nykredit.bf.Model.Player;
import dk.skov.nykredit.bf.Model.Score;
import dk.skov.nykredit.bf.Model.TotalScore;

import java.util.*;

/**
 * Created by aogj on 21-08-2016.
 */
public class ScoreBoardGenerator {

    public static int INTENSE_LEVEL = 5;

    public enum RedDog {
        TOPDOG, UNDERDOG, NODOG
    }

    private List<Game> allGames = new LinkedList<>();
    private Map<String, Player> allPlayers = new HashMap<>();
    private List<TotalScore> allScores = new LinkedList<>();
    private TotalScore allTimeScore;

    private static final ScoreBoardGenerator singleton = new ScoreBoardGenerator();

    public List<Game> getAllGames() {
        return allGames;
    }

    public Map<String, Player> getAllPlayers() {
        return allPlayers;
    }

    public List<Player> getAllPlayesSortedByName() {
        ArrayList<Player> names = new ArrayList<>(allPlayers.values());
        Collections.sort(names, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
            }
        });
        return names;
    }

    public List<TotalScore> getAllScores() {
        return allScores;
    }

    public static ScoreBoardGenerator getSingleton() {
        return singleton;
    }

    public ScoreBoardGenerator() {
        refreshAll();
    }

    public void refreshAll() {
        refreshPlayers();
        allGames = SimpleDBHandler.getAllGames(allPlayers);
        List<Date> defaultDates = new LinkedList<>();

        Calendar calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        defaultDates.add(calendar.getTime());

        calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.DATE, -7);
        defaultDates.add(calendar.getTime());

        calendar = GregorianCalendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        defaultDates.add(calendar.getTime());

        calendar = GregorianCalendar.getInstance();
        calendar.setTimeInMillis(0);
        defaultDates.add(calendar.getTime());

        allTimeScore = new TotalScore(calendar.getTime());

        refreshScore(defaultDates);
    }

    public void refreshPlayers() {
        allPlayers = new HashMap<>();
        List<Player> players = SimpleDBHandler.getAllPlayers();

        for (Player player : players) {
            allPlayers.putIfAbsent(player.getName(), player);
        }
    }

    public void refreshScore(List<Date> beforeDates) {
        allScores = new LinkedList<>();
        for (Date beforeDate : beforeDates) {
            allScores.add(new TotalScore(beforeDate));
        }

        for (Game game : allGames) {
            updateGame(game);
        }
    }

    public void updateGame(Game game) {
        updateGame(game, false);
    }

    public void updateGame(Game game, boolean updateAllGames) {
        for (TotalScore allScore : allScores) {
            if (game.getTimestamp().after(allScore.getFromDate())) {
                updateTotalScore(game, allScore);
            }
        }
        updateTotalScore(game, allTimeScore);

        if (updateAllGames) {
            allGames.add(0, game);
        }
    }

    public void switchReadyPlayer(String name) {
        allPlayers.get(name).setReady(!allPlayers.get(name).isReady());
    }

    public void addPlayer(String name) {
        allPlayers.put(name, new Player(name, true));
    }

    private void updateTotalScore(Game game, TotalScore allScore) {
        allScore.addOneGame(game.getPlayer_red_1(), getPointsDifference(game, game.isRedWinner(), true), game.getTimestamp());
        allScore.addOneGame(game.getPlayer_red_2(), getPointsDifference(game, game.isRedWinner(), true), game.getTimestamp());
        allScore.addOneGame(game.getPlayer_blue_1(), getPointsDifference(game, game.isRedWinner(), false), game.getTimestamp());
        allScore.addOneGame(game.getPlayer_blue_2(), getPointsDifference(game, game.isRedWinner(), false), game.getTimestamp());
    }

    public int getPointsDifference(Game game, boolean redWins, boolean knowRedPlayersPointsDifference) {
        int pointsDifferenceIfEqualTeams = getPointsDifferenceIfEqualTeams(game, redWins, knowRedPlayersPointsDifference);

        //If a players wins alone against 2 others, he willdouble his points.
        if (knowRedPlayersPointsDifference && game.getPlayer_red_1() == null && (game.getPlayer_blue_1() != null && game.getPlayer_blue_2() != null)) {
            pointsDifferenceIfEqualTeams = pointsDifferenceIfEqualTeams * 2;
        } else if (knowRedPlayersPointsDifference &&game.getPlayer_red_2() == null && (game.getPlayer_blue_1() != null && game.getPlayer_blue_2() != null)) {
            pointsDifferenceIfEqualTeams = pointsDifferenceIfEqualTeams * 2;
        } else if (!knowRedPlayersPointsDifference && game.getPlayer_blue_1() == null && (game.getPlayer_red_1() != null && game.getPlayer_red_2() != null)) {
            pointsDifferenceIfEqualTeams = pointsDifferenceIfEqualTeams * 2;
        } else if (!knowRedPlayersPointsDifference && game.getPlayer_blue_2() == null && (game.getPlayer_red_1() != null && game.getPlayer_red_2() != null)) {
            pointsDifferenceIfEqualTeams = pointsDifferenceIfEqualTeams * 2;
        }

        return pointsDifferenceIfEqualTeams;
    }

    private int getPointsDifferenceIfEqualTeams(Game game, boolean redWins, boolean knowRedPlayersPointsDifference) {
        RedDog redDog = getRedDog(game);

        if (redDog == RedDog.TOPDOG) {
            if (redWins && knowRedPlayersPointsDifference) return 1;
            if (redWins && !knowRedPlayersPointsDifference) return -1;
            if (!redWins && knowRedPlayersPointsDifference) return -2;
            else return 2; //(!redWins && !knowRedPlayersPointsDifference)
        } else if (redDog == RedDog.UNDERDOG) {
            if (redWins && knowRedPlayersPointsDifference) return 2;
            if (redWins && !knowRedPlayersPointsDifference) return -2;
            if (!redWins && knowRedPlayersPointsDifference) return -1;
            else return 1; //(!redWins && !knowRedPlayersPointsDifference)
        } else {
            if (redWins && knowRedPlayersPointsDifference) return 1;
            if (redWins && !knowRedPlayersPointsDifference) return -1;
            if (!redWins && knowRedPlayersPointsDifference) return -1;
            else return 1; //(!redWins && !knowRedPlayersPointsDifference)
        }
    }


    public RedDog getRedDog(Game game) {
        int blueAheadPoints = getBlueAheadPoints(game);

        if (blueAheadPoints <= -INTENSE_LEVEL) {
            return RedDog.TOPDOG;
        } else if (blueAheadPoints >= INTENSE_LEVEL) {
            return RedDog.UNDERDOG;
        } else {
            return RedDog.NODOG;
        }

    }

    public int getBlueAheadPoints(Game game) {
        int redSum = getRedSum(game);
        int blueSum = getBlueSum(game);
        return blueSum - redSum;
    }

    public int getBlueSum(Game game) {
        Score score;

        int blueSum = 0;
        score = allTimeScore.getAllScores().get(game.getPlayer_blue_1());
        if (score != null) {
            blueSum += score.getScore();
        }
        score = allTimeScore.getAllScores().get(game.getPlayer_blue_2());
        if (score != null) {
            blueSum += score.getScore();
        }
        return blueSum;
    }

    public int getRedSum(Game game) {
        int redSum = 0;
        Score score = allTimeScore.getAllScores().get(game.getPlayer_red_1());
        if (score != null) {
            redSum += score.getScore();
        }
        score = allTimeScore.getAllScores().get(game.getPlayer_red_2());
        if (score != null) {
            redSum += score.getScore();
        }
        return redSum;
    }

}
