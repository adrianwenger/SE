package de.htwg.blackjack.aview.tui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.util.observer.IObserver;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
public final class Tui implements IObserver {

    /**
     * controller.
     */
    private final IBlackJackController controller;

    /**
     * Constructor.
     *
     * @param cont controller
     */
    public Tui(final IBlackJackController cont) {
        this.controller = cont;
        controller.addObserver(this);
    }

    /**
     * "refresh" the screen.
     */
    public void printTui() {
        System.out.print(controller.output());
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
     * -->.
     */
    private static final String INPUT = "\t-->\t";

    /**
     * create the game.
     */
    public void createGame() {
        //Initialize player and dealer
        this.controller.setStatusLine("\nBitte geben Sie ihren Namen ein:\n");
        this.controller.setStatusLine(INPUT);
        this.controller.setPlayer(SCANNER.next());
        this.controller.setDealer();

        //Initialize the number of decks
//        this.controller.setStatusLine("Player: "
//                + this.controller.getPlayer().getName() +"\n");
        this.controller.setStatusLine("How many decks you "
                + "want for playing BlackJack?\n");
        this.controller.setStatusLine(INPUT);
        this.controller.setDeck(SCANNER.nextInt());
        //DEAL FIRST TWO CARDS
        this.controller.setStatusLine("First two dealt cards:\n\n");
        this.controller.setStatusLine(controller.getPlayer().getName() + ": ");
        this.controller.setStatusLine(this.controller.getFirstTwoCardsPlayer() 
                + "\n");
        this.controller.setStatusLine("Dealer: ");
        this.controller.setStatusLine(this.controller.getFirstTwoCardsDealer() 
                + "\n\n");
        this.controller.checkIfDealerNeedsCard();
        this.controller.checkGameState();
        //print MENUE
        printHelpMenu();
    }

    /**
     * uses the controller to save data in model layers. prints returned values
     * from controller.
     */
    public void continueGame() {
        controller.setStatusLine(INPUT);
        int eingabe = SCANNER.nextInt();

        //Game Runner
        while (eingabe <= FOUR) {
            switch (eingabe) {
                case ONE:
                    printHelpMenu();
                    break;
                case TWO:
                    controller.setStatusLine("One more card? [y/n]\n");
                    controller.setStatusLine(INPUT);
                    String eingabe2 = SCANNER.next();

                    if (eingabe2.equals("y")) {
                        controller.setStatusLine(controller.getPlayer().getName() 
                                + ": ");
                        controller.setStatusLine(controller.getCardPlayer() 
                                + "\n");
                        controller.checkIfDealerNeedsCard();
                        controller.setStatusLine("Dealer: ");
                        controller.setStatusLine(controller.getDealer()
                                .printPlayersHand() + "\n\n");
                        controller.checkGameState();
                    } else if (eingabe2.equals("n")) {
                        controller.checkIfDealerNeedsCard();
                        controller.setStatusLine(controller.getPlayer().getName() 
                                + ": ");
                        controller.setStatusLine(controller.getPlayer()
                                .printPlayersHand() + "\n");
                        controller.setStatusLine("Dealer: ");
                        controller.setStatusLine(controller.getDealer()
                                .printPlayersHand() + "\n\n");
                        controller.checkGameState();
                    } else if (controller.hasBlackJack(controller.getDealer())) {
                        controller.checkGameState();
                    }
                    break;
                case THREE:
                    controller.endGame();
                    controller.setStatusLine("END!\n");
                    System.exit(0);
                default:
            }
            printHelpMenu();
            controller.setStatusLine(INPUT);
            eingabe = SCANNER.nextInt();
        }
    }

    /**
     * print the tui menu.
     */
    public void printHelpMenu() {
        this.controller.setStatusLine("----------------------- MENUE --"
                + "---------------------\n");
        this.controller.setStatusLine("1 -- HELP\n2 -- Next card \n3 "
                + "-- Quit Game\n");
    }
}
