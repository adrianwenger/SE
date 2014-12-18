/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.model.IPlayer;
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

    @Before
    public void setUp() {
        this.controller = new BlackJackController();
        this.controller.setPlayer("Fritz");
        this.calcController = new CalcProfitController(controller);
        this. player = controller.getPlayer();
    }
    
    @Test
    public void testGetSetStake(){
        player.setStake(5);
        int expResult = 5;
        int result = (int) player.getStake();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testCheckStake(){
        player.setStake(0);
        calcController.checkStake();
        boolean result = this.controller.getCurrentState() instanceof StateEndGame;
        assert (result);
        
    }
    
    @Test
    public void testCalcProfit() {
//asdasd
    }

    @Test
    public void testCheckDouble() {
        this.controller.getPlayer().setStake(100);
        this.controller.getPlayer().setRoundStake(50);
        boolean expResult = true;
        boolean result = this.controller.getCalcController().checkDouble();

        assertEquals(expResult, result);
    }
}
