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
     * calculates the profit .
     */
    void calcProfit();
    /**
     * Checks if player can double his roundstake
     * If doubled rounstake bigger than roundstake: return false.
     * @return if doubling stake is possible
     */
    boolean checkDouble();
    /**
     * checks the Stake. Player can't make depts.
     */
    void checkStake();
}
