/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.panels.NavbarPanel;
import io.finnstainton.crewrosterlite.panels.entry.SignInPanel;
import io.finnstainton.crewrosterlite.panels.entry.SignUpPanel;
import io.finnstainton.crewrosterlite.panels.job.JobPanel;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author finnstainton
 */
public class CrewRosterView extends JPanel {
    private final JPanel content = new JPanel();
    private final CardLayout cardLayout = new CardLayout();
    private final NavbarPanel navbar;
    private final SignInPanel signin;
    private final SignUpPanel signup;
    private final JobPanel job;
    
    public CrewRosterView() {
        
        this.navbar = new NavbarPanel();
        this.signin = new SignInPanel();
        this.signup = new SignUpPanel();
        this.job = new JobPanel();
        
    }
}
