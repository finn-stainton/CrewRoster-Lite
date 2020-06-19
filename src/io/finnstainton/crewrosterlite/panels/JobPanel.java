/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author finnstainton
 */
public class JobPanel extends JPanel implements Observer{
    private final JobDetailPanel jobDetailPanel = new JobDetailPanel();
    private final JobListPanel jobListPanel;
    private final EventDetailPanel eventDetailPanel;
    private final EventListPanel eventListPanel;
    
    private JScrollPane scrollPane;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    
    public JobPanel() {
        this.setLayout(new BorderLayout(10,10));
        
        this.jobDetailPanel.setPreferredSize(new Dimension(778, 110));
        this.jobListPanel = new JobListPanel();
        this.jobListPanel.setPreferredSize(new Dimension(192, 980));
        this.eventDetailPanel = new EventDetailPanel();
        this.eventDetailPanel.setPreferredSize(new Dimension(384, 420));
        this.eventListPanel = new EventListPanel();
        this.eventListPanel.setPreferredSize(new Dimension(384, 420));
        
        // Add to main panel
        this.add(this.jobDetailPanel, BorderLayout.NORTH);
        this.add(this.jobListPanel, BorderLayout.WEST);
        this.add(this.eventDetailPanel, BorderLayout.EAST);
        this.add(this.eventListPanel, BorderLayout.CENTER);
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
        this.eventListPanel.addController(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
} 
