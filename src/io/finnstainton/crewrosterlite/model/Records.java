/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import java.util.LinkedHashMap;
import java.util.Observable;

/**
 * Home-brew LinkedHashMap which extends Observable to notify Observers when updated.
 * @author finnstainton
 */
public class Records<V> extends Observable{
    private LinkedHashMap<String, V> map;
    
    /**
     * Setup a the Linked Hash Map
     */
    public Records() {
        this.map = new LinkedHashMap<>();
    }
    
    /**
     * @param key of the value to return
     * @return V value 
     */
    public V getValue(String key) {
        if(key != null  && this.map != null){
             return this.map.get(key);
        }
        return null;
    }
    
    /**
     * @param key String of the key to the value
     * @param value V of the main value to be stored
     * @return boolean whether the value was stored
     */
    public boolean addValue(String key, V value) {
        if(!this.map.containsKey(key)) {
            this.map.put(key, value);
            setChanged();
            notifyObservers(this);
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * @param key String to see if there is a mapping containing it
     * @return boolean whether or not it is stored
     */
    public boolean containsKey(String key) {
        return this.map.containsKey(key);
    }
    
    /**
     * @param value V to see if this records has it stored
     * @return boolean whether or not it is stored
     */
    public boolean containsValue(V value) {
        return this.map.containsValue(value);
    }
    
    /**
     * @param key String key of value to remove
     * @return V removed item, or null
     */
    public V removeValue(String key) {
        if(key != null && this.map != null) {
            setChanged();
            notifyObservers(this);
            return this.map.remove(key);
        } else { 
           return null;
        }
    }
    
    /**
     * @return int of the amount of entries
     */
    public int getNumberValues() {
        if(this.map != null) {
            return this.map.size();
        } else {
            return 0;
        }
    }
    
    /**
     * @return String[] of Keys
     */
    public String[] getKeyArray() {
        return this.map.keySet().toArray(new String[this.map.size()]); 
    }
    
    /**
     * @return boolean whether this map is empty
     */
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
    /**
     * Prints values in this map to console
     */
    public void printValues(){
        if(this.map.isEmpty()) {
             System.out.println("No Values.");
        }else {
            this.map.values().forEach((value) -> {
                value.toString();
                System.out.println();
            });
        }
    }
}
