/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.finnstainton.crewrosterlite.panels;

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

/**
 *
 * @author finnstainton
 */
public class JobListPanel extends JPanel implements Observer{
    private JScrollPane scrollPane;
    private JList<String> list;
    private DefaultListModel<String> listModel;
    
    public JobListPanel() {
        this.setBackground(Color.WHITE);
        
        listModel = new DefaultListModel<>();
        String[] values = new String[]{"Job 1", "Job 2", "Job 3"};
        for(int c = 0; c < values.length; c++){
            listModel.addElement(values[c]);
        }
        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);
        list.setBorder(BorderFactory.createTitledBorder("Jobs"));
        scrollPane = new JScrollPane(list);
        //scrollPane.setPreferredSize(new Dimension(this.getWidth()-5, this.getHeight()-5));
        this.add(scrollPane);

    }
    
    public void addController(CrewRosterLiteController controller) {
       
    }

    @Override
    public void update(Observable o, Object arg) {
        
    }
    
} 
