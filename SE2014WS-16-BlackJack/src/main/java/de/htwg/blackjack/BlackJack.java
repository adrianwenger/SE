package de.htwg.blackjack;

import de.htwg.controller.BlackJackController;
import de.htwg.controller.IBlackJackController;
import de.htwg.aview.TUI;

/**
 *
 * @author Adrian Wenger
 */
public final class BlackJack {
    /**
     * tui.
     */
    private TUI tui;
    /**
     * static BlackJack instance.
     */
    private static BlackJack instance = null;

    /**
     * Singleton.
     *
     * @return instance
     */
    public static BlackJack getInstance() {
        if (instance == null) {
            instance = new BlackJack();
        }
        return instance;

    }

    /**
     * @return tui
     */
    public TUI getTui() {
        return tui;
    }

    /**
     *
     */
    private BlackJack() {
        IBlackJackController controller = new BlackJackController();
        tui = new TUI(controller);
        controller.create();
        tui.printTui();
    }

    /**
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        //Create new BlackJack Object
        BlackJack game = BlackJack.getInstance();
        //Starts the TextUserInterface
        game.getTui().createGame();
        game.getTui().continueGame();
    }
}
