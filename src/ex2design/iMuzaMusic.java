/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import entities.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    /**
     * Initiates a DB Connection
     */
    public void init(){
        try {
            log("Attempting connection to MS Access DB");
            DB = new DBManager("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\ex2Design\\src\\ex2design\\DB.accdb");
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
  
    
    
    public static void logIn(String id, String pass) throws SQLException{
            ResultSet tmp = null;
            
            log("Attempting login using "+id+"/"+pass);
            
            
            tmp = iMuzaMusic.DB.query("select * from Customers where ClientID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                if(tmp.getString(1).length()>0){
                    //Logged in as customer
                    String ID = tmp.getString("ClientID");
                    String strFirstName = tmp.getString("strFirstName");
                    String strLastName = tmp.getString("strLastName");
                    Person p = new Customer(ID, strFirstName, strLastName, pass);
                    loggedUser = p;
                    log("Customer logged in");
                }
            }
            
            tmp = iMuzaMusic.DB.query("select * from Agents where AgentID=\""+id+"\" AND strPasswd=\""+pass+"\"");
            if(tmp.next()){
                if(tmp.getString(1).length()>0){
                    //Logged in as agent
                    String ID = tmp.getString("AgentID");
                    String strFirstName = tmp.getString("FirstName");
                    String strLastName = tmp.getString("LastName");
                    Person p = new Agent(ID, strFirstName, strLastName, pass);
                    loggedUser = p;
                    log("Agent logged in");
                }
                
                
                
            }
            
            if(loggedUser!=null){
                //Activate the GUI
                log("Activating GUI");
            }
            
                
    } 
    
    
    
}
    
