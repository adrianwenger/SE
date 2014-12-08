package de.htwg.model;

/**
 * IDeck to Increase Abstractness.
 * @author Adrian Wenger
 */
public interface IDeck {
    /**
     * Return the deck.
     * @return Card[] deck of cards
     */
    ICard[] getDeck();

    /**
     * Deal next card from the top of the deck.
     * @return top Card of the deck
     */
    ICard dealCard();
}
