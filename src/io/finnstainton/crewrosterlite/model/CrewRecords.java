/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * CrewRecord records multiple Crew objects.
 * @author finnstainton (17982742)
 */
public class CrewRecords {
    private Map<String, Crew> crewMap;
    
    /**
     * Default Constructor sets up crewMap
     */
    public CrewRecords() {
        this.crewMap = new LinkedHashMap();
    }
    
    private String generate ID
    public boolean addCrew(String first, String last) {
        Crew crew = new Crew(this.crewMap.size())
        if(crew != null && this.crewMap != null) {
            if(!this.crewMap.containsKey(crew.getID())) {
                this.crewMap.put(crew.getID(), crew);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Adds Crew to the records
     * @param crew Crew object to add
     * @return boolean regarding if crew add (true), or not(false)
     */
    public boolean addCrew(Crew crew) {
        if(crew != null && this.crewMap != null) {
            if(!this.crewMap.containsKey(crew.getID())) {
                this.crewMap.put(crew.getID(), crew);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    /**
     * Removes Crew from the records
     * @param crewID String associated with the Crew to be removed
     * @return Crew that's been removed, or null
     */
    public Crew removeCrew(String crewID) {
        if(crewID != null && crewMap != null) {
            return crewMap.remove(crewID);
        } else {
            return null;
        }
    }
    
    /**
     * @return String[] with all current crew IDs in records 
     */
    public String[] getCrewIDs() {
        return (String[])this.crewMap.keySet().toArray();
    }
    
    /**
     * Get a crew object
     * @param ID String associated with a crew object
     * @return Crew object, or null
     */
    public Crew getCrew(String ID) {
        if(this.crewMap.containsKey(ID)) {
            return this.crewMap.get(ID);
        } else {
            return null;
        }
    }
    
    /**
     * @return boolean regarding if records are empty(true) or not(false)
     */
    public boolean isEmpty() {
        return this.crewMap.isEmpty();
    }
    
    /**
     * Prints all crew currently stored to console
     */
    public void print() {
        this.crewMap.values().forEach((c) -> {
            System.out.println("ID: " + c.getID() + ", "  + c);
        });
    }
    
    /**
     * Prints all crew in (if stored) given array to console 
     * @param IDs String[] of crew IDs
     */
    public void print(String[] IDs) {
        //For each ID
        for(String ID : IDs){
            if(this.crewMap.containsKey(ID)){
                System.out.println(this.crewMap.get(ID));
            }
        }
    }
}
