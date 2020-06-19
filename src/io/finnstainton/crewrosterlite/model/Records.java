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
public class Records<String, V> extends Observable{
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
             return true;
        } else {
            return false;
        }
    }
    
    public V removeValue(String key) {
        if(key != null && this.map != null) {
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
    
    
    public Object[] getKeyArray() {
        // Won't let me do this ???
//        return this.map.keySet().toArray(new String[this.map.size()]);
        // But this returns a Object[], even though key is String
        return this.map.keySet().toArray();
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
