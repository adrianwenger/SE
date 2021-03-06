package de.htwg.blackjack.controller;

import com.google.inject.Injector;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.util.observer.IObservable;

/**
 *
 * @author Adrian Wenger
 */
public interface IBlackJackController extends IObservable {

    /**
     * setInjector
     */
    void setInjector(Injector injector);

    /**
     * create a Deck with a specific number.
     *
     * @param numOfDeck numbers of decks
     */
    void setDeck(int numOfDeck);

    /**
     *
     * @param player Player
     */
    void setPlayer(String player);

    /**
     * set Game State.
     *
     * @param state state
     */
    void setCurrentState(IGameState state);

    /**
     *
     */
    void setCalcController(ICalcProfitController calcController);

    /**
     * create Dealer.
     */
    void setDealer();

    /**
     *
     * @return deck
     */
    IDeck getDeck();

    /**
     *
     * @return player
     */
    IPlayer getPlayer();

    /**
     *
     * @return dealer
     */
    IPlayer getDealer();

    /**
     *
     * @return statuLine
     */
    String getStatusLine();

    /**
     *
     * @return state
     */
    IGameState getCurrentState();

    /**
     * set statuLine Text and notifyObservers.
     *
     * @param text statusLine value
     */
    void setStatusLine(String text);

    /**
     *
     * @return first Cards with value
     */
    String getFirstTwoCardsPlayer();

    /**
     *
     */
    ICalcProfitController getCalcController();

    /**
     *
     * @return first Cards with value
     */
    String getFirstTwoCardsDealer();

    /**
     *
     * @return playersHand
     */
    String getCardPlayer();

    /**
     *
     * @return playersHand
     */
    String getCardDealer();

    /**
     *
     * (If Dealer < Player.getVlaue() && < 21 ) getCardDealer().
     */
    boolean checkIfDealerNeedsCard();

    /**
     *
     * @param subject player
     * @return hasBlackJack ture/false
     */
    boolean hasBlackJack(IPlayer subject);

    /**
     * checks game state.
     */
    void checkGameState();

    /**
     *
     * @return statusLine
     */
    String output();

    /**
     * finish game.
     */
    void endGame();

    /**
     * create new ROund.
     */
    void createNewRound();
}
