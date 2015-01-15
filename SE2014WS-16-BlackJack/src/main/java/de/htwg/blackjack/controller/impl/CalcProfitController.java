/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import static de.htwg.blackjack.util.StaticCollections.BLACKJACK;

/**
 *
 * @author philippschultheiss
 */
public final class CalcProfitController implements ICalcProfitController {

    /**
     * CalcProfitController.
     */
    private final IBlackJackController controller;
    /**
     * Profit of Player.
     */
    private double profit;
    /**
     * stake multicator constant for 1.5.
     */
    private static final double MULTICATOR = 1.5;
    /**
     * TWO constant for 2.
     */
    private static final double TWO = 2;
    /**
     * ZERO constant for 0.
     */
    private static final double ZERO = 0;

    /**
     *
     * @param cont IBlackJackCOntroller
     */
    @Inject
    public CalcProfitController(final IBlackJackController cont) {
        this.controller = cont;
    }

    @Override
    public void calcProfit() {
        if (controller.getCurrentState() instanceof StateWon) {
            //Round won --> Player asset = roundstake plus roundstake * 1.5
            profit = controller.getPlayer().getRoundStake()
                    + (controller.getPlayer().getRoundStake() * MULTICATOR);
        } else if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            //Player got BlackJack = roundstake * 2
            profit = controller.getPlayer().getRoundStake()
                    + (controller.getPlayer().getRoundStake() * TWO);
        } else if (this.controller.hasBlackJack(this.controller.getDealer())) {
            profit = ZERO;
        } else if (controller.getPlayer().getValue() == BLACKJACK
                && controller.getDealer().getValue() == BLACKJACK) {
            //Player and Dealer got BlackJack = Player gets roundstake back
            profit = controller.getPlayer().getRoundStake();
        } else {
            profit = ZERO;
            //Player lost = stake - roundStake
            controller.getPlayer().setStake(controller.getPlayer().getStake()
                    - controller.getPlayer().getRoundStake());
            
        }
    }

    /**
     * Calculates the current stake plus the profit.
     */
    @Override
    public void clacStake() {
        this.controller.getPlayer().setStake(
                this.controller.getPlayer().getStake() + profit);
    }

    /**
     * Checks if player can double his roundstake If doubled roundstake bigger
     * than roundstake: return false.
     *
     * @return true if double the stake is possible
     */
    @Override
    public boolean checkDouble() {
        return controller.getPlayer().getStake()
                >= (controller.getPlayer().getRoundStake() * TWO);
    }

    /**
     * checks the Stake. Player can't make depts.
     */
    @Override
    public void checkStake() {
        if (controller.getPlayer().getStake() == 0) {
            this.controller.setCurrentState(new StateEndGame(controller));
            this.controller.getCurrentState().change();
        }
    }
    @Override
    public boolean setRoundStake(double stake) {
        if(this.controller.getCurrentState() instanceof StateEndRound
            || this.controller.getCurrentState() == null) {
            if(stake <= this.controller.getPlayer().getStake()) {
                this.controller.getPlayer().setRoundStake(stake);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
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

    /**
     * Calculates profit and new credit and sets new Statusline.
     */
    @Override
    public void printCurrentCreditState() {
       throw new UnsupportedOperationException();
    }
}
