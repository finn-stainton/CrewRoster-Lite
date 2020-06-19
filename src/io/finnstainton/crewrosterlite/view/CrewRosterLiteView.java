/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.view;

import io.finnstainton.crewrosterlite.controller.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


/**
 * View
 * @author finnstainton
 */
public class CrewRosterLiteView extends JFrame implements Observer{
    private final NavbarPanel navbar;
    
    public CrewRosterLiteView() {
        JFrame frame = new JFrame("CrewRoster Lite");
        
        // Navbar
        this.navbar = new NavbarPanel();
        frame.setJMenuBar(this.navbar);
        
        frame.addWindowListener(new WindowAdapter() {
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
        
        frame.setSize(1000, 700);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }

    @Override
    public void update(Observable o, Object arg) {
        
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