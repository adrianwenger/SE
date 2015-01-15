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
    private static final Logger loggerTui
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
        //controller.setTui(this);
        loggerTui.info("\nInsert Name\n");
    }

    /**
     * "refresh" the screen.
     */
    public void printTui() {
        loggerTui.info(controller.getStatusLine());
    }

    /**
     * implement method update from IObserver.
     */
    @Override
    public void update() {
        if (this.controller.getCurrentState() instanceof StateInGame) {
            loggerTui.info(controller.getPlayer().getName() + ": ");
            loggerTui.info(this.controller.getPlayer().printPlayersHand());
            loggerTui.info("Dealer: ");
            loggerTui.info(this.controller.getDealer().printPlayersHand());
            //print MENUE
            printHelpMenu();
        } else if (this.controller.getCurrentState() instanceof StateWon) {
            loggerTui.info("Round WON!!! -->  "
                    + this.controller.getPlayer().printPlayersHand() + "\n\n");
             loggerTui.info("Dealer Value --> "
                    + this.controller.getDealer().printPlayersHand() + "\n\n");
            // print Credit
            printCurrentCredit();
        } else if (this.controller.getCurrentState() instanceof StateLost) {
            loggerTui.info("Round LOST!!!\n\n");
            // print credit
            printCurrentCredit();
        } else if (this.controller.getCurrentState() instanceof StateBlackJack) {
            if (this.controller.hasBlackJack(this.controller.getDealer())) {
                loggerTui.info("Dealer got BlackJack!\n\n");
            } else {
                loggerTui.info("Congratulations "
                        + this.controller.getPlayer().getName()
                        + ", you got BlackJack!\n\n");
            }
            //print credit
            printCurrentCredit();
        } else if (this.controller.getCurrentState() instanceof StateEndRound) {
            loggerTui.info("Round ended\n");
            loggerTui.info("Do you want to start a new Round?\n");
        } else if (this.controller.getCurrentState() instanceof StateEndGame) {
             loggerTui.info("-----------------------------------"
                    + "---------------------\n");
            loggerTui.info("Final Credit: "
                    + this.controller.getPlayer().getStake() + "€\n");
            loggerTui.info("-----------------------------------"
                    + "---------------------\n");
           loggerTui.info("END!\n");
        }
    }

    public void processInputLine(String nextLine) {
        // if state null (game just started) setStake or set Player, depending
        // on input.
        if (this.controller.getCurrentState() == null) {
            if (isInt(nextLine)) {
                if (!stakeSet) {
                    this.controller.getPlayer().setStake(Integer.parseInt(nextLine));
                    stakeSet = true;
                    //Initialize the number of decks
                    loggerTui.info("How many decks you "
                            + "want for playing BlackJack?\n");
                } else if (!deckSet) {
                    this.controller.setDeck(Integer.parseInt(nextLine));
                    deckSet = true;
                    //ROUND STAKE
                    loggerTui.info("Your round stake:\n");
                } else {
                    this.controller.getPlayer().setRoundStake(Integer.parseInt(nextLine));
                    //DEAL FIRST TWO CARDS
                    this.controller.getFirstTwoCardsPlayer();
                    this.controller.getFirstTwoCardsDealer();
                    this.controller.checkGameState();
                }
            } else {
                this.controller.setPlayer(nextLine);
                this.controller.setDealer();
                loggerTui.info("With how much effort you want to "
                        + "start the game?\n");
            }
        } else if (this.controller.getCurrentState() instanceof StateInGame) {
            if (isInt(nextLine)) {
                switch (Integer.parseInt(nextLine)) {
                    case ONE:
                        printHelpMenu();
                        break;
                    case TWO:
                        loggerTui.info("One more card? [y/n]\n");
                        loggerTui.info(INPUT);
                        break;
                    case THREE:
                        if (this.calcController.checkDouble()) {
                            this.controller.getPlayer().doubleRoundStake();
                            loggerTui.info("Round Stake doubled!\n");
                            loggerTui.info("Round Stake: "
                                    + this.controller.getPlayer().getRoundStake()
                                    + "€\n");
                        } else {
                            loggerTui.info("Round Stake can't be"
                                    + " doubled. Not enough money on Stake!\n");
                            loggerTui.info("Credit: "
                                    + (this.controller.getPlayer().getStake()
                                    - this.controller.getPlayer().
                                    getRoundStake()) + "€\n");
                        }
                        controller.checkGameState();
                        break;
                    case FOUR:
                        loggerTui.info("Your current Credit:\n");
                        loggerTui.info(INPUT
                                + (this.controller.getPlayer().getStake()
                                - this.controller.getPlayer().getRoundStake())
                                + "€\n");
                        loggerTui.info("Your current"
                                + " Round Stake:\n");
                        loggerTui.info(INPUT
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
                } else {
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
                //startNewRound();
                //
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
        loggerTui.info("\"----------------------- MENUE --\"\n"
                + "---------------------\n");
        loggerTui.info("1 -- HELP\n2 -- Next card \n3 "
                + "-- Double Stake" + "\n4 -- Current Stake"
                + "\n5 -- Quit and Resolve\n");
    }

    /**
     * start a new Round.
     */
    public void startNewRound() {
        stakeSet = roundStakeSet = deckSet = secondOpportunity = false;
        this.controller.createNewRound();
    }

    /**
     * prints current Credit.
     */
    private void printCurrentCredit() {
        calcController.calcProfit();
        loggerTui.info("-----------------------------------"
                + "---------------------\n");
        loggerTui.info("Your profit: "
                + calcController.getProfit() + "€\n");
        calcController.clacStake();
        loggerTui.info("Your new Credit: "
                + this.controller.getPlayer().getStake() + "€\n");
        loggerTui.info("-----------------------------------"
                + "---------------------\n");
    }

}
