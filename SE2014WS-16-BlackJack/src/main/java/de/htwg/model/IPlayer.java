package de.htwg.model;

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
     * set Player Value for tets.
     * @param playerVal Player Value
     */
    void setPlayerVal(int playerVal);

    /**
     *
     * @return name
     */
    String getName();

}
