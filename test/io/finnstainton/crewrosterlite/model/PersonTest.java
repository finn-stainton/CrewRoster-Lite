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
public class PersonTest {
    private static Person person;
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        person = new Person("CP0001", "Finn", "Stainton", "example@example.com");
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
     * Test of getID method, of class Person.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Person instance = person;
        String expResult = "CP0001";
        String result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of setID method, of class Person.
     */
    @Test
    public void testSetID() {
        System.out.println("setID");
        String ID = "CP0001";
        Person instance = person;
        instance.setID(ID);
    }

    /**
     * Test of getFirstName method, of class Person.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Person instance = person;
        String expResult = "Finn";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Person.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "Finn";
        Person instance = person;
        instance.setFirstName(firstName);
    }

    /**
     * Test of getLastName method, of class Person.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Person instance = person;
        String expResult = "Stainton";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Person.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "Stainton";
        Person instance = person;
        instance.setLastName(lastName);
    }

    /**
     * Test of getContact method, of class Person.
     */
    @Test
    public void testGetContact() {
        System.out.println("getContact");
        Person instance = person;
        String expResult = "example@example.com";
        String result = instance.getContact();
        assertEquals(expResult, result);
    }

    /**
     * Test of setContact method, of class Person.
     */
    @Test
    public void testSetContact() {
        System.out.println("setContact");
        String contact = "example@example.com";
        Person instance = person;
        instance.setContact(contact);
    }

    /**
     * Test of getFullName method, of class Person.
     */
    @Test
    public void testGetFullName() {
        System.out.println("getFullName");
        Person instance = person;
        String expResult = "Finn Stainton";
        String result = instance.getFullName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getInitials method, of class Person.
     */
    @Test
    public void testGetInitials() {
        System.out.println("getInitials");
        Person instance = person;
        String expResult = "FS";
        String result = instance.getInitials();
        assertEquals(expResult, result);
    }

    /**
     * Test of hashCode method, of class Person.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Person instance = person;
        int expResult = 603638834;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Person.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Person("CP0001", "Finn", "Stainton", "example@example.com");
        Person instance = person;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Person instance = person;
        String expResult = "Finn Stainton, Contact: example@example.com";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
