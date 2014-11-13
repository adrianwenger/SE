package de.htwg.view;

import de.htwg.model.Card;
import de.htwg.model.Deck;
import de.htwg.model.Player;
import de.htwg.model.Suit;
import java.util.Scanner;

public class TUI {
    //define Scanner
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Bitte geben Sie ihren Namen ein: ");
        System.out.printf("-->: ");
        Player player1 = new Player(scanner.next());
        
        System.out.println("Player: " + player1.getName());
        
        Deck deck = new Deck(5);
        deck.getDeck();
        
        String result = new Card(Suit.DIAMONDS, 2).toString();
        System.out.println(result);
    }

}
