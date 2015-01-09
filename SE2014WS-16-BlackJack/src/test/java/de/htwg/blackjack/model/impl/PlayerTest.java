package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
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
    private Player player;
    /**
     *
     */
    @Before
    public void setUp() {
        player = new Player();
        player.setName("John");
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
        Deck deckMax = new Deck();
        deckMax.setNumOfDecks(1);
        ICard cardMax = null;
        for (int i = 0; i < TEN; i++) {
            cardMax = deckMax.dealCard();
            player.add(cardMax);
        }
        boolean expResult = false;
        boolean result =  player.add(cardMax);
        assertEquals(expResult, result);
    }

    /**
     *
     */
    @Test
    public void testgetValue() {
        int cardVal = new Card(Suit.HEARTS, 2).getNumber();
        Card test = new Card(Suit.HEARTS, 2);
        player.add(test);
        int expResult = cardVal;
        int result = player.getValue();
        assertEquals(expResult, result);

        test = new Card(Suit.CLUBS, 11);
        cardVal = test.getNumber();
        player.add(test);
        expResult = 12;
        result = player.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of printPlayersHand method, of class Player.
     */
    @Test
    public void testPrintPlayersHand() {
        Player test = new Player();
        test.setName("test");
        test.add(new Card(Suit.CLUBS, FIVE));
        String expResult = "FiveOfCLUBS Value: 5";
        String result = test.printPlayersHand();
        assertEquals(expResult, result);

    }
    
    @Test
    public void testSetPlayerVal(){
        player.add(new Card(Suit.CLUBS, FIVE));
        //player.setPlayerVal(FIVE);
        int expResult = 5;
        int result = player.getValue();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetRoundStake(){
        player.setRoundStake(5);
        double expResult = 5;
        double result = player.getRoundStake();
        assertEquals(expResult, result, 0.);
    }
    
    @Test
      public void testDoubleRoundStake() {
          player.setRoundStake(5);
          player.doubleRoundStake();
          double expResult = 10;
          double result = player.getRoundStake();
          assertEquals(expResult, result, 0.);
      }

     @Test
    public void testClearHand(){
        player.add(new Card(Suit.CLUBS, 5));
        player.clearHand();
        int expResult = 0;
        int result = player.getValue();
        assertEquals(expResult, result);
        
    }
}
