/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
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
public class EventTest {
    
    public EventTest() {
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
     * Test of getID method, of class Event.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Event instance = null;
        String expResult = "";
        String result = instance.getID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getParentJob method, of class Event.
     */
    @Test
    public void testGetParentJob() {
        System.out.println("getParentJob");
        Event instance = null;
        String expResult = "";
        String result = instance.getParentJob();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDate method, of class Event.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Event instance = null;
        LocalDate expResult = null;
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDate method, of class Event.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        LocalDate date = null;
        Event instance = null;
        instance.setDate(date);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStartTime method, of class Event.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        Event instance = null;
        LocalTime expResult = null;
        LocalTime result = instance.getStartTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStartTime method, of class Event.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        LocalTime startTime = null;
        Event instance = null;
        instance.setStartTime(startTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFinishTime method, of class Event.
     */
    @Test
    public void testGetFinishTime() {
        System.out.println("getFinishTime");
        Event instance = null;
        LocalTime expResult = null;
        LocalTime result = instance.getFinishTime();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFinishTime method, of class Event.
     */
    @Test
    public void testSetFinishTime() {
        System.out.println("setFinishTime");
        LocalTime finishTime = null;
        Event instance = null;
        instance.setFinishTime(finishTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLocation method, of class Event.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Event instance = null;
        String expResult = "";
        String result = instance.getLocation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLocation method, of class Event.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "";
        Event instance = null;
        instance.setLocation(location);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class Event.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Event instance = null;
        Event.EventType expResult = null;
        Event.EventType result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class Event.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Event.EventType type = null;
        Event instance = null;
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSpecialties method, of class Event.
     */
    @Test
    public void testGetSpecialties() {
        System.out.println("getSpecialties");
        Event instance = null;
        Set<Specialties> expResult = null;
        Set<Specialties> result = instance.getSpecialties();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberCrew method, of class Event.
     */
    @Test
    public void testGetNumberCrew() {
        System.out.println("getNumberCrew");
        Event instance = null;
        int expResult = 0;
        int result = instance.getNumberCrew();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCrewIDs method, of class Event.
     */
    @Test
    public void testGetCrewIDs() {
        System.out.println("getCrewIDs");
        Event instance = null;
        String[] expResult = null;
        String[] result = instance.getCrewIDs();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCrewInEvent method, of class Event.
     */
    @Test
    public void testIsCrewInEvent() {
        System.out.println("isCrewInEvent");
        String crewID = "";
        Event instance = null;
        boolean expResult = false;
        boolean result = instance.isCrewInEvent(crewID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCrew method, of class Event.
     */
    @Test
    public void testAddCrew() {
        System.out.println("addCrew");
        String ID = "";
        Event instance = null;
        boolean expResult = false;
        boolean result = instance.addCrew(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeCrew method, of class Event.
     */
    @Test
    public void testRemoveCrew() {
        System.out.println("removeCrew");
        String ID = "";
        Event instance = null;
        boolean expResult = false;
        boolean result = instance.removeCrew(ID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Event.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Event instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
