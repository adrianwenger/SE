package de.htwg.controller;

/**
 *
 * @author Adrian Wenger
 */
public class StateWon implements IGameState {

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

    /**
     *
     */
    public final void change() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
