package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
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
            this.controller.setStatusLine("Game Over!\n"
                    + this.controller.getPlayer().printPlayersHand() + "\n\n");
            // change state to StateEndGame
            this.controller.setCurrentState(new StateEndGame(controller));
        }
    }
}