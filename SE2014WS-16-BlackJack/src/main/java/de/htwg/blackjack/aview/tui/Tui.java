package de.htwg.blackjack.aview.tui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.impl.StateBlackJack;
import de.htwg.blackjack.controller.impl.StateEndGame;
import de.htwg.blackjack.controller.impl.StateEndRound;
import de.htwg.blackjack.controller.impl.StateInGame;
import de.htwg.blackjack.controller.impl.StateLost;
import de.htwg.blackjack.controller.impl.StateWon;
import de.htwg.blackjack.util.observer.IObserver;
import org.apache.log4j.Logger;


/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
public final class Tui implements IObserver {


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
     * \n\n.
     */
    private static final String NEWLINE = "\n\n";
    /**
     *
     */
    private static final String EURO = "€\n";
    /**
     * check if game is inGame.
     */
    private boolean stakeSet = false, deckSet = false;
    /**
     * logger.
     */
    private static final Logger LOGGERTUI
            = Logger.getLogger("de.htwg.blackjack.aview.tui");
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
    @Inject
    public Tui(final IBlackJackController cont) {
        this.controller = cont;
        this.calcController = cont.getCalcController();
        controller.addObserver(this);
        LOGGERTUI.info("\nInsert Name\n");
    }

    /**
     * "refresh" the screen.
     */
    public void printTui() {
        LOGGERTUI.info(controller.getStatusLine());
    }

    /**
     * implement method update from IObserver.
     */
    @Override
    public void update() {
        if (this.controller.getCurrentState() instanceof StateInGame) {
            LOGGERTUI.info(this.controller.getPlayer().printPlayersHand());
            LOGGERTUI.info(this.controller.getDealer().printPlayersHand());
            //print MENUE
            printHelpMenu();
        } else if (this.controller.getCurrentState() instanceof StateWon) {
            LOGGERTUI.info("Round WON!!! -->  "
                    + this.controller.getPlayer().printPlayersHand() + NEWLINE);
            LOGGERTUI.info("Dealer Value --> "
                    + this.controller.getDealer().printPlayersHand() + NEWLINE);
            // print Credit
            LOGGERTUI.info(this.calcController.printCurrentCreditState());
        } else if (this.controller.getCurrentState() instanceof StateLost) {
            LOGGERTUI.info("Round LOST!!! " + NEWLINE + " --> "
                    + this.controller.getPlayer().printPlayersHand() + "\n");
            LOGGERTUI.info(this.controller.getDealer().printPlayersHand()
                    + NEWLINE);
            // print credit
            LOGGERTUI.info(this.calcController.printCurrentCreditState());
        } else if (this.controller.getCurrentState() instanceof StateBlackJack) {
            if (this.controller.hasBlackJack(this.controller.getDealer())) {
                LOGGERTUI.info("Dealer got BlackJack!" + NEWLINE);
                LOGGERTUI.info(this.controller.getPlayer().printPlayersHand()
                        + NEWLINE);
            } else {
                LOGGERTUI.info("Congratulations "
                        + this.controller.getPlayer().getName()
                        + ", you got BlackJack!" + NEWLINE);
                LOGGERTUI.info(this.controller.getDealer().printPlayersHand()
                        + NEWLINE);
            }
        } else if (this.controller.getCurrentState() instanceof StateEndRound) {
            LOGGERTUI.info("Round ended\n");
            LOGGERTUI.info("Do you want to start a new Round?\n");
        } else if (this.controller.getCurrentState() instanceof StateEndGame) {
            LOGGERTUI.info(calcController.printCurrentCreditState());
            LOGGERTUI.info("END!\n");
        }
    }

