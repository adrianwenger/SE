package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.controller.impl.StateBlackJack;
import de.htwg.blackjack.controller.impl.StateEndRound;
import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.util.observer.IObserver;
import de.htwg.blackjack.controller.impl.StateInGame;
import de.htwg.blackjack.controller.impl.StateLost;
import de.htwg.blackjack.controller.impl.StateWon;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

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

    private final WelcomeFrame welcomeFrame;
    private  MainFrame mainFrame;

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
        
        // initialize Player
        welcomeFrame = activateWelcomeFrame();
        
    }
    public WelcomeFrame activateWelcomeFrame() {
        return new WelcomeFrame(this, controller);
    }
    public void activateMainFrame() {
        mainFrame = new MainFrame(this, controller, calcController);
    }
    
    @Override
    public void update() {
             mainFrame.changeText(controller.getStatusLine());        
    }
}


