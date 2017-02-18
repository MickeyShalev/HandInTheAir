/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Artist;

import Controller.General.iMuzaMusic;
import Entity.Agent;
import Entity.Artist;
import Entity.EAuth;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
}
