package de.htwg.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adi
 */
public class PlayerTest {
    
    private static final int FIFTY = 50;
    private static final int FIVE   = 5;
    private static final int ZERO = 0;
    private Player player;
    
    @Before
    public void setUp() {
        player = new Player("John");
    }
        
    @Test
    public void testGetName() {
        String expResult = "John";
        String result = player.getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testgetPlayerVal() {
        player.setPlayerVal(FIFTY);
        int expResult =  FIFTY;
        int result = player.getPlayerVal();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testgetUserCards() {
        List<Card> userCards = new LinkedList<Card>();
        userCards.add(new Card(Suit.CLUBS,FIVE));
        
        player.setUserCards(userCards);
        
        Suit resultSuit = null;
        int resultNumber = ZERO;
        
        for(Card l : player.getUserCards()) {
            resultSuit = l.getSuit();
            resultNumber = l.getNumber();
        }

        int expResultNumber = FIVE;
        assertEquals(expResultNumber, resultNumber);
        Suit expResultSuit = Suit.CLUBS;        
        assertEquals(expResultSuit, resultSuit);
    }
    
    
    
}
