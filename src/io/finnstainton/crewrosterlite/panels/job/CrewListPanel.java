/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels.job;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Event;
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
 * @author finnstainton (17982742)
 */
public class CrewListPanel extends JPanel {
    private JScrollPane scrollPane;
    private JList<String> crewList;
    private DefaultListModel<String> listModel;
    
    public CrewListPanel() {
        this.setBackground(Color.WHITE);
        
        listModel = new DefaultListModel<>(); 
        crewList = new JList<>(listModel);
        crewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        crewList.setLayoutOrientation(JList.VERTICAL);
        crewList.setVisibleRowCount(-1);
        crewList.setBorder(BorderFactory.createTitledBorder("Crew"));
        crewList.setName("CrewList");
        scrollPane = new JScrollPane(crewList);
        scrollPane.setPreferredSize(new Dimension(380, 400));
        this.add(scrollPane);
        this.scrollPane.setVisible(true);
    }
    
    public void addController(CrewRosterLiteController controller) {
        this.crewList.addListSelectionListener(controller);
    }

    public void updateList(Object arg) {
        try{
            Event e = (Event)arg;
            this.remove(scrollPane);
            this.invalidate();
            this.listModel.removeAllElements();
            this.listModel.addAll(new ArrayList<String>(Arrays.asList(e.getCrewIDs())));
            this.scrollPane = new JScrollPane(crewList);
            this.scrollPane.setPreferredSize(new Dimension(380, 400));
            this.add(scrollPane);
            this.revalidate();
            this.repaint();
        } catch(ClassCastException c) {
            
        }
    }
    
}
