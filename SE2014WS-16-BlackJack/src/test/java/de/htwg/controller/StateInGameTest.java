package de.htwg.controller;

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
    private static final int BLACKJACK = 21;

    private final IBlackJackController controller = new BlackJackController();

    private final StateInGame state = new StateInGame(controller);

    @Before
    public final void setUp() {
        this.controller.setCurrentState(state);
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // Create Player
        this.controller.setPlayer("Adrian");
        // Create Dealer
        this.controller.setDealer();
        // Create Deck
        this.controller.setDeck(1);
        // Deal first 2 Cards from Deck and add them to Player and Dealer
        this.controller.getPlayer().add(this.controller.getDeck().dealCard());
        this.controller.getPlayer().add(this.controller.getDeck().dealCard());
        this.controller.getDealer().add(this.controller.getDeck().dealCard());
        this.controller.getDealer().add(this.controller.getDeck().dealCard());

        IGameState result;
        IGameState expResult;

        if ((this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() > BLACKJACK)) {
            result = this.controller.getCurrentState();
            expResult = new StateWon(controller);
            assertEquals(expResult, result);
            // game will move on (both < 21)
        } else if (this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() < BLACKJACK) {
            result = this.controller.getCurrentState();
            expResult = state;
            assertEquals(expResult, result);
        } else {
            // Player lost BlackJack reached
            result = this.controller.getCurrentState();
            expResult = new StateLost(controller);
            assertEquals(expResult, result);
        }
    }
}
