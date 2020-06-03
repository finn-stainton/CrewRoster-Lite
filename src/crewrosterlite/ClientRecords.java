/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package crewrosterlite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 * ClientRecords stores Clients in a Map 
 * @author finnstainton (17982742)
 */
public class ClientRecords extends LinkedHashMap<String, Client> {
    private Map<String, Client> clientMap;
    private boolean log;
    
    /**
     * Default Constructor. Sets up an empty ClientRecords
     */
    public ClientRecords() {
        this.clientMap = new LinkedHashMap();
    }
    
    /**
     * Constructor.Sets up a ClientRecords from a XML file
     * @param document A pre-build Document from a XML file
     * @param log Boolean which denotes the use of console logging
     */
    public ClientRecords(Document document, Boolean log) {
        this();
        if(document != null){
            this.log = log;

            DocumentHelper docHelper = new DocumentHelper();
            Node rootNode = document.getDocumentElement();

            //Client
            System.out.println("\n->Adding Clients");
            
            for (Node clientNode : docHelper.getAllChildNodes(rootNode, "client")) {
                //
                String ID = docHelper.getAttributeString(clientNode, "ID");
                String name = docHelper.getAttributeString(clientNode, "name");

                //Future feature, client with multiple contact people
                ArrayList<Person> contacts = new ArrayList();
                Collection<Node> contactNodes = docHelper.getAllChildNodes(clientNode, "contact");
                Iterator<Node> contactNodesIterator = contactNodes.iterator();
                Person contact = null;

                //Future feature,
                //while(contactNodesIterator.hasNext()) {
                if(contactNodesIterator.hasNext()) {
                    Node contactNode = contactNodesIterator.next();

                    String contactID = docHelper.getAttributeString(contactNode, "ID");
                    String firstName = docHelper.getAttributeString(contactNode, "firstName");
                    String lastName = docHelper.getAttributeString(contactNode, "lastName");

                    //New Person
                    contact = new Person(contactID, firstName, lastName);
                }

                //Skip client if unknown contact person.
                if(contact != null) { 
                    Client newClient = new Client(ID, name, contact);
                    boolean clientAdded = this.addClient(newClient);
                    if(log) {
                        System.out.print("Attempting to Add " + newClient);
                        if(clientAdded) {
                            System.out.println(" - Succeed");
                        } else {
                             System.out.println(" - Failed");
                        }
                    }
                }           
            }
        }
    }
    
    /**
     * Generates the next available Client ID.
     * @return String ID of next Client, or null.
     */
    public String generateID() {
        if(this.clientMap != null) {
            return String.format("%04d", (this.clientMap.size() + 1));
        } else {
            return null;
        }
    }
    
    /**
     * Add a client to the records
     * @param client, Client object to be recorded
     * @return boolean whether client added (true) or not (false)
     */
    public boolean addClient(Client client) {
        boolean clientAdded = false;
        
        if(!this.clientMap.containsKey(client.getID())) {
            this.clientMap.put(client.getID(), client);
            clientAdded = true;
        }
        
        return clientAdded;
    }
    
    /**
     * Remove a client from the recorded
     * @param clientID String ID of the client object to be removed
     * @return Client that has been removed
     */
    public Client removeClient(String clientID){
        return this.clientMap.remove(clientID);
    }
    
    /**
     * Check if a client is stored in these records
     * @param clientID String ID of the client object to be removed
     * @return Boolean regarding if it contains a client with the ID (true) or not (false)
     */
    public boolean containsClient(String clientID) {
        if(!this.isEmpty()) {
            return this.clientMap.containsKey(clientID);
        } else {
            return false;
        }
    }
        
    /**
     * Check if records are empty
     * @return boolean regarding if the records is empty (true) or not (false)
     */
    @Override
    public boolean isEmpty() {
        if(this.clientMap != null) {
            return this.clientMap.isEmpty();
        } else {
            return true;
        }
    }
    
    /**
     * Print all clients to console
     */
    public void print() {
        this.clientMap.values().forEach((c) -> {
            System.out.println("ID: " + c.getID() + ", " + c);
        });
    }
    
