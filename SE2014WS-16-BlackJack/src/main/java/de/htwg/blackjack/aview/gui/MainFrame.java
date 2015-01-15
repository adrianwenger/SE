package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author philippschultheiss
 */
public class MainFrame extends JFrame {

    GUI gui;
    IBlackJackController controller;
    ICalcProfitController calcController;

    /**
     * Dimension for playing frame.
     */
    private static final Dimension PLAYING_FRAME = new Dimension(1000, 500);

    /**
     * default Background for info.
     */
    private final ImageIcon infoBackground
            = new ImageIcon(getClass().getResource("info.jpg"));
    /**
     * JTextField for RoundStake.
     */
    JTextField tfroundStake;
    /**
     * JButtons for ButtonGroup.
     */
    private final JButton set, doubleStake, nextCard, newRound;
    /**
     * JTextArea for OutputField.
     */
    private JTextArea taGame;
    /**
     * JLabels for InfoLabel.
     */
    private JLabel outStake, outProfit, outCurRoundStake;

    /**
     *
     * @param gui
     * @param controller
     * @param calcController
     */
    public MainFrame(GUI gui, IBlackJackController controller, ICalcProfitController calcController) {
        this.gui = gui;
        this.controller = controller;
        this.calcController = calcController;

        //Configurations for MainLabel
        this.setTitle("BlackJack");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(PLAYING_FRAME);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLocation(new Point((int) this.getLocation().getX() - (int) PLAYING_FRAME.getWidth() / 2, (int) this.getLocation().getY() - (int) PLAYING_FRAME.getHeight() / 2));

        //JLabel for background
        JLabel startContainer = new JLabel(infoBackground);
        startContainer.setLayout(null);

        //Info JLabel
        JLabel info = new JLabel(infoBackground);
        info.setPreferredSize(new Dimension(300, 500));

        //Info JLabels
        JLabel plName = new JLabel("Player: ");
        JLabel outName = new JLabel(controller.getPlayer().getName());
        JLabel numOfDecks = new JLabel("Number of Decks: ");
        JLabel outDecks = new JLabel(Integer.toString(controller.getDeck().getNumOfDecks()));
        JLabel stake = new JLabel("Your stake:");
        outStake = new JLabel(Double.toString(controller.getPlayer().getStake()) + " €");
        JLabel profit = new JLabel("Profit:");
        outProfit = new JLabel(Double.toString(calcController.getProfit()) + " €");
        JLabel currentRoundStake = new JLabel("Current RoundStake: ");
        outCurRoundStake = new JLabel(Double.toString(controller.getPlayer().getRoundStake()) + " €");
        JLabel roundStake = new JLabel("RoundStake: ");
        tfroundStake = new JTextField();
        tfroundStake.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        //Info Label zusammenbauen
        info.setLayout(new GridLayout(8, 2));
        info.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3), "Info"));
        info.add(plName);
        info.add(outName);
        info.add(numOfDecks);
        info.add(outDecks);
        info.add(stake);
        info.add(outStake);
        info.add(profit);
        info.add(outProfit);
        info.add(currentRoundStake);
        info.add(outCurRoundStake);
        info.add(roundStake);
        info.add(tfroundStake);

        //Button Label
        JLabel buttonGroup = new JLabel(infoBackground);
        buttonGroup.setPreferredSize(new Dimension(100, 200));
        buttonGroup.setLayout(new GridLayout(4, 1));

        //JButtons
        set = new JButton("Set");
        set.addActionListener(new SetListener());

        doubleStake = new JButton("DoubleStake");
        doubleStake.addActionListener(new DoubleStakeListener());

        nextCard = new JButton("Next Card");
        nextCard.addActionListener(new nexCardListener());

        newRound = new JButton("New Game");
        newRound.addActionListener(new newGameListener());

        //Button Label zusammenbauen
        buttonGroup.add(set);
        buttonGroup.add(doubleStake);
        buttonGroup.add(nextCard);
        buttonGroup.add(newRound);
        buttonGroup.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3), "Buttons"));

        //Spielfeld Label
        JLabel field = new JLabel(infoBackground);
        field.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        field.setPreferredSize(new Dimension(500, 200));
        field.setLayout(new BorderLayout());

        //TextArea Spielfeld
        taGame = new JTextArea();
        taGame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        taGame.setLineWrap(false);
        taGame.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(taGame);
        scrollPane.setPreferredSize(new Dimension(200, 200));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //Spielfeld zusammenbauen
        field.add(scrollPane, BorderLayout.CENTER);
        field.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3), "Output"));

        //Hauptfenster zusammenbauen
        this.add(startContainer);
        this.add(info, BorderLayout.WEST);
        this.add(buttonGroup, BorderLayout.CENTER);
        this.add(field, BorderLayout.EAST);

        //Hauptfenster ausgeben
        this.pack();
        this.setVisible(true);

    }

    /**
     * Method to setText or textchanges in the output textarea and the info
     * label.
     *
     * @param text
     */
    public void changeText(String text) {
        taGame.setText(taGame.getText() + "\n" + text);
        outCurRoundStake.setText(Double.toString(controller.getPlayer().getRoundStake()) + " €");
        outStake.setText(Double.toString(controller.getPlayer().getStake()) + " €");
        calcController.calcProfit();
        outProfit.setText(Double.toString(calcController.getProfit()) + " €");
    }

    /**
     * Method to setText or textchanges in the output textarea and the info
     * label.
     *
     * @param text
     */
    public void changeText(String player, String dealer) {
        taGame.setText(taGame.getText() + "\n" + player);
        taGame.setText(taGame.getText() + "\n" + dealer);
        outCurRoundStake.setText(Double.toString(controller.getPlayer().getRoundStake()) + " €");
        outStake.setText(Double.toString(controller.getPlayer().getStake()) + " €");
        calcController.calcProfit();
        outProfit.setText(Double.toString(calcController.getProfit()) + " €");
    }

    /**
     * ActionListener to start a new Game.
     */
    private class newGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            gui.activateWelcomeFrame();
            dispose();
        }
    }

    /**
     * ActionListener to deal next card. 
     */
    private class nexCardListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.getCardPlayer();
            controller.checkIfDealerNeedsCard();
            String playerCards = controller.getPlayer().printPlayersHand();
            String dealerCards = controller.getDealer().printPlayersHand();
            changeText(playerCards, dealerCards);
            controller.checkGameState();
        }
    }

    /**
     * ActionListener to set new round stake.
     */
    private class SetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            //if (controller.getCalcController().setRoundStake(Double.parseDouble(tfroundStake.getText()))) { 
            //controller.createNewRound();
            taGame.setText("");
            changeText("----------------  Welcome to BlackJack... " + controller.getPlayer().getName() + "  ---------------- \n");
            tfroundStake.setText("");
            controller.getFirstTwoCardsPlayer();
            controller.getFirstTwoCardsDealer();
//            String playerCards = controller.getPlayer().printPlayersHand();
//            String dealerCards = controller.getDealer().printPlayersHand();
//            changeText(playerCards, dealerCards);
            controller.checkGameState();

        }
    }

    /**
     * ActionListener to double round stake.
     */
    private class DoubleStakeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (calcController.checkDouble()) {
                controller.getPlayer().doubleRoundStake();
                changeText("-------------------------------------------------------------");
                changeText("RoundStake was doubled!");
                changeText("-------------------------------------------------------------");
                controller.checkGameState();
            } else {
                changeText("-------------------------------------------------------------");
                changeText("RoundStake can't be doubled!");
                changeText("-------------------------------------------------------------");
                controller.checkGameState();
            }
        }
    }
}
