package dk.skov.nykredit.bf.Model;

/**
 * Created by super on 26/09/2016.
 */
public class Score implements Comparable<Score> {
    private Player player;
    private int score;
    private int gamesPlayed;

    public Score(Player player, int score, int gamesPlayed) {
        this.player = player;
        this.score = score;
        this.gamesPlayed = gamesPlayed;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void changeScore(int difference) {
        score += difference;
    }

    public void addOneGamePlayed() {
        gamesPlayed++;
    }

    @Override
    public int compareTo(Score o) {
        return o.getScore() - getScore();
    }
}
