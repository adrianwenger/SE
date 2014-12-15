/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.controller.IGameState;
import de.htwg.controller.impl.BlackJackController;
import de.htwg.model.ICard;
import de.htwg.model.IDeck;
import de.htwg.model.IPlayer;
import de.htwg.model.impl.Card;
import de.htwg.model.impl.Deck;
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
        StateBlackJackTest state = new StateBlackJackTest();
        boolean result = state instanceof StateBlackJackTest;
        boolean expResult = true;
        assertEquals(expResult, result);

        StateInGameTest state2 = new StateInGameTest();
        result = state2 instanceof StateInGameTest;
        assertEquals(expResult, result);
    }

    @Test
    public void testSetDeck() {
        deck = new Deck();
        ICard[] result = deck.getDeck();
        assertEquals(result.length, deck.getDeck().length);
    }

    @Test
    public void testGetStatusLine() {
        controller.setStatusLine("Hallo");
        String expResult = "";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFirstTwoCardsPlayer() {
        player = new Player("Philipp");
        player.add(new Card(Suit.SPADES, 1));
        player.add(new Card(Suit.CLUBS, 6));
        boolean expResult = true;
        boolean result = player.printPlayersHand().equals("Cards: AceOfSPADES "
                + "SixOfCLUBS Value: 17");
        //String expResult = "Cards: AceOfSPADES SixOfCLUBS Value: 17";
        String result0 = "Cards: AceOfSPADES SixOfCLUBS Value: 17";
        String result1 = player.printPlayersHand();
        boolean test = result0.equals(result1);
        assertEquals(expResult, test);
    }

    @Test
    public void testGetCardPlayer() {
        player = new Player("Philipp");
        player.add(new Card(Suit.CLUBS, 6));
        //String expResult = "Cards: SixOfCLUBS Value: 6";
        int expResult = 6;
        assertEquals(expResult, player.getValue());
    }

    @Test
    public void testCreate() {
        controller.setStatusLine("Welcome to BlackJack...");
        String expResult = "";
        assertEquals(expResult, controller.output());
    }
}
