package com.codewars.chrisgw.games.kyu_4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TopDownMovementSystemTest {

    private static final Map<Integer, String> keysDir = new HashMap<>();

    static {
        keysDir.put(8, "Up");
        keysDir.put(2, "Down");
        keysDir.put(4, "Left");
        keysDir.put(6, "Right");
    }

    @Test
    @Disabled
    public void sampleTests() {
        TopDownMovementSystem player = new TopDownMovementSystem(0, 0);

        pressOrReleaseAndDisplay("Press", 2);
        doTest(player, 2, 0, 0);
        doTest(player, 2, 0, -1);
        doTest(player, 2, 0, -2);

        pressOrReleaseAndDisplay("Press", 4);
        pressOrReleaseAndDisplay("Press", 6);

        doTest(player, 4, 0, -2);
        doTest(player, 4, -1, -2);

        pressOrReleaseAndDisplay("Release", 4);
        doTest(player, 6, -1, -2);

        pressOrReleaseAndDisplay("Release", 6);
        doTest(player, 2, -1, -2);
        doTest(player, 2, -1, -3);

        pressOrReleaseAndDisplay("Release", 2);
        doTest(player, 2, -1, -3);

        Input.clear();
    }

    private void doTest(TopDownMovementSystem player, int dir, int x, int y) {
        player.update();
        assertEquals(dir, player.getDirection(), "Wrong direction after update;");
        assertEquals(new Tile(x, y), player.getPosition(), "Wrong position after update;");
    }

    private void pressOrReleaseAndDisplay(String action, int k) {
        if (action.equals("Release")) {
            Input.release(k);
        } else if (action.equals("Press")) {
            Input.press(k);
        } else {
            throw new RuntimeException("The tests are flawed...");
        }
        System.out.printf("%s %s%n", action, keysDir.get(k));
    }

}
