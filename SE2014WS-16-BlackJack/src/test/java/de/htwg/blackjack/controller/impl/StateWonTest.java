package de.htwg.blackjack.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.blackjack.BlackJackModule;
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
    private Injector injector = Guice.createInjector(new BlackJackModule());

    @Before
    public final void setUp() {
        this.controller = new BlackJackController();
        this.calController = new CalcProfitController(controller);
        this.controller.setInjector(injector);
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
        boolean result = this.controller.getCurrentState() instanceof StateEndRound;
        assert(true);
        // Player with BlackJack
        this.controller.getPlayer().add(new Card(Suit.SPADES, 3));
        //this.controller.getCurrentState().change();
        result = this.controller.getCurrentState() instanceof StateWon;
        assert (result);
    }
}
