package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.IGameState;

/**
 * @author Adrian Wenger
 */
public final class StateEndGame implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     * @param controller 
     */
    public StateEndGame(final IBlackJackController controller) {
        this.controller = controller;
    }

    /**
     *
     */
    @Override
    public void change() {
    }
}
