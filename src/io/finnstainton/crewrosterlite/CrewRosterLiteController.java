/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;
import io.finnstainton.crewrosterlite.panels.CrewForm;
import io.finnstainton.crewrosterlite.panels.EventForm;
import io.finnstainton.crewrosterlite.panels.JobForm;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

/**
 *
 * @author finnstainton
 */
public class CrewRosterLiteController implements ActionListener {
    private CrewRosterLiteModel model;
    private CrewRosterLiteView view;
    
    // Controllers
    
    public CrewRosterLiteController() {
    }
    
    public void addModel(CrewRosterLiteModel model) {
        this.model = model;
    }
    
    public void addView(CrewRosterLiteView view) {
        this.view = view;
    }
    
    // Listeners
    public void listener() {
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String action = e.getActionCommand();
            
            switch(action){
                case "Login in":
                    break;
                case "Save":
                    break;
                case "Add Job": 
                JobForm jobFrame = new JobForm("Add Job", null);
                
                jobFrame.addComponents(jobFrame.getContentPane());
                jobFrame.pack();
                jobFrame.setVisible(true);
                break;
            case "Add Event":
                EventForm eventFrame = new EventForm("Add Event", null);
                
                eventFrame.addComponents(eventFrame.getContentPane());
                eventFrame.pack();
                eventFrame.setVisible(true);
                break;
            case "Add Crew":
                CrewForm crewFrame = new CrewForm();
                crewFrame.pack();
                crewFrame.setVisible(true);
                break;
            }
        } catch(NumberFormatException ex) {
            
        }
    }
}
