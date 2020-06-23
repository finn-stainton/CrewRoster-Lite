/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton (17982742)
 */
public class CrewForm extends JFrame {
    private final static int maxGap = 20;
    private PersonPanel personPanel = new PersonPanel();
    private JButton doneButton = new JButton("Done");
    private JButton cancelButton = new JButton("Cancel");
    
    public CrewForm() {
        super("Add Crew");
        JPanel inputPanel = new JPanel(new GridLayout(1,2));
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.add(doneButton);
        buttonPanel.add(cancelButton);
        cancelButton.setActionCommand("Close Crew Form");
        
//        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(personPanel, BorderLayout.CENTER);
//        this.add(new JSeparator());
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setPreferredSize(new Dimension(300, 300));
        this.setResizable(false);
    }

    public PersonPanel getPersonPanel() {
        return personPanel;
    }
        
    public void addController(CrewRosterLiteController controller) {
        doneButton.addActionListener(e -> controller.crewFormListener());
        cancelButton.addActionListener(controller);
    }
}
