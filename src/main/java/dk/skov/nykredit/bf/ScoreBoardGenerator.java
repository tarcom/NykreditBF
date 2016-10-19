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
        for (TotalScore allScore : allScores) {
            if (game.getTimestamp().after(allScore.getFromDate())) {
                updateTotalScore(game, allScore);
            }
        }
        updateTotalScore(game, allTimeScore);
    }

    private void updateTotalScore(Game game, TotalScore allScore) {
        int blueAheadPoints = getBlueAheadPoints(game);

        if (blueAheadPoints <= -5) {
            allScore.addOneGame(game.getPlayer_red_1(), game.isRedWinner() ? 1 : -2, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_red_2(), game.isRedWinner() ? 1 : -2, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_blue_1(), game.isRedWinner() ? -1 : 2, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_blue_2(), game.isRedWinner() ? -1 : 2, game.getTimestamp());
        } else if (blueAheadPoints >= 5) {
            allScore.addOneGame(game.getPlayer_red_1(), game.isRedWinner() ? 2 : -1, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_red_2(), game.isRedWinner() ? 2 : -1, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_blue_1(), game.isRedWinner() ? -2 : 1, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_blue_2(), game.isRedWinner() ? -2 : 1, game.getTimestamp());
        } else {
            allScore.addOneGame(game.getPlayer_red_1(), game.isRedWinner() ? 1 : -1, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_red_2(), game.isRedWinner() ? 1 : -1, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_blue_1(), game.isRedWinner() ? -1 : 1, game.getTimestamp());
            allScore.addOneGame(game.getPlayer_blue_2(), game.isRedWinner() ? -1 : 1, game.getTimestamp());
        }
    }

    private int getBlueAheadPoints(Game game) {
        int redSum = 0;
        Score score = allTimeScore.getAllScores().get(game.getPlayer_red_1());
        if (score != null) {
            redSum += score.getScore();
        }
        score = allTimeScore.getAllScores().get(game.getPlayer_red_2());
        if (score != null) {
            redSum += score.getScore();
        }

        int blueSum = 0;
        score = allTimeScore.getAllScores().get(game.getPlayer_blue_1());
        if (score != null) {
            blueSum += score.getScore();
        }
        score = allTimeScore.getAllScores().get(game.getPlayer_blue_2());
        if (score != null) {
            blueSum += score.getScore();
        }

        return blueSum - redSum;
    }
}
