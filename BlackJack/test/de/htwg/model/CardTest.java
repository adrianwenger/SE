package de.htwg.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian Wenger
 * 22222
 */
public class CardTest {
    
    @Before
    public void setUp() {
        Card card = new Card(Suit.Clubs, 10);
        
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
        int result = new Card(Suit.Hearts, 2).getNumber();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetSuit() {
        Suit expResult = Suit.Hearts;
        Suit result = new Card(Suit.Hearts, 2).getSuit();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Card.
     */
    @Test
    public void testToString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TwoOfDiamonds");
        
        StringBuilder sbResult = new StringBuilder();
        sbResult.append(new Card(Suit.Diamonds, 2).toString());
                
        assertEquals(sbResult.toString(), sb.toString());
    }
    
}
