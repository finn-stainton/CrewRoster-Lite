/*
 * Dreamt, Designed, Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;

/**
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteApp {
    private final CrewRosterLiteModel model;
    private final CrewRosterLiteView view;
    private final CrewRosterLiteController controller;
    
    public CrewRosterLiteApp() {
        this.model = new CrewRosterLiteModel();
        this.view = new CrewRosterLiteView();
        this.controller = new CrewRosterLiteController();
        
        // Add Observers
        this.model.addObserver(this.view);
        this.model.getClientRecords().addObserver(this.view.getJobForm());
        this.model.getCrewRecords().addObserver(this.view);
        this.model.getJobRecords().addObserver(this.view.getJobPanel());
        
        this.controller.addModel(this.model);
        this.controller.addView(this.view);
        
        this.view.addController(this.controller);
        this.view.setVisible(true);
    }
    
    public static void main(String[] args) {
        CrewRosterLiteApp app = new CrewRosterLiteApp();
    }
}

 