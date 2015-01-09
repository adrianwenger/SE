package de.htwg.blackjack.model;

import de.htwg.blackjack.model.impl.Suit;


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
     
     /**
      *
      * @return Suit
      */
     Suit getSuit();
}
