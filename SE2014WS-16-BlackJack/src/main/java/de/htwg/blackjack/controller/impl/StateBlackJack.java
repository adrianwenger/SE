package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
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
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateBlackJack(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
        change();
    }

    /**
     * now other State available.
     */
    @Override
    public void change() {
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            this.controller.setStatusLine("Dealer got BlackJack!\n");
        } else {
            this.controller.setStatusLine("Congratulations "
                    + this.controller.getPlayer().getName()
                    + ", you got BlackJack!\n");
        }
        //System.exit(0);
    }
}
