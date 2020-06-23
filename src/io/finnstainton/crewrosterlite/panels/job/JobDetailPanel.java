/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Job;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author finnstainton
 */
public class JobDetailPanel extends JPanel implements Observer{
    private Job job;
    private final JLabel jobTitleLabel;
    private final JLabel clientLabel;
    private final JLabel venueLabel;
    private final EventListPanel eventListPanel;
    private final JPanel detailPanel;
    private JSplitPane splitPane;
    
    public JobDetailPanel() {
        // Panel style parameters
        this.setBackground(Color.WHITE);
        
        // Job Details
        this.detailPanel = new JPanel(new GridLayout(3,1));
        this.detailPanel.setSize(new Dimension(340, 340));
        this.detailPanel.setBackground(Color.WHITE);
        this.detailPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.jobTitleLabel = new JLabel("No Job Selected");
        this.jobTitleLabel.setFont(new Font("Open Sans", Font.PLAIN, 40));
        this.clientLabel = new JLabel("");
        this.clientLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
        this.venueLabel = new JLabel("");
        this.venueLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
        
        //detailPanel.add(new JLabel("Title: "));
        detailPanel.add(this.jobTitleLabel);
        //detailPanel.add(new JLabel("Client: "));
        detailPanel.add(this.clientLabel);
        //detailPanel.add(new JLabel("Venue: "));
        detailPanel.add(this.venueLabel);
        
        
        // Job Events
        this.eventListPanel = new EventListPanel();
        this.eventListPanel.setSize(new Dimension(340, 400));
        eventListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Split Pane
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailPanel, eventListPanel);
        this.add(splitPane);
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }

    public EventListPanel getEventListPanel() {
        return eventListPanel;
    }
      
    @Override
    public void update(Observable o, Object arg) {
        this.job = (Job) arg;
        if(job != null) {
            this.jobTitleLabel.setText(job.getTitle());
            this.clientLabel.setText(job.getClientID());
            this.venueLabel.setText(job.getVenue());
            this.eventListPanel.update(null, arg);
            this.revalidate();
            this.repaint();
        } else {
            this.jobTitleLabel.setText("No Job Selected");
            this.clientLabel.setText("");
            this.venueLabel.setText("");
            this.eventListPanel.update(null, null);
            this.revalidate();
            this.repaint();
        }
    }
}
