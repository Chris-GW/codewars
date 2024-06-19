package com.codewars.chrisgw.reference.kyu_4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HowManyNumbersIiiTest {

    @Test
    public void exampleTests() {
        assertEquals(Arrays.asList(8L, 118L, 334L), HowManyNumbersIii.findAll(10, 3));
        assertEquals(Arrays.asList(1L, 999L, 999L), HowManyNumbersIii.findAll(27, 3));
        assertEquals(new ArrayList<Long>(), HowManyNumbersIii.findAll(84, 4));
        assertEquals(Arrays.asList(123L, 116999L, 566666L), HowManyNumbersIii.findAll(35, 6));
    }

}
