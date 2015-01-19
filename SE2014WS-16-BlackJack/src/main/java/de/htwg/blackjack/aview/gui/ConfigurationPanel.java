/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.IBlackJackController;
import static de.htwg.blackjack.util.StaticCollections.*;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * ActionListener for the State of the game where the game is over and the game
 * starts. START / END
 */
public class ConfigurationPanel implements ActionListener {

    private final GUI gui;
    private final IBlackJackController controller;
    private final WelcomeFrame frame;
    private JButton confirm;

    /**
     * JTextField for game output.
     */
    private JTextField name, stake;
    /**
     * JComboBox.
     */
    private JComboBox numOfDecks;
    /**
     * JDialog which has to be saved that the program can dispose them after its
     * not in use anymore.
     */
    private JDialog configPanel;
    /**
     * JDialog width.
     */
    private static final int width = 200;
    /**
     * JDialog height.
     */
    private static final int height = 250;
    /**
     *
     * @param gui
     * @param frame
     * @param controller
     */
    public ConfigurationPanel(GUI gui, WelcomeFrame frame, IBlackJackController controller) {
        this.gui = gui;
        this.frame = frame;
        this.controller = controller;

        //Create new Panel for JOptionPaneMessageDialog
        JPanel panel = new JPanel();
        // Erstellung Array vom Datentyp Object, Hinzufügen der Komponenten		
        name = new JTextField();

        // Array for JComboBox
        String comboBoxListe[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        //JComboBox with number of decks created
        numOfDecks = new JComboBox(comboBoxListe);

        //JTextField for total stake
        stake = new JTextField();

        //JComboBox wird Panel hinzugefügt
        panel.add(numOfDecks);

        confirm = new JButton("Confirm");
        confirm.addActionListener(this);
        JPanel dialogPanel = new JPanel(new GridLayout(CONFIGURE_ROWS, CONFIGURE_COLS));
        dialogPanel.add(new JLabel("Name:"));
        dialogPanel.add(name);
        dialogPanel.add(new JLabel("Number of Decks:"));
        dialogPanel.add(numOfDecks);
        dialogPanel.add(new JLabel("Total Stake (in €) :"));
        dialogPanel.add(stake);
        dialogPanel.add(confirm);

        configPanel = new JDialog();
        configPanel.setLocationRelativeTo(null);
        configPanel.setLocation(new Point((int) configPanel.getLocation().getX() 
                - (int) width/TWO, (int) configPanel.getLocation().getY() - (int) height/TWO));

        configPanel.add(dialogPanel);
        configPanel.setSize(CONFIGURE_LABEL_WIDTH, CONFIGURE_LABEL_HEIGTH);
        configPanel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if (source.equals(confirm)) {
            configPanel.setVisible(false);
            controller.setPlayer(name.getText());
            controller.setDealer();
            controller.setDeck(Integer.parseInt(numOfDecks.getSelectedItem().toString()));
            controller.getPlayer().setStake(Double.parseDouble(stake.getText()));
            gui.activateMainFrame();
        }
    }
}
