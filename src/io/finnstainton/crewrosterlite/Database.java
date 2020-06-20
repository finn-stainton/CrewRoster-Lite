/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Crew;
import io.finnstainton.crewrosterlite.model.Event;
import io.finnstainton.crewrosterlite.model.Job;
import io.finnstainton.crewrosterlite.model.Person;
import io.finnstainton.crewrosterlite.model.Records;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 *
 * @author finnstainton
 */
public class Database {
    private final String dbURL = "jdbc:derby://localhost:1527/CrewRosterDB;create=true";
    private final String dbUsername = "dbadmin";
    private final String dbPassword = "crewroster";
    private Connection conn;
    private Statement statement;
    
    public Database() {
//        try{
//            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }

        if (!connect()) {
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(null, "DB Connection Fail", 
                    "Couldn't open a connection with the Database", 
                    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
            System.exit(0);
        }
        dbSetup();
    }
    
    public boolean connect() {
        boolean success = false;
        try {
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("DATABASE: Connected");
            statement = conn.createStatement();
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
        return success;
    }
    
    public void dbSetup() {
        try {
            // Crew Table
            if (!checkTable("Crew")) {
                statement.executeUpdate("CREATE TABLE Crew (ID VARCHAR(8) PRIMARY, FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), CONTACT VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Crew (ID, FIRSTNAME, LASTNAME, CONTACT) VALUES ('CR0001', 'John', 'Blogs', '021 1 234 5678)");
            }
            
            // Clients Table
            if (!checkTable("Clients")) {
                statement.executeUpdate("CREATE TABLE Clients (ID VARCHAR(8) PRIMARY, TITLE VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Clients (ID, TITLE) VALUES ('CT0001', 'Big Company')");
            }
            
            // Client Contact Person Table
            if (!checkTable("ClientContacts")) {
                statement.executeUpdate("CREATE TABLE ClientContacts (ID VARCHAR(8) PRIMARY, FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), CONTACT VARCHAR(255), CLIENTID VARCHAR(255)");
                statement.executeUpdate("INSERT INTO ClientContacts (ID, FIRSTNAME, LASTNAME, CONTACT, CLIENTID) VALUES ('CP0001', 'Jess', 'Blogs', '027 1234 5678', 'CT0001')");
            }
            
            // Jobs Table
            if (!checkTable("Jobs")) {
                statement.executeUpdate("CREATE TABLE Jobs (ID VARCHAR(8) PRIMARY, TITLE VARCHAR(255), CLIENTID VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Jobs (ID, TITLE, VENUE, CLIENTID) VALUES ('JB0001', 'AGM', 'Spark Arena', 'CT0001')");
            }
                    
            // Events Table
            if (!checkTable("Events")) {
                statement.executeUpdate("CREATE TABLE Events (ID VARCHAR(8) PRIMARY, JOBID VARCHAR(8), EVENTDATE DATE, STARTTIME TIME, FINISHTIME TIME, LOCATION VARCHAR(255), TYPE VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Events (ID, JOBID, DATE, STARTTIME, FINISHTIME, LOCATION, TYPE) VALUES ('EV0001', 'JB0001', '2020-06-20', '09:30:00', '23:59:59', 'FoH', 'PACKIN')");
           
            }
            
            // CrewEvents Table
            if (!checkTable("CrewEvents")) {
                statement.executeUpdate("CREATE TABLE CrewEvents (EVENTID VARCHAR(8) PRIMARY, CREWID VARCHAR(8) PRIMARY");
                statement.executeUpdate("INSERT INTO CrewEvents (EVENTID, CREWID) VALUES ('EV0001', 'CR0001')");
           
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
    
    public boolean checkTable(String tableName) {
        boolean tableExists = false;
        try {
            DatabaseMetaData dbMetaData = conn.getMetaData();
            ResultSet rsMeta = dbMetaData.getTables(null, null, null, null);
            while (rsMeta.next()) {
                String currentTableName = rsMeta.getString("TABLE_NAME");
                if (currentTableName.equalsIgnoreCase(tableName)) {
                    tableExists = true;
                    break;
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        return tableExists;
    }
    
    // Sign In
    
    // Sign Up(Register)
    
    // Add Crew
    public boolean addCrew(Crew crew) {
        boolean success = false;
        try {
            // Add Client
            statement.executeUpdate("INSERT INTO Crew (ID, FIRSTNAME, LASTNAME, CONTACT) VALUES ('" + crew.getID() + "', '" + crew.getFirstName() + "', '" + crew.getLastName() + "', '" + crew.getContact() +"')");
            
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return success;
    }
    
    // Add Client
    public boolean addClient(Client client) {
        boolean success = false;
        try {
            // Add Client
            statement.executeUpdate("INSERT INTO Clients (ID, TITLE) VALUES ('" + client.getID() + "', '" + client.getName() +"')");
            
            Person person = client.getContact();
            //Add Contact Persons
            statement.executeUpdate("INSERT INTO ClientContacts (ID, FIRSTNAME, LASTNAME, CONTACT, CLIENTID) VALUES ('" + person.getID() + "', '" + person.getFirstName() + "', '" + person.getLastName() + "', '" + person.getContact() + "', '" + client.getID());
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return success;
    }
    
    // Add Client contact
    
    // Add Job
    public boolean addJob(Job job) {
        boolean success = false;
        try {
            // Add Client
            statement.executeUpdate("INSERT INTO Jobs (ID, TITLE, VENUE, CLIENTID) VALUES ('" + job.getID() + "', '" + job.getTitle() + "', '" + job.getVenue() + "', '" + job.getClientID() +"')");
            
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return success;
    }
    
    // Add Event
    public boolean addEvent(Event event) {
        boolean success = false;
        try {
            // Add Client
            statement.executeUpdate("INSERT INTO Events (ID, JOBID, DATE, STARTTIME, FINISHTIME, LOCATION, TYPE) VALUES ('" + event.getID() + "', '" + event.getParentJob() + "', '" + event.getDate().toString() + "', '" + event.getStartTime().toString() + "', '" + event.getFinishTime().toString() + "', '" + event.getLocation() + "', '" + event.getType().toString().toUpperCase() + "')");
            
            String[] IDs = event.getCrewIDs();
            for(int c = 0; c < IDs.length; c++) {
                try {
                    statement.executeUpdate("INSERT INTO ClientContacts (EVENTID, CREWID) VALUES ('" + event.getID() + "', '" + IDs[c] + "'");
                } catch (SQLException ex) {
                    System.err.println("SQL Exception: " + ex.getMessage());
                }
            }
            //Add Crew to Event
           
            success = true;
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
        return success;
    }
    
    // Load Crew
    public void loadCrew(Records<Crew> crewRecords) {
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Crew");
            
            while(rs.next()){
                Crew crew = new Crew(rs.getString("ID"), rs.getString("FIRSTNAME"), rs.getString("LASTNAME"), rs.getString("CONTACT"));
                
                // Get All events
                this.loadCrewEvents(crew);
                
                crewRecords.addValue(crew.getID(), crew);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    // Load Clients
    public void loadClients(Records<Client> clientRecords) {
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Clients");
            
            while(rs.next()){
                Person contact = null;
                try{
                    ResultSet result = statement.executeQuery("SELECT * FROM ClientContacts WHERE CLIENTID = '" + rs.getString("ID") + "'");
                    contact = new Person(result.getString("ID"), result.getString("FIRSTNAME"), result.getString("LASTNAME"), result.getString("CONTACT"));
                } catch (SQLException ex) {
                    System.err.println("SQL Exception: " + ex.getMessage());
                } 
                if(contact != null) {
                    Client client = new Client(rs.getString("ID"), rs.getString("TITLE"), contact);
                    clientRecords.addValue(client.getID(), client);
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    // Get all Jobs
    public void loadJobs(Records<Job> jobRecords) {
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Jobs");
            
            while(rs.next()){
                Job j = new Job(rs.getString("ID"), rs.getString("CLIENTID"), rs.getString("TITLE"), rs.getString("VENUE"));
                
                // Get All events
                this.loadJobEvents(j);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    // Get Events
    public void loadJobEvents(Job job) {
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM Events WHERE JOBID = '" + job.getID() + "'");
            
            while(rs.next()){
                Event e = new Event(rs.getString("ID"), rs.getString("JOBID"), LocalDate.parse(rs.getString("DATE")), LocalTime.parse(rs.getString("STARTTIME")), LocalTime.parse(rs.getString("FINISHTIME")), rs.getString("LOCATION"), Event.EventType.valueOf(rs.getString("TYPE")));
                job.getEventRecords().addValue(e.getID(), e);
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }
    
    // Load Events to Crew Object
    public void loadCrewEvents(Crew crew) {
        try{
            ResultSet rs = statement.executeQuery("SELECT * FROM CrewEvents WHERE CREWID = '" + crew.getID() + "'");
            
            while(rs.next()){
                try{
                    ResultSet results = statement.executeQuery("SELECT * FROM Events WHERE ID = '" + rs.getString("EventID") + "'");
                    while(results.next()){
                        Event e = new Event(rs.getString("ID"), rs.getString("JOBID"), LocalDate.parse(rs.getString("DATE")), LocalTime.parse(rs.getString("STARTTIME")), LocalTime.parse(rs.getString("FINISHTIME")), rs.getString("LOCATION"), Event.EventType.valueOf(rs.getString("TYPE")));
                        crew.getEvents().addValue(e.getID(), e);
                    }
                } catch (SQLException ex) {
                    System.err.println("SQL Exception: " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.err.println("SQL Exception: " + ex.getMessage());
        }
    }

    // Remove all Crew
    
    // Remove all Clients
    
    // Remove all ClientContacts
    
    // Remove all Jobs
    
    // Remove all events
    
}
