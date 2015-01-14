package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.util.observer.IObserver;
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
     * width/height for the description labels.
     */
    private static final int DESCRIPTION_WIDTH_HEIGHT = 40;

    /**
     * default Font.
     */
    private static final Font FONT = new Font("Helvetica", Font.BOLD, 12);
    /**
     * width of the east label.
     */
    private static final int EAST_WIDTH = 100;

    /**
     * width of the mainframe.
     */
    private static final int FRAME_WIDTH = 1000;

    /**
     * height of the bottom label.
     */
    private static final int BOTTOM_HEIGHT = 50;
    /**
     * height of the mainframe.
     */
    private static final int FRAME_HEIGHT = 610;

    /**
     * set Bounds for playerframe textfield.
     */
    private static final Rectangle PLAYER_FRAME_TEXT
            = new Rectangle(25, 40, 250, 30);
    /**
     * set Bounds for playerframe label.
     */
    private static final Rectangle PLAYER_FRAME_LABEL
            = new Rectangle(25, 5, 250, 30);

    /**
     * set Bound for playerframe button.
     */
    private static final Rectangle PLAYER_FRAME_BUTTON
            = new Rectangle(75, 80, 150, 30);
    /**
     * Dimension for playername frame.
     */
    private static final Dimension PLAYER_FRAME = new Dimension(300, 150);
    /**
     * Dimension for playing frame.
     */
    private static final Dimension PLAYING_FRAME = new Dimension(800, 500);
    /**
     * Border for selected Field.
     */
    private static final LineBorder SELECTED = new LineBorder(Color.RED, 4);

    /**
     * To save the IBlackJackController for all of the several UIs.
     */
    private final IBlackJackController controller;

    private final ICalcProfitController calcController;

    /**
     * Container which contains all object of the mainframe.
     */
    private Container container;

    /**
     * JLabel for the center of the mainframe.
     */
    private JLabel center;

    /**
     * JLabel for the east side of the mainframe.
     */
    private JLabel east;

    /**
     * JLabel to send out instructions.
     */
    private JLabel ausgabe;

    /**
     * JTextField where the player should input his name.
     */
    private JTextField player, textField;
    /**
     * JFrame for the menu.
     */
    private JFrame startFrame;

    private JPanel contentPane;
    /**
     * default Background for mainframe.
     */
    private final ImageIcon background
            = new ImageIcon(getClass().getResource("background.jpg"));
    private final WelcomeFrame welcomeFrame;

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
        welcomeFrame = new WelcomeFrame(this, controller);
        
    }

    public void activateStartFrame() {
        new InfoPanel(this, controller, calcController);
        //startFrame.setVisible(true);
    }
    
    public void update() {
    }

}
