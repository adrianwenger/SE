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
     * check if game is inGame.
     */
    private boolean stakeSet = false, roundStakeSet = false, deckSet = false,
            secondOpportunity = false;
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
                    + this.controller.getPlayer().printPlayersHand() + "\n\n");
            LOGGERTUI.info("Dealer Value --> "
                    + this.controller.getDealer().printPlayersHand() + "\n\n");
            // print Credit
            printCurrentCredit();
        } else if (this.controller.getCurrentState() instanceof StateLost) {
            LOGGERTUI.info("Round LOST!!! --> \n\n"
                    + this.controller.getPlayer().printPlayersHand() + "\n");
            LOGGERTUI.info(this.controller.getDealer().printPlayersHand()
                    + "\n\n");
            // print credit
            printCurrentCredit();
        } else if (this.controller.getCurrentState() instanceof StateBlackJack) {
            if (this.controller.hasBlackJack(this.controller.getDealer())) {
                LOGGERTUI.info("Dealer got BlackJack!\n\n");
                LOGGERTUI.info(this.controller.getPlayer().printPlayersHand()
                        + "\n\n");
            } else {
                LOGGERTUI.info("Congratulations "
                        + this.controller.getPlayer().getName()
                        + ", you got BlackJack!\n\n");
                LOGGERTUI.info(this.controller.getDealer().printPlayersHand()
                        + "\n\n");
            }
            //print credit
            printCurrentCredit();
        } else if (this.controller.getCurrentState() instanceof StateEndRound) {
            LOGGERTUI.info("Round ended\n");
            LOGGERTUI.info("Do you want to start a new Round?\n");
        } else if (this.controller.getCurrentState() instanceof StateEndGame) {
            LOGGERTUI.info("-----------------------------------"
                    + "---------------------\n");
            LOGGERTUI.info("Final Credit: "
                    + this.controller.getPlayer().getStake() + "€\n");
            LOGGERTUI.info("-----------------------------------"
                    + "---------------------\n");
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
                    this.controller.getPlayer()
                            .setStake(Integer.parseInt(nextLine));
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
                    this.controller.getPlayer()
                            .setRoundStake(Integer.parseInt(nextLine));
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
                            this.controller.getPlayer().doubleRoundStake();
                            LOGGERTUI.info("Round Stake doubled!\n");
                            LOGGERTUI.info("Round Stake: "
                                    + this.controller.getPlayer()
                                    .getRoundStake() + "€\n");
                        } else {
                            LOGGERTUI.info("Round Stake can't be"
                                    + " doubled. Not enough money on Stake!\n");
                            LOGGERTUI.info("Credit: "
                                    + (this.controller.getPlayer().getStake()
                                    - this.controller.getPlayer().
                                    getRoundStake()) + "€\n");
                        }
                        controller.checkGameState();
                        break;
                    case FOUR:
                        LOGGERTUI.info("Your current Credit:\n");
                        LOGGERTUI.info(INPUT
                                + (this.controller.getPlayer().getStake()
                                - this.controller.getPlayer().getRoundStake())
                                + "€\n");
                        LOGGERTUI.info("Your current"
                                + " Round Stake:\n");
                        LOGGERTUI.info(INPUT
                                + this.controller.getPlayer().getRoundStake()
                                + "€\n");
                        controller.checkGameState();
                        break;
                    case FIVE:
                        startNewRound();
                        break;
                    default:
                        break;
                }
            } else {
                if (nextLine.equals("y")) {
                    controller.getCardPlayer();
                    controller.checkIfDealerNeedsCard();
                    controller.checkGameState();
                } else if (nextLine.equals("n")) {
                    controller.checkIfDealerNeedsCard();
                    controller.checkGameState();
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
            } else {
                endGame();
            }
            controller.checkGameState();
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
        roundStakeSet = false;
        deckSet = false;
        secondOpportunity = false;
        this.controller.createNewRound();
    }

    /**
     * prints current Credit.
     */
    private void printCurrentCredit() {
        calcController.calcProfit();
        LOGGERTUI.info("-----------------------------------"
                + "---------------------\n");
        LOGGERTUI.info("Your profit: "
                + calcController.getProfit() + "€\n");
        calcController.calcStake();
        LOGGERTUI.info("Your new Credit: "
                + this.controller.getPlayer().getStake() + "€\n");
        LOGGERTUI.info("-----------------------------------"
                + "---------------------\n");
    }

    private void endGame() {
        LOGGERTUI.info("Thanks for Playing BlackJack. See you soon...");
        System.exit(0);
    }

}
