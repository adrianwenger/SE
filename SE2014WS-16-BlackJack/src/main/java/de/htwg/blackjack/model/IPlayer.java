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

    /**
     * reset playerhand
     */
    void clearHand();

    /**
     *
     * @return ICard[]
     */
    ICard[] getPlayerHand();

    /**
     * Sets the stake
     *
     * @param stake
     */
    void setStake(double stake);

    /**
     * Returns the profit from the Player
     *
     * @return stake
     */
    double getStake();

    /**
     * double Stake
     */
    void doubleRoundStake();

    /**
     *
     */
    double getRoundStake();

    /**
     *
     */
    void setRoundStake(double roundStake);

    /**
     *
     */
    void setName(String nam);
}
