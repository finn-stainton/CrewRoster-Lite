/*
 * Dreamt, Designed, Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteApp extends JFrame {
    private final CrewRosterLite model;
    private final CrewRosterView view;
    
    public CrewRosterLiteApp(String name) {
        super(name);
        this.model = new CrewRosterLite();
        this.view = new CrewRosterView();
        
        
        this.getContentPane().add(this.view);
        setSize(1000, 700);
        setResizable(false);
        
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Save", "Cancel"};
                int result = JOptionPane.showOptionDialog(getParent(), 
                        "Do you want to save your work?", "Exit?", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
                        null, options, options[0]);
                if (result == JOptionPane.YES_OPTION) {
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                } else {
                    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        
        
    }
    
    public static void main(String[] args) {
        JFrame frame = new CrewRosterLiteApp("CrewRoster Lite");
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
