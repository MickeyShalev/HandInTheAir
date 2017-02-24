/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.LRep;

import Controller.Agent.ManageController;
import Controller.Main.iMuzaMusic;
import Entity.LRep;
import Entity.Location;
import Entity.OpenLocation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author nisan
 */
public abstract class LocationManager {
    public static Location getLocation(){
        Location loc = null;
        Boolean openLoc = false;
        ResultSet rs = iMuzaMusic.getDB().query("SELECT Locations.*, OpenLocations.StageTop, OpenLocations.isExistingSits, OpenLocations.isStageLifted, OpenLocations.isExistingAmp, OpenLocations.isExistingToilets\n" +
"FROM Locations INNER JOIN OpenLocations ON Locations.LocationID = OpenLocations.iLocation\n" +
"WHERE (((Locations.RepID) In (\""+iMuzaMusic.getLoggedUser().getID()+"\")));");
        iMuzaMusic.log("Aquiring location details for LRep "+iMuzaMusic.getLoggedUser().getID());
        try {
            while(rs.next()){
                    String StageTop = null;
                    Integer isExistingSits = null, isStageLifted = null, isExistingAmp = null, isExistingToilets = null;
                    //regular location
                    String LocationID = rs.getString(1);
                    String strName = rs.getString(2);
                    String strAddress = rs.getString(3);
                    String strEmail = rs.getString(4);
                    String urlGoogleMap = rs.getString(5);
                    String iPhoneNumber = rs.getString(6);
                    Integer maxCapacity = rs.getInt(7);
                    String RepID = rs.getString(8);
                    
                if(rs.getObject("isExistingToilets")!=null) {
                    //open location
                     StageTop = rs.getString(9);
                     isExistingSits = rs.getInt(10);
                     isStageLifted = rs.getInt(11);
                     isExistingAmp = rs.getInt(12);
                     isExistingToilets = rs.getInt(13);
                    openLoc = true;
                }
                if(openLoc){
                    iMuzaMusic.log("Found open location "+strName);
                    loc = new OpenLocation(StageTop, isExistingSits, isStageLifted, isExistingAmp, isExistingToilets, LocationID, strName, strAddress, strEmail, urlGoogleMap, iPhoneNumber, new LRep(RepID), maxCapacity);
                    
                }else{
                    iMuzaMusic.log("Found regular location "+strName);
                    loc = new Location(LocationID, strName, strAddress, strEmail, urlGoogleMap, iPhoneNumber, new LRep(RepID), maxCapacity);
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(LocationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        
        
        
        
        return loc;
    }

    public static String getNextIndex() {
         String qry = "select TOP 1 LocationID from Locations order by LocationID Desc";
        ResultSet rs = iMuzaMusic.getDB().query(qry);
        String recent = "";
        try {
            if(rs.next()){
                recent = rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        recent = recent.substring(2, recent.length());
 
        recent = String.format("%03d",Integer.parseInt(recent)+1);
        
        recent = "LC"+recent;
        iMuzaMusic.log("Returning next ID for Location - "+recent);
        
        return recent;
    }

    public static void update(Location toAdd) {
        
        //Test if there is a location exists in the system
        String qry = "select count(*) from Locations where LocationID in (\""+toAdd.getLocationID()+"\")";
        ResultSet rs = iMuzaMusic.getDB().query(qry);
        Integer num = 0;
        try {
            if(rs.next()){
                num = rs.getInt(1);
            }
            //TO CONTINUE
        } catch (SQLException ex) {
            Logger.getLogger(LocationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(num==0){
            //Create new one!
            qry = "INSERT INTO Locations(LocationID, strName, strAddress, strEmail, urlGoogleMap, iPhoneNumber, iMaxCapacity, RepID)\n "
                    + "VALUES (\""+toAdd.getLocationID()+"\",\""+toAdd.getStrAddress()+"\",\""+toAdd.getStrEmail()+"\", \""+toAdd.getUrlGoogleMaps()+"\", \""+toAdd.getiPhoneNum()+"\", "+toAdd.getMaxCapacity()+", \""+toAdd.getOwner().getID()+"\")";
            iMuzaMusic.log("Inserting new location: "+toAdd.getLocationID());
            iMuzaMusic.log(qry);
            iMuzaMusic.getDB().updateReturnID(qry);
            return;
        }
        
        //Update location details
        qry = "UPDATE Locations SET strName=\""+toAdd.getStrName()+"\", strAddress=\""+toAdd.getStrAddress()+"\", strEmail=\""+toAdd.getStrEmail()+"\", urlGoogleMap=\""+toAdd.getUrlGoogleMaps()+"\", iPhoneNumber="+toAdd.getiPhoneNum()+", iMaxCapacity=\""+toAdd.getMaxCapacity()+"\" WHERE LocationID=\""+toAdd.getLocationID()+"\"";
        iMuzaMusic.log("Updating existing location: "+toAdd.getLocationID());
        iMuzaMusic.getDB().updateReturnID(qry);
        
        //Test if open location
        Boolean isOpenInDB = false;
        qry = "SELECT count(*) from OpenLocations where LocationID in(\""+toAdd.getLocationID()+"\")";
        rs = iMuzaMusic.getDB().query(qry);
        
        if(toAdd instanceof OpenLocation){
            //is open location
            //check if was already opened
            
        }
        
    }
}
