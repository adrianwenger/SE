/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.util.observer.IObserver;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author philippschultheiss
 */
public class BlackJackFrame extends JFrame implements IObserver {

    private final IBlackJackController controller;
    private Container pane;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;

    public BlackJackFrame(final IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);

        JMenuBar menuBar;

        JMenuItem newGameItem, closeGameItem;

        JMenu fileMenu;
        /**
         * Title
         */
        setTitle("HTWG BlackJack");
        /**
         * Exit Options
         */
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                JFrame frame = (JFrame) e.getSource();
                int x = JOptionPane.showConfirmDialog(frame, "Do you really want to quit HTWG BlackJack?", "Exit", JOptionPane.YES_NO_OPTION);

                if (x == JOptionPane.YES_OPTION) {
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
        /**
         * Create new Menu
         */
        menuBar = new JMenuBar();

        /*
         * BlackJack Menu
         */
        fileMenu = new JMenu("BackJack");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new NewListener());

        closeGameItem = new JMenuItem("Exit...");
        closeGameItem.addActionListener(new CloseListener());
        

        fileMenu.add(newGameItem);
        fileMenu.addSeparator();
        fileMenu.add(closeGameItem);
        
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);
        setVisible(true);
    }

    public void update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    /**
     * Checks if input is a Integer
     * @param argument
     * @return 
     */
    private boolean isInt(String argument) {
        try {
            Integer.parseInt(argument);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private class CloseListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            int x = JOptionPane.showConfirmDialog(null, "Do you really want to quit HTWG BlackJack?", "Exit", JOptionPane.YES_NO_OPTION);
            if (x == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }

    private class NewListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            //Create new Panel for JOptionPaneMessageDialog
            JPanel panel = new JPanel();
            // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten		
            JTextField name = new JTextField();

            // Array für unsere JComboBox
            String comboBoxListe[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

            //JComboBox mit number of decks wird erstellt
            JComboBox numOfDecks = new JComboBox(comboBoxListe);

            //JComboBox wird Panel hinzugefügt
            panel.add(numOfDecks);

            Object[] message = {"Name:", name,
                "Number of Decks:", numOfDecks};

            JOptionPane pane = new JOptionPane(message,
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.OK_CANCEL_OPTION);
            pane.createDialog(null, "Welcome to BlackJack...").setVisible(true);
        }

    }
}
