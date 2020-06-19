/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;

/**
 *
 * @author finnstainton
 */
public class NavbarPanel extends JMenuBar implements Observer{
    private final JMenu accountMenu;
    private final JMenu actionsMenu;
//    private final JTextField searchField;
    private JMenuItem addJob, addEvent, addClient, addCrew, viewRoster, signItem;
    
            
    public NavbarPanel() {
        
        //ImageIcon icon = new ImageIcon("../media/textlogo.png"); 
        JLabel title = new JLabel("CrewRoster Lite", SwingConstants.LEFT);
        //thumb.setIcon(icon);
        this.add(title);
        
        this.add(Box.createHorizontalGlue());
        
        actionsMenu = new JMenu("Actions");
//        actionsMenu.setForeground(Color.white);
//        actionsMenu.setBackground(new Color(40,40,40));
        accountMenu = new JMenu("Account");
//        accountMenu.setForeground(Color.white);
//        accountMenu.setBackground(new Color(40,40,40));
        accountMenu.setOpaque(true);
       
        // Setup actionsMenu
        addJob = new JMenuItem("Add Job");
        addJob.getAccessibleContext().setAccessibleDescription("Add a new Job");
        actionsMenu.add(addJob);
        
        addEvent = new JMenuItem("Add Event");
        addEvent.getAccessibleContext().setAccessibleDescription("Add a new Event");
        actionsMenu.add(addEvent);
        
        addClient = new JMenuItem("Add Client");
        addClient.getAccessibleContext().setAccessibleDescription("Add a new Client");
        actionsMenu.add(addClient);
        
        addCrew = new JMenuItem("Add Crew");
        addCrew.getAccessibleContext().setAccessibleDescription("Add a new Crew Member");
        actionsMenu.add(addCrew);
        
        actionsMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        this.add(actionsMenu);
        
        // Setup accountMenu
        viewRoster = new JMenuItem("View Week Roster");
        viewRoster.getAccessibleContext().setAccessibleDescription("View the current accounts week roster");
        
        accountMenu.add(viewRoster);
        
        signItem = new JMenuItem("Sign Out");
        signItem.getAccessibleContext().setAccessibleDescription("View the current accounts week roster");
        
        accountMenu.add(signItem);
        
        accountMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        this.add(accountMenu);
        
        this.add(Box.createRigidArea(new Dimension(25,50)));
    }
    
    public void addController(CrewRosterLiteController controller) {
        addJob.addActionListener(controller);
        addEvent.addActionListener(controller);
        addClient.addActionListener(controller);
        addCrew.addActionListener(controller);
        viewRoster.addActionListener(controller);
        signItem.addActionListener(controller);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
//        g2d.setColor(new Color(40,40,40));
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
    
    

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
