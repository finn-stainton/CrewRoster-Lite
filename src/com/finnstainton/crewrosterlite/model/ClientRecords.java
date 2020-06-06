/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package com.finnstainton.crewrosterlite.model;

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
}
