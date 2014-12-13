package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.controller.IGameState;


/**
 * @author Adrian Wenger
 */
public final class StateLost implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateLost(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
        change();
    }

    /**
     *
     */
    @Override
    public void change() {
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
        } else {
            this.controller.setStatusLine("Game Over! --> "
                    + this.controller.getPlayer().printPlayersHand());
            //System.exit(0);
        }
    }
}