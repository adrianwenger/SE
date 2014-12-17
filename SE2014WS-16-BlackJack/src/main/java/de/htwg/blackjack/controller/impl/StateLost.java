package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;


/**
 * @author Adrian Wenger
 */
public final class StateLost implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;
   /**
     * calc controller.
     */
    private final ICalcProfitController calcController;

    /**
     * Public Constructor.
     *
     * @param blackJackController IBlackJackController
     * @param cal  ICalcProfitController
     */
    public StateLost(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     *
     */
    @Override
    public void change() {
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            this.controller.setCurrentState(new StateBlackJack(controller,
                    calcController));
            this.controller.getCurrentState().change();
        } else {
            this.controller.setStatusLine("Round Lost!\n"
                    + this.controller.getPlayer().printPlayersHand() + "\n\n");
             this.calcController.calcProfit();
            this.controller.setStatusLine("Your profit: " + this.calcController.getProfit() + "\n\n");
            // change state to StateEndGame
            this.controller.setCurrentState(new StateEndRound(controller,
                    calcController));
            this.controller.getCurrentState().change();
        }
    }
}