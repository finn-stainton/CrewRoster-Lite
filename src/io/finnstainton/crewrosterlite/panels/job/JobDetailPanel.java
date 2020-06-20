/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Job;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author finnstainton
 */
public class JobDetailPanel extends JPanel implements Observer{
    private Job job;
    private final JLabel jobTitleLabel;
    private final JLabel clientLabel;
    private final JLabel venueLabel;
    
    public JobDetailPanel() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        
        this.jobTitleLabel = new JLabel("Job Title");
        this.jobTitleLabel.setFont(new Font("Open Sans", Font.PLAIN, 40));
        this.clientLabel = new JLabel("Client");
        this.clientLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
        this.venueLabel = new JLabel("Venue");
        this.venueLabel.setFont(new Font("Open Sans", Font.BOLD, 20));
        
        // Add to main panel
        this.add(this.jobTitleLabel, BorderLayout.NORTH);
        this.add(this.clientLabel, BorderLayout.CENTER);
        this.add(this.venueLabel, BorderLayout.SOUTH);
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.job = (Job) arg;
        if(job != null) {
            this.jobTitleLabel.setText(job.getTitle());
            this.clientLabel.setText(job.getClientID());
            this.venueLabel.setText(job.getVenue());
            this.revalidate();
            this.repaint();
        } else {
            this.jobTitleLabel.setText("");
            this.clientLabel.setText("");
            this.venueLabel.setText("");
            this.revalidate();
            this.repaint();
        }
    }
}
    
//    @Override
//    public void paint(Graphics g){
//	g.drawString("Hello, World", 10, 10);
//    }
//
//    this.revalidate();
//    this.repaint()    
//