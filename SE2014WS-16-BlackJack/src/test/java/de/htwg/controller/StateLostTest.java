package de.htwg.controller;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adrian Wenger
 */
public class StateLostTest {

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

        // Player Won in concerning BlackJack
        if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
            assert (this.controller.getCurrentState() instanceof StateBlackJack);
        } else {
            this.controller.setCurrentState(new StateWon(controller));
            assert (this.controller.getCurrentState() instanceof StateWon);
        }
    }

}
