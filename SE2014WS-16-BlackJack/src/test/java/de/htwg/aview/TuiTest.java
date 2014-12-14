package de.htwg.aview;

import de.htwg.controller.impl.BlackJackController;
import de.htwg.controller.IBlackJackController;
import de.htwg.model.impl.Deck;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author philippschultheiss
 */
public class TuiTest {

    IBlackJackController controller;
    Deck deck;
    Tui tui;
    private static final int TWO = 2;

    @Before
    public void setUp() {
        controller = new BlackJackController();
        tui = new Tui(controller);
        deck = new Deck();
    }

//    @Test
//    public void testUpdate() {
//
//    }

    @Test
    public void testcreateGame() {
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
