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
public class Records<K, V> extends Observable{
    private LinkedHashMap<K, V> map;
    
    public V getValue(K key) {
        if(key != null  && this.map != null){
             return this.map.get(key);
        }
        return null;
    }
    
    public boolean addValue(K key, V value) {
        if(!this.map.containsKey(key)) {
             this.map.put(key, value);
             return true;
        } else {
            return false;
        }
    }
    
    public V removeValue(K key) {
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
    
    public K[] getKeyArray() {
        return (K[])this.map.keySet().toArray();
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
