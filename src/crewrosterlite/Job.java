/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package crewrosterlite;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
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
    
    /******************** Event Console Methods ***********************/
    /**
     * Enables the user to add an Event via the console.
     * @return Event that user chose, or null if they exited
     */
    public Event consoleAddEvent() {
        Scanner scanner = new Scanner(System.in);
        
        //Get an ID
        String IDNumber = String.format("%02d", (this.eventMap.size() + 1));
        String eventID = this.ID.substring(2).concat(IDNumber);
        
        boolean typeSet = false;
        Event.EventType type = null;
        
        while(!typeSet){
            System.out.println("\nChoose type of Event: ");
            
            //Print options
            int eventTypeCount = 0;
            for(Event.EventType t : Event.EventType.values()) {
                System.out.println("(" + eventTypeCount + ") " + t);
                eventTypeCount++;
            }
            
            String userInput = "";
            
            //Get user input
            try {
                System.out.print("Type: ");
                userInput = scanner.nextLine();
            
            } catch(InputMismatchException inputError) {
                //System.err.println("\n$Unsupported Input, Please enter a number.");
            } catch(NoSuchElementException NSEError) {//scanner system.in closed
                typeSet = true;
                System.out.println("\n$Error. Call a Nerd");
            }
            
            switch(userInput) {
                case "0":
                    type = Event.EventType.General;
                    break;
                case "1":
                    type = Event.EventType.Preperation;
                    break;
                case "2":
                    type = Event.EventType.PackIn;
                    break;
                case "3":
                    type = Event.EventType.Operation;
                    break;
                case "4":
                    type = Event.EventType.PackOut;
                    break;
                default:
                    System.out.println("Unsupported Option, Please try again.");
            }
            
            if(type != null) {
                typeSet = true;
            }
            
        }
        
        //Date
        System.out.println("\nEvent Date:");
        LocalDate date = this.consoleGetDate();
        
        //Times
        System.out.println("Event Start Time: ");
        LocalTime startTime = this.consoleGetTime();
        LocalTime finishTime = startTime;
        
        while(finishTime.compareTo(startTime) < 1) {
            System.out.println("Event Finish Time: ");
            finishTime = this.consoleGetTime();
        }
        
        //Location
        String location = "";
        if(!Event.EventType.Preperation.equals(type)) {
            System.out.print("Event Location: ");
            location = scanner.nextLine();
        }
        
        Event newEvent = new Event(eventID, this, date, startTime, finishTime, location, type);
        
        //Roster Crew
        newEvent.consoleRosterCrew();
        
        this.eventMap.put(newEvent.getID(), newEvent);
        
        return newEvent;
    }
    
    /**
     * Enables the user to edit an Event via the console.
     */
    public void consoleEditEvent() {
        Scanner scanner = new Scanner(System.in);
        
        String eventID = this.consoleGetEventID();
        
        if(this.eventMap.containsKey(eventID)){
            Event event = this.eventMap.get(eventID);
            
            //Start Date
            System.out.println("Current Date: " + event.getDate());
            String updateDate = "";
            while(!updateDate.equalsIgnoreCase("y") && !updateDate.equalsIgnoreCase("n")){
                System.out.print("Update Start Date (y/n):");
                updateDate = scanner.next();

                if(updateDate.equalsIgnoreCase("y")){
                    event.setDate(this.consoleGetDate());
                }
            } 
            
            //Start Time
            System.out.println("Current Start Time: " + event.getStartTime());
            String updateStart = "";
            while(!updateStart.equalsIgnoreCase("y") && !updateStart.equalsIgnoreCase("n")){
                System.out.print("Update Start Time (y/n):");
                updateStart = scanner.next();

                if(updateStart.equalsIgnoreCase("y")){
                    event.setStartTime(this.consoleGetTime());
                }
            } 
            
            //Finish Time
            System.out.println("Current Finish Time: " + event.getFinishTime());
            String updateFinish = "";
            while(!updateFinish.equalsIgnoreCase("y") && !updateFinish.equalsIgnoreCase("n")){
                System.out.print("Update End Date (y/n):");
                updateFinish = scanner.next();

                if(updateFinish.equalsIgnoreCase("y")){
                    event.setFinishTime(this.consoleGetTime());
                }
            } 
            
            //Location
            //if eventtype = prep, then can't change location
            String updateLocation = "";
            if(!event.getType().equals(Event.EventType.Preperation)){
                System.out.println("Current Location: " + event.getLocation());
                while(!updateLocation.equalsIgnoreCase("y") && !updateLocation.equalsIgnoreCase("n")){
                    System.out.print("Update Location (y/n)");
                    updateLocation = scanner.next();

                    if(updateLocation.equalsIgnoreCase("y")){
                        System.out.print("New Location: ");
                        String newLocation = scanner.next();
                        
                        event.setLocation(newLocation);
                    }
                } 
            }
            
            //Crew
            for(String crewID : event.getCrewIDs()){
                Crew crew = CrewRosterLiteApp.getCrewRecords().getCrew(crewID);
                
                if(crew != null) {
                    System.out.println("Do you want to remove " + crew);

                    String updateCrew = scanner.next();
                    boolean crewDone = false;

                    while(!crewDone) {
                        if(updateCrew.equalsIgnoreCase("Y")) {
                            event.removeCrew(crew.getID());
                            crewDone = true;
                        } else if(updateCrew.equalsIgnoreCase("n") || updateCrew.equalsIgnoreCase("exit")) {
                            crewDone = true;
                        } else {
                            System.out.println("Please enter 'y', 'n' or 'exit'.");
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Enables the user to remove an Event form the eventMap via the console.
     * @return Event which has been removed, or null
     */
    public Event consoleRemoveEvent() {
        
        //Print current events
        this.printEvents();
        
        String eventID = this.consoleGetEventID();
        Event removedEvent = null;
        
        if(eventID != null) {
            if(this.eventMap.containsKey(eventID)){
                removedEvent = this.eventMap.remove(eventID);
                System.out.println("Removed " + removedEvent);
            } else { //Console Get EventID should only return IDs that exist, but just incase.
                System.out.println(eventID + " couldn't be removed");
            }
        } else {
//            System.out.println("Remove failed");
        }
        
        return removedEvent;
    }
    
    /**
     * Used by other console methods to allow the user to enter the event ID
     * @return String ID of Event
     */
     public String consoleGetEventID() {
            Scanner scanner = new Scanner(System.in);
            String eventID = null;
            boolean eventFlag = false;

            if(this.eventMap != null && !this.eventMap.isEmpty()) {
                while(!eventFlag) {
                    System.out.print("Event ID: ");
                    eventID = scanner.nextLine();

                    if(eventID.equalsIgnoreCase("exit")){
                        eventID = null;
                        eventFlag = true;
                    } else if(this.eventMap.containsKey(eventID)){//Check if event exists
                        eventFlag = true;
                    } else{
                        System.out.println("Can't find Event# " + eventID);
                    } 
                }
            } else{
                eventID = null;
                System.out.println("No Events. Please add an Event first.");
            }

            return eventID;
        }
    
    
    /**
     * Enables the user to pick a upcoming date 
     * (lower than year 2100) via the console
     * @return LocalDate which user picked
     */
    private LocalDate consoleGetDate() {
        Scanner scanner = new Scanner(System.in);
        boolean dateSet = false;
        LocalDate date = LocalDate.now();
        
        //Date
        do{
            //Use current year?
            int year = 0;
//            System.out.print("Use year (" + year + ") (y/n): ");
//            String yearResponse = scanner.nextLine();
//            
//            if(!yearResponse.equalsIgnoreCase("y")) {
                do{
                    System.out.print("Year (yyyy): ");
                    try{
                        int newYear = 0;
                        newYear = scanner.nextInt();

                        //Check new Year
                        if(newYear > LocalDate.now().getYear() 
                                && newYear <= CrewRosterLiteApp.MAX_YEAR) {
                            year = newYear;
                        }
                    }catch(InputMismatchException inputError) {
                            
                    }
                }while(year > LocalDate.now().getYear());
//            }
            
            //Use current month
            int month = 0;
            do{
                System.out.print("Month (1-12): ");
                try{
                    month = scanner.nextInt();
                }catch(InputMismatchException inputError) {}
            } while (month < 1 || month > 13);
            
            int maxDay;
            switch (month) {
                case 2:
                    maxDay = 28;
                    break;
                case 4: //Fall Through
                case 6: //Fall Through
                case 9: //Fall Through
                case 11:
                    maxDay = 30;
                    break;
                default:
                    maxDay = 31;
                    break;
            }
           
            //Day    
            int day = 0;
            do{
                try{
                    System.out.print("Day (1-" + maxDay + "): ");
                    if(scanner.nextLine().equals("\n")) {
                        scanner.next();
                    }
                    int dayInput = scanner.nextInt();

                    if(dayInput >= 1 && dayInput <= maxDay) {
                        day = dayInput;
                    }
                } catch(InputMismatchException inputError){}
            } while(day == 0);


            try{
                date = LocalDate.of(year, month, day);
                dateSet = true;
            } catch(DateTimeException dateError) {
                System.out.println("Date adding error. The max year is " + CrewRosterLiteApp.MAX_YEAR);
            }
        } while(!dateSet);
        
        return date;
    }
    
    /**
     * Enables the user to pick a time via the console
     * @return LocalTime which user picked
     */
    private LocalTime consoleGetTime() {
        Scanner scanner = new Scanner(System.in);
        boolean timeSet = false;
        LocalTime time = LocalTime.now();
        
        //Time
        do{
            System.out.print("Insert Time (hh:mm): ");
            String stringTime = scanner.nextLine();
        
            if(stringTime != null) {
                if(!stringTime.equalsIgnoreCase("exit")) {
                    try{
                        time = LocalTime.parse(stringTime);
                        timeSet = true;
                    } catch(DateTimeParseException parseError) {
                        System.out.println("Time Error, please try again. \n");
                        timeSet = false;
                    }
                } else {
                    timeSet = true;
                }
            }
            
        } while(!timeSet);
        
        return time;
    }
}
