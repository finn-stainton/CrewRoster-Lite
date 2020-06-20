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
 *
 * @author finnstainton
 */
public class CrewRosterLiteController implements ActionListener, WindowListener, ListSelectionListener {
    private CrewRosterLiteModel model;
    private CrewRosterLiteView view;
    
    // Controllers
    
    public CrewRosterLiteController() {
    }
    
    public void addModel(CrewRosterLiteModel model) {
        this.model = model;
    }
    
    public void addView(CrewRosterLiteView view) {
        this.view = view;
    }
    
    // Listeners
    public void jobFormListener() {
        try {
            String title = this.view.getJobForm().getTitleField().getText();
            String clientID = (String)this.view.getJobForm().getClientBox().getSelectedItem();
            String venue = this.view.getJobForm().getVenueField().getText();
            
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
            
        }
    }
    
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
                
                if(sDate != "" || ssTime != "" || sfTime != "" || location != "" || stype != "") {
                    try{
                        date = LocalDate.parse(sDate);
                        sTime = LocalTime.parse(ssTime);
                        fTime = LocalTime.parse(sfTime);
                        type = Event.EventType.valueOf(stype);
                        
                        Job j = this.model.getJobRecords().getValue(jobID);
                        String eventID = "EV" + String.format("%04d", (j.getEventRecords().getNumberValues()));
                        Event e = new Event(eventID, jobID, date, sTime, fTime, location, type);
                        j.getEventRecords().addValue(eventID, e);
                        this.view.getEventForm().setVisible(false);
                    } catch(DateTimeParseException dateError) {
                        Object[] options = {"Ok"};
                        JOptionPane.showOptionDialog(this.view, 
                            "Date or Time entered incorrectly. Date(yyyy-mm-dd), Time(hh:mm)", 
                            "Date or Time Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                            null, options, null);
                    } catch(IllegalArgumentException enumError) {
                        
                    }
                }
            }
        } catch(ClassCastException e) {
            
        }
    }
    
    public void clientFormListener() {
        try{
            String title = this.view.getClientForm().getTitleField().getText();
            String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
            String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
            String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

            if(title.equals("") || firstname.equals("") || lastname.equals("") || contact.equals("")) {
                
            } else {
                String personID = "CP" + String.format("%04d", (this.model.getClientRecords().getNumberValues() + 1));
                String clientID = "CT" + String.format("%04d", (this.model.getClientRecords().getNumberValues() + 1));

                Person person = new Person(personID, firstname, lastname, contact);
                Client client = new Client(clientID, title, person);
                this.model.getClientRecords().addValue(client.getID(), client);
                System.out.println(client.getID() + ": " + client);
                
                //Update DB
                //this.model.getDb().addClient(client);

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
    
    public void crewFormListener() {
        try{
            String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
            String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
            String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

            if(firstname == null || lastname == null || contact == null) {
                Object[] options = {"Ok"};
                JOptionPane.showOptionDialog(this.view.getClientForm(),
                    "Please fill out all text fields", 
                    "Crew Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                     null, options, null);
            } else {
                String crewID = "CR" + String.format("%04d", (this.model.getCrewRecords().getNumberValues() + 1));

                Crew crew = new Crew(crewID, firstname, lastname, contact);
                this.model.getCrewRecords().addValue(crew.getID(), crew);
                System.out.println(crew);
                
                //Update DB
                this.model.getDb().addCrew(crew);

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
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        JList<String> list = (JList)e.getSource();
        if(list.getName().equals("JobList")) {
            if(list.getValueIsAdjusting()) {
                if (list.getSelectedValue() != null) {
                    String jobID = list.getSelectedValue();
                    this.view.getJobPanel().getJobDetailPanel().update(null, this.model.getJobRecords().getValue(jobID));
                } else {
                    this.view.getJobPanel().getJobDetailPanel().update(null, null);
                }
            }
        } else if(list.getName().equals("EventList")) {
            
        }
    }
    
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
                        jobForm.setClientOptions((String[])this.model.getClientRecords().getKeyArray());
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
                        eventForm.updateJobSummaries((String[])this.model.getJobRecords().getKeyArray());
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

//@Override
//    public void valueChanged(ListSelectionEvent e) {
//        JList<String> list = (JList)e.getSource();
//        if(list.getValueIsAdjusting()) {
//            if (list.getSelectedValue() != null) {
//                String nhi = list.getSelectedValue();
//
//                this.remove(patientPanel);
//                this.invalidate();
//                this.patientPanel = patientRecords.getPatient(nhi).getDisplayPanel();
//                this.add(patientPanel);
//                this.revalidate();
//                this.repaint();
//            } else {
//                this.remove(patientPanel);
//                this.invalidate();
//                this.patientPanel = nilSelected();
//                this.add(patientPanel);
//                this.revalidate();
//                this.repaint();
//            }
//        }
//    }