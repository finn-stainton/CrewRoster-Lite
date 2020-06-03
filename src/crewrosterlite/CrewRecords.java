/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package crewrosterlite;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

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
    
    /**
     * Tries to load data from document into new CrewRecord 
     * @param document XML Document with crew data
     * @param logStartUp boolean which indicates if the user wants to see log data
     */
    public CrewRecords(Document document, Boolean logStartUp) {
        this();
        
        //Load from document if not null
        if(document != null){
            DocumentHelper docHelper = new DocumentHelper();
            Node rootNode = document.getDocumentElement();
            Element root = (Element) rootNode;
            
            System.out.println(root.getTagName());
            System.out.println("\n->Adding Crew");

            //Foreach crew, load attributes
            for (Node crewNode : docHelper.getAllChildNodes(rootNode, "crew")) {
                String ID = docHelper.getAttributeString(crewNode, "ID");
                String firstName = docHelper.getAttributeString(crewNode, "firstname");
                String lastName = docHelper.getAttributeString(crewNode, "lastname");

                Crew newCrew = new Crew(ID, firstName, lastName);
                boolean crewAdded = this.addCrew(newCrew);
                if(logStartUp) {
                    System.out.print("Attempting to Add " + newCrew);
                    if(crewAdded) {
                        System.out.println(" - Succeed");
                    } else {
                        System.out.println(" - Failed");
                    }
                } 
            }
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
    
    /*************** Console Methods ***************/
    /**
     * Enables other methods to get a Crew object by 
     * prompting the user for details via the console.
     * @return Crew object, or null
     */
    public Crew consoleGetCrew() {
        Scanner scanner = new Scanner(System.in);
        Crew crew = null;
        Set<String> crewIDs = null;

        //Get user input
        boolean exitFlag = false;
        while(!exitFlag) {
            //Title
            System.out.println("\nChoose a Crew Member from the list: ");
        
            //Check if empty
            if(!this.crewMap.isEmpty()) {
                //Get all client keys
                crewIDs = crewMap.keySet();
                Iterator<String> clientIterator = crewIDs.iterator();
            
                //Print Clients
                while(clientIterator.hasNext()) {
                    String clientID = clientIterator.next();
                    System.out.println("(" + clientID + ") " + this.crewMap.get(clientID));
                }
            }
        
            //Add new client option
            System.out.println("(new) Add New Crew Member");
              
            System.out.print("ID: ");
            String crewChoosen = scanner.next();

            //check if user wants to add client
            if(crewChoosen.equalsIgnoreCase("exit")) {
                crew = null;
                exitFlag = true;
            } else if(crewChoosen.equalsIgnoreCase("new")) {
                //Create new crew member
                crew = this.consoleAddCrew();
                exitFlag = true;
            } else { //Check if Client ID exists
                if(crewMap.containsKey(crewChoosen)) {
                    crew = this.crewMap.get(crewChoosen);
                    exitFlag = true;
                } else {
                    System.out.println("Please type the Client's ID (CTxxxx), 'new' or 'exit'.");
                }
            }
        }
        
        return crew;
    }  
    
    /**
     * Enables the user to add a NEW crew member to crewRecords via the console.
     * @return Crew that has been added, or null
     */
    public Crew consoleAddCrew() {
        if(this.crewMap != null) { //If null, setup has failed
            Scanner scanner = new Scanner(System.in);
            System.out.println("### New Client ###");
            
            //Should have zerofill of 4, e.g. 10 => 0010
            String IDNumber = String.format("%04d", (this.crewMap.size() + 1));
            
            System.out.print("Crew Member's FirstName: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Crew Member's LastName: ");
            String lastName = scanner.nextLine();
            
            Crew crew = new Crew(IDNumber, firstName, lastName);
            
            //Insert into Map
            try{
                this.crewMap.put(crew.getID(), crew);
                System.out.println("Adding: " + crew);
            } catch(Exception badID) { //Should never happen
                crew = null;
            }
            
            return crew;
        } else {
            System.err.println("Setup Error, Restart application.");
            return null;
        }
    }
    
    /**
     * Enables the user to edit a crew member via the console.
     */
    public void consoleEditCrew() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("### Edit Crew ###");
        
        //Get Crew members ID
        Crew crew = this.consoleGetCrew();
         
        if(crew != null){
            if(this.crewMap.containsKey(crew.getID())){
                crew = this.crewMap.get(crew.getID());

                String response = "Gotta init";

                //Firstname
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("Update Firstname (y/n): ");
                    response = scanner.next();

                    if(response.equalsIgnoreCase("y")){
                        Scanner firstScanner = new Scanner(System.in);
                        System.out.print("New Firstname: ");
                        String firstName = firstScanner.next();

                        crew.setFirstName(firstName);
                    } 
                }

                response = "a";

                //Lastname
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("Update Lastname (y/n): ");
                    response = scanner.next();

                    if(response.equalsIgnoreCase("y")){
                        Scanner lastScanner = new Scanner(System.in);
                        System.out.print("New Lastname: ");
                        String lastName = lastScanner.next();

                        crew.setLastName(lastName);
                    } 
                }

                response = "a";

                //Contact
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("Update Contact (y/n): ");
                    response = scanner.next();

                    if(response.equalsIgnoreCase("y")){
                        Scanner contactScanner = new Scanner(System.in);
                        System.out.print("New Contact: ");
                        String contact = contactScanner.nextLine();

                        crew.setContact(contact);
                    } 
                }
                
                //Specialties
                //Print current specialties
                for(Specialties specialty : crew.getSpecialties()){
                    System.out.println("Current specialties: ");
                    System.out.print(specialty + ", ");
                }
                
                response = "a";
                
                //Add Specialty
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("\nDo you want to add any specialties (y/n): ");
                    response = scanner.next();
                    
                    if(response.equalsIgnoreCase("y")) {
                        for(Specialties specialty : crew.getSpecialties()){
                            response = "a";
                             while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                                System.out.print("Add " + specialty + " (y/n): ");
                                response = scanner.next();

                                if(response.equalsIgnoreCase("y")) {
                                    crew.addSpecialty(specialty);
                                }
                            }
                        }
                    }
                }
                
                response = "a";
                
                //Remove Specialty
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("Do you want to remove any specialties (y/n): ");
                    response = scanner.next();
                    
                    if(response.equalsIgnoreCase("y")) {
                        for(Specialties specialty : crew.getSpecialties()){
                            response = "a";
                             while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                                System.out.print("Remove " + specialty + " (y/n): ");
                                response = scanner.next();

                                if(response.equalsIgnoreCase("y")) {
                                    crew.removeSpecialty(specialty);
                                }
                            }
                        }
                    }
                }
                
                //Print updated crew
                System.out.println("Updated: " + crew);
            } 
        }
    }
    
    /**
     * Enables the user to remove a crew member by entering their ID, via the console.
     * @return Crew that has been removed, or null
     */
    public Crew consoleRemoveCrew() {
        System.out.println("### Remove Crew ###");
        boolean crewRemoved = false;
        Crew crew = null;
        
        do{
            crew = this.consoleGetCrew();
            
            if(crew != null) {
                crew = this.removeCrew(crew.getID());
                if(crew != null) {
                    System.out.println("Removed Crew " + crew);
                     crewRemoved = true;
                } else {
                    System.out.println("Can't find Crew# " + crew.getID());
                    System.out.println("If you want to exit, type 'exit'.");
                }
            } else { //User entered 'exit'
                crewRemoved = true;
            }
        }while(!crewRemoved);
        
        return crew;
    }
}
