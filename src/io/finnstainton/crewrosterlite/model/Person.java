/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

import java.util.Objects;

/**
 * Person object used for crew and contact person
 * @author finnstainton (17982742)
 */
public class Person {
    private String ID;
    private String firstName;
    private String lastName;
    private String contact;
  
    /**
     * Creates a new Person object
     * @param ID String unique identifier 
     * @param firstName String firstname of person
     * @param lastName String lastname of person
     */
    public Person(String ID, String firstName, String lastName){
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contact = "No Contact Details";
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
    
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
    /**
     * Creates Initials from the first letter of a persons first and last names
     * @return String Initials
     */
    public String getInitials() {
        return (this.firstName.substring(0, 1).toUpperCase()) + 
                (this.lastName.substring(0, 1).toUpperCase());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.ID);
        hash = 71 * hash + Objects.hashCode(this.firstName);
        hash = 71 * hash + Objects.hashCode(this.lastName);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.firstName + " " + this.lastName + ", Contact: " + this.contact;
    }
}
