/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.panels.ClientForm;
import io.finnstainton.crewrosterlite.panels.JobForm;
import io.finnstainton.crewrosterlite.panels.JobPanel;
import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;  
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 * View
 * @author finnstainton
 */
public class CrewRosterLiteView extends JFrame implements Observer {
    private final NavbarPanel navbar;
    private JobPanel jobPanel = new JobPanel();
    private JobForm jobForm = new JobForm();
    private ClientForm clientForm = new ClientForm();
    
    
    public CrewRosterLiteView() {
        super("CrewRoster Lite");
        
        this.setLayout(new CardLayout());
        this.setBackground(Color.GRAY);
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch(ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e){}
        
        // Add Panels
        this.add(jobPanel);
        
        // Add Navbar
        this.navbar = new NavbarPanel();
        this.setJMenuBar(this.navbar);
        
        // Window Close Listener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Save", "Don't Save", "Cancel"};
                int result = JOptionPane.showOptionDialog(getParent(), 
                        "Do you want to save your work?", "Exit?", 
                        JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
                        null, options, options[0]);
                switch (result) {
                    case JOptionPane.YES_OPTION:
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        break;
                    case JOptionPane.NO_OPTION:
                        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        break;
                    default:
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        break;
                }
            }
        });
        
        this.setSize(1000, 700);
        this.setResizable(false);
        
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.jobPanel.addController(controller);
        this.navbar.addController(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        UpdateInfo update = (UpdateInfo) arg;   
    }

    public JobForm getJobForm() {
        return jobForm;
    }

    public ClientForm getClientForm() {
        return clientForm;
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