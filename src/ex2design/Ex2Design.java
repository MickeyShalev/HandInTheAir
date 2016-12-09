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
public class Ex2Design {
    DBManager DB;
    
    public Ex2Design(){
        init();   
        
           
        try {
            ResultSet test = DB.query("select FirstName, LastName from Agents");
            String results="";
            while(test.next()){
                String firstName = test.getString("FirstName");
                String lastName = test.getString("LastName");
                System.err.println(firstName+" "+lastName);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Ex2Design.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   
 
    }
    
    /**
     * Initiates a DB Connection
     */
    public void init(){
        try {
            DB = new DBManager("C:\\Users\\Administrator\\Documents\\NetBeansProjects\\ex2Design\\src\\ex2design\\DB.accdb");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Ex2Design.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Ex2Design.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
    
}
    
