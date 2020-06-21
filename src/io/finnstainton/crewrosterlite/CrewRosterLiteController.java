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
import io.finnstainton.crewrosterlite.panels.EventForm;
import io.finnstainton.crewrosterlite.panels.JobForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
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
    private String currentJobID;
    
    public CrewRosterLiteController() {}
    
    public void addModel(CrewRosterLiteModel model) {
        this.model = model;
    }
    
    public void addView(CrewRosterLiteView view) {
        this.view = view;
    }
    
    // Listeners
    /**
     * Creates a new Job which the user presses done on the Job Form
     */
    public void jobFormListener() {
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
                String jobID = "JB" + String.format("%04d", (this.model.getJobRecords().getNumberValues() + 1));
                Job j = new Job(jobID, clientID, title, venue);
                
                this.model.getJobRecords().addValue(jobID, j);
                this.view.getJobForm().setVisible(false);
            }
        } catch(ClassCastException e) {
            this.view.getJobForm().setVisible(false);
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this.view,
                "An internal error occuried", 
                "Event Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                 null, options, null);
        }
    }
    
    /**
     * Creates a new Event which the user presses done on the Event Form
     */
    public void eventFormListener() {
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
                        String eventID = "EV" + j.getID() + String.format("%02d", (j.getEventRecords().getNumberValues()));
                        Event e = new Event(eventID, jobID, date, sTime, fTime, location, type);
                        j.getEventRecords().addValue(eventID, e);
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
                this.view.getEventForm().setVisible(false);
                Object[] options = {"Ok"};
                JOptionPane.showOptionDialog(this.view.getClientForm(),
                    "Couldn't find parent Job", "Event Error",JOptionPane.OK_OPTION,
                    JOptionPane.ERROR_MESSAGE, 
                    null, options, null);
            }
        } catch(ClassCastException e) {
            this.view.getEventForm().setVisible(false);
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this.view,
                "An internal error occuried", 
                "Event Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                 null, options, null);
        }
    }
    
    /**
     * Creates a new Client which the user presses done on the Client Form
     */
    public void clientFormListener() {
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
        } catch(NullPointerException e) {
            this.view.getClientForm().setVisible(false);
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this.view,
                "An internal error occuried", 
                "Client Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                 null, options, null);
        }
    }
    
    /**
     * Creates a new Crew which the user presses done on the Crew Form
     */
    public void crewFormListener() {
        try{
            String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
            String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
            String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

            // Check if all fields are filled out
            if(firstname == "" || lastname == "" || contact == "") {
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
                this.view.getClientForm().setVisible(false);
            }
        } catch(NullPointerException e) {
            this.view.getClientForm().setVisible(false);
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this.view,
                "An internal error occuried", 
                "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                 null, options, null);
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
                    this.view.getJobPanel().getEventListPanel().update(null, this.model.getJobRecords().getValue(this.currentJobID));
                } else {
                    this.view.getJobPanel().getJobDetailPanel().update(null, null);
                    this.view.getJobPanel().getEventListPanel().update(null, null);
                }
            }
        } else if(list.getName().equals("EventList")) {
            if(list.getValueIsAdjusting()) {
                if (list.getSelectedValue() != null) {
                    String eventID = list.getSelectedValue();
                    this.view.getJobPanel().getEventDetailPanel().update(null, this.model.getJobRecords().getValue(currentJobID).getEventRecords().getValue(eventID));
                } else {
                    this.view.getJobPanel().getEventDetailPanel().update(null, null);
                }
            }
        } 
    }
    
    // Small Action Events
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String action = e.getActionCommand();
            System.out.println("ActionEvent: " + action);
            switch(action){
                case "Login in":
                    break;
                case "Save":
                    break;
                case "Add Client":
                    ClientForm clientForm = this.view.getClientForm();
                    clientForm.revalidate();
                    clientForm.repaint();
                    clientForm.addController(this);
                    clientForm.pack();
                    clientForm.setVisible(true);
                    break;
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
                case "Add Event":
                    if(!this.model.getJobRecords().isEmpty()) {
                        EventForm eventForm = new EventForm();
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
                case "Add Crew":
                    CrewForm crewFrame = new CrewForm();
                    crewFrame.revalidate();
                    crewFrame.repaint();
                    crewFrame.addController(this);
                    crewFrame.pack();
                    crewFrame.setVisible(true);
                    break;
                case "Close Job Form":
                    this.view.getJobForm().setVisible(false);
                    break;
                case "Close Crew Form":
                    this.view.getJobForm().setVisible(false);
                    break;
                case "Close Client Form":
                    this.view.getClientForm().setVisible(false);
                    break;
                case "Close Event Form":
                    this.view.getEventForm().setVisible(false);
                    break;
                
            }
        } catch(NumberFormatException ex) {
            System.out.println(ex);
        }
    }

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
                this.model.saveToDB();
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