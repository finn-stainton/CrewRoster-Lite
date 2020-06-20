/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

/**
 * A Job is the collection of events for a single client
 * @author finnstainton (17982742)
 */
public class Job {
    private Records<Event> eventRecords;
    private String ID;
    private String clientID;
    private String title;
    private String venue;
    
    public Job(String ID, String clientID, String title, String venue) {
        if(ID.substring(0, 2).equals("JB")) {
            this.ID = ID;
        } else {
            this.ID = "JB" + ID;
        }
        this.eventRecords = new Records<>();
        this.clientID = clientID;
        this.title = title;
        this.venue = venue;
        
        //this.eventRecords.addObserver();
    }

    /******************** Getter and Setters ********************/
    
    public String getClientID() {
        return clientID;
    }

    public void setClient(String clientID) {
        this.clientID = clientID;
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

    public Records getEventRecords() {
        return this.eventRecords;
    }

    public String getVenue() {
        return venue;
    }

    public void setEventRecords(Records<Event> eventRecords) {
        this.eventRecords = eventRecords;
    }

    public void setVenue(String venue) {
        this.venue = venue;
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
     * @return String summarising the Job
     */
    @Override
    public String toString() {
        return("Job #" + this.getID() + " " + this.getTitle() + " for " + this.getClientID());
    }
}
