package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.util.Suit;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.ICard;
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
    private ICard[] deck;
    /**
     * Number of cards currently in the deck.
     */
    private int numOfCards;

    /**
     * Number of Decks.
     */
    private int numOfDecks;

    /**
     * Card index.
     */
    private int index = ZERO;
    /**
     *
     */
    private static Random rmd = new Random();

    /**
     * set amount of decks.
     *
     * @param numOfDecks number of decks
     */
    public void setNumOfDecks(final int numOfDecks) {
        this.numOfDecks = numOfDecks;
        buildDeck();
    }
    
    public int getNumOfDecks() {
        return numOfDecks;
    }
    
     public int getNumOfCards() {
        return numOfCards;
    }
    
    /**
     * this method initializes the deck attributes.
     */
    private void buildDeck() {
        //Initialise size of deck and number of cards
        this.numOfCards = this.numOfDecks * FIFTYTWO;
        //create new Deck Array
        this.deck = new ICard[this.numOfCards];
        //Creates Deck deck
        createDeck();
        //shuffles deck
        shuffleCards(deck);
    }

    /**
     * Returns the deck.
     *
     * @return deck
     */
    @Override
    public ICard[] getDeck() {
        return deck;
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
     *
     * @param myCards contains a ICard Array
     */
    private void shuffleCards(final ICard[] myCards) {
        ICard tmp;
        int rand;
        for (int i = ZERO; i < myCards.length; i++) {
            rand = rmd.nextInt(myCards.length);
            tmp = myCards[i];
            myCards[i] = myCards[rand];
            myCards[rand] = tmp;
        }
    }

    /**
     * Deal next card from the top of the deck.
     *
     * @return top Card of the deck
     */
    @Override
    public ICard dealCard() {
        //get the first card form top of the deck
        ICard top = this.deck[ZERO];
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
