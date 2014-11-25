package de.htwg.view;

import de.htwg.model.Deck;
import de.htwg.model.Player;
import java.util.Scanner;

/**
 *
 * @author Adrian Wenger
 */
final class TUI {
    /**
     * define Scanner.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /**
     *
     */
    private static final int ONE = 1;

    /**
     *
     */
    private static final int TWO = 2;
    /**
     *
     */
    private static final int THREE = 3;
    /**
     *
     */
    private static final int FOUR = 4;

    /**
     *
     */
    public static void main(final String[] args) {
        //Initialize player and dealer
        System.out.println("Bitte geben Sie ihren Namen ein: ");
        System.out.printf("-->: ");
        Player player = new Player(SCANNER.next());
        Player dealer = new Player("Dealer");
        //Initialize the number of decks
        System.out.println("Player: " + player.getName());
        System.out.println("How many decks you want for playing BlackJack?");
        System.out.printf("-->: ");
        Deck deck = new Deck(SCANNER.nextInt());

        System.out.println("-----------------------MENUE--"
                + "---------------------");
        System.out.print("1 -- HILFE\n2 -- Play\n3 -- "
                + "Deal next card\n4 -- Quit Game\n");
        System.out.print("-->: ");
        int eingabe = SCANNER.nextInt();

        //Game Runner
        while (eingabe <= FOUR) {
            switch (eingabe) {
                case ONE:
                    System.out.print("1 -- HELP\n2 -- Play\n3 -- Deal "
                            + "next card\n4 -- Quit Game\n");
                    break;
                case TWO:
                    //get the first two cards
                    player.add(deck.dealCard());
                    dealer.add(deck.dealCard());
                    player.add(deck.dealCard());
                    dealer.add(deck.dealCard());

                    System.out.println("First two cards are dealt!");
                    System.out.print("Player --> ");
                    player.printPlayersHand();
                    System.out.print("Dealer --> ");
                    dealer.printPlayersHand();
                    System.out.println();
                    break;
                case THREE:
                    System.out.println("Do you want one more card? [y/n]");
                    String eingabe2 = SCANNER.next();
                    if (eingabe2.equals("y")) {
                        player.add(deck.dealCard());
                        dealer.add(deck.dealCard());
                        //print value
                        System.out.print("Player --> ");
                        player.printPlayersHand();
                        System.out.print("Dealer --> ");
                        dealer.printPlayersHand();
                    } else if (eingabe2.equals("n")) {
//                       if(player.blackJack()){
//                           System.out.println("You got BlackJack! You win!");
                        System.out.print("Player --> ");
                        player.printPlayersHand();
                        System.out.print("Dealer --> ");
                        dealer.printPlayersHand();
//                            }else{
//                                System.out.println("You loose!");
//                            }
                    }
                    break;
                case FOUR:
                    System.out.println("END!");
                    System.exit(1);
                    break;
                default:
            }
            System.out.print("-->: ");
            eingabe = SCANNER.nextInt();
        }
    }

}
