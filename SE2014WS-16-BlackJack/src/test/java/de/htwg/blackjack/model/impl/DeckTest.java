package de.htwg.blackjack.model.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author Adi
 */
public class DeckTest {


    private static final int TWO = 2;
    private static final int ONE = 1;
    private static final int SIZEOF2DECKS = 104;
    private static final int SIZEOF1DECK = 52;

    Deck deck;
    Deck deckDefault;

    /**
     *
     */
    @Before
    public final void setUp() {
        deck = new Deck();
        deck.setNumOfDecks(TWO);
        deckDefault = new Deck();
        deckDefault.setNumOfDecks(ONE);
    }

    /**
     * Test if a Deck[] with size of 104 was getting created.
     */
    @Test
    public final void testContDeck() {
        int expResult = SIZEOF2DECKS;
        int result = deck.getDeck().length;
        assertEquals(expResult, result);
    }

    /**
     * Test if a Deck[] with size of 52 was getting created.
     */
    @Test
    public final void testDefConstDeck() {
        int expResult = SIZEOF1DECK;
        int result = deckDefault.getDeck().length;
        assertEquals(expResult, result);
    }

    /**
     * Test getNumOfCards()
     */
    @Test
    public final void testGetNumOfCards() {
        Deck cards = new Deck();
        cards.setNumOfDecks(1);

        int expResult = 52;
        int result = cards.getNumOfCards();

        assertEquals(expResult, result);
    }

    /**
     * Test getNumOfDecks()
     */
    @Test
    public final void testGetNumOfDecks() {
        Deck cards = new Deck();
        cards.setNumOfDecks(1);

        int expResult = 1;
        int result = cards.getNumOfDecks();

        assertEquals(expResult, result);
    }

}
