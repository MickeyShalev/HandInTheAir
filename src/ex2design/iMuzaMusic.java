/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class iMuzaMusic {
    static DBManager DB;
    
    
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
  
    
    
    public static void logIn(String email, String pass) throws SQLException{
       int id=0;
            System.err.println("Attempting login using "+email+"/"+pass);
            ResultSet result = iMuzaMusic.DB.query("select count(*) as flag from Customers where strEmail=\""+email+"\" AND strPasswd=\""+pass+"\"");
            if(result.next())
                id=result.getInt(1);
        
            if(id>0) 
                System.err.println("Successfully connected!");
            else
                System.err.println("Email and password does not match customer db!");
    
        
        
    } 
    
}
    
