package de.htwg.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author Adrian Wenger
 * 
 */
public class CardTest {
    
    @Before
    public void setUp() {
        Card card = new Card(Suit.CLUBS, 10);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNumber method, of class Card.
     */
    @Test
    public void testGetNumber() {
        int expResult = 2;
        int result = new Card(Suit.HEARTS, 2).getNumber();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetSuit() {
        Suit expResult = Suit.HEARTS;
        Suit result = new Card(Suit.HEARTS, 2).getSuit();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TwoOfDIAMONDS");
        
        StringBuilder sbResult = new StringBuilder();
        sbResult.append(new Card(Suit.DIAMONDS, 2).toString());
                
        assertEquals(sbResult.toString(), sb.toString());
    }
    
}