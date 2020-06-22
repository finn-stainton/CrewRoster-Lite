/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author finnstainton
 */
public class JobPanel extends JPanel implements Observer{
    private final JobDetailPanel jobDetailPanel;
    private final JobListPanel jobListPanel;
    private final EventDetailPanel eventDetailPanel;
    

    public JobPanel() {
        this.setLayout(new BorderLayout(10,10));
        GridBagConstraints c = new GridBagConstraints();
        
        this.jobListPanel = new JobListPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        this.jobListPanel.setPreferredSize(new Dimension(200, this.getHeight()));
        this.add(this.jobListPanel, BorderLayout.LINE_START);
        
        this.jobDetailPanel = new JobDetailPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 0.5;
        c.gridx = 1;
        c.gridy = 0;
        this.jobDetailPanel.setPreferredSize(new Dimension(400, this.getHeight()));
        this.add(this.jobDetailPanel, BorderLayout.CENTER);
        
        this.eventDetailPanel = new EventDetailPanel();
        c.fill = GridBagConstraints.HORIZONTAL;
        //c.weightx = 0.5;
        c.gridx = 2;
        c.gridy = 0;
        this.eventDetailPanel.setPreferredSize(new Dimension(400, this.getHeight()));
        this.add(this.eventDetailPanel, BorderLayout.LINE_END);
        
    }
    
//    @Override
//    protected void paintComponent(Graphics g) {
//
//        super.paintComponent(g);
//            g.drawImage(Toolkit.getDefaultToolkit().createImage("../media/bg-1.jpg"), 0, 0, null);
//    }
    
    public void addController(CrewRosterLiteController controller) {
        this.jobDetailPanel.addController(controller);
        this.jobListPanel.addController(controller);
        this.eventDetailPanel.addController(controller);
        this.jobDetailPanel.getEventListPanel().addController(controller);
    }

    public JobDetailPanel getJobDetailPanel() {
        return jobDetailPanel;
    }

    public JobListPanel getJobListPanel() {
        return jobListPanel;
    }

    public EventDetailPanel getEventDetailPanel() {
        return eventDetailPanel;
    }
    
    @Override
    public void update(Observable o, Object arg) {
        //this.jobListPanel.update(o, arg);
        //Records<Job> records = (Records<Job>) arg;
        
        //this.jobListPanel.updateJobIDs(records.getKeyArray());
        //this.eventListPanel.update(null, arg);
    }
    
} 
