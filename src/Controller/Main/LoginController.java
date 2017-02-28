/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Main;

import Boundary.Main.LoginGui;
import Entity.Person;
import java.lang.invoke.MethodHandles;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nisan
 */
public abstract class LoginController {
    static LoginGui loginGUI = null;
    public static List<Person> getPersons(){
        List<Person> l = new ArrayList<Person>();
        l.add(new Person("0000", "", "Select User", ""));
        //Get agents
        try{
            l.add(new Person("0000", "", "------------Agents------------", ""));
            String qry = "select * from Agents";
            ResultSet rs = iMuzaMusic.getDB().query(qry);
            while(rs.next()){
                Person toAdd = new Person(rs.getString(1), rs.getString("strPasswd"), rs.getString(2), rs.getString(3));
                l.add(toAdd);
            }
            
            l.add(new Person("0000", "", "------------Customers------------", ""));
            qry = "select * from Customers";
            rs = iMuzaMusic.getDB().query(qry);
            while(rs.next()){
                Person toAdd = new Person(rs.getString(1), rs.getString("strPasswd"), rs.getString(2), rs.getString(3));
                l.add(toAdd);
            }
            l.add(new Person("0000", "", "------------Representatives------------", ""));
            qry = "select * from Reps";
            rs = iMuzaMusic.getDB().query(qry);
            while(rs.next()){
                Person toAdd = new Person(rs.getString(1), rs.getString("strPasswd"), rs.getString(3), rs.getString(4));
                l.add(toAdd);
            }
            l.add(new Person("0000", "", "------------Location Representative------------", ""));
            qry = "select * from LReps";
            rs = iMuzaMusic.getDB().query(qry);
            while(rs.next()){
                Person toAdd = new Person(rs.getString(1), rs.getString("strPasswd"), rs.getString(2), rs.getString(3));
                l.add(toAdd);
            }
            
            
           
        }

        catch(SQLException e){
        }

        return l;
    }
    
     public static void setLoginGui(LoginGui lg){
            loginGUI = lg;
        }
     
     
     public static void logIN(String id, String pw){
        try {
            loginGUI.submitForm(id, pw);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
}
