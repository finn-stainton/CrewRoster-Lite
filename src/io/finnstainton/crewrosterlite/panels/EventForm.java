/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Specialties;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
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
public class EventForm extends JFrame {
    private final static int maxGap = 20;
    private JTextField dateField, startTimeField, finishTimeField, locationField;
    private JComboBox parentJob;
    private JComboBox eventType = new JComboBox();
    private JButton doneButton = new JButton("Done");
    private JButton cancelButton = new JButton("Cancel");
    private String[] jobSummaries = new String[0];
    private String[] eventTypes = Specialties.getValues();
    
    public EventForm() {
        super("Add Event");
        
        if(jobSummaries.length > 0) {
            // Input Panel
            JPanel inputPanel = new JPanel(new GridLayout(2,6));
            inputPanel.add(new Label("Job:"));
            parentJob = new JComboBox(jobSummaries);
            parentJob.setEditable(true);
            inputPanel.add(parentJob);
            inputPanel.add(new Label("Date:"));
            dateField = new JTextField();
            inputPanel.add(dateField);
            inputPanel.add(new Label("Start Time:"));
            startTimeField = new JTextField();
            inputPanel.add(startTimeField);
            inputPanel.add(new Label("Finish Date:"));
            finishTimeField = new JTextField();
            inputPanel.add(finishTimeField);
            inputPanel.add(new Label("Location:"));
            locationField = new JTextField();
            inputPanel.add(locationField);
            inputPanel.add(new Label("Type:"));
            eventType = new JComboBox(eventTypes);
            eventType.setEditable(true);
            eventType.setSelectedItem(eventTypes[0]);
            inputPanel.add(eventType);

            // Button Panel
            JPanel buttonPanel = new JPanel(new GridLayout(0,2));
            buttonPanel.add(doneButton);
            buttonPanel.add(cancelButton);
            cancelButton.setActionCommand("Close Event Form");

            this.add(inputPanel, BorderLayout.NORTH);
            this.add(new JSeparator(), BorderLayout.CENTER);
            this.add(buttonPanel, BorderLayout.SOUTH);

            this.setAlwaysOnTop (true);
            this.setPreferredSize(new Dimension(300, 300));
            this.setResizable(false);
        } else {}
    }
    
    public void updateJobSummaries(String[] jobSummaries) {
        this.jobSummaries = jobSummaries;
    }
    
    public void addController(CrewRosterLiteController controller) {
        doneButton.addActionListener(e -> controller.eventFormListener());
        cancelButton.addActionListener(controller);
        eventType.addActionListener(controller);
        parentJob.addActionListener(controller);
    }

    public JTextField getDateField() {
        return dateField;
    }

    public JTextField getStartTimeField() {
        return startTimeField;
    }

    public JTextField getFinishTimeField() {
        return finishTimeField;
    }

    public JTextField getLocationField() {
        return locationField;
    }

    public JComboBox getJobBox() {
        return parentJob;
    }

    public JComboBox getTypeBox() {
        return eventType;
    }
    
    
}

