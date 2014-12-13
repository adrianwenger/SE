package de.htwg.controller.impl;

import de.htwg.controller.IBlackJackController;
import de.htwg.controller.IGameState;

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
            this.controller.setStatusLine("Dealer got BlackJack!");
        } else {
            this.controller.setStatusLine("Congratulations "
                    + this.controller.getPlayer().getName()
                    + ", you got BlackJack!");
        }
        //System.exit(0);
    }
}
