package de.htwg.blackjack;

import de.htwg.controller.BlackJackController;
import de.htwg.controller.IBlackJackController;
import de.htwg.aview.TUI;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger
 */
public final class BlackJack {
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
    private BlackJack() {
        controller = new BlackJackController();
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
        new BlackJack();
        //Starts the TextUserInterface
        tui.createGame();
        }
}
