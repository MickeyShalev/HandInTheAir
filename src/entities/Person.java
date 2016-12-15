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
public abstract class Person {
    private String firstName, lastName, ID, password;
    private EAuth userAuth=null;
    public Person(String ID, String firstName, String lastName, String password, EAuth userAuth){
        this.ID=ID;
        this.firstName=(firstName.substring(0, 1).toUpperCase() + firstName.substring(1));
        if(lastName.length()>0)
        this.lastName=(lastName.substring(0, 1).toUpperCase() + lastName.substring(1));
        else
            this.lastName="";
        this.password=password;
        this.userAuth=userAuth;
        
    }

    public EAuth getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(EAuth userAuth) {
        this.userAuth = userAuth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getID() {
        return ID;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
  
    
}
