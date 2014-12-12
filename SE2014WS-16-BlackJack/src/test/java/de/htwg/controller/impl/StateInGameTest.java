package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.controller.IGameState;
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

    private IBlackJackController controller;

    private StateInGame state;

    @Before
    public final void setUp() {
        this.controller = new BlackJackController();
       // state = new StateInGame(controller);
        //this.controller.setCurrentState(state);
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

    }
}
