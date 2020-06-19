/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.model.Job;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;

/**
 *
 * @author finnstainton
 */
public class JobForm extends JFrame implements ActionListener{
    final static int maxGap = 20;
    
    private Job newJob, oldJob;
    
    public JobForm(String name, Job job) {
        super(name);
        try{
            this.newJob = job;
            this.oldJob = job;
        } catch(NullPointerException nullPointer) {
            //this.newJob = new Job();
        }
        setSize(200, 250);
        setResizable(false);
    }
    
    public void addComponents(Container pane) {
        JPanel buttonPanel = new JPanel(new GridLayout(0,2));
        JPanel textPanel = new JPanel(new GridLayout(2,3));
        
        
        buttonPanel.add(new JButton("Done"));
        buttonPanel.add(new JButton("Cancel"));
        textPanel.add(new Label("Job Title:"));
        textPanel.add(new JTextField());
        textPanel.add(new Label("Job Client:"));
//        textPanel.add(new JTextField());
        
        String[] patternExamples = {
            "dd MMMMM yyyy",
             "dd.MM.yy",
             "MM/dd/yy",
             "yyyy.MM.dd G 'at' hh:mm:ss z",
             "EEE, MMM d, ''yy",
            "h:mm a",
             "H:mm:ss:SSS",
            "K:mm a,z",
            "yyyy.MMMMM.dd GGG hh:mm aaa"
        };

        JComboBox patternList = new JComboBox(patternExamples);
        patternList.setEditable(true);
        patternList.addActionListener(this);
        
        textPanel.add(patternList);
        
        pane.add(textPanel, BorderLayout.NORTH);
        pane.add(new JSeparator(), BorderLayout.CENTER);
        pane.add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void doneListener() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
