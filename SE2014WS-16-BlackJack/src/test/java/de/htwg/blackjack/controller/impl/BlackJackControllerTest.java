/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.model.impl.Deck;
import de.htwg.blackjack.model.impl.Player;
import de.htwg.blackjack.model.impl.Suit;
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
    IDeck deck;
    IPlayer player;

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
    public final void testCheckGameState() {
        // Test StateBlackJack
//        StateBlackJackTest state = new StateBlackJackTest();
//        boolean result = state instanceof StateBlackJackTest;
//        boolean expResult = true;
//        assertEquals(expResult, result);
//        
//        StateInGameTest state2 = new StateInGameTest();
//        result = state2 instanceof StateInGameTest;
//        assertEquals(expResult, result);
    }
    
    @Test
    public void testSetDeck(){
        deck = new Deck();
        ICard[] result = deck.getDeck();
        assertEquals(result.length, deck.getDeck().length);
    }
    
    @Test
    public void testGetStatusLine(){
        controller.setStatusLine("Hallo");
        String expResult = "";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testGetFirstTwoCardsPlayer(){
        player = new Player("Philipp");
        player.add(new Card(Suit.SPADES, 1));
        player.add(new Card(Suit.CLUBS, 6));
        String expResult = "Cards: AceOfSPADES SixOfCLUBS Value: 17";
        assertEquals(expResult, player.printPlayersHand());
    }
}
