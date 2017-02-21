/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary.Internal.Agent;

import Controller.Main.iMuzaMusic;
import Entity.Artist;
import Entity.Person;
import Controller.Main.DBManager;
import Entity.EArtistStatus;
import Entity.EAuth;
import Boundary.Main.iWindow;
import Controller.Show.ShowController;
import Entity.Agent;
import Entity.Location;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/**
 *
 * @author nisan
 */
public class frmCreateShow extends javax.swing.JInternalFrame {
    Map<String, Artist> artistNames;
    Boolean rePaint = true;
    /**
     * Creates new form frmTemplate
     */
    private static Artist art = null;
    public frmCreateShow() {
        artistNames = new HashMap<String, Artist>();
        initComponents();
        setTitle("Create New Show");
        slctArtist.removeAllItems();
        txtLocation.setVisible(false);
        slctLocation.removeAllItems();
        slctLocation.setVisible(false);
        Location tmpL = new Location("0000");
        tmpL.setStrName("Select Location");
        slctLocation.addItem(tmpL);
        lblAddress.setVisible(false);
        slctedArtists.removeAll();
        slctSubArtist.setVisible(false);
        slctedArtists.setBackground(new Color(0,0,0,0));
        slctedArtists.setForeground(Color.black);
        slctedArtists.setEnabled(false);
        slctedArtists.setVisible(false);
        pnlSubArtists.setVisible(false);
        jScrollPane1.setVisible(false);
        btnCreate.setVisible(false);
        fldPrice.setVisible(false);
        fldAge.setVisible(false);
        txtSign.setVisible(false);
        txtMinimum.setVisible(false);
        txtPrice.setVisible(false);
        pnlArtistSelected.setVisible(false);
        jXDatePicker1.setVisible(false);
        jLabel3.setVisible(false);
        slctedArtists.setModel(new DefaultListModel());
        slctArtist.removeAllItems();
        Artist tmp = new Artist("0000");
        tmp.setStageName("Select Artist");
        slctArtist.addItem(tmp);
        
        
        for(Artist a : ShowController.getArtistsByAgent((Agent) iMuzaMusic.getLoggedUser())){
            slctArtist.addItem(a);
        }
        
        
        //Handle time
        slctTime.removeAllItems();
        for(int i=0; i<=24;i++){
            slctTime.addItem(String.format("%02d:00",i));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel16 = new javax.swing.JLabel();
        jXDatePicker1 = new org.jdesktop.swingx.JXDatePicker();
        txtSlctArtist = new javax.swing.JLabel();
        slctArtist = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtLocation = new javax.swing.JLabel();
        slctLocation = new javax.swing.JComboBox<>();
        lblAddress = new javax.swing.JLabel();
        pnlSubArtists = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        try {
            slctedArtists =(javax.swing.JList)java.beans.Beans.instantiate(getClass().getClassLoader(), "Boundary/Internal/Agent.frmCreateShow_slctedArtists");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        jLabel1 = new javax.swing.JLabel();
        addArtist = new javax.swing.JButton();
        slctSubArtist = new javax.swing.JComboBox<>();
        btnCreate = new javax.swing.JButton();
        fldPrice = new javax.swing.JTextField();
        txtPrice = new javax.swing.JLabel();
        txtSign = new javax.swing.JLabel();
        txtMinimum = new javax.swing.JLabel();
        fldAge = new javax.swing.JTextField();
        slctTime = new javax.swing.JComboBox<>();
        pnlArtistSelected = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblArtistStageName = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtBio = new javax.swing.JLabel();

        setBackground(new Color(0,0,0,0));
        setAutoscrolls(true);
        setOpaque(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel16.setBackground(new Color(0,0,0,0));
        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Welcome to Create New Show feature!");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 360, 30));

        jXDatePicker1.setBackground(new Color(0,0,0,0));
        jXDatePicker1.setVisible(false);
        jXDatePicker1.setForeground(new java.awt.Color(255, 255, 255));
        jXDatePicker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jXDatePicker1ActionPerformed(evt);
            }
        });
        getContentPane().add(jXDatePicker1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 210, 160, 20));

