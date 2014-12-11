/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.controller;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian Wenger
 */
public class StateWonTest {

    private final IBlackJackController controller = new BlackJackController();

    private StateWon state = new StateWon(controller);

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

        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
            assert (this.controller.getCurrentState() instanceof StateBlackJack);
        } else {
            this.controller.setCurrentState(new StateLost(controller));
            assert (this.controller.getCurrentState() instanceof StateLost);
        }
    }
}
