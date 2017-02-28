/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Main;

import java.sql.*;
import Controller.Main.iMuzaMusic;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.ucanaccess.jdbc.UcanaccessSQLException;

/**
 * DB Connection Manager
 * @author Administrator
 */
public class DBManager {
    private static Connection conn;
    
   
    public DBManager() throws ClassNotFoundException, SQLException{
        String dbFile = (new File("sources/MM_DB.accdb")).getAbsolutePath();
        iMuzaMusic.log("DB File: "+dbFile);
        String driver="net.ucanaccess.jdbc.UcanaccessDriver"; 
        Class.forName(driver); 
        try{
            System.err.println("CONNECTING DB TO: jdbc:ucanaccess://"+dbFile);
        this.conn=DriverManager.getConnection("jdbc:ucanaccess://"+dbFile); 
        
        }
        catch(Exception e){
            
            dbFile = (new File("src/sources/MM_DB.accdb")).getAbsolutePath();
            System.err.println("Using second URL: jdbc:ucanaccess://"+dbFile);
            this.conn=DriverManager.getConnection("jdbc:ucanaccess://"+dbFile);

        } 
        
        
    }
    
    /**
     * This method returns a ResultSet on given SQL Query
     * @param SQL
     * @return 
     */
    public static ResultSet query(String SQL){
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = stmt.executeQuery(SQL);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return result;
    }
    
    /**
     * This is an update query which returns the generated keys
     * TO TEST
     * @param SQL
     * @return
     * @throws SQLException 
     */
    public static void updateReturnID(String SQL){

        iMuzaMusic.log("Sending UPDATE Query: "+SQL);
        try {
            Statement stmt= conn.createStatement();
            stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
            ResultSet result = stmt.getGeneratedKeys();
            if(result.next()){

            }
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * This method will check if a row exists on given SQL Query
     * @param SQL
     * @return true if exists, false otherwise
     */
    public static Boolean queryBool(String SQL){
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
            result = stmt.executeQuery(SQL);
            if(result.next())
                if(result.getInt(1)>0)
                    return true;
 
        } catch (SQLException ex) {
            Logger.getLogger(DBManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            return false;
    }

    public Connection getConn() {
        return this.conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }
    public Connection getConnection(){
        return conn;
    }
    

}


