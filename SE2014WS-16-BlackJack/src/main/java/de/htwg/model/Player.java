package de.htwg.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    
    private static final int ZERO = 0;
    private static final int ELEVEN = 11;
    private static final int TEN = 10;
    private static final int ONE = 1;
    private final int MAXCARDS = 10;
    private final int BLACKJACK = 21;
    //Value of players hand
    private int playerVal;
    //Value of a card
    private int numOfCards;
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
        for(int i = ZERO; i < MAXCARDS; i++){
            this.playerHand[i] = null;
        }  
    }
    
    //add card to users hand
    public boolean add(Card card){
        if(this.numOfCards == MAXCARDS){
            System.err.printf("%s's hand already has 10 cards!\n", this.name);
            System.exit(ZERO);
        }
        this.playerHand[this.numOfCards] = card;
        this.numOfCards++;
        
        return(this.getValue() <= BLACKJACK);
    }
    
    //Returns the value of players hand
    private int getValue(){
        this.playerVal = ZERO;
        int cardNum;
        int numAces = ZERO;
        for(int i = ZERO; i < this.numOfCards; i++){
            cardNum = this.playerHand[i].getNumber();
            //ACES
            if(cardNum == ONE){
                numAces++;
                playerVal += ELEVEN;
            //FACE CARD    
            }else if(cardNum > TEN){
                playerVal += TEN;
            //NUMBERS
            }else{
                playerVal += cardNum;
            }
        }
        //ABFRAGE Ob >21 dann ACES = 1 sonst ACES = 11
        while(playerVal > BLACKJACK && numAces > ZERO){
            playerVal -= TEN;
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
        return playerVal == BLACKJACK;
    }
}
