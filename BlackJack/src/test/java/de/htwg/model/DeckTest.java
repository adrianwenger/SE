package de.htwg.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

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

    @Before
    public void setUp() {
        deck = new Deck(TWO);
        deckDefault = new Deck();

    }

    /**
     * Test if a Deck[] with size of 104 was getting created
     */
    @Test
    public void testContDeck() {
        int expResult = SIZEOF2DECKS;
        int result = deck.getDeck().length;
        assertEquals(expResult, result);
    }

    /**
     * Test if a Deck[] with size of 52 was getting created
     */
    @Test
    public void testDefConstDeck() {
        int expResult = SIZEOF1DECK;
        int result = deckDefault.getDeck().length;
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumCards method, of class Deck.
     */
    @Test
    public void testGetNumCards() {

        int expResult = SIZEOF2DECKS;
        int result = deck.getNumCards();
        assertEquals(expResult, result);

        expResult = SIZEOF1DECK;
        result = deckDefault.getNumCards();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumDeck method, of class Deck.
     */
    @Test
    public void testGetNumDeck() {
        int expResult = TWO;
        int result = deck.getNumDeck();
        assertEquals(expResult, result);

        expResult = ONE;
        result = deckDefault.getNumDeck();
        assertEquals(expResult, result);
    }
}
