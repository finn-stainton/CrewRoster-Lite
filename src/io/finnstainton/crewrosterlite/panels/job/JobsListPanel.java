/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.model.JobRecords;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author finnstainton (17982742)
 */
public class JobsListPanel extends JPanel implements ListSelectionListener{
    private final JobRecords jobRecords;
    private final JScrollPane scrollPane;
    private final JList<String> list;
    private final DefaultListModel<String> listModel;
    
    public JobsListPanel(JobRecords jobRecords) {
        this.jobRecords = jobRecords;
        
        this.listModel = new DefaultListModel<>();
        String[] jobs = this.jobRecords.getJobsIDs();
        for(int c = 0; c < jobRecords.getNumberJobs(); c++){
            listModel.addElement(jobs[c]);
        }
        
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setBorder(BorderFactory.createTitledBorder("Jobs"));
        scrollPane = new JScrollPane(list);
        
        //Listeners
        list.addListSelectionListener(this);
        
        this.add(scrollPane);
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
