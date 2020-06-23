/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
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
public class CrewTest {
    private Crew crew;
    private Records<Event> eventRecords;
    
    public CrewTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        crew = new Crew("CR0001", "Finn", "Stainton", "example@example.com");
        Event e = new Event("EV000101", "JB0001", LocalDate.parse("2020-01-01"), LocalTime.parse("00:00"), LocalTime.parse("23:59"), "Location", Event.EventType.General);
        crew.getEvents().addValue(e.getID(), e);
        eventRecords = new Records<>();
        eventRecords.addValue(e.getID(), e);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUsername method, of class Crew.
     */
    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        Crew instance = crew;
        String expResult = "staintonf";
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    /**
     * Test of setUsername method, of class Crew.
     */
    @Test
    public void testSetUsername() {
        System.out.println("setUsername");
        String username = "staintonfinn";
        Crew instance = crew;
        instance.setUsername(username);
    }

    /**
     * Test of getEvents method, of class Crew.
     */
//    @Test
//    public void testGetEvents() {
//        System.out.println("getEvents");
//        Crew instance = crew;
//        Records<Event> expResult = eventRecords;
//        Records<Event> result = instance.getEvents();
//        assertEquals(expResult, result);
//    }

    /**
     * Test of setEvents method, of class Crew.
     */
    @Test
    public void testSetEvents() {
        System.out.println("setEvents");
        Records<Event> events = eventRecords;
        Crew instance = crew;
        instance.setEvents(events);
    }

    /**
     * Test of getSpecialties method, of class Crew.
     */
    @Test
    public void testGetSpecialties() {
        System.out.println("getSpecialties");
        Crew instance = crew;
        HashSet<Specialties> expResult = new HashSet<>();
        expResult.add(Specialties.General);
        HashSet<Specialties> result = instance.getSpecialties();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpecialties method, of class Crew.
     */
    @Test
    public void testSetSpecialties() {
        System.out.println("setSpecialties");
        HashSet<Specialties> specialties = new HashSet<>();
        specialties.add(Specialties.Sound);
        Crew instance = crew;
        instance.setSpecialties(specialties);
    }

    /**
     * Test of addSpecialty method, of class Crew.
     */
    @Test
    public void testAddSpecialty() {
        System.out.println("addSpecialty");
        Specialties specialty = Specialties.Sound;
        Crew instance = crew;
        boolean expResult = true;
        boolean result = instance.addSpecialty(specialty);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeSpecialty method, of class Crew.
     */
    @Test
    public void testRemoveSpecialty() {
        System.out.println("removeSpecialty");
        Specialties specialty = Specialties.Sound;
        Crew instance = crew;
        boolean expResult = true;
        boolean result = instance.removeSpecialty(specialty);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAvailable method, of class Crew.
     */
    @Test
    public void testIsAvailable() {
        System.out.println("isAvailable");
        LocalDate date = LocalDate.parse("2020-01-01");
        LocalTime start = LocalTime.parse("10:00");
        LocalTime end =  LocalTime.parse("23:59");
        Crew instance = crew;
        boolean expResult = true;
        boolean result = instance.isAvailable(date, start, end);
        assertEquals(expResult, result);
    }
    
}
