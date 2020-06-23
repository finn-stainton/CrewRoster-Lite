/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author finns
 */
public class JobTest {
    private Job testJob;
    private Records<Event> eventRecords;
    
    public JobTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        testJob = new Job("JB0001", "CT0001", "Job Title", "Job Venue");
        Event event = new Event("EV000101", "JB0001", LocalDate.parse("2020-09-02"), LocalTime.parse("02:04"), LocalTime.parse("10:40"), "Event Location", "PackIn");
        testJob.getEventRecords().addValue(event.getID(), event);
        eventRecords = new Records<>();
        eventRecords.addValue(event.getID(), event);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getClientID method, of class Job.
     */
    @Test
    public void testGetClientID() {
        System.out.println("getClientID");
        Job instance = testJob;
        String expResult = "CT0001";
        String result = instance.getClientID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setClient method, of class Job.
     */
    @Test
    public void testSetClient() {
        System.out.println("setClient");
        String clientID = "CT0001";
        Job instance = testJob;
        instance.setClient(clientID);
    }

    /**
     * Test of getTitle method, of class Job.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Job instance = testJob;
        String expResult = "Job Title";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of setTitle method, of class Job.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "New Job Title";
        Job instance = testJob;
        instance.setTitle(title);
    }

    /**
     * Test of getID method, of class Job.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Job instance = testJob;
        String expResult = "JB0001";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getEventRecords method, of class Job.
     */
//    @Test
//    public void testGetEventRecords() {
//        System.out.println("getEventRecords");
//        Job instance = testJob;
//        Records expResult = eventRecords;
//        Records result = instance.getEventRecords();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getVenue method, of class Job.
     */
    @Test
    public void testGetVenue() {
        System.out.println("getVenue");
        Job instance = testJob;
        String expResult = "Job Venue";
        String result = instance.getVenue();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEventRecords method, of class Job.
     */
    @Test
    public void testSetEventRecords() {
        System.out.println("setEventRecords");
        Records<Event> eventRecords = new Records<Event>();
        Job instance = testJob;
        instance.setEventRecords(eventRecords);
    }

    /**
     * Test of setVenue method, of class Job.
     */
    @Test
    public void testSetVenue() {
        System.out.println("setVenue");
        String venue = "Another Venue";
        Job instance = testJob;
        instance.setVenue(venue);
    }

    /**
     * Test of toString method, of class Job.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Job instance = testJob;
        String expResult = "Job #JB0001 Job Title for CT0001";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
