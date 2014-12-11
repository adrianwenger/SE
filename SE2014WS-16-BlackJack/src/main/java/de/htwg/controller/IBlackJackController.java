package de.htwg.controller;

import de.htwg.model.IDeck;
import de.htwg.model.IPlayer;
import de.htwg.util.observer.IObservable;

/**
 *
 * @author Adrian Wenger
 */
public interface IBlackJackController extends IObservable {
    /**
     * create a Deck with a specific number.
     * @param numOfDeck numbers of decks
     */
    void setDeck(int numOfDeck);

    /**
     * 
     * @param player 
     */
    void setPlayer(String player);
    
    void setCurrentState(IGameState state);

    void setDealer();

    IDeck getDeck();

    IPlayer getPlayer();

    IPlayer getDealer();
    
    public String getStatusLine(); 
    
    IGameState getCurrentState();
    
    void setStatusLine(String text);

    String getFirstTwoCardsPlayer();

    String getFirstTwoCardsDealer();

    String getCardPlayer();

    String getCardDealer();

    void checkIfDealerNeedsCard();
    
    boolean hasBlackJack(IPlayer subject);
    
    void checkGameState();
    
    void create();
    
    String output();
    
    void endGame();
}
