/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.panels.ClientForm;
import io.finnstainton.crewrosterlite.panels.CrewForm;
import io.finnstainton.crewrosterlite.panels.CrewSelectionForm;
import io.finnstainton.crewrosterlite.panels.EventForm;
import io.finnstainton.crewrosterlite.panels.JobForm;
import io.finnstainton.crewrosterlite.panels.job.JobPanel;
import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import io.finnstainton.crewrosterlite.panels.RosterForm;
import java.awt.CardLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;  
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * View
 * @author finnstainton
 */
public class CrewRosterLiteView extends JFrame implements Observer {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 700;
    private final NavbarPanel navbar;
    private JobPanel jobPanel = new JobPanel();
    private JobForm jobForm = new JobForm();
    private EventForm eventForm = new EventForm();
    private ClientForm clientForm = new ClientForm();
    private CrewForm crewForm = new CrewForm();
    private RosterForm rosterForm = new RosterForm();
    private CrewSelectionForm crewSltForm = new CrewSelectionForm();
    
    public CrewRosterLiteView() {
        super("CrewRoster Lite");
        
        this.setLayout(new CardLayout());
        this.setBackground(Color.GRAY);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){}
        
        // Add Navbar
        this.navbar = new NavbarPanel();
        this.setJMenuBar(this.navbar);
        
        // Add Panels
        this.jobPanel.setSize(WIDTH, HEIGHT-this.navbar.getHeight());
        this.add(jobPanel);
        
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.jobPanel.addController(controller);
        this.navbar.addController(controller);
        this.addWindowListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
       
    }

    // Get Components 
    public JobForm getJobForm() {
        return jobForm;
    }

    public EventForm getEventForm() {
        return eventForm;
    }
    
    public ClientForm getClientForm() {
        return clientForm;
    }

    public CrewForm getCrewForm() {
        return crewForm;
    }

    public JobPanel getJobPanel() {
        return jobPanel;
    }

    public RosterForm getRosterForm() {
        return rosterForm;
    }

    public CrewSelectionForm getCrewSltForm() {
        return crewSltForm;
    }
    
    
    
}

//
//        
//        this.setLayout(new GridLayout());
//        this.signinPanel = new SignInPanel();
//        this.signupPanel = new SignUpPanel();
//        this.jobPanel = new JobPanel(model.getJobRecords());
//        
//        content.setLayout(cardLayout);
//        content.add(this.signinPanel);
//        content.add(this.signupPanel);
//        content.add(this.jobPanel);
//        
//        this.cardLayout.addLayoutComponent(this.signinPanel, "signin");
//        this.cardLayout.addLayoutComponent(this.signupPanel, "signup");
//        this.cardLayout.addLayoutComponent(this.jobPanel, "job");
//        this.cardLayout.show(content, "job");
//        
//        this.add(content);