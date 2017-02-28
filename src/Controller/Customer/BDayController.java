/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Customer;

import Controller.Main.iMuzaMusic;
import Entity.Customer;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nisan
 */
public abstract class BDayController {

    public static String getBirthdayText(Customer cust) {
        //Get bday text

        String bdayText = "", name = "";

        Integer age = 0;
        Date birthDate = null;
        iMuzaMusic.log("Creating birthday card for customer: " + cust.getFirstName());
        try {
            String qry = "SELECT TOP 1 * from BDayCards order by id desc";
            ResultSet rs = iMuzaMusic.getDB().query(qry);
            rs.next();
            bdayText = rs.getString(2);
            String qry2 = "SELECT Customers.ClientID, Customers.strFirstName, Customers.strLastName, Customers.BirthDate, DateDiff(\"yyyy\",[BirthDate],Now()) AS Age\n"
                    + "FROM Customers WHERE ClientID in(\"" + cust.getID() + "\")";
            //{date}, {name}, {age}
            ResultSet rs2 = iMuzaMusic.getDB().query(qry2);
            rs2.next();
            age = rs2.getInt("Age");
            birthDate = rs2.getDate("BirthDate");
            name = rs2.getString("strFirstName") + " " + rs2.getString("strLastName");

            //Alter bday text
            bdayText = bdayText.replace("{name}", name);
            bdayText = bdayText.replace("{date}", (new SimpleDateFormat("dd/MM").format(birthDate)));
            bdayText = bdayText.replace("{age}", age.toString());
            return bdayText;
        } catch (SQLException ex) {
            Logger.getLogger(BDayController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "";

    }

    public static List<File> getSigList(Customer cust) {
        List<File> fileList = new ArrayList<File>();
        //get artist sigs
        String qry3 = "SELECT Artists.sig, CustomerAdmires.ClientID, CustomerAdmires.ArtistID\n"
                + "FROM Artists INNER JOIN CustomerAdmires ON Artists.ArtistID = CustomerAdmires.ArtistID\n"
                + "WHERE (((CustomerAdmires.ClientID) In (\"" + cust.getID() + "\")));";
        ResultSet rs3 = iMuzaMusic.getDB().query(qry3);
        try {
            while (rs3.next()) {
                iMuzaMusic.log("Added signarture file of artist " + rs3.getString("ArtistID"));
                fileList.add(Controller.Main.FileManager.fromBase64(rs3.getString("sig")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDayController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fileList;
    }
}
