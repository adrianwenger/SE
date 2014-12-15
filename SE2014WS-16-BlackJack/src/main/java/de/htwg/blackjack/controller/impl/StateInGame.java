package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.IGameState;

/**
 *
 * @author Adrian Wenger
 */
public final class StateInGame implements IGameState {

    /**
     * BlackJack Value 21.
     */
    private static final int BLACKJACK = 21;

    /**
     * IBlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param cont IBlackJackController
     */
    public StateInGame(final IBlackJackController cont) {
        this.controller = cont;
        change();
    }

    /**
     * change GameState if nessecary.
     */
    @Override
    public void change() {
        // Player won (Player < BlackJack && Dealer > BlackJack)
        if ((this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() > BLACKJACK)) {
            this.controller.setCurrentState(new StateWon(controller));
            // game will move on (both < 21)
        } else if (this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() < BLACKJACK) {
            this.controller.setStatusLine("Please take another card (2) or "
                    + "finish game (3)\n");
            // Player has BlackJack
        } else if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
        } else {
            // Player lost Dealer reached BlackJack or just won
            this.controller.setCurrentState(new StateLost(controller));
        }
    }
}
