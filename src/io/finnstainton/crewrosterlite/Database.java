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
import java.net.InetAddress;
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
    private final String dbURL = "jdbc:derby:CrewRosterDB;create=true";
    private final String dbUsername = "dbadmin";
    private final String dbPassword = "crewroster";
    private Connection conn;
    private Statement statement;
    private ResultSet crewRS, clientRS, contactRS, jobRS, eventRS;
    
    public Database() {
//        try{
//            DriverManager.registerDriver(new org.apache.derby.jdbc.EmbeddedDriver());
//        } catch (SQLException ex) {
//            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
//        }

        if (!connect()) {
            Object[] options = {"OK"};
            JOptionPane.showOptionDialog(null, "DB Connection Fail", 
                    "Couldn't open a connection with the Database. There maybe multiple "
                            + "\"CrewRoster Lite \" windows open. Make sure you only have one window open.", 
                    JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE, null, options, null);
            System.exit(0);
        }
        
        // Setup databases, if not already setup
        dbSetup();
    }
    
    public boolean connect() {
        boolean success = false;
        try {
//            NetworkServerControl serverControl = new NetworkServerControl(InetAddress.getByName("CrewRosterDB"),1527);
//            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
            System.out.println("DATABASE: Connected");
            conn.setAutoCommit(false);
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
                statement.executeUpdate("CREATE TABLE Crew (ID VARCHAR(8), FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), CONTACT VARCHAR(255), PRIMARY KEY(ID))");
                statement.executeUpdate("INSERT INTO Crew (ID, FIRSTNAME, LASTNAME, CONTACT) VALUES ('CR0001', 'John', 'Blogs', '021 1 234 5678')");
            }
            
            // Clients Table
            if (!checkTable("Clients")) {
                statement.executeUpdate("CREATE TABLE Clients (ID VARCHAR(8), TITLE VARCHAR(255), PRIMARY KEY(ID))");
                statement.executeUpdate("INSERT INTO Clients (ID, TITLE) VALUES ('CT0001', 'Big Company')");
            }
            
            // Client Contact Person Table
            if (!checkTable("ClientContacts")) {
                statement.executeUpdate("CREATE TABLE ClientContacts (ID VARCHAR(8), FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255), CONTACT VARCHAR(255), CLIENTID VARCHAR(255), PRIMARY KEY(ID))");
                statement.executeUpdate("INSERT INTO ClientContacts (ID, FIRSTNAME, LASTNAME, CONTACT, CLIENTID) VALUES ('CP0001', 'Jess', 'Blogs', '027 1234 5678', 'CT0001')");
            }
            
            // Jobs Table
            if (!checkTable("Jobs")) {
                statement.executeUpdate("CREATE TABLE Jobs (ID VARCHAR(8), TITLE VARCHAR(255), VENUE VARCHAR(255), CLIENTID VARCHAR(255), PRIMARY KEY(ID))");
                statement.executeUpdate("INSERT INTO Jobs (ID, TITLE, VENUE, CLIENTID) VALUES ('JB0001', 'AGM', 'Spark Arena', 'CT0001')");
            }
                    
            // Events Table
            if (!checkTable("Events")) {
                statement.executeUpdate("CREATE TABLE Events (ID VARCHAR(8), JOBID VARCHAR(8), EVENTDATE DATE, STARTTIME TIME, FINISHTIME TIME, LOCATION VARCHAR(255), TYPE VARCHAR(255), PRIMARY KEY(ID))");
                statement.executeUpdate("INSERT INTO Events (ID, JOBID, DATE, STARTTIME, FINISHTIME, LOCATION, TYPE) VALUES ('EV0001', 'JB0001', '2020-06-20', '09:30:00', '23:59:59', 'FoH', 'PACKIN')");
           
            }
            
            // CrewEvents Table
            if (!checkTable("CrewEvents")) {
                statement.executeUpdate("CREATE TABLE CrewEvents (EVENTID VARCHAR(8), CREWID VARCHAR(8) NOT NULL, PRIMARY KEY(EVENTID))");
                statement.executeUpdate("ALTER TABLE CrewEvents ADD PRIMARY KEY (CREWID)");
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
            System.err.println("checkTable SQL Exception: " + ex.getMessage());
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
            System.err.println("addCrew SQL Exception: " + ex.getMessage());
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
            System.err.println("addClient SQL Exception: " + ex.getMessage());
        }
        return success;
    }
        
    // Add Job
    public boolean addJob(Job job) {
        boolean success = false;
        try {
            // Add Client
            statement.executeUpdate("INSERT INTO Jobs (ID, TITLE, VENUE, CLIENTID) VALUES ('" + job.getID() + "', '" + job.getTitle() + "', '" + job.getVenue() + "', '" + job.getClientID() +"')");
            
            success = true;
        } catch (SQLException ex) {
            System.err.println("addJob SQL Exception: " + ex.getMessage());
        }
        return success;
    }
    
    // Add Event
    private boolean addEvent(Event event) {
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
            System.err.println("addEvent SQL Exception: " + ex.getMessage());
        }
        return success;
    }
    
    // Load Crew
    public void loadCrew(Records<Crew> crewRecords) {
        if(checkTable("Crew")) {
            try{
                crewRS = statement.executeQuery("SELECT * FROM Crew");
                while(crewRS.next()) {
                    Crew crew = new Crew(crewRS.getString("ID"), crewRS.getString("FIRSTNAME"), crewRS.getString("LASTNAME"), crewRS.getString("CONTACT")); 
                    crewRecords.addValue(crew.getID(), crew);
                    System.out.println("Loaded from DB: " + crew);
                } 
            } catch (SQLException ex) {
                System.err.println("loadCrew SQL Exception: " + ex.getMessage());
            }
        }
    }
    
    // Load Clients
    public void loadClients(Records<Client> clientRecords) {
        if(checkTable("Clients")) {
            try{
                clientRS = statement.executeQuery("SELECT * FROM Clients");

                while(clientRS.next()){
                    Client client = new Client(clientRS.getString("ID"), clientRS.getString("TITLE"));
                    clientRecords.addValue(client.getID(), client);
                    System.out.println("Loaded from DB: " + client);
                }
            } catch (SQLException ex) {
                System.err.println("loadClients SQL Exception: " + ex.getMessage());
            }
        }
    }
    
    public void loadClientContacts(Records<Client> clientRecords) {
        if(checkTable("ClientContacts")) {
            for(String ID : clientRecords.getKeyArray()) {
            Client client = clientRecords.getValue(ID);
                try{
                    contactRS = statement.executeQuery("SELECT * FROM ClientContacts WHERE CLIENTID = '" + clientRS.getString("ID") + "'");

                    while(contactRS.next()){
                        Person contact = new Person(contactRS.getString("ID"), contactRS.getString("FIRSTNAME"), contactRS.getString("LASTNAME"), contactRS.getString("CONTACT"));
                        client.setContact(contact);
                    }

                } catch (SQLException ex) {
                     System.err.println("SQL Exception: " + ex.getMessage());
                } 
            }
        }
    }
    
    // Get all Jobs
    public void loadJobs(Records<Job> jobRecords) {
        if(checkTable("Jobs")) {
            try{
                jobRS = statement.executeQuery("SELECT * FROM Jobs");

                while(jobRS.next()){
                    Job j = new Job(jobRS.getString("ID"), jobRS.getString("CLIENTID"), jobRS.getString("TITLE"), jobRS.getString("VENUE"));

                    // Get All events
                    this.loadJobEvents(j);

                    jobRecords.addValue(j.getID(), j);
                    System.out.println("Loaded from DB: " + j);
                }
            } catch (SQLException ex) {
                System.err.println("loadJobs SQL Exception: " + ex.getMessage());
            }
        }
    }
    
    // Get Events
    public void loadJobEvents(Job job) {
        try{
            ResultSet eventRS = statement.executeQuery("SELECT * FROM Events WHERE JOBID = '" + job.getID() + "'");
            
            while(eventRS.next()){
                Event e = new Event(eventRS.getString("ID"), eventRS.getString("JOBID"), LocalDate.parse(eventRS.getString("DATE")), LocalTime.parse(eventRS.getString("STARTTIME")), LocalTime.parse(eventRS.getString("FINISHTIME")), eventRS.getString("LOCATION"), Event.EventType.valueOf(eventRS.getString("TYPE")));
                job.getEventRecords().addValue(e.getID(), e);
            }
        } catch (SQLException ex) {
            System.err.println("loadJobEvents SQL Exception: " + ex.getMessage());
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
            System.err.println("loadCrewEvents SQL Exception: " + ex.getMessage());
        }
    }

    // Remove all Crew
    
    // Remove all Clients
    
    // Remove all ClientContacts
    
    // Remove all Jobs
    
    // Remove all events
    
}
