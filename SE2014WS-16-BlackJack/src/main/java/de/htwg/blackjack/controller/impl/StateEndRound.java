package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;

/**
 * @author Adrian Wenger
 */
public final class StateEndRound implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;
    /**
     * calc controller.
     */
    private final ICalcProfitController calcController;

    /**
     * Contstructor.
     * @param controller
     * @param calcController 
     */
    public StateEndRound(final IBlackJackController controller,
            final ICalcProfitController calcController) {
        this.calcController = calcController;
        this.controller = controller;
    }

    /**
     * ask if Player want to run another round or want to end the game.
     */
    @Override
    public void change() {
            //checks if player has enough money --> no depts!
            this.calcController.checkStake();
            // start new round
    }
}
