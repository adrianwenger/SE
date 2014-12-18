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
     * BlackJack Controller.
     */
    private final IBlackJackController controller;

    /**
     * Public Constructor.
     *
     * @param blackJackController IBlackJackController
     */
    public StateEndGame(final IBlackJackController blackJackController) {
        this.controller = blackJackController;
    }

    /**
     *
     */
    @Override
    public void change() {
        this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
        this.controller.setStatusLine("Your final Credit: " + this.controller.getPlayer().getStake() + "€\n");
         this.controller.setStatusLine("-----------------------------------"
                + "---------------------\n");
        this.controller.setStatusLine("END!\n");
        System.exit(0);
    }
}
