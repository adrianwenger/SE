package de.htwg.model;

import java.util.Map;
import java.util.TreeMap;

public class Card {

    private static final int ONE = 1;
    //One of the four valid suits for this card
    private final Suit suit;
    //The number of this card
    private final int number;
    // TreeMap card saves cards
    private final Map<Integer, String> cards = new TreeMap<Integer, String>();
    private int i = ONE;


    /*
     Card constructor
     */
    public Card(Suit aSuit, int aNumber) {
        this.suit = aSuit;
        this.number = aNumber;
        createCards();
    }

    /*
     return Suit
     */
    public Suit getSuit() {
        return suit;
    }

    /*
     return the Number of the Card
     */
    public int getNumber() {
        return number;
    }

    /*
     return Card as String 
     */
    public String toString() {
        return cards.get(number) + "Of" + suit.toString();
    }

    /*
     creates the Cards the game contains
     */
    private void createCards() {
        insertCardValues("Ace");
        insertCardValues("Two");
        insertCardValues("Three");
        insertCardValues("Four");
        insertCardValues("Five");
        insertCardValues("Six");
        insertCardValues("Seven");
        insertCardValues("Eight");
        insertCardValues("Nine");
        insertCardValues("Ten");
        insertCardValues("Jack");
        insertCardValues("Queen");
        insertCardValues("King");
        insertCardValues("Ace");
    }

    /*
     fills the TreeMap cards with keys and values
     */
    private void insertCardValues(String num) {
        cards.put(i, num);
        i++;
    }
}
