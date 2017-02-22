/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.General;

import Boundary.Main.iWindow;
import Controller.Main.iMuzaMusic;
import Entity.Customer;
import Entity.LRep;
import Entity.Person;
import Entity.Show;
import Entity.ShowsToArtists;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
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
                
                Show s = new Show(rs.getString("pID"), rs.getString("pStatus"), rs.getString("strName"), rs.getString("strStageName"), rs.getString("strAddress"), rs.getString("urlGoogleMap"), rs.getDate("pStartDate"), rs.getDate("pDateCreated"), rs.getDouble("pTicketPrice"),rs.getInt("pMinAge"), rs.getInt("iMaxCapacity"), rs.getInt("SumOfNumberofTickets"));
                
                if(s.getpStartDate().before(new Date())){
                    iMuzaMusic.log("Show Date: "+s.getpStartDate()+" now: "+new Date());
                    continue;
                }
                iMuzaMusic.log("Added show to ShowList ("+s.getpID()+" "+s.getiMainArtist()+")");
                AL.add(s);
            }
      
        
        //There are shows to display
        jtbl.getColumnModel().getColumn(1).setMinWidth(50);
        jtbl.getColumnModel().getColumn(1).setPreferredWidth(50);
        jtbl.doLayout();
        Object[][] objs = new Object[AL.size()][6];
        String clmns[] = null;
        
        if(iMuzaMusic.getLoggedUser() instanceof LRep){
        
        clmns = new String[]{
          "#","Artist","Date","Status","Tickets","Ticket Price"  
        };
        } else if(iMuzaMusic.getLoggedUser() instanceof Customer){
            clmns = new String[]{
          "#","Artist","Date","Location","Tickets","Ticket Price"  
        };
        }
            int i = 0;
            for (Show s : AL) {
                
                objs[i][0] = i+1;
                objs[i][1] = s;
                objs[i][2] = (new SimpleDateFormat("dd/MM/Y hh:mm")).format(s.getpStartDate());
                 if(iMuzaMusic.getLoggedUser() instanceof LRep)
                     objs[i][3] = s.getpStatus();
                     else  if(iMuzaMusic.getLoggedUser() instanceof Customer)
                             objs[i][3] = s.getiLocation();
                
                objs[i][4] = s.getNumPurchased()+"/"+s.getMaxCapacity();
                objs[i][5] = "$"+s.getpTicketPrice();
               
                    
                
                i++;
            }
            jtbl.setModel(new javax.swing.table.DefaultTableModel(
                    objs,
                    clmns
            ));
DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
centerRenderer.setHorizontalAlignment( JLabel.CENTER );
jtbl.setDefaultRenderer(Object.class, centerRenderer);
          } catch (SQLException ex) {
            Logger.getLogger(ViewShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public static ResultSet getShowResult(Person person){
        String qry = "";
        if(person instanceof Customer){
        qry = "SELECT TOP 10 Shows.*, Artists.strStageName, Locations.strName, Locations.strAddress, Locations.urlGoogleMap, Locations.iMaxCapacity, qryShowTicketPurchases.SumOfNumberofTickets\n" +
"FROM Locations INNER JOIN (Artists INNER JOIN (qryShowTicketPurchases RIGHT JOIN Shows ON qryShowTicketPurchases.PerformenceID = Shows.pID) ON Artists.ArtistID = Shows.iMainArtist) ON Locations.LocationID = Shows.iLocation\n" +
"WHERE (((Shows.pStartDate)>Now()) AND ((Shows.pStatus) In (\"Approved\")))\n" +
"ORDER BY Shows.pStartDate;";
        }else if(person instanceof LRep){
            qry = "SELECT TOP 10 Shows.*, Artists.strStageName, Locations.strName, Locations.strAddress, Locations.urlGoogleMap, Locations.iMaxCapacity, qryShowTicketPurchases.SumOfNumberofTickets\n" +
"FROM Locations INNER JOIN (Artists INNER JOIN (qryShowTicketPurchases RIGHT JOIN Shows ON qryShowTicketPurchases.PerformenceID = Shows.pID) ON Artists.ArtistID = Shows.iMainArtist) ON Locations.LocationID = Shows.iLocation\n" +
"WHERE (((Shows.pStatus) Not In (\"Cancelled\")) AND ((Shows.pStartDate)>Now()) AND ((Locations.RepID) In (\""+person.getID()+"\")))\n" +
"ORDER BY Shows.pStartDate;";
        }
        return iMuzaMusic.getDB().query(qry);
    }
    
    public static String fillSubArtistsTable(String ShowID){
        String qry = "SELECT Artists.strStageName\n" +
"FROM Artists INNER JOIN ShowsToArtists ON Artists.ArtistID = ShowsToArtists.ArtistID\n" +
"WHERE (((ShowsToArtists.ShowID) In (\""+ShowID+"\")));";
        ResultSet rs = iMuzaMusic.getDB().query(qry);
       String txt = "";
        try {
            while(rs.next()){
                txt = txt + rs.getString(1)+", ";
            }
        } catch (SQLException ex) {
            Logger.getLogger(ViewShowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       iMuzaMusic.log("TEXT BEFORE: "+txt);
       try{
        txt = txt.substring(0, txt.length()-2)+".";
       }
       catch(Exception e){
           txt = "None";
       }
        iMuzaMusic.log("TEXT AFTER: "+txt);
       return txt;
        
        
    }

    public static void purchaseTickets(Show show, Customer customer, Integer numOfTickets) {
        Timestamp tsNow = new Timestamp(new Date().getTime());
        iMuzaMusic.log("Purchasing "+numOfTickets+" tickets to show "+show.getpID()+" for customer "+customer.getFirstName()+" "+customer.getLastName());
        String qry = "INSERT INTO ClientPurchases (ClientID, PerformenceID, NumberofTickets, datePurchased) values (\""+customer.getID()+"\",\""+show.getpID()+"\","+numOfTickets+", \""+tsNow+"\")";
        iMuzaMusic.getDB().updateReturnID(qry);
        iWindow.update();
        
    }
}
