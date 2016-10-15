package dk.skov.nykredit.bf;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by aogj on 15-08-2016.
 */
public class TestSelenium {

    //I will disable tests, as I do not want maven to execute the selenium test on every build etc.
    // ALSO: running maven does NOT start application server jetty, and the test will fail.
    // currently, you will have to start jetty/application server youreself, before running the test.
    //@Test
    public void testSite() throws InterruptedException {

        System.out.println("hejsa med digsa");

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/index.jsp");

        for (int i = 0 ; i < 15 ; i++) {
            doTestCycle(driver);
        }

        System.out.println("bye");
        driver.quit();
    }

    private void doTestCycle(WebDriver driver) {
        System.out.println();
        System.out.println("doTestCycle enter");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/form[1]/input[2]")).click(); //new random game

        ArrayList<String> winners = new ArrayList<>();
        ArrayList<String> loosers = new ArrayList<>();

        HashMap<String, Integer> initPointsMap = getInitPointMap(driver, winners, loosers);
        boolean isIntense = isIntense(driver);

        System.out.println("initPointsMap=" + initPointsMap + ", isIntense=" + isIntense + ", winners=" + winners + ", loosers=" + loosers);
        validateIntenseGame(isIntense, initPointsMap, winners, loosers, driver);

        driver.findElement(By.name("t1RedWinner")).click(); //claim red winner


        winners.clear();
        loosers.clear();
        HashMap<String, Integer> postPointsMap = getInitPointMap(driver, winners, loosers);
        System.out.println("postPointsMap=" + postPointsMap + ", isIntense=" + isIntense + ", winners=" + winners + ", loosers=" + loosers);

        int pointsAtStake = getPointsAtStake(winners, loosers, initPointsMap, isIntense);

        validateResult(winners, loosers, initPointsMap, postPointsMap, pointsAtStake);

        System.out.println("doTestCycle out.");
    }

    private int getPointsAtStake(ArrayList<String> winners, ArrayList<String> loosers, HashMap<String, Integer> initPointsMap, boolean isIntense) {
        int pointsAtStake = isIntense ? 2 : 1;

        //only 2 if winners are the non-favorites to win
        if (pointsAtStake == 2) {
            int winnerPoint = 0;
            for (String winner : winners) {
                winnerPoint += initPointsMap.get(winner);
            }

            int looserPoint = 0;
            for (String looser : loosers) {
                looserPoint += initPointsMap.get(looser);
            }

            if (winnerPoint > looserPoint) {
                //since winning team is already in favor, they only get 1 point.
                pointsAtStake = 1;
            }

        }
        return pointsAtStake;
    }

    private void validateIntenseGame(boolean isIntense, HashMap<String, Integer> initPointsMap, ArrayList<String> winners, ArrayList<String> loosers, WebDriver driver) {
        int winnerPoint = 0;
        for (String winner : winners) {
            winnerPoint += initPointsMap.get(winner);
        }

        int looserPoint = 0;
        for (String looser : loosers) {
            looserPoint += initPointsMap.get(looser);
        }

        int diff = Math.abs(winnerPoint - looserPoint);
        boolean isTrulyIntense = diff >= 5;

        System.out.format("winnerPoints=%d, loosersPoints=%d, diff=%d, isIntense=%s, isTrulyIntense=%s \n", winnerPoint, looserPoint, diff, isIntense, isTrulyIntense);

        if (isIntense != isTrulyIntense) {
            Assert.fail("This fight is not correctly in matters of intensity!!");
        }

        if (isIntense) {
            String intenseStr = getIntenseStr(driver);

            if (intenseStr.contains("red")) {
                Assert.assertTrue("intense str is wrong!! getIntenseStr(driver)=" + intenseStr, winnerPoint < looserPoint);
            } else if (intenseStr.contains("blue")) {
                Assert.assertTrue("intense str is wrong!! getIntenseStr(driver)=" + intenseStr, winnerPoint > looserPoint);
            } else {
                Assert.fail("intenseStr is not blue or red!!");
            }
        }
    }

    private void validateResult(ArrayList<String> winners, ArrayList<String> loosers, HashMap<String, Integer> initPointsMap, HashMap<String, Integer> postPointsMap, int pointsAtStake) {
        for (String winner : winners) {
            if (initPointsMap.get(winner) + pointsAtStake == postPointsMap.get(winner)) {
                System.out.println("correct " + winner + " from " + initPointsMap.get(winner) + " -> " + postPointsMap.get(winner));
            } else {
                Assert.fail("WTF!!! " + winner + " from " + initPointsMap.get(winner) + " -> " + postPointsMap.get(winner));
            }
        }


        for (String looser : loosers) {
            if (initPointsMap.get(looser) - pointsAtStake == postPointsMap.get(looser)) {
                System.out.println("correct " + looser + " from " + initPointsMap.get(looser) + " -> " + postPointsMap.get(looser));
            } else {
                Assert.fail("WTF!!! " + looser + " from " + initPointsMap.get(looser) + " -> " + postPointsMap.get(looser));
            }
        }
    }

    /**
     * quick n' dirty
     */
    private boolean isIntense(WebDriver driver) {
        String intense = getIntenseStr(driver);
        return intense.startsWith("INTENSE!!!");
    }

    /**
     * quick n' dirty
     */
    private String getIntenseStr(WebDriver driver) {
        try {
            return driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/form[2]/table/tbody/tr[1]/td/b/font")).getText();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    private HashMap<String, Integer> getInitPointMap(WebDriver driver, ArrayList<String> winners, ArrayList<String> loosers) {
        HashMap<String, Integer> initPointsMap = new HashMap<>();

        String playerT1R1 = driver.findElement(By.name("t1r1")).getAttribute("value");
        initPointsMap.put(playerT1R1, getPointsFromUser(driver, playerT1R1));
        winners.add(playerT1R1);

        String playerT1R2 = driver.findElement(By.name("t1r2")).getAttribute("value");
        initPointsMap.put(playerT1R2, getPointsFromUser(driver, playerT1R2));
        winners.add(playerT1R2);

        String playerT1B1 = driver.findElement(By.name("t1b1")).getAttribute("value");
        initPointsMap.put(playerT1B1, getPointsFromUser(driver, playerT1B1));
        loosers.add(playerT1B1);

        String playerT1B2 = driver.findElement(By.name("t1b2")).getAttribute("value");
        initPointsMap.put(playerT1B2, getPointsFromUser(driver, playerT1B2));
        loosers.add(playerT1B2);

        return initPointsMap;
    }

    private int getPointsFromUser(WebDriver driver, String user) {
        for (int i = 2; i < 30; i++) {
            String name = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[3]/td/table/tbody/tr/td[1]/table/tbody/tr[" + i + "]/td[2]")).getText();
            if (user.equalsIgnoreCase(name)) {
                String point = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[3]/td/table/tbody/tr/td[1]/table/tbody/tr[" + i + "]/td[3]")).getText();
                //System.out.println("found user " + user + " on pos i=" + i + ", points=" + point);
                return Integer.valueOf(point);
            }
        }
        throw new RuntimeException("getPointsFromUser: cannot find user!!! No points to get");
    }

}