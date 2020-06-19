/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * A Job is the collection of events for a single client
 * @author finnstainton (17982742)
 */
public class Job {
    private Map<String, Event> eventMap;
    private String ID;
    private Client client;
    private String title;
    
    public Job(String ID, Client client, String title) {
        if(ID.substring(0, 2).equals("JB")) {
            this.ID = ID;
        } else {
            this.ID = "JB" + ID;
        }
        this.eventMap = new LinkedHashMap<>();
        this.client = client;
        this.title = title;
    }

    /******************** Getter and Setters ********************/
    
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID() {
        return ID;
    }

    public Collection<Event> getEvents() {
        return this.eventMap.values();
    }
    
    /* Current unused methods
    private LocalDateTime getFirstDate() {
        return null;
    }
    
    private LocalDateTime getLastDate() {
        return null;
    }
    */
    
    /**
     * Prints Job Info directly to the console.
     * Also prints all events
     */
    public void print(){
        //Title
        String title = "Can't Find Title";
        if(this.getTitle() != null){
            title = this.getTitle();
        }
        System.out.println("Title: " + title);
        //Client
        System.out.println("Client: " + (this.getClient() != null  ? this.getClient() : "Can't Find Client"));
        //Events
        System.out.println("Events: ");
        this.printEvents();
    }
    
    /**
     * @return String summarising the Job
     */
    @Override
    public String toString() {
        return("Job #" + this.getID() + " " + this.getTitle() + " for " + this.getClient());
    }
    
    /******************** Event Section ************************/
    /**
     * Prints all events directly to the console.
     */
    public void printEvents(){
        if(this.eventMap != null){
            if(this.getNumberEvents() > 0) {
                for(String eventID : this.getEventIDs()){
                     System.out.println(this.getEvent(eventID));
                }
            } else {
                System.out.println("This Job doesn't have any Events");
            }
        } else {
            System.out.println("This Job doesn't have any Events");
        }
    }
    
    public void printEventSummaries(){
        if(this.eventMap != null){
            if(this.getNumberEvents() > 0) {
                System.out.println("Events");
                for(String eventID : this.getEventIDs()){
                    System.out.println(eventID);
                }
            }
        }
    }
    
    /**
     * Returns an Event from the eventMap given the event ID
     * @param eventID String associated with the Event to get
     * @return Event associated with the given eventID, or null
     */
    public Event getEvent(String eventID) {
        if(this.eventMap.containsKey(eventID)) {
            return this.eventMap.get(eventID);
        } else {
            return null;
        }
    }
    
    /**
     * Adds an Event to the Job
     * @param event Event object to be added
     * @return boolean regarding if the event has been added(true), or not(false)
     */
    public boolean addEvent(Event event) {
        if(!this.eventMap.containsKey(event.getID())) {
            this.eventMap.put(event.getID(), event);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Removes an Event from a Job eventMap given the event ID.
     * @param eventID String ID associated with the Event to be removed
     * @return Event that has been removed, or null
     */
    public Event removeEvent(String eventID) {
        return this.eventMap.remove(eventID);
    }
    
    /**
     * @return int Sum of number of Events stored
     */
    public int getNumberEvents() {
        if(this.eventMap != null) {
            return this.eventMap.size();
        } else {
            return 0;
        }
    }
    
    /**
     * Get all ID of events currently stored
     * @return String[] of Event IDs
     */
    public String[] getEventIDs(){
        Set<String> IDs = this.eventMap.keySet();
        String[] strings = new String[IDs.size()];

        //Iterate over events
        Iterator iterator = IDs.iterator();
        int counter = 0;
        while(iterator.hasNext()){
            strings[counter] = iterator.next().toString();
            counter++;
        }

        return strings;
    }
}
