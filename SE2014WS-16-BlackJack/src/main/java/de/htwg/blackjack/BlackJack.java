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
        controller.create();
        tui = new TUI(controller);
        tui.printTui();
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

        BlackJack.getInstance();

        // continue to read user input on the tui until the user decides to quit
        boolean continu = true;
        scanner = new Scanner(System.in);
        while (continu) {
            continu = tui.processInputLine(scanner.next());
        }
    }
}
