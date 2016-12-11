/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design.utilities;

/**
 * EAuth - User Auth Types
 * @author Administrator
 */
public enum EAuth {

    Customer, Agent, Artist, Location_Representative, Representative, Administrator;

    /**
     * This class returns a toString of a given EAuth object
     * @return 
     */
    @Override
    public String toString(){
        String toReturn = "";
        switch(this){
            case Customer: toReturn = "Customer"; break;
            case Agent: toReturn = "Agent"; break;
            case Artist: toReturn = "Artist"; break;
            case Location_Representative: toReturn = "Location Representative"; break;
            case Representative: toReturn = "Representative"; break;
            case Administrator: toReturn = "Administrator"; break;
            default: toReturn = "Unknown Type"; break;
        }
        return toReturn;
    }
}


