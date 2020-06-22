/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Records;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * Displays all Job currently store in program model jobRecords
 * @author finnstainton (17982742)
 */
public class JobListPanel extends JPanel implements Observer{
    private JScrollPane scrollPane;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    
    public JobListPanel() {
        this.setBackground(Color.WHITE);
        
        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setBorder(BorderFactory.createTitledBorder("Jobs"));
        list.setName("JobList");
        scrollPane = new JScrollPane(list);
        scrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
        this.add(scrollPane);
        this.scrollPane.setVisible(true);
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.list.addListSelectionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            Records<Job> records = (Records<Job>) arg;
            this.remove(scrollPane);
            this.invalidate();
            this.listModel.removeAllElements();
            this.listModel.addAll(new ArrayList<String>(Arrays.asList(records.getKeyArray())));
            this.scrollPane = new JScrollPane(list);
            this.scrollPane.setPreferredSize(new Dimension(this.getWidth(), this.getHeight()));
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        } catch(ClassCastException c) {
            
        }
    }
    
} 
