package dk.skov.nykredit.bf.Model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by super on 26/09/2016.
 */
public class TotalScore {
    private Map<Player, Score> allScores;
    private Date fromDate;

    public Map<Player, Score> getAllScores() {
        return allScores;
    }
    public Date getFromDate() {
        return fromDate;
    }

    public TotalScore(Date fromDate) {
        this.fromDate = fromDate;
        allScores = new HashMap<>();
    }

    public void addOneGame(Player player, int pointDifference, Date timestamp) {
        if (player != null) {
            allScores.putIfAbsent(player, new Score(player, 0, 0));
            allScores.get(player).addOneGamePlayed();
            allScores.get(player).changeScore(pointDifference);
            player.updateLastGamePlayedIfNewerDate(timestamp);
        }
    }
}
