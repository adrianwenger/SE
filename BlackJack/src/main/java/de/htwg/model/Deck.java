package de.htwg.model;

import java.util.Random;

/**
 *
 * @author philippschultheiss
 */
public class Deck {

    private static final int FIFTYTWO = 52;
    private static final int ONE = 1;
    private static final int FOUR = 4;
    private static final int ZERO = 0;
    private static final int THIRTEEN = 13;
    //Deck
    private final Card[] deckVals;
    //Number of cards currently in the deck
    private final int numCards;
    //Number of Decks
    private final int numDeck;
    //Card index
    private int index = ZERO;
    private static Random rmd = new Random();

    public Deck() {
        this.numDeck = ONE;
        this.numCards = FIFTYTWO;
        this.deckVals = new Card[this.numCards];
    }

    //Constructor which defines the number of decks and shuffle
    public Deck(int numDeck) {
        //Initialise size of deck
        this.numDeck = numDeck;
        this.numCards = numDeck * FIFTYTWO;
        //create new Deck Array
        this.deckVals = new Card[this.numCards];
        //Creates Deck deck
        createDeck();
        //shuffles deck
        shuffleCards(deckVals);
    }

    public Card[] getDeck() {
        return deckVals;
    }

    private void createDeck() {
        //create Deck
        //for each deck
        for (int i = ZERO; i < numDeck; i++) {
            //for each suit
            for (int j = ZERO; j < FOUR; j++) {
                //for each number
                for (int z = ONE; z <= THIRTEEN; z++) {
                    //add new card to deck
                    this.deckVals[index] = new Card(Suit.values()[j], z);
                    index++;
                }
            }
        }
    }

    //this method shuffles the cards and returns a mixed deck
    private void shuffleCards(Card[] myCards) {
        Card tmp;
        int rand;
        for (int i = ZERO; i < myCards.length; i++) {
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
