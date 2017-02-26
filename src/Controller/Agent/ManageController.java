/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Agent;

import Boundary.Agent.frmManageArtists;
import Controller.Main.FileManager;
import Controller.Main.iMuzaMusic;
import Entity.Agent;
import Entity.Artist;
import Entity.EAuth;
import Entity.ShowsToArtists;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public abstract class ManageController {
    public static List<Artist> getArtistsByAgent(Agent agent){
        List<Artist> arr = new ArrayList<Artist>();
        
         ResultSet r = iMuzaMusic.getDB().query("select * from Artists where AgentID='" + agent.getID() + "'");
        try {
            while (r.next()) {
               
                arr.add(GeneralController.generateArtistEntity(r));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ManageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public static void updateShowAttendance(String ArtistID, String ShowID, Integer status){
        String qry = "update ShowsToArtists set Status="+status+" where ShowID=\""+ShowID+"\" AND ArtistID=\""+ArtistID+"\"";
        iMuzaMusic.getDB().updateReturnID(qry);
    }
    
    public static void updateAttendanceTable(JTable jTable1, Artist art){
        
            List<ShowsToArtists> AL = new ArrayList<ShowsToArtists>();
            String ShowID, ArtistID, showDate, showLocation;
            ResultSet shows = iMuzaMusic.getDB().query("SELECT Shows.*, Locations.strName\n"
                    + "FROM Locations INNER JOIN (Shows INNER JOIN ShowsToArtists ON Shows.pID = ShowsToArtists.ShowID) ON Locations.LocationID = Shows.iLocation\n"
                    + "WHERE (((Shows.pID)=[ShowsToArtists].[ShowID]) AND ((ShowsToArtists.Status) = 1) AND ((ShowsToArtists.ArtistID)=\"" + art.getID() + "\") AND ((Locations.LocationID)=[Shows].[iLocation]));");
            try {
                while (shows.next()) {
                    ShowID = shows.getString("pID");
                    ArtistID = shows.getString("iMainArtist");
                    ResultSet getArtistName = iMuzaMusic.getDB().query("select strStageName from Artists where ArtistiD=\"" + ArtistID + "\"");
                    while (getArtistName.next()) {
                        ArtistID = getArtistName.getString("strStageName");
                    }
                    showLocation = shows.getString("strName");
                    showDate = shows.getString("pStartDate").substring(0, 10);
                    iMuzaMusic.log(ShowID + " " + ArtistID + " " + showLocation + " " + showDate);
                    AL.add(new ShowsToArtists(ShowID, ArtistID, "Pending Approval", showDate, showLocation));

                }
            } catch (SQLException ex) {
                Logger.getLogger(frmManageArtists.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Object[][] objs = new Object[AL.size()][6];
            int i = 0;
            for (ShowsToArtists sta : AL) {
                objs[i][0] = sta.getShowID();
                objs[i][1] = sta.getShowDate();
                objs[i][2] = sta.getArtistID();
                objs[i][3] = sta.getLocation();
                objs[i][4] = false;
                objs[i][5] = false;
                i++;
            }
            jTable1.setModel(new javax.swing.table.DefaultTableModel(
                    objs,
                    new String[]{
                        "#", "Date", "Main Artist", "Location", "Approve Show", "Cancel Attendance"
                    }
            ));
TableColumn tc = jTable1.getColumnModel().getColumn(4);
            tc.setCellEditor(jTable1.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(jTable1.getDefaultRenderer(Boolean.class));
            
    }

    public static String getNewArtistID() {
        String qry = "select TOP 1 ArtistID from Artists order by ArtistID Desc";
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
        recent = "AR"+(Integer.parseInt(recent)+1);
        iMuzaMusic.log("Returning next ID for Artist - "+recent);
        
        return recent;
    }

    public static void createArtist(Artist a) {
        iMuzaMusic.log("Attempting to add Artist "+a.getStageName()+" to DB");
        String qry = "INSERT INTO Artists VALUES (\""+a.getID()+"\",\""+a.getStageName()+"\",\""+a.getBiography()+"\", \""+a.getEmailAddr()+"\",\""+a.getFbAddr()+"\",\""+iMuzaMusic.getLoggedUser().getID()+"\",1, \""+a.getSig()+"\")";
        iMuzaMusic.getDB().updateReturnID(qry);
        
    }
        
}
