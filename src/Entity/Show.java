/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Show {
    String pID, pStatus, iLocation, iMainArtist, iLocationAddress, locGoogleMap;
    Date pStartDate, pDateCreated;
    Double pTicketPrice;
    Integer pMinAge, maxCapacity, numPurchased;

    public Show(String pID, String pStatus, String iLocation, String iMainArtist, String iLocationAddress, String locGoogleMap, Date pStartDate, Date pDateCreated, Double pTicketPrice, Integer pMinAge, Integer maxCapacity, Integer numPurchased) {
        this.pID = pID;
        this.pStatus = pStatus;
        this.iLocation = iLocation;
        this.iMainArtist = iMainArtist;
        this.iLocationAddress = iLocationAddress;
        this.locGoogleMap = locGoogleMap;
        this.pStartDate = pStartDate;
        this.pDateCreated = pDateCreated;
        this.pTicketPrice = pTicketPrice;
        this.pMinAge = pMinAge;
        this.maxCapacity = maxCapacity;
        this.numPurchased = numPurchased;
    }


    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getpStatus() {
        return pStatus;
    }

    public void setpStatus(String pStatus) {
        this.pStatus = pStatus;
    }

    public String getiLocation() {
        return iLocation;
    }

    public String getiLocationAddress() {
        return iLocationAddress;
    }

    public void setiLocationAddress(String iLocationAddress) {
        this.iLocationAddress = iLocationAddress;
    }

    public String getLocGoogleMap() {
        return locGoogleMap;
    }

    public void setLocGoogleMap(String locGoogleMap) {
        this.locGoogleMap = locGoogleMap;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Integer getNumPurchased() {
        return numPurchased;
    }

    public void setNumPurchased(Integer numPurchased) {
        this.numPurchased = numPurchased;
    }

    public void setiLocation(String iLocation) {
        this.iLocation = iLocation;
    }

    public String getiMainArtist() {
        return iMainArtist;
    }

    public void setiMainArtist(String iMainArtist) {
        this.iMainArtist = iMainArtist;
    }

    public Date getpStartDate() {
        return pStartDate;
    }

    public void setpStartDate(Date pStartDate) {
        this.pStartDate = pStartDate;
    }

    public Date getpDateCreated() {
        return pDateCreated;
    }

    public void setpDateCreated(Date pDateCreated) {
        this.pDateCreated = pDateCreated;
    }

    public Double getpTicketPrice() {
        return pTicketPrice;
    }

    public void setpTicketPrice(Double pTicketPrice) {
        this.pTicketPrice = pTicketPrice;
    }

    public Integer getpMinAge() {
        return pMinAge;
    }

    public void setpMinAge(Integer pMinAge) {
        this.pMinAge = pMinAge;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.pID);
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
        final Show other = (Show) obj;
        if (!Objects.equals(this.pID, other.pID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return pID;
    }
    
    
    
}
