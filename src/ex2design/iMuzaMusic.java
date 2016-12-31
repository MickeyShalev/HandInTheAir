/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import entities.*;
import ex2design.utilities.EArtistStatus;
import java.util.Date;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ex2design.utilities.EAuth;
import gui.internal.SuccessExport;
import gui.internal.frmManageArtists;
import gui.main.iWindow;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;

/**
 *
 * @author Administrator
 */
public class iMuzaMusic {

    private static DBManager DB;
    private static Person loggedUser = null;
    private static String fileName = "iMuzaMusicLogger";
    private static FileWriter logFile;
    private static PrintStream logWriter;

    public iMuzaMusic() {
        //Reset log
        try {
            SimpleDateFormat sdfDate = new SimpleDateFormat("ddM_hhmm");
            Date now = new Date();
            String strDate = sdfDate.format(now);
            logWriter = new PrintStream(new File(fileName+"_"+strDate+".log"));
            System.setErr(logWriter);
            System.setOut(logWriter);
            logWriter.print("=================  iMuzaMusic v1.0 - " + new Date() + " ==================" + System.getProperty("line.separator"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.err.println("Cannot write to log file!");
            System.exit(0);
        }

        //Initiate DB
        init();

    }

    public static DBManager getDB() {
        return DB;
    }

    public static void setDB(DBManager DB) {
        iMuzaMusic.DB = DB;
    }

    public static Person getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(Person loggedUser) {
        iMuzaMusic.loggedUser = loggedUser;
    }

    /**
     * Initiates a DB Connection
     */
    public void init() {
        try {
            log("Attempting connection to MS Access DB");
            DB = new DBManager();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(iMuzaMusic.class.getName()).log(Level.SEVERE, null, ex);
            return;
        } catch (SQLException ex) {
            Logger.getLogger(iMuzaMusic.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        log("Successfully connected to MS Access DB.");
    }

    /**
     * Internal Logging
     *
     */
    public static void log(String str) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm:ss");
        Date now = new Date();
        String strDate = sdfDate.format(now);
        logWriter.print(strDate + "\t" + str + System.getProperty("line.separator"));
        logWriter.flush();
    }

    public static boolean logIn(String id, String pass) throws SQLException {
        ResultSet tmp = null;
        Person tmpPerson = null;
        log("Attempting login using " + id + "/" + pass);
        /**
         * TO BE DELETED id = id.replace("Cust",""). replace("RE", "").
         * replace("AR", ""). replace("AAG", ""). replace("LR", "");
         *
         */

        tmp = iMuzaMusic.DB.query("select * from Customers where ClientID=\"" + id + "\" AND strPasswd=\"" + pass + "\"");
        if (tmp.next()) {
            log("test");
            if (tmp.getString(1).length() > 0) {
                //Logged in as customer
                String ID = tmp.getString("ClientID");
                String strFirstName = tmp.getString("strFirstName");
                String strLastName = tmp.getString("strLastName");
                Person p = new Customer(ID, strFirstName, strLastName, pass, EAuth.Customer);
                loggedUser = p;
                log("Customer logged in");
                return true;
            }
        }

        tmp = iMuzaMusic.DB.query("select * from Agents where AgentID=\"" + id + "\" AND strPasswd=\"" + pass + "\"");
        if (tmp.next()) {
            if (tmp.getString(1).length() > 0) {
                //Logged in as agent
                String ID = tmp.getString("AgentID");
                String strFirstName = tmp.getString("FirstName");
                String strLastName = tmp.getString("LastName");
                Person p = new Agent(ID, strFirstName, strLastName, pass, EAuth.Agent);
                loggedUser = p;
                log("Agent logged in");
                return true;
            }

        }

        tmp = iMuzaMusic.DB.query("select * from Artists where ArtistID=\"" + id + "\" AND strPasswd=\"" + pass + "\"");
        if (tmp.next()) {
            if (tmp.getString(1).length() > 0) {
                //Logged in as agent
                String ID = tmp.getString("ArtistID");
                String strFirstName = tmp.getString("strStageName");
                String strLastName = "";
                String bio = tmp.getString("strShortBio");
                String stageName = tmp.getString("strStageName");
                String arStatus = tmp.getString("iStatus");
                System.err.println("status: " + arStatus);
                Person p = new Artist(ID, strFirstName, strLastName, pass, EAuth.Artist, bio, stageName, bio, EArtistStatus.Active, "");
                loggedUser = p;
                log("Artist logged in");
                return true;
            }

        }
        tmp = iMuzaMusic.DB.query("select * from LReps where LRepID=\"" + id + "\" AND strPasswd=\"" + pass + "\"");
        if (tmp.next()) {
            if (tmp.getString(1).length() > 0) {
                //Logged in as agent
                String ID = tmp.getString("LRepID");
                String strFirstName = tmp.getString("FirstName");
                String strLastName = tmp.getString("LastName");
                Person p = new LRep(ID, strFirstName, strLastName, pass, EAuth.Location_Representative);
                loggedUser = p;
                log("Location Represantative logged in");
                return true;
            }

        }

        tmp = iMuzaMusic.DB.query("select * from Reps where RepID=\"" + id + "\" AND strPasswd=\"" + pass + "\"");
        if (tmp.next()) {
            if (tmp.getString(1).length() > 0) {
                //Logged in as agent
                String ID = tmp.getString("RepID");
                String strFirstName = tmp.getString("FirstName");
                String strLastName = tmp.getString("LastName");
                Person p = new Rep(ID, strFirstName, strLastName, pass, EAuth.Representative);
                loggedUser = p;
                log("Artist logged in");
                return true;
            }

        }
        return false;

    }

    public static String getID(String str) {

        try {
            String tmp[] = str.split("\\(");
            tmp = tmp[1].split("\\)");
            str = tmp[0];
        } catch (Exception e) {
            iMuzaMusic.log("" + e.getStackTrace());
            e.printStackTrace();
        }
        return str;
    }

    /**
     * This method returns a new artist entity from a given artist id
     *
     * @param ArtistID
     * @return
     */
    public static Artist getAgentEntity(String ArtistID) {
        Artist toReturn = null;

        ResultSet getAgent = iMuzaMusic.getDB().query("select * from Artists where ArtistID=\"" + ArtistID + "\"");
        try {
            while (getAgent.next()) {
                String ID = getAgent.getString("ArtistID");
                String strFirstName = getAgent.getString("strStageName");
                String strLastName = "";
                String pass = getAgent.getString("strPasswd");
                String bio = getAgent.getString("strShortBio");
                String stageName = getAgent.getString("strStageName");
                String emailAddr = getAgent.getString("strEmailAddr");
                String fbAddr = getAgent.getString("strFacebook");
                /**
                 * EArtist Status *
                 */
                EArtistStatus arStatus = null;
                switch (getAgent.getString("iStatus")) {
                    case "1":
                        arStatus = EArtistStatus.Active;
                        break;
                    case "2":
                        arStatus = EArtistStatus.Inactive;
                        break;

                    default:
                        System.exit(0);
                        break;
                }

                System.err.println(arStatus);
                toReturn = new Artist(ID, strFirstName, strLastName, pass, EAuth.Artist, bio, stageName, fbAddr, arStatus, emailAddr);

            }
        } catch (SQLException ex) {
            Logger.getLogger(frmManageArtists.class.getName()).log(Level.SEVERE, null, ex);
        }

        return toReturn;
    }

    /**
     * This method generates an annual report given a specified year
     *
     * @param year
     */
    public void generateReport(String year) {

        /**
         * To be added altered
         *
         * try { Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
         *
         * try (Connection conn = (iMuzaMusic.getDB().getConn())) {
         * Map<String, Object> n = new HashMap<String, Object>();
         * n.put("year",year); iMuzaMusic.log("Sending Report Query with Year:
         * "+n.get("year")); JasperPrint print =
         * JasperFillManager.fillReport(getClass()
         * .getResourceAsStream("/ex2design/reports/annualReport.jasper"), n,
         * conn); JFrame frame = new JFrame("iMuzaMusic - Annual Report
         * "+n.get("year")); frame.getContentPane().add(new JRViewer(print));
         * frame.setExtendedState(JFrame.MAXIMIZED_BOTH); frame.pack();
         * frame.setVisible(true); n.clear(); SuccessExport t = new
         * SuccessExport(); iWindow.openWin(t); } catch (SQLException |
         * JRException | NullPointerException e) { e.printStackTrace(); } }
         * catch (ClassNotFoundException e) { e.printStackTrace(); }
        *
         */
    }

    public static ResultSet getAvailableArtistsByAgent(String AgentID) {

        String qry = "SELECT * from Artists where Artists.AgentID=\"" + AgentID + "\" AND Artists.iStatus=1";

        return getDB().query(qry);
    }

    public static ResultSet getLocationsByAgent(String AgentID) {
        String qry = "SELECT Locations.LocationID, Locations.strName, Agents.AgentID\n"
                + "FROM Locations INNER JOIN (Agents INNER JOIN AgentPreferLocation ON Agents.AgentID = AgentPreferLocation.AgentID) ON Locations.LocationID = AgentPreferLocation.LocationID\n"
                + "WHERE (((Agents.AgentID)=[AgentPreferLocation].[AgentID]) AND ((Locations.LocationID)=[AgentPreferLocation].[LocationID]) AND ((Agents.AgentID)=\"" + AgentID + "\"))";

        return iMuzaMusic.getDB().query(qry);
    }

    public static ResultSet getLocationDetails(String LocationID) {
        String qry = "SELECT strAddress from Locations where LocationID=\"" + LocationID + "\"";
        return iMuzaMusic.getDB().query(qry);
    }

}
