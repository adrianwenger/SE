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
        this.calcController = new CalcProfitController(controller);
        this.player = controller.getPlayer();
        this.dealer = controller.getDealer();
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
        expResult = 1000;
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
        this.controller.getPlayer().setStake(100);
        this.controller.getPlayer().setRoundStake(50);
        player.add(new Card(Suit.CLUBS, 10));
        player.add(new Card(Suit.CLUBS, 1));
        player.add(new Card(Suit.CLUBS, 10));
        this.calcController.calcProfit();
        this.calcController.calcStake();
        double result = this.controller.getPlayer().getStake();
        // Player got BlackJack. With RoundStake of 50 he wins 150€
        // new Stake = 100 + 150; 
        double expResult = 250;
        assertEquals(expResult, result, 0);
    }
    
    @Test
    public void testPrintCurrentCreditState() {
        /**
         *  StringBuilder sb = new StringBuilder();

        sb.append("Your new Stake: ");
        calcStake();
        sb.append(controller.getPlayer().getStake());
        calcProfit();
        sb.append("\n").append("Your Profit: ").append(getProfit());

        return sb.append("\n").toString();
         */
        this.player.setStake(100);
        this.player.setRoundStake(50);
        this.player.add(new Card(Suit.CLUBS, 10));
        this.player.add(new Card(Suit.CLUBS, 1));
        this.player.add(new Card(Suit.CLUBS, 10));
        this.calcController.calcProfit();
        this.calcController.calcStake();
        String result = this.calcController.printCurrentCreditState();
        String expResult = "Your new Stake: 250.0 €\n" + "Your Profit: 150.0 €\n"; 
        boolean compare = result.equals(expResult);
        assert(compare);
    }
}
