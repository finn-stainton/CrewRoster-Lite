/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.panels;

import io.finnstainton.crewrosterlite.CrewRosterLiteController;
import io.finnstainton.crewrosterlite.model.Event;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;

/**
 *
 * @author finnstainton (17982742)
 */
public class RosterForm extends JFrame implements Observer{
    private final static int maxGap = 20;
    private DefaultListModel<String> listModel;
    private JList crewList;
    private JScrollPane scrollPane;
    private JComboBox eventBox;
    private JButton addButton = new JButton("Add Crew");
    private JButton deleteButton = new JButton("Remove Crew");
    private JButton doneButton = new JButton("Done");
    private Event event;
    
    public RosterForm() {
        super("Roster Crew");
        
        // Input Panel
        JPanel inputPanel = new JPanel();
        
        listModel = new DefaultListModel<>();
        crewList = new JList<>(listModel);
        crewList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        crewList.setLayoutOrientation(JList.VERTICAL);
        crewList.setVisibleRowCount(-1);
        crewList.setBorder(BorderFactory.createTitledBorder("Crew"));
        crewList.setName("CrewList");
        scrollPane = new JScrollPane(crewList);
        scrollPane.setPreferredSize(new Dimension(450, 170));
        inputPanel.add(scrollPane);
        this.scrollPane.setVisible(true);
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(0,3));
        buttonPanel.add(addButton);
        addButton.setActionCommand("Roster Add Crew");
        buttonPanel.add(deleteButton);
        deleteButton.setActionCommand("Roster Remove Crew");
        buttonPanel.add(doneButton);
        doneButton.setActionCommand("Close Roster Form");
        
        this.add(inputPanel, BorderLayout.NORTH);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        
        this.setPreferredSize(new Dimension(450, 250));
        this.setResizable(false);
        
        if(event != null) {
            this.setVisible(true);
        }
    }
    
    public void addController(CrewRosterLiteController controller) {
        addButton.addActionListener(controller);
        deleteButton.addActionListener(controller);
        doneButton.addActionListener(controller);
        crewList.addListSelectionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        Event event = (Event) arg;
        this.event = event;
        this.listModel.removeAllElements();
        this.listModel.addAll(new ArrayList<String>(Arrays.asList(event.getCrewIDs())));
        this.crewList.revalidate();
        this.crewList.repaint();
    }

    public JList getCrewList() {
        return crewList;
    }

    
}
