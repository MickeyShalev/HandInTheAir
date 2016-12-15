/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import entities.*;
import ex2design.utilities.EArtistStatus;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import ex2design.utilities.EAuth;
import gui.internal.frmManageArtists;

/**
 *
 * @author Administrator
 */
public class iMuzaMusic {
    static DBManager DB;
    static Person loggedUser = null;
    
    public iMuzaMusic(){
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
    public void init(){
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
     **/
    public static void log(String str){
        System.err.println(str);
    }
  
    
    
    public static boolean logIn(String id, String pass) throws SQLException{
            ResultSet tmp = null;
            Person tmpPerson = null;
            log("Attempting login using "+id+"/"+pass);
            /** TO BE DELETED 
            id = id.replace("Cust","").
                            replace("RE", "").
                                replace("AR", "").
                                    replace("AAG", "").
                                        replace("LR", "");
            **/

            tmp = iMuzaMusic.DB.query("select * from Customers where ClientID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                log("test");
                if(tmp.getString(1).length()>0){
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
            
            
            
            tmp = iMuzaMusic.DB.query("select * from Agents where AgentID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                if(tmp.getString(1).length()>0){
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
            
            
            
            tmp = iMuzaMusic.DB.query("select * from Artists where ArtistID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                if(tmp.getString(1).length()>0){
                    //Logged in as agent
                    String ID = tmp.getString("ArtistID");
                    String strFirstName = tmp.getString("strStageName");
                    String strLastName = "";
                    String bio = tmp.getString("strShortBio");
                    String stageName = tmp.getString("strStageName");
                    String arStatus = tmp.getString("iStatus");
                    System.err.println("status: "+arStatus);
                    Person p = new Artist(ID, strFirstName, strLastName, pass, EAuth.Artist, bio, stageName, bio, EArtistStatus.Active, "");
                    loggedUser = p;
                    log("Artist logged in");
                    return true;
                }
                
                
                
            }
            tmp = iMuzaMusic.DB.query("select * from LReps where LRepID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                if(tmp.getString(1).length()>0){
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
            
            tmp = iMuzaMusic.DB.query("select * from Reps where RepID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                if(tmp.getString(1).length()>0){
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
    
    
    public static String getID(String str){
        
        try{
        String tmp[] = str.split("\\(");
        tmp = tmp[1].split("\\)");
        str = tmp[0];
        }
        catch(Exception e){
            iMuzaMusic.log(""+e.getStackTrace());
            e.printStackTrace();
        }
        return str;
    }
    
    /**
     * This method returns a new artist entity from a given artist id
     * @param ArtistID
     * @return 
     */
    public static Artist getAgentEntity(String ArtistID){
            Artist toReturn = null;
            
            ResultSet getAgent = iMuzaMusic.getDB().query("select * from Artists where ArtistID=\""+ArtistID+"\"");
            try {
                while(getAgent.next()){
                     String ID = getAgent.getString("ArtistID");
                    String strFirstName = getAgent.getString("strStageName");
                    String strLastName = "";
                    String pass = getAgent.getString("strPasswd");
                    String bio = getAgent.getString("strShortBio");
                    String stageName = getAgent.getString("strStageName");
                    String emailAddr = getAgent.getString("strEmailAddr");
                    String fbAddr = getAgent.getString("strFacebook");
                    /**
                     *  EArtist Status
                    ***/
                    EArtistStatus arStatus = null;
                    switch(getAgent.getString("iStatus")){
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
    
    
    
}
    
