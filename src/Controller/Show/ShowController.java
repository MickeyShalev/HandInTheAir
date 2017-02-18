/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Show;

import Controller.General.DBManager;
import Controller.General.iMuzaMusic;
import Entity.Agent;
import Entity.Artist;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Entity.Location;
/**
 *
 * @author Administrator
 */
public abstract class ShowController {

    DBManager DB = iMuzaMusic.getDB();

    public Date[] getOccupiedDates(Artist artist) {
        List<Date> arr = new ArrayList<Date>();

        //Get show dates
        ResultSet rs = DB.query("SELECT Shows.pStartDate, Shows.iMainArtist\n"
                + "FROM Shows\n"
                + "WHERE (((Shows.iMainArtist) In (\"" + artist.getID() + "\")));");
        try {
            while (rs.next()) {
                arr.add(rs.getDate(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get from sub shows
        rs = DB.query("SELECT Shows.pStartDate, ShowsToArtists.ShowID, ShowsToArtists.ArtistID\n"
                + "FROM Shows INNER JOIN ShowsToArtists ON Shows.pID = ShowsToArtists.ShowID\n"
                + "WHERE (((ShowsToArtists.ShowID)=[Shows].[pID]) AND ((ShowsToArtists.ArtistID) In (\"" + artist.getID() + "\")));");
        try {
            while (rs.next()) {
                arr.add(rs.getDate(1));
            }

        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Get from XML
        //TBD
        return (Date[]) arr.toArray();
    }

    public static List<Artist> getArtistsByAgent(Agent agent) {
        List<Artist> arr = new ArrayList<Artist>();

        String qry = "SELECT * from Artists where Artists.AgentID=\"" + agent.getID() + "\" AND Artists.iStatus=1";
        ResultSet rs = iMuzaMusic.getDB().query(qry);

        try {
            while (rs.next()) {
                Artist a = new Artist(rs.getString(1));
                a.setStageName(rs.getString(2));
                arr.add(a);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return arr;
    }
    
    public static List<Location> getFavLocationsByAgent(Agent agent){
        List<Location> arr = new ArrayList<Location>();
           String qry = "SELECT Locations.LocationID, Locations.strName, Agents.AgentID\n"
                + "FROM Locations INNER JOIN (Agents INNER JOIN AgentPreferLocation ON Agents.AgentID = AgentPreferLocation.AgentID) ON Locations.LocationID = AgentPreferLocation.LocationID\n"
                + "WHERE (((Agents.AgentID)=[AgentPreferLocation].[AgentID]) AND ((Locations.LocationID)=[AgentPreferLocation].[LocationID]) AND ((Agents.AgentID)=\"" + agent.getID()+ "\"))";

       ResultSet rs = iMuzaMusic.getDB().query(qry);
        try {
            while(rs.next()){
                Location loc = new Location(rs.getString(1), rs.getString(2), rs.getString(3));
                
                arr.add(loc);
                
                
            }} catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return arr;
        
    }
    
    /**
     * Returns appreciated artists of a given artist
     * @param artist
     * @return 
     */
    public static List<Artist> getSubArtistsForShow(Artist artist){
        List<Artist> arr = new ArrayList<Artist>();
        
        String qry = "SELECT ArtistLikes.LikeID, Artists.strStageName\n" +
"FROM ArtistLikes, Artists\n" +
"WHERE (((ArtistLikes.ArtistID) In (\""+artist.getID()+"\")) AND ((Artists.iStatus)=\"1\") AND ((Artists.ArtistID)=[ArtistLikes].[LikeID]))\n" +
"ORDER BY Artists.strStageName;";
        ResultSet r = iMuzaMusic.getDB().query(qry);
         try {
             while(r.next()){
                 Artist tmp = new Artist(r.getString(1));
                 tmp.setStageName(r.getString(2));
                 tmp.setBiography(r.getString(3));
                 arr.add(tmp);
             }} catch (SQLException ex) {
             Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
         }
         return arr;
    }
    
}
