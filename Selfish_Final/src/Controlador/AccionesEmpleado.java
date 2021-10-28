
package Controlador;

import Principal.Main;
import DAO.EmpleadoCRUD;
import DAO.MostrarConsulta;
import Modelo.Empleado;
import static Principal.Main.e1;
import Vistas.Empleados;
import Vistas.ListaEmpleado;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class AccionesEmpleado implements ActionListener{
    private Empleados e1;
    Empleado EM;
    int dato=0;
    int x=0;
    public AccionesEmpleado(Empleados e1){
        this.e1=e1;
        this.e1.btnRegistrarEmpleado.addActionListener(this);
        this.e1.btnBuscarEmpleado.addActionListener(this);
        this.e1.btnModificarEmpleado.addActionListener(this);
        this.e1.btnEliminarEmpleado.addActionListener(this);
        this.e1.btnNuevoEmpleado.addActionListener(this);
        this.e1.btnMostrarEmpleado.addActionListener(this);
        
    }
    public void actionPerformed(ActionEvent e){
        if(e1.btnRegistrarEmpleado==e.getSource()){
            String cod_emp=e1.txtCodEmpleado.getText();
            String ape_emp=e1.txtApellidoEmpleado.getText();
            String nom_emp=e1.txtNombreEmpleado.getText();
            String sex_emp=e1.cbxSexoEmpleado.getSelectedItem().toString();
            String car_emp=e1.cbxCargo.getSelectedItem().toString();
            String dni_emp=e1.txtDNIEmpleado.getText();
            String tel_emp=e1.txtTelefonoEmpleado.getText();
            String email_emp=e1.txtEmailEmpleado.getText();
            
            Empleado E=new Empleado(cod_emp,ape_emp,nom_emp,sex_emp,car_emp,dni_emp,tel_emp,email_emp);
            Validar();
            if(x==0){
            EmpleadoCRUD ec= new EmpleadoCRUD();
            ec.Create(E);
            //Limpiar cuadros de texto
            //Codigo automatico
            dato=ec.ListarCantidadEMpleado()+1;            
            e1.txtCodEmpleado.setText("EMP"+dato);
            Limpiar();
            }
        }
        else if(e1.btnBuscarEmpleado==e.getSource()){
            String COD_EMP=JOptionPane.showInputDialog("Ingrese Codigo del empleado, que solicita buscar");
            EmpleadoCRUD ec= new EmpleadoCRUD();
            EM=ec.Read(COD_EMP);
            if(EM!=null){
                e1.txtCodEmpleado.setText(EM.cod_emp);
                e1.txtApellidoEmpleado.setText(EM.ape_emp);
                e1.txtNombreEmpleado.setText(EM.nom_emp);
                e1.cbxSexoEmpleado.setSelectedItem(EM.sex_emp);
                e1.cbxCargo.setSelectedItem(EM.car_emp);
                e1.txtDNIEmpleado.setText(EM.dni_emp);
                e1.txtTelefonoEmpleado.setText(EM.tel_emp);
                e1.txtEmailEmpleado.setText(EM.email_emp);
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del empleado no existe");
            }
        }
        else if(e1.btnModificarEmpleado==e.getSource()){
            String cod_emp=e1.txtCodEmpleado.getText();
            String ape_emp=e1.txtApellidoEmpleado.getText();
            String nom_emp=e1.txtNombreEmpleado.getText();
            String sex_emp=e1.cbxSexoEmpleado.getSelectedItem().toString();
            String car_emp=e1.cbxCargo.getSelectedItem().toString();
            String dni_emp=e1.txtDNIEmpleado.getText();
            String tel_emp=e1.txtTelefonoEmpleado.getText();
            String email_emp=e1.txtEmailEmpleado.getText();
            
            EM=new Empleado(cod_emp,ape_emp,nom_emp,sex_emp,car_emp,dni_emp,tel_emp,email_emp);

            EmpleadoCRUD ec= new EmpleadoCRUD();
            ec.Update(EM);

        }
        else if(e1.btnNuevoEmpleado==e.getSource()){
            Limpiar();
            //CODIGO AUTOMATICO
            EmpleadoCRUD ec= new EmpleadoCRUD();            
            dato=ec.ListarCantidadEMpleado()+1;            
            e1.txtCodEmpleado.setText("EMP"+dato);
        }
        else if(e1.btnEliminarEmpleado==e.getSource()){
            int reply=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar?","CONFIRMAR",JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_NO_OPTION){
                EmpleadoCRUD ec= new EmpleadoCRUD();
                ec.Delete(EM.cod_emp);
            }
        }
        else if(e1.btnMostrarEmpleado==e.getSource()){
            Main.e2=new ListaEmpleado();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaEmpleado(Main.e2.TablaEmpleados);
            Main.e2.setVisible(true);
            Main.e2.setLocationRelativeTo(null);
        }
        
    }
    
    public void Limpiar(){
        e1.txtCodEmpleado.setText("");
        e1.txtApellidoEmpleado.setText("");
        e1.txtNombreEmpleado.setText("");
        e1.cbxSexoEmpleado.setSelectedItem(0);
        e1.cbxCargo.setSelectedItem(0);
        e1.txtDNIEmpleado.setText("");
        e1.txtTelefonoEmpleado.setText("");
        e1.txtEmailEmpleado.setText("");
    }
    
    public void Validar(){
    try{
        if(!e1.txtCodEmpleado.getText().isEmpty()){
            if(!e1.txtApellidoEmpleado.getText().isEmpty()){
                if(!e1.txtNombreEmpleado.getText().isEmpty()){
                    if(!e1.txtDNIEmpleado.getText().isEmpty()){
                        if(!e1.txtTelefonoEmpleado.getText().isEmpty()){
                            if(!e1.txtEmailEmpleado.getText().isEmpty()){         
                            }else
                                {JOptionPane.showMessageDialog(this.e1,"Ingrese el Email", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                                x=1;}  
                        }else
                            {JOptionPane.showMessageDialog(this.e1,"Ingrese la Telefono", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                            x=1;}  
                    }else
                        {JOptionPane.showMessageDialog(this.e1,"Ingrese el DNI", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                        x=1;}                 
                }else
                    {JOptionPane.showMessageDialog(this.e1,"Ingrese los Nombres", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                    x=1;}            
            }else
                {JOptionPane.showMessageDialog(this.e1,"Ingrese los Apellidos", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                x=1;}
        }else
            {JOptionPane.showMessageDialog(this.e1,"Ingrese el Codigo", "mensaje de error", JOptionPane.ERROR_MESSAGE);
            x=1;}            
           
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(this.e1,e.getMessage());
    }
    }    
    
}
