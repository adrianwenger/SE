package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
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
     * calc controller.
     */
    private final ICalcProfitController calcController;

    /**
     * Public Constructor.
     *
     * @param blackJackController IBlackJackController
     * @param cal ICalcProfitController
     */
    public StateInGame(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     * change GameState if nessecary. Different Cases: 1. Player won (Player <
     * BlackJack && Dealer > BlackJack) 2. Game will move on (both < 21) 3.
     * Player has BlackJack 4. Player lost Dealer reached BlackJack or just won
     */
    @Override
    public void change() {
        // 1. Player won (Player < BlackJack && Dealer > BlackJack)
        if ((this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() > BLACKJACK)) {
            this.controller.setCurrentState(new StateWon(controller,
                    calcController));
            this.controller.getCurrentState().change();
            // 2. Game will move on in StateInGame
        } else if (this.controller.getPlayer().getValue() < BLACKJACK
                && this.controller.getDealer().getValue() < BLACKJACK) {
            //this.controller.setCurrentState(this);
            // 3. Player has BlackJack
        } else if (this.controller.hasBlackJack(this.controller.getPlayer())) {
            this.controller.setCurrentState(new StateBlackJack(controller,
                    calcController));
            this.controller.getCurrentState().change();
            // 4. Player lost Dealer reached BlackJack or just won
        } else {
            this.controller.setCurrentState(new StateLost(controller,
                    calcController));
            this.controller.getCurrentState().change();
        }
    }
}
