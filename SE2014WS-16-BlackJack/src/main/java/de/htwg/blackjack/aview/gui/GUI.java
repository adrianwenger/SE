package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.impl.StateBlackJack;
import de.htwg.blackjack.controller.impl.StateEndRound;
import de.htwg.blackjack.controller.impl.StateInGame;
import de.htwg.blackjack.controller.impl.StateLost;
import de.htwg.blackjack.controller.impl.StateWon;
import static de.htwg.blackjack.util.StaticCollections.*;
import de.htwg.blackjack.util.observer.IObserver;
import javax.swing.JFrame;

/**
 *
 * @author philippschultheiss
 */
public class GUI extends JFrame implements IObserver {

    /**
     * To save the IBlackJackController for all of the several UIs.
     */
    private final IBlackJackController controller;
    /**
     * To save the ICalcProfitController for all of the several UIs.
     */
    private final ICalcProfitController calcController;

    private MainFrame mainFrame;

    /**
     * Public Contructor to create a GUI.
     *
     * @param controller
     * @param calcController
     */
    @Inject
    public GUI(final IBlackJackController controller, final ICalcProfitController calcController) {
        this.controller = controller;
        this.calcController = calcController;
        controller.addObserver(this);

        // initialize WelcomeFrame
        activateWelcomeFrame();

    }

    /**
     * Method to activate the MenuFrame/WelcomeFrame.
     *
     * @return WelcomeFrame()
     */
    public WelcomeFrame activateWelcomeFrame() {
        return new WelcomeFrame(this, controller);
    }

    /**
     * Method to activate the MainFrame/PlayFrame.
     */
    public void activateMainFrame() {
        mainFrame = new MainFrame(this, controller, calcController);
    }

    @Override
    public void update() {
        if (controller.getCurrentState() instanceof StateInGame) {
            mainFrame.changeText(controller.getPlayer().printPlayersHand(),
                    controller.getDealer().printPlayersHand());
        } else if (controller.getCurrentState() instanceof StateWon) {
            mainFrame.changeText(DIVIDINGLINE);
            mainFrame.changeText("Round WON!!!\n");
            mainFrame.changeText(calcController.printCurrentCreditState());
            mainFrame.changeText(DIVIDINGLINE);
        } else if (controller.getCurrentState() instanceof StateLost) {
            mainFrame.changeText(DIVIDINGLINE);
            mainFrame.changeText("Round LOST!!!\n");
            mainFrame.changeText(calcController.printCurrentCreditState());
            mainFrame.changeText(DIVIDINGLINE);
        } else if (controller.getCurrentState() instanceof StateBlackJack) {
            mainFrame.changeText(DIVIDINGLINE);

            if (this.controller.hasBlackJack(this.controller.getDealer())) {
                mainFrame.changeText("Dealer got BlackJack!\n\n");
            } else {
                mainFrame.changeText("Congratulations "
                        + this.controller.getPlayer().getName()
                        + ", you got BlackJack!\n");
            }
            mainFrame.changeText(calcController.printCurrentCreditState());
            mainFrame.changeText(DIVIDINGLINE);
        } else if (controller.getCurrentState() instanceof StateEndRound) {
            mainFrame.changeText("Round ended!");
            mainFrame.changeText("To start a new round set a new RoundStake please!");
        } else {
            mainFrame.changeText("END!");
        }
    }
}
