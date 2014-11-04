package de.htwg.model;

import java.util.Random;

public class Card {
    Random rmd = new Random();
    //create a new CardDeck
    public Card() {
        //abfangen des Falls Ass! Ass hat den Wert 11 oder 1, je nach dem was für den Player nütlicher ist
        int[] card = {2, 3, 4, 5, 6, 7, 8, 9, 10, 10, 10, 10, 11};
    }
    
    //this method shuffles the cards and returns a mixed deck
    public int[] shuffleCards(int [] card) {
        int tmp;
        int rand;
        for (int i = 0; i < card.length; i++) {
            rand = rmd.nextInt(card.length);
            tmp = card[i];
            card[i] = card[rand];
            card[rand] = tmp;
        }
        return card;
    }
    
    
}
