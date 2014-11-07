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
    
    public void testPlayerVal() {
        player.setPlayerVal(50);
        int expResult =  50;
        int result = player.getPlayerVal();
        assertEquals(expResult, result);
    }
    
    public void testUserCards() {
        List<Card> userCards = new LinkedList<>();
        userCards.add(new Card(Suit.CLUBS,5));
        
        player.setUsersCards(userCards);
        
        Suit resultSuit = null;
        int resultNumber = 0;
        
        for(Card l : player.getUsersCards()) {
            resultSuit = l.getSuit();
            resultNumber = l.getNumber();
        }

        int expResultNumber = 5;
        assertEquals(expResultNumber, resultNumber);
        Suit expResultSuit = Suit.CLUBS;        
        assertEquals(expResultSuit, resultSuit);
    }
    
    
    
}
