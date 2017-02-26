/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Main;

import Controller.Main.iMuzaMusic;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Administrator
 */
public abstract class FileManager {


    private static String uploadDir = "src\\sources\\uploads\\";
    private static String uploadDirJar = "sources\\uploads\\";
 

    /**
     * Decodes a given file to a base 64 string
     *
     * @param f
     * @return
     */
    public static String toBase64(File f) {
        byte[] arr = null;
        iMuzaMusic.log("Decoding file to base64: " + f.getAbsolutePath());
        try {
            arr = Files.readAllBytes(f.toPath());
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        String base64encode = Base64.getEncoder().encodeToString(arr);
        iMuzaMusic.log("Encoded to string: " + base64encode);
        return base64encode;
    }

    /**
     * Encode into File and creates a tmp file
     * @param str
     * @return 
     */
    public static File fromBase64(String str) {
        
        byte arr[] = Base64.getDecoder().decode(str);
        File f = new File(uploadDir+new Date().getTime()+".tmp");
        
        try {
            Files.write(f.toPath(), arr);
        } catch (IOException ex) {
            f = new File(uploadDirJar+new Date().getTime()+".tmp");
            try {
                Files.write(f.toPath(), arr);
            } catch (IOException ex1) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return f;
    }
    
    
    public static ImageIcon fileToIcon(File f){
        return new ImageIcon(f.getAbsolutePath());
    }

}
