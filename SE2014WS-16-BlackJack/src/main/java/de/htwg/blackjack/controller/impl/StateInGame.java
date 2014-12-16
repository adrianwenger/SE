package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.IGameState;
import static de.htwg.blackjack.util.StaticCollections.BLACKJACK;

/**
 *
 * @author Adrian Wenger
 */
public final class StateInGame implements IGameState {

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
            this.controller.getCurrentState().change();
            // game will move on (both < 21)
        } else if (this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() < BLACKJACK) {
            this.controller.setStatusLine("Please take another card (2) or "
                    + "finish game (3)\n");
            // Player has BlackJack
        } else if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller));
            this.controller.getCurrentState().change();
        } else {
            // Player lost Dealer reached BlackJack or just won
            this.controller.setCurrentState(new StateLost(controller));
            this.controller.getCurrentState().change();
        }
    }
}
