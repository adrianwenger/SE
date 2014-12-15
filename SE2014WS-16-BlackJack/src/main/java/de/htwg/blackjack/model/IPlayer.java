package de.htwg.blackjack.model;

/**
 * Interface to increase abstractness.
 *
 * @author Adrian Wenger
 */
public interface IPlayer {

    /**
     * add a specific player.
     *
     * @param card card
     * @return added correctly?
     */
    boolean add(final ICard card);

    /**
     *
     * @return player Cards with values.
     */
    String printPlayersHand();

    /**
     * Returns the value of players hand.
     *
     * @return Card Value
     */
    int getValue();

    /**
     *
     * @return name
     */
    String getName();

    ICard[] getPlayerHand();
}
