package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Adrian Wenger
 */
public class StateBlackJackTest {

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

        this.controller.setCurrentState(new StateBlackJack(controller));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
    }

}
