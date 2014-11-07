package de.htwg.model;

import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adi
 */
public class PlayerTest {
    
    private Player player;
    
    @Before
    public void setUp() {
        player = new Player("John");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetName() {
        String expResult = "John";
        String result = player.getName();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testgetPlayerVal() {
        player.setPlayerVal(50);
        int expResult =  50;
        int result = player.getPlayerVal();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testgetUserCards() {
        List<Card> userCards = new LinkedList<>();
        userCards.add(new Card(Suit.CLUBS,5));
        
        player.setUserCards(userCards);
        
        Suit resultSuit = null;
        int resultNumber = 0;
        
        for(Card l : player.getUserCards()) {
            resultSuit = l.getSuit();
            resultNumber = l.getNumber();
        }

        int expResultNumber = 5;
        assertEquals(expResultNumber, resultNumber);
        Suit expResultSuit = Suit.CLUBS;        
        assertEquals(expResultSuit, resultSuit);
    }
    
    
    
}
