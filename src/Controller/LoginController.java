/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Apresentacao.Login;
import Apresentacao.MainMenu;
import javax.swing.JOptionPane;


/**
 *
 * @author Carlos Ferreira - 4124376 - 5
 *         Letícia Valentini
 *         Jéssica Cestariolli
 *         Thiago Baldim
 *         Thiago Lima
 */

public class LoginController {
    Login loginGui;
    
    
    public LoginController(){
        loginGui = new Login();
    }
    
    
    public static void main(String args[]) {
    
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    
    public void MakeLogin(String User, char[] Password){
    		if ((User.equals("")) || (Password.length == 0)) {
                    JOptionPane.showMessageDialog(null, "Usuário ou senha em branco!", "Login", JOptionPane.INFORMATION_MESSAGE);
    		} else {
                    loginGui.dispose();
                    MainMenu mainMenu = new MainMenu();
                    mainMenu.setVisible(true);
    		}
    }
}