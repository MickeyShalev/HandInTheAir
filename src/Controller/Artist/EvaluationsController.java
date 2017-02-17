/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Artist;
import Controller.General.DBManager;
import Entity.Artist;
import java.sql.ResultSet;

/**
 *
 * @author nisan
 */
public abstract class EvaluationsController{
    DBManager DB = Controller.General.iMuzaMusic.getDB();
    public static void getEvaluatedArtist(Artist artist){
        ResultSet rs = DBManager.query("select * from Artists where ArtistID!=\""+artist.getID()+"\"");
    }
}
