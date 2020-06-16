/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author finnstainton
 */
public class ClientRecordsTest {
    
    public ClientRecordsTest() {
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
     * Test of generateID method, of class ClientRecords.
     */
    @Test
    public void testGenerateID() {
        System.out.println("generateID");
        ClientRecords instance = new ClientRecords();
        String expResult = "";
        String result = instance.generateID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClient method, of class ClientRecords.
     */
    @Test
    public void testAddClient() {
        System.out.println("addClient");
        Client client = null;
        ClientRecords instance = new ClientRecords();
        boolean expResult = false;
        boolean result = instance.addClient(client);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class ClientRecords.
     */
    @Test
    public void testRemoveClient() {
        System.out.println("removeClient");
        String clientID = "";
        ClientRecords instance = new ClientRecords();
        Client expResult = null;
        Client result = instance.removeClient(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsClient method, of class ClientRecords.
     */
    @Test
    public void testContainsClient() {
        System.out.println("containsClient");
        String clientID = "";
        ClientRecords instance = new ClientRecords();
        boolean expResult = false;
        boolean result = instance.containsClient(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientIDs method, of class ClientRecords.
     */
    @Test
    public void testGetClientIDs() {
        System.out.println("getClientIDs");
        ClientRecords instance = new ClientRecords();
        String[] expResult = null;
        String[] result = instance.getClientIDs();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClient method, of class ClientRecords.
     */
    @Test
    public void testGetClient() {
        System.out.println("getClient");
        String clientID = "";
        ClientRecords instance = new ClientRecords();
        Client expResult = null;
        Client result = instance.getClient(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class ClientRecords.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        ClientRecords instance = new ClientRecords();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
