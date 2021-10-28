
package Controlador;

import DAO.ClienteCRUD;
import DAO.MostrarConsulta;
import Modelo.Cliente;
import Principal.Main;
import Vistas.Clientes;
import Vistas.ListaCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AccionesCliente implements ActionListener {
    private Clientes c1;
    Cliente CL;
    int x;
    public AccionesCliente(Clientes c1){
        this.c1=c1;
        this.c1.jbtnRegistrarCliente.addActionListener(this);
        this.c1.jbtnBuscarCliente.addActionListener(this);
        this.c1.jbtnModificarCliente.addActionListener(this);
        this.c1.jbtnEliminarCliente.addActionListener(this);
        this.c1.jbtnNuevoCliente.addActionListener(this);
        this.c1.jbtnMostrarClientes.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(c1.jbtnRegistrarCliente==e.getSource()){
            String dni_cli=c1.txtDNICliente.getText();
            String nom_cli=c1.txtNombreCliente.getText();
            String ape_cli=c1.txtApellidoCliente.getText();
            String sex_cli=c1.cbxSexoCliente.getSelectedItem().toString();
            String tel_cli=c1.txtTelefonoCliente.getText();
            String dir_cli=c1.txtDireccionCliente.getText();
            String email_cli=c1.txtEmailCliente.getText();
            
            Cliente C=new Cliente(dni_cli,nom_cli,ape_cli,sex_cli,tel_cli,dir_cli,email_cli);
            Validar();
            if(x==0){
            ClienteCRUD ec= new ClienteCRUD();
            
            ec.Create(C);
            
            Limpiar();
            }
        }
        else if(c1.jbtnBuscarCliente==e.getSource()){
            String DNI=JOptionPane.showInputDialog("Ingrese el DNI del cliente, que solicita buscar");
            ClienteCRUD ec= new ClienteCRUD();
            CL=ec.Read(DNI);
            if(CL!=null){
                c1.txtDNICliente.setText(CL.dni_cli);
                c1.txtNombreCliente.setText(CL.nom_cli);
                c1.txtApellidoCliente.setText(CL.ape_cli);
                c1.cbxSexoCliente.setSelectedItem(CL.sex_cli);
                c1.txtTelefonoCliente.setText(CL.tel_cli);
                c1.txtDireccionCliente.setText(CL.dir_cli);
                c1.txtEmailCliente.setText(CL.email_cli);
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del cliente no existe");
            }
        }
        else if(c1.jbtnModificarCliente==e.getSource()){
            String dni_cli=c1.txtDNICliente.getText();
            String nom_cli=c1.txtNombreCliente.getText();
            String ape_cli=c1.txtApellidoCliente.getText();
            String sex_cli=c1.cbxSexoCliente.getSelectedItem().toString();
            String tel_cli=c1.txtTelefonoCliente.getText();
            String dir_cli=c1.txtDireccionCliente.getText();
            String email_cli=c1.txtEmailCliente.getText();
            
            CL=new Cliente(dni_cli,nom_cli,ape_cli,sex_cli,tel_cli,dir_cli,email_cli);
            ClienteCRUD ec= new ClienteCRUD();
            ec.Update(CL);
        }
        else if(c1.jbtnEliminarCliente==e.getSource()){
            int reply=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar?","CONFIRMAR",JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_NO_OPTION){
                ClienteCRUD ec= new ClienteCRUD();
                ec.Delete(CL.dni_cli);
            }
        }
        else if(c1.jbtnNuevoCliente==e.getSource()){
            Limpiar();
        }
        else if(c1.jbtnMostrarClientes==e.getSource()){
            Main.c2=new ListaCliente();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaCliente(Main.c2.TablaCliente);
            Main.c2.setVisible(true);
            Main.c2.setLocationRelativeTo(null);
        }
    }
    public void Limpiar(){
        c1.txtDNICliente.setText("");
        c1.txtNombreCliente.setText("");
        c1.txtApellidoCliente.setText("");
        c1.cbxSexoCliente.setSelectedItem(0);
        c1.txtTelefonoCliente.setText("");
        c1.txtDireccionCliente.setText("");
        c1.txtEmailCliente.setText("");
    }
    
    
    public void Validar(){
    try{
        if(!c1.txtDNICliente.getText().isEmpty()){
            if(!c1.txtNombreCliente.getText().isEmpty()){
                if(!c1.txtApellidoCliente.getText().isEmpty()){
                    if(!c1.txtTelefonoCliente.getText().isEmpty()){
                        if(!c1.txtDireccionCliente.getText().isEmpty()){
                            if(!c1.txtEmailCliente.getText().isEmpty()){         
                            }else
                                {JOptionPane.showMessageDialog(this.c1,"Ingrese el Email", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                                x=1;}  
                        }else
                            {JOptionPane.showMessageDialog(this.c1,"Ingrese la Direccion", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                            x=1;}  
                    }else
                        {JOptionPane.showMessageDialog(this.c1,"Ingrese el Telefono", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                        x=1;}                 
                }else
                    {JOptionPane.showMessageDialog(this.c1,"Ingrese los Apellidos", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                    x=1;}            
            }else
                {JOptionPane.showMessageDialog(this.c1,"Ingrese los Nombres", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                x=1;}
        }else
            {JOptionPane.showMessageDialog(this.c1,"Ingrese el DNI", "mensaje de error", JOptionPane.ERROR_MESSAGE);
            x=1;}            
           
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(this.c1,e.getMessage());
    }
    }    
}
