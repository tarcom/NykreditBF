package dk.skov.nykredit.bf;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Created by aogj on 15-08-2016.
 */
public class TestSelenium {

    @Test
    public void testOne() throws InterruptedException {

        System.out.println("hejsa med digsa");

        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/index.jsp");

        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);
        doTestCycle(driver);

        System.out.println("bye");
        driver.quit();
    }

    private void doTestCycle(WebDriver driver) {
        System.out.println("doTestCycle enter");
        driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/form[1]/input[2]")).click(); //new random game

        ArrayList<String> winners = new ArrayList<>();
        ArrayList<String> loosers = new ArrayList<>();

        HashMap<String, Integer> initPointsMap = getInitPointMap(driver, winners, loosers);

        boolean isIntense = isIntense(driver);

        System.out.println("isIntense=" + isIntense);

        System.out.println("initPointsMap=" + initPointsMap);

        driver.findElement(By.name("t1RedWinner")).click(); //claim red winner

        winners.clear();
        loosers.clear();
        HashMap<String, Integer> postPointsMap = getInitPointMap(driver, winners, loosers);
        System.out.println("postPointsMap=" + postPointsMap);

        int pointsAtStake = isIntense ? 2 : 1;

        validateResult(winners, loosers, initPointsMap, postPointsMap, pointsAtStake);

        System.out.println("doTestCycle out.");
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
        try {
            String intense = driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/form[2]/table/tbody/tr[1]/td/b/font")).getText();
            return intense.startsWith("INTENSE!!!");
        }catch (NoSuchElementException e) {
            return false;
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
