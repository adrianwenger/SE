/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.controller.ICalcProfitController;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.*;

/**
 *
 * @author philippschultheiss
 */
public class SetRoundStake extends JPanel implements  ActionListener {

    private final ICalcProfitController calcController;
    JTextField tfRoundStake;
    JButton buttonSet;

    public SetRoundStake(ICalcProfitController cC){
        calcController = cC;
        
         //New JTextfield for roundstake und stake
        JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayout(3, 1));
        panel1.add(new JLabel("Your RoundStake:"));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3, 1));
        tfRoundStake = new JTextField(null, 20);
        panel2.add(tfRoundStake);

        Border border = BorderFactory.createTitledBorder("Stake menue");
        //this.setBorder(border);
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(panel1);
        this.add(panel2);
        buttonSet = new JButton("Set");
        this.add(buttonSet);
        //buttonSet.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source == buttonSet) {
            JOptionPane.showMessageDialog(buttonSet, "Your bet has been placed!");
            tfRoundStake.setText(null);
        }
    }
    
}
