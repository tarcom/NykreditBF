package dk.skov.nykredit.bf.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by super on 26/09/2016.
 */
public class Player implements Comparable<Player> {
    private int id;
    private String name;
    private boolean ready;
    private Date lastGamePlayed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public Date getLastGamePlayed() {
        return lastGamePlayed;
    }

    public void setLastGamePlayed(Date lastGamePlayed) {
        this.lastGamePlayed = lastGamePlayed;
    }

    public void updateLastGamePlayedIfNewerDate(Date lastGamePlayed) {
        if (lastGamePlayed.after(this.lastGamePlayed)) {
            this.lastGamePlayed = lastGamePlayed;
        }
    }

    public Player(String name, boolean ready) {
        this.name = name;
        this.ready = ready;
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTimeInMillis(0);
        lastGamePlayed = instance.getTime();
    }

    public Player(int id, String name, boolean ready) {
        this.id = id;
        this.name = name;
        this.ready = ready;
        Calendar instance = GregorianCalendar.getInstance();
        instance.setTimeInMillis(0);
        lastGamePlayed = instance.getTime();
    }

    @Override
    public int compareTo(Player o) {
        return getLastGamePlayed().compareTo(o.getLastGamePlayed());
    }

    public String toString() {
        return getName() + ", " + isReady() + ", " + getLastGamePlayed();
    }
}
