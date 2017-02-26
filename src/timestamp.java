
import java.sql.Timestamp;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class timestamp {
    public static void main(String args[]){
        System.err.println(new Timestamp(new Date().getTime()).getTime());
        
    }
}
