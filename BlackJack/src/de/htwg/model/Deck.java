package de.htwg.model;

import java.util.Random;

/**
 *
 * @author philippschultheiss
 */
public class Deck {

    private Card[] myCards;

    //Getter for
    public Card[] getMyCards() {
        return myCards;
    }

    //Number of cards currently in the deck
    private int numCards;
    private int numDeck;
    //Card index
    private int index = 0;
    public static Random rmd = new Random();

    //Constructor which defines the number of decks and shuffle
    public Deck(int numDeck) {
        //Initialise size of myCards
        this.numDeck = numDeck;
        this.numCards = numDeck * 52;
        this.myCards = new Card[this.numCards];
        //Creates Deck myCards
        createDeck();
        //shuffles myCards
        shuffleCards(myCards);
    }

    private void createDeck() {
        //create Deck
        //for each deck
        for (int i = 0; i < numDeck; i++) {
            //for each suit
            for (int j = 0; j < 4; j++) {
                //for each number
                for (int z = 1; z <= 13; z++) {
                    //add new card to deck
                    this.myCards[index] = new Card(Suit.values()[j], z);
                    index++;
                }
            }
        }
    }

    //this method shuffles the cards and returns a mixed deck
    private void shuffleCards(Card[] myCards) {
        Card tmp;
        int rand;
        for (int i = 0; i < myCards.length; i++) {
            rand = rmd.nextInt(myCards.length);
            tmp = myCards[i];
            myCards[i] = myCards[rand];
            myCards[rand] = tmp;
        }
    }
}
