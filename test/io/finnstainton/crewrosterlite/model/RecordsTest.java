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
public class RecordsTest {
    
    public RecordsTest() {
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
     * Test of getValue method, of class Records.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        String key = "";
        Records instance = new Records();
        Object expResult = null;
        Object result = instance.getValue(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of addValue method, of class Records.
     */
    @Test
    public void testAddValue() {
        System.out.println("addValue");
        String key = "";
        Object value = null;
        Records instance = new Records();
        boolean expResult = false;
        boolean result = instance.addValue(key, value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsKey method, of class Records.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        String key = "";
        Records instance = new Records();
        boolean expResult = false;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsValue method, of class Records.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");
        Object value = null;
        Records instance = new Records();
        boolean expResult = false;
        boolean result = instance.containsValue(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeValue method, of class Records.
     */
    @Test
    public void testRemoveValue() {
        System.out.println("removeValue");
        String key = "";
        Records instance = new Records();
        Object expResult = null;
        Object result = instance.removeValue(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberValues method, of class Records.
     */
    @Test
    public void testGetNumberValues() {
        System.out.println("getNumberValues");
        Records instance = new Records();
        int expResult = 0;
        int result = instance.getNumberValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKeyArray method, of class Records.
     */
    @Test
    public void testGetKeyArray() {
        System.out.println("getKeyArray");
        Records instance = new Records();
        String[] expResult = null;
        String[] result = instance.getKeyArray();
        assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class Records.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Records instance = new Records();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of printValues method, of class Records.
     */
    @Test
    public void testPrintValues() {
        System.out.println("printValues");
        Records instance = new Records();
        instance.printValues();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
