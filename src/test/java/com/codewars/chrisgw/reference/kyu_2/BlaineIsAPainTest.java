package com.codewars.chrisgw.reference.kyu_2;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BlaineIsAPainTest {


    @Test
    @Disabled
    public void example() {
        String track = "" //
                + "                                /------------\\             \n" //
                + "/-------------\\                /             |             \n" //
                + "|             |               /              S             \n" //
                + "|             |              /               |             \n" //
                + "|        /----+--------------+------\\        |\n" //
                + "\\       /     |              |      |        |             \n" //
                + " \\      |     \\              |      |        |             \n" //
                + " |      |      \\-------------+------+--------+---\\         \n" //
                + " |      |                    |      |        |   |         \n" //
                + " \\------+--------------------+------/        /   |         \n" //
                + "        |                    |              /    |         \n" //
                + "        \\------S-------------+-------------/     |         \n" //
                + "                             |                   |         \n" //
                + "/-------------\\              |                   |         \n" //
                + "|             |              |             /-----+----\\    \n" //
                + "|             |              |             |     |     \\   \n" //
                + "\\-------------+--------------+-----S-------+-----/      \\  \n" //
                + "              |              |             |             \\ \n" //
                + "              |              |             |             | \n" //
                + "              |              \\-------------+-------------/ \n" //
                + "              |                            |               \n" //
                + "              \\----------------------------/               "; //
        assertEquals(516, BlaineIsAPain.trainCrash(track, "Aaaa", 147, "Bbbbbbbbbbb", 288, 1000));
    }


    @Test
    public void multipleExpress() {
        String track = "" //
                + "/-----------------\\\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "\\---------S-------/"; // //
        int actual = BlaineIsAPain.trainCrash(track, "xX", 10, "xxxxxX", 30, 200);
        assertEquals(-1, actual);
    }


    @Test
    public void crashBeforeStarted_1() {
        String track = "" //
                + "/-----------------\\\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "\\-----------------/"; //
        int actual = BlaineIsAPain.trainCrash(track, "oO", 10, "oO", 10, 100);
        assertEquals(0, actual);
    }


    @Test
    public void crashBeforeStarted_2() {
        String track = ""//
                + "/----\\     /----\\ \n"//
                + "|     \\   /     | \n"//
                + "|      \\ /      | \n"//
                + "|       S       | \n"//
                + "|      / \\      | \n"//
                + "|     /   \\     | \n" +//
                "\\----/     \\----/";
        int actual = BlaineIsAPain.trainCrash(track, "Eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", 7, "Xxxx", 0, 100);
        assertEquals(0, actual);
    }


    @Test
    public void letters() {
        String track = "" //
                + "/-----------------\\\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "|                 |\n" //
                + "\\---------S-------/"; //
        int actual = BlaineIsAPain.trainCrash(track, "xX", 10, "sssssS", 30, 200);
        assertEquals(108, actual);
    }

    @Test
    public void noCrash0Tricky() {
        String track = "" //
                + "/------\\               /--\\  \n" //
                + "|      |               |  |  \n" //
                + "|      \\---------------/  |  \n"  //
                + "\\------\\               /--/  \n" //
                + "       |               |     \n" //
                + "       \\---------------/     "; //
        int actual = BlaineIsAPain.trainCrash(track, "aaaA", 15, "bbbB", 5, 500);
        assertEquals(-1, actual);
    }

    @Test
    public void crashMisc() {
        String track = "" //
                + "/---\\ \n" //
                + "|   |  \n" //
                + "\\--\\| \n" //
                + "   ||  \n" //
                + "   |\\------\\  \n" //
                + "   |/----\\ |   \n" //
                + "   ||    | |    \n" //
                + "/--/|    | |    \n" //
                + "|   |    | |    \n" //
                + "\\---/    \\-/"; // //
        int actual = BlaineIsAPain.trainCrash(track, "Eee", 33, "aaA", 2, 100);
        assertEquals(16, actual);
    }

}
