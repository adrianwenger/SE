package de.htwg.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int MAXCARDS = 10;
    private final int BLACKJACK = 21;
    //Value of players hand
    private int playerVal;
    //Value of a card
    private int numCards;
    //name from player
    private final String name;
    //players hand
    private Card[] playerHand = new Card[MAXCARDS];

    public Player(String name) {
        this.name = name;
        this.clearHand();
    }

    public String getName() {
        return name;
    }

    public int getPlayerVal() {
        return playerVal;
    }

    //Reset players hand
    private void clearHand() {
        for(int i = 0; i < MAXCARDS; i++){
            this.playerHand[i] = null;
        }  
    }
    
    //add card to users hand
    public boolean add(Card card){
        if(this.numCards == MAXCARDS){
            System.err.printf("%s's hand already has 10 cards!\n", this.name);
            System.exit(1);
        }
        this.playerHand[this.numCards] = card;
        this.numCards++;
        
        return(this.getValue() <= 21);
    }
    
    //Returns the value of players hand
    private int getValue(){
        this.playerVal = 0;
        int cardNum;
        int numAces = 0;
        for(int i = 0; i < this.numCards; i++){
            cardNum = this.playerHand[i].getNumber();
            //ACES
            if(cardNum == 1){
                numAces++;
                playerVal += 11;
            //FACE CARD    
            }else if(cardNum > 10){
                playerVal += 10;
            //NUMBERS
            }else{
                playerVal += cardNum;
            }
        }
        //ABFRAGE Ob >21 dann ACES = 1 sonst ACES = 11
        while(playerVal > 21 && numAces > 0){
            playerVal -= 10;
            numAces--;
        }
        return playerVal;
    }
    //Returns the hand from the player
    public void printPlayersHand(){
        StringBuilder sb = new StringBuilder();
        sb.append("Cards: ");
        for (Card card : playerHand) {
            if(card == null){
                break;
            }
            sb.append(card).append(" ");
        }
        System.out.println(sb.toString());
        System.out.printf("Value: %d \n", playerVal);
    }
    
    //Checks BlackJack
    public boolean blackJack(){
        if(playerVal == BLACKJACK){
            //System.out.println("You got BlackJack! You win!");
            return true;
        } 
        return false;
    }
}
