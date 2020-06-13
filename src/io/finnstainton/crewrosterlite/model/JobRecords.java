/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author finnstainton (17982742)
 */
 public class JobRecords extends LinkedHashMap<String, Job> {
        private Map<String, Job> jobMap;

        /**
         * Default Constructor
         */
        public JobRecords() {
            this.jobMap = new LinkedHashMap<>();
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
    }
