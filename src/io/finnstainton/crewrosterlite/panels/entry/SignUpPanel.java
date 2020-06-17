/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.entry;

import io.finnstainton.crewrosterlite.LargeButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton
 */
public class SignUpPanel extends JPanel {
    private final JTextField usernameText;
    private final JPasswordField passwordText;
    private final LargeButton signup;
    private final LargeButton signin;
    
    public SignUpPanel() {
        this.usernameText = new JTextField();
        this.passwordText = new JPasswordField();
        this.signup = new LargeButton();
        this.signin = new LargeButton();
    }
}
