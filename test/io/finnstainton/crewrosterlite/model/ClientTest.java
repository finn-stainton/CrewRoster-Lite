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
public class ClientTest {
    private Client client;
    
    public ClientTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        client = new Client("CT0001", "Client Title");
        client.setContact(new Person("CP0001", "First", "Last", "example@example.com"));
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getID method, of class Client.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Client instance = client;
        String expResult = "CT0001";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class Client.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Client instance = client;
        String expResult = "Client Title";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setName method, of class Client.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "Client Name";
        Client instance = client;
        instance.setName(name);
    }

    /**
     * Test of getContact method, of class Client.
     */
    @Test
    public void testGetContact() {
        System.out.println("getContact");
        Client instance = client;
        Person expResult = new Person("CP0001", "First", "Last", "example@example.com");
        Person result = instance.getContact();
        assertEquals(expResult, result);
    }

    /**
     * Test of setContact method, of class Client.
     */
    @Test
    public void testSetContact() {
        System.out.println("setContact");
        Person contact = new Person("CP0001", "First", "Last", "example@example.com");;
        Client instance = client;
        instance.setContact(contact);
    }

    /**
     * Test of toString method, of class Client.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Client instance = client;
        String expResult = "Client Title";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
