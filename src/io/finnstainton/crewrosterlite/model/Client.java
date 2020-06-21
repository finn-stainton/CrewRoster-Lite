/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

/**
 * Client is an owner of an event/ job.
 * @author finnstainton (17982742)
 */
public class Client {
    private String ID;
    private String name;
    private Person contact;
    private Records<Person> contacts;
    
    public Client(String ID, String name) {
        if(ID.substring(0, 2).equals("CT")) {
            this.ID = ID;
        } else {
            this.ID = "CT" + ID;
        }
        this.name = name;
        this.contact = null;
    }

    public String getID() {
        return ID;
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getContact() {
        return contact;
    }
    
    public void setContact(Person contact) {
        this.contact = contact;
    }
    
    @Override
    public String toString() {
        return this.getName();
        // + ", Contact Person: " + ((this.getContact().getFullName() != null ) ? this.getContact().getFullName() : "No Contact Person")
    }
}
