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

    public void calcProfit() {
        if (controller.getCurrentState() instanceof StateWon) {
            profit = player.getRoundStake() + (player.getRoundStake() * MULTICATOR);
        } else {
            profit = 0;
            player.setStake(player.getStake() - player.getRoundStake());
        }
    }

    @Override
    public double getProfit() {
        return profit;
    }
}
