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
    private int numCards;
    //Number of Decks
    private final int numDeck;
    //Card index
    private int index = 0;
    private static Random rmd = new Random();
    
    //An array filled with the cards from top of the Deck --> Hand
    private Card[] hand;
    
    //Returns the deck
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
        //Initialise size of deck and number of cards
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
    
    //returns Cards left on deck
    public int getNumCards() {
        return numCards;
    }
        
    //Deal next card from the top of the deck
    public Card dealCard(){
        //get the first card form top of the deck
        Card top = this.deck[0];
        //shift cards to the left, because we get the first one
        for(int i = 1; i < this.numCards; i++){
            this.deck[i-1] = this.deck[i];
        }
        this.deck[this.numCards-1] = null;
        //decrement the number of cards currently in the deck
        this.numCards--;
        
        return top; 
    }
}