    /*************** Console Methods ***************/
    /**
     * Enables the other methods to get a Client stored in these record by 
     * prompting the user via the console.
     * @return Client object, or null.
     */
    public Client consoleGetClient() {
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        Set<String> clientIDs = null;

        //Get user input
        boolean clientFlag = true;
        while(clientFlag) {
            //Title
            System.out.println("Choose a Client from the list: ");
        
            //Check if empty
            if(!this.clientMap.isEmpty()) {
                //Get all client keys
                clientIDs = clientMap.keySet();
                Iterator<String> clientIterator = clientIDs.iterator();
            
                //Print Clients
                while(clientIterator.hasNext()) {
                    String clientID = clientIterator.next();
                    System.out.println("(" + clientID + ") " + this.clientMap.get(clientID));
                }
            }
        
            //Add new client option
            System.out.println("(new) Add New Client");
              
            System.out.print("ID: ");
            String clientChoosen = scanner.next();

            //check if user wants to add client
            if(clientChoosen.equalsIgnoreCase("exit")) {
                client = null;
                clientFlag = false;
            } else if(clientChoosen.equalsIgnoreCase("new")) {
                //Create new client
                client = this.consoleAddClient();
                clientFlag = false;
            } else { //Check if Client ID exists
                if(clientMap.containsKey(clientChoosen)) {
                    client = this.clientMap.get(clientChoosen);
                    clientFlag = false;
                } else {
                    System.out.println("Please type the Client's ID (CTxxxx), 'new' or 'exit'.");
                }
            }
        }
        
        return client;
    }
    
    /**
     * Enables the user to add a NEW Client via the console.
     * @return Client that's been added, or null
     */
    public Client consoleAddClient() {
        if(this.clientMap != null) { //If null, setup has failed
            Scanner scanner = new Scanner(System.in);
            System.out.println("### New Client ###");
            
            //Should have zerofill of 4, e.g. 10 => 0010
            String IDNumber = this.generateID();
            
            System.out.print("Client's Company: ");
            String clientName = scanner.nextLine();
            
            System.out.print("Contact Person's FirstName: ");
            String firstName = scanner.nextLine();
            
            System.out.print("Contact Person's LastName: ");
            String lastName = scanner.nextLine();
            
            Person contact = new Person(IDNumber, firstName, lastName);
            Client client = new Client(IDNumber, clientName, contact);
            
            //Insert into Map
            try{
                this.clientMap.put(client.getID(), client);
                System.out.println("Adding " + client);
            } catch(Exception badID) { //Should never happen
                client = null;
            }
            
            return client;
        } else {
            System.err.println("Setup Error, Restart application.");
            return null;
        }
    }
    
    /**
     * Enables the user to edit an existing Client via the console.
     */
    public void consoleEditClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("### Edit Client ###");
        
        //Get clients ID
        Client client = this.consoleGetClient();
         
        if(client != null){
            if(this.clientMap.containsKey(client.getID())){
                client = this.clientMap.get(client.getID());
                System.out.println("Currently: " + client);
                String response = "";

                //Name
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("Update Name (y/n): ");
                    response = scanner.next();

                    if(response.equalsIgnoreCase("y")){
                        System.out.print("New Name: ");
                        String name = scanner.next();

                        client.setName(name);
                    } 
                }

                response = "a";

                //Contact
//                Future feature
//                for(Person contact : client.getContactList()) { 
                while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                    System.out.print("Update Contact " + client.getName() + " (y/n): ");
                    response = scanner.next();

                    if(response.equalsIgnoreCase("y")){
                        
                        response = "a";
                        //Contact Persons firstName
                        while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                            System.out.print("Update Firstname (y/n): ");
                            response = scanner.next();

                            if(response.equalsIgnoreCase("y")){
                                System.out.print("New Firstname: ");
                                String firstName = scanner.next();

                                client.getContact().setContact(firstName);
                            } 
                        }
                        
                        response = "a";
                        
                        //Contact Persons lastName
                        while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                            System.out.print("Update Lastname (y/n): ");
                            response = scanner.next();

                            if(response.equalsIgnoreCase("y")){
                                System.out.print("New Lastname: ");
                                String lastName = scanner.next();

                                client.getContact().setLastName(lastName);
                            } 
                        }
                        
                        response = "a";
                        
                        //Contact Persons contact details
                        while(!response.equalsIgnoreCase("y") && !response.equalsIgnoreCase("n")){
                            System.out.print("Update Contact Details (y/n): ");
                            response = scanner.next();

                            if(response.equalsIgnoreCase("y")){
                                System.out.print("New Contact Detail: ");
                                String contact = scanner.next();

                                client.getContact().setContact(contact);
                            } 
                        }
                    } 
                }

                //Print update client details
                System.out.println("Updated: " + client);
            } else {
                System.err.println("Client Modification Failed. Add a Client first.");
            }
        }
    }
    
    /**
     * Enables the user to remove a Client via the console.
     * @return Client that was removed, or null
     */
    public Client consoleRemoveClient() {
        System.out.println("### Remove Client ###");
        boolean clientRemoved = false;
        Client client = null;
        
        do{
            client = this.consoleGetClient();

            if(client != null) {
                client = this.removeClient(client.getID());
                if(client != null) {
                    System.out.println("Removed Client: " + client);
                     clientRemoved = true;
                } else { //Shouldn't return a client thats not in clientMap.
                    System.out.println("If you want to exit, type 'exit'.");
                }
            } else { //User entered 'exit'
                clientRemoved = true;
            }
        }while(!clientRemoved);
        
        return client;
    }
}
