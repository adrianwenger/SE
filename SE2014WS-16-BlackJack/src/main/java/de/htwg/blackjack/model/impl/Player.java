package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IPlayer;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
public final class Player implements IPlayer {

    /**
     *
     */
    private static final int ZERO = 0;
    /**
     * 
     */
    private static final int TWO = 2;
    /**
     *
     */
    private static final int ELEVEN = 11;
    /**
     *
     */
    private static final int TEN = 10;
    /**
     *
     */
    private static final int ONE = 1;
    /**
     *
     */
    private static final int MAXCARDS = 10;
    /**
     *
     */
    private static final int BLACKJACK = 21;
    /**
     * Value of players hand.
     */
    private int playerVal;
    /**
     * Players round stake.
     */
    private double roundStake;
    /**
     * Players stake.
     */
    private double stake;
    /**
     * Value of a card.
     */
    private int numOfCards;
    /**
     * name from player.
     */
    private final String name;
    /**
     * players hand.
     */
    private ICard[] playerHand;

    /**
     *
     * @param playerName Player Name
     */
    public Player(final String playerName) {
        this.name = playerName;
        this.playerHand = new Card[MAXCARDS];
    }

    /**
     *
     * @return name
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * sets the stake.
     * @param stak  stake
     */
    @Override
    public void setStake(final double stak) {
        this.stake = stak;
    }
    /**
     *
     * @return stake stake
     */
    @Override
    public double getStake() {
        return stake;
    }
    /**
     * Sets the round stake.
     * @param round roundStake
     */
    @Override
    public void setRoundStake(final double round) {
        this.roundStake = round;
    }
    /**
     * Returns the profit from the Player for the actual round.
     * @return stake
     */
    @Override
    public double getRoundStake() {
        return roundStake;
    }
    /**
     * double Stake.
     */
    @Override
    public void doubleRoundStake() {
        this.roundStake = roundStake * TWO;
    }
    /**
     * Reset players hand.
     */
    public void clearHand() {
        this.playerHand = new Card[MAXCARDS];
        this.playerVal = 0;
        this.numOfCards = 0;
    }

    /**
     * add card to users hand.
     *
     * @return added correctly?
     * @param card Card to be added
     */
    @Override
    public boolean add(final ICard card) {
        if (this.numOfCards == MAXCARDS) {
            return false;
        }
        // add Card to Player
        this.playerHand[this.numOfCards] = card;
        this.numOfCards++;

        return (this.getValue() <= BLACKJACK);
    }

    /**
     * Returns the value of players hand.
     *
     * @return Card Value
     */
    @Override
    public int getValue() {
        this.playerVal = ZERO;
        int cardNum;
        int numAces = ZERO;
        for (int i = ZERO; i < this.numOfCards; i++) {
            cardNum = this.playerHand[i].getNumber();
            //ACES
            if (cardNum == ONE) {
                numAces++;
                playerVal += ELEVEN;
                //FACE CARD
            } else if (cardNum > TEN) {
                playerVal += TEN;
                //NUMBERS
            } else {
                playerVal += cardNum;
            }
        }
        //ABFRAGE Ob >21 dann ACES = 1 sonst ACES = 11
        while (playerVal > BLACKJACK && numAces > ZERO) {
            playerVal -= TEN;
            numAces--;
        }
        return playerVal;
    }

    /**
     * Returns the hand from the player.
     *
     * @return String Value
     */
    @Override
    public String printPlayersHand() {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for (ICard card : playerHand) {
            if (card == null) {
                break;
            }
            sb.append(card).append(" ");
        }
        return sb.append("Value: ").append(playerVal).toString();
    }

    @Override
    public ICard[] getPlayerHand() {
        return this.playerHand;
    }
}
