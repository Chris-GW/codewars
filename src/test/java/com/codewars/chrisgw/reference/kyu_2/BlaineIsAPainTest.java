package com.codewars.chrisgw.reference.kyu_2;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BlaineIsAPainTest {


    @Test
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
        assertEquals(-1, BlaineIsAPain.trainCrash(track, "xX", 10, "xxxxxX", 30, 200));
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
        assertEquals(0, BlaineIsAPain.trainCrash(track, "oO", 10, "oO", 10, 100));
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
        assertEquals(0, BlaineIsAPain.trainCrash(track, "Eeeeeeeeeeeeeeeeeeeeeeeeeeeeeee", 7, "Xxxx", 0, 100));
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
        assertEquals(108, BlaineIsAPain.trainCrash(track, "xX", 10, "sssssS", 30, 200));
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
        assertEquals(-1, BlaineIsAPain.trainCrash(track, "aaaA", 15, "bbbB", 5, 500));
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
        assertEquals(16, BlaineIsAPain.trainCrash(track, "Eee", 33, "aaA", 2, 100));
    }

}
