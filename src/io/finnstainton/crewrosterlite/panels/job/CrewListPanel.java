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

/**
 *
 * @author finnstainton (17982742)
 */
public class CrewListPanel extends JPanel implements Observer {
    private JScrollPane scrollPane;
    private JList<String> eventList;
    private DefaultListModel<String> listModel;
    private String[] values = new String[0];
    
    public CrewListPanel() {
        this.setBackground(Color.WHITE);
        
        listModel = new DefaultListModel<>();
        for(int c = 0; c < values.length; c++){
            listModel.addElement(values[c]);
        }
        eventList = new JList<>(listModel);
        eventList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventList.setLayoutOrientation(JList.VERTICAL);
        eventList.setVisibleRowCount(-1);
        eventList.setBorder(BorderFactory.createTitledBorder("Crew"));
        eventList.setName("CrewList");
        scrollPane = new JScrollPane(eventList);
        scrollPane.setPreferredSize(new Dimension(384, 450));
        this.add(scrollPane);
        this.scrollPane.setVisible(true);
    }
    
    public void updateCrewIDs(String[] eventIDs) {
        this.values = eventIDs;
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.eventList.addListSelectionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        try{
            this.values = (String[]) arg;
        } catch(ClassCastException c) {
            
        }
    }
    
}
