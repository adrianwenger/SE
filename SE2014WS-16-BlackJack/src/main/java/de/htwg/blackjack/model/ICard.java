package de.htwg.blackjack.model;

import de.htwg.blackjack.util.Suit;


/**
 *
 * @author Adrian Wenger
 */
public interface ICard {

    /**
     *
     * @return number of Cards.
     */
     int getNumber();
     
     Suit getSuit();
}
