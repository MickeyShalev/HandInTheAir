/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Administrator
 */
public class ShowsToArtists {
    private String ShowID;
    private String ArtistID;
    private String Status;
    private String showDate;
    private String Location;

    public ShowsToArtists(String ShowID, String ArtistID, String Status, String showDate, String Location) {
        this.ShowID = ShowID;
        this.ArtistID = ArtistID;
        this.Status = Status;
        this.showDate = showDate;
        this.Location = Location;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public String getShowID() {
        return ShowID;
    }

    public void setShowID(String ShowID) {
        this.ShowID = ShowID;
    }

    public String getArtistID() {
        return ArtistID;
    }

    public void setArtistID(String ArtistID) {
        this.ArtistID = ArtistID;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
