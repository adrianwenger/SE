package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
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
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateWon(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
    }

    /**
     * GameState not longer changeable.
     */
    @Override
    public void change() {
        // Player Won in concerning BlackJack
        if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
            this.controller.getCurrentState().change();
        } else {
            // Player won concerning better face
            this.controller.setStatusLine(this.controller.getPlayer().getName()
                    + " ,you won!!!! --> "
                    + this.controller.getPlayer().printPlayersHand() + "\n\n");
            // change state to StateEndGame
            this.controller.setCurrentState(new StateEndGame(controller));
            this.controller.getCurrentState().change();
        }
    }
}
