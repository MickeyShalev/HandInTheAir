/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Rep;

import Controller.Main.iMuzaMusic;
import Entity.Agent;
import Entity.Customer;
import Entity.EAuth;
import Entity.Rep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import Controller.Main.FileManager;

/**
 *
 * @author Administrator
 */
public abstract class createManager {
    public static String getNextID(EAuth e){
        String toReturn = "";
        String prefix = "";
        String tblName = "";
        String clmName = "";
        iMuzaMusic.log("Getting Next ID for "+e.toString());
        
        switch(e){
            case Customer:
                prefix = "CU";
                tblName = "Customers";
                clmName = "ClientID";
                break;
            case Artist:
                prefix = "AR";
                tblName = "Artists";
                clmName = "ArtistID";
                break;
            case Location_Representative:
                prefix = "LR";
                tblName = "LReps";
                clmName = "LRepID";
                break;
            case Agent:
                prefix = "AG";
                tblName = "Agents";
                clmName = "AgentID";
                break;
            case Representative:
                prefix = "RE";
                tblName = "Reps";
                clmName = "RepID";
                break;

        }
        
        //Get last user ID
        String qry = "select top 1 "+clmName+" from "+tblName+" order by "+clmName+" desc";
        ResultSet rs = iMuzaMusic.getDB().query(qry);
        String tmpID = "";
        try {
            while(rs.next()){
                tmpID = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(createManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        iMuzaMusic.log("Current last ID is: "+tmpID);
        tmpID = tmpID.substring(prefix.length(), tmpID.length());
 
        tmpID = String.format("%03d",Integer.parseInt(tmpID)+1);
        
        toReturn = prefix+tmpID;
        iMuzaMusic.log("Returning next ID for "+e.name().toString()+" - "+toReturn);
        
        
        
        
        
        
        
        return toReturn;
    }

    public static void create(Rep rep) {
        String qry = "INSERT INTO Reps (RepID, stRPasswd, FirstName, LastName) values (\""+rep.getID()+"\", \""+rep.getPassword()+"\",\""+rep.getFirstName()+"\",\""+rep.getLastName()+"\")";
        iMuzaMusic.getDB().updateReturnID(qry);
    }
    
    public static void create(Agent ag) {
        Timestamp ts = new Timestamp(ag.getBirthDate().getTime());

        String qry = "INSERT INTO Agents (AgentID, FirstName, LastName, strEmail, BirthDate, PhoneNumber, strPasswd) VALUES (\""+ag.getID()+"\",\""+ag.getFirstName()+"\",\""+ag.getLastName()+"\",\""+ag.getEmail()+"\",\""+ts+"\",\""+ag.getPhoneNumber()+"\",\""+ag.getPassword()+"\")";
        iMuzaMusic.getDB().updateReturnID(qry);
    }
    
    public static void create(Customer cust){
        Timestamp ts = new Timestamp(cust.getBirthDate().getTime());
        String avatar = FileManager.toBase64(cust.getAvatar());
        String qry = "INSERT INTO Customers (ClientID, strFirstName, strLastName, strNickname, strEmail, BirthDate, strPasswd, avatar) VALUES (\""+cust.getID()+"\",\""+cust.getFirstName()+"\",\""+cust.getLastName()+"\",\""+cust.getNickName()+"\",\""+cust.getEmail()+"\",\""+ts+"\",\""+cust.getPassword()+"\",\""+avatar+"\")";
        iMuzaMusic.getDB().updateReturnID(qry);
        
        
    }
}
