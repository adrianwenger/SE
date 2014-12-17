package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.controller.impl.StateBlackJack;
import de.htwg.blackjack.controller.IBlackJackController;
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

        this.controller.setCurrentState(new StateBlackJack(controller, this.controller.getCalcController()));
    }
}
