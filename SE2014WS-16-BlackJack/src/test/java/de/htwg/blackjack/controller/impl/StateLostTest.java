package de.htwg.blackjack.controller.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.blackjack.BlackJackModule;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.IGameState;
import de.htwg.blackjack.model.impl.Card;
import de.htwg.blackjack.util.Suit;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author Adrian Wenger
 */
public class StateLostTest {


    private IBlackJackController controller;
    private Injector injector = Guice.createInjector(new BlackJackModule());

    @Before
    public final void setUp() {
        this.controller = new BlackJackController();
        this.controller.setInjector(injector);
        // Create Player
        this.controller.setPlayer("test");
        // Create Dealer
        this.controller.setDealer();
        // Create Deck
        this.controller.setDeck(1);
        // set State to StateLost
        this.controller.setCurrentState(new StateLost(controller, this.controller.getCalcController()));
    }

    /**
     * Test of change method, of class StateInGame.
     */
    @Test
    public final void testChange() {
        // Add Cards to Dealer
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));
        this.controller.getDealer().add(new Card(Suit.SPADES, 9));

        // no BlackJack Case 
        this.controller.getPlayer().setStake(1);
        this.controller.getCurrentState().change();
        boolean result = this.controller.getCurrentState() instanceof StateEndRound;
        assert (result);

        this.controller.getDealer().add(new Card(Suit.SPADES, 3));
        // BlackJack Case
        if (this.controller.hasBlackJack(this.controller.getDealer())) {
            this.controller.getCurrentState().change();
            result = this.controller.getCurrentState() instanceof StateEndRound;
            assert (result);
        }

    }

}
