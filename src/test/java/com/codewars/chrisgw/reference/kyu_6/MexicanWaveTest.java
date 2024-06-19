package com.codewars.chrisgw.reference.kyu_6;


import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MexicanWaveTest {

    @Test
    public void basicTest1() {
        String[] result = new String[]{"Hello", "hEllo", "heLlo", "helLo", "hellO"};
        String[] actual = MexicanWave.wave("hello");
        assertArrayEquals(result, actual, "it should return '" + Arrays.toString(result) + "'");
    }

    @Test
    public void basicTest2() {
        String[] result = new String[]{"Codewars", "cOdewars", "coDewars", "codEwars", "codeWars", "codewArs", "codewaRs", "codewarS"};
        String[] actual = MexicanWave.wave("codewars");
        assertArrayEquals(result, actual, "it should return '" + Arrays.toString(result) + "'");
    }

    @Test
    public void basicTest3() {
        String[] result = new String[]{};
        String[] actual = MexicanWave.wave("");
        assertArrayEquals(result, actual, "it should return '" + Arrays.toString(result) + "'");
    }

    @Test
    public void basicTest4() {
        String[] result = new String[]{"Two words", "tWo words", "twO words", "two Words", "two wOrds", "two woRds", "two worDs", "two wordS"};
        String[] actual = MexicanWave.wave("two words");
        assertArrayEquals(result, actual, "it should return '" + Arrays.toString(result) + "'");
    }

    @Test
    public void basicTest5() {
        String[] result = new String[]{" Gap ", " gAp ", " gaP "};
        String[] actual = MexicanWave.wave(" gap ");
        assertArrayEquals(result, actual, "it should return '" + Arrays.toString(result) + "'");
    }

}
