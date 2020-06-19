/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Crew;
import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;
import io.finnstainton.crewrosterlite.model.Person;
import io.finnstainton.crewrosterlite.panels.ClientForm;
import io.finnstainton.crewrosterlite.panels.CrewForm;
import io.finnstainton.crewrosterlite.panels.EventForm;
import io.finnstainton.crewrosterlite.panels.JobForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author finnstainton
 */
public class CrewRosterLiteController implements ActionListener {
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
    public void JobFormDoneListener() {
        
        this.view.getJobForm().setVisible(false);
    }
    
    public void ClientFormListener() {
        try{
            String title = this.view.getClientForm().getTitleField().getText();
            String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
            String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
            String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

            if(title == null || firstname == null || lastname == null || contact == null) {
                Object[] options = {"Ok"};
                JOptionPane.showOptionDialog(this.view.getClientForm(),
                    "Please fill out all text fields", 
                    "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                     null, options, null);
            } else {
                String personID = "CP" + String.format("%04d", (this.model.getClientRecords().getNumberValues() + 1));
                String clientID = "CT" + String.format("%04d", (this.model.getClientRecords().getNumberValues() + 1));

                Person person = new Person(personID, firstname, lastname, contact);
                Client client = new Client(clientID, title, person);
                this.model.getClientRecords().addValue(client.getID(), client);
                System.out.println(client);
                
                //Update DB
                this.model.getDb().addClient(client);

                this.view.getClientForm().setVisible(false);
            }
        } catch(NullPointerException e) {
            this.view.getClientForm().setVisible(false);
            Object[] options = {"Ok"};
            JOptionPane.showOptionDialog(this.view,
                "Ohh noooo. Something when wrong", 
                "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                 null, options, null);
        }
    }
    
    public void CrewFormListener() {
        try{
            String firstname = this.view.getClientForm().getPersonPanel().getFirstName().getText();
            String lastname = this.view.getClientForm().getPersonPanel().getLastName().getText();
            String contact = this.view.getClientForm().getPersonPanel().getContact().getText();

            if(firstname == null || lastname == null || contact == null) {
                Object[] options = {"Ok"};
                JOptionPane.showOptionDialog(this.view.getClientForm(),
                    "Please fill out all text fields", 
                    "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
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
                "Ohh noooo. Something when wrong", 
                "Error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, 
                 null, options, null);
        }
    }

    public void EventFormListener() {
        
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
                    clientForm.addController(this);
                    clientForm.pack();
                    clientForm.setVisible(true);
                    break;
                case "Add Job": 
                    if(!this.model.getClientRecords().isEmpty()) {
                        JobForm jobFrom = this.view.getJobForm();
                        jobFrom.setClientOptions((String[])this.model.getClientRecords().getKeyArray());
                        jobFrom.addController(this);
                        jobFrom.pack();
                        jobFrom.setVisible(true);
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
}
