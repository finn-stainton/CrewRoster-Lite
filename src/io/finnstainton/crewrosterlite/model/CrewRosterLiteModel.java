/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import io.finnstainton.crewrosterlite.Database;
import java.util.Observable;

/**
 * Model
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteModel extends Observable{
    private Database db;
    private static Records<Crew> crewRecords;
    private static Records<Client> clientRecords;
    private static Records<Job> jobRecords;
    
    /**
     * Constructor, Sets up a {@code CrewRosterLiteModel}
     */
    public CrewRosterLiteModel() {
        this.db = new Database();
        crewRecords = new Records<>();
        clientRecords = new Records<>();
        jobRecords = new Records<>();
    }
    
    public Database getDb() {
        return db;
    }

    public Records<Crew> getCrewRecords() {
        return crewRecords;
    }
    
    public Records<Client> getClientRecords() {
        return clientRecords; 
    }

    public Records<Job> getJobRecords() {
        return jobRecords;
    }
    
    /**
     * Load Crew, Clients, Jobs and there Events from DB
     */
    public void loadFromDB() {
        // Try load from db
        System.out.println("Loading from DB");
        db.loadCrew(crewRecords);
        db.loadClients(clientRecords);
        db.loadJobs(jobRecords);
        db.loadClientContacts(clientRecords);
        for(String jobID : jobRecords.getKeyArray()){
            db.loadJobEvents(jobRecords.getValue(jobID));
        }
    }
    
    /**
     * Save Crew, Clients, Jobs and there Events to DB
     * @return boolean whether everything saved
     */
    public boolean saveToDB() {
        boolean success = true;
        
        // Crew
        System.out.println("Attempting to save Crew");
        for(String crewID : crewRecords.getKeyArray()) {
            success = db.addCrew(crewRecords.getValue(crewID));
        }
        
        // Clients
        System.out.println("Attempting to save Clients");
        for(String clientID : clientRecords.getKeyArray()) {
            success = db.addClient(clientRecords.getValue(clientID));  
        }
        
        // Jobs
        System.out.println("Attempting to save Jobs");
        for(String jobID : jobRecords.getKeyArray()) {
            success = db.addJob(jobRecords.getValue(jobID));
            Records<Event> eventRecords = jobRecords.getValue(jobID).getEventRecords();
            for(String eventID : eventRecords.getKeyArray()) {
                if(eventRecords.getValue(eventID) != null) {
                    success = db.addEvent(eventRecords.getValue(eventID));
                    
                }
                
            }
        }
        
        return success;
    }
}