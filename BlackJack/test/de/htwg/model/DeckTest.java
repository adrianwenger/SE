/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.model;

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
    
    public DeckTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getNumCards method, of class Deck.
     */
    @Test
    public void testGetNumCards() {
        Deck instance = null;
        int expResult = 0;
        int result = instance.getNumCards();
        assertEquals(expResult, result);
       
    }

    /**
     * Test of getNumDeck method, of class Deck.
     */
    @Test
    public void testGetNumDeck() {
        Deck instance = null;
        int expResult = 0;
        int result = instance.getNumDeck();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.

    }
    
}
