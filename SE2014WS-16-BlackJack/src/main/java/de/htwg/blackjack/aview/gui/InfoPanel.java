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
public class InfoPanel extends JFrame {

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

    private final JButton set, doubleStake, newCard;

    public InfoPanel(GUI gui, IBlackJackController controller, ICalcProfitController calcController) {
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
        JLabel outName = new JLabel(controller.getPlayer().getName());
        JLabel numOfDecks = new JLabel("Number of Decks: ");
        JLabel outDecks = new JLabel(Integer.toString(controller.getDeck().getNumOfDecks()));
        JLabel stake = new JLabel("Your stake:");
        JLabel outStake = new JLabel(Double.toString(controller.getPlayer().getStake()) + " €");
        JLabel profit = new JLabel("Profit:");
        JLabel outProfit = new JLabel(Double.toString(calcController.getProfit()) + " €");
        JLabel currentRoundStake = new JLabel("Current RoundStake: ");
        JLabel outCurRoundStake = new JLabel(Double.toString(controller.getPlayer().getRoundStake()) + " €");
        JLabel roundStake = new JLabel("RoundStake: ");
        JTextField tfroundStake = new JTextField();
        tfroundStake.setBorder(BorderFactory.createLineBorder( Color.BLACK, 2));

        //JButtons
        set = new JButton("Set");
        set.setBackground(Color.darkGray);
        //set.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        set.addActionListener(new SetListener());
        
        doubleStake = new JButton("DoubleStake");
        doubleStake.setBackground(Color.BLACK);
        //doubleStake.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        doubleStake.addActionListener(new DoubleStakeListener());
        
        
        newCard = new JButton("Next Card");
        newCard.setBackground(Color.BLACK);
        //newCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        
        //Label zusammenbauen
        info.setLayout(new GridLayout(8, 2));
        info.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
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
        info.add(set);
        info.add(doubleStake);
        info.add(newCard, BorderLayout.PAGE_END);
        
        //Spielfeld
        JLabel field = new JLabel(infoBackground);
        field.setPreferredSize(new Dimension(500, 400));
        
        //TextArea Spielfeld
        JTextArea taGame = new JTextArea(600, 500);
        JScrollPane scrollPane = new JScrollPane(taGame);
        taGame.setEditable(false);
        
        //Spielfeld zusammenbauen
        field.add(taGame);
        
        //Hauptfenster zusammenbauen
        this.add(startContainer);
        this.add(info, BorderLayout.WEST);
        this.add(field, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);

        //Hauptfenster ausgeben
        this.pack();
        this.setVisible(true);

    }

    private class SetListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == set) {
                controller.getPlayer().setRoundStake(Double.parseDouble(set.getText()));
                set.setText("0");
            }
        }
    }

    private class DoubleStakeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object source = e.getSource();
            if (source == doubleStake) {
                controller.getPlayer().doubleRoundStake();
            }
        }
    }
}
