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
public abstract class Person {
    private String firstName, lastName, ID, password;
    private int userAuth=0;
    public Person(String ID, String firstName, String lastName, String password, int userAuth){
        this.ID=ID;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.userAuth=userAuth;
        
    }

    public int getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(int userAuth) {
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
    
    public String getAuth2Text(){
        String toReturn="";
        
        
        switch(this.userAuth){
            case 1:
                toReturn = "Customer";
            break;
            case 2:
                toReturn = "Agent";
            break;
            case 3:
                toReturn = "Artist";
            break;
            case 4:
                toReturn = "Location Represantative";
            break;
            case 5:
                toReturn = "Represantative";
            break;
            case 6:
                toReturn = "admin";
            break;
            default:
                toReturn = "Unknown";
            break;
        }
        
        return toReturn;
    }
    
    
    
    
}
