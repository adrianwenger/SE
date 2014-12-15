/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.BlackJack;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.util.observer.IObserver;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.event.MenuListener;

/**
 *
 * @author philippschultheiss
 */
public class BlackJackFrame extends JFrame implements IObserver {

    private final IBlackJackController controller;
    private Container pane;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 200;

    public BlackJackFrame(final IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);

        JMenuBar menuBar;
        
        JMenuItem newMenuItem, newCloseMenuItem;

        JMenu fileMenu;

        /**
         * Title
         */
        setTitle("HTWG Sudoku");
        /**
         * Exit Options
         */
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                JFrame frame = (JFrame) e.getSource();
                int x = JOptionPane.showConfirmDialog(frame, "Do you really want to quit HTWG BlackJack?", "Exit", JOptionPane.YES_NO_OPTION);
                
                if(x == JOptionPane.YES_OPTION){
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                }
            }
        });

        /**
         * Set Width and Height of frame
         */
        setSize(WIDTH, HEIGHT);
        pane = getContentPane();
        pane.setLayout(new BorderLayout());

        menuBar = new JMenuBar();

        /*
         * File Menu
         */
        fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newMenuItem = new JMenuItem("New");
        newMenuItem.addActionListener(new NewListener());

        newCloseMenuItem = new JMenuItem("Exit");
        newCloseMenuItem.addActionListener(new CloseListener());
        
        fileMenu.add(newMenuItem);
        fileMenu.add(newCloseMenuItem);
        menuBar.add(fileMenu);
        
        this.setJMenuBar(menuBar);
        setVisible(true);
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class CloseListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int x = JOptionPane.showConfirmDialog(null, "Do you really want to quit HTWG BlackJack?", "Exit", JOptionPane.YES_NO_OPTION);
            if(x == JOptionPane.YES_OPTION){
                System.exit(0);
            }
        }
    }

    private class NewListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            
        }

    }
}