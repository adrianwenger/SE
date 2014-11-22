package de.htwg.model;

import java.util.LinkedList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adi
 */
public final class PlayerTest {
    /**
     *
     */
    private static final int FIVE = 5;
    /**
     *
     */
    private static final int ZERO = 0;
     /**
     *
     */
    private static final int TEN = 10;
     /**
     *
     */
    private static final int ACE = 11;
    /**
     *
     */
    private Player player;

    /**
     *
     */
    @Before
    public void setUp() {
        player = new Player("John");
    }

    /**
     *
     */
    @Test
    public void testGetName() {
        String expResult = "John";
        String result = player.getName();
        assertEquals(expResult, result);
    }

    /**
     *
     */
    @Test
    public void testAdd() {
        Card card = new Card(Suit.CLUBS, FIVE);
        boolean expResult = true;
        boolean result = player.add(card);
        assertEquals(expResult, result);
    }

    /**
     *
     */
    @Test
    public void testgetValue() {
        Deck deck = new Deck(1);
        Card cardTest = deck.dealCard();
        player.add(cardTest);
        int expResult = cardTest.getNumber();
        int result = player.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of printPlayersHand method, of class Player.
     */
    @Test
    public void testPrintPlayersHand() {
        Player test = new Player("test");
        test.add(new Card(Suit.CLUBS, FIVE));
        String expResult = "Cards: FiveOfCLUBS Value: 5";
        String result = test.printPlayersHand();
        assertEquals(expResult, result);
        
    }


    /**
     *
     */
    @Test
    public void testgetUserCards() {
        List<Card> userCards = new LinkedList<Card>();
        userCards.add(new Card(Suit.CLUBS, FIVE));

        Suit resultSuit = null;
        int resultNumber = ZERO;

        for (Card l : userCards) {
            resultSuit = l.getSuit();
            resultNumber = l.getNumber();
        }

        int expResultNumber = FIVE;
        assertEquals(expResultNumber, resultNumber);
        Suit expResultSuit = Suit.CLUBS;
        assertEquals(expResultSuit, resultSuit);
    }

    /**
     * Test of blackJack method, of class Player.
     */
    @Test
    public void testBlackJack() {
        player.add(new Card(Suit.CLUBS, ACE));
        player.add(new Card(Suit.CLUBS, ACE));
        boolean expResult = false;
        boolean result =  player.blackJack();
        assertEquals(expResult, result);
    }

}
