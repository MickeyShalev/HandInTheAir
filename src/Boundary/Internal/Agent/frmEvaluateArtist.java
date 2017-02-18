/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary.Internal.Agent;

import Controller.Artist.EvaluationsController;
import Entity.Artist;
import java.awt.Color;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author nisan
 */
public class frmEvaluateArtist extends javax.swing.JFrame {
    Artist artist = null;
    /**
     * Creates new form NewJFrame
     */
    public frmEvaluateArtist(Artist artist, List<Artist> unEvaluated, List<Artist> evaluated) {
        this.artist = artist;
        setUndecorated(true);
        setLocationRelativeTo(null);
        initComponents();
        DefaultListModel lm = new DefaultListModel();
        jList1.setModel(lm);
        for(Artist a : unEvaluated){
            lm.addElement(a);
            //EvaluatedJList1.getModel().addElement(a);
            
        }
        DefaultListModel lm2 = new DefaultListModel();
        jList2.setModel(lm2);
        for(Artist a : evaluated)
            lm2.addElement(a);
            
        
        
        
        
        
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblUnEvaluate = new javax.swing.JLabel();
        lblEvaluate = new javax.swing.JLabel();
        lblApply = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(null);

        lblUnEvaluate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblUnEvaluateMouseClicked(evt);
            }
        });
        getContentPane().add(lblUnEvaluate);
        lblUnEvaluate.setBounds(350, 260, 100, 50);

        lblEvaluate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEvaluateMouseClicked(evt);
            }
        });
        getContentPane().add(lblEvaluate);
        lblEvaluate.setBounds(350, 200, 100, 50);

        lblApply.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblApplyMouseClicked(evt);
            }
        });
        getContentPane().add(lblApply);
        lblApply.setBounds(320, 450, 170, 40);

        jScrollPane2.setBackground(new java.awt.Color(0, 0, 0));

        jList2.setBackground(new java.awt.Color(0, 0, 0));
        jList2.setForeground(new java.awt.Color(255, 255, 255));
        jList2.setModel(new DefaultListModel());
        jScrollPane2.setViewportView(jList2);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(550, 180, 170, 130);

        jScrollPane1.setBackground(new java.awt.Color(0, 0, 0));

        jList1.setBackground(new java.awt.Color(0, 0, 0));
        jList1.setForeground(new java.awt.Color(255, 255, 255));
        jList1.setModel(new DefaultListModel());
        jScrollPane1.setViewportView(jList1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(90, 180, 170, 130);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Boundary/Images/artist_evaluations.png"))); // NOI18N
        getContentPane().add(bg);
        bg.setBounds(0, 0, 800, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblUnEvaluateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblUnEvaluateMouseClicked
        // TODO add your handling code here:
         DefaultListModel lm = (DefaultListModel) jList1.getModel();
        DefaultListModel lm2 = (DefaultListModel) jList2.getModel();
        try{
        Artist a = (Artist) lm2.getElementAt(jList2.getSelectedIndex());
        lm2.removeElement(a);
        lm.addElement(a);
        }
        catch(Exception e){
           
        }
    }//GEN-LAST:event_lblUnEvaluateMouseClicked

    private void lblEvaluateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEvaluateMouseClicked
        // TODO add your handling code here:
        // TODO add your handling code here:
         DefaultListModel lm = (DefaultListModel) jList1.getModel();
        DefaultListModel lm2 = (DefaultListModel) jList2.getModel();
        try{
        Artist a = (Artist) lm.getElementAt(jList1.getSelectedIndex());
        lm.removeElement(a);
        lm2.addElement(a);
        }
        catch(Exception e){
           
        }
    }//GEN-LAST:event_lblEvaluateMouseClicked

    private void lblApplyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblApplyMouseClicked
        // TODO add your handling code here:
        DefaultListModel lm = (DefaultListModel) jList2.getModel();
        EvaluationsController.wipeEvaluations(artist);
        for(int i=0; i<jList2.getModel().getSize(); i++)
            EvaluationsController.addEvaluation(artist, (Artist) lm.getElementAt(i));
        
        dispose();
    }//GEN-LAST:event_lblApplyMouseClicked

 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bg;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblApply;
    private javax.swing.JLabel lblEvaluate;
    private javax.swing.JLabel lblUnEvaluate;
    // End of variables declaration//GEN-END:variables
}