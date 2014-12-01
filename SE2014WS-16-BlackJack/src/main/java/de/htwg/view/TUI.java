package de.htwg.view;

import de.htwg.controller.IBlackJackController;
import de.htwg.util.observer.IObserver;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
public final class TUI implements IObserver {

    private final IBlackJackController controller;

    public TUI(IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    public void printTui() {
        System.out.println(controller.output());
    }

    public void update() {
        printTui();
    }

    /**
     * define Scanner.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /**
     *
     */
    private static final int ONE = 1;

    /**
     *
     */
    private static final int TWO = 2;
    /**
     *
     */
    private static final int THREE = 3;
    /**
     *
     */
    private static final int FOUR = 4;

    /**
     *
     * @param input String
     * @return boolean
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
                    controller.setStatusLine("Do you want one more card? [y/n]");
                    controller.setStatusLine("-->: ");
                    String eingabe2 = SCANNER.next();

                    if (eingabe2.equals("y")) {
                        controller.setStatusLine("Player --> ");
                        controller.setStatusLine(controller.getCardPlayer());
                        controller.checkIfDealerNeedsCard();
                        controller.setStatusLine("Dealer --> ");
                        controller.setStatusLine(controller.getDealer().printPlayersHand());
                        controller.checkGameStatus();
                    } else if (eingabe2.equals("n")) {
                        controller.checkIfDealerNeedsCard();
                        controller.checkGameStatus();
                        controller.setStatusLine(controller.getPlayer().printPlayersHand());
                        controller.setStatusLine("Dealer --> ");
                        controller.setStatusLine(controller.getDealer().printPlayersHand());
                        System.exit(0);
                    } else if (controller.hasBlackJack(controller.getDealer())) {
                        controller.checkGameStatus();
                    }

                    break;
                case THREE:
                    controller.setStatusLine("END!");
                    System.exit(0);
                    break;
                default:
            }
            controller.setStatusLine("-->: ");
            eingabe = SCANNER.nextInt();
        }
    }

}
