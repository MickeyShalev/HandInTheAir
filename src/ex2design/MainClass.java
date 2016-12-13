/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex2design;

import gui.main.LoginGui;
import gui.main.iWindow;
import javax.swing.JFrame;
import ex2design.utilities.EAuth;

/**
 *
 * @author Administrator
 */
public class MainClass {
      public static void main(String[] args) {
          
            //Initiate Main Project
            iMuzaMusic project = new iMuzaMusic();
            //Initiate GUI
            //JFrame LoginGui = new LoginGui();
            iMuzaMusic.log("Opening Login Window through iWindow");
            iWindow.openLogin();
            
        }
      }

