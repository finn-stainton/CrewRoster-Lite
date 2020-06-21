/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Crew;
import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Records;
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
public class DatabaseTest {
    
    public DatabaseTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of connect method, of class Database.
     */
    @Test
    public void testConnect() {
        System.out.println("connect");
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.connect();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dbSetup method, of class Database.
     */
    @Test
    public void testDbSetup() {
        System.out.println("dbSetup");
        Database instance = new Database();
        instance.dbSetup();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkTable method, of class Database.
     */
    @Test
    public void testCheckTable() {
        System.out.println("checkTable");
        String tableName = "";
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.checkTable(tableName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addCrew method, of class Database.
     */
    @Test
    public void testAddCrew() {
        System.out.println("addCrew");
        Crew crew = null;
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.addCrew(crew);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClient method, of class Database.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        Client client = null;
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.addClient(client);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addJob method, of class Database.
     */
    @Test
    public void testAddJob() {
        System.out.println("addJob");
        Job job = null;
        Database instance = new Database();
        boolean expResult = false;
        boolean result = instance.addJob(job);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadCrew method, of class Database.
     */
    @Test
    public void testLoadCrew() {
        System.out.println("loadCrew");
        Records<Crew> crewRecords = null;
        Database instance = new Database();
        instance.loadCrew(crewRecords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadClients method, of class Database.
     */
    @Test
    public void testLoadClients() {
        System.out.println("loadClients");
        Records<Client> clientRecords = null;
        Database instance = new Database();
        instance.loadClients(clientRecords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadClientContacts method, of class Database.
     */
    @Test
    public void testLoadClientContacts() {
        System.out.println("loadClientContacts");
        Records<Client> clientRecords = null;
        Database instance = new Database();
        instance.loadClientContacts(clientRecords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadJobs method, of class Database.
     */
    @Test
    public void testLoadJobs() {
        System.out.println("loadJobs");
        Records<Job> jobRecords = null;
        Database instance = new Database();
        instance.loadJobs(jobRecords);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadJobEvents method, of class Database.
     */
    @Test
    public void testLoadJobEvents() {
        System.out.println("loadJobEvents");
        Job job = null;
        Database instance = new Database();
        instance.loadJobEvents(job);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadCrewEvents method, of class Database.
     */
    @Test
    public void testLoadCrewEvents() {
        System.out.println("loadCrewEvents");
        Crew crew = null;
        Database instance = new Database();
        instance.loadCrewEvents(crew);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
