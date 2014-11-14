package de.htwg.model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adi
 */
public class SuitTest {
    
    private Suit enumTest;
    
    @Before
    public void setUp() {
        enumTest = Suit.CLUBS;
    }

    /**
     * Test of valueOf method, of class Suit.
     */
    @Test
    public void testValueOf() {
        Suit expResult = Suit.CLUBS;
        Suit result = enumTest.CLUBS;
        assertEquals(expResult, result);
    }
    
}
