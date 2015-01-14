/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/**
 *
 * @author philippschultheiss
 */
class WelcomeFrame extends JFrame implements ActionListener {

    private final GUI gui;
    private JFrame menuFrame;

    private final IBlackJackController controller;

    /**
     * JButton to start or later to restart the Game.
     */
    private final JButton start = new JButton("Start Game");
    /**
     * Dimension for the menuFrame.
     */
    private static final Dimension MENU_FRAME_SIZE = new Dimension(800, 360);
    /**
     * set Bounds for menuframe startbutton.
     */
    private static final Rectangle MENU_FRAME_START_BUTTON
            = new Rectangle(300, 240, 200, 50);

    /**
     * default background for the menuframe.
     */
    private final ImageIcon backgroundMenu
            = new ImageIcon(getClass().getResource("background_menu.png"));

    public WelcomeFrame(GUI gui, IBlackJackController controller) {
        this.gui = gui;
        this.controller = controller;

        this.menuFrame = new JFrame("BlackJack");
        this.menuFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //build Buttons
        this.start.setBounds(MENU_FRAME_START_BUTTON);
        this.start.addActionListener(this);
        //Container setup
        JLabel startContainer = new JLabel(backgroundMenu);
        startContainer.setLayout(null);
        startContainer.add(start);
        //Frame setup
        this.menuFrame.add(startContainer);
        this.menuFrame.setSize(MENU_FRAME_SIZE);
        //this.menuFrame.setResizable(false);
        this.menuFrame.setLocationRelativeTo(null);
        this.menuFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == start) {
            controller.createNewRound();
            this.menuFrame.setVisible(false);
            new ConfigurationPanel(gui, this, controller);
            
        }
    }

}
