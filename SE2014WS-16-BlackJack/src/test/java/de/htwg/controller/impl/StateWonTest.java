package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.model.impl.Card;
import de.htwg.model.impl.Suit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Adrian Wenger
 */
public class StateWonTest {

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
        // set State to StateWon
        this.controller.setCurrentState(new StateWon(controller));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // add Cards to Player and Dealer
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        // no BlackJAck Dealer > 21 && Player < 21
        if (!this.controller.hasBlackJack(this.controller.getPlayer())) {
            String result = this.controller.getStatusLine();
            String expResult = "";
            assertEquals(expResult, result);
        }
        
        this.controller.getPlayer().add(new Card(Suit.SPADES, 3));
        // Player with BlackJack
        if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            boolean result = this.controller.getCurrentState() instanceof StateWon;
            boolean expResult = true;
            assertEquals(expResult, result);
        }

    }
}
