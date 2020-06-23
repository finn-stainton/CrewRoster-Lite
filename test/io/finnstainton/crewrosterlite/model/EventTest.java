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
    private Event event;
    
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
        event = new Event("EV000101", "JB0001", LocalDate.parse("2020-09-02"), LocalTime.parse("02:04"), LocalTime.parse("10:40"), "Event Location", "PackIn");
        event.addCrew("CR0001");
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
        Event instance = event;
        String expResult = "EV000101";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getParentJob method, of class Event.
     */
    @Test
    public void testGetParentJob() {
        System.out.println("getParentJob");
        Event instance = event;
        String expResult = "JB0001";
        String result = instance.getParentJob();
        assertEquals(expResult, result);
    }

    /**
     * Test of getDate method, of class Event.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        Event instance = event;
        LocalDate expResult = LocalDate.parse("2020-09-02");
        LocalDate result = instance.getDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDate method, of class Event.
     */
    @Test
    public void testSetDate() {
        System.out.println("setDate");
        LocalDate date = LocalDate.parse("2020-09-03");
        Event instance = event;
        instance.setDate(date);
    }

    /**
     * Test of getStartTime method, of class Event.
     */
    @Test
    public void testGetStartTime() {
        System.out.println("getStartTime");
        Event instance = event;
        LocalTime expResult = LocalTime.parse("02:04");
        LocalTime result = instance.getStartTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setStartTime method, of class Event.
     */
    @Test
    public void testSetStartTime() {
        System.out.println("setStartTime");
        LocalTime startTime = LocalTime.parse("02:05");
        Event instance = event;
        instance.setStartTime(startTime);
    }

    /**
     * Test of getFinishTime method, of class Event.
     */
    @Test
    public void testGetFinishTime() {
        System.out.println("getFinishTime");
        Event instance = event;
        LocalTime expResult = LocalTime.parse("10:40");
        LocalTime result = instance.getFinishTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFinishTime method, of class Event.
     */
    @Test
    public void testSetFinishTime() {
        System.out.println("setFinishTime");
        LocalTime finishTime = LocalTime.parse("10:41");
        Event instance = event;
        instance.setFinishTime(finishTime);
    }

    /**
     * Test of getLocation method, of class Event.
     */
    @Test
    public void testGetLocation() {
        System.out.println("getLocation");
        Event instance = event;
        String expResult = "Event Location";
        String result = instance.getLocation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLocation method, of class Event.
     */
    @Test
    public void testSetLocation() {
        System.out.println("setLocation");
        String location = "New Event Location";
        Event instance = event;
        instance.setLocation(location);
    }

    /**
     * Test of getType method, of class Event.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        Event instance = event;
        Event.EventType expResult = Event.EventType.PackIn;
        Event.EventType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of setType method, of class Event.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        Event.EventType type = Event.EventType.Operation;
        Event instance = event;
        instance.setType(type);
    }

    /**
     * Test of getSpecialties method, of class Event.
     */
//    @Test
//    public void testGetSpecialties() {
//        System.out.println("getSpecialties");
//        Event instance = event;
//        Set<Specialties> expResult = new String[0];
//        Set<Specialties> result = instance.getSpecialties();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getNumberCrew method, of class Event.
     */
    @Test
    public void testGetNumberCrew() {
        System.out.println("getNumberCrew");
        Event instance = event;
        int expResult = 1;
        int result = instance.getNumberCrew();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCrewIDs method, of class Event.
     */
    @Test
    public void testGetCrewIDs() {
        System.out.println("getCrewIDs");
        Event instance = event;
        String[] expResult = new String[]{"CR0001"};
        String[] result = instance.getCrewIDs();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of isCrewInEvent method, of class Event.
     */
    @Test
    public void testIsCrewInEvent() {
        System.out.println("isCrewInEvent");
        String crewID = "CR0002";
        Event instance = event;
        boolean expResult = false;
        boolean result = instance.isCrewInEvent(crewID);
        assertEquals(expResult, result);
    }

    /**
     * Test of addCrew method, of class Event.
     */
    @Test
    public void testAddCrew() {
        System.out.println("addCrew");
        String ID = "CR0001";
        Event instance = event;
        boolean expResult = false;
        boolean result = instance.addCrew(ID);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeCrew method, of class Event.
     */
    @Test
    public void testRemoveCrew() {
        System.out.println("removeCrew");
        String ID = "CR0002";
        Event instance = event;
        boolean expResult = false;
        boolean result = instance.removeCrew(ID);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Event.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Event instance = event;
        String expResult = "PackIn on 2020-09-02 from 02:04 and estimated to finish at 10:40,\n" +
            "taking place at Event Location.\n" + "Crew: ";
        String result = instance.toString();
        System.out.println(result);
        assertEquals(expResult, result);
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
    
}
