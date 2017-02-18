/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Location {
    String LocationID, strName, strAddress, strEmail, urlGoogleMaps, iPhoneNum;
    LRep Owner;

    public Location(String LocationID, String strName, String strAddress, String strEmail, String urlGoogleMaps, String iPhoneNum, LRep Owner) {
        this.LocationID = LocationID;
        this.strName = strName;
        this.strAddress = strAddress;
        this.strEmail = strEmail;
        this.urlGoogleMaps = urlGoogleMaps;
        this.iPhoneNum = iPhoneNum;
        this.Owner = Owner;
    }

    public Location(String LocationID, String strName, String strAddress) {
        this.LocationID = LocationID;
        this.strName = strName;
        this.strAddress = strAddress;
    }
    
    public Location(String LocationID){
        this.LocationID = LocationID;
    }

    public String getLocationID() {
        return LocationID;
    }

    public void setLocationID(String LocationID) {
        this.LocationID = LocationID;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrAddress() {
        return strAddress;
    }

    public void setStrAddress(String strAddress) {
        this.strAddress = strAddress;
    }

    public String getStrEmail() {
        return strEmail;
    }

    public void setStrEmail(String strEmail) {
        this.strEmail = strEmail;
    }

    public String getUrlGoogleMaps() {
        return urlGoogleMaps;
    }

    public void setUrlGoogleMaps(String urlGoogleMaps) {
        this.urlGoogleMaps = urlGoogleMaps;
    }

    public String getiPhoneNum() {
        return iPhoneNum;
    }

    public void setiPhoneNum(String iPhoneNum) {
        this.iPhoneNum = iPhoneNum;
    }

    public LRep getOwner() {
        return Owner;
    }

    public void setOwner(LRep Owner) {
        this.Owner = Owner;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.LocationID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Location other = (Location) obj;
        if (!Objects.equals(this.LocationID, other.LocationID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getStrName();
    }
    
    
    
    
    
    
}
