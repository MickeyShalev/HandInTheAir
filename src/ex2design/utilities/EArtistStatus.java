/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design.utilities;

/**
 * EArtistSTatus - Artist Status Types
 * @author Administrator
 */
public enum EArtistStatus {

    Active, Inactive;
    
    
    /**
     * This class returns a toString of a given EAuth object
     * @return 
     */
    @Override
    public String toString(){
        String toReturn = "";
        switch(this){
            case Active: toReturn = "Active"; break;
            case Inactive: toReturn = "Inactive"; break;
            
            default: toReturn = "Unknown Type"; break;
        }
        return toReturn;
    }
}


