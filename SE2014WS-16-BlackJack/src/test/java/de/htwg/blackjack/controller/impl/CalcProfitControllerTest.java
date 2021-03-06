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
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.util.Suit;
import de.htwg.blackjack.model.impl.Card;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author philippschultheiss
 */
public class CalcProfitControllerTest {


    ICalcProfitController calcController;
    IBlackJackController controller;
    private IPlayer player;
    private IPlayer dealer;

    @Before
    public void setUp() {
        this.controller = new BlackJackController();
        this.controller.setInjector(Guice.createInjector(new BlackJackModule()));
        this.controller.setPlayer("Fritz");
        this.controller.setDealer();
        this.controller.setDeck(1);
        this.calcController = new CalcProfitController(controller);
        this.player = controller.getPlayer();
        this.dealer = controller.getDealer();
        this.player.add(new Card(Suit.CLUBS, 10));
        this.dealer.add(new Card(Suit.CLUBS, 10));
    }

    @Test
    public void testGetSetStake() {
        player.setStake(5);
        int expResult = 5;
        int result = (int) player.getStake();
        assertEquals(expResult, result);
    }

    @Test
    public void testCheckStake() {
        player.setStake(0);
        calcController.checkStake();
        boolean result = this.controller.getCurrentState() instanceof StateEndGame;
        assert (result);

    }

    @Test
    public void testCalcProfit() {
        this.player.clearHand();
        this.dealer.clearHand();
        //Round won --> Player asset = roundstake + roundstake * 1.5
        this.controller.setCurrentState(new StateWon(controller, this.controller.getCalcController()));
        player.setStake(1000);
        player.setRoundStake(20);
        calcController.calcProfit();
        double result = this.calcController.getProfit();
        // roundstake + roundstake * 1.5;
        double expResult = 50;
        assertEquals(expResult, result, 0.0);

        //Player got BlackJack = roundstake + roundstake * 2
        this.controller.setCurrentState(null);
        player.add(new Card(Suit.CLUBS, 10));
        player.add(new Card(Suit.CLUBS, 1));
        player.add(new Card(Suit.CLUBS, 10));
        calcController.calcProfit();
        result = this.calcController.getProfit();
        // roundstake + roundstake * 2
        expResult = 60;
        assertEquals(expResult, result, 0);

        //Player and Dealer got BlackJack = Player gets roundstake back
        this.player.setRoundStake(20);
        dealer.add(new Card(Suit.CLUBS, 10));
        dealer.add(new Card(Suit.CLUBS, 10));
        dealer.add(new Card(Suit.CLUBS, 1));
        calcController.calcProfit();
        result = this.calcController.getProfit();
        // Player gets roundstake back
        expResult = 20;
        assertEquals(expResult, result, 0);

        // Dealer has BlackJack --> Profit = 0
        this.controller.getPlayer().clearHand();
        calcController.calcProfit();
        result = this.calcController.getProfit();
        expResult = 0.0;
        assertEquals(expResult, result, 0);

        // else Player Lost -->  Player lost = stake - roundStake
        this.controller.getDealer().clearHand();
        calcController.calcProfit();
        result = this.calcController.getProfit();
        expResult = 0.0;
        assertEquals(expResult, result, 0);
        result = this.controller.getPlayer().getStake();
        expResult = 1130;
        assertEquals(expResult, result, 0);

    }

    @Test
    public void testCheckDouble() {
        this.controller.getPlayer().setStake(100);
        this.controller.getPlayer().setRoundStake(50);
        boolean expResult = true;
        boolean result = this.controller.getCalcController().checkDouble();
        assertEquals(expResult, result);
    }

    @Test
    public void testCalcStake() {
        this.player.clearHand();
        this.controller.getPlayer().setStake(100);
        this.controller.getPlayer().setRoundStake(50);
        player.add(new Card(Suit.CLUBS, 10));
        player.add(new Card(Suit.CLUBS, 1));
        player.add(new Card(Suit.CLUBS, 10));
        this.calcController.calcProfit();
        double result = this.controller.getPlayer().getStake();
        // Player got BlackJack. With RoundStake of 50 he wins 150€
        // new Stake = 100 + 150; 
        double expResult = 250;
        assertEquals(expResult, result, 0);

        this.controller.setCurrentState(new StateLost(controller, calcController));
        ICalcProfitController tmp = new CalcProfitController(controller);
        this.controller.getPlayer().setStake(100);
        this.controller.getPlayer().setRoundStake(50);
        tmp.calcProfit();
        expResult = 100;
        result = this.controller.getPlayer().getStake();
        assertEquals(expResult, result, 0);
    }

    @Test
    public void testPrintCurrentCreditState() {
        this.player.clearHand();
        this.player.setStake(100);
        this.player.setRoundStake(50);
        this.player.add(new Card(Suit.CLUBS, 10));
        this.player.add(new Card(Suit.CLUBS, 1));
        this.player.add(new Card(Suit.CLUBS, 10));
        this.calcController.calcProfit();
        String result = this.calcController.printCurrentCreditState();
        String expResult = "Your new Stake: 250.0 €\n" + "Your Profit: 150.0 €\n";
        boolean compare = result.equals(expResult);
        assert (compare);
    }

    @Test
    public void testSetStake() {
        //         controller.getPlayer().setStake(stake);
        this.calcController.setStake(100);
        double result = this.controller.getPlayer().getStake();
        double expResult = 100;
        assertEquals(expResult, result, 0);
    }

    @Test          
    public void testSetRoundStake() {
//        controller.getPlayer().setRoundStake(roundStake);
//        controller.getPlayer().setStake(controller.getPlayer().getStake() - roundStake);
//        controller.notifyObservers();
        this.player.setStake(100);
        this.calcController.setRoundStake(50);
        double resultStake = this.controller.getPlayer().getStake();
        double expResultStake = 50;
        assertEquals(resultStake, expResultStake, 0);
        
        resultStake = this.controller.getPlayer().getRoundStake();
        expResultStake = 50;
        assertEquals(resultStake, expResultStake, 0);
    }

    @Test
    public void doubleRoundStake() {
//        double oldRStake = controller.getPlayer().getRoundStake();
//        controller.getPlayer().setStake(controller.getPlayer().getStake() - oldRStake);
//        oldRStake *= 2;
//        controller.getPlayer().setRoundStake(oldRStake);
//        controller.notifyObservers();
        this.player.setStake(160);
        this.player.setRoundStake(40);
        this.calcController.doubleRoundStake();
        double result = this.controller.getPlayer().getStake();
        double expResult = 120;
        assertEquals(expResult, result, 0);
        
    }

}
