/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.controller;

import de.htwg.model.impl.Card;
import de.htwg.model.impl.Deck;
import de.htwg.model.impl.Player;
import de.htwg.model.impl.Suit;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author philippschultheiss
 */
public class BlackJackControllerTest {
    IBlackJackController controller;

    @Before
    public void setUp() {
        controller = new BlackJackController();
    }

    public void testSetStatusLine(){
        controller.setStatusLine("status");
        String expResult = "";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }

    @Test
    public void testSetCurrentState(){
        controller.setCurrentState(new StateInGame(controller));
        IGameState result = controller.getCurrentState();
        assert (result instanceof StateInGame);
    }
    
    @Test
    public void testSetDeck(){
        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
