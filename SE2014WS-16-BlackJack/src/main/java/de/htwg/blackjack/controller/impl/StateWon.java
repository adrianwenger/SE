package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;

/**
 *
 * @author Adrian Wenger
 */
final class StateWon implements IGameState {

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
    public StateWon(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     * GameState not longer changeable.
     */
    @Override
    public void change() {
        // Player Won in concerning BlackJack
        if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller,
                    calcController));
            this.controller.getCurrentState().change();
        } else {
            // Player won concerning better face
            this.controller.setStatusLine(this.controller.getPlayer().getName()
                    + " ,you won!!!! --> "
                    + this.controller.getPlayer().printPlayersHand() + "\n\n");
            //print credit
            this.calcController.printCurrentCreditState();
            // change state to StateEndGame
            this.controller.setCurrentState(new StateEndRound(controller,
                    calcController));
            this.controller.getCurrentState().change();
        }
    }
}
