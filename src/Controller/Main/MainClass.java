/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller.Main;

import Boundary.Main.LoginGui;
import Boundary.Main.iWindow;
import javax.swing.JFrame;
import Entity.EAuth;

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

