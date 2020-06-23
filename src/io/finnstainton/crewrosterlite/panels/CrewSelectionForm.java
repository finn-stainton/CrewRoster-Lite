/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Crew;
import io.finnstainton.crewrosterlite.model.Records;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 *
 * @author finns
 */
public class CrewSelectionForm extends JFrame implements Observer{;
    private DefaultComboBoxModel<String> crewListModel;
    private JComboBox crewBox;
    private JButton addButton = new JButton("Add");
    private JButton cancelButton = new JButton("Cancel");
    
    public CrewSelectionForm() {
        super("Crew");
        
        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(3,2));
        inputPanel.add(new Label("Crew ID:"));
        crewListModel = new DefaultComboBoxModel<>();
        crewBox = new JComboBox(crewListModel);
        crewBox.setEditable(true);
        inputPanel.add(crewBox);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        buttonPanel.add(addButton);
        addButton.setActionCommand("Slt Add Crew");
        buttonPanel.add(cancelButton);
        cancelButton.setActionCommand("Close Crew Slt Form");
        
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setPreferredSize(new Dimension(300, 300));
        this.setResizable(false);
    }
    
    public void addController(CrewRosterLiteController controller) {
        crewBox.addActionListener(controller);
        addButton.addActionListener(controller);
        cancelButton.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        Records<Crew> records = (Records<Crew>) arg;
        if(records != null) {
            crewListModel.removeAllElements();
            crewListModel.addAll(new ArrayList<String>(Arrays.asList(records.getKeyArray())));
            crewBox.revalidate();
            crewBox.repaint();
        }
    }

    public JComboBox getCrewBox() {
        return crewBox;
    }
    
}
