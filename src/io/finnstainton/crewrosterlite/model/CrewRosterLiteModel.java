/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import io.finnstainton.crewrosterlite.Database;
import java.util.Observable;

/**
 * Model
 * @author finnstainton
 */
public class CrewRosterLiteModel {
    public static final int MAX_YEAR = 2100;
    private static Records<String, Crew> crewRecords;
    private static Records<String, Client> clientRecords;
    private static Records<String, Job> jobRecords;
    
    /**
     * Constructor, Sets up a {@code CrewRosterLite}
     */
    public CrewRosterLiteModel() {
        crewRecords = new Records<>();
        clientRecords = new Records<>();
        jobRecords = new Records<>();
    }
    
    Database db = new Database();
    
    // Do you want to get all records from db or access them one at a time (maybe just load all IDs)?

    public static Records<String, Crew> getCrewRecords() {
        return crewRecords;
    }

    public static Records<String, Client> getClientRecords() {
        return clientRecords;
    }

    public static Records<String, Job> getJobRecords() {
        return jobRecords;
    }
    
    
}
