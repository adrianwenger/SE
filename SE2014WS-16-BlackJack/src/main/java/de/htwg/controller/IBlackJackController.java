package de.htwg.controller;

import de.htwg.model.Deck;
import de.htwg.model.Player;
import de.htwg.util.observer.IObservable;
import de.htwg.view.TUI;

/**
 *
 * @author Adrian Wenger
 */
public interface IBlackJackController extends IObservable {

    public boolean setDeck(int numOfDeck);

    public void setPlayer(String player);

    public void setDealer();

    public Deck getDeck();

    public Player getPlayer();

    public Player getDealer();
    
    public void setStatusLine(String text);

    public String getFirstTwoCardsPlayer();

    public String getFirstTwoCardsDealer();

    public String getCardPlayer();

    public String getCardDealer();

    public void checkIfDealerNeedsCard();
    
    public boolean hasBlackJack(Player subject);
    
    public void checkGameStatus();
    
    public void create();
    
    public String output();

}
