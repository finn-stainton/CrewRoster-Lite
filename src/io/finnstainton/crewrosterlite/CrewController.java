/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.CrewRecords;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author finnstainton
 */
public class CrewController implements ActionListener{
    private final CrewRecords crewRecords;
    
    public CrewController() {
        this.crewRecords = new CrewRecords();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
