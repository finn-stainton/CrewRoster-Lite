/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Event;
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
public class EventDetailPanel extends JPanel implements Observer{
    private Event event;
    private final JLabel dateLabel, sTimeLabel, fTimeLabel, locationLabel, typeLabel;
    
    public EventDetailPanel() {
        //this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        
        this.dateLabel = new JLabel("Date");
        this.dateLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.sTimeLabel = new JLabel("Start Time");
        this.sTimeLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.fTimeLabel = new JLabel("Finish Time");
        this.fTimeLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.locationLabel = new JLabel("Location");
        this.locationLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        this.typeLabel = new JLabel("Type");
        this.typeLabel.setFont(new Font("Open Sans", Font.PLAIN, 20));
        
        // Add to main panel
        this.add(this.dateLabel);
        this.add(this.sTimeLabel);
        this.add(this.fTimeLabel);
        this.add(this.locationLabel);
        this.add(this.typeLabel);
                
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        this.event = (Event) arg;
        if(event != null) {
            this.dateLabel.setText(event.getDate().toString());
            this.sTimeLabel.setText(event.getStartTime().toString());
            this.fTimeLabel.setText(event.getFinishTime().toString());
            this.locationLabel.setText(event.getLocation());
            this.typeLabel.setText(event.getType().toString());
            this.revalidate();
            this.repaint();
        } else {
            this.dateLabel.setText("");
            this.sTimeLabel.setText("");
            this.fTimeLabel.setText("");
            this.locationLabel.setText("");
            this.typeLabel.setText("");
            this.revalidate();
            this.repaint();
        }
    }
}
