package com.codewars.chrisgw.algorithms.kyu_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;


/**
 * A famous casino is suddenly faced with a sharp decline of their revenues. They decide to offer Texas hold'em also online. Can you help them by writing an algorithm that can rank poker hands?
 *
 *
 * <b style='font-size:16px'>Task:</b>
 *
 * <ul>
 *
 * <li>Create a poker hand that has a constructor that accepts a string containing 5 cards:</li>
 *
 * <li>Apply the <a href="https://en.wikipedia.org/wiki/Texas_hold_%27em">Texas Hold'em</a> rules for ranking the cards. </li>
 * <li>There is no ranking for the suits.</li>
 * <li>An ace can either rank high or rank low in a straight or straight flush. Example of a straight with a low ace:<br>
 * `"5C 4D 3C 2S AS"`</li>
 * </ul>
 * </li>
 *
 *
 * </ul>
 * <br>
 */
public class SortablePokerHands {


}


class PokerHand implements Comparable<PokerHand> {

    public static final int INITIAL_CAPACITY = 5;

    private List<PokerCard> cards = new ArrayList<>(INITIAL_CAPACITY);


    public PokerHand(String hand) {
        for (String cardStr : hand.split("\\s+")) {
            int cardValue = getCardValue(hand.charAt(0));
            CardSuit cardSuite = getCardSuite(hand.charAt(1));
            cards.add(new PokerCard(cardValue, cardSuite));
        }
    }

    private int getCardValue(char cardValueChar) {
        switch (cardValueChar) {
        case 'T': /* T(en) */
            return 10;
        case 'J': /* J(ack) */
            return 11;
        case 'Q': /* Q(ueen) */
            return 12;
        case 'K': /* K(ing) */
            return 13;
        case 'A': /* A(ce) */
            return 14;
        default:
            return cardValueChar - '0';
        }
    }

    private CardSuit getCardSuite(char cardSuiteChar) {
        return Arrays.stream(CardSuit.values())
                .filter(cardSuit -> cardSuit.name().charAt(0) == cardSuiteChar)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public int compareTo(PokerHand o) {
        return 0;
    }

}


class PokerCard {

    int cardValue;
    CardSuit cardSuit;


    public PokerCard(int cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

}


enum TexasHoldemHand {

    ROYAL_FLUSH(isRoalyFlushPokerHand()), HIGHCARD(null);

    private static Function<PokerHand, Boolean> isRoalyFlushPokerHand() {
        return pokerHand -> {
            return false;
        };
    }


    private Function<PokerHand, Boolean> isPokerHand;


    TexasHoldemHand(Function<PokerHand, Boolean> isPokerHand) {
        this.isPokerHand = isPokerHand;
    }

}


enum CardSuit {

    SPADES, HEARTS, DIAMONDS, CLUBS; /* in cardSuit value order */

}