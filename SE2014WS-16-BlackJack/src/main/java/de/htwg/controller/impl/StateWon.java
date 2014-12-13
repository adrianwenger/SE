package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.controller.IGameState;

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
        change();
    }

    /**
     * GameState not longer changeable.
     */
    @Override
    public void change() {
        // Player Won in concerning BlackJack
        if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
        } else {
            // Player won concerning better face
            this.controller.setStatusLine(this.controller.getPlayer().getName()
                    + " ,you won!!!! --> "
                    + this.controller.getPlayer().printPlayersHand());
            //System.exit(0);
        }
    }
}