    /**
     * processes input from BlackJack.
     *
     * @param nextLine input String
     */
    public void processInputLine(final String nextLine) {
        // if state null (game just started) setStake or set Player, depending
        // on input.
        if (this.controller.getCurrentState() == null) {
            if (isInt(nextLine)) {
                if (!stakeSet) {
                    calcController.setStake(Integer.parseInt(nextLine));
                    stakeSet = true;
                    //Initialize the number of decks
                    LOGGERTUI.info("How many decks you "
                            + "want for playing BlackJack?\n");
                } else if (!deckSet) {
                    this.controller.setDeck(Integer.parseInt(nextLine));
                    deckSet = true;
                    //ROUND STAKE
                    LOGGERTUI.info("Your round stake:\n");
                } else {
                    calcController.setRoundStake(Integer.parseInt(nextLine));
                    LOGGERTUI.info("First two Cards are dealt -->  \n");
                    //DEAL FIRST TWO CARDS
                    this.controller.getFirstTwoCardsPlayer();
                    this.controller.getFirstTwoCardsDealer();
                    this.controller.checkGameState();
                }
            } else {
                this.controller.setPlayer(nextLine);
                this.controller.setDealer();
                LOGGERTUI.info("With how much effort you want to "
                        + "start the game?\n");
            }
        } else if (this.controller.getCurrentState() instanceof StateInGame) {
            if (isInt(nextLine)) {
                switch (Integer.parseInt(nextLine)) {
                    case ONE:
                        printHelpMenu();
                        break;
                    case TWO:
                        LOGGERTUI.info("One more card? [y/n]" + INPUT);
                        break;
                    case THREE:
                        if (this.calcController.checkDouble()) {
                            calcController.doubleRoundStake();
                            LOGGERTUI.info("Round Stake doubled!\n");
                            LOGGERTUI.info("Round Stake: "
                                    + this.controller.getPlayer()
                                    .getRoundStake() + EURO);
                        } else {
                            LOGGERTUI.info("Round Stake can't be"
                                    + " doubled. Not enough money on Stake!\n");
                            LOGGERTUI.info("Credit: "
                                    + calcController.printCurrentCreditState());
                        }
                        controller.checkGameState();
                        break;
                    case FOUR:
                        LOGGERTUI.info("Your current Credit:\n");
                        LOGGERTUI.info(INPUT
                                + this.controller.getPlayer().getStake()
                                + EURO);
                        LOGGERTUI.info("Your current"
                                + " Round Stake:\n");
                        LOGGERTUI.info(INPUT
                                + this.controller.getPlayer().getRoundStake()
                                + EURO);
                        printHelpMenu();
                        break;
                    case FIVE:
                        this.controller.setCurrentState(
                                new StateEndGame(controller));
                        this.controller.getCurrentState().change();
                        System.exit(0);
                        break;
                    default:
                        break;
                }
            } else {
                if (nextLine.equals("y")) {
                    controller.getCardPlayer();
                    controller.checkIfDealerNeedsCard();
                    controller.checkGameState();
                    if (this.controller.getCurrentState()
                            instanceof StateInGame) {
                        this.controller.notifyObservers();
                    }
                } else if (nextLine.equals("n")) {
                    controller.checkIfDealerNeedsCard();
                    controller.setCurrentState(new StateEndGame(controller));
                    controller.getCurrentState().change();
                    System.exit(0);
                }
            }
        } else if (controller.getCurrentState() instanceof StateWon) {
            controller.checkGameState();
        } else if (controller.getCurrentState() instanceof StateLost) {
            controller.checkGameState();
        } else if (controller.getCurrentState() instanceof StateBlackJack) {
            controller.checkGameState();
        } else if (controller.getCurrentState() instanceof StateEndRound) {
            if (nextLine.equals("y")) {
                startNewRound();
                LOGGERTUI.info("a new round is created");
                printHelpMenu();
            } else {
                this.controller.setCurrentState(new StateEndGame(controller));
                this.controller.getCurrentState().change();
                System.exit(0);
            }
        } else {
            controller.checkGameState();
        }

    }

    /**
     * validate if line is numeric or String.
     *
     * @param line line
     * @return boolean
     */
    public boolean isInt(final String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * print the tui menu.
     */
    public void printHelpMenu() {
        LOGGERTUI.info("\n\"------- MENUE -------\"" + "\n1 -- HELP\n2 "
                + "-- Next card \n3 -- Double Stake" + "\n4 -- Current Stake"
                + "\n5 -- Quit and Resolve\n");
    }

    /**
     * start a new Round.
     */
    public void startNewRound() {
        stakeSet = false;
        deckSet = false;
        this.controller.createNewRound();
    }

}
