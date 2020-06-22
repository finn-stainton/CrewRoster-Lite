/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;

/**
 *
 * @author finnstainton
 */
public class NavbarPanel extends JMenuBar {
    private final JMenu addMenu, rosterMenu;
//    private final JTextField searchField;
    private JMenuItem addJob, addEvent, addClient, addCrew, rosterCrew;
    
            
    public NavbarPanel() {
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Title
        JLabel title = new JLabel("CrewRoster Lite", SwingConstants.LEFT);
        title.setFont(new Font("Open Sans", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        this.add(title);
        
        this.add(Box.createHorizontalGlue());
        this.setOpaque(true);
        this.add(Box.createRigidArea(new Dimension(25,50)));
        
        // Add Menu
        addMenu = new JMenu("Add Menu");
        addMenu.setForeground(Color.white);
        addMenu.setBackground(new Color(50,50,50));

        addJob = new JMenuItem("Add Job");
        addJob.getAccessibleContext().setAccessibleDescription("Add a new Job");
        addMenu.add(addJob);
        
        addEvent = new JMenuItem("Add Event");
        addEvent.getAccessibleContext().setAccessibleDescription("Add a new Event");
        addMenu.add(addEvent);
        
        addClient = new JMenuItem("Add Client");
        addClient.getAccessibleContext().setAccessibleDescription("Add a new Client");
        addMenu.add(addClient);
        
        addCrew = new JMenuItem("Add Crew");
        addCrew.getAccessibleContext().setAccessibleDescription("Add a new Crew Member");
        addMenu.add(addCrew);
        
        addMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        this.add(addMenu); 
        
        // Roster Menu
        rosterMenu = new JMenu("Roster Menu");
        rosterMenu.setForeground(Color.white);
        rosterMenu.setBackground(new Color(50,50,50));
        
        rosterCrew = new JMenuItem("Roster Crew");
        rosterCrew.getAccessibleContext().setAccessibleDescription("Roster Crew to an Event");
        rosterMenu.add(rosterCrew);
        
        rosterMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        this.add(rosterMenu);
    }
    
    public void addController(CrewRosterLiteController controller) {
        addJob.addActionListener(controller);
        addEvent.addActionListener(controller);
        addClient.addActionListener(controller);
        addCrew.addActionListener(controller);
        rosterCrew.addActionListener(controller);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(40,40,40));
//        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
    }
    
}
