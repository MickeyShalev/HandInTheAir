/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Agent;

import Controller.Main.DBManager;
import Controller.Main.XMLManager;
import Controller.Main.iMuzaMusic;
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
import java.sql.Timestamp;
import java.util.Map;

/**
 *
 * @author Administrator
 */
public abstract class ShowController {

    DBManager DB = iMuzaMusic.getDB();

    public static Date[] getOccupiedDates(Artist artist) {
        List<Date> arr = new ArrayList<Date>();

        //Get show dates
        ResultSet rs = iMuzaMusic.getDB().query("SELECT Shows.pStartDate, Shows.iMainArtist\n"
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
        rs = iMuzaMusic.getDB().query("SELECT Shows.pStartDate, ShowsToArtists.ShowID, ShowsToArtists.ArtistID\n"
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
        Map<Artist, List<Timestamp>> hm = XMLManager.importXML();
        if (hm.containsKey(artist)) {
            for (Timestamp ts : hm.get(artist)) {
                iMuzaMusic.log("Adding session date occupied: " + ts);
                arr.add(new Date(ts.getTime()));
            }
        }
        //TBD
        Date darr[] = new Date[arr.size()];
        arr.toArray(darr);
        return darr;
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

    public static List<Location> getFavLocationsByAgent(Agent agent) {
        List<Location> arr = new ArrayList<Location>();
        String qry = "SELECT Locations.LocationID, Locations.strName, Agents.AgentID\n"
                + "FROM Locations INNER JOIN (Agents INNER JOIN AgentPreferLocation ON Agents.AgentID = AgentPreferLocation.AgentID) ON Locations.LocationID = AgentPreferLocation.LocationID\n"
                + "WHERE (((Agents.AgentID)=[AgentPreferLocation].[AgentID]) AND ((Locations.LocationID)=[AgentPreferLocation].[LocationID]) AND ((Agents.AgentID)=\"" + agent.getID() + "\"))";

        ResultSet rs = iMuzaMusic.getDB().query(qry);
        try {
            while (rs.next()) {
                Location loc = new Location(rs.getString(1), rs.getString(2), rs.getString(3));

                arr.add(loc);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arr;

    }

    /**
     * Returns appreciated artists of a given artist
     *
     * @param artist
     * @return
     */
    public static List<Artist> getSubArtistsForShow(Artist artist) {
        List<Artist> arr = new ArrayList<Artist>();

        String qry = "SELECT ArtistLikes.LikeID, Artists.strStageName, Artists.strShortBio\n"
                + "FROM ArtistLikes, Artists\n"
                + "WHERE (((ArtistLikes.ArtistID) In (\"" + artist.getID() + "\")) AND ((Artists.iStatus)=\"1\") AND ((Artists.ArtistID)=[ArtistLikes].[LikeID]))\n"
                + "ORDER BY Artists.strStageName;";
        ResultSet r = iMuzaMusic.getDB().query(qry);
        try {
            while (r.next()) {
                Artist tmp = new Artist(r.getString(1));
                tmp.setStageName(r.getString(2));
                tmp.setBiography(r.getString(3));
                arr.add(tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
    }

    public static void CreateShow(Timestamp ts, String ticketPrice, String minAge, String LocationID, String MainArtist, Timestamp dateCreated, List<Artist> subArtists) {
        //Generate Show Key
        ResultSet rs = iMuzaMusic.getDB().query("Select TOP 1 pID from Shows order by pID desc");
        String pID = "";
        Integer tmp = 0;
        try {
            while (rs.next()) {
                pID = rs.getString("pID");
                pID = pID.replace("PF", "");
                tmp = Integer.parseInt(pID);
                tmp++;
                pID = "PF" + String.format("%03d", tmp);
            }

            String qry = "INSERT INTO Shows (pID, pStartDate, pTicketPrice, pMinAge, pStatus, iLocation, iMainArtist, pDateCreated)"
                    + " VALUES ('" + pID + "',\"" + ts + "\",'" + ticketPrice + "','" + minAge + "','Awaiting Approval','" + LocationID + "','" + MainArtist + "',\"" + dateCreated + "\")";
            iMuzaMusic.log("Starting to insert show " + pID + " with " + subArtists.size() + " sub artists");
            iMuzaMusic.getDB().updateReturnID(qry);
            for (Artist subArtist : subArtists) {
                //Test if artist is managed by the agent created the session
                Integer toApprove = 1;

                qry = "SELECT count(*) from Artists where ArtistID=\"" + subArtist.getID() + "\" AND AgentID=\"" + iMuzaMusic.getLoggedUser().getID() + "\"";
                rs = iMuzaMusic.getDB().query(qry);
                if (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        toApprove = 2;
                    }
                }

                qry = "INSERT INTO ShowsToArtists(ShowID, ArtistID, Status) values (\"" + pID + "\",\"" + subArtist.getID() + "\", " + toApprove + ")";
                iMuzaMusic.getDB().updateReturnID(qry);
                System.err.println("Added show to artist: " + qry);
            }

            updateShowStatus(pID);

        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update show status
     *
     * @param pID
     */
    public static void updateShowStatus(String pID) {
        iMuzaMusic.log("Updating show status: " + pID);
        String pStatus = "";
        Integer numNotApproved = 0;
        Boolean didCancel = false;
        Date pStartDate = null;
        String qry = "SELECT pStatus, pStartDate from Shows where pID in(\"" + pID + "\")";
        ResultSet rs = iMuzaMusic.getDB().query(qry);
        try {
            if (rs.next()) {
                pStatus = rs.getString(1);
                pStartDate = rs.getDate(2);

                if (pStatus.length() == 0 || pStatus.equals("Cancelled") || pStatus.equals("Approved"))//error or cancelled already
                {
                    iMuzaMusic.log("Show is already approved or cancelled.");
                    return;
                }

                iMuzaMusic.log("Show date: " + pStartDate);
                iMuzaMusic.log("Show Status: "+ pStatus);
                //Check if there is an artist who cancelled the attendance
                qry = "SELECT COUNT(ArtistID) from ShowsToArtists where ShowID in(\""+pID+"\") AND Status=3";
                rs = iMuzaMusic.getDB().query(qry);
                if(rs.next()){
                    if(rs.getInt(1)>0){
                        //An artist cancelled his attendance
                        //Update show with cancelled!
                        iMuzaMusic.log("Cancelling show because we found an artist who cancelled.");
                        qry = "UPDATE Shows set pStatus=\"Cancelled\" where pID in (\""+pID+"\")";
                        iMuzaMusic.getDB().updateReturnID(qry);
                        return;
                    }
                }
                //Get number of artists who did not approve show yet
                qry = "SELECT COUNT(ArtistID) from ShowsToArtists where ShowID in(\"" + pID + "\") AND Status=1";
                rs = iMuzaMusic.getDB().query(qry);
                if (rs.next()) {
                    numNotApproved = rs.getInt(1);
                }
                if(numNotApproved>0){
                    iMuzaMusic.log("Artists who did not approve show yet: "+numNotApproved);
                } 
                
                if(numNotApproved>0){
                //Show is not approved yet, now check if its been more than a week since it was created!
                    qry = "SELECT Shows.pID, DateDiff(\"d\",[pDateCreated], Now()) AS Diff\n" +
"FROM Shows WHERE pID in(\""+pID+"\")";
                    rs = iMuzaMusic.getDB().query(qry);
                    if(rs.next()){
                        if(rs.getInt(2)>=7){
                            //Cancel show! its been too long.
                            iMuzaMusic.log("Cancelling show because its been more than a week for approval!");
                             qry = "UPDATE Shows set pStatus=\"Cancelled\" where pID in (\""+pID+"\")";
                            iMuzaMusic.getDB().updateReturnID(qry);
                            return;
                        }
                    }
                } else{
                
                //Show can be approved!
                    iMuzaMusic.log("Approving show!");
                    qry = "UPDATE Shows set pStatus=\"Approved\" where pID in (\""+pID+"\")";
                    iMuzaMusic.getDB().updateReturnID(qry);
                    
                }
                
                
                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(ShowController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
