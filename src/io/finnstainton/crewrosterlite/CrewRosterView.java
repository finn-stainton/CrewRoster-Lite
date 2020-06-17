/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import io.finnstainton.crewrosterlite.panels.entry.SignInPanel;
import io.finnstainton.crewrosterlite.panels.entry.SignUpPanel;
import io.finnstainton.crewrosterlite.panels.job.JobPanel;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;

/**
 *
 * @author finnstainton
 */
public class CrewRosterView extends JPanel {
    private final JPanel content = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final SignInPanel signinPanel;
    private final SignUpPanel signupPanel;
    private final JobPanel jobPanel;
    
    public CrewRosterView() {
        this.setLayout(new GridLayout());
        this.signinPanel = new SignInPanel();
        this.signupPanel = new SignUpPanel();
        this.jobPanel = new JobPanel();
        
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
}
