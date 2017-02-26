/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.File;
import java.util.Date;

/**
 *
 * @author Administrator
 */
public class Customer extends Person {
    Date birthDate;
    String nickName, email;
    File avatar;
    public Customer(String ID, String strFirstName, String strLastName, String strPasswd, EAuth userAuth, Date birthDate, String nickName, File avatar, String email){
        super(ID, strFirstName, strLastName, strPasswd, userAuth);
        this.birthDate = birthDate;
        this.avatar = avatar;
        this.nickName = nickName;
        this.email = email;
    }

    public File getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    
    
}
