package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Adrian Wenger, Philipp Schutltheiß
 */
public final class Card implements ICard {

    /**
     *
     */
    private static final int ONE = 1;
    /**
     * One of the four valid suits for this card.
     *
     */
    private final Suit suit;
    /**
     * The number of this card.
     */
    private final int number;
    /**
     * TreeMap card saves cards.
     */
    private final Map<Integer, String> cards = new TreeMap<Integer, String>();
    /**
     *
     */
    private int i = ONE;

    /**
     * Card constructor.
     *
     * @param aSuit Suit
     * @param aNumber Number
     */
    public Card(final Suit aSuit, final int aNumber) {
        this.suit = aSuit;
        this.number = aNumber;
        createCards();
    }

    /**
     * return the Number of the Card.
     *
     * @return nuber
     */
    @Override
    public int getNumber() {
        return number;
    }

    /**
     * return Card as String.
     *
     * @return Card with number and suit
     */
    @Override
    public String toString() {
        return cards.get(number) + "Of" + suit.toString();
    }

    /**
     * creates the Cards the game contains.
     */
    private void createCards() {
        String[] values = new String[]{"Ace", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King",
            "Ace"};
        for (String value : values) {
            insertCardValues(value);
        }
    }

    /**
     * fills the TreeMap cards with keys and values.
     *
     * @param num value of the card
     */
    private void insertCardValues(final String num) {
        cards.put(i, num);
        i++;
    }

    @Override
    public Suit getSuit() {
        return this.suit;
    }
}
