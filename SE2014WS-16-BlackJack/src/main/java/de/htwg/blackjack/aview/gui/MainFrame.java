/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.ICalcProfitController;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
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
     * default Background for mainframe.
     */
    private final ImageIcon background
            = new ImageIcon(getClass().getResource("background.jpg"));

    /**
     * default Background for info.
     */
    private final ImageIcon infoBackground
            = new ImageIcon(getClass().getResource("info.jpg"));
    JTextField tfroundStake;
    private final JButton set, doubleStake, nextCard;
    private JTextArea taGame;
    private JLabel outName, outStake, outProfit, outCurRoundStake;

    public MainFrame(GUI gui, IBlackJackController controller, ICalcProfitController calcController) {
        this.gui = gui;
        this.controller = controller;
        this.calcController = calcController;

        this.setTitle("BlackJack");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(PLAYING_FRAME);

        JLabel startContainer = new JLabel(infoBackground);
        startContainer.setLayout(null);

        //Label
        JLabel info = new JLabel(infoBackground);
        info.setPreferredSize(new Dimension(400, 500));
        
        //JLabels
        JLabel plName = new JLabel("Player: ");
        outName = new JLabel(controller.getPlayer().getName());
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
        tfroundStake.setBorder(BorderFactory.createLineBorder( Color.BLACK, 2));

        
        //InfoLabel zusammenbauen
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
        buttonGroup.setPreferredSize(new Dimension(200, 200));
        buttonGroup.setLayout(new GridLayout(3, 1));
        
         //JButtons
        set = new JButton("Set");
        set.addActionListener(new SetListener());
        
        doubleStake = new JButton("DoubleStake");
        doubleStake.addActionListener(new DoubleStakeListener());
        
        nextCard = new JButton("Next Card");
        
        //ButtonLabel zusammenbauen
        buttonGroup.add(set);
        buttonGroup.add(doubleStake);
        buttonGroup.add(nextCard);
        buttonGroup.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3), "Buttons"));
        
        //Spielfeld Label
        JLabel field = new JLabel(infoBackground);
        field.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));
        field.setPreferredSize(new Dimension(300, 200));
        field.setLayout(new BorderLayout());
        
        //TextArea Spielfeld
        taGame = new JTextArea(20, 20);
        taGame.setPreferredSize(new Dimension(200, 200));
        taGame.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(taGame);
        taGame.setEditable(true);
        
        //Spielfeld zusammenbauen
        field.add(scrollPane, BorderLayout.CENTER);
        field.add(taGame, BorderLayout.CENTER);
        field.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK, 3), "Output"));
        
        //Hauptfenster zusammenbauen
        this.add(startContainer);
        this.add(info, BorderLayout.WEST);
        this.add(buttonGroup, BorderLayout.CENTER);
        this.add(field, BorderLayout.EAST);
        this.setLocationRelativeTo(null);

        //Hauptfenster ausgeben
        this.pack();
        this.setVisible(true);

    }
    
    public void changeText(String player, String dealer){
        taGame.setText(taGame.getText() + "\n" + player);
        taGame.setText(taGame.getText() + "\n" + dealer);
        outCurRoundStake.setText(Double.toString(controller.getPlayer().getRoundStake()));
        outStake.setText(Double.toString(controller.getPlayer().getStake()));
        outProfit.setText(Double.toString(calcController.getProfit()));
        
    }
    
    private class SetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                controller.getPlayer().setRoundStake(Double.parseDouble(tfroundStake.getText()));
                tfroundStake.setText("0");
                controller.getFirstTwoCardsPlayer();
                controller.getFirstTwoCardsDealer();
                controller.checkGameState();
        }
    }

    private class DoubleStakeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                controller.getPlayer().doubleRoundStake();
        }
    }
}
