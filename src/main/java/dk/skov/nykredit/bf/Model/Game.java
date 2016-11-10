package dk.skov.nykredit.bf.Model;

import java.util.Date;

/**
 * Created by super on 26/09/2016.
 */
public class Game implements Comparable<Game> {
    private int id;
    private Player player_red_1;
    private Player player_red_2;
    private Player player_blue_1;
    private Player player_blue_2;
    private Date timestamp;
    private boolean redWinner;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Player getPlayer_red_1() {
        return player_red_1;
    }

    public Player getPlayer_red_2() {
        return player_red_2;
    }

    public Player getPlayer_blue_1() {
        return player_blue_1;
    }

    public Player getPlayer_blue_2() {
        return player_blue_2;
    }

    public String getPlayer_red_1_name() {
        return player_red_1 != null ? player_red_1.getName() : "";
    }

    public String getPlayer_red_2_name() {
        return player_red_2 != null ? player_red_2.getName() : "";
    }


    public String getPlayer_blue_1_name() {
        return player_blue_1 != null ? player_blue_1.getName() : "";
    }


    public String getPlayer_blue_2_name() {
        return player_blue_2 != null ? player_blue_2.getName() : "";
    }


    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isRedWinner() {
        return redWinner;
    }

    public void setRedWinner(boolean redWinner) {
        this.redWinner = redWinner;
    }

    public Game(Player player_red_1, Player player_red_2, Player player_blue_1, Player player_blue_2) {
        this.player_red_1 = player_red_1;
        this.player_red_2 = player_red_2;
        this.player_blue_1 = player_blue_1;
        this.player_blue_2 = player_blue_2;
    }

    public Game(int id, Player player_red_1, Player player_red_2, Player player_blue_1, Player player_blue_2, Date timestamp, boolean redWinner) {
        this.id = id;
        this.player_red_1 = player_red_1;
        this.player_red_2 = player_red_2;
        this.player_blue_1 = player_blue_1;
        this.player_blue_2 = player_blue_2;
        this.timestamp = timestamp;
        this.redWinner = redWinner;
    }

    @Override
    public int compareTo(Game o) {
        return this.getTimestamp().compareTo(o.getTimestamp());
    }

    public String toString() {
        return player_red_1 + ", " + player_red_2 + ", " + player_blue_1 + ", " + player_blue_2 + "( id: " + id + ", " + redWinner + ")";
    }

    public String getWinner() {
        if (redWinner) {
            return "red";
        } else {
            return "blue";
        }
    }




}
