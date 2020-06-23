/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Events are day long periods of activity for a job
 * @author finnstainton (17982742)
 */
public class Event {
    
    /**
     * Enum of types of events that can occur
     */
    public enum EventType {
        General("General"), Preperation("Preperation"), PackIn("PackIn"), Operation("Operation"), PackOut("PackOut");
        private final String text;

        /**
         * @param text
         */
        private EventType(final String text) {
            this.text = text;
        }

        public static String[] getValues() {
            EventType[] eventType = values();
            String[] values = new String[eventType.length];

            for (int i = 0; i < eventType.length; i++) {
                values[i] = eventType[i].name();
            }

            return values;
        }

        @Override
        public String toString() {
            return text;
        }                                                                                                                                               
    }
    
    private String ID;
    private String parentJobID;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime finishTime;
    private String location;
    private EventType type;
    private HashSet<Specialties> specialtiesNeeded;
    private HashSet<String> crewIDs;
    
    /**
     * Creates new Event object
     * Future update, events will be a part of a tree to make parentJob variable redundant 
     * @param ID String id of the event
     * @param parentJobID String which is event is part of
     * @param date LocalDate of event
     * @param startTime LocalTime of event
     * @param finishTime LocalTime of Event
     * @param location String of where the event is happening
     * @param type EventType what type of event is it
     */
    public Event(String ID, String parentJobID, LocalDate date, LocalTime startTime, 
            LocalTime finishTime, String location, EventType type){
        
        //Check ID has prefix "EV"
        if(ID.substring(0, 2).equals("EV")) {
            this.ID = ID;
        } else {
            this.ID = "EV" + ID;
        }
        this.parentJobID = parentJobID;
        this.date = date;
        this.startTime = startTime;
        
        //Check finish time is after start time
        if(this.startTime.isBefore(finishTime)) {
            this.finishTime = finishTime;
        } else { //Set finish time to startTime
            this.finishTime = startTime;
        }
        
        //Prep is always done at the factory
        if(type.equals(EventType.Preperation)){
            this.location = "The Factory";
        } else {
            this.location = location;
        }
        
        this.crewIDs = new HashSet<>();
        this.specialtiesNeeded = new HashSet<>();
    }
    
    public Event(String ID, String parentJobID, LocalDate date, LocalTime startTime, 
            LocalTime finishTime, String location, String stringType){
        this(ID, parentJobID, date, startTime, finishTime, location, EventType.General);
        
        try {
            this.type = Event.EventType.valueOf(stringType);
        } catch(Exception e){}
        
    }
    
    /********************** Getter and Setters ********************/  
   
    public String getID() {    
        return ID;
    }

    public String getParentJob() {
        return parentJobID;
    }
       
    public LocalDate getDate() {
        return date;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
    
    public Set<Specialties> getSpecialties() {
        return this.specialtiesNeeded;
    }
    
    public int getNumberCrew() {
        return this.crewIDs.size();
    }
    
    public String[] getCrewIDs() {
        return this.crewIDs.toArray(new String[this.crewIDs.size()]);
    }
    
    /**
     * Check if crew in event
     * @param crewID String ID of Crew to check
     * @return boolean regarding if the Crew is on event (true) or not (false).
     */
    public boolean isCrewInEvent(String crewID) {
        return this.crewIDs.contains(crewID);
    }
    
    /**
     * Add crew to event
     * @param ID String ID of the Crew to be added
     * @return Boolean regarding if the Crew has be added (true) or not (false).
     */
    public boolean addCrew(String ID){
        if(!isCrewInEvent(ID)){
            return this.crewIDs.add(ID);
        }
        return false;
    }
    
    /**
     *  Remove Crew from event
     * @param ID String ID of the Crew to be removed
     * @return Boolean regarding if the Crew has be removed (true) or not (false).
     */
    public boolean removeCrew(String ID) {
        if(isCrewInEvent(ID)){
            return this.crewIDs.remove(ID);
        }
        return false;
    }

    /**
     * @return String representation of an Event
     */
    @Override
    public String toString() {
        StringBuilder output;
        
        output = new StringBuilder(this.type + " on " + this.date + " from " + 
            this.startTime + " and estimated to finish at " + this.finishTime + ",\n" + 
            "taking place at " + this.location + ".\nCrew: ");

        //Crew
        for(String ID : this.crewIDs) {
            //Crew crew = CrewRosterLiteModel.getCrewRecords().getCrew(ID);
            
            //if(crew != null) {
            //    output.append(crew.getInitials().concat(" "));
            //}
        }
            
        return output.toString();
    }
}
