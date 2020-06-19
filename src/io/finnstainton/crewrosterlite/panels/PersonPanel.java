/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton
 */
public class PersonPanel extends JPanel {
    private JTextField firstName, lastName, contact;
    
    public PersonPanel() {
        this.firstName = new JTextField();
        this.lastName = new JTextField();
        this.contact = new JTextField();
        
        this.setLayout(new GridLayout(3, 2));
        this.add(new JLabel("First name: "));
        this.add(this.firstName);
        this.add(new JLabel("Last name: "));
        this.add(this.lastName);
        this.add(new JLabel("Contact: "));
        this.add(this.contact);
    }

    public JTextField getFirstName() {
        return firstName;
    }

    public JTextField getLastName() {
        return lastName;
    }

    public JTextField getContact() {
        return contact;
    }
}
