/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.aview;

import de.htwg.controller.impl.BlackJackController;
import de.htwg.controller.IBlackJackController;
import de.htwg.model.impl.Deck;
import javax.accessibility.AccessibleRelation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author philippschultheiss
 */
public class TUITest {

    IBlackJackController controller;
    Deck deck;
    TUI tui;
    private static final int TWO = 2;

    @Before
    public void setUp() {
        controller = new BlackJackController();
        tui = new TUI(controller);
        deck = new Deck();
    }

//    @Test
//    public void testUpdate() {
//
//    }

    @Test
    public void testcreateGame() {
    }

    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
