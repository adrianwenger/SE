package de.htwg.controller;

import de.htwg.model.impl.Deck;
import de.htwg.model.impl.Player;
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
     * Deck Object to be created.
     */
    private Deck deck;
    /**
     * Player Object to be created.
     */
    private Player player;
    /**
     * Player Object (Dealer) to be created.
     */
    private Player dealer;
    /**
     * BlackJack Value 21.
     */
    private static final int BLACKJACK = 21;
    /**
     * statusLine.
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
    @Override
    public void setDeck(final int numOfDeck) {
        this.deck = new Deck(numOfDeck);
    }

    /**
     *
     * @param pla Player
     */
    @Override
    public void setPlayer(final String pla) {
        this.player = new Player(pla);
    }

    /**
     * set Dealer.
     */
    @Override
    public void setDealer() {
        this.dealer = new Player("Dealer");
    }

    /**
     *
     * @param status statusLine
     */
    @Override
    public void setStatusLine(final String status) {
        this.statusLine = status;
        notifyObservers();
        this.statusLine = "";
    }

    /**
     * change game state.
     *
     * @param state GameState
     */
    @Override
    public void setCurrentState(final IGameState state) {
        this.currentState = state;
        notifyObservers();
    }

    /**
     *
     * @return deck
     */
    @Override
    public Deck getDeck() {
        return deck;
    }

    /**
     *
     * @return player
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return dealer
     */
    @Override
    public Player getDealer() {
        return dealer;
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getFirstTwoCardsPlayer() {
        player.add(deck.dealCard());
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getFirstTwoCardsDealer() {
        dealer.add(deck.dealCard());
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getCardPlayer() {
        player.add(deck.dealCard());

        return (player.printPlayersHand());
    }

    /**
     *
     * @return printPlayersHand
     */
    @Override
    public String getCardDealer() {
        dealer.add(deck.dealCard());

        return (dealer.printPlayersHand());
    }

    /**
     *
     * @return current GameState
     */
    @Override
    public IGameState getCurrentState() {
        return currentState;
    }

    /**
     * checks if Dealer needs another Card, after Player got his.
     */
    @Override
    public void checkIfDealerNeedsCard() {
        if (dealer.getValue() < BLACKJACK
                && dealer.getValue() < player.getValue()) {
            getCardDealer();

        }
    }

    /**
     * Check BlackJack.
     *
     * @param subject Player Object
     * @return blackjack?
     */
    @Override
    public boolean hasBlackJack(final Player subject) {
        return subject.getValue() == BLACKJACK;
    }

    /**
     * Changes the IGameState Object.
     */
    @Override
    public void checkGameState() {
        // dealer or player reached BlackJack
        if (player.getValue() == BLACKJACK || dealer.getValue() == BLACKJACK) {
            this.setCurrentState(new StateBlackJack(this));
            // new Game. Initialize with StateInGame
        } else if (this.currentState == null) {
            this.setCurrentState(new StateInGame(this));
            this.currentState.change();
        } else {
            // check if GameState will change
            this.currentState.change();
        }
    }

    /**
     * initialize Game in StateInGame.
     */
    @Override
    public void create() {
        setStatusLine("Welcome to BlackJack...");
    }

    /**
     * returns stautsLine.
     *
     * @return statusLine
     */
    @Override
    public String output() {
        return statusLine;
    }

    /**
     * End Game.
     */
    @Override
    public void endGame() {
        if (this.getCurrentState() == null) {
            this.setStatusLine("Endergebins: ");
            this.setStatusLine("Player:" + this.getPlayer().printPlayersHand());
            this.setStatusLine("Dealer:" + this.getDealer().printPlayersHand());
        }
    }
}
