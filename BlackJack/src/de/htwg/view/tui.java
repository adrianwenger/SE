package de.htwg.view;

import de.htwg.model.Card;
import de.htwg.model.Deck;
import de.htwg.model.Suit;

public class tui {

public static void main(String[] args) {
      Deck test = new Deck(2, true);
      System.out.println(test.getMyCards());
      
    }

}
