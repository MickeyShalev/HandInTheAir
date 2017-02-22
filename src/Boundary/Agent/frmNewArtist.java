/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary.Agent;

import Boundary.Main.iWindow;
import Controller.Agent.ManageController;
import Controller.Main.iMuzaMusic;
import Entity.Artist;
import Entity.EArtistStatus;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author Administrator
 */
public class frmNewArtist extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmNewArtist
     */
    public frmNewArtist() {
        initComponents();
        lblID.setText(Controller.Agent.ManageController.getNewArtistID());
        lblGreeting.setText("Dear "+iMuzaMusic.getLoggedUser().getFirstName()+", please use the form below to add an artist to your ranks.");
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAdd = new javax.swing.JPanel();
        lblID = new javax.swing.JLabel();
        lblStudioID = new javax.swing.JLabel();
        lblStudioName = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblAddr = new javax.swing.JLabel();
        lbldesc = new javax.swing.JLabel();
        lblMailError = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        tfEmail = new javax.swing.JTextField();
        lblAddrError = new javax.swing.JLabel();
        lblNameError = new javax.swing.JLabel();
        tfaddr = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        lblPhoneError = new javax.swing.JLabel();
        stDesc = new javax.swing.JLabel();
        stStage = new javax.swing.JLabel();
        stEmail = new javax.swing.JLabel();
        stFB = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        lblPath = new javax.swing.JLabel();
        errFile = new javax.swing.JLabel();
        imgDisplay = new javax.swing.JLabel();
        vIcon = new javax.swing.JLabel();
        xIcon = new javax.swing.JLabel();
        lblGreeting = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        getContentPane().setLayout(null);

        pnlAdd.setBackground(new Color(255,255,255,60));
        pnlAdd.setOpaque(false);
        pnlAdd.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblID.setForeground(new java.awt.Color(255, 255, 255));
        lblID.setText("Studio ID");
        pnlAdd.add(lblID, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 170, -1));

        lblStudioID.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStudioID.setForeground(new java.awt.Color(255, 255, 255));
        lblStudioID.setText("Artist ID");
        pnlAdd.add(lblStudioID, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 110, -1));

        lblStudioName.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblStudioName.setForeground(new java.awt.Color(255, 255, 255));
        lblStudioName.setText("Stage Name");
        pnlAdd.add(lblStudioName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 90, 20));

        lblEmail.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(255, 255, 255));
        lblEmail.setText("Email");
        pnlAdd.add(lblEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 50, -1));

        lblAddr.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblAddr.setForeground(new java.awt.Color(255, 255, 255));
        lblAddr.setText("Facebook URL");
        pnlAdd.add(lblAddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 100, -1));

        lbldesc.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbldesc.setForeground(new java.awt.Color(255, 255, 255));
        lbldesc.setText("Decription");
        pnlAdd.add(lbldesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, -1, -1));

        lblMailError.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblMailError.setForeground(new java.awt.Color(255, 0, 51));
        pnlAdd.add(lblMailError, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 70, 360, 20));

        tfFirstName.setText("Enter Stage Name");
        tfFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfFirstNameFocusLost(evt);
            }
        });
        tfFirstName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfFirstNameActionPerformed(evt);
            }
        });
        pnlAdd.add(tfFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, 190, -1));

        tfEmail.setText("Enter Email");
        tfEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfEmailFocusLost(evt);
            }
        });
        pnlAdd.add(tfEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 190, -1));

        lblAddrError.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblAddrError.setForeground(new java.awt.Color(255, 0, 51));
        pnlAdd.add(lblAddrError, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 100, 370, 20));

        lblNameError.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblNameError.setForeground(new java.awt.Color(255, 0, 51));
        pnlAdd.add(lblNameError, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 360, 20));

        tfaddr.setText("Facebook Profile URL");
        tfaddr.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tfaddrFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfaddrFocusLost(evt);
            }
        });
        pnlAdd.add(tfaddr, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 190, -1));

        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Calibri", 0, 10)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("Please describe the artists' Biography here");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextArea1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextArea1FocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(jTextArea1);

        pnlAdd.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 130, 190, 80));

        lblPhoneError.setFont(new java.awt.Font("Tahoma", 3, 12)); // NOI18N
        lblPhoneError.setForeground(new java.awt.Color(255, 0, 51));
        pnlAdd.add(lblPhoneError, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 370, 20));
        pnlAdd.add(stDesc, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 130, 40, 20));
        pnlAdd.add(stStage, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 40, 50, 20));
        pnlAdd.add(stEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 70, 40, 20));
        pnlAdd.add(stFB, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 40, 20));

        jButton1.setText("Upload Image");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        pnlAdd.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, -1, -1));

        lblPath.setForeground(new java.awt.Color(255, 255, 255));
        pnlAdd.add(lblPath, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, 570, 20));
        pnlAdd.add(errFile, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 230, 30, 20));
        pnlAdd.add(imgDisplay, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 260, 190, 120));

        getContentPane().add(pnlAdd);
        pnlAdd.setBounds(10, 40, 780, 390);

        vIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Images/v.png"))); // NOI18N
        vIcon.setVisible(false);
        getContentPane().add(vIcon);
        vIcon.setBounds(750, 340, 48, 16);

        xIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Images/x.png"))); // NOI18N
        xIcon.setVisible(false);
        getContentPane().add(xIcon);
        xIcon.setBounds(750, 320, 16, 16);

        lblGreeting.setForeground(new java.awt.Color(255, 255, 255));
        lblGreeting.setText("greetingtext");
        getContentPane().add(lblGreeting);
        lblGreeting.setBounds(20, 10, 700, 14);

        jButton2.setText("Add Artist");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(20, 460, 100, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFirstNameFocusGained
        if (tfFirstName.getText().equals("Enter Stage Name"))
        tfFirstName.setText("");
    }//GEN-LAST:event_tfFirstNameFocusGained

    private void tfFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfFirstNameFocusLost
        String sn = tfFirstName.getText();
        if (sn.length() < 3)
            lblNameError.setText("Name is too short");
        else  if (!Controller.Validators.CharValidator.isWord(sn))
            lblNameError.setText("Name must contain at least 3 valid letters");
        else lblNameError.setText("");
        
        if(lblNameError.getText().equals("")) //no error
            stStage.setIcon(vIcon.getIcon());
        else stStage.setIcon(xIcon.getIcon());
        
        iWindow.update();
        
        
    }//GEN-LAST:event_tfFirstNameFocusLost

    private void tfEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusGained
        if (tfEmail.getText().equals("Enter Email"))
        tfEmail.setText("");
    }//GEN-LAST:event_tfEmailFocusGained

    private void tfEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfEmailFocusLost
        String mail = tfEmail.getText();
        if (!Controller.Validators.EmailValidator.validateEmail(mail))
            lblMailError.setText("Please enter valid email - username@domain.host");
         else lblMailError.setText("");

        if(lblMailError.getText().equals("")) //no error
            stEmail.setIcon(vIcon.getIcon());
        else stEmail.setIcon(xIcon.getIcon());
        iWindow.update();

    }//GEN-LAST:event_tfEmailFocusLost

    private void tfaddrFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfaddrFocusGained
        if (tfaddr.getText().equals("Facebook Profile URL"))
        tfaddr.setText("");
    }//GEN-LAST:event_tfaddrFocusGained

    private void tfaddrFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfaddrFocusLost
        String sn = tfaddr.getText();
        if(!sn.contains("facebook.com"))
            lblAddrError.setText("URL is Invalid.");
        else if (sn.length() < 5)
            lblAddrError.setText("Address is too short");
        else lblAddrError.setText("");
        
        if(lblAddrError.getText().equals("")) //no error
            stFB.setIcon(vIcon.getIcon());
        else stFB.setIcon(xIcon.getIcon());
        
        iWindow.update();
    }//GEN-LAST:event_tfaddrFocusLost

    private void jTextArea1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusGained
        if (jTextArea1.getText().equals("Please describe the artists' Biography here"))
        jTextArea1.setText("");
    }//GEN-LAST:event_jTextArea1FocusGained

    private void jTextArea1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextArea1FocusLost
        
        if (jTextArea1.getText() == null || jTextArea1.getText().length() < 1)
        jTextArea1.setText("Not Available");

         if(jTextArea1.getText().equals("Not Available")) //no error
            stDesc.setIcon(xIcon.getIcon());
        else stDesc.setIcon(vIcon.getIcon());
        
        iWindow.update();

    }//GEN-LAST:event_jTextArea1FocusLost

    private void tfFirstNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfFirstNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfFirstNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File f = chooser.getSelectedFile();
        try {
            long size = Files.size(f.toPath()) / 1000;
            if(size>2048){
                errFile.setIcon(xIcon.getIcon());
                lblPath.setText("Sorry, but it seems like you are trying to upload a large file.");
                iWindow.update();
                return;
            }
            iMuzaMusic.log("Attempting to upload file size: "+(Files.size(f.toPath())) / 1000+"KB" );
          
        } catch (IOException ex) {
            Logger.getLogger(frmNewArtist.class.getName()).log(Level.SEVERE, null, ex);
        }
        String path = f.getAbsolutePath();
        lblPath.setText(path);
        
        String extension = "";

        int i = path.lastIndexOf('.');
        int p = Math.max(path.lastIndexOf('/'), path.lastIndexOf('\\'));

        if (i > p) {
            extension = path.substring(i+1);
        }
        
        if(!extension.contains("png") && !extension.contains("jpg") && !extension.contains("gif") && !extension.contains("bmp") && !extension.contains("jpeg")){
            errFile.setIcon(xIcon.getIcon());
            lblPath.setText("File type is incorrect. Please upload an image.");
            iWindow.update();
            return;
        }
        
        
        //Try creating the file
        File uploads = new File("./src/sources/uploads");
        iMuzaMusic.log("Upload dir: "+uploads.getAbsolutePath());
        File tmp = new File(uploads, lblID.getText()+"."+extension);
        iMuzaMusic.log("Tmp file: "+tmp.getAbsolutePath());
        
        try {
           Files.copy(f.toPath(), tmp.toPath(), REPLACE_EXISTING);
           errFile.setIcon(vIcon.getIcon());
           lblPath.setText("File successfully saved.");
        } catch (IOException ex) {
            Logger.getLogger(frmNewArtist.class.getName()).log(Level.SEVERE, null, ex);
            errFile.setIcon(xIcon.getIcon());
            lblPath.setText("Failed to save file.");
        }
        iMuzaMusic.log("Finished uploading. Short path: "+tmp.toPath());
        String shortIconPath = tmp.toPath().toString().replace("\\", "/").replace("/src", "");
        shortIconPath = shortIconPath.substring(1);
        iMuzaMusic.log("Short Icon Path: "+shortIconPath);
        imgDisplay.setIcon(new javax.swing.ImageIcon(getClass().getResource(shortIconPath))); // NOI18N
         
        iWindow.update();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        //Attempt to add an artist
        Artist a = new Artist(lblID.getText(), lbldesc.getText(), tfFirstName.getText(), lblAddr.getText() ,EArtistStatus.Active, lblEmail.getText());
        ManageController.createArtist(a);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel errFile;
    private javax.swing.JLabel imgDisplay;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel lblAddr;
    private javax.swing.JLabel lblAddrError;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGreeting;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblMailError;
    private javax.swing.JLabel lblNameError;
    private javax.swing.JLabel lblPath;
    private javax.swing.JLabel lblPhoneError;
    private javax.swing.JLabel lblStudioID;
    private javax.swing.JLabel lblStudioName;
    private javax.swing.JLabel lbldesc;
    private javax.swing.JPanel pnlAdd;
    private javax.swing.JLabel stDesc;
    private javax.swing.JLabel stEmail;
    private javax.swing.JLabel stFB;
    private javax.swing.JLabel stStage;
    private javax.swing.JTextField tfEmail;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfaddr;
    private javax.swing.JLabel vIcon;
    private javax.swing.JLabel xIcon;
    // End of variables declaration//GEN-END:variables
}