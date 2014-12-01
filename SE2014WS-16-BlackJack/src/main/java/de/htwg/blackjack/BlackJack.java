package de.htwg.blackjack;

import de.htwg.controller.BlackJackController;
import de.htwg.controller.IBlackJackController;
import de.htwg.view.TUI;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger
 */
public final class BlackJack {

    /**
     *
     */
    private static Scanner scanner;
    /**
     *
     */
    private final IBlackJackController controller;
    /**
     *
     */
    private static TUI tui;
    /**
     *
     */
    private static BlackJack instance = null;

    /**
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
     *
     */
    private BlackJack() {
        controller = new BlackJackController();
        tui = new TUI(controller);
        tui.printTui();
        controller.create();
    }

    /**
     *
     * @return controller
     */
    public IBlackJackController getController() {
        return controller;
    }

    /**
     *
     * @return tui
     */
    public TUI getTUI() {
        return tui;
    }
    /**
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        //Create new BlackJack Object
        BlackJack.getInstance();
        //Starts the TextUserInterface
        tui.processInputLine();
        }
}
