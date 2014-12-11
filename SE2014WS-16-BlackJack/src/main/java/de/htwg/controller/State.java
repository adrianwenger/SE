package de.htwg.controller;

/**
 * this class is used to implement the stateInGame.
 *
 * @author Adi
 */
public final class State { }

/**
 *
 * @author Adrian Wenger
 */
final class StateInGame implements IGameState {

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
                    + "finish game (3)");
        } else {
            // Player lost BlackJack reached
            this.controller.setCurrentState(new StateLost(controller));
        }
    }
}

/**
 * @author Adrian Wenger
 */
final class StateLost implements IGameState {

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
            System.exit(0);
        }
    }
}

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
            System.exit(0);
        }
    }
}

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
        System.exit(0);
    }
}
