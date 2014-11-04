package de.htwg.model;

import java.util.Random;

public class Card {

    //One of the four valid suits for this card
    private Suit mySuit;

    //The number of this card
    private int myNumber;

    //Card constructor
    public Card(Suit aSuit, int aNumber) {
        this.mySuit = aSuit;
        this.myNumber = aNumber;
    }

    //return the Number of the Card
    public int getNumber() {
        return myNumber;
    }

    public String toString() {
        String numStr = null;

        switch (this.myNumber) {
            case 2:
                numStr = "Two";
                break;
            case 3:
                numStr = "Three";
                break;
            case 4:
                numStr = "Four";
                break;
            case 5:
                numStr = "Five";
                break;
            case 6:
                numStr = "Six";
                break;
            case 7:
                numStr = "Seven";
                break;
            case 8:
                numStr = "Eight";
                break;
            case 9:
                numStr = "Nine";
                break;
            case 10:
                numStr = "Ten";
                break;
            case 11:
                numStr = "Jack";
                break;
            case 12:
                numStr = "Queen";
                break;
            case 13:
                numStr = "King";
                break;
            case 1:
                numStr = "Ace";
                break;
        }
        return numStr + "of" + mySuit.toString();
    }

}
