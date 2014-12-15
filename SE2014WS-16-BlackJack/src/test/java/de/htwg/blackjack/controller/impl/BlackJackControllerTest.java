/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.IGameState;
import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.model.impl.Deck;
import de.htwg.blackjack.model.Suit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author philippschultheiss
 */
public class BlackJackControllerTest {

    static final int BLACKJACK = 21;

    private IBlackJackController controller;
    private IDeck deck;
    private IPlayer player, dealer;

    @Before
    public void setUp() {
        this.controller = new BlackJackController();
        this.controller.setPlayer("Fritz");
        this.controller.setDealer();
        this.controller.setDeck(1);
        this.player = controller.getPlayer();
        this.dealer = controller.getDealer();
        this.deck = this.controller.getDeck();
    }

    @Test
    public void testGetDeck() {
        deck = new Deck();
        boolean result = this.controller.getDeck() instanceof IDeck;
        assert (result);
    }

    @Test
    public void testSetStatusLine() {
        controller.setStatusLine("status");
        String expResult = "";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }

    /**
     * Test GameState.
     */
    @Test
    public final void testCheckGameState() {
        // Case: new Game
        this.controller.setCurrentState(null);
        this.controller.checkGameState();
        boolean result = this.controller.getCurrentState() instanceof StateInGame;
        assert (result);

        // Case: running game
        this.controller.checkGameState();
        result = this.controller.getCurrentState() instanceof IGameState;
        assert (result);
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
    public void getCardPlayer() {
        ICard[] cards = new Deck().getDeck();
        controller.getCardPlayer();
        ICard[] plcards = player.getPlayerHand();
        boolean first = false;
        for (ICard card : cards) {
            if (plcards[0].getSuit() == card.getSuit() && plcards[0].getNumber() == card.getNumber()) {
                first = true;
            }
        }
        assert (first);
    }

    @Test
    public void getCardDealer() {
        ICard[] cards = new Deck().getDeck();
        controller.getCardDealer();
        ICard[] plcards = dealer.getPlayerHand();
        boolean first = false;
        for (ICard card : cards) {
            if (plcards[0].getSuit() == card.getSuit() && plcards[0].getNumber() == card.getNumber()) {
                first = true;
            }
        }
        assert (first);
    }

    @Test
    public void testCreate() {
        controller.setStatusLine("Welcome to BlackJack...");
        String expResult = "";
        assertEquals(expResult, controller.output());
    }

    @Test
    public void testDealerLowerPlayer() {
        player.add(new Card(Suit.CLUBS, 4));
        player.add(new Card(Suit.CLUBS, 10));
        player.add(new Card(Suit.CLUBS, 10));

        dealer.add(new Card(Suit.CLUBS, 10));
        dealer.add(new Card(Suit.CLUBS, 5));

        controller.checkIfDealerNeedsCard();
        int expResult = 3;
        int i;
        for (i = 0; i < dealer.getPlayerHand().length; i++) {
            if (dealer.getPlayerHand()[i] == null) {
                break;
            }
        }
        assertEquals(expResult, i);
    }

    /**
     * Test endGame.
     */
    @Test
    public void testEndGame() {
        this.controller.setCurrentState(null);
        this.controller.endGame();
        String result = this.controller.getStatusLine();
        String expResult = "";
        assertEquals(expResult, result);
    }
}
