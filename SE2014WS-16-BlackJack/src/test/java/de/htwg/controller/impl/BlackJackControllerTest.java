/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.controller.IGameState;
import de.htwg.model.IPlayer;
import de.htwg.model.impl.Card;
import de.htwg.model.impl.Player;
import de.htwg.model.impl.Suit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author philippschultheiss
 */
public class BlackJackControllerTest {

    static final int BLACKJACK = 21;

    IBlackJackController controller;

    IPlayer player;

    IPlayer dealer;

    IGameState currentState;

    @Before
    public void setUp() {
        controller = new BlackJackController();
        this.controller.setPlayer("Test");
        this.controller.setDealer();
    }

    @Test
    public void testSetStatusLine() {
        controller.setStatusLine("status");
        String expResult = "";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetStatusLine() {
        this.controller.setStatusLine("test");
        String result = controller.getStatusLine();
        String expResult = "";
        assertEquals(expResult, result);
    }

    @Test
    public final void testCheckGameState() {
        // Test StateBlackJack
        StateBlackJackTest state = new StateBlackJackTest();
        boolean result = state instanceof StateBlackJackTest;
        boolean expResult = true;
        assertEquals(expResult, result);
        
        StateInGameTest state2 = new StateInGameTest();
        result = state2 instanceof StateInGameTest;
        assertEquals(expResult, result);
    }
    
    @Test
    public final void testFirstTwoCardsOfPlayer() {
        this.controller.getPlayer().add(new Card(Suit.CLUBS, 5));

        String result = this.controller.getPlayer().printPlayersHand();
        String expResult = "Cards: FiveOfCLUBS Value: 5";
        
        assertEquals(expResult, result);
    }
}
