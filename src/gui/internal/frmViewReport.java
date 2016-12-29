/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.internal;

import ex2design.iMuzaMusic;
import java.awt.event.ItemEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.NULL;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JRViewer;
/**
 *
 * @author nisan
 */
public class frmViewReport extends javax.swing.JInternalFrame {

    /**
     * Creates new form frmViewReport
     */
    public frmViewReport() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slctYear = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        slctYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Year", "2016", "2015", "2014"}));
        slctYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                slctYearItemStateChanged(evt);
            }
        });
        getContentPane().add(slctYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 190, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Welcome to iMuzaMusic Report Generator.");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 310, -1));

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("To initiate a report, please select a year");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void slctYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_slctYearItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() != ItemEvent.SELECTED) 
            return;
            Object item = evt.getItem();
            if (item.equals("Select Year")) 
                return;
            
        iMuzaMusic.log("Exporting report for year "+item.toString());
        
   try {
       
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try{
            
                JasperPrint print = JasperFillManager.fillReport(getClass()
                        .getResourceAsStream("/ex2design/reports/report1.jasper"), 
                        new HashMap());
                JFrame frame = new JFrame("Customer Orders Report");
                frame.getContentPane().add(new JRViewer(print));
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                frame.pack();
                frame.setVisible(true);
            } catch (JRException | NullPointerException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }        
        
    }//GEN-LAST:event_slctYearItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> slctYear;
    // End of variables declaration//GEN-END:variables
}
