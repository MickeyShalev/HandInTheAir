/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Rep;

import Controller.Main.iMuzaMusic;
import Entity.EAuth;
import Entity.Rep;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        switch(e){
            case Customer:
                prefix = "Cust";
                tblName = "Customers";
                clmName = "ClientID";
            case Artist:
                prefix = "AR";
                tblName = "Artists";
                clmName = "ArtistID";
            case Location_Representative:
                prefix = "LR";
                tblName = "LReps";
                clmName = "LRepID";
            case Agent:
                prefix = "AG";
                tblName = "Agents";
                clmName = "AgentID";
            case Representative:
                prefix = "RE";
                tblName = "Reps";
                clmName = "RepID";

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
}
