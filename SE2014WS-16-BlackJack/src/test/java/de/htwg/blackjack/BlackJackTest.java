package de.htwg.blackjack;

import de.htwg.blackjack.aview.tui.Tui;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author philippschultheiss
 */
public class BlackJackTest {

    /**
     * tui.
     */
    private Tui tui;
    /**
     * static BlackJack instance.
     */
    private BlackJack instance;

    @Before
    public void setUp() {
        instance = BlackJack.getInstance();
    }

    @Test
    public void testGetInstance() {
        boolean expResult = true;
        boolean result = instance instanceof BlackJack;
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTui() {
        tui = instance.getTui();
        boolean expResult = true;
        boolean result = tui instanceof Tui;
        assertEquals(expResult, result);
    }
    
    @Test public void testMain() {
        //?
        //?
        //?
    }
}
