/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.view;

import io.finnstainton.crewrosterlite.controller.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;
import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import io.finnstainton.crewrosterlite.panels.entry.SignInPanel;
import io.finnstainton.crewrosterlite.panels.entry.SignUpPanel;
import io.finnstainton.crewrosterlite.panels.job.JobPanel;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * View
 * @author finnstainton
 */
public class CrewRosterLiteView extends JFrame implements Observer{
    private final NavbarPanel navbar;
    private final JPanel content = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final SignInPanel signinPanel;
    private final SignUpPanel signupPanel;
    private final JobPanel jobPanel;
    private final CrewRosterLiteModel model;
    
    
    // Views 
    private final JobView jobView;
    
    public CrewRosterLiteView(String name) {
        this.navbar = new NavbarPanel();
        
        // Panels
        
        
        this.add(this.view);
        
        this.getContentPane().add(this.view);
        setSize(1000, 700);
        setResizable(false);
        
        //FIXME: Options maybe inwrong order
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Save", "Cancel", "Don't Save"};
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
        
        this.setJMenuBar(navbar);
        
        
        this.setLayout(new GridLayout());
        this.signinPanel = new SignInPanel();
        this.signupPanel = new SignUpPanel();
        this.jobPanel = new JobPanel(model.getJobRecords());
        
        content.setLayout(cardLayout);
        content.add(this.signinPanel);
        content.add(this.signupPanel);
        content.add(this.jobPanel);
        
        this.cardLayout.addLayoutComponent(this.signinPanel, "signin");
        this.cardLayout.addLayoutComponent(this.signupPanel, "signup");
        this.cardLayout.addLayoutComponent(this.jobPanel, "job");
        this.cardLayout.show(content, "job");
        
        this.add(content);
    }
    
    public void addController(CrewRosterLiteController controller) {
        
    }

    public JPanel getContent() {
        return content;
    }

    public SignInPanel getSigninPanel() {
        return signinPanel;
    }

    public SignUpPanel getSignupPanel() {
        return signupPanel;
    }

    public JobPanel getJobPanel() {
        return jobPanel;
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
