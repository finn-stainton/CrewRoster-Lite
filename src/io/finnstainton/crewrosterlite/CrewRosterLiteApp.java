/*
 * Dreamt, Designed, Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;

/**
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteApp {
    private static CrewRosterLiteModel model;
    private static CrewRosterLiteView view;
    private static CrewRosterLiteController controller;
    
    public CrewRosterLiteApp() {
        model = new CrewRosterLiteModel();
        view = new CrewRosterLiteView();
        controller = new CrewRosterLiteController();
        
        // Add Observers
        model.addObserver(view);
        model.getClientRecords().addObserver(view.getJobForm());
        model.getCrewRecords().addObserver(view);
        model.getJobRecords().addObserver(view.getJobPanel());
        model.getJobRecords().addObserver(view.getJobPanel().getJobListPanel());
        model.getJobRecords().addObserver(view.getJobPanel().getEventListPanel());
        model.getJobRecords().addObserver(view.getEventForm());
        
        controller.addModel(model);
        controller.addView(view);
        
       
        
        view.addController(controller);
        view.setVisible(true);
    }
    
    public static void main(String[] args) {
        CrewRosterLiteApp app = new CrewRosterLiteApp();
        app.model.loadFromDB();
    }
}

 