/*
 * Nightmare, Designed and Developed by Finn Stainton (c) 2020
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
 *
 * @author finnstainton
 */
public class EventListPanel extends JPanel implements Observer {
    private JScrollPane scrollPane;
    private JList<String> eventList;
    private DefaultListModel<String> listModel;
    
    public EventListPanel() {
        this.setBackground(Color.WHITE);
        
        listModel = new DefaultListModel<>();
        eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setLayoutOrientation(JList.VERTICAL);
        eventList.setVisibleRowCount(-1);
        eventList.setBorder(BorderFactory.createTitledBorder("Events"));
        eventList.setName("EventList");
        scrollPane = new JScrollPane(eventList);
        scrollPane.setPreferredSize(new Dimension(380, 400));
        this.add(scrollPane);
        this.scrollPane.setVisible(true);
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.eventList.addListSelectionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            Job j = (Job)arg;
            this.remove(scrollPane);
            this.invalidate();
            this.listModel.removeAllElements();
            this.listModel.addAll(new ArrayList<String>(Arrays.asList(j.getEventRecords().getKeyArray())));
            this.scrollPane = new JScrollPane(eventList);
            this.scrollPane.setPreferredSize(new Dimension(380, 400));
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        } catch(ClassCastException c) {
            
        }
    }
    
} 
