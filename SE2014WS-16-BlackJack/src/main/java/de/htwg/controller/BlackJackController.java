package de.htwg.controller;

import de.htwg.model.Deck;
import de.htwg.model.Player;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
/**
 * Der Controller muss beide die View und das Model kennen. da dieser für die
 * Kommunikation zwischen den Beiden sorgt
 */
public class BlackJackController {

    private Deck deck;
    private Player player;
    private Player dealer;
    private final int BLACKJACK = 21;
    //view schicht fehlt, da kein Konstruktor

    /**
     * Um den Controller bekannt zu machen müssen hier die Model,View Objekte
     * erzeugt werden
     */
    public BlackJackController() {

    }

    public boolean setDeck(int numOfDeck) {
        this.deck = new Deck(numOfDeck);
        return true;
    }

    public void setPlayer(String player) {
        this.player = new Player(player);
    }

    public void setDealer() {
        this.dealer = new Player("Dealer");
    }

    public Deck getDeck() {
        return deck;
    }

    public Player getPlayer() {
        return player;
    }

    public Player getDealer() {
        return dealer;
    }

    public String getFirstTwoCardsPlayer() {
        player.add(deck.dealCard());
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    public String getFirstTwoCardsDealer() {
        dealer.add(deck.dealCard());
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    public String getCardPlayer() {
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    public String getCardDealer() {
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    public void checkIfDealerNeedsCard() {
        if (dealer.getValue() < BLACKJACK) {
            if (dealer.getValue() < player.getValue()) {
                getCardDealer();
            }
        }
    }

    /**
     * Checks BlackJack.
     *
     * @return blackjack?
     */
    public boolean hasBlackJack(Player subject) {
        return subject.getValue() == BLACKJACK;
    }

    public void checkGameStatus() {
        if (hasBlackJack(getDealer())) {
            System.out.println("You Loose! Dealer got BlackJack! GAME OVER!");
            System.exit(0);
        }
        if (hasBlackJack(getPlayer())) {
            System.out.print("BLACKJACK!!!!! You win!");
            System.exit(0);
        }
        if(player.getValue() > BLACKJACK){
            System.out.println("You loose! Value bigger than 21!");
            System.exit(0);
        }
        if(dealer.getValue() > BLACKJACK){
            System.out.println("You win! Dealer get bigger value than 21!");
            System.exit(0);
        }
        if(player.getValue() < BLACKJACK &&  dealer.getValue() < BLACKJACK){
            System.out.println("Take another card!");
        }
       
    }
}