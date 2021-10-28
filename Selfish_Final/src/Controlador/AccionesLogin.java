/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import static Principal.Main.fp;
import Vistas.FrmPrincipal;
import Vistas.Login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Leila
 */
public class AccionesLogin implements ActionListener {
    private Login ls;
    public AccionesLogin(Login ls){
        this.ls=ls;
        ls.jbtnIngresar.addActionListener(this);
        ls.btnSalir.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
      if(ls.jbtnIngresar==e.getSource()){
       
        String usuario="selfish";
        String contraseña="admin";
        String Pass=new String (ls.Password.getPassword());
        
        if(ls.jtxtUsuario.getText().equalsIgnoreCase(usuario)&&Pass.equals(contraseña)){
            fp=new FrmPrincipal();
            fp.setVisible(true);
            ls.dispose();
            fp.setLocationRelativeTo(null);
            fp.setExtendedState(JFrame.MAXIMIZED_BOTH);
            AccionesPrincipal p=new AccionesPrincipal(fp);
        }
        else{
            JOptionPane.showMessageDialog(null,"El usuario o  contraseña son incorrectos","CONFIRMAR",JOptionPane.YES_NO_OPTION);
        }
        
      }
      else if(ls.btnSalir==e.getSource()){
          System.exit(0);
      }
        
    }
}
