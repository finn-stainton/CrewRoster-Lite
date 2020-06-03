/*
 * Dreamt, Designed, Developed by Finn Stainton (c) 2020.
 */
package crewrosterlite;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author finnstainton (17982742)
 */
public class CrewRosterLiteApp {
    private static final String DATA_PATH = "src/crewrosterlite/data/";
    public static final int MAX_YEAR = 2100;
    private static CrewRecords crewRecords;
    private static ClientRecords clientRecords;
    private static JobRecords jobRecords;
    private boolean run = true;
    private static boolean log = true;
    private String[] options = {"Exit", "Display All Jobs", "Display Job", 
        "Add Job", "Edit Job", "Remove Job", "Add Event", "Edit Event", 
        "Remove Event","Roster Crew", "Add Crew", "Edit Crew", "Remove Crew", 
        "Add Client","Edit Client", "Remove Client", 
        "Help! Print all crew and clients ID", "About", "Save without Quiting"};
    private static String crewPath = DATA_PATH + "crew.xml",
            clientPath = DATA_PATH + "clients.xml",
            jobPath = DATA_PATH + "roster.xml";
    
    /**
     * Constructor, Sets up a {@code CrewRosterLiteApp}
     */
    private CrewRosterLiteApp() {
        crewRecords = new CrewRecords();
        clientRecords = new ClientRecords();
        jobRecords = new JobRecords(this, this.log);
    }
    
    private CrewRosterLiteApp(String crewPath, String clientPath, String jobPath, Boolean log) {
        this();
        this.log = log;
        this.setupCrew(crewPath);
        this.setupClients(clientPath);
    }
    
    private void setupCrew(String crewPath){
        Document crewDoc = buildDocument(crewPath);
        
        //Setup CrewRecords
        if(crewDoc != null) {
            crewRecords = new CrewRecords(crewDoc, log);
        }
    }
    
    private void setupClients(String clientPath) {
        Document clientDoc = buildDocument(clientPath);
        
        //Setup ClientRecords
        if(clientDoc != null) {
            clientRecords = new ClientRecords(clientDoc, log);
        }
    }
    
    private void setupJobs(String jobPath) {
        Document jobDoc = buildDocument(jobPath);
        
        //Setup JobRecords
        if(jobDoc != null) {
            jobRecords = new JobRecords(jobDoc, this, log);
        }
    }

    public static ClientRecords getClientRecords() {
        return clientRecords;
    }

    public static CrewRecords getCrewRecords() {
        return crewRecords;
    }
    
    /**
     * Document builder for xml documents
     * @param filePath
     * @return 
     */
    private Document buildDocument(String filePath) {
        File file = new File(filePath);
        Document document = null;
        
        try{
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            builder.setErrorHandler(null);
            document = builder.parse(file);
            document.getDocumentElement().normalize();
        } catch (FactoryConfigurationError e) {
            System.out.println("FactoryConfigurationError ");
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException "); 
        } catch (SAXException ex) {
            System.out.println("SAX PROBLEM ");
        } catch (IOException ex) {
            System.out.println("File Not Found" + ex.getMessage());
        }
        
        return document;
    }
    
