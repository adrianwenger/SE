package de.htwg.controller;

import de.htwg.model.Card;
import de.htwg.model.Deck;
import de.htwg.model.Player;
import de.htwg.model.Suit;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger, Philipp Schultheiß
 */

/**
 * Der Controller muss beide die View und das Model kennen.
 * da dieser für die Kommunikation zwischen den Beiden sorgt
 */
public class BlackJackController {
    private final Deck deck;
    private final Player player;
    private final Player dealer;
    //view schicht fehlt, da kein Konstruktor
    
    /**
    * Um den Controller bekannt zu machen müssen hier die Model,View Objekte
    * erzeugt werden
    */
    public BlackJackController(Deck deck, Player player, Player dealer){
        this.deck = deck;
        this.player = player;
        this.dealer = dealer;
    }
}