/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adrian Wenger
 */
public class StateBlackJackTest {

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

        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            result = this.controller.getCurrentState();
            expResult = state;
            assertEquals(expResult, result);
        } else {
            result = this.controller.getCurrentState();
            expResult = state;
            assertEquals(expResult, result);
        }
    }

}
