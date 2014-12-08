package de.htwg.model.impl;

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
     *
     */
    @Test
    public final void testGetSuit() {
        String expResult = "TwoOfHEARTS";
        String result = new Card(Suit.HEARTS, TWO).toString();
        assert (expResult.equals(result));
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public final void testToString() {
        StringBuilder sbExpResult = new StringBuilder();
        sbExpResult.append("TwoOfDIAMONDS");

        StringBuilder sbResult = new StringBuilder();
        sbResult.append(new Card(Suit.DIAMONDS, TWO).toString());

        assertEquals(sbResult.toString(), sbExpResult.toString());
    }
}
