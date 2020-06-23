/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton (17982742)
 */
public class ClientForm extends JFrame {
    private final static int maxGap = 20;
    private JTextField titleField = new JTextField();
    private PersonPanel personPanel = new PersonPanel();
    private JButton doneButton = new JButton("Done");
    private JButton cancelButton = new JButton("Cancel");
    
    public ClientForm() {
        super("Add Client");
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0,2));
        inputPanel.add(new Label("Client Title:"));
        inputPanel.add(titleField);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.add(doneButton);
        buttonPanel.add(cancelButton);
        cancelButton.setActionCommand("Close Client Form");
        
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(personPanel, BorderLayout.CENTER);
        //this.add(new JSeparator());
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setPreferredSize(new Dimension(300, 300));
        this.setResizable(false);
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public PersonPanel getPersonPanel() {
        return personPanel;
    }
        
    public void addController(CrewRosterLiteController controller) {
        doneButton.addActionListener(e -> controller.clientFormListener());
        cancelButton.addActionListener(controller);
    }
}
