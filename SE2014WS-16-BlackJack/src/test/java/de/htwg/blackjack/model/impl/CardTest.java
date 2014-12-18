package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.model.Suit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 *
 * @author Adrian Wenger
 *
 */
public class CardTest {
    /**
     *
     */
    private static final int TEN = 10;
    /**
     *
     */
    private static final int TWO = 2;
    /**
     *
     */
    private Card card;

    /**
     *
     */
    @Before
    public final void setUp() {
        card = new Card(Suit.CLUBS, TEN);
    }

    /**
     * Test of getNumber method, of class Card.
     */
    @Test
    public final void testGetNumber() {
        int expResult = TWO;
        int result = new Card(Suit.HEARTS, TWO).getNumber();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public final void testToString() {
        ICard card = new Card(Suit.DIAMONDS, TWO);
        
        StringBuilder sbExpResult = new StringBuilder();
        sbExpResult.append("TwoOfDIAMONDS");

        StringBuilder sbResult = new StringBuilder();
        sbResult.append(card.toString());

        assertEquals(sbResult.toString(), sbExpResult.toString());
    }
    
    @Test
    public final void testGetSuit() {
        ICard card = new Card(Suit.CLUBS, TEN);
        Suit expResult = Suit.CLUBS;
        Suit result = card.getSuit();
        assertEquals(expResult, result);
    }
    
}
