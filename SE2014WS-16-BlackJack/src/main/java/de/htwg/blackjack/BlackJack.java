package de.htwg.blackjack;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.aview.tui.Tui;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.aview.gui.GUI;
import org.apache.log4j.PropertyConfigurator;

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
    private static IBlackJackController controller;

    /**
     * CalcProfitController
     */
    private static ICalcProfitController calcController;

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
        // Set up Google Guice Dependency Injector
        Injector injector = Guice.createInjector(new BlackJackModule());
        // Build up the application, resolving dependencies automatically by
        // Guice
        controller = injector.getInstance(IBlackJackController.class);
        // forward injector Object to BlackJackController
        controller.setInjector(injector);
        // create Tui
        tui = injector.getInstance(Tui.class);
        
        controller.create();
        
        calcController = injector.getInstance(ICalcProfitController.class);
        GUI gui = injector.getInstance(GUI.class);
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
    }

    /**
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        //Create new GUI
        //GUI gui = new GUI(controller, calcController);
        //Create new BlackJack Object
        BlackJack game = BlackJack.getInstance();
        //Starts the TextUserInterface
        game.getTui().initialize();
        game.getTui().createGame();
        game.getTui().continueGame();
    }
}
