package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.BlackJack;
import de.htwg.blackjack.aview.tui.Tui;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.IGameState;
import java.util.Scanner;

/**
 * @author Adrian Wenger
 */
public final class StateEndGame implements IGameState {

    /**
     * define Scanner.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /**
     * BlackJack Controller.
     */
    private final IBlackJackController controller;
    /**
     * tui.
     */
    private Tui tui = null;

    /**
     * Public Constructor.
     *
     * @param blackJackController controller
     */
    public StateEndGame(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
        change();
    }

    /**
     *
     */
    @Override
    public void change() {
        this.controller.setStatusLine("Do you want to start a new round?\n");
        this.controller.setStatusLine("\t-->\t");

        String eingabe = SCANNER.next();
        if (eingabe.equals("y")) {
            this.controller.setCurrentState(new StateInGame(controller));
            this.controller.create();
//            tui = new Tui(controller);
//            tui.printTui();
        } else if (eingabe.equals("n")) {
            this.controller.endGame();
        }
    }
}
