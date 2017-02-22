/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Agent;

import Boundary.Agent.frmManageArtists;
import Boundary.Main.iWindow;
import java.sql.ResultSet;
import javax.swing.JComboBox;
import Controller.Main.iMuzaMusic;
import Entity.LRep;
import Entity.Location;
import Entity.OpenLocation;
import Entity.ShowsToArtists;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
/**
 *
 * @author nisan
 */
public abstract class LocationsController {
    
    
    
    public static void updateFormData(JComboBox jcb, JTable jtbl){
        Map<String, Location> locList = new HashMap<String, Location>();
        Map<String, Location> favList = new HashMap<String, Location>();
        Boolean openLoc = false;
        jcb.removeAllItems();
        //Get locations 
        ResultSet rs = iMuzaMusic.getDB().query("SELECT Locations.*, OpenLocations.StageTop, OpenLocations.isExistingSits, OpenLocations.isStageLifted, OpenLocations.isExistingAmp, OpenLocations.isExistingToilets\n" +
"FROM Locations LEFT JOIN OpenLocations ON Locations.LocationID = OpenLocations.iLocation;");
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
                Location add = null;
                if(openLoc){
                    openLoc=false;
                    add = new OpenLocation(StageTop, isExistingSits, isStageLifted, isExistingAmp, isExistingToilets, LocationID, strName, strAddress, strEmail, urlGoogleMap, iPhoneNumber, new LRep(RepID), maxCapacity);
                    
                }else{
                    add = new Location(LocationID, strName, strAddress, strEmail, urlGoogleMap, iPhoneNumber, new LRep(RepID), maxCapacity);
                    
                }
                locList.put(LocationID, add);
                
                
               
            iWindow.update();
                
            }
            
            //Finished adding all locations
            //Get locations already favorited
            rs = iMuzaMusic.getDB().query("SELECT AgentPreferLocation.LocationID\n" +
"FROM AgentPreferLocation\n" +
"WHERE (((AgentPreferLocation.AgentID) In (\""+iMuzaMusic.getLoggedUser().getID()+"\")));");
            
            while(rs.next()){
                Location tmp = locList.get(rs.getString(1));
                iMuzaMusic.log("Removing "+tmp.getStrName()+" from favorited list");
                locList.remove(tmp.getLocationID());
                
                favList.put(tmp.getLocationID(), tmp);
            }
            
            iMuzaMusic.log("Found "+favList.size()+" favorited locations");
            //Add to select box
            Location tmp = new Location("0000");
            tmp.setStrName("Select Location");
            jcb.addItem(tmp);
            for(Location l : locList.values())
                jcb.addItem(l);
            
            Object[][] objs = new Object[favList.size()][3];
            int i = 0;
            for (Location loc : favList.values()) {
                objs[i][0] = loc.getLocationID();
                objs[i][1] = loc;
                objs[i][2] = false;
                i++;
            }
            jtbl.setModel(new javax.swing.table.DefaultTableModel(
                    objs,
                    new String[]{
                        "#", "Location Name", "Unfavorite"
                    }
            ));
TableColumn tc = jtbl.getColumnModel().getColumn(2);
            tc.setCellEditor(jtbl.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(jtbl.getDefaultRenderer(Boolean.class));
            
                
                
            
        } catch (SQLException ex) {
            Logger.getLogger(LocationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    public static void unfavLocation(String LocationID) {
        iMuzaMusic.getDB().updateReturnID("delete from AgentPreferLocation where AgentID=\""+iMuzaMusic.getLoggedUser().getID()+"\" AND LocationID=\""+LocationID+"\"");
    }
    
    public static void addFav(String LocationID){
        iMuzaMusic.getDB().updateReturnID("INSERT INTO AgentPreferLocation (AgentID, LocationID) values (\""+iMuzaMusic.getLoggedUser().getID()+"\",\""+LocationID+"\")");
    }
}

   
