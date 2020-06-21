/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import io.finnstainton.crewrosterlite.Database;
import io.finnstainton.crewrosterlite.UpdateInfo;
import java.util.Arrays;
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
    private static Records<Crew> crewRecords;
    private static Records<Client> clientRecords;
    private static Records<Job> jobRecords;
    
    /**
     * Constructor, Sets up a {@code CrewRosterLite}
     */
    public CrewRosterLiteModel() {
        this.db = new Database();
        crewRecords = new Records<>();
        clientRecords = new Records<>();
        jobRecords = new Records<>();
        
        // Try load from db
        System.out.println("Loading from DB");
        db.loadCrew(crewRecords);
        db.loadClients(clientRecords);
        db.loadJobs(jobRecords);
        db.loadClientContacts(clientRecords);
        for(String jobID : jobRecords.getKeyArray()){
            db.loadJobEvents(jobRecords.getValue(jobID));
        }
        
        System.out.println("Loaded Jobs: " + Arrays.toString(jobRecords.getKeyArray()));
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
        }
        
        // Job
        for(String jobID : jobRecords.getKeyArray()) {
            db.addJob(jobRecords.getValue(jobID));
        }
    }
}