/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

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
        Job instance = null;
        String expResult = "";
        String result = instance.getClientID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClient method, of class Job.
     */
    @Test
    public void testSetClient() {
        System.out.println("setClient");
        String clientID = "";
        Job instance = null;
        instance.setClient(clientID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class Job.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        Job instance = null;
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class Job.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        Job instance = null;
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getID method, of class Job.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Job instance = null;
        String expResult = "";
        String result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventRecords method, of class Job.
     */
    @Test
    public void testGetEventRecords() {
        System.out.println("getEventRecords");
        Job instance = null;
        Records expResult = null;
        Records result = instance.getEventRecords();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVenue method, of class Job.
     */
    @Test
    public void testGetVenue() {
        System.out.println("getVenue");
        Job instance = null;
        String expResult = "";
        String result = instance.getVenue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEventRecords method, of class Job.
     */
    @Test
    public void testSetEventRecords() {
        System.out.println("setEventRecords");
        Records<Event> eventRecords = null;
        Job instance = null;
        instance.setEventRecords(eventRecords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVenue method, of class Job.
     */
    @Test
    public void testSetVenue() {
        System.out.println("setVenue");
        String venue = "";
        Job instance = null;
        instance.setVenue(venue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Job.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Job instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
