/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author finnstainton
 */
public class JobPanel extends JPanel {
    private final CardLayout cardLayout = new CardLayout();
    private final JobsListPanel jobList;
    private final JobDetailsPanel jobDetails;
    private final EventsListPanel eventList;
    private final EventDetailsPanel eventDetails;
    
    public JobPanel() {
        this.jobList = new JobsListPanel();
        this.jobDetails = new JobDetailsPanel();
        this.eventList = new EventsListPanel();
        this.eventDetails = new EventDetailsPanel();
        
        this.add(this.jobList);
        this.add(this.jobDetails);
        this.add(this.eventList);
        this.add(this.eventDetails);
    }
}
