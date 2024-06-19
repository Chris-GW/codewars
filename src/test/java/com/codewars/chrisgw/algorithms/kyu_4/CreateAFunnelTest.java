package com.codewars.chrisgw.algorithms.kyu_4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * <h2>Create a funnel</h2>
 * <a href="https://www.codewars.com/kata/585b373ce08bae41b800006e">https://www.codewars.com/kata/585b373ce08bae41b800006e</a>
 */
class CreateAFunnelTest {

    String SETUP = "\n\n-----------------------------------\n";
    String TEARDOWN = "\n";

    @Test
    public void basicTests() {

        CreateAFunnel funnel = new CreateAFunnel();
        String now = "";
        Character dropped = null;

        System.out.println(SETUP + "Creating an empty funnel" + TEARDOWN);

        now = "\\         /\n" +
                " \\       /\n" +
                "  \\     /\n" +
                "   \\   /\n" +
                "    \\ /";
        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());


        System.out.println(SETUP + "Testing the fill method" + TEARDOWN);

        funnel.fill('1', '2', '3');
        now = ("\\         /\n" +
                " \\       /\n" +
                "  \\     /\n" +
                "   \\2 3/\n" +
                "    \\1/");

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());


        System.out.println(SETUP + "Testing the drip method" + TEARDOWN);

        dropped = funnel.drip();
        now = ("\\         /\n" +
                " \\       /\n" +
                "  \\     /\n" +
                "   \\  3/\n" +
                "    \\2/");

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());
        assertEquals(Character.valueOf('1'), dropped, "The drip method should return the value at the bottom of the funnel");


        System.out.println(SETUP + "Testing the fill functions with gaps and excess" + TEARDOWN);

        funnel.fill('4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H');
        now = ("\\C D E F G/\n" +
                " \\8 9 A B/\n" +
                "  \\5 6 7/\n" +
                "   \\4 3/\n" +
                "    \\2/");

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());
    }


    @Test
    public void exampleTests() {
        CreateAFunnel funnel = new CreateAFunnel();
        String now = "";
        Character dropped = null;

        funnel.fill('1', '2', '3', '4', '5', '6', '7', '8', '9');
        now = ("\\         /\n" +
                " \\7 8 9  /\n" +
                "  \\4 5 6/\n" +
                "   \\2 3/\n" +
                "    \\1/");

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());


        System.out.println(SETUP + "Testing the drip method" + TEARDOWN);

        dropped = funnel.drip();
        now = ("\\         /\n" +
                " \\  8 9  /\n" +
                "  \\7 5 6/\n" +
                "   \\4 3/\n" +
                "    \\2/");

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());
        assertEquals(Character.valueOf('1'), dropped, "The drip method should return the value at the bottom of the funnel");


        System.out.println(SETUP + "Testing the fill functions with gaps and excess" + TEARDOWN);

        dropped = funnel.drip();
        now = "\\         /\n" +
                " \\    9  /\n" +
                "  \\7 8 6/\n" +
                "   \\5 3/\n" +
                "    \\4/";

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());
        assertEquals(Character.valueOf('2'), dropped, "The drip method should return the value at the bottom of the funnel");


        dropped = funnel.drip();
        now = "\\         /\n" +
                " \\       /\n" +
                "  \\7 9 6/\n" +
                "   \\8 3/\n" +
                "    \\5/";

        System.out.println("Funnel should be now:\n" + now);
        assertEquals(now, funnel.toString());
        assertEquals(Character.valueOf('4'), dropped, "The drip method should return the value at the bottom of the funnel");
    }

}
