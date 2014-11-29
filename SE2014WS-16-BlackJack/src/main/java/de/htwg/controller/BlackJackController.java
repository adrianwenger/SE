package de.htwg.controller;

import de.htwg.model.Deck;
import de.htwg.model.Player;
import de.htwg.util.observer.Event;
import de.htwg.util.observer.IObserver;
import de.htwg.util.observer.Observable;
import de.htwg.view.TUI;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
/**
 * Der Controller muss beide die View und das Model kennen. da dieser für die
 * Kommunikation zwischen den Beiden sorgt
 */
public class BlackJackController extends Observable implements IBlackJackController {

    private Deck deck;
    private Player player;
    private Player dealer;
    private final static int BLACKJACK = 21;
    //view schicht fehlt, da kein Konstruktor
    private String statusLine;

    /**
     * Um den Controller bekannt zu machen müssen hier die Model,View Objekte
     * erzeugt werden
     */
    public BlackJackController() {
    }

    public boolean setDeck(int numOfDeck) {
        this.deck = new Deck(numOfDeck);
        notifyObservers();
        return true;
    }

    public void setPlayer(String player) {
        this.player = new Player(player);
        notifyObservers();
    }

    public void setDealer() {
        this.dealer = new Player("Dealer");
        notifyObservers();

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
        notifyObservers();

        return (player.printPlayersHand());
    }

    public String getFirstTwoCardsDealer() {
        dealer.add(deck.dealCard());
        dealer.add(deck.dealCard());
        notifyObservers();

        return (dealer.printPlayersHand());
    }

    public String getCardPlayer() {
        player.add(deck.dealCard());
        notifyObservers();

        return (player.printPlayersHand());
    }

    public String getCardDealer() {
        dealer.add(deck.dealCard());
        notifyObservers();

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
        if (player.getValue() > BLACKJACK) {
            System.out.println("You loose! Value bigger than 21!");
            System.exit(0);
        }
        if (dealer.getValue() > BLACKJACK) {
            System.out.println("You win! Dealer get bigger value than 21!");
            System.exit(0);
        }
        if (player.getValue() < BLACKJACK && dealer.getValue() < BLACKJACK) {
            System.out.println("Take another card!");
        }

    }

    public void create() {
        statusLine = "";
        notifyObservers();
    }

    public String output() {
        return statusLine;
    }
}
