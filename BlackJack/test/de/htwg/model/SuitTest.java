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
public class SuitTest {
    
    private Suit enumTest;
    
    @Before
    public void setUp() {
        Suit enumTest = Suit.CLUBS;
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of values method, of class Suit.
     */
    @Test
    public void testValues() {
       
    }

    /**
     * Test of valueOf method, of class Suit.
     */
    @Test
    public void testValueOf() {
        Suit expResult = Suit.CLUBS;
        Suit result = enumTest.CLUBS;
        assertEquals(expResult, result);
    }
    
}
