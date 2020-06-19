/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite.controller;

import io.finnstainton.crewrosterlite.model.CrewRosterLiteModel;
import io.finnstainton.crewrosterlite.view.CrewRosterLiteView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author finnstainton
 */
public class CrewRosterLiteController implements ActionListener{
    private CrewRosterLiteModel model;
    private CrewRosterLiteView view;
    
    // Controllers
    private final CrewController crewController;
    private final ClientController clientController;
    private final JobController jobController;
    
    public CrewRosterLiteController() {
        this.crewController = new CrewController(CrewRosterLiteModel.getCrewRecords());
        this.clientController = new ClientController(CrewRosterLiteModel.getClientRecords());
        this.jobController = new JobController(CrewRosterLiteModel.getJobRecords());
    }
    
    public void addModel(CrewRosterLiteModel model) {
        this.model = model;
    }
    
    public void addView(CrewRosterLiteView view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
