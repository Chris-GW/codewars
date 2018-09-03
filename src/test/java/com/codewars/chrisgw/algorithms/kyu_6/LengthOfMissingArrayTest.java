package com.codewars.chrisgw.algorithms.kyu_6;

import org.junit.Test;

import static com.codewars.chrisgw.algorithms.kyu_6.LengthOfMissingArray.getLengthOfMissingArray;
import static org.junit.Assert.assertEquals;


public class LengthOfMissingArrayTest {

    @Test
    public void BasicTests() {
        assertEquals(3, getLengthOfMissingArray(new Object[][] { //
                new Object[] { 1, 2 }, //
                new Object[] { 4, 5, 1, 1 }, //
                new Object[] { 1 }, //
                new Object[] { 5, 6, 7, 8, 9 } //
        }));
        assertEquals(2, getLengthOfMissingArray(new Object[][] { //
                new Object[] { 5, 2, 9 }, //
                new Object[] { 4, 5, 1, 1 }, //
                new Object[] { 1 }, //
                new Object[] { 5, 6, 7, 8, 9 } //
        }));
        assertEquals(2, getLengthOfMissingArray(new Object[][] { //
                new Object[] { null }, //
                new Object[] { null, null, null } //
        }));
        assertEquals(5, getLengthOfMissingArray(new Object[][] { //
                new Object[] { 'a', 'a', 'a' }, //
                new Object[] { 'a', 'a' }, //
                new Object[] { 'a', 'a', 'a', 'a' }, //
                new Object[] { 'a' }, //
                new Object[] { 'a', 'a', 'a', 'a', 'a', 'a' } //
        }));

        assertEquals(0, getLengthOfMissingArray(new Object[][] {}));
    }

    @Test
    public void without1LengthArray() {
        assertEquals(3, getLengthOfMissingArray(new Object[][] { //
                new Object[] { 0, 4, 0, 1, 1 }, //
                new Object[] { 1, 4 }, //
                new Object[] { 1, 0, 1, 0 }, //
                new Object[] { 4, 3, 2, 4, 4, 0 }, //
                new Object[] { 2, 0, 4, 4, 3, 3, 0, 1 }, //
                new Object[] { 1, 1, 1, 1, 4, 4, 3, 1, 3 }, //
                new Object[] { 2, 0, 1, 2, 1, 1, 1 } //
        }));
    }


    @Test
    public void with0LengthArray() {
        assertEquals(0, getLengthOfMissingArray(new Object[][] { //
                new Object[] { 1 }, //
                new Object[] {  }, //
                new Object[] { 4, 4, 0 }, //
                new Object[] { 0, 4, 4, 3, 3, 0, 1 }, //
                new Object[] { 1, 0 }, //
                new Object[] { 1, 2, 1, 1, 1 } //
        }));
    }

    @Test
    public void withNullLengthArray() {
        assertEquals(0, getLengthOfMissingArray(new Object[][] { //
                new Object[] { 1 }, //
                new Object[] { 4, 4, 0 }, //
                null, //
                new Object[] { 1, 0 }, //
                new Object[] { 1, 2, 1, 1, 1 } //
        }));
    }

}
