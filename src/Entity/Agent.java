/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Agent extends Person {
    String PhoneNumber, Email;
    Date birthDate;
    public Agent(String ID, String strFirstName, String strLastName, String strPasswd, EAuth userAuth){
        super(ID, strFirstName, strLastName, strPasswd, userAuth);
        
    }

    public Agent(String PhoneNumber, String Email, Date birthDate, String ID, String firstName, String lastName, String password, EAuth userAuth) {
        super(ID, firstName, lastName, password, userAuth);
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.birthDate = birthDate;
    }

    public Agent(String PhoneNumber, String Email, Date birthDate, String ID, String firstName, String lastName, EAuth userAuth) {
        super(ID, firstName, lastName, userAuth);
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.birthDate = birthDate;
    }

    public Agent(String PhoneNumber, String Email, Date birthDate, String ID) {
        super(ID);
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.birthDate = birthDate;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
    
}
