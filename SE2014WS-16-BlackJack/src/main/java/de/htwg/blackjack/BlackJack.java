package de.htwg.blackjack;

import de.htwg.blackjack.controller.impl.BlackJackController;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.aview.tui.Tui;
import de.htwg.blackjack.aview.gui.BlackJackFrame;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.impl.CalcProfitController;

/**
 *
 * @author Adrian Wenger
 */
public final class BlackJack {
    /**
     * tui.
     */
    private final Tui tui;
    /**
     * static BlackJack instance.
     */
    private static BlackJack instance = null;
    /**
     * BlackJackController.
     */
    private static IBlackJackController controller = new BlackJackController();

    /**
     * CalcProfitController
     */
    private static ICalcProfitController calcController = new CalcProfitController(controller);

    /**
     * Singleton.
     *
     * @return instance
     */
    public static BlackJack getInstance() {
        if (instance == null) {
            instance = new BlackJack();
        }
        return instance;

    }

    /**
     * @return tui
     */
    public Tui getTui() {
        return tui;
    }

    /**
     *
     */
    private BlackJack() {
        //controller = new BlackJackController();
        tui = new Tui(controller);
        controller.create();
    }

    /**
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        //Create new GUI
        //BlackJackFrame gui = new BlackJackFrame(controller, calcController);
        GUI gui = new GUI(controller, calcController);
        //Create new BlackJack Object
        BlackJack game = BlackJack.getInstance();
        //Starts the TextUserInterface
        game.getTui().initialize();
        game.getTui().createGame();
        game.getTui().continueGame();
    }
}
