package de.htwg.model;

/**
 *
 * @author philippschultheiss
 */
public class Hand {

    public void addCard(Card card) {

    }

    public void hasBlackJack() {
        if (getCardsValue() <= 21) {
            System.out.println("Your hand is lower than 21!");
        } else if (getCardsValue() == 21) {
            System.out.println("BlackJack! You win!");
        } else {
            System.out.println("You loosed! Hand is bigger than 21!");
        }
    }
    public int getCardsValue() {

    }

}
