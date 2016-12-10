/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Administrator
 */
public class Customer extends Person {
    public Customer(String ID, String strFirstName, String strLastName, String strPasswd, int userAuth){
        super(ID, strFirstName, strLastName, strPasswd, userAuth);
        
    }
}
