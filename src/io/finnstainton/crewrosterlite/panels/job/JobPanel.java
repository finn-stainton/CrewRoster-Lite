/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.model.JobRecords;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author finnstainton
 */
public class JobPanel extends JPanel implements ListSelectionListener{
    private final JobRecords jobRecords;
    private final JScrollPane scrollPane;
    private final JList<String> list;
    private final DefaultListModel<String> listModel;
    
    public JobPanel(JobRecords jobRecords) {
        super(new GridBagLayout());
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception e) {}
        
        setSize(1000-10, 700-50);
        this.jobRecords = jobRecords;
        GridBagConstraints constraints = new GridBagConstraints();
        
        //Setup Job List
        this.listModel = new DefaultListModel<>();
        String[] jobs;
        try{
            jobs = this.jobRecords.getJobsIDs();
            for(int c = 0; c < jobRecords.getNumberJobs(); c++){
                listModel.addElement(jobs[c]);
            }
        } catch(NullPointerException nullPointer) {
            jobs = new String[0];
        }
        
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setBorder(BorderFactory.createTitledBorder("Jobs"));
        scrollPane = new JScrollPane(list);
        
        constraints.fill = GridBagConstraints.VERTICAL;
        constraints.ipady = 40;      //make this component tall
        constraints.weightx = 0.0;
        constraints.gridwidth = 3;
        constraints.gridx = 0;
        constraints.gridy = 1;
        
        //Listeners
        list.addListSelectionListener(this);
        
        this.add(scrollPane, constraints);
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList)e.getSource();
        if(list.getValueIsAdjusting()) {
            if (list.getSelectedValue() != null) {
                
            } else {
                
            }
        }
    }
}