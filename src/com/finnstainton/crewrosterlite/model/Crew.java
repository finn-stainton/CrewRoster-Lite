/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package com.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Map;

/**
 * Crew is an extension of a person, but has specialties. 
 * @author finnstainton (17982742)
 */
public class Crew extends Person {
    private Map<String, CrewWeekRoster> crewRosters;
    private HashSet<Specialties> specialties;
    
    /**
     * Creates a new Crew Object.
     * All Crew have "General" specialty added. 
     * @param ID String ID of the crew. 
     * @param firstName String crew's first name
     * @param lastName String crew's last name
     */
    public Crew(String ID, String firstName, String lastName) {
        super(ID, firstName, lastName);
        this.specialties = new HashSet<>();
        this.specialties.add(Specialties.General);
        
        //Update ID to include prefix "CR"
        if(!ID.substring(0, 2).equals("CR")) {
            this.setID("CR" + ID);
        }
    }

    public HashSet<Specialties> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(HashSet<Specialties> specialties) {
        this.specialties = specialties;
    }
    
    /**
     * Add a new specialty to crew
     * @param specialty From Specialties enum
     * @return boolean regarding if the specialty has been added (t), or not (f)
     */
    public boolean addSpecialty(Specialties specialty) {
        if(this.specialties != null) {
            if(!this.specialties.contains(specialty)){
                this.specialties.add(specialty);
            }
            //Returns true even if the "new" specialty hasn't been added, 
            //as this is due to the crew already having the specalty 
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Remove a specialty from crew
     * @param specialty From Specialties enum
     * @return boolean regarding if the specialty has been removed (t), or not (f)
     */
    public boolean removeSpecialty(Specialties specialty) {
        if(this.specialties != null) {
            if(!Specialties.General.equals(specialty)){
                if(this.specialties.contains(specialty)){
                    this.specialties.remove(specialty);
                }
            }
            return true;
        } else {
            return false;
        }
    }
    
    public boolean isAvailable(LocalDate date, LocalTime start, LocalTime end){
        //Not implemented full, Yet...
        return true;
    }
}
