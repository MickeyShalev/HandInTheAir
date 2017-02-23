/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author nisan
 */
public class OpenLocation extends Location {
    String StageTop;
    Boolean isExistingSits=false, isStageLifted=false, isExistingAmp=false, isExistingToilets=false;

    public OpenLocation(String StageTop, Integer isExistingSits, Integer isStageLifted, Integer isExistingAmp, Integer isExistingToilets, String LocationID, String strName, String strAddress, String strEmail, String urlGoogleMaps, String iPhoneNum, LRep Owner, Integer maxCapacity) {
        super(LocationID, strName, strAddress, strEmail, urlGoogleMaps, iPhoneNum, Owner, maxCapacity);
        this.StageTop = StageTop;
        
        if(isExistingSits==1)
        this.isExistingSits = true;
        if(isStageLifted==1)
        this.isStageLifted = true;
        if(isExistingAmp==1)
        this.isExistingAmp = true;
        if(isExistingToilets==1)
        this.isExistingToilets = true;
    }
     public OpenLocation(String StageTop, Boolean isExistingSits, Boolean isStageLifted, Boolean isExistingAmp, Boolean isExistingToilets, String LocationID, String strName, String strAddress, String strEmail, String urlGoogleMaps, String iPhoneNum, LRep Owner, Integer maxCapacity) {
        super(LocationID, strName, strAddress, strEmail, urlGoogleMaps, iPhoneNum, Owner, maxCapacity);
        this.StageTop = StageTop;
        
  
        this.isExistingSits = isExistingSits;

        this.isStageLifted = isStageLifted;

        this.isExistingAmp = isExistingAmp;
 
        this.isExistingToilets = isExistingToilets;
    }
    
    public OpenLocation(String LocationID){
        super(LocationID);
    }

    public String getStageTop() {
        return StageTop;
    }

    public void setStageTop(String StageTop) {
        this.StageTop = StageTop;
    }

    public Boolean getIsExistingSits() {
        return isExistingSits;
    }

    public void setIsExistingSits(Boolean isExistingSits) {
        this.isExistingSits = isExistingSits;
    }

    public Boolean getIsStageLifted() {
        return isStageLifted;
    }

    public void setIsStageLifted(Boolean isStageLifted) {
        this.isStageLifted = isStageLifted;
    }

    public Boolean getIsExistingAmp() {
        return isExistingAmp;
    }

    public void setIsExistingAmp(Boolean isExistingAmp) {
        this.isExistingAmp = isExistingAmp;
    }

    public Boolean getIsExistingToilets() {
        return isExistingToilets;
    }

    public void setIsExistingToilets(Boolean isExistingToilets) {
        this.isExistingToilets = isExistingToilets;
    }
    
}
