/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.General;

import Controller.Main.iMuzaMusic;
import Entity.Customer;
import Entity.LRep;
import Entity.Person;
import Entity.Show;
import Entity.ShowsToArtists;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author Administrator
 */
public abstract class ViewShowController {
    public static void fillShowTable(JTable jtbl, JLabel err){
        ResultSet rs = null;
        List<Show> AL = null;
        
        try {
            rs = getShowResult(iMuzaMusic.getLoggedUser());
            if(!rs.next()){
                err.setVisible(true);
                jtbl.setVisible(false);
            } 
            rs.previous();
            
        } catch (SQLException ex) {
            Logger.getLogger(ViewShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        AL = new ArrayList<Show>();
        try {
            while(rs.next()){
                System.err.println("TEST");
                Show s = new Show(rs.getString("pID"), rs.getString("pStatus"), rs.getString("strName"), rs.getString("strStageName"), rs.getString("strAddress"), rs.getString("urlGoogleMap"), rs.getDate("pStartDate"), rs.getDate("pDateCreated"), rs.getDouble("pTicketPrice"),rs.getInt("pMinAge"), rs.getInt("iMaxCapacity"), rs.getInt("SumOfNumberofTickets"));
                iMuzaMusic.log("Added show to ShowList ("+s.getpID()+" "+s.getiMainArtist()+")");
                AL.add(s);
            }
      
        
        //There are shows to display
        Object[][] objs = new Object[AL.size()][6];
            int i = 0;
            for (Show s : AL) {
                objs[i][0] = s;
                objs[i][1] = s.getpStatus();
                objs[i][2] = s.getiMainArtist();
                objs[i][3] = s.getNumPurchased()+"/"+s.getMaxCapacity();
                objs[i][4] = false;
                objs[i][5] = false;
                i++;
            }
            jtbl.setModel(new javax.swing.table.DefaultTableModel(
                    objs,
                    new String[]{
                        "#", "Date", "Main Artist", "Location", "Approve Show", "Cancel Attendance"
                    }
            ));
TableColumn tc = jtbl.getColumnModel().getColumn(4);
            tc.setCellEditor(jtbl.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(jtbl.getDefaultRenderer(Boolean.class));
        
          } catch (SQLException ex) {
            Logger.getLogger(ViewShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static ResultSet getShowResult(Person person){
        String qry = "";
        if(person instanceof Customer){
        qry = "SELECT TOP 10 Shows.*, Artists.strStageName, Locations.strName, Locations.strAddress, Locations.urlGoogleMap, Locations.iMaxCapacity, qryShowTicketPurchases.SumOfNumberofTickets\n" +
"FROM qryShowTicketPurchases INNER JOIN (Locations INNER JOIN (Artists INNER JOIN Shows ON Artists.ArtistID = Shows.iMainArtist) ON Locations.LocationID = Shows.iLocation) ON qryShowTicketPurchases.PerformenceID = Shows.pID\n" +
"WHERE (((Shows.[pStatus]) In (\"Approved\")))\n" +
"ORDER BY Shows.pStartDate;";
        }else if(person instanceof LRep){
            qry = "SELECT TOP 10 Shows.*, Artists.strStageName, Locations.strName, Locations.strAddress, Locations.urlGoogleMap, Locations.iMaxCapacity, qryShowTicketPurchases.SumOfNumberofTickets\n" +
"FROM Locations INNER JOIN (Artists INNER JOIN (qryShowTicketPurchases INNER JOIN Shows ON qryShowTicketPurchases.PerformenceID = Shows.pID) ON Artists.ArtistID = Shows.iMainArtist) ON Locations.LocationID = Shows.iLocation\n" +
"WHERE (((Shows.pStatus) Not In (\"Cancelled\")) AND ((Locations.RepID) In (\""+person.getID()+"\"))) order by Shows.pStartDate";
        }
        return iMuzaMusic.getDB().query(qry);
    }
    
    
}
