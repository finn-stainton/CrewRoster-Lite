/*
 * Dreamt, Designed and Developed by Finn Stainton (c) 2020
 */
package io.finnstainton.crewrosterlite;

import io.finnstainton.crewrosterlite.model.Client;
import io.finnstainton.crewrosterlite.model.Crew;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            if (!checkTable("Crew")){
                statement.executeUpdate("CREATE TABLE Crew (ID VARCHAR(8) PRIMARY, FIRSTNAME VARCHAR(255), LASTNAME VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Crew (ID, FIRSTNAME, LASTNAME) VALUES ('CR0001', 'John', 'Blogs')");
            }
            
            // Clients Table
            if (!checkTable("Client")){
                statement.executeUpdate("CREATE TABLE Clients (ID VARCHAR(8) PRIMARY, TITLE VARCHAR(255), CONTACT VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Clients (ID, FIRSTNAME, LASTNAME) VALUES ('CR0001', 'John', 'Blogs')");
            }
            
            // Jobs Table
            if (!checkTable("Jobs")){
                statement.executeUpdate("CREATE TABLE Jobs (ID VARCHAR(8) PRIMARY, TITLE VARCHAR(255), CLIENTID VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Jobs (ID, FIRSTNAME, LASTNAME) VALUES ('JB0001', 'Large Company', 'CT0001')");
            }
                    
            // Events Table
            if (!checkTable("Events")){
                statement.executeUpdate("CREATE TABLE Events (ID VARCHAR(8) PRIMARY, JOBID VARCHAR(8), NAME VARCHAR(255), TYPE VARCHAR(255)");
                statement.executeUpdate("INSERT INTO Events (ID, JOBID, NAME, TYPE) VALUES ('EV0001', 'JB0001', 'AGM', 'PACKIN')");
           
            }
            
            // CrewEvents Table
            if (!checkTable("CrewEvents")){
                statement.executeUpdate("CREATE TABLE CrewEvents (EVENTID VARCHAR(8) PRIMARY, CREWID VARCHAR(8) PRIMARY");
                statement.executeUpdate("INSERT INTO Events (ID, FIRSTNAME, LASTNAME) VALUES ('EV0001', 'CR0001')");
           
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
    public void addCrew(Crew crew) {
        
    }
    
    // Load Crew
    
    // Add Client
    public void addClient(Client client) {
        
    }
    
    // Load Client
    
    // Add Job
    
    // Load Job
    
    // Add Event
    
    // Load Event
}
