/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import java.sql.*;

/**
 * DB Connection Manager
 * @author Administrator
 */
public class DBManager {
    private Connection conn;
    
    public DBManager(String urlToDatabase) throws ClassNotFoundException, SQLException{
        String driver="net.ucanaccess.jdbc.UcanaccessDriver"; 
        Class.forName(driver); 
        conn=DriverManager.getConnection("jdbc:ucanaccess://"+urlToDatabase);
    }
    
    
    public ResultSet query(String SQL) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(SQL);
        return result;
    }
    
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
    
}