    /**
     * runApp prints the main menu and then executes the users input 
     * until the user exits
     */
    private void runApp() {
        //Welcome Screen
        System.out.println("\n#******************************#");
        System.out.println("#  Welcome to CrewRoster Lite  #");
        System.out.println("#******************************#");
        
        //
        while(run){
            //Print Menu
            this.printMainMenu();
            
            int userInput = -1;
            Scanner scanner = new Scanner(System.in);
            
            //Get user input
            try {
            System.out.print("Option: ");
            userInput = scanner.nextInt();
            
//            //Remove Buffer from Scanner
//            while(scanner.hasNext("\n")) {
//                scanner.next();
//            }
            
            //Universial Text Line Break
            System.out.println("––––––––––––––––––––––––––––––\n");
            } catch(InputMismatchException inputError) {
                //System.err.println("\n$Unsupported Input, Please enter a number.");
            } catch(NoSuchElementException NSEError) {//scanner system.in closed
                this.run = false;
                System.out.println("\n$Error. Call a Nerd");
            }
            
            //Switch depending on option
            switch(userInput) {
                case 0: //Exit
                    System.out.println("Exiting...");
                    
                    //Save files?
                    System.out.print("Do you want to save modifcations (y/n): ");
                    String save = scanner.next();
                    
                    if(save.equalsIgnoreCase("y")){ //Write to file
                        this.save();
                    }
                    this.run = false;
                    break;
                case 1: //Print All Jobs
                    System.out.println("### Display All Jobs ###");
                    jobRecords.printJobs();
                    break;
                case 2: //Print Job
                    jobRecords.consolePrintJob();
                    break;
                case 3: //Add Job
                    jobRecords.consoleAddJob();
                    break;
                case 4: //Edit Job
                    jobRecords.consoleEditJob();
                    break;
                case 5: //Remove Job
                    jobRecords.consoleRemoveJob();
                    break;
                case 6: //Add Event
                    jobRecords.consoleAddJobEvent();
                    break;
                case 7: //Edit Event
                    jobRecords.consoleEditJobEvent();
                    break;
                case 8: //Remove Event
                    jobRecords.consoleRemoveJobEvent();
                    break;
                case 9: //Roster Crew
                    jobRecords.consoleRosterCrew();
                    break;
                case 10: //Add Crew
                    crewRecords.consoleAddCrew();
                    break;
                case 11: //Modify Crew
                    crewRecords.consoleEditCrew();
                    break;
                case 12: //Remove Crew
                    crewRecords.consoleRemoveCrew();
                    break;
                case 13: //Add Client
                    clientRecords.consoleAddClient();
                    break;
                case 14: //Modify Client
                    clientRecords.consoleEditClient();
                    break;
                case 15: //Remove Client
                    clientRecords.consoleRemoveClient();
                    break;
                case 16: //Help!!!
                    this.printHelp();
                    break;
                case 17: //About, Read from file about.txt
                    try{
                        BufferedReader reader = new BufferedReader(new FileReader(DATA_PATH + "about.txt"));
                        String currentLine = "";
                        
                        while((currentLine = reader.readLine()) != null){
                            if(currentLine != null) {
                                System.out.println(currentLine); 
                            }
                        }
                        
                        reader.close();
                    } catch (FileNotFoundException ex) {
                        //Logger.getLogger(CrewRosterLiteApp.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Sorry! I can't tell you about CrewRosterLite right now."); 
                    } catch(IOException e) {
                        //
                    }
                    break;

                case 18: //Saves to file in xml format
                    this.save();
                    
                    break;
                default:
                    System.out.println("Unsupported Option, Please try again.");
            }
        }
        
        //Exit Screen
        System.out.println("GoodBye");
    }
    
    /**
     * 
     * @return 
     */
    private void printMainMenu() {
        //Title
        System.out.println("\n––––––––––––––––––––––––––––––");
        System.out.println("Main Menu:");
        
        //Print all options
        for(int optionCount = 0; optionCount < options.length; optionCount++) {
            System.out.print("(" + optionCount + ") ");
            System.out.println(options[optionCount]);
        }
    }
    
    /**
     * Prints all Crew, Clients, Jobs and there Events
     * currently in program memory to the console.
     */
    private void printHelp() {
        //Crew
        if(crewRecords.isEmpty()){
            System.out.println("No Crew.");
        } else {
            System.out.println("Crew:");
            crewRecords.print();
        }
        
        //Clients
        if(clientRecords.isEmpty()){
            System.out.println("\nNo Clients.");
        } else {
            System.out.println("\nClients:");
            clientRecords.print();
        }
    }
    
    private void save() {
        //Jobs
        if(jobRecords != null) {
            System.out.println("Saving files.");
            String jobFileName = jobRecords.createXMLDocument();
            
            try { //Writes latest file location to filenamestore.txt
                PrintWriter writer = new PrintWriter(new FileOutputStream(DATA_PATH + "filenamestore.txt"));
                
                writer.println("JobFile: " + jobFileName);
                System.out.println("Saved to file:" + jobFileName);
                
                writer.close();
            
            } catch (FileNotFoundException ex) {
                System.out.println("Couldn't save Jobs.");
            }
        } else {
            System.out.println("Couldn't save Jobs.");
        }
    }
    
    /******************** Main ********************/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CrewRosterLiteApp app = new CrewRosterLiteApp(crewPath, clientPath, jobPath, log);

        //Run App
        app.runApp();
    }
}
