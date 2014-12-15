package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.controller.impl.StateLost;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.model.impl.Suit;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adrian Wenger
 */
public class StateLostTest {

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
        // set State to StateLost
        this.controller.setCurrentState(new StateLost(controller));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // Add Cards to Dealer
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        // no BlackJack Case 
        if (!this.controller.hasBlackJack(this.controller.getDealer())) {
            String result = this.controller.getStatusLine();
            String expResult = "";
            assertEquals(expResult, result);
        }

        this.controller.getDealer().add(new Card(Suit.SPADES, 3));
        // BlackJack Case
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            boolean result = this.controller.getCurrentState() instanceof StateLost;
            boolean expResult = true;
            assertEquals(expResult, result);
        }

    }
}
