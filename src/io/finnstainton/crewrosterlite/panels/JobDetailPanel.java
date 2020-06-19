/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.Color;
import java.awt.FlowLayout;
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
   private final JLabel jobTitleLabel;
   private final JLabel clientLabel;
   private final JLabel venueLabel;
    
    public JobDetailPanel() {
        this.setLayout(new FlowLayout());
        this.setBackground(Color.WHITE);
        
        this.jobTitleLabel = new JLabel("Job Title");
        this.jobTitleLabel.setFont(new Font("Open Sans", Font.PLAIN, 40));
        this.clientLabel = new JLabel("Client");
        this.venueLabel = new JLabel("Venue");
        
        // Add to main panel
        this.add(this.jobTitleLabel);
        this.add(this.clientLabel);
        this.add(this.venueLabel);
    }
    
    public void setJobDetails() {
        
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }
    

    @Override
    public void update(Observable o, Object arg) {
        // arg: field:data
        
        String[] args = arg.toString().split(":");
        
        switch(args[0]) {
            case "title":
                this.jobTitleLabel.setText(args[1]);
                break;
            case "client" :
                this.clientLabel.setText(args[1]);
                break;
            case "venue" :
                this.venueLabel.setText(args[1]);
                break;
        }
        this.repaint();
    }
}
    
//} 
//    @Override
//    public void paint(Graphics g){
//	g.drawString("Hello, World", 10, 10);
//    }
//
//    this.revalidate();
//    this.repaint()    
//
