package de.htwg.blackjack.controller.impl;

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
     * calc controller.
     */
    private final ICalcProfitController calcController;

    /**
     * Public Constructor.
     *
     * @param blackJackController IBlackJackController
     * @param cal ICalcProfitController
     */
    public StateEndRound(final IBlackJackController blackJackController,
            final ICalcProfitController cal) {
        this.calcController = cal;
        this.controller = blackJackController;
    }

    /**
     * ask if Player want to run another round or want to end the game.
     */
    @Override
    public void change() {
        this.controller.setStatusLine("Do you want to start "
                + "a new round? [y/n]\n");
        this.controller.setStatusLine("\t-->\t");

        String eingabe = SCANNER.next();
        // Player want to continue a new round
        if (eingabe.equals("y")) {
            // start new round
            this.controller.createNewRound();
            //checks if player has enough money --> no depts!
            this.calcController.checkStake();
        // Player want to end Game
        } else {
            this.controller.setCurrentState(new StateEndGame(controller,
                    calcController));
            this.controller.getCurrentState().change();
        }
    }
}
