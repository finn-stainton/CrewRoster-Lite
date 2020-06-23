/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Event;
import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Records;
import io.finnstainton.crewrosterlite.model.Specialties;
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
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
/**
 *
 * @author finnstainton
 */
public class EventForm extends JFrame implements Observer{
    private final static int maxGap = 20;
    private JTextField dateField, startTimeField, finishTimeField, locationField;
    private JComboBox parentJob, eventType;
    private DefaultComboBoxModel<String> parentJobBoxModel = new DefaultComboBoxModel<>();
    private DefaultComboBoxModel<String> eventTypeBoxModel = new DefaultComboBoxModel<>();
    private JButton doneButton = new JButton("Done");
    private JButton cancelButton = new JButton("Cancel");
    private String[] eventTypes = Specialties.getValues();
    
    public EventForm() {
        super("Add Event");
        
            // Input Panel
            JPanel inputPanel = new JPanel(new GridLayout(6,2));
            
            inputPanel.add(new Label("Job:"));
            parentJob = new JComboBox(parentJobBoxModel);
            parentJob.setEditable(true);
            inputPanel.add(parentJob);
            
            inputPanel.add(new Label("Date(yyyy-mm-dd):"));
            dateField = new JTextField();
            inputPanel.add(dateField);
            
            inputPanel.add(new Label("Start Time (hh:mm):"));
            startTimeField = new JTextField();
            inputPanel.add(startTimeField);
            
            inputPanel.add(new Label("Finish Time (hh:mm):"));
            finishTimeField = new JTextField();
            inputPanel.add(finishTimeField);
            
            inputPanel.add(new Label("Location:"));
            locationField = new JTextField();
            inputPanel.add(locationField);
            
            inputPanel.add(new Label("Type:"));
            eventTypeBoxModel.addAll(new ArrayList<String>(Arrays.asList(Event.EventType.getValues())));
            eventType = new JComboBox(eventTypeBoxModel);
            eventType.setEditable(true);
            eventType.setSelectedItem(eventTypes[0]);
            inputPanel.add(eventType);

            // Button Panel
            JPanel buttonPanel = new JPanel(new GridLayout(0,2));
            buttonPanel.add(doneButton);
            doneButton.setActionCommand("Add New Event");
            buttonPanel.add(cancelButton);
            cancelButton.setActionCommand("Close Event Form");

            this.add(inputPanel, BorderLayout.NORTH);
            this.add(new JSeparator(), BorderLayout.CENTER);
            this.add(buttonPanel, BorderLayout.SOUTH);

            this.setAlwaysOnTop (true);
            this.setPreferredSize(new Dimension(300, 300));
            this.setResizable(false);
    }
    
    // Getters
    
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
    
    // Add 
    public void addController(CrewRosterLiteController controller) {
        doneButton.addActionListener(controller);
        cancelButton.addActionListener(controller);
        eventType.addActionListener(controller);
        parentJob.addActionListener(controller);
    }

    

    @Override
    public void update(Observable o, Object arg) {
        Records<Job> records = (Records<Job>)arg;
        if(records != null) {
            parentJobBoxModel.removeAllElements();
            parentJobBoxModel.addAll(new ArrayList<String>(Arrays.asList(records.getKeyArray())));
            parentJob.revalidate();
            parentJob.repaint();
        }
    }
}

