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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton
 */
public class JobForm extends JFrame {
    private final static int maxGap = 20;
    private String[] clientOptions = new String[0];
    private JTextField titleField = new JTextField();
    private JComboBox clientBox = new JComboBox(clientOptions);
    private JButton doneButton = new JButton("Done");
    private JButton cancelButton = new JButton("Cancel");
    
    public JobForm() {
        super("Add Job");
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2,2));
        inputPanel.add(new Label("Job Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new Label("Job Client:"));
        clientBox.setEditable(true);
        inputPanel.add(clientBox);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.add(doneButton);
        buttonPanel.add(cancelButton);
        cancelButton.setActionCommand("Close Job Form");
        
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setAlwaysOnTop (true);
        this.setPreferredSize(new Dimension(300, 300));
        this.setResizable(false);
    }
    
    public void setClientOptions(String[] options) {
        this.clientOptions = options;
    }
    
    public void addController(CrewRosterLiteController controller) {
        clientBox.addActionListener(controller);
        doneButton.addActionListener(e -> controller.JobFormListener());
        cancelButton.addActionListener(controller);
    }
}
