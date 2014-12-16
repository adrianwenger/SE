/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.impl.Player;

/**
 *
 * @author philippschultheiss
 */
public class CalcProfitController implements ICalcProfitController {

    private IPlayer player;
    private IPlayer dealer;
    private IBlackJackController controller;
    private double profit;
    private final double MULTICATOR = 1.5;

    private final int BLACKJACK = 21;
    private final double TWO = 2;

    public CalcProfitController(IBlackJackController controller) {
        this.controller = controller;
    }

    /**
     *
     * @param pla Player
     */
    @Override
    public void setPlayer(final String pla) {
        this.player = new Player(pla);
    }

    /**
     * set Dealer.
     */
    @Override
    public void setDealer() {
        this.dealer = new Player("Dealer");
    }

    @Override
    public void calcProfit() {
        if (controller.getCurrentState() instanceof StateWon) {
            //Round won --> Player asset = stake plus stake * 1.5
            profit = player.getRoundStake() + (player.getRoundStake() * MULTICATOR);
        } else if (controller.getCurrentState() instanceof StateBlackJack) {
            //Player got BlackJack = stake * 2
            profit = player.getRoundStake() + (player.getRoundStake() * TWO);
        } else if (player.getValue() == BLACKJACK && dealer.getValue() == BLACKJACK) {
            //Player and Dealer got BlackJack = Player gets stake back
            profit = player.getRoundStake();
        } else {
            //Player lost = stake - roundStake
            player.setStake(player.getStake() - player.getRoundStake());
        }
    }
    /**
     * Checks if player can double his roundstake
     * If doubled rounstake bigger than roundstake: return false
     */
    @Override
    public boolean checkDouble(){
        if(player.getStake() <= (player.getStake() * TWO)) {
            return false;
        }
        return true;
    }
//    @Override
//    public void checkStake(){
//        if(player.getStake() <= 0){
//            this.controller.setCurrentState(new State);
//        }
//    }
    @Override
    public double getProfit() {
        return profit;
    }
}
