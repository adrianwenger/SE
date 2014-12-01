package de.htwg.controller;

import de.htwg.model.Deck;
import de.htwg.model.Player;
import de.htwg.util.observer.Observable;
import java.util.Scanner;

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
    private String statusLine;
    /**
     * saves current Game state of BlackJack.
     */
    private IGameState currentState;
    
    /**
     * Um den Controller bekannt zu machen müssen hier die Model,View Objekte
     * erzeugt werden
     */
    public BlackJackController() {
        // create a start state
        currentState = new StateInGame();
    }

    public boolean setDeck(int numOfDeck) {
        this.deck = new Deck(numOfDeck);
        //notifyObservers();
        return true;
    }

    public void setPlayer(String player) {
        this.player = new Player(player);
        //notifyObservers();
    }

    public void setDealer() {
        this.dealer = new Player("Dealer");
        //notifyObservers();

    }
    
    public void setStatusLine(String statusLine) {
        this.statusLine = statusLine;
        notifyObservers();
    }
    
    public void setCurrentState(IGameState currentState) {
        this.currentState = currentState;
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
    
    /**
     * Changes the IGameState Object
     */
    public void changeGameState() {
        currentState.change(this);
    }

    public void checkGameStatus() {
        if (hasBlackJack(getDealer())) {
            statusLine = "You Loose! Dealer got BlackJack! GAME OVER!";
            notifyObservers();
            System.exit(0);
        }
        if (hasBlackJack(getPlayer())) {
            statusLine = "BLACKJACK!!!!! You win!";
            notifyObservers();
            System.exit(0);
        }
        if (player.getValue() > BLACKJACK) {
            statusLine = "You loose! Value bigger than 21!";
            notifyObservers();
            System.exit(0);
        }
        if (dealer.getValue() > BLACKJACK) {
            statusLine = "You win! Dealer get bigger value than 21!";
            notifyObservers();
            System.exit(0);
        }
        if (player.getValue() < BLACKJACK && dealer.getValue() < BLACKJACK) {
            statusLine = "Take another card!";
            notifyObservers();
        }

    }

    public void create() {
        Scanner SCANNER = new Scanner(System.in);
         //Initialize player and dealer
        setStatusLine("Bitte geben Sie ihren Namen ein: ");
        setStatusLine("-->: ");
        setPlayer(SCANNER.next());
        setDealer();
        //Initialize the number of decks
        setStatusLine("Player: " + getPlayer().getName());
        setStatusLine("How many decks you want for playing BlackJack?");
        setStatusLine("-->: ");

        setDeck(SCANNER.nextInt());
        
        setStatusLine("-----------------------MENUE--"
                + "---------------------");
        setStatusLine("1 -- HELP\n2 -- Play\n3 -- "
                + "Deal next card\n4 -- Quit Game\n");
    }

    public String output() {
        return statusLine;
    }
}
