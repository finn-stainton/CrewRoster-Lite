/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

/**
 *
 * @author finnstainton
 */
public class User extends Person{
    public enum Type {
        Administrator, 
    }
    
    private Type type;
    
    public User(String ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
        
    }
}
