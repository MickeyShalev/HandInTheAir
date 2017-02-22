/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer;

import Boundary.Main.iWindow;

import Controller.Main.iMuzaMusic;
import Entity.Artist;
import Entity.EArtistStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

/**
 *
 * @author nisan
 */
public abstract class FanController {
    public static void updateFormData(JComboBox combo, JTable table){
        Map<String, Artist> artList = new HashMap<String, Artist>();
        Map<String, Artist> favList = new HashMap<String, Artist>();
        Boolean openLoc = false;
        combo.removeAllItems();
        //Get Artists 
        ResultSet rs = iMuzaMusic.getDB().query("SELECT * from Artists");
        try {
            while(rs.next()){
                    //regular location
                    String ArtistID = rs.getString(1);
                    String strStageName = rs.getString(2);
                    String strBio = rs.getString(3);
                    String strEmail = rs.getString(4);
                    String strFacebook = rs.getString(5);
                    String strAgent = rs.getString(6);
                    String strSig = rs.getString(8);
                    iMuzaMusic.log("Artist FB: "+rs.getObject(5));
               
                
                   Artist add = new Artist(ArtistID, strBio, strStageName, strFacebook, EArtistStatus.Active, strEmail, strSig);
                   artList.put(ArtistID, add);
                }
                
                
                
               
            iWindow.update();
                
            } catch (SQLException ex) {
            Logger.getLogger(FanController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            //Finished adding all locations
            //Get locations already favorited
            rs = iMuzaMusic.getDB().query("SELECT CustomerAdmires.ArtistID\n" +
"FROM CustomerAdmires\n" +
"WHERE (((CustomerAdmires.ClientID) In (\""+iMuzaMusic.getLoggedUser().getID()+"\")));");
            
        try {
            while(rs.next()){
                Artist tmp = artList.get(rs.getString(1));
                iMuzaMusic.log("Removing "+tmp.getStageName()+" from favorited list");
                artList.remove(tmp.getID());
                
                favList.put(tmp.getID(), tmp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FanController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            iMuzaMusic.log("Found "+favList.size()+" favorited artists");
            //Add to select box
            Artist tmp = new Artist("0000");
            tmp.setStageName("Select Artist");
            combo.addItem(tmp);
            for(Artist l : artList.values())
                combo.addItem(l);
            
            Object[][] objs = new Object[favList.size()][3];
            int i = 0;
            for (Artist art : favList.values()) {
                objs[i][0] = art.getID();
                objs[i][1] = art;
                objs[i][2] = false;
                i++;
            }
            table.setModel(new javax.swing.table.DefaultTableModel(
                    objs,
                    new String[]{
                        "#", "Artist Name", "Unfavorite"
                    }
            ));
TableColumn tc = table.getColumnModel().getColumn(2);
            tc.setCellEditor(table.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(table.getDefaultRenderer(Boolean.class));
            
                
    
        
    }

       public static void unFav(String ArtistID) {
        iMuzaMusic.getDB().updateReturnID("delete from CustomerAdmires where ClientID=\""+iMuzaMusic.getLoggedUser().getID()+"\" AND ArtistID=\""+ArtistID+"\"");
    }
    
    public static void addFav(String ArtistID){
        iMuzaMusic.getDB().updateReturnID("INSERT INTO CustomerAdmires (ClientID, ArtistID) values (\""+iMuzaMusic.getLoggedUser().getID()+"\",\""+ArtistID+"\")");
    }
}
