/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.model;

import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adi
 */
public class DeckTest {

    Deck deck;
    Deck deckDefault;

    @Before
    public void setUp() {
        //Testing Construktor
        Deck expResult;
        expResult = new Deck(2);

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getNumCards method, of class Deck.
     */
    @Test
    public void testGetNumCards() {

        int expResult = 104;
        int result = deck.getNumCards();
        assertEquals(expResult, result);

        expResult = 52;
        result = deckDefault.getNumCards();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumDeck method, of class Deck.
     */
    @Test
    public void testGetNumDeck() {
        int expResult = 2;
        int result = deck.getNumDeck();
        assertEquals(expResult, result);

        expResult = 1;
        result = deckDefault.getNumDeck();
        assertEquals(expResult, result);
    }
}
