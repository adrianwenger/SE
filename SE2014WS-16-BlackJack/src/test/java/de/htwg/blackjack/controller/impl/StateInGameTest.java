package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.impl.StateInGame;
import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.controller.impl.StateWon;
import de.htwg.blackjack.controller.impl.StateLost;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.model.impl.Suit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Adrian Wenger
 */
public class StateInGameTest {

    /**
     * BlackJack Value 21.
     */

    private IBlackJackController controller;

    @Before
    public final void setUp() {
        this.controller = new BlackJackController();
        // Create Player
        this.controller.setPlayer("Test");
        // Create Dealer
        this.controller.setDealer();
        // Create Deck
        this.controller.setDeck(1);
        // set State to StateInGame
        this.controller.setCurrentState(new StateInGame(controller));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // test BlackJack Case
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));

        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        // Case Player and Dealer < 21)
        this.controller.setStatusLine("test");
        String result = this.controller.getStatusLine();
        String expResult = "";
        assertEquals(expResult, result);

        // Case Dealer BlackJack
        this.controller.getDealer().add(new Card(Suit.SPADES, 3));
        this.controller.getCurrentState().change();
        boolean result1 = this.controller.getCurrentState() instanceof StateLost;
        assert (result1);

        // Clears the hand to simulate case Dealer > 21 Player < 21
        this.controller.getPlayer().clearHand();
        this.controller.getDealer().clearHand();
        
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));

        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.setCurrentState(new StateInGame(controller));
        
        this.controller.getDealer().add(new Card(Suit.SPADES, 3));
        this.controller.getCurrentState().change();
        boolean result2 = this.controller.getCurrentState() instanceof StateWon;
        assert (result2);
    }
}
