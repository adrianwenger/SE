package de.htwg.model;

import java.util.Random;

/**
 *
 * @author philippschultheiss
 */
public class Deck {
    private static final int sizeOfDeck = 52;
    private static final int defNumDeck = 1; 
    //Deck
    private final Card[] deck;
    //Number of cards currently in the deck
    private final int numCards;
    //Number of Decks
    private final int numDeck;
    //Card index
    private int index = 0;
    public static Random rmd = new Random();

    public Card[] getDeck() {
        return deck;
    }

    public Deck() {
        this.numDeck = defNumDeck;
        this.numCards = sizeOfDeck;
        this.deck = new Card[this.numCards];
    }
    
    //Constructor which defines the number of decks and shuffle
    public Deck(int numDeck) {
        //Initialise size of deck
        this.numDeck = numDeck;
        this.numCards = numDeck * sizeOfDeck;
        //create new Deck Array
        this.deck = new Card[this.numCards];
        //Creates Deck deck
        createDeck();
        //shuffles deck
        shuffleCards(deck);
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
                    this.deck[index] = new Card(Suit.values()[j], z);
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
    
    //reutn Cards left on deck
    public int getNumCards() {
        return numCards;
    }
    
    //return 
    public int getNumDeck() {
        return numDeck;
    }
}
