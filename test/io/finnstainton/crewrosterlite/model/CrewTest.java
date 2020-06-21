/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author finns
 */
public class CrewTest {
    private static Crew crew;
    private static Records<Event> testEventRecord = new Records<Event>();
    private static HashSet<Specialties> specialties = new HashSet<>();
    
    public CrewTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {

    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        crew = new Crew("CR0001", "Finn", "Stainton", "example@example.com");
//        Job job = new Job("JB0001", "Job Title", "Venue", "CT0001");
        Event e = new Event("EV000101", "JB0001", LocalDate.parse("2020/01/01"), LocalTime.parse("00:00"), LocalTime.parse("23:59"), "Location", Event.EventType.General);
        crew.getEvents().addValue(e.getID(), e);
        testEventRecord.addValue(e.getID(), e);
        specialties.add(Specialties.General);
    }
    
    @AfterEach
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
        String username = "staintonfm";
        Crew instance = crew;
        instance.setUsername(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEvents method, of class Crew.
     */
    @Test
    public void testGetEvents() {
        System.out.println("getEvents");
        Crew instance = crew;
        Records<Event> expResult = testEventRecord;
        Records<Event> result = instance.getEvents();
        assertEquals(expResult, result);
    }

    /**
     * Test of setEvents method, of class Crew.
     */
    @Test
    public void testSetEvents() {
        System.out.println("setEvents");
        Records<Event> events = testEventRecord;
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
        HashSet<Specialties> expResult = specialties;
        HashSet<Specialties> result = instance.getSpecialties();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSpecialties method, of class Crew.
     */
    @Test
    public void testSetSpecialties() {
        System.out.println("setSpecialties");
        HashSet<Specialties> specialties = new HashSet<Specialties>();
        Crew instance = crew;
        instance.setSpecialties(specialties);
    }

    /**
     * Test of addSpecialty method, of class Crew.
     */
    @Test
    public void testAddSpecialty() {
        System.out.println("addSpecialty");
        Specialties specialty = Specialties.General;
        Crew instance = crew;
        boolean expResult = false;
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
        boolean expResult = false;
        boolean result = instance.removeSpecialty(specialty);
        assertEquals(expResult, result);
    }

    /**
     * Test of isAvailable method, of class Crew.
     */
    @Test
    public void testIsAvailable() {
        System.out.println("isAvailable");
        LocalDate date = LocalDate.parse("2020/01/01");
        LocalTime start = LocalTime.parse("00:00");
        LocalTime end = LocalTime.parse("23:59");
        Crew instance = crew;
        boolean expResult = true;
        boolean result = instance.isAvailable(date, start, end);
        assertEquals(expResult, result);
    }
    
}
