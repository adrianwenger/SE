package de.htwg.blackjack;

import com.google.inject.AbstractModule;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.impl.Suit;


/**
 *
 * @author Adrian Wenger
 */
public final class BlackJackModule extends AbstractModule {


    /**
     *
     */
    @Override
    protected void configure() {

        bind(IBlackJackController.class)
                .to(de.htwg.blackjack.controller.impl.BlackJackController.class);
        bind(IPlayer.class)
                .to(de.htwg.blackjack.model.impl.Player.class);
        bind(IDeck.class).
                to(de.htwg.blackjack.model.impl.Deck.class);
        bind(ICalcProfitController.class).
                to(de.htwg.blackjack.controller.impl.CalcProfitController.class);
    }

}
