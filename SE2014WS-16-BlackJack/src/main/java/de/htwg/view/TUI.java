package de.htwg.view;

import de.htwg.controller.BlackJackController;
import de.htwg.controller.IBlackJackController;
import de.htwg.util.observer.Event;
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
    public boolean processInputLine(String input) {
        boolean continu = true;
        //Auf controller umbauen
        //Initialize player and dealer
        System.out.println("Bitte geben Sie ihren Namen ein: ");
        System.out.printf("-->: ");
        controller.setPlayer(SCANNER.next());
        controller.setDealer();
        //Initialize the number of decks
        System.out.println("Player: " + controller.getPlayer().getName());
        System.out.println("How many decks you want for playing BlackJack?");
        System.out.printf("-->: ");

        controller.setDeck(SCANNER.nextInt());

        System.out.println("-----------------------MENUE--"
                + "---------------------");
        System.out.print("1 -- HELP\n2 -- Play\n3 -- "
                + "Deal next card\n4 -- Quit Game\n");
        System.out.print("-->: ");
        int eingabe = SCANNER.nextInt();

        //Game Runner
        while (eingabe <= FOUR) {
            switch (eingabe) {
                case ONE:
                    System.out.print("1 -- HELP\n2 -- Play\n3 -- Deal "
                            + "next card\n4 -- Quit Game\n");
                    break;
                case TWO:
                    System.out.println("First two cards are dealt!");
                    System.out.print("Player --> ");
                    System.out.println(controller.getFirstTwoCardsPlayer());
                    System.out.print("Dealer --> ");
                    System.out.println(controller.getFirstTwoCardsDealer());
                    System.out.println();
                    controller.checkGameStatus();
                    break;
                case THREE:
                    System.out.println("Do you want one more card? [y/n]");
                    System.out.print("-->: ");
                    String eingabe2 = SCANNER.next();

                    if (eingabe2.equals("y")) {
                        System.out.print("Player --> ");
                        System.out.println(controller.getCardPlayer());
                        controller.checkIfDealerNeedsCard();
                        System.out.print("Dealer --> ");
                        System.out.println(controller.getDealer().printPlayersHand());
                        controller.checkGameStatus();
                    } else if (eingabe2.equals("n")) {
                        controller.checkIfDealerNeedsCard();
                        controller.checkGameStatus();
                        System.out.println(controller.getPlayer().printPlayersHand());
                        System.out.print("Dealer --> ");
                        System.out.println(controller.getDealer().printPlayersHand());
                        System.exit(0);
                    } else if (controller.hasBlackJack(controller.getDealer())) {
                        controller.checkGameStatus();
                    }

                    break;
                case FOUR:
                    System.out.println("END!");
                    System.exit(0);
                    break;
                default:
            }
            System.out.print("-->: ");
            eingabe = SCANNER.nextInt();
        }
        return continu;
    }

}
