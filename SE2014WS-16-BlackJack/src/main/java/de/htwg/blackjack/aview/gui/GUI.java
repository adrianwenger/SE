package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import de.htwg.blackjack.util.observer.IObserver;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuListener;

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
     * set Bounds for menuframe startbutton.
     */
    private static final Rectangle MENU_FRAME_START_BUTTON
            = new Rectangle(300, 240, 200, 50);

    /**
     * set Bounds for menuframe exitbutton.
     */
    private static final Rectangle MENU_FRAME_EXIT_BUTTON
            = new Rectangle(300, 310, 200, 50);

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
     * JDialog which has to be saved that the program can dispose them after its
     * not in use anymore.
     */
    private JDialog notifyframe;
    /**
     * height of the bottom label.
     */
    private static final int BOTTOM_HEIGHT = 50;
    /**
     * height of the mainframe.
     */
    private static final int FRAME_HEIGHT = 610;
    /**
     * Dimension for the menuFrame.
     */
    private static final Dimension MENU_FRAME_SIZE = new Dimension(800, 360);
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
     * Border for selected Field.
     */
    private static final LineBorder SELECTED = new LineBorder(Color.RED, 4);

    /**
     * JButton to start or later to restart the Game.
     */
    private final JButton start = new JButton("Start Game");

    /**
     * default background for the menuframe.
     */
    private final ImageIcon backgroundMenu
            = new ImageIcon(getClass().getResource("background_menu.png"));
    /**
     * default Background for mainframe.
     */
    private final ImageIcon background
            = new ImageIcon(getClass().getResource("background.jpg"));

    /**
     * To save the IBlackJackController for all of the several UIs.
     */
    private final IBlackJackController controller;

    private final ICalcProfitController calcController;

    /**
     * Container which contains all object of the mainframe.
     */
    private final Container container;

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
     * JTextField for game output
     */
    private JTextField output;
    /**
     * JTextField where the player should input his name.
     */
    private JTextField player, textField;
    /**
     * JFrame for the menu.
     */
    private JFrame menuFrame;
    
    private JPanel contentPane;

    /**
     * Public Contructor to create a GUI.
     *
     * @param controller
     * @param calcController
     */
    public GUI(final IBlackJackController controller, final ICalcProfitController calcController) {
        this.controller = controller;
        this.calcController = calcController;
        controller.addObserver(this);

        //Initialize mainFrame
        this.setTitle("BlackJack");
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.container = new JLabel(background);
        this.add(container);
        //Initialize Menu
        this.menuFrame();
        //start Game
        this.newGame();
    }

    /**
     * Method build the menuframe.
     */
    private void menuFrame() {
        this.menuFrame = new JFrame("BlackJack");
        this.menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //build Buttons
        this.start.setBounds(MENU_FRAME_START_BUTTON);

        //this.start.addActionListener(new MenuListener());
        this.start.addActionListener(new NewListener());
        //Container setup
        JLabel startContainer = new JLabel(backgroundMenu);
        startContainer.setLayout(null);
        startContainer.add(start);
        //Frame setup
        this.menuFrame.add(startContainer);
        this.menuFrame.setSize(MENU_FRAME_SIZE);
        this.menuFrame.setResizable(false);
        this.menuFrame.setLocationRelativeTo(null);
        this.menuFrame.setVisible(true);
    }

    public void newGame() {
//        //new Layout
//        container.setLayout(new BorderLayout(0, 0));
//
//        //panel for the left description
//        JLabel left = new JLabel();
//        left.setPreferredSize(new Dimension(DESCRIPTION_WIDTH_HEIGHT,
//                FRAME_HEIGHT
//                - BOTTOM_HEIGHT
//                - DESCRIPTION_WIDTH_HEIGHT));
//        output = new JTextField();
//        output.setSize(40, 40);
//        left.add(output);
//        container.add(left, BorderLayout.WEST);
        
        contentPane = new JPanel();
        contentPane.setSize(800, 800);
        setContentPane(contentPane);

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        setLayout(new GridLayout(1, 3));
        JLabel stake = new JLabel("Total Stake");
        JLabel bet = new JLabel("Your bet");
        JLabel profit = new JLabel("Profit:");
        panel.add(stake);
        panel.add(bet);
        panel.add(profit);

        textField = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        panel.add(textField, gbc_textField);
        textField.setColumns(10);

        JPanel panel_1 = new JPanel();
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));

        JButton btnConfirm = new JButton("Next Card");
        panel_1.add(btnConfirm);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        JTextArea textArea = new JTextArea("Spiel Ausgabe!");
        textArea.setColumns(20);
        scrollPane.setViewportView(textArea);

        pack();

    }

    public void update() {
    }

    /**
     * ActionListener for the State of the game where the game is over and the
     * game starts. START / END
     */
    private class MenuListener implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {
            menuFrame.setVisible(false);
            JButton target = (JButton) e.getSource();
            switch (target.getText()) {
                case "Start Game":
                    controller.create();
                    setVisible(true);
                    break;
                default:
                    break;
            }
        }

    }

    private class NewListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            menuFrame.setVisible(false);
            JButton target = (JButton) e.getSource();
            switch (target.getText()) {
                case "Start Game":
                    controller.create();
                    setVisible(true);
                    //Create new Panel for JOptionPaneMessageDialog
                    JPanel panel = new JPanel();
                    // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten		
                    JTextField name = new JTextField();

                    // Array für unsere JComboBox
                    String comboBoxListe[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

                    //JComboBox mit number of decks wird erstellt
                    JComboBox numOfDecks = new JComboBox(comboBoxListe);

                    //Erstelling JTextField für gesamten Spieleinsatz
                    JTextField stake = new JTextField();

                    //JComboBox wird Panel hinzugefügt
                    panel.add(numOfDecks);

                    Object[] message = {"Name:", name,
                        "Number of Decks:", numOfDecks, "Total Stake (in €) :", stake};

                    JOptionPane pane = new JOptionPane(message,
                            JOptionPane.PLAIN_MESSAGE,
                            JOptionPane.OK_CANCEL_OPTION);
                    pane.createDialog(null, "Welcome to BlackJack...").setVisible(true);
                    break;
            }
        }

    }
}
