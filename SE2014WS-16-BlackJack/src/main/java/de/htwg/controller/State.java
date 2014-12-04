package de.htwg.controller;

/**
 * this class is used to implement the stateInGame.
 * @author Adi
 */
public class State implements IGameState {
    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Contructor.
     * @param cont IBlackJackController
     */
    public State(final IBlackJackController cont) {
        this.controller = cont;
        change();
    }

    /**
     * implement the inGameState.
     */
    public final void change() {
        controller.setCurrentState(new StateInGame(controller));
    }
}

/**
 *
 * @author Adrian Wenger
 */
class StateInGame implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * BlackJack Value.
     */
    private static final int BLACKJACK = 21;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateInGame(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
    }

    /**
     * change GameState if nessecary.
     */
    public final void change() {
//        if (this.controller.hasBlackJack(this.controller.getDealer())
//                || this.controller.getPlayer().getValue() > BLACKJACK) {
//            this.controller.setCurrentState(new StateLost(controller));
//        }
    }
}

/**
 * @author Adrian Wenger
 */
class StateLost implements IGameState {

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
    }

    public void change() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/**
 *
 * @author Adrian Wenger
 */
class StateWon implements IGameState {

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
    }

    public void change() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

/**
 *
 * @author Adrian Wenger
 */
class StateBlackJack implements IGameState {

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
    }

    public void change() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
