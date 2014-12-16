/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.blackjack.controller;

/**
 *
 * @author philippschultheiss
 */
public interface ICalcProfitController {
     /**
     *
     * @param pla Player
     */
    public void setPlayer(final String pla);
    /**
     * set Dealer.
     */
    public void setDealer();
    /**
     * 
     * @return profit
     */
    public double getProfit();
    /**
     * calculates the profit
     */
    public void calcProfit();
    /**
     * Checks if player can double his roundstake
     * If doubled rounstake bigger than roundstake: return false
     */
    public boolean checkDouble();
}
