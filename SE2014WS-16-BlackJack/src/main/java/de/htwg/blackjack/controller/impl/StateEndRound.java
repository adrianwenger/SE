package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.aview.tui.Tui;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.IGameState;
import java.util.Scanner;

/**
 * @author Adrian Wenger
 */
public final class StateEndRound implements IGameState {

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
     * calc controller.
     */
    private final ICalcProfitController calcController;

    /**
     * Public Constructor.
     *
     * @param blackJackController IBlackJackController
     * @param cal  ICalcProfitController
     */
    public StateEndRound(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     *
     */
    @Override
    public void change() {
        this.controller.setStatusLine("Do you want to start "
                + "a new round? [y/n]\n");
        this.controller.setStatusLine("\t-->\t");

        String eingabe = SCANNER.next();
        if (eingabe.equals("y")) {
            // start new round
            this.controller.create();
            tui = new Tui(controller);
            tui.printTui();
            this.tui.createGame();
            this.tui.continueGame();
        } else if (eingabe.equals("n")) {
            this.controller.setCurrentState(new StateEndGame(controller,
                    calcController));
        }
    }
}
