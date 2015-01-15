package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.controller.impl.StateWon;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.util.Suit;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Adrian Wenger
 */
public class StateWonTest {

    private IBlackJackController controller;
    
    private ICalcProfitController calController;

    @Before
    public final void setUp() {
        this.controller = new BlackJackController();
        this.calController = new CalcProfitController(controller);
        
        // Create Player
        this.controller.setPlayer("Test");
        // Create Dealer
        this.controller.setDealer();
        // Create Deck
        this.controller.setDeck(1);
        // set State to StateWon
        this.controller.setCurrentState(new StateWon(controller, this.controller.getCalcController()));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // Player < 21... just won
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        // no BlackJAck Dealer > 21 && Player < 21
        if (!this.controller.hasBlackJack(this.controller.getPlayer())) {
            String result = this.controller.getStatusLine();
            String expResult = null;
            assertEquals(expResult, result);
        }

        // Player with BlackJack
        this.controller.getPlayer().add(new Card(Suit.SPADES, 3));
        //this.controller.getCurrentState().change();
        boolean result = this.controller.getCurrentState() instanceof StateWon;
        assert (result);
    }
}
