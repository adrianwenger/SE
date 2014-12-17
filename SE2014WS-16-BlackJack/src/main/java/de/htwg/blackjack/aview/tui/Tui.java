package de.htwg.blackjack.aview.tui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
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
     * StakeController.
     */
    private final ICalcProfitController calcController;


    /**
     * Constructor.
     *
     * @param cont controller
     */
    public Tui(final IBlackJackController cont) {
        this.controller = cont;
        this.calcController = cont.getCalcController();
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
     * FIVE.
     */
    private static final int FIVE = 5;
    /**
     * -->.
     */
    private static final String INPUT = "\t-->\t";

    /**
     * initialize the game.
     */
    public void initialize() {
        //Initialize player and dealer
        this.controller.setStatusLine("\nInsert Name\n");
        this.controller.setStatusLine(INPUT);
        this.controller.setPlayer(SCANNER.next());
        this.controller.setDealer();
        //Set STAKE
        this.controller.setStatusLine("With how much effort you want to "
                + "start the game?\n");
        this.controller.setStatusLine(INPUT);
        this.controller.getPlayer().setStake(SCANNER.nextDouble());
    }

    /**
     * create the game.
     */
    public void createGame() {
        //Initialize the number of decks
        this.controller.setStatusLine("How many decks you "
                + "want for playing BlackJack?\n");
        this.controller.setStatusLine(INPUT);
        this.controller.setDeck(SCANNER.nextInt());
        //ROUND STAKE
        this.controller.setStatusLine("Your round stake:\n");
        this.controller.setStatusLine(INPUT);
        this.controller.getPlayer().setRoundStake(SCANNER.nextDouble());
        //DEAL FIRST TWO CARDS
        this.controller.setStatusLine("First two dealt cards:\n\n");
        this.controller.setStatusLine(controller.getPlayer().getName() + ": ");
        this.controller.setStatusLine(this.controller.getFirstTwoCardsPlayer()
                + "\n");
        this.controller.setStatusLine("Dealer: ");
        this.controller.setStatusLine(this.controller.getFirstTwoCardsDealer()
                + "\n\n");
        //this.controller.checkIfDealerNeedsCard();
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
        while (eingabe > 4) {
            printHelpMenu();
            eingabe = SCANNER.nextInt();
        }
        //Game Runner
        while (eingabe <= FIVE) {
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
                    if(this.calcController.checkDouble()){
                        this.controller.getPlayer().doubleRoundStake();
                        this.controller.setStatusLine("Stake doubled!\n");
                        this.controller.setStatusLine("Round Stake: " + this.controller.getPlayer().getRoundStake() + "€\n");
                    } else {
                        this.controller.setStatusLine("Round Stake can't be doubled. Not enough money on Stake!\n");
                        this.controller.setStatusLine("Total Stake: " + (this.controller.getPlayer().getStake() - this.controller.getPlayer().getRoundStake()) + "€\n");
                    }
                    break;
                case FOUR:
                    this.controller.setStatusLine("Your current Stake:\n");
                    this.controller.setStatusLine(INPUT + (this.controller.getPlayer().getStake() - this.controller.getPlayer().getRoundStake()) +"€\n");
                    this.controller.setStatusLine("Your current Round Stake:\n");
                    this.controller.setStatusLine(INPUT + this.controller.getPlayer().getRoundStake() +"€\n");
                    break;
                case FIVE:
                    controller.endGame();
                    controller.setStatusLine("END!\n");
                    System.exit(0);
                    break;
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
                + "-- Double Stake" + "\n4 -- Current Stake" + "\n5 -- Quit and resolve\n");
    }
}
