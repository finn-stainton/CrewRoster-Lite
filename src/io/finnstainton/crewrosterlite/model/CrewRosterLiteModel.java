/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import io.finnstainton.crewrosterlite.Database;
import io.finnstainton.crewrosterlite.UpdateInfo;
import java.util.Observable;

/**
 * Model
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteModel extends Observable{
    private static final int MAX_YEAR = 2100;
    private Database db;// Do you want to get all records from db or access them one at a time (maybe just load all IDs)?
    private UpdateInfo uInfo;
    private String[] crewIDs, clientIDs, jobIDs;
    private final Records<Crew> crewRecords;
    private final Records< Client> clientRecords;
    private final Records< Job> jobRecords;
    
    /**
     * Constructor, Sets up a {@code CrewRosterLite}
     */
    public CrewRosterLiteModel() {
        this.db = new Database();
        this.crewRecords = new Records<>();
        this.clientRecords = new Records<>();
        this.jobRecords = new Records<>();
        
        // Try load from db
        db.loadCrew(crewRecords);
        db.loadClients(clientRecords);
        db.loadJobs(jobRecords);
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
        
        // Client
        for(String clientID : clientRecords.getKeyArray()) {
            db.addClient(clientRecords.getValue(clientID));
            
            // Contact
            
        }
        
        // Job
        for(String jobID : jobRecords.getKeyArray()) {
            Job j = jobRecords.getValue(jobID);
            db.addJob(j);
            
            // Event
            Records<Event> eventRecords = j.getEventRecords();
            for(String eventID : eventRecords.getKeyArray()) {
                db.addEvent(eventRecords.getValue(eventID));
            }
        }
    }
}