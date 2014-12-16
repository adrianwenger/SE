/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;

/**
 *
 * @author philippschultheiss
 */
public class CalcProfitController implements ICalcProfitController {

    private IBlackJackController controller;
    private double profit;
    private final double MULTICATOR = 1.5;

    private final int BLACKJACK = 21;
    private final double TWO = 2;

    public CalcProfitController(IBlackJackController controller) {
        this.controller = controller;
    }

    @Override
    public void calcProfit() {
        if (controller.getCurrentState() instanceof StateWon) {
            //Round won --> Player asset = stake plus stake * 1.5
            profit = controller.getPlayer().getRoundStake() + (controller.getPlayer().getRoundStake() * MULTICATOR);
        } else if (controller.getCurrentState() instanceof StateBlackJack) {
            //Player got BlackJack = stake * 2
            profit = controller.getPlayer().getRoundStake() + (controller.getPlayer().getRoundStake() * TWO);
        } else if (controller.getPlayer().getValue() == BLACKJACK && controller.getDealer().getValue() == BLACKJACK) {
            //Player and Dealer got BlackJack = Player gets stake back
            profit = controller.getPlayer().getRoundStake();
        } else {
            //Player lost = stake - roundStake
            controller.getPlayer().setStake(controller.getPlayer().getStake() - controller.getPlayer().getRoundStake());
        }
    }
    /**
     * Checks if player can double his roundstake
     * If doubled rounstake bigger than roundstake: return false
     */
    @Override
    public boolean checkDouble(){
        if(controller.getPlayer().getStake() <= (controller.getPlayer().getStake() * TWO)) {
            return false;
        }
        return true;
    }
    /**
     * checks the Stake. Player can't make depts.
     */
    @Override
    public void checkStake(){
        if(controller.getPlayer().getStake() == 0){
            this.controller.setCurrentState(new StateEndGame(controller, this));
        }
    }
    /**
     * 
     * @return profit
     */
    @Override
    public double getProfit() {
        return profit;
    }
}
