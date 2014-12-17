package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;

/**
 *
 * @author Adrian Wenger
 */
final class StateBlackJack implements IGameState {

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
     * @param cal ICalcProfitController
     */
    public StateBlackJack(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     * now other State available.
     */
    @Override
    public void change() {
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            this.controller.setStatusLine("Dealer got BlackJack!\n\n");
            this.calcController.calcProfit();
             this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
            this.controller.setStatusLine("Your profit: " + this.calcController.getProfit() + "\n");
             this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
        } else {
            this.controller.setStatusLine("Congratulations "
                    + this.controller.getPlayer().getName()
                    + ", you got BlackJack!\n\n");
            this.calcController.calcProfit();
             this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
            this.controller.setStatusLine("Your profit: " + this.calcController.getProfit() + "\n");
             this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
        }
        // change state to StateEndGame
        this.controller.setCurrentState(new StateEndRound(controller,
                calcController));
        this.controller.getCurrentState().change();
    }
}
