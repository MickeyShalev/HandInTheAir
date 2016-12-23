/**
 * This Class controls the opened windows and prevents more than one shown window
 * Other windows will remain active but at hide state;
 */
package gui.main;


import gui.main.MainGui;
import gui.main.LoginGui;
import java.awt.Color;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import jdk.nashorn.internal.scripts.JO;

/**
 * Main iWindow Class -> Manages all internal jframes and DB connection to GUI
 */
public abstract class iWindow {
    
    //User Decalress
    protected static int authLogged;
    
    
    //Main
    protected static JLabel lblTitle = null;
    
    //iWindow Management
    protected static JInternalFrame currentWindow = null;
    protected static JInternalFrame lastWindow = null;
    protected static JPanel panel = null;
    protected static BufferedWriter out = null;
    
//    //======================================= Main ==========================================
    public static void openLogin(){
        LoginGui tmp = new LoginGui();
        
    }
    
    
    //================================= Setters and Getters ==================================
    
    
    /**
     * Set the current active frame
     *
     * @param frame
     */
    public static void setCurrentWindow(JInternalFrame frame) {
        currentWindow = frame;
    }
    
    /**
     * Get the current active frame
     *
     * @return
     */
    public static JInternalFrame getCurrentWindow() {
        return currentWindow;
    }
    
    //================================ Methods =========================================
    /**
     * This method controls the opened internal windows by hiding and showing
     * desired windows
     *
     * @param frame
     * @param WindowID
     * @param windowTitle
     */
    public static void openWin(JInternalFrame frame) {
        if (frame == null) {
            return;
        }
        
        //Check for exceptions
        
        
        BasicInternalFrameUI bi = (BasicInternalFrameUI) frame.getUI();
        bi.setNorthPane(null);
        
        frame.setBackground(new Color(255, 255, 255, 5));
        
        frame.setSize(800, 600);
        frame.setBorder(null);
        lblTitle.setText(frame.getTitle());
        
        if (getCurrentWindow() == null) {
            setCurrentWindow(frame);
            
        } else {
            if (frame == getCurrentWindow()) {
                frame.setVisible(true);
                return;
            } else {
                
                //Hide the opened window
                getCurrentWindow().setVisible(false);
                
                //Set the last window from the current one
                setLastWindow(getCurrentWindow());
                
                //Set the current window
                setCurrentWindow(frame);
                
            }
        }
        
        getPanel().add(getCurrentWindow());
        getPanel().setVisible(true);
        frame.setVisible(true);
        
        iWindow.update();
        return;
    }
    
    /**
     * Set the content pane panel
     *
     * @param tmp
     */
    public static void setPanel(JPanel tmp) {
        panel = tmp;
    }
    
    /**
     * Returns one loop back to the last window opened
     */
    public static void returnWindow() {
        
        if (getCurrentWindow() == null || getLastWindow() == null) {
            return;
        }
        
        getCurrentWindow().hide();
        JInternalFrame tmp = getCurrentWindow();
        setCurrentWindow(getLastWindow());
        setLastWindow(tmp);
        
        iWindow.update();
    }
    
    /**
     * Aquire the content pane panel
     *
     * @return
     */
    public static JPanel getPanel() {
        return panel;
    }
    
    /**
     * Sets the last window opened
     *
     * @param frame
     */
    public static void setLastWindow(JInternalFrame frame) {
        lastWindow = frame;
    }
    
    /**
     * Returns the last window opened
     *
     * @return
     */
    public static JInternalFrame getLastWindow() {
        return lastWindow;
    }
    
    /**
     * Log Function - print message to log file and save it
     *
     * @param message
     */
    public static void log(String message) {
        try
        {
            FileWriter fstream = new FileWriter("LOG.txt", true); //true tells to append data.
            out = new BufferedWriter(fstream);
            out.write(message + "\n");
            out.flush();
        }
        catch (IOException e)
        {
            System.err.println("Error: " + e.getMessage());
        }
        
        System.err.println(message);
    }
    
    
    
    /**
     * Return String Type by Auth
     *
     * @return
     */
    public static String getAuthType() {
        String toReturn = null;
        
        switch (authLogged) {
            case 1:
                toReturn = "Customer";
                break;
            case 2:
                toReturn = "Receptionist";
                break;
            case 3:
                toReturn = "Coach";
                break;
                
            case 4:
                toReturn = "Administrator";
                break;
            default:
                toReturn = "ERROR";
                break;
        }
        return toReturn;
        
    }
    
    /**
     * Return color by auth
     *
     * @return
     */
    public static Color getAuthColor() {
        Color color = Color.white;
        switch (authLogged) {
            case 1:
                color = Color.green;
                break;
            case 2:
                color = Color.orange;
                break;
            case 3:
                color = Color.blue;
                break;
            case 4:
                color = Color.red;
                break;
                
        }
        return color;
    }
    
    /**
     * Return AUTH ID
     *
     * @return
     */
    public static int getAuthValue() {
        return authLogged;
    }
    
    /**
     * Sets customer rights
     *
     * @param AuthType
     * @param user
     */
    public static void setUser(int AuthType, Object user) {
        if (AuthType <= 0) {
            return;
        }
        if (user == null) {
            return;
        }
        authLogged = AuthType;
        switch (AuthType) {
            case 1:
                System.err.println("CUSTOMER");
                
                break;
            default:
                System.err.println("EMPLOYEE");
                
                break;
                
        }
        
        return;
    }
    
    /**
     * Sets the title lbl, from main
     *
     * @param title
     */
    public static void setLblTitle(JLabel title) {
        lblTitle = title;
    }
    
    
    /**
     * DC Method to clean GUI Vars
     */
    public static void clean() {
        authLogged = 0;
        currentWindow = null;
        
        lblTitle.setText("Welcome to Virutal iShape");
        
    }
    
    /**
     * Updates the current frame used
     */
    public static void update() {
        if (getCurrentWindow() == null) {
            return;
        }
        getCurrentWindow().setVisible(false);
        getCurrentWindow().setVisible(true);
    }
    
    /**
     * Updates the frame given ~ frame
     *
     * @param frame
     */
    public static void update(JInternalFrame frame) {
        frame.setVisible(false);
        frame.setVisible(true);
    }
    
    
    
}
