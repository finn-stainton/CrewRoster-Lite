/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author finnstainton (17982742)
 */
public class JobListPanel extends JPanel implements Observer{
    private JScrollPane scrollPane;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    private String[] values = new String[0];
    
    public JobListPanel() {
        this.setBackground(Color.WHITE);
        
        listModel = new DefaultListModel<>();
        for(int c = 0; c < values.length; c++){
            listModel.addElement(values[c]);
        }
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setBorder(BorderFactory.createTitledBorder("Jobs"));
        list.setName("JobList");
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(192, 450));
        this.add(scrollPane);
        this.scrollPane.setVisible(true);
    }
    
    public void updateJobIDs(String[] jobIDs) {
        this.values = jobIDs;
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.list.addListSelectionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            if(arg == null) {
                this.values = new String[0];
            }
            this.values = (String[]) arg;
        } catch(ClassCastException c) {
            
        }
    }
    
} 
