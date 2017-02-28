/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Objects;

/**
 *
 * @author Administrator
 */
public class Person {
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
      public Person(String ID, String firstName, String lastName, EAuth userAuth){
        this.ID=ID;
        this.firstName=(firstName.substring(0, 1).toUpperCase() + firstName.substring(1));
        if(lastName.length()>0)
        this.lastName=(lastName.substring(0, 1).toUpperCase() + lastName.substring(1));
        else
            this.lastName="";
        this.userAuth=userAuth;
        
    }  
      public Person(String ID, String PW, String firstName, String lastName){
          this.ID = ID;
          this.password = PW;
          this.firstName = firstName;
          this.lastName = lastName;
      }
    public Person(String ID){
        this.ID=ID;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.ID);
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
        final Person other = (Person) obj;
        if (!Objects.equals(this.ID, other.ID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        if(ID.equals("0000"))
            return firstName;
        
        return firstName+" "+lastName+" ("+ID+")";
    }
    
  
    
}
