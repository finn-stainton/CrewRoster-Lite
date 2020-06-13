/*
 * Dreamt, Designed, Developed by Finn Stainton (c) 2020.
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.ClientRecords;
import io.finnstainton.crewrosterlite.model.CrewRecords;
import io.finnstainton.crewrosterlite.model.JobRecords;

/**
 *
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteApp {
    public static final int MAX_YEAR = 2100;
    private static CrewRecords crewRecords;
    private static ClientRecords clientRecords;
    private static JobRecords jobRecords;
    
    /**
     * Constructor, Sets up a {@code CrewRosterLiteApp}
     */
    private CrewRosterLiteApp() {
        crewRecords = new CrewRecords();
        clientRecords = new ClientRecords();
        jobRecords = new JobRecords();
    }

    public static ClientRecords getClientRecords() {
        return clientRecords;
    }

    public static CrewRecords getCrewRecords() {
        return crewRecords;
    }
    
    /**
     * runApp prints the main menu and then executes the users input 
     * until the user exits
     */
    private void runApp() {
        
    }
    
    /******************** Main ********************/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CrewRosterLiteApp app = new CrewRosterLiteApp();

        //Run App
        app.runApp();
    }
}
