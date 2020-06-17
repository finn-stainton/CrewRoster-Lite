/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author finnstainton
 */
public class JobPanel extends JPanel implements ActionListener, DocumentListener{
    private final JobsListPanel jobList;
    private final JobDetailsPanel jobDetails;
    private final EventsListPanel eventList;
    private final EventDetailsPanel eventDetails;
    
    public JobPanel() {
        super(new GridLayout(3,3));
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e) {}
        
        //
        this.jobList = new JobsListPanel();
        this.jobDetails = new JobDetailsPanel();
        this.eventList = new EventsListPanel();
        this.eventDetails = new EventDetailsPanel();
        
        //
        this.add(this.jobList);
        this.add(this.jobDetails);
        this.add(this.eventList);
        this.add(this.eventDetails);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
