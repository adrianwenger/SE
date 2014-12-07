package de.htwg.model.impl;
import de.htwg.model.IPlayer;

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
    private final Card[] playerHand = new Card[MAXCARDS];

    /**
     *
     * @param playerName Player Name
     */
    public Player(final String playerName) {
        this.name = playerName;
        this.clearHand();
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Reset players hand.
     */
    private void clearHand() {
        for (int i = ZERO; i < MAXCARDS; i++) {
            this.playerHand[i] = null;
        }
    }

    /**
     * add card to users hand.
     *
     * @return added correctly?
     * @param card Card to be added
     */
    public boolean add(final Card card) {
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
     *@return String Value
     */
    public String printPlayersHand() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cards: ");
        for (Card card : playerHand) {
            if (card == null) {
                break;
            }
            sb.append(card).append(" ");
        }
        return sb.append("Value: ").append(playerVal).toString();
    }
}
