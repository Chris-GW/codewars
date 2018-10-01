package com.codewars.chrisgw.games.kyu_4;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;
import java.util.TreeMap;


/**
 * <h1>Task</h1>
 * <p>
 * Create a top-down movement system that would feel highly responsive to the player. In your Update method you have to check for the keys that are currently being pressed, the keys correspond to the enum Direction shown below, based on which key is pressed or released your method should behave this way:
 * <p>
 * 1) When a key is first pressed, the player has to change his direction to that of the current key, without moving
 * <p>
 * 2) If the key is still being pressed during the next Update, the player will move towards his current direction using these vectors: (Up = { 0, +1 } , Down = { 0, -1 }, Left = { -1, 0 }, Right = { +1, 0 })
 * <p>
 * 3) If a new key is pressed, it will gain precedence over the previous key and the player will act as per 1)
 * <p>
 * 4-A) If the current key (A) is released, then the precedence will go back to the previous key (B) (or the one before it, if (B) is not pressed anymore, and so on), then the player will behave as per 1).
 * <p>
 * 4-B) If the current key is released, and no other keys are being pressed, the player will stand still
 * <p>
 * 5) If all keys are released at once, the player will not move nor change direction
 * <p>
 * 6) If multiple keys are pressed at once, the order of precedence will be the following { Up, Down, Left, Right }
 *
 *
 * <h1>Examples</h1>
 * <p>
 * (n = pressed key, [n] = current key, p() = press, r() = release, (8,2,4,6 = up, down, left, right)):
 * <p>
 * [] , p(8) -> [8] , p(4,6) -> 86[4] , r(6) -> 8[4] , r(4) -> [8] , r(8) -> []
 * <p>
 * [] , p(2486) -> 642[8] , r(2,8) -> 6[4] , r(4,6) -> []
 * <p>
 * <p>
 * This is what you'll need to use in your code (NB: the tile coordinates cannot be changed, you'll need to assign a new Tile each time the player moves):
 */
public class TopDownMovementSystem {

    private int direction;
    private Tile position;

    public TopDownMovementSystem(int x, int y) {
        position = new Tile(x, y);
        direction = 8;
    }

    public Tile getPosition() { return position; }

    public int getDirection() { return direction; }

    public void update() {
        // Your code here! (at least... ;) )
        // Reminder: access to the states of the keys via: Input.getState(int d)
    }


}


class Tile {

    private int x = 0;
    private int y = 0;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", x, y)// formated as: "(x,y)"
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Tile)) {
            return false;
        }

        Tile tile = (Tile) o;

        return new EqualsBuilder().append(x, tile.x).append(y, tile.y).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37).append(x).append(y).toHashCode();
    }

}


class Input {

    private static Map<Integer, Boolean> directionPressed = new TreeMap<>();

    public static boolean getState(int direction) {
        return directionPressed.getOrDefault(direction, false);
    }

    public static void release(int k) {
        directionPressed.put(k, false);
    }

    public static void press(int k) {
        directionPressed.put(k, true);
    }

    public static void clear() {
        directionPressed.clear();
    }

}
