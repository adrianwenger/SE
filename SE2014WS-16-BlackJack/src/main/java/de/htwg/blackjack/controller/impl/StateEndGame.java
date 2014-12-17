package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.aview.tui.Tui;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
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
     * calc controller.
     */
    private ICalcProfitController calcController;

    /**
     * Public Constructor.
     *
     * @param blackJackController IBlackJackController
     * @param cal ICalcProfitController
     */
    public StateEndGame(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     *
     */
    @Override
    public void change() {
        this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
        this.calcController.clacStake();
        this.controller.setStatusLine("Your total Stake: " + this.controller.getPlayer().getStake() + "€\n");
         this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
        this.controller.setStatusLine("END!\n");
        System.exit(0);
    }
}
