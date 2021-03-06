package de.htwg.blackjack.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.blackjack.BlackJackModule;
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
public class StateInGameTest {

    /**
     * BlackJack Value 21.
     */
    private IBlackJackController controller;
    
    private ICalcProfitController calcController;
    private Injector injector = Guice.createInjector(new BlackJackModule());
    

    @Before
    public final void setUp() {
        this.controller = new BlackJackController();
        this.calcController = new CalcProfitController(controller);
        this.controller.setInjector(injector);
        // Create Player
        this.controller.setPlayer("Test");
        // Create Dealer
        this.controller.setDealer();
        // Create Deck
        this.controller.setDeck(1);
        // set State to StateInGame
        this.controller.setCurrentState(new StateInGame(controller, this.controller.getCalcController()));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // Case Player  < 21 && Dealer < 21
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().setStake(500);
        this.controller.getPlayer().setRoundStake(200);
        this.controller.getCurrentState().change();

        // Case Dealer BlackJack
        this.controller.getDealer().add(new Card(Suit.SPADES, 3));
        boolean result1 = this.controller.getCurrentState() instanceof StateInGame;
        assert(result1);
        this.controller.getCurrentState().change();
        result1 = this.controller.getCurrentState() instanceof StateEndRound;
        assert (result1);

        // Case Player BlackJack
        this.controller.getPlayer().clearHand();
        this.controller.getDealer().clearHand();
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 3));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.setCurrentState(new StateInGame(controller, this.controller.getCalcController()));
        boolean result2 = this.controller.getCurrentState() instanceof StateInGame;
        assert (result2);
        this.controller.getCurrentState().change();
        result2 = this.controller.getCurrentState() instanceof StateEndRound;
        assert (result2);

        // Case Dealer > 21 Player < 21 (Player Won)
        this.controller.getPlayer().clearHand();
        this.controller.getDealer().clearHand();
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getPlayer().add(new Card(Suit.SPADES, 9));
        this.controller.setCurrentState(new StateInGame(controller, calcController));
        this.controller.getCurrentState().change();
        boolean result3 = this.controller.getCurrentState() instanceof StateEndRound;
        assert (result3);
    }
}
