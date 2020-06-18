/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.view;

import io.finnstainton.crewrosterlite.model.Event;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import org.jdatepicker.JDatePicker;

/**
 *
 * @author finnstainton
 */
public class EventForm extends JFrame implements ActionListener{
    final static int maxGap = 20;
    
    private Event newEvent, oldEvent;
    
    public EventForm(String name, Event event) {
        super(name);
        try{
            this.newEvent = event;
            this.oldEvent = event;
        } catch(NullPointerException nullPointer) {
//            this.newEvent = 
        }
        setSize(200, 250);
        setResizable(false);
    }
    
    public void addComponents(Container pane) {
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        JPanel textPanel = new JPanel(new GridLayout(2,3));
        
        
        buttonPanel.add(new JButton("Done"));
        buttonPanel.add(new JButton("Cancel"));
        
        
        textPanel.add(new Label("Job:"));
        
        String[] patternExamples = {
            "dd MMMMM yyyy",
             "dd.MM.yy",
             "MM/dd/yy",
             "yyyy.MM.dd G 'at' hh:mm:ss z",
             "EEE, MMM d, ''yy",
            "h:mm a",
             "H:mm:ss:SSS",
            "K:mm a,z",
            "yyyy.MMMMM.dd GGG hh:mm aaa"
        };

        JComboBox patternList = new JComboBox(patternExamples);
        patternList.setEditable(true);
        patternList.addActionListener(this);
        
        textPanel.add(new Label("Event Start Date:"));
//        JDatePicker picker = new JDatePicker();
//        picker.setDate(Calendar.getInstance().getTime());
//        picker.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
//        textPanel.add(new JTextField());
        
        textPanel.add(patternList);
        
        pane.add(textPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void doneListener() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

