/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Agent;

import Controller.Main.iMuzaMusic;
import Entity.Artist;
import Entity.EArtistStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public abstract class GeneralController {
    public static Artist generateArtistEntity(ResultSet rs){
        Artist art = null;
        String iStatus = null;
        try {
            String ArtistID = rs.getString("ArtistID");
            String strStageName = rs.getString("strStageName");
            String strBio = rs.getString("strShortBio");
            String strEmailAddr = rs.getString("strEmailAddr");
            String strFaceBook = rs.getString("strFaceBook");
            String AgentID = rs.getString("AgentID");
            iStatus = rs.getString("iStatus");
            String iconPath = rs.getString("Signarture");
           
            art = new Artist(ArtistID, strBio, strStageName, strFaceBook, EArtistStatus.getStatus(iStatus), strEmailAddr, iconPath);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException ex){
            iMuzaMusic.log("Can't add artist with status "+iStatus);
        }
        
        return art;
    }
}
