/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package siomaisupplyapp;

import javax.swing.JFrame;
import siomaisupplyapp.Frames.LoginFrame;

/**
 *
 * @author Josh
 */
public class SiomaiSupplyApp {
     
    // !!! CHANGE SETTINGS HERE !!!
    private static final int PORT = 3306;
    private static final String DB = "kittenrescuedb";
    
    
    public static Connect c;
    
    public static void showLogin(JFrame from){
        LoginFrame lf = new LoginFrame();
        lf.setVisible(true);
        lf.setLocationRelativeTo(from);
        from.dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        // Start Connection
        c = new Connect("jdbc:mysql://localhost:" + PORT + "/" + DB);
        
        (new LoginFrame()).setVisible(true);
    }
    
}
