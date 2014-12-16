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
     * @return profit
     */
    double getProfit();
    /**
     * calculates the profit
     */
    void calcProfit();
    /**
     * Checks if player can double his roundstake
     * If doubled rounstake bigger than roundstake: return false
     */
    boolean checkDouble();
    /**
     * checks the Stake. Player can't make depts.
     */
    void checkStake();
}