        txtSlctArtist.setBackground(new java.awt.Color(0, 0, 0));
        txtSlctArtist.setForeground(new java.awt.Color(255, 255, 255));
        txtSlctArtist.setText("Select Main Artist");
        getContentPane().add(txtSlctArtist, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 170, -1));

        slctArtist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                slctArtistItemStateChanged(evt);
            }
        });
        slctArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slctArtistActionPerformed(evt);
            }
        });
        getContentPane().add(slctArtist, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 170, -1));

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Select Show Date");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 170, 30));

        txtLocation.setForeground(new java.awt.Color(255, 255, 255));
        txtLocation.setText("Select Location");
        getContentPane().add(txtLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 190, 170, 20));

        slctLocation.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                slctLocationItemStateChanged(evt);
            }
        });
        getContentPane().add(slctLocation, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 170, -1));

        lblAddress.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        lblAddress.setForeground(new java.awt.Color(255, 255, 255));
        lblAddress.setText("Address");
        getContentPane().add(lblAddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 230, 230, 20));

        pnlSubArtists.setBackground(new Color(0,0,0,0));
        pnlSubArtists.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new Color(0,0,0,0));
        jScrollPane1.setBorder(null);
        jScrollPane1.setForeground(new java.awt.Color(255, 255, 255));

        slctedArtists.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(slctedArtists);

        pnlSubArtists.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 280, 170));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Selected Invited Artists");
        pnlSubArtists.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        addArtist.setText("Add");
        addArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addArtistActionPerformed(evt);
            }
        });
        pnlSubArtists.add(addArtist, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 80, 20));

        slctSubArtist.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                slctSubArtistItemStateChanged(evt);
            }
        });
        slctSubArtist.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                slctSubArtistInputMethodTextChanged(evt);
            }
        });
        slctSubArtist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slctSubArtistActionPerformed(evt);
            }
        });
        pnlSubArtists.add(slctSubArtist, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 170, -1));

        getContentPane().add(pnlSubArtists, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 320, 250));

        btnCreate.setText("Create Show");
        btnCreate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateActionPerformed(evt);
            }
        });
        getContentPane().add(btnCreate, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, 280, -1));

        fldPrice.setText("0.00");
        fldPrice.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fldPriceFocusLost(evt);
            }
        });
        getContentPane().add(fldPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 160, 40, -1));

        txtPrice.setForeground(new java.awt.Color(255, 255, 255));
        txtPrice.setText("Ticket Price: ");
        getContentPane().add(txtPrice, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 80, 20));

        txtSign.setForeground(new java.awt.Color(255, 255, 255));
        txtSign.setText("$");
        getContentPane().add(txtSign, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 10, 20));

        txtMinimum.setForeground(new java.awt.Color(255, 255, 255));
        txtMinimum.setText("Minimum Age:");
        getContentPane().add(txtMinimum, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 160, 90, 20));

        fldAge.setText("0");
        fldAge.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fldAgeFocusLost(evt);
            }
        });
        fldAge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldAgeActionPerformed(evt);
            }
        });
        getContentPane().add(fldAge, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 30, -1));

        slctTime.setVisible(false);
        slctTime.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        getContentPane().add(slctTime, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 240, 70, -1));

        pnlArtistSelected.setBackground(new Color(0,0,0,0));
        pnlArtistSelected.setVisible(false);
        pnlArtistSelected.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnlArtistSelected.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Selected Artist Details");
        pnlArtistSelected.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 210, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Stage Name: ");
        pnlArtistSelected.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 100, -1));

        lblArtistStageName.setBackground(new java.awt.Color(255, 255, 255));
        lblArtistStageName.setForeground(new java.awt.Color(255, 255, 255));
        lblArtistStageName.setText("jLabel5");
        pnlArtistSelected.add(lblArtistStageName, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, 120, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Biography");
        pnlArtistSelected.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 60, -1));

        txtBio.setForeground(new java.awt.Color(255, 255, 255));
        txtBio.setText("jLabel7");
        pnlArtistSelected.add(txtBio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 330, -1));

        getContentPane().add(pnlArtistSelected, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 280, 380, 230));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jXDatePicker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jXDatePicker1ActionPerformed
        
        
        
    }//GEN-LAST:event_jXDatePicker1ActionPerformed

    private void slctArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slctArtistActionPerformed
        
    }//GEN-LAST:event_slctArtistActionPerformed

    private void slctArtistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_slctArtistItemStateChanged
        // TODO add your handling code here:
        // TODO add your handling code here:
     if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            if (item.toString().equals("Select Artist")) 
                return;
            
       slctSubArtist.removeAllItems();
       Artist a = new Artist("0000");
       a.setStageName("Select Sub Artists");
       slctSubArtist.addItem(a);
        
        
        for(Artist tmpSubArt : ShowController.getSubArtistsForShow((Artist)slctArtist.getSelectedItem()))
            slctSubArtist.addItem(tmpSubArt);
             
        jScrollPane1.setVisible(true);
        slctSubArtist.setVisible(true);
        txtLocation.setVisible(true);
        slctLocation.setVisible(true);
        Date occdates[] = ShowController.getOccupiedDates((Artist)slctArtist.getSelectedItem());
        jXDatePicker1.getMonthView().setUnselectableDates(occdates);
        jXDatePicker1.setVisible(true);
        jLabel3.setVisible(true);
        slctTime.setVisible(true);
        //This will get available artists on a given date
        //Go through all of this agents' artists
       
        
        
      for(Location l : ShowController.getFavLocationsByAgent((Agent)iMuzaMusic.getLoggedUser()))
                slctLocation.addItem(l);
            
        
        
        
        // Enable Fields
        slctArtist.setVisible(true);
        txtSlctArtist.setVisible(true);
        txtMinimum.setVisible(true);
        txtPrice.setVisible(true);
        txtSign.setVisible(true);
        fldPrice.setVisible(true);
        fldAge.setVisible(true);
     }
     
     
     
     
    }//GEN-LAST:event_slctArtistItemStateChanged

    private void slctLocationItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_slctLocationItemStateChanged
        // TODO add your handling code here:
            if (evt.getStateChange() == ItemEvent.SELECTED) {
            Object item = evt.getItem();
            if (item.equals(new Location("0000"))) {
                    lblAddress.setVisible(false);
                return;
            }
            
            //Get location addr
            lblAddress.setText(((Location)slctLocation.getSelectedItem()).getStrAddress());
         
            slctedArtists.setVisible(false);
            jScrollPane1.setVisible(false);
            
            lblAddress.setVisible(true);
            btnCreate.setVisible(true);
            pnlSubArtists.setVisible(true);
            pnlArtistSelected.setVisible(true);
            }
    }//GEN-LAST:event_slctLocationItemStateChanged

    
    
    private void addArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addArtistActionPerformed
        // TODO add your handling code here:
           //<name><id>
           //Get artist
           if(slctSubArtist.getSelectedItem().toString().equals("Select Sub Artists"))
               return;
           Artist chosenSubArt = (Artist) slctSubArtist.getSelectedItem();
           
                artistNames.put(chosenSubArt.getID(), chosenSubArt);
           
           
        
        slctedArtists.setListData(artistNames.values().toArray(new Artist[artistNames.size()]));
        if(!slctedArtists.isVisible())
        slctedArtists.setVisible(true);
        if(!jScrollPane1.isVisible())
        jScrollPane1.setVisible(true);
        btnCreate.setVisible(true);
        iWindow.update();
    }//GEN-LAST:event_addArtistActionPerformed

    private void slctSubArtistItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_slctSubArtistItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() != ItemEvent.SELECTED) 
        return;
            Object item = evt.getItem();
            if (item.toString().equals("Select Sub Artists")) {
                   pnlArtistSelected.setVisible(false);
                return;
            }
            Artist chosenSub = (Artist) slctSubArtist.getSelectedItem();
          
                pnlArtistSelected.setVisible(true);
                
                txtBio.setText(chosenSub.getBiography());
                
                lblArtistStageName.setText(chosenSub.getStageName());
                
                if(rePaint) {
                    rePaint = false;
                    slctSubArtistItemStateChanged(evt);
                    
                }
                
            
        
       
        iWindow.update();
        
    }//GEN-LAST:event_slctSubArtistItemStateChanged

    private void slctSubArtistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slctSubArtistActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_slctSubArtistActionPerformed

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateActionPerformed
       
            // TODO add your handling code here:
            iMuzaMusic.log("Creating Show..");
            
            String LocationID = ((Location)slctLocation.getSelectedItem()).getLocationID();
            String MainArtist = ((Artist)slctArtist.getSelectedItem()).getID();
            Timestamp dateCreated = new Timestamp(new Date().getTime());
            SimpleDateFormat df = new SimpleDateFormat("d/M/Y");
            Integer hrs = Integer.parseInt(slctTime.getSelectedItem().toString().substring(0, 2));
            Date dt = jXDatePicker1.getDate();
            dt.setHours(hrs);
            Timestamp ts = new Timestamp(dt.getTime());
            String ticketPrice = Double.parseDouble(fldPrice.getText())+"";
            String minAge = Integer.parseInt(fldAge.getText())+"";
            
            List<Artist> subArtists = new ArrayList<Artist>();
            ListModel lm = slctedArtists.getModel();
            
            for(int i=0; i<lm.getSize();i++)
                subArtists.add((Artist)lm.getElementAt(i));
            ShowController.CreateShow(ts, ticketPrice, minAge, LocationID, MainArtist, dateCreated, subArtists);
           
            
            //show success dialog
            dispose();
            iWindow.openWin(new frmCreateShow());

    }//GEN-LAST:event_btnCreateActionPerformed

    private void fldAgeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldAgeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldAgeActionPerformed

    private void slctSubArtistInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_slctSubArtistInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_slctSubArtistInputMethodTextChanged

    private void fldPriceFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fldPriceFocusLost
        checkTicketPrice();
    }//GEN-LAST:event_fldPriceFocusLost

    private void fldAgeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fldAgeFocusLost
        checkMinimalAge();
    }//GEN-LAST:event_fldAgeFocusLost

    /**
     * To be added - check minimal age
     */
    private void checkMinimalAge(){
        
    }
    
    /**
     * To Be Implented - Check ticket price
     */
    private void checkTicketPrice(){
        
    }
    
    
    public void updateData(){
        iWindow.update();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addArtist;
    private javax.swing.JButton btnCreate;
    private javax.swing.JTextField fldAge;
    private javax.swing.JTextField fldPrice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker1;
    private javax.swing.JLabel lblAddress;
    private javax.swing.JLabel lblArtistStageName;
    private javax.swing.JPanel pnlArtistSelected;
    private javax.swing.JPanel pnlSubArtists;
    private javax.swing.JComboBox<Artist> slctArtist;
    private javax.swing.JComboBox<Location> slctLocation;
    private javax.swing.JComboBox<Artist> slctSubArtist;
    private javax.swing.JComboBox<String> slctTime;
    private javax.swing.JList<Artist> slctedArtists;
    private javax.swing.JLabel txtBio;
    private javax.swing.JLabel txtLocation;
    private javax.swing.JLabel txtMinimum;
    private javax.swing.JLabel txtPrice;
    private javax.swing.JLabel txtSign;
    private javax.swing.JLabel txtSlctArtist;
    // End of variables declaration//GEN-END:variables
}
