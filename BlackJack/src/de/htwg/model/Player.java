package de.htwg.model;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int playerVal = 0;
    private final String name;

    List<Card> usersCards = new ArrayList();

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

    public List<Card> getUsersCards() {
        return usersCards;
    }

    public void setUsersCards(List<Card> usersCards) {
        this.usersCards = usersCards;
    }

}
