/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Agent;

import Controller.Main.FileManager;
import Controller.Main.iMuzaMusic;
import Entity.Artist;
import Entity.EArtistStatus;
import java.io.File;
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
            File sig = null;
            if(rs.getString("sig")!=null)
                sig = FileManager.fromBase64(rs.getString("sig"));
           
            art = new Artist(ArtistID, strBio, strStageName, strFaceBook, EArtistStatus.getStatus(iStatus), strEmailAddr, sig);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(GeneralController.class.getName()).log(Level.SEVERE, null, ex);
        } catch(NumberFormatException ex){
            iMuzaMusic.log("Can't add artist with status "+iStatus);
        }
        
        return art;
    }
}
