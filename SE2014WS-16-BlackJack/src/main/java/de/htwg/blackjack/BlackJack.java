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
    private BlackJack() {
        controller = new BlackJackController();
        tui = new TUI(controller);
        controller.create();
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
        //Create new BlackJack Object
        new BlackJack();
        //Starts the TextUserInterface
        tui.processInputLine();
        }
}
