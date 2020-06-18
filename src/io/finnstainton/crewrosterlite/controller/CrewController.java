/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.controller;

import io.finnstainton.crewrosterlite.model.Records;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author finnstainton
 */
public class CrewController implements ActionListener{
    private final Records crewRecords;
    
    public CrewController(Records crewRecords) {
        this.crewRecords = crewRecords;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
