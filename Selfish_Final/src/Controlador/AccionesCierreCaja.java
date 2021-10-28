/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.MostrarConsulta;
import Modelo.CierreCaja;
import static Principal.Main.c1;
import Vistas.CierreCajas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AccionesCierreCaja implements ActionListener{
    private CierreCajas cc1;
    CierreCaja CC;
    int x;
    public AccionesCierreCaja(CierreCajas cc1){
        this.cc1=cc1;
        this.cc1.btnPedido.addActionListener(this);
        this.cc1.btnVenta.addActionListener(this);
        
    }

    public void actionPerformed(ActionEvent e){
        if(cc1.btnPedido==e.getSource()){
            ValidarDPedido();
            if(x==0){
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaCierrePedido(cc1.TablaPedidoFecha,cc1.txtPedido.getText());}   
        }
        else if(cc1.btnVenta==e.getSource()){
            ValidarDVenta();
            if(x==0){
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaCierreVenta(cc1.TablaCierreVenta,cc1.txtVenta.getText());}  
        }
    }
    public void ValidarDVenta(){
    try{
        if(!cc1.txtVenta.getText().isEmpty()){
        }else
            {JOptionPane.showMessageDialog(this.cc1,"Ingrese la fecha venta ", "mensaje de error", JOptionPane.ERROR_MESSAGE);
            x=1;}            
           
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(this.cc1,e.getMessage());
    }
    } 
    public void ValidarDPedido(){
    try{
        if(!cc1.txtPedido.getText().isEmpty()){
        }else
            {JOptionPane.showMessageDialog(this.cc1,"Ingrese la fecha del pedido", "mensaje de error", JOptionPane.ERROR_MESSAGE);
            x=1;}            
           
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(this.cc1,e.getMessage());
    }
    }  
}
