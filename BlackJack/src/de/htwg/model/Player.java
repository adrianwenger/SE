package de.htwg.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
        private int playerVal = 0;
	private final String name;
        
        List usersCards = new ArrayList();
        
        public Player(String name){
            this.name = name;
        }

}
