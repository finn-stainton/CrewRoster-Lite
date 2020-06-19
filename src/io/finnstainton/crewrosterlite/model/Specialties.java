/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite.model;

import java.util.Arrays;

/**
 *
 * @author finnstainton (17982742)
 */
public enum Specialties {
    General("General"), Lighting("Lighting"), Sound("Sound"), Video("Video"), 
    Camera("Camera"), Rigging("Rigging"), Scissor("Scissor"), Operation("Operation");

    private final String text;
        
    /**
     * @param text
     */
    private Specialties(final String text) {
        this.text = text;
    }
    
    public static String[] getValues() {
        Specialties[] specialties = values();
        String[] values = new String[specialties.length];

        for (int i = 0; i < specialties.length; i++) {
            values[i] = specialties[i].name();
        }
        
        return values;
    }

    @Override
    public String toString() {
        return text;
    }
    
}
