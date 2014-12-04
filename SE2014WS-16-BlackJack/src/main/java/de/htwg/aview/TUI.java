package de.htwg.aview;

import de.htwg.controller.IBlackJackController;
import de.htwg.util.observer.IObserver;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
public final class TUI implements IObserver {

    /**
     * controller.
     */
    private final IBlackJackController controller;

    /**
     * Constructor.
     *
     * @param cont controller
     */
    public TUI(final IBlackJackController cont) {
        this.controller = cont;
        controller.addObserver(this);
    }

    /**
     * "refresh" the screen.
     */
    public void printTui() {
        System.out.println(controller.output());
    }

    /**
     * implement method update from IObserver.
     */
    @Override
    public void update() {
        printTui();
    }

    /**
     * define Scanner.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /**
     * ONE.
     */
    private static final int ONE = 1;

    /**
     * TWO.
     */
    private static final int TWO = 2;
    /**
     * THREE.
     */
    private static final int THREE = 3;
    /**
     * FOUR.
     */
    private static final int FOUR = 4;

    /**
     * uses the controller to save data in model layers. prints returned values
     * from controller.
     */
    public void processInputLine() {
        controller.setStatusLine("-->: ");
        int eingabe = SCANNER.nextInt();

        //Game Runner
        while (eingabe <= FOUR) {
            switch (eingabe) {
                case ONE:
                    controller.setStatusLine("1 -- HELP\n2 -- Next card \n3 "
                            + "-- Quit Game\n");
                    break;
                case TWO:
                    controller.setStatusLine("One more card? [y/n]");
                    controller.setStatusLine("-->: ");
                    String eingabe2 = SCANNER.next();

                    if (eingabe2.equals("y")) {
                        controller.setStatusLine("Player --> ");
                        controller.setStatusLine(controller.getCardPlayer());
                        controller.checkIfDealerNeedsCard();
                        controller.setStatusLine("Dealer --> ");
                        controller.setStatusLine(controller.getDealer()
                                .printPlayersHand());
                        controller.checkGameStatus();
                    } else if (eingabe2.equals("n")) {
                        controller.checkIfDealerNeedsCard();
                        controller.checkGameStatus();
                        controller.setStatusLine(controller.getPlayer()
                                .printPlayersHand());
                        controller.setStatusLine("Dealer --> ");
                        controller.setStatusLine(controller.getDealer()
                                .printPlayersHand());
                        System.exit(0);
                    } else if (controller.hasBlackJack(controller.getDealer())) {
                        controller.checkGameStatus();
                    }
                    break;
                case THREE:
                    controller.setStatusLine("END!");
                    System.exit(0);
            }
            controller.setStatusLine("-->: ");
            eingabe = SCANNER.nextInt();
        }
    }

}
