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
    private Records<String> stringRecords;
    private String testKey = "Key";
    private String testValue = "Test string data";
    
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
        stringRecords = new Records<>();
        
        stringRecords.addValue(testKey, testValue);
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
        String key = testKey;
        Records instance = stringRecords;
        Object expResult = testValue;
        Object result = instance.getValue(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of addValue method, of class Records.
     */
    @Test
    public void testAddValue() {
        System.out.println("addValue");
        String key = "Add Key";
        String value = "Add Value";
        Records instance = stringRecords;
        boolean expResult = true;
        boolean result = instance.addValue(key, value);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsKey method, of class Records.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        String key = testKey;
        Records instance = stringRecords;
        boolean expResult = true;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
    }

    /**
     * Test of containsValue method, of class Records.
     */
    @Test
    public void testContainsValue() {
        System.out.println("containsValue");
        Object value = testValue;
        Records instance = stringRecords;
        boolean expResult = true;
        boolean result = instance.containsValue(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of removeValue method, of class Records.
     */
    @Test
    public void testRemoveValue() {
        System.out.println("removeValue");
        String key = testKey;
        Records instance = stringRecords;
        Object expResult = testValue;
        Object result = instance.removeValue(key);
        assertEquals(expResult, result);
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
    }

    /**
     * Test of getKeyArray method, of class Records.
     */
    @Test
    public void testGetKeyArray() {
        System.out.println("getKeyArray");
        Records instance = stringRecords;
        String[] expResult = new String[]{testKey};
        String[] result = instance.getKeyArray();
        assertArrayEquals(expResult, result);
    }

    /**
     * Test of isEmpty method, of class Records.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        Records instance = stringRecords;
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
    }

    /**
     * Test of printValues method, of class Records.
     */
    @Test
    public void testPrintValues() {
        System.out.println("printValues");
        Records instance = stringRecords;
        instance.printValues();
    }
    
}
