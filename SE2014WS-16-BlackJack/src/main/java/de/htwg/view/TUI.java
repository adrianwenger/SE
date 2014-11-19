package de.htwg.view;

import de.htwg.model.Deck;
import de.htwg.model.Player;
import java.util.Scanner;

class TUI {
    //define Scanner
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        //Initialize player and dealer
        System.out.println("Bitte geben Sie ihren Namen ein: ");
        System.out.printf("-->: ");
        Player player = new Player(scanner.next());
        Player dealer = new Player("Dealer");
        //Initialize the number of decks
        System.out.println("Player: " + player.getName());
        System.out.println("How many decks you want for playing BlackJack?");
        System.out.printf("-->: ");
        int numOfDecks = scanner.nextInt();
        Deck deck = new Deck(numOfDecks);
        
        System.out.println("-----------------------MENUE-----------------------");  
        System.out.print("1 -- HILFE\n2 -- Play\n3 -- Deal next card\n4 -- Quit Game\n");
        System.out.print("-->: ");     
        int eingabe = scanner.nextInt();
        
        //Game Runner
        while(eingabe <= 4){
            switch(eingabe){
                    case 1:
                        System.out.print("1 -- HELP\n2 -- Play\n3 -- Deal next card\n4 -- Quit Game\n");
                        break;
                    case 2:
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
                    case 3:
                        System.out.println("Do you want one more card? [y/n]");
                        String eingabe2 = scanner.next();
                        if (eingabe2.equals("y")){
                            player.add(deck.dealCard());
                            dealer.add(deck.dealCard());
                            //print value
                            System.out.print("Player --> ");
                            player.printPlayersHand();
                            System.out.print("Dealer --> ");
                            dealer.printPlayersHand();
                        }else if(eingabe2.equals("n")){
//                            if(player.blackJack()){
//                                System.out.println("You got BlackJack! You win!");  
                                System.out.print("Player --> ");
                                player.printPlayersHand();
                                System.out.print("Dealer --> ");
                                dealer.printPlayersHand();
//                            }else{
//                                System.out.println("You loose!");
//                            }
                        }
                        break;
                    case 4:
                        System.out.println("END!");
                        System.exit(1);
                        break;
            }
            System.out.print("-->: ");
            eingabe = scanner.nextInt();
        }
    }

}
