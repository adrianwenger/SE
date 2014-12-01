package de.htwg.controller;

/**
 *
 * @author Adrian Wenger
 */
public class StateInGame implements IGameState {

    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateInGame(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
    }

    /**
     *
     */
    public final void change() {
        throw new UnsupportedOperationException("Menthode nicht benötigt");
        //controller.setStatusLine(controller.getPlayer().getName() +
        //" Welcome to BlackJack");
    }

}
