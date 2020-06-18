/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.view.CrewForm;
import io.finnstainton.crewrosterlite.view.EventForm;
import io.finnstainton.crewrosterlite.view.JobForm;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Box;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author finnstainton
 */
public class NavbarPanel extends JMenuBar implements ActionListener, ItemListener{
    private final JMenu accountMenu;
    private final JMenu actionsMenu;
//    private final JTextField searchField;
    private JMenuItem menuItem;
    
            
    public NavbarPanel() {
        this.add(Box.createHorizontalGlue());
        actionsMenu = new JMenu("Actions");
        actionsMenu.setForeground(Color.white);
        actionsMenu.setBackground(new Color(40,40,40));
        accountMenu = new JMenu("Account");
        accountMenu.setForeground(Color.white);
        //cacountMenu.setBackground(new Color(40,40,40));
        accountMenu.setOpaque(true);
       
        // Setup actionsMenu
        menuItem = new JMenuItem("Add Job");
        menuItem.getAccessibleContext().setAccessibleDescription(
        "Add a new Job");
        menuItem.addActionListener(this);
        actionsMenu.add(menuItem);
        
        menuItem = new JMenuItem("Add Event");
        menuItem.getAccessibleContext().setAccessibleDescription(
        "Add a new Event");
        menuItem.addActionListener(this);
        actionsMenu.add(menuItem);
        
        menuItem = new JMenuItem("Add Client");
        menuItem.getAccessibleContext().setAccessibleDescription(
        "Add a new Client");
        menuItem.addActionListener(this);
        actionsMenu.add(menuItem);
        
        menuItem = new JMenuItem("Add Crew");
        menuItem.getAccessibleContext().setAccessibleDescription(
        "Add a new Crew Member");
        menuItem.addActionListener(this);
        actionsMenu.add(menuItem);
        actionsMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        this.add(actionsMenu);
        
        // Setup accountMenu
        menuItem = new JMenuItem("View Week Roster");
        menuItem.getAccessibleContext().setAccessibleDescription(
        "View the current accounts week roster");
        menuItem.addActionListener(this);
        accountMenu.add(menuItem);
        
        menuItem = new JMenuItem("Sign Out");
        menuItem.getAccessibleContext().setAccessibleDescription(
        "View the current accounts week roster");
        menuItem.addActionListener(this);
        accountMenu.add(menuItem);
        accountMenu.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT); 
        this.add(accountMenu);
        
        this.add(Box.createRigidArea(new Dimension(25,50)));
        
        
        
        
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
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()) {
            case "Add Job": 
                JobForm jobFrame = new JobForm("Add Job", null);
                
                jobFrame.addComponents(jobFrame.getContentPane());
                jobFrame.pack();
                jobFrame.setVisible(true);
                break;
            case "Add Event":
                EventForm eventFrame = new EventForm("Add Event", null);
                
                eventFrame.addComponents(eventFrame.getContentPane());
                eventFrame.pack();
                eventFrame.setVisible(true);
                break;
            case "Add Crew":
                CrewForm crewFrame = new CrewForm();
                crewFrame.pack();
                crewFrame.setVisible(true);
                break;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
