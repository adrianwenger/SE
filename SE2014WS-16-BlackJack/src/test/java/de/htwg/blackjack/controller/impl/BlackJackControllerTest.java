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
    IPlayer dealer;

    @Before
    public void setUp() {
        controller = new BlackJackController();
        this.controller.setPlayer("Fritz");
        this.controller.setDealer();
        controller.setDeck(1);
        player = controller.getPlayer();
        dealer = controller.getDealer();
    }

    @Test
    public void testGetDeck() {
        deck = new Deck();
        boolean expResult = true;
        boolean result = deck.getDeck() instanceof ICard[];
        assertEquals(expResult, result);
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
        ICard[] cards = new Deck().getDeck();
        controller.getFirstTwoCardsPlayer();
        ICard[] plcards = player.getPlayerHand();
        boolean first = false;
        boolean second = false;
        for (ICard card : cards) {
            if (plcards[0].getSuit() == card.getSuit() && plcards[0].getNumber() == card.getNumber()) {
                first = true;
            }
            if (plcards[1].getSuit() == card.getSuit() && plcards[1].getNumber() == card.getNumber()) {
                second = true;
            }
        }
        assert (first);
        assert (second);
    }
    
    @Test
    public void testGetFirstTwoCardsDealer() {
        ICard[] cards = new Deck().getDeck();
        controller.getFirstTwoCardsDealer();
        ICard[] plcards = dealer.getPlayerHand();
        boolean first = false;
        boolean second = false;
        for (ICard card : cards) {
            if (plcards[0].getSuit() == card.getSuit() && plcards[0].getNumber() == card.getNumber()) {
                first = true;
            }
            if (plcards[1].getSuit() == card.getSuit() && plcards[1].getNumber() == card.getNumber()) {
                second = true;
            }
        }
        assert (first);
        assert (second);
    }

    @Test
    public void testGetCardPlayer() {
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

    @Test
    public void testCheckIfDealerNeedsCard() {

    }
}
