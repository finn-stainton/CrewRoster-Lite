/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import io.finnstainton.crewrosterlite.Database;
import java.util.Arrays;
import java.util.Observable;

/**
 * Model
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteModel extends Observable{
    private static final int MAX_YEAR = 2100;
    private Database db;// Do you want to get all records from db or access them one at a time (maybe just load all IDs)?
    private String[] crewIDs, clientIDs, jobIDs;
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
    
    public void saveToDB() {
        // Crew
        for(String crewID : crewRecords.getKeyArray()) {
            db.addCrew(crewRecords.getValue(crewID));
        }
        
        // Clients
        for(String clientID : clientRecords.getKeyArray()) {
            db.addClient(clientRecords.getValue(clientID));  
        }
        
        // Jobs
        for(String jobID : jobRecords.getKeyArray()) {
            db.addJob(jobRecords.getValue(jobID));
           Records<Event> eventRecords = jobRecords.getValue(jobID).getEventRecords();
            for(String eventID : eventRecords.getKeyArray()) {
                db.addEvent(eventRecords.getValue(eventID));
            }
        }
        
        
    }
}