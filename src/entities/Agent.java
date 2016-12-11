/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import ex2design.utilities.EAuth;
/**
 *
 * @author Administrator
 */
public class Agent extends Person {
    public Agent(String ID, String strFirstName, String strLastName, String strPasswd, EAuth userAuth){
        super(ID, strFirstName, strLastName, strPasswd, userAuth);
        
    }
}
