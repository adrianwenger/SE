package de.htwg.controller;

import de.htwg.model.Deck;
import de.htwg.model.Player;
import de.htwg.util.observer.Observable;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */
/**
 * Der Controller muss beide die View und das Model kennen. da dieser für die
 * Kommunikation zwischen den Beiden sorgt
 */
public final class BlackJackController extends Observable
        implements IBlackJackController {

    /**
     *
     */
    private Deck deck;
    /**
     *
     */
    private Player player;
    /**
     *
     */
    private Player dealer;
    /**
     *
     */
    private static final int BLACKJACK = 21;
    /**
     *
     */
    private String statusLine;
    /**
     * saves current Game state of BlackJack.
     */
    private IGameState currentState;

    /**
     *
     * @param numOfDeck number of Decks
     */
    public void setDeck(final int numOfDeck) {
        this.deck = new Deck(numOfDeck);
    }

    /**
     *
     * @param pla Player
     */
    public void setPlayer(final String pla) {
        this.player = new Player(pla);
    }

    /**
     * set Dealer.
     */
    public void setDealer() {
        this.dealer = new Player("Dealer");
    }

    /**
     *
     * @param status statusLine
     */
    public void setStatusLine(final String status) {
        this.statusLine = status;
        notifyObservers();
    }

    /**
     * change game state.
     *
     * @param state GameState
     */
    public void setCurrentState(final IGameState state) {
        this.currentState = state;
    }

    /**
     *
     * @return deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     *
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return dealer
     */
    public Player getDealer() {
        return dealer;
    }

    /**
     *
     * @return printPlayersHand
     */
    public String getFirstTwoCardsPlayer() {
        player.add(deck.dealCard());
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    public String getFirstTwoCardsDealer() {
        dealer.add(deck.dealCard());
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    public String getCardPlayer() {
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    public String getCardDealer() {
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    /**
     *
     * @return current GameState
     */
    public IGameState getCurrentState() {
        checkGameState();
        return currentState;
    }

    /**
     * checks if Dealer needs another Card, after Player got his.
     */
    public void checkIfDealerNeedsCard() {
        if (dealer.getValue() < BLACKJACK) {
            if (dealer.getValue() < player.getValue()) {
                getCardDealer();
            }
        }
    }

    /**
     * Check BlackJack.
     *
     * @param subject Player Object
     * @return blackjack?
     */
    public boolean hasBlackJack(final Player subject) {
        return subject.getValue() == BLACKJACK;
    }

    /**
     * Changes the IGameState Object.
     */
    public void checkGameState() {
        currentState.change();
    }

    /**
     * initialize Game in StateInGame.
     */
    public void create() {
        setCurrentState(new StateInGame(this));
        statusLine = "Welcome to BlackJack...";
    }

    /**
     * returns stautsLine.
     *
     * @return statusLine
     */
    public String output() {
        return statusLine;
    }
}
