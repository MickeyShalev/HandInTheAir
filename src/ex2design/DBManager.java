/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DB Connection Manager
 * @author Administrator
 */
public class DBManager {
    private static Connection conn;
    
    public DBManager(String urlToDatabase) throws ClassNotFoundException, SQLException{
        String driver="net.ucanaccess.jdbc.UcanaccessDriver"; 
        Class.forName(driver); 
        conn=DriverManager.getConnection("jdbc:ucanaccess://"+urlToDatabase);
    }
    
    /**
     * This method returns a ResultSet on given SQL Query
     * @param SQL
     * @return 
     */
    public static ResultSet query(String SQL){
        ResultSet result = null;
        try {
            Statement stmt = conn.createStatement();
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
    public int updateReturnID(String SQL) throws SQLException{
        Statement stmt=conn.createStatement();
        int id=-1;
        stmt.executeUpdate(SQL, Statement.RETURN_GENERATED_KEYS);
        ResultSet result = stmt.getGeneratedKeys();
        if(result.next()){
            id=result.getInt(1);
        }
        
        return id;
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
    
}


