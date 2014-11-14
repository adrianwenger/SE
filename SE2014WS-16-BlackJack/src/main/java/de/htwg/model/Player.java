package de.htwg.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int playerVal = 0;
    private final String name;

    private List<Card> userCards = new ArrayList();

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayerVal() {
        return playerVal;
    }

    public void setPlayerVal(int playerVal) {
        this.playerVal = playerVal;
    }

    public List<Card> getUserCards() {
        return userCards;
    }

    public void setUserCards(List<Card> usersCards) {
        this.userCards = usersCards;
    }

}
