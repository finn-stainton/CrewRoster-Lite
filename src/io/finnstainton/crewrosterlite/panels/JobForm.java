/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Records;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton
 */
public class JobForm extends JFrame implements Observer{
    private final static int maxGap = 20;
    private JTextField titleField = new JTextField();
    private JTextField venueField = new JTextField();
    private DefaultComboBoxModel<String> clientListModel;
    private JComboBox clientBox;
    private JButton doneButton = new JButton("Done");
    private JButton cancelButton = new JButton("Cancel");
    
    public JobForm() {
        super("Add Job");
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3,2));
        inputPanel.add(new Label("Job Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new Label("Venue:"));
        inputPanel.add(venueField);
        
        // Client Combo Box
        inputPanel.add(new Label("Job Client:"));
        clientListModel = new DefaultComboBoxModel<>();
        
        clientBox = new JComboBox(clientListModel);
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
        
        this.setAlwaysOnTop(true);
        this.setPreferredSize(new Dimension(300, 300));
        this.setResizable(false);
    }
    
    public void addController(CrewRosterLiteController controller) {
        clientBox.addActionListener(controller);
        doneButton.addActionListener(e -> controller.jobFormListener());
        cancelButton.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        Records<Client> records = (Records<Client>) arg;
        if(records != null) {
            clientListModel.addAll(new ArrayList<String>(Arrays.asList(records.getKeyArray())));
        }
    }

    public JTextField getTitleField() {
        return titleField;
    }

    public JComboBox getClientBox() {
        return clientBox;
    }

    public JTextField getVenueField() {
        return venueField;
    }
    
}
