package de.htwg.model.impl;

import de.htwg.model.IDeck;
import java.util.Random;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
public final class Deck implements IDeck {

    /**
     *
     */
    private static final int FIFTYTWO = 52;
    /**
     *
     */
    private static final int ONE = 1;
    /**
     *
     */
    private static final int FOUR = 4;
    /**
     *
     */
    private static final int ZERO = 0;
    /**
     *
     */
    private static final int THIRTEEN = 13;
    /**
     *
     */
    private final Card[] deck;
    /**
     * Number of cards currently in the deck.
     */
    private int numOfCards;
    /**
     * Number of Decks.
     */
    private final int numOfDecks;

    /**
     * Card index.
     */
    private int index = ZERO;
    /**
     *
     */
    private static Random rmd = new Random();

    /**
     *
     * @return numOfDecks
     */
    public int getNumOfDecks() {
        return numOfDecks;
    }

    /**
     * Returns the deck.
     * @return deck
     */
    public Card[] getDeck() {
        return deck;
    }

    /**
     * Default Constructor.
     */
    public Deck() {
        this.numOfDecks = ONE;
        this.numOfCards = FIFTYTWO;
        this.deck = new Card[this.numOfCards];
    }

    /**
     * Constructor which defines the number of decks and shuffle.
     * @param numDeck number of decks which want to be created
     */
    public Deck(final int numDeck) {
        //Initialise size of deck and number of cards
        this.numOfDecks = numDeck;
        this.numOfCards = numDeck * FIFTYTWO;
        //create new Deck Array
        this.deck = new Card[this.numOfCards];
        //Creates Deck deck
        createDeck();
        //shuffles deck
        shuffleCards(deck);
    }

    /**
     *
     */
    private void createDeck() {
        //create Deck
        //for each deck
        for (int i = ZERO; i < numOfDecks; i++) {
            //for each suit
            for (int j = ZERO; j < FOUR; j++) {
                //for each number
                for (int z = ONE; z <= THIRTEEN; z++) {
                    //add new card to deck
                    this.deck[index] = new Card(Suit.values()[j], z);
                    index++;
                }
            }
        }
    }

    /**
     * this method shuffles the cards and returns a mixed deck.
     * @param myCards contains a Card Array
     */
    private void shuffleCards(final Card[] myCards) {
        Card tmp;
        int rand;
        for (int i = ZERO; i < myCards.length; i++) {
            rand = rmd.nextInt(myCards.length);
            tmp = myCards[i];
            myCards[i] = myCards[rand];
            myCards[rand] = tmp;
        }
    }

    /**
     * returns Cards left on deck.
     * @return numOfCards
     */
    public int getNumOfCards() {
        return numOfCards;
    }

    /**
     * Deal next card from the top of the deck.
     * @return top Card of the deck
     */
    public Card dealCard() {
        //get the first card form top of the deck
        Card top = this.deck[ZERO];
        //shift cards to the left, because we get the first one
        for (int i = ONE; i < this.numOfCards; i++) {
            this.deck[i - ONE] = this.deck[i];
        }
        this.deck[this.numOfCards - ONE] = null;
        //decrement the number of cards currently in the deck
        this.numOfCards--;

        return top;
    }
}
