/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import com.google.inject.Guice;
import de.htwg.blackjack.BlackJackModule;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;
import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.model.impl.Deck;
import de.htwg.blackjack.util.Suit;
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
    private ICalcProfitController calController;
    private IDeck deck;
    private IPlayer player, dealer;

    @Before
    public void setUp() {
        this.controller = new BlackJackController();
        this.controller.setCalcController(new CalcProfitController(controller));
        this.controller.setInjector(Guice.createInjector(new BlackJackModule()));
        this.controller.setPlayer("Fritz");
        this.controller.setDealer();
        this.controller.setDeck(1);
        this.player = controller.getPlayer();
        this.dealer = controller.getDealer();
        this.deck = controller.getDeck();
        this.calController = controller.getCalcController();
    }

    @Test
    public void testSetCalcController() {
        this.controller.setCalcController(calController);
        ICalcProfitController epxResult = this.calController;
        ICalcProfitController result = this.controller.getCalcController();
        assertEquals(epxResult, result);
    }

    @Test
    public void testGetStatusLine() {
        controller.setStatusLine("Hallo");
        String expResult = "Hallo";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetFirstTwoCardsPlayer() {
        Deck tmp = new Deck();
        tmp.setNumOfDecks(1);
        ICard[] cards = tmp.getDeck();
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
        Deck tmp = new Deck();
        tmp.setNumOfDecks(1);
        ICard[] cards = tmp.getDeck();
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
        Deck tmp = new Deck();
        tmp.setNumOfDecks(1);
        ICard[] cards = tmp.getDeck();
        controller.getCardPlayer();
        ICard[] plcards = player.getPlayerHand();
        boolean first = false;
        for (int i = 0; i < cards.length; i++ ) {
            if (plcards[0].getSuit() == cards[i].getSuit() && plcards[0].getNumber() == cards[i].getNumber()) {
                first = true;
            }
        }
        assert (first);
    }

    @Test
    public void getCardDealer() {
        Deck tmp = new Deck();
        tmp.setNumOfDecks(1);
        ICard[] cards = tmp.getDeck();
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
    public void testGetDeck() {
        boolean result = this.controller.getDeck() instanceof IDeck;
        assert (result);
    }

    @Test
    public void testSetDeck() {
        ICard[] result = deck.getDeck();
        assertEquals(result.length, deck.getDeck().length);
    }

    @Test
    public void testSetStatusLine() {
        controller.setStatusLine("status");
        String expResult = "status";
        String result = controller.getStatusLine();
        assertEquals(expResult, result);
    }

    @Test
    public void testHasBlackJack() {
        // Case:  < BlackJAck
        player.add(new Card(Suit.CLUBS, 10));
        player.add(new Card(Suit.CLUBS, 10));
        boolean expResult = false;
        boolean result = this.controller.hasBlackJack(player);
        assertEquals(expResult, result);

        // Case: BlackJack
        player.add(new Card(Suit.CLUBS, 1));
        expResult = true;
        result = this.controller.hasBlackJack(player);
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckIfDealerNeedsCard() {
        player.add(new Card(Suit.CLUBS, 4));
        player.add(new Card(Suit.CLUBS, 10));

        dealer.add(new Card(Suit.CLUBS, 10));
        dealer.add(new Card(Suit.CLUBS, 5));

        boolean expResult = false;
        boolean result = controller.checkIfDealerNeedsCard();
        assertEquals(expResult, result);

        player.add(new Card(Suit.CLUBS, 2));
        expResult = true;
        result = controller.checkIfDealerNeedsCard();
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

        // Case: game in StateEndRound
//        this.controller.setCurrentState(new StateEndRound(controller, calController));
//        this.controller.checkGameState();
//        result = this.controller.getCurrentState() instanceof StateEndRound;
//        assert (result);
    }

    @Test
    public void testCreate() {
        controller.setStatusLine("Welcome to BlackJack...");
        String expResult = "Welcome to BlackJack...";
        assertEquals(expResult, controller.output());
    }

    @Test
    public void testCreateNewRound() {
        this.controller.getPlayer().add(new Card(Suit.CLUBS, 5));
        this.controller.getDealer().add(new Card(Suit.CLUBS, 5));
        this.controller.setDeck(1);
        this.controller.setCurrentState(null);
        this.controller.setStatusLine("test");
        this.controller.getPlayer().setRoundStake(50);

        //this.controller.createNewRound();
        // Test PlayerVal
        int expResultPlayerVal = 5;
        int resultPlayerVal = this.controller.getPlayer().getValue();
        assertEquals(expResultPlayerVal, resultPlayerVal);

        // Test DealerVal
        int expResultDealerVal = 5;
        int resultDealerVal = this.controller.getPlayer().getValue();
        assertEquals(expResultDealerVal, resultDealerVal);

        // Test state
        IGameState expResultState = null;
        IGameState resultState = this.controller.getCurrentState();
        assertEquals(expResultState, resultState);

//        // Test deck
//        IDeck expResultDeck = null;
//        IDeck resultDeck = this.controller.getDeck();
//        assertEquals(expResultDeck,resultDeck);
//        
        // Test statusLine
        String expResultStatusLine = "test";
        String resultStatusLine = this.controller.getStatusLine();
        assertEquals(expResultStatusLine, resultStatusLine);

        // Test roundStake
        double expResultRoundStake = 50;
        double resultRoundStake = this.controller.getPlayer().getRoundStake();
        assertEquals(expResultRoundStake, resultRoundStake, .0);
    }

    /**
     * Test endGame.
     */
    @Test
    public void testEndGame() {
        // Case Dealer doesn't need another Card
        this.controller.getPlayer().add(new Card(Suit.CLUBS, 3));
        this.controller.getDealer().add(new Card(Suit.CLUBS, 5));
        this.controller.setCurrentState(new StateInGame(controller, calController));
        this.controller.endGame();
        boolean result = this.controller.getCurrentState() instanceof StateEndGame;
        assert (result);

//        // Case Dealer needs another Card
//        this.controller.getPlayer().add(new Card(Suit.CLUBS, 3));
//
//        this.controller.endGame();
//        String expResult = "FiveOfCLUBS Value: 5";
//        String result = this.controller.getDealer().printPlayersHand();
//        assertEquals(expResult, result);
//
//        // Case Dealer needs another Card
//        this.controller.getPlayer().add(new Card(Suit.CLUBS, 3));
//        testEndGame();
//        this.controller.setCurrentState(new StateEndGame(controller, calController));
//        IGameState expResult = StateEndGame;
//        if (this.currentState instanceof StateInGame) {
//            this.currentState.change();
//        }
//        // Player <= Dealer && Dealer < BLACKJACK
//        if ((this.player.getValue() <= dealer.getValue())
//                && dealer.getValue() < BLACKJACK) {
//            setCurrentState(new StateLost(this, calcController));
//            this.currentState.change();
//        }
//
//        this.setCurrentState(new StateEndGame(this, calcController));
//        this.currentState.change();
//        checkGameState();
    }

}
