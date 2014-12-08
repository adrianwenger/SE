package de.htwg.model;

import de.htwg.model.impl.Card;



/**
 * IDeck to Increase Abstractness.
 * @author Adrian Wenger
 */
public interface IDeck {
    /**
     * Return the deck.
     * @return Card[] deck of cards
     */
    Card[] getDeck();

    /**
     * Deal next card from the top of the deck.
     * @return top Card of the deck
     */
    Card dealCard();
}
