/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.controller;

import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Records;

/**
 *
 * @author finnstainton
 */
public class JobController {
    private final Records<String, Job> jobRecords;
    
    public JobController(Records jobRecords) {
        this.jobRecords = jobRecords;
    }
}
