/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.main;

import ex2design.iMuzaMusic;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Administrator
 */
public class LoginGui extends javax.swing.JFrame {

    public static boolean skipLogin=true;
    public static String skipID="AG001";
    public static String skipPW="asf230g2";
    /**
     * Creates new form LoginGui
     */
    public LoginGui(){
        setUndecorated(true);
        initComponents();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(true);
            }
        });
        setLocationRelativeTo(null);
        fldLogin.setCaretPosition(0);
        if(skipLogin){
            try {
                //Skip the login GUI
                fldLogin.setText(skipID);
                fldPassword.setText(skipPW);
                submitForm();
            } catch (SQLException ex) {
                Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
            }
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

        fldPassword = new javax.swing.JTextField();
        fldLogin = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bg = new javax.swing.JLabel();
        btnSubmit = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fldPassword.setBackground(new Color(0,0,0,0));
        fldPassword.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        fldPassword.setForeground(new java.awt.Color(153, 153, 153));
        fldPassword.setText("Password");
        fldPassword.setBorder(null);
        fldPassword.setOpaque(false);
        fldPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldPasswordActionPerformed(evt);
            }
        });
        fldPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fldPasswordKeyPressed(evt);
            }
        });
        getContentPane().add(fldPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 270, 400, 40));

        fldLogin.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        fldLogin.setForeground(new java.awt.Color(153, 153, 153));
        fldLogin.setText("Username");
        fldLogin.setBorder(null);
        fldLogin.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                fldLoginCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        fldLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fldLoginActionPerformed(evt);
            }
        });
        fldLogin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fldLoginPropertyChange(evt);
            }
        });
        fldLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fldLoginKeyPressed(evt);
            }
        });
        getContentPane().add(fldLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 210, 400, 40));

        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 490, 110, 110));

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/gui/images/login.png"))); // NOI18N
        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnSubmit.setText("jButton1");
        btnSubmit.setEnabled(false);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setFocusable(false);
        btnSubmit.setRequestFocusEnabled(false);
        btnSubmit.setRolloverEnabled(false);
        btnSubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnSubmitMouseClicked(evt);
            }
        });
        getContentPane().add(btnSubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 400, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fldLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldLoginActionPerformed
        // TODO add your handling code here:

 
    }//GEN-LAST:event_fldLoginActionPerformed

    private void fldPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fldPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fldPasswordActionPerformed

    private void btnSubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSubmitMouseClicked
        try {
            // TODO add your handling code here:
            submitForm();
        } catch (SQLException ex) {
            Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSubmitMouseClicked

    private void fldLoginCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_fldLoginCaretPositionChanged
        // TODO add your handling code here:

    }//GEN-LAST:event_fldLoginCaretPositionChanged

    private void fldLoginPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fldLoginPropertyChange
        // TODO add your handling code here:
 
    }//GEN-LAST:event_fldLoginPropertyChange

    private void fldLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fldLoginKeyPressed
        // TODO add your handling code here:
        if(fldLogin.getText().equals("Username"))
            fldLogin.setText("");
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                submitForm();
            } catch (SQLException ex) {
                Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }//GEN-LAST:event_fldLoginKeyPressed

    private void fldPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fldPasswordKeyPressed
        // TODO add your handling code here:
        if(fldPassword.getText().equals("Password"))
            fldPassword.setText("");
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                submitForm();
            } catch (SQLException ex) {
                Logger.getLogger(LoginGui.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_fldPasswordKeyPressed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        iMuzaMusic.log("Exiting application...");
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginGui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
 
                    new LoginGui().setVisible(true);

            }
        });
        
        
    }
    
    public void submitForm() throws SQLException{
        iMuzaMusic.log("Submitted");
        iMuzaMusic.log("User: "+fldLogin.getText()+" Password: "+fldPassword.getText());
        
        if(iMuzaMusic.logIn(fldLogin.getText(), fldPassword.getText())){
        //Open main gui if successfull    
            setVisible(false);
            
            iMuzaMusic.log("Initiating main UI");
            
            MainGui tmp = new MainGui();
            tmp.setVisible(true);
        }
        
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JButton btnSubmit;
    private javax.swing.JTextField fldLogin;
    private javax.swing.JTextField fldPassword;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
