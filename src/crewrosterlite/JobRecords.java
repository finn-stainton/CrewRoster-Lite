/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package crewrosterlite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 *
 * @author finnstainton (17982742)
 */
 public class JobRecords extends LinkedHashMap<String, Job> {
        private CrewRosterLiteApp app;
        private Map<String, Job> jobMap;

        /**
         * Default Constructor
         * @param app CrewRosterApp for linking with Crew/ Client Records
         * @param setuplog boolean used in development
         */
        public JobRecords(CrewRosterLiteApp app, boolean setuplog) {
            this.jobMap = new LinkedHashMap<>();
            this.app = app;
        }
        

        /**
         * Attempts to load data from xml File
         * @param jobDocument Document xml 
         * @param app CrewRosterApp for linking with Crew/ Client Records
         * @param setuplog boolean used in development 
         */
        public JobRecords(Document jobDocument, CrewRosterLiteApp app, boolean setuplog) {
            this(app, setuplog);
            
            //Setup Roster
            if(jobDocument != null){

                DocumentHelper docHelper = new DocumentHelper();
                Node rootNode = jobDocument.getDocumentElement();

                System.out.println("\nAdding Roster");

                //Job
                for(Node jobNode : docHelper.getAllChildNodes(rootNode, "job")) {
                    Job job = null;
                    String ID = docHelper.getAttributeString(jobNode, "ID");
                    String clientID = docHelper.getAttributeString(jobNode, "clientID");

                    // If contains client continue
                    if(CrewRosterLiteApp.getClientRecords().containsClient(clientID)) {
                        Client client = CrewRosterLiteApp.getClientRecords().get(clientID);

                        if(client == null){
                            if(setuplog)
                                System.out.println("Can't Find Client");
                        }
                        
                        Node titleNode = null;
                        String title = docHelper.getTextContent(titleNode);

                        job = new Job(ID, client, title);
                        if(setuplog)
                            System.out.println("Initating " + job);


                        //Event
                        for(Node eventNode : docHelper.getAllChildNodes(jobNode, "event")) {
                            String eventID = docHelper.getAttributeString(eventNode, "ID");

                            String dateString = docHelper.getTextContent(docHelper.getFirstChildNode(eventNode, "date"));
                            String startString = docHelper.getTextContent(docHelper.getFirstChildNode(eventNode, "starttime"));
                            String finishString = docHelper.getTextContent(docHelper.getFirstChildNode(eventNode, "finishtime"));

                            //Get Date and Time
                            LocalDate date = null;
                            LocalTime startTime = null;
                            LocalTime finishTime = null;

                            //Try add date and times
                            if(dateString != null && startString != null && finishString != null) {
                                try{    
                                    date = LocalDate.parse(dateString);
                                    startTime = LocalTime.parse(startString);
                                    finishTime = LocalTime.parse(finishString);
                                } catch (DateTimeParseException dateError) {
                                    if(setuplog)
                                        System.out.println("Couldn't load Event#" + eventID + " dates.");
                                }
                                
                                     String location = docHelper.getTextContent(docHelper.getFirstChildNode(eventNode, "location"));

                                    //Get Event Type
                                    String type = docHelper.getTextContent(docHelper.getFirstChildNode(eventNode, "type"));
                                    Event.EventType eventType;
                                    switch(type) {
                                        case "Preperation":
                                            eventType = Event.EventType.Preperation;
                                            break;
                                        case "Pack In":
                                             eventType = Event.EventType.PackIn;
                                            break;
                                        case "Pack Out":
                                             eventType = Event.EventType.PackOut;
                                            break;
                                        case "Operation":
                                             eventType = Event.EventType.Operation;
                                            break;
                                        default:
                                             eventType = Event.EventType.General;
                                            break;
                                    }

                                    Event newEvent = new Event(eventID, job, date, startTime, finishTime, location, eventType);
                                    
                                    //Add Crew
                                    for(Node eventIDNode : docHelper.getAllChildNodes(eventNode, "crew")) {
                                         newEvent.addCrew(docHelper.getTextContent(docHelper.getFirstChildNode(eventIDNode, "ID")));
                                    }
                                    boolean eventAdded = job.addEvent(newEvent);
                                    if(setuplog)
                                        System.out.println("Attempting to Add " + newEvent);

                                } else {
                                    if(setuplog)
                                        System.out.println("Finish Date was before Start Date, deleted finish date.");
                                            
                                }
                        }

                        //Add Job
                        boolean jobAdded = this.addJob(job);
                        if(setuplog){
                            System.out.print("Attempting to Add " + job);
                            if(jobAdded) {
                                System.out.println(" - Succeed");
                            } else {
                                System.out.println(" - Failed");
                            }
                        }

                    } else { //Error, unknown client
                        if(setuplog)
                            System.out.println("Couldn't find client# " + clientID);
                    }
                }
            }
        }

        /**
         * @param jobID String ID associated with job to return
         * @return Job if stored, or null
         */
        public Job getJob(String jobID) {
            if(jobID != null  && this.jobMap != null){
                if(this.jobMap.containsKey(jobID)){
                    return this.jobMap.get(jobID);
                }
            }
            return null;
        }

        /**
         * Adds Job to record
         * @param job Job to be added
         * @return boolean regarding if the job has been added(true), or not(false)
         */
        public boolean addJob(Job job) {
            if(!this.jobMap.containsKey(job.getID())) {
                this.jobMap.put(job.getID(), job);
                return true;
            } else {
                return false;
            }
        }

        /**
         * Removes Job from record
         * @param ID String associated with the Job to remove
         * @return Job that has been removed, or null
         */
        public Job removeJob(String ID) {
            if(ID != null && this.jobMap != null) {
                return this.jobMap.remove(ID);
            } else {
                return null;
            }
        }
        
        /**
         * 
         * @return int with the number of jobs currently stored
         */
        public int getNumberJobs() {
            if(this.jobMap != null) {
                return this.jobMap.size();
            } else {
                return 0;
            }
        }
        
        /**
         * @return String[] of all jobs currently stored
         */
        public String[] getJobsIDs(){
            Set<String> IDs = jobMap.keySet();
            String[] strings = new String[IDs.size()];

            Iterator iterator = IDs.iterator();
            int counter = 0;
            while(iterator.hasNext()){
                strings[counter] = iterator.next().toString();
                counter++;
            }

            return strings;
        }

        /**
         * @return boolean regarding if there are jobs or not
         */
        public boolean isJobsEmpty() {
            if(this.jobMap != null) {
                return this.jobMap.isEmpty();
            } else {
                return true;
            }
        }
        
        /**
         * Prints all Jobs to console
         */
        public void printJobs() {
            if(this.isJobsEmpty()) {
                System.out.println("No Jobs.");
            }else {
                this.jobMap.values().forEach((job) -> {
                    System.out.print("ID: " + job.getID() + ", ");
                    job.print();
                });
            }
        }
        
        public void printJobSummarys() {
            if(!this.isJobsEmpty()) {
                System.out.println("Jobs");
                this.jobMap.values().forEach((job) -> {
                    System.out.println("ID: " + job.getID() + ", " + job.getTitle());
                });
            }
        }
        
        /**
         * Creates new XML document with current Job Records
         * @return String name of file created, or null
         */
        public String createXMLDocument(){
            String name = null; //File name
            try {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document doc = builder.newDocument();

                // Create root
                Element rootElement = doc.createElement("jobs");
                
                // XSD schema 
                //Attr attr1 = doc.createAttribute("xmlns:xsi");
                //attr1.setValue("http://www.w3.org/2001/XMLSchema-instance");
                //Attr attr2 = doc.createAttribute("xsi:noNamespaceSchemaLocation");
                //attr2.setValue("roster.xsd");
                //rootElement.setAttributeNode(attr1);
                //rootElement.setAttributeNode(attr2);
                doc.appendChild(rootElement);

                // Add jobs
                String[] jobIDs = this.getJobsIDs();
                
//                if(setuplog){
//                    System.out.println(Arrays.toString(jobIDs));
//                    System.out.println(this.getNumberJobs()); 
//               }
                    
                for (int c = 0; c < this.getNumberJobs(); c++) {
                    Job job = this.getJob(jobIDs[c]);

                    Element jobElement = doc.createElement("job");
                    rootElement.appendChild(jobElement);

                    //Job attributes
                    String[] jobAttributes = {"ID", "clientID"};
                    String[] jobAttributeValues = {job.getID(), job.getClient().getID()};
                    for (int count = 0; count < jobAttributes.length; count++){
                        Attr attr = doc.createAttribute(jobAttributes[count]);
                        attr.setValue(jobAttributeValues[count] == null ? " " : jobAttributeValues[count]);
                        jobElement.setAttributeNode(attr);
                    }

                    //Job Tags
                    String[] jobTags = {"title"};
                    String[] jobTagValues = {job.getTitle()};
                    for (int count = 0; count < jobTags.length; count++) {
                        Element tagElement = doc.createElement(jobTags[count]);
                        tagElement.appendChild(doc.createTextNode(jobTagValues[count] == null ? " " : jobTagValues[count]));
                        jobElement.appendChild(tagElement);
                    }
                    
                    //Add Events
                    Element eventsElement = doc.createElement("events");
                    jobElement.appendChild(eventsElement);
                    
                    String[] eventIDs = job.getEventIDs();
                    for (int eventCount = 0; eventCount < job.getNumberEvents(); eventCount++) {
                        Event event = job.getEvent(eventIDs[c]);
                        Element eventElement = doc.createElement("event");
                        eventsElement.appendChild(eventElement);
                        
                        //Event attributes
                        String[] eventAttributes = {"ID"};
                        String[] eventAttributeValues = {event.getID()};
                        for (int count = 0; count < eventAttributes.length; count++){
                            Attr attr = doc.createAttribute(eventAttributes[count]);
                            attr.setValue(eventAttributeValues[count] == null ? " " : eventAttributeValues[count]);
                            eventElement.setAttributeNode(attr);
                        }
                    
                        //Event Tags
                        String[] eventTags = {"date" + "startTime", "finishTime", "location", "type"};
                        String[] eventTagValues = {event.getDate().toString(), event.getStartTime().toString(), event.getFinishTime().toString(), event.getLocation(), event.getType().toString()};
                        for (int count = 0; count < eventTags.length; count++) {
                            Element tagElement = doc.createElement(eventTags[count]);
                            tagElement.appendChild(doc.createTextNode(eventTagValues[count] == null ? " " : eventTagValues[count]));
                            eventElement.appendChild(tagElement);
                        }
                        
                        //Crew
                        Element crewElement = doc.createElement("crew");
                        eventElement.appendChild(crewElement);
                        
                        String[] crewIDs = event.getCrewIDs();
                        for (int crewCount = 0; crewCount < event.getNumberCrew(); crewCount++) {
                            Element idElement = doc.createElement("ID");
                            idElement.appendChild(doc.createTextNode(crewIDs[crewCount] == null ? " " : eventTagValues[crewCount]));
                            crewElement.appendChild(idElement);
                        }
                    }
                }

                // Transfer to XML file
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); //ISO-8859-1

                DOMSource source = new DOMSource(doc);

                String timeStamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm").format(new Date());
                name = timeStamp + "_roster.xml";
                StreamResult result = new StreamResult(new File(name));

                try {
                    transformer.transform(source, result);
                } catch (Exception e){
                    System.err.println("File save location denied");
                    
                }

                // Output to console for testing
//                if(log) {
//                    StreamResult consoleResult = new StreamResult(System.out);
//                    transformer.transform(source, consoleResult);
//                }
            } catch (Exception e) {
                //e.printStackTrace();
            }
            
            return name;
        }

        /************************* Console Methods ******************************/
        /**
         * Enables other methods to interact with the user to get a Job ID via the console
         * @return String ID of a Job, or, null if user exits
         */
        public String consoleGetJobID() {
            Scanner scanner = new Scanner(System.in);
            String jobID = null;
            boolean jobFlag = false;

            if(this.jobMap != null && !this.isJobsEmpty()) {
                while(!jobFlag) {
                    System.out.print("Job ID: ");
                    jobID = scanner.nextLine();

                    if(jobID.equalsIgnoreCase("exit")){
                        jobID = null;
                        jobFlag = true;
                    } else if(this.jobMap.containsKey(jobID)){//Check if job exists
                        jobFlag = true;
                    } else{
                        System.out.println("Can't find Job# " + jobID);
                    } 
                }
            } else{
                jobID = null;
                System.out.println("No Jobs. Please add a Job first.");
            }

            return jobID;
        }
        
        /**
         * Enables the user to add a NEW job to the jobRecords via the console.
         * @return Job that has been added, or, null if no Job added.
         */
        public Job consoleAddJob() {
            System.out.println("### Add Job ###");
            Job job = null;
            
            //Get Client of new Job
            Client client = this.app.getClientRecords().consoleGetClient();
            
            if(this.jobMap != null && client != null) {
                Scanner scanner = new Scanner(System.in);

                //ID
                String ID = String.format("%04d", (this.jobMap.size() + 1));
                  
                //Title
                //scanner.nextLine();
                System.out.print("Job title: ");
                String title = scanner.nextLine();

                job = new Job(ID, client, title);

                System.out.print("Adding " + job);
                boolean jobAdded = this.addJob(job);
                
                if(jobAdded) {
                    System.out.println(" - Succeed");
                }
            } else { //this.jobMap is null, hasn't been init. 
                System.out.println("Add Job Failed.)");
            }
            
            System.out.println();
            return job;
        }

        /**
         * Enables the user to edit a Job via the console.
         */
        public void consoleEditJob() {
            System.out.println("### Edit Job ###");
            Scanner scanner = new Scanner(System.in);
            String jobID = this.consoleGetJobID();

            if(jobID != null) {
                Job job = this.getJob(jobID);
                String response = "a";

                if(job != null) {

                    //Title
                    while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                        System.out.print("Update Title (y/n)");
                        response = scanner.next();

                        if(response.equalsIgnoreCase("y")){
                            scanner.nextLine();
                            System.out.print("New Title: ");
                            String title = scanner.nextLine();
                            job.setTitle(title);
                        } 
                    }

                    //Events
                    for(Event e : job.getEvents()) {
                        response = "a";

                        while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                            System.out.print("Remove " + e + "? (y/n)");
                            response = scanner.next();

                            if(response.equalsIgnoreCase("y")){
                                Event removedEvent = job.removeEvent(e.getID());
                                if(removedEvent != null) {
                                    System.out.print("Removed " + e);
                                } else {
                                    System.out.print("Failed to remove " + e);
                                }
                            } 
                        }
                    }
                } else {
                    System.out.println("Can't find Job# " + jobID);
                    System.out.println("If you want to exit, type 'exit'.");
                }
            }
        }

        /**
         * Enables the user to remove a Job via the console.
         * @return Job that has been removed, or, null if no Job removed.
         */
        public Job consoleRemoveJob() {
            System.out.println("### Remove Job ###");
            Scanner scanner = new Scanner(System.in);
            Job job = null;
            String jobID = this.consoleGetJobID();

            if(jobID != null){
                job = this.removeJob(jobID);
                
                if(job != null) {
                    System.out.println("Removed " + job);
                } else {
                    System.out.println("Can't find Job# " + jobID);
                    System.out.println("If you want to exit, type 'exit'.");
                }
            }
            return job;
        }

        /**
         * Enables the user to print a Job via the console.
         */
        public void consolePrintJob() {
            System.out.println("### Display Job ###");
            String jobID = this.consoleGetJobID();
            Job job = null;

            if(jobID != null){
                job = this.getJob(jobID);
                if(job != null) {
                    job.print();
                } else {
                    System.out.println("Can't find Job# " + jobID);
                    System.out.println("If you want to exit, type 'exit'.");
                }
            }
        }
        
        /**
         * Enables the user to add an Event to a Job via the console. 
         * @return Event that has been added, or, null if no Event added.
         */
        public Event consoleAddJobEvent(){
            System.out.println(" ### Add Event ###");
            
            this.printJobSummarys();
            try{
                Job job = this.getJob(this.consoleGetJobID());
                if (job != null) {
                    return job.consoleAddEvent();
                }
            } catch(Exception e) {}
            return null;
        }
        
        /**
         * Enables the user to edit an Event from a Job via the console.
         */
        public void consoleEditJobEvent() {
            System.out.println(" ### Edit Event ###");
            
            try{
                Job job = this.getJob(this.consoleGetJobID());
                job.consoleEditEvent();
            } catch(Exception e) {}
        }
        
        /**
         * Enables the user to remove an Event from a Job via the console.
         * @return Event that has been removed, or, null if no Event removed.
         */
        public Event consoleRemoveJobEvent() {
            System.out.println(" ### Remove Event ###");
            
            try{
                Job job = this.getJob(this.consoleGetJobID());
                if(job != null) {
                    return job.consoleRemoveEvent();
                }
            } catch(Exception e) {}
            
            return null;
        }
        
        /**
         * Enables the user to roster crew to an Event within this current job
         */
        public void consoleRosterCrew(){
            System.out.println(" ### Roster Crew ###");
            
            this.printJobSummarys();
            Job job = this.getJob(this.consoleGetJobID());
            if(job != null){
                job.printEvents();
                String eventID = job.consoleGetEventID();

                if(eventID != null){
                    job.getEvent(eventID).consoleRosterCrew();
                } else {}
            }
        }
        
    }
