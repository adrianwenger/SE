package de.htwg.model.impl;

import de.htwg.model.ICard;
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
        Deck deckMax = new Deck(1);
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
        expResult = cardVal;
        result = player.getValue();
        //assertEquals(expResult, result);
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
}
