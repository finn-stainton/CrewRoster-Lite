/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Crew;
import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;
import io.finnstainton.crewrosterlite.model.Event;
import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Person;
import io.finnstainton.crewrosterlite.panels.ClientForm;
import io.finnstainton.crewrosterlite.panels.CrewForm;
import io.finnstainton.crewrosterlite.panels.CrewSelectionForm;
import io.finnstainton.crewrosterlite.panels.EventForm;
import io.finnstainton.crewrosterlite.panels.JobForm;
import io.finnstainton.crewrosterlite.panels.RosterForm;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * Controller
 * @author finnstainton
 */
public class CrewRosterLiteController implements ActionListener, WindowListener, ListSelectionListener {
    private CrewRosterLiteModel model;
    private CrewRosterLiteView view;
    private String currentJobID, currentEventID, selectedCrewID;
    
    public CrewRosterLiteController() {}
    
    public void addModel(CrewRosterLiteModel model) {
        this.model = model;
    }
    
    public void addView(CrewRosterLiteView view) {
        this.view = view;
    }
    
    // All the Action Events
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String action = e.getActionCommand();
            System.out.println("ActionEvent: " + action);
            switch(action){
                //Opens ClientForm so the user can add a client
                case "Add Client":
                    ClientForm clientForm = this.view.getClientForm();
                    clientForm.revalidate();
                    clientForm.repaint();
                    clientForm.addController(this);
                    clientForm.pack();
                    clientForm.setVisible(true);
                    break;
                    
                //Creates a new Client which the user presses done on the Client Form   
                case "Add New Client":
                    try{
                        String title = this.view.getClientForm().getTitleField().getText();
                        String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
                        String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
                        String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

                        // Check if all fields are filled out
                        if(title.equals("") || firstname.equals("") || lastname.equals("") || contact.equals("")) {
                            Object[] options = {"Ok"};
                            JOptionPane.showOptionDialog(this.view.getClientForm(),
                                "Please fill out all text fields", 
                                "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                 null, options, null);
                        } else {
                            String personID = "CP" + String.format("%04d", (this.model.getClientRecords().getNumberValues() + 1));
                            String clientID = "CT" + String.format("%04d", (this.model.getClientRecords().getNumberValues() + 1));

                            Person person = new Person(personID, firstname, lastname, contact);
                            Client client = new Client(clientID, title);
                            client.setContact(person);
                            this.model.getClientRecords().addValue(client.getID(), client);
                            System.out.println(client.getID() + ": " + client);
                            this.view.getClientForm().setVisible(false);
                        }
                    } catch(NullPointerException ex) {
                        this.view.getClientForm().setVisible(false);
                        Object[] options = {"Ok"};
                        JOptionPane.showOptionDialog(this.view,
                            "An internal error occuried", 
                            "Client Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                             null, options, null);
                    } 
                    break;
                
                //Opens JobForm so the user can add a job
                case "Add Job": 
                    if(!this.model.getClientRecords().isEmpty()) {
                        JobForm jobForm = this.view.getJobForm();
                        jobForm.revalidate();
                        jobForm.repaint();
                        jobForm.addController(this);
                        jobForm.pack();
                        jobForm.setVisible(true);
                    } else {
                        Object[] options = {"OK"};
                        JOptionPane.showOptionDialog(this.view,
                            "Please add a clients before attempting to add a new Job.", 
                            "No Clients", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                            null, options, null);
                    }
                    break;
                   
                //Creates a new Job which the user presses done on the Job Form
                case "Add New Job":
                    try {
                        String title = this.view.getJobForm().getTitleField().getText();
                        String clientID = (String)this.view.getJobForm().getClientBox().getSelectedItem();
                        String venue = this.view.getJobForm().getVenueField().getText();

                        // Check if all fields are filled out
                        if(title.equals("") || clientID.equals("")) {
                            Object[] options = {"Ok"};
                            JOptionPane.showOptionDialog(this.view.getJobForm(),
                                "Please fill all text fields", 
                                "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                 null, options, null);
                        } else{
                            // Check client exists
                            if(this.model.getClientRecords().containsKey(clientID)) {
                                String jobID = "JB" + String.format("%04d", (this.model.getJobRecords().getNumberValues() + 1));
                                Job j = new Job(jobID, clientID, title, venue);
                                this.model.getJobRecords().addValue(jobID, j);
                                this.view.getJobForm().setVisible(false);
                            } else {
                                Object[] options = {"Ok"};
                                JOptionPane.showOptionDialog(this.view, "Unknown Client, please try again.", 
                                    "Event Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                    null, options, null);
                            }
                        }
                    } catch(ClassCastException castError) {
                        this.view.getJobForm().setVisible(false);
                        Object[] options = {"Ok"};
                        JOptionPane.showOptionDialog(this.view,
                            "An internal error occuried", 
                            "Event Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                             null, options, null);
                    }
                    break;
                    
                //Opens EventForm so the user can add an Event
                case "Add Event":
                    if(!this.model.getJobRecords().isEmpty()) {
                        EventForm eventForm = this.view.getEventForm();
                        eventForm.revalidate();
                        eventForm.repaint();
                        eventForm.addController(this);
                        eventForm.pack();
                        eventForm.setVisible(true);
                    } else {
                        Object[] options = {"OK"};
                        JOptionPane.showOptionDialog(this.view,
                            "Please Add a Job before attempting to add a new Event.", 
                            "No Jobs", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                            null, options, null);
                    }
                    break;
                   
                //Creates a new Event which the user presses done on the Event Form
                case "Add New Event":
                    try {
                        String jobID = (String)this.view.getEventForm().getJobBox().getSelectedItem();
                        if(this.model.getJobRecords().containsKey(jobID)){
                            LocalDate date;
                            LocalTime sTime, fTime;
                            Event.EventType type;
                            String sDate = this.view.getEventForm().getDateField().getText();
                            String ssTime = this.view.getEventForm().getStartTimeField().getText();
                            String sfTime = this.view.getEventForm().getFinishTimeField().getText();
                            String location = this.view.getEventForm().getLocationField().getText();
                            String stype = (String)this.view.getEventForm().getTypeBox().getSelectedItem();

                            // Check if all fields are filled out
                            if(sDate != "" || ssTime != "" || sfTime != "" || location != "" || stype != "") {
                                try{
                                    date = LocalDate.parse(sDate);
                                    sTime = LocalTime.parse(ssTime);
                                    fTime = LocalTime.parse(sfTime);
                                    type = Event.EventType.valueOf(stype);

                                    Job j = this.model.getJobRecords().getValue(jobID);
                                    String eventID = "EV" + j.getID().substring(2) + String.format("%02d", (j.getEventRecords().getNumberValues()));
                                    Event event = new Event(eventID, jobID, date, sTime, fTime, location, type);
                                    j.getEventRecords().addValue(eventID, event);

                                    //Update view
                                    this.view.getJobPanel().getJobDetailPanel().update(null, this.model.getJobRecords().getValue(currentJobID));
                                    this.view.getEventForm().setVisible(false);
                                } catch(DateTimeParseException dateError) { // Catch Date or time errors
                                    Object[] options = {"Ok"};
                                    JOptionPane.showOptionDialog(this.view, 
                                        "Date or Time entered incorrectly. Date(yyyy-mm-dd), Time(hh:mm)", 
                                        "Date or Time Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                        null, options, null);
                                } catch(IllegalArgumentException enumError) {
                                    this.view.getEventForm().setVisible(false);
                                    Object[] options = {"Ok"};
                                    JOptionPane.showOptionDialog(this.view,
                                        "An internal error occuried", "Event Error", 
                                        JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                        null, options, null);
                                }
                            } else {
                                Object[] options = {"Ok"};
                                JOptionPane.showOptionDialog(this.view.getClientForm(),
                                    "Please fill out all text fields", "Crew Error", 
                                    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                    null, options, null);
                            }
                        } else {
                            Object[] options = {"Ok"};
                            JOptionPane.showOptionDialog(this.view.getClientForm(),
                                "Couldn't find Job, please try again", "Event Error",JOptionPane.OK_OPTION,
                                JOptionPane.ERROR_MESSAGE, 
                                null, options, null);
                        }
                    } catch(ClassCastException castError) {
                        this.view.getEventForm().setVisible(false);
                        Object[] options = {"Ok"};
                        JOptionPane.showOptionDialog(this.view,
                            "An internal error occuried", 
                            "Event Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                             null, options, null);
                    }
                    break;
                    
                //Opens CrewForm so the user can add a crew
                case "Add Crew":
                    CrewForm crewFrame = this.view.getCrewForm();
                    crewFrame.revalidate();
                    crewFrame.repaint();
                    crewFrame.addController(this);
                    crewFrame.pack();
                    crewFrame.setVisible(true);
                    break;
                    
                //Creates a new Crew which the user presses done on the Crew Form
                case "Add New Crew":
                    try{
                        String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
                        String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
                        String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

                        // Check if all fields are filled out
                        if(firstname.equals("") || lastname.equals("") || contact.equals("")) {
                            Object[] options = {"Ok"};
                            JOptionPane.showOptionDialog(this.view.getClientForm(),
                                "Please fill out all text fields", 
                                "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                 null, options, null);
                        } else {
                            String crewID = "CR" + String.format("%04d", (this.model.getCrewRecords().getNumberValues() + 1));

                            // New Crew
                            Crew crew = new Crew(crewID, firstname, lastname, contact);
                            this.model.getCrewRecords().addValue(crew.getID(), crew);
                            System.out.println(crew);
                            this.view.getCrewForm().setVisible(false);
                        }
                    } catch(HeadlessException ex) {
                        this.view.getClientForm().setVisible(false);
                            Object[] options = {"Ok"};
                        JOptionPane.showOptionDialog(this.view,
                            "An internal error occuried", 
                            "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                             null, options, null);
                    }
                    break;

                    
                //Opens RosterForm so the user can add a crew to an Event, From Navbar
                case "Roster Crew":
                    if(this.currentEventID == null){
                        Object[] options = {"OK"};
                        JOptionPane.showOptionDialog(this.view,
                            "Please select an event before attempting to roster crew.", 
                            "Select an Event", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                            null, options, null);
                    } else if(this.model.getCrewRecords().isEmpty()) {
                        Object[] options = {"OK"};
                        JOptionPane.showOptionDialog(this.view,
                            "Please add crew before attempting to roster crew.", 
                            "Select an Event", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                            null, options, null);
                    } else {
                        RosterForm rosterFrame = this.view.getRosterForm();
                        rosterFrame.revalidate();
                        rosterFrame.repaint();
                        rosterFrame.addController(this);
                        rosterFrame.pack();
                        rosterFrame.setVisible(true);
                    }
                    break;
                    
                //Opens a sub form, CrewSelectionForm to let the user select a crew to add
                case "Roster Add Crew":
                        CrewSelectionForm crewSltFrame = this.view.getCrewSltForm();
                        crewSltFrame.revalidate();
                        crewSltFrame.repaint();
                        crewSltFrame.addController(this);
                        crewSltFrame.pack();
                        crewSltFrame.setVisible(true);
                    break;
                    
                //Removes a selected crew from an Event, FROM RosterForm
                case "Roster Remove Crew":
                    if(this.selectedCrewID == null) {
                        Object[] options = {"OK"};
                        JOptionPane.showOptionDialog(this.view.getRosterForm(),
                            "Please select a crew before attempting to remove.", 
                            "Select Crew", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                            null, options, null);
                    } else {
                        try{
                            Event ev = (Event)this.model.getJobRecords().getValue(currentJobID).getEventRecords().getValue(currentEventID);
                            System.out.println(Arrays.toString(ev.getCrewIDs()));
                            System.out.println(selectedCrewID);
                            if(ev.removeCrew(selectedCrewID)) {
                                this.view.getRosterForm().update(null, ev);
                                this.view.getRosterForm().getCrewList().revalidate();
                                this.view.getRosterForm().getCrewList().repaint();
                            } else {
                                Object[] options = {"OK"};
                                JOptionPane.showOptionDialog(this.view.getRosterForm(),
                                    "Sorry, Failed to remove crew.", 
                                    "Remove Crew", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                null, options, null);
                            }
                        } catch(HeadlessException ex){}
                    }
                    break;
                    
                // Add crew to event, From CrewSelectionForm
                case "Slt Add Crew":
                    try {
                        String crewID = (String)this.view.getCrewSltForm().getCrewBox().getSelectedItem();
                        if(crewID != null) {
                            Event ent = (Event)this.model.getJobRecords().getValue(currentJobID).getEventRecords().getValue(currentEventID);
                            if(this.model.getCrewRecords().containsKey(crewID)) {
                                if(ent.addCrew(crewID)) {
                                    this.view.getRosterForm().update(null, ent);
                                    this.view.getJobPanel().getEventDetailPanel().update(null, ent);
                                    this.view.getCrewSltForm().setVisible(false);
                                } else {
                                    Object[] options = {"Ok"};
                                    JOptionPane.showOptionDialog(this.view, crewID + " is already Rostered", 
                                        "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                         null, options, null);
                                }
                            } else {
                                Object[] options = {"Ok"};
                                JOptionPane.showOptionDialog(this.view, "That is an invalid crew, please try again.", 
                                    "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                     null, options, null);
                            }
                        } else {
                           this.view.getCrewSltForm().setVisible(false);
                            Object[] options = {"Ok"};
                            JOptionPane.showOptionDialog(this.view, "An internal error occuried", 
                                "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                                 null, options, null);
                        }
                    } catch (HeadlessException ex) {}
                    break;
                    
                 // Following close forms upon request.
                case "Close Job Form":
                    this.view.getJobForm().setVisible(false);
                    break;
                case "Close Crew Form":
                    this.view.getCrewForm().setVisible(false);
                    break;
                case "Close Client Form":
                    this.view.getClientForm().setVisible(false);
                    break;
                case "Close Event Form":
                    this.view.getEventForm().setVisible(false);
                    break;  
                case "Close Roster Form":
                    this.view.getRosterForm().setVisible(false);
                    this.view.getCrewSltForm().setVisible(false);
                    break;
                case "Close Crew Slt Form":
                    this.view.getCrewSltForm().setVisible(false);
                    break;
            }
        } catch(NumberFormatException ex) {
            System.out.println(ex);
        }
    }
    
    //List (Job and Event) Event listener
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList)e.getSource();
        // Job in JobListPanel selected
        if(list.getName().equals("JobList")) {
            if(list.getValueIsAdjusting()) {
                if (list.getSelectedValue() != null) {
                    this.currentJobID = list.getSelectedValue();
                    this.view.getJobPanel().getJobDetailPanel().update(null, this.model.getJobRecords().getValue(this.currentJobID));
                    this.view.getJobPanel().getEventDetailPanel().update(null, null);
                } else {
                    this.view.getJobPanel().getJobDetailPanel().update(null, null);
                    this.view.getJobPanel().getEventDetailPanel().update(null, null);
                }
            }
        } else if(list.getName().equals("EventList")) {
            if(list.getValueIsAdjusting()) {
                if (list.getSelectedValue() != null) {
                    String eventID = list.getSelectedValue();
                    this.view.getJobPanel().getEventDetailPanel().update(null, this.model.getJobRecords().getValue(currentJobID).getEventRecords().getValue(eventID));
                    this.view.getRosterForm().update(null, this.model.getJobRecords().getValue(currentJobID).getEventRecords().getValue(eventID));
                    this.currentEventID = eventID;
                } else {
                    this.view.getJobPanel().getEventDetailPanel().update(null, null);
                    this.view.getRosterForm().update(null, null);
                    this.currentEventID = null;
                }
            }
        } else if(list.getName().equals("CrewList")) {
            if(list.getValueIsAdjusting()) {
                if (list.getSelectedValue() != null) {
                    String crewID = list.getSelectedValue();
                    this.selectedCrewID = crewID;
                } else {
                    this.selectedCrewID = null;
                }
            }
        } 
    }
    
    //VVV Window Events VVV

    @Override
    public void windowOpened(WindowEvent e) {
        
    }

    @Override
    public void windowClosing(WindowEvent e) {
        Object[] options = {"Save", "Don't Save", "Cancel"};
        int result = JOptionPane.showOptionDialog(this.view, 
            "Do you want to save your work?", "Exit?", 
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, 
            null, options, options[0]);
                
        switch (result) {
            case JOptionPane.YES_OPTION:
                this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                if(!this.model.saveToDB()) {
                    JOptionPane.showOptionDialog(this.view, "Sorry, couldn't save data", "Crew Error", 
                        JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                        null, new Object[]{"Ok"}, null);
                }
                break;
            case JOptionPane.NO_OPTION:
                this.view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                break;
            default:
                this.view.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                break;
        }
    }

    @Override
    public void windowIconified(WindowEvent e) {
        
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        
    }

    @Override
    public void windowActivated(WindowEvent e) {
        
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
   
    }

    @Override
    public void windowClosed(WindowEvent e) {
        
    }
}