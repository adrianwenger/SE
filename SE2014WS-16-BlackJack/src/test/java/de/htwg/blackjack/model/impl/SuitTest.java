package de.htwg.blackjack.model.impl;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Adi
 */
public class SuitTest {

    /**
     *
     */
    private Suit enumTest;

    /**
     *
     */
    @Before
    public final void setUp() {
        enumTest = Suit.CLUBS;
    }

    /**
     * Test of valueOf method, of class Suit.
     */
    @Test
    public final void testValueOf() {
        Suit expResult = Suit.CLUBS;
        Suit result = enumTest.CLUBS;
        assertEquals(expResult, result);
    }

}
