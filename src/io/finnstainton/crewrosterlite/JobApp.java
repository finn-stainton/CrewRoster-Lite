/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Records;

/**
 *
 * @author finnstainton
 */
public class JobApp {
    private final Records<String, Job> jobModel;
    
    public JobApp(){
        this.jobModel = new Records<>();
    }
}
