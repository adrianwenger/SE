package de.htwg.model;

import java.util.Random;

/**
 *
 * @author philippschultheiss
 */
public class Deck {

    private Card[] myCards;
    //Number of cards currently in the deck
    private int numCards;

    //Constructor which defines the number of decks and shuffle 
    public Deck(int numDeck, boolean shuffle) {
        //
        this.numCards = numDeck * 52;
        this.myCards = new Card[this.numCards];
        //Card index
        int index = 0;

        //create Deck
        //for each deck
        for (int i = 0; i < numDeck; i++) {
            //for each suit
            for (int j = 0; j < 4; j++) {
                //for each number
                for (int z = 0; z <= 13; z++) {
                    //add new card to deck
                    this.myCards[index] = new Card(Suit.values()[s], n);

                }
            }
        }
        //    //this method shuffles the cards and returns a mixed deck
//    public static int[] shuffleCards(int [] card) {
//        int tmp;
//        int rand;
//        for (int i = 0; i < card.length; i++) {
//            rand = rmd.nextInt(card.length);
//            tmp = card[i];
//            card[i] = card[rand];
//            card[rand] = tmp;
//        }
//        return card;
//    }
    }
}
