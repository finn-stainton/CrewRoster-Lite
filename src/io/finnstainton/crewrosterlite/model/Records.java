/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.model;

import java.util.LinkedHashMap;
import java.util.Observable;

/**
 *
 * @author finnstainton
 */
public class Records<V> extends Observable{
    private LinkedHashMap<String, V> map;
    
    public Records() {
        this.map = new LinkedHashMap<>();
    }
    
    public V getValue(String key) {
        if(key != null  && this.map != null){
             return this.map.get(key);
        }
        return null;
    }
    
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
    
    public boolean containsKey(String key) {
        return this.map.containsKey(key);
    }
    
    public boolean containsValue(V value) {
        return this.map.containsValue(value);
    }
    
    public V removeValue(String key) {
        if(key != null && this.map != null) {
            setChanged();
            notifyObservers(this);
            return this.map.remove(key);
        } else { 
           return null;
        }
    }
    
    public int getNumberValues() {
        if(this.map != null) {
            return this.map.size();
        } else {
            return 0;
        }
    }
    
    public String[] getKeyArray() {
        return this.map.keySet().toArray(new String[this.map.size()]); 
    }
    
    public boolean isEmpty() {
        return this.map.isEmpty();
    }
    
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
