/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Event;
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
public class EventDetailPanel extends JPanel implements Observer{
    private Event event;
    private final JLabel dateLabel, sTimeLabel, fTimeLabel, locationLabel, typeLabel;
    private final JPanel detailPanel;
    private final CrewListPanel crewListPanel;
    private JSplitPane splitPane;
    
    public EventDetailPanel() {
        this.splitPane = new JSplitPane();
        
        // Event Details
        this.detailPanel = new JPanel(new GridLayout(6,2));
        this.detailPanel.setSize(new Dimension(380, 400));
        this.detailPanel.setBackground(Color.WHITE);
        
        this.detailPanel.add(new JLabel("Location: "));
        this.typeLabel = new JLabel("");
        this.typeLabel.setFont(new Font("Open Sans", Font.PLAIN, 32));
        this.detailPanel.add(this.typeLabel);
        
        this.detailPanel.add(new JLabel("Date: "));
        this.dateLabel = new JLabel("");
        this.dateLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.detailPanel.add(this.dateLabel);
        
        this.detailPanel.add(new JLabel("Start Time: "));
        this.sTimeLabel = new JLabel("");
        this.sTimeLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.detailPanel.add(this.sTimeLabel);
        
        this.detailPanel.add(new JLabel("Finish Time: "));
        this.fTimeLabel = new JLabel("");
        this.fTimeLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.detailPanel.add(this.fTimeLabel);
        
        this.detailPanel.add(new JLabel("Location: "));
        this.locationLabel = new JLabel("");
        this.locationLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.detailPanel.add(this.locationLabel);
        
        // Crew List
        this.crewListPanel = new CrewListPanel();
        this.crewListPanel.setSize(new Dimension(380, 400));
        crewListPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
                
        // Split Pane
        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, detailPanel, crewListPanel);
        this.add(splitPane);
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        try{
            this.event = (Event) arg;
            if(event != null) {
                this.dateLabel.setText(event.getDate().toString());
                this.sTimeLabel.setText(event.getStartTime().toString());
                this.fTimeLabel.setText(event.getFinishTime().toString());
                this.locationLabel.setText(event.getLocation());
                if(event.getType() == null) {
                    event.setType(Event.EventType.General);
                }
                this.typeLabel.setText(event.getType().toString());
                this.crewListPanel.updateList(arg);
                this.revalidate();
                this.repaint();
            } else {
                this.dateLabel.setText("");
                this.sTimeLabel.setText("");
                this.fTimeLabel.setText("");
                this.locationLabel.setText("");
                this.typeLabel.setText("");
                this.crewListPanel.updateList(null);
                this.revalidate();
                this.repaint();
            }
        } catch (Exception e) {}
    }
}
