package com.codewars.chrisgw.algorithms.kyu_4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SortablePokerHandsTest {

    @Test
    @Disabled
    public void pokerHandSortTest() {
        // Arrange
        List<PokerHand> expected = new ArrayList<>();
        expected.add(new PokerHand("KS AS TS QS JS"));
        expected.add(new PokerHand("2H 3H 4H 5H 6H"));
        expected.add(new PokerHand("AS AD AC AH JD"));
        expected.add(new PokerHand("JS JD JC JH 3D"));
        expected.add(new PokerHand("2S AH 2H AS AC"));
        expected.add(new PokerHand("AS 3S 4S 8S 2S"));
        expected.add(new PokerHand("2H 3H 5H 6H 7H"));
        expected.add(new PokerHand("2S 3H 4H 5S 6C"));
        expected.add(new PokerHand("2D AC 3H 4H 5S"));
        expected.add(new PokerHand("AH AC 5H 6H AS"));
        expected.add(new PokerHand("2S 2H 4H 5S 4C"));
        expected.add(new PokerHand("AH AC 5H 6H 7S"));
        expected.add(new PokerHand("AH AC 4H 6H 7S"));
        expected.add(new PokerHand("2S AH 4H 5S KC"));
        expected.add(new PokerHand("2S 3H 6H 7S 9C"));

        Random random = new Random();
        List<PokerHand> actual = createRandomOrderedList(random, expected);

        // Act
        Collections.sort(actual);

        // Assert
        Iterator<PokerHand> a = actual.iterator();
        for (PokerHand e : expected) {
            assertEquals(e, a.next());
        }
    }

    private List<PokerHand> createRandomOrderedList(Random random, List<PokerHand> expected) {
        List<PokerHand> actual = new ArrayList<>();
        for (PokerHand pokerHand : expected) {
            int j = random.nextInt(actual.size() + 1);
            actual.add(j, pokerHand);
        }
        return actual;
    }
    
}

