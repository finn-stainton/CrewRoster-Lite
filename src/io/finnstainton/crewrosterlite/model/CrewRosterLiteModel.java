/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import io.finnstainton.crewrosterlite.Database;
import io.finnstainton.crewrosterlite.UpdateInfo;
import java.util.Observable;

/**
 * Model
 * @author finnstainton
 */
public class CrewRosterLiteModel extends Observable{
    private static final int MAX_YEAR = 2100;
//    private Database db;// Do you want to get all records from db or access them one at a time (maybe just load all IDs)?
    private UpdateInfo uInfo;
    private String[] crewIDs, clientIDs, jobIDs;
    private final Records<String, Client> crewRecords;
    private final Records<String, Client> clientRecords;
    private final Records<String, Job> jobRecords;
    
    /**
     * Constructor, Sets up a {@code CrewRosterLite}
     */
    public CrewRosterLiteModel() {
//        this.db = new Database();
        this.crewRecords = new Records<>();
        this.clientRecords = new Records<>();
        this.jobRecords = new Records<>();
    }

//    public Database getDb() {
//        return db;
//    }

    public Records<String, Client> getCrewRecords() {
        return crewRecords;
    }
    
    public Records<String, Client> getClientRecords() {
        return clientRecords; 
    }

    public Records<String, Job> getJobRecords() {
        return jobRecords;
    }
   
    
}

//public class CrewRecords extends Records<String, Crew> {
//        
//        public CrewRecords() {
//            super();
//        }
//        
//        public void getCrew(String ID) {
//            super.getValue(ID);
//        }
//    }