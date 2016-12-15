/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import ex2design.utilities.EArtistStatus;
import ex2design.utilities.EAuth;

/**
 *
 * @author Administrator
 */
public class Artist extends Person {

    public String getEmailAddr() {
        return emailAddr;
    }

    public void setEmailAddr(String emailAddr) {
        this.emailAddr = emailAddr;
    }
    private String biography = "";
    private String stageName = "";
    private String fbAddr = "";
    private EArtistStatus arStatus = null;
    private String emailAddr = "";
    public Artist(String ID, String strFirstName, String strLastName, String strPasswd, EAuth userAuth, String biography, String stageName, String fbAddr, EArtistStatus arStatus, String emailAddr){
        super(ID, strFirstName, strLastName, strPasswd, userAuth);
        this.biography=biography;
        this.stageName=stageName;
        this.fbAddr=fbAddr;
        this.arStatus=arStatus;
        this.emailAddr=emailAddr;
    }

    public String getFbAddr() {
        return fbAddr;
    }

    public void setFbAddr(String fbAddr) {
        this.fbAddr = fbAddr;
    }

    public EArtistStatus getArStatus() {
        return arStatus;
    }

    public void setArStatus(EArtistStatus arStatus) {
        this.arStatus = arStatus;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }
    
}
