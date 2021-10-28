
package Controlador;

import DAO.ClienteCRUD;
import DAO.EmpleadoCRUD;
import DAO.MostrarConsulta;
import DAO.VentaRealizadaCRUD;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Pedido;
import Modelo.VentaRealizada;
import Vistas.VentasRealizadas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class AccionesVentasRealizada implements ActionListener{
    private VentasRealizadas vr1;
    VentaRealizada VRE;
    Empleado EM;
    Cliente CL;
    Pedido PE;
    DefaultTableModel mt;
    int dato=0;
    int x=0;
    public AccionesVentasRealizada(VentasRealizadas vr1){
        this.vr1=vr1;
        this.vr1.btnBuscarVenta.addActionListener(this);
        this.vr1.btnBuscarCliente.addActionListener(this);
        this.vr1.btnBuscarEmpleado.addActionListener(this);
        this.vr1.btnBuscarNumPed.addActionListener(this);
        this.vr1.btnRegistrarVenta.addActionListener(this);
        this.vr1.btnEliminarVenta.addActionListener(this);
        this.vr1.btnNuevo.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(vr1.btnBuscarVenta==e.getSource()){
            String NUM_VEN=JOptionPane.showInputDialog("Ingrese el numero de la venta, que solicita buscar");
            VentaRealizadaCRUD ec= new VentaRealizadaCRUD();
            VRE=ec.Read(NUM_VEN);
            if(VRE!=null){
                vr1.txtNumVenta.setText(VRE.num_ven);
                vr1.txtDNICliente.setText(VRE.dni_cli);
                vr1.txtNombreCli.setText(VRE.nom_cli);
                vr1.txtApellidoCli.setText(VRE.ape_cli);
                vr1.txtCodEmpleado.setText(VRE.cod_emp);
                vr1.txtApellidooEmp.setText(VRE.ape_emp);
                vr1.txtNombreEmp.setText(VRE.nom_emp);
                vr1.txtNumPedido.setText(VRE.num_ped);
                vr1.txtSubtotal.setText(String.valueOf(VRE.sub_total));
                vr1.txtIGV.setText(String.valueOf(VRE.igv));
                vr1.TXTtOTAL.setText(String.valueOf(VRE.total));
                /*MostrarConsulta mc = new MostrarConsulta();
                mc.MostrarPrecios(vr1.TablaPrecios,vr1.txtNumPedido.getText()); */ 
            }else{
                JOptionPane.showMessageDialog(null,"El numero de venta no existe");
            }
        }
        else if(vr1.btnBuscarEmpleado==e.getSource()){
            String COD_EMP=JOptionPane.showInputDialog("Ingrese codigo del empleado a buscar, que solicita buscar");
            EmpleadoCRUD ec=new EmpleadoCRUD();
            EM=ec.ReadEmpleado(COD_EMP);            
            if(EM!=null){                
                vr1.txtCodEmpleado.setText(EM.cod_emp);
                vr1.txtApellidooEmp.setText(EM.ape_emp);
                vr1.txtNombreEmp.setText(EM.nom_emp);            
            }else{
                JOptionPane.showMessageDialog(null,"El empleado no existe");
            }
        }
        else if(vr1.btnBuscarCliente==e.getSource()){
            String DNI_CLI=JOptionPane.showInputDialog("Ingrese DNI a buscar, que solicita buscar");
            ClienteCRUD ec=new ClienteCRUD();
            CL=ec.ReadCliente(DNI_CLI);
            if(CL!=null){              
                vr1.txtDNICliente.setText(CL.dni_cli);
                vr1.txtNombreCli.setText(CL.nom_cli);
                vr1.txtApellidoCli.setText(CL.ape_cli);
            }else{
                JOptionPane.showMessageDialog(null,"El cliente no existe");
            }
        }
        else if(vr1.btnBuscarNumPed==e.getSource()){
           String NUM_PED=JOptionPane.showInputDialog("Ingrese numero del pedido a buscar, que solicita buscar");
            VentaRealizadaCRUD ec= new VentaRealizadaCRUD();
            VRE=ec.ReadPrecio(NUM_PED);
            if(VRE!=null){                
               vr1.txtNumPedido.setText(VRE.num_ped);
               vr1.txtSubtotal.setText(String.valueOf(VRE.sub_total));
               vr1.txtIGV.setText(String.valueOf(VRE.igv));
               vr1.TXTtOTAL.setText(String.valueOf(VRE.total));
            }else{
                JOptionPane.showMessageDialog(null,"El numero del pedido no existe");
            }           
        }
        else if(vr1.btnRegistrarVenta==e.getSource()){
            String num_ven=vr1.txtNumVenta.getText();
            String dni_cli=vr1.txtDNICliente.getText();
            String cod_emp=vr1.txtCodEmpleado.getText();
            String num_ped=vr1.txtNumPedido.getText();
            double sub_total=Double.parseDouble(vr1.txtSubtotal.getText());
            double igv=Double.parseDouble(vr1.txtIGV.getText());
            double total=Double.parseDouble(vr1.TXTtOTAL.getText());
            
            VentaRealizada VR=new VentaRealizada(num_ven,dni_cli,cod_emp,num_ped,sub_total,igv,total);
            VentaRealizadaCRUD ec= new VentaRealizadaCRUD();
            ec.Create(VR);            
            Limpiar();
            //CONTADOR PARA EL CODIGO        
            dato=ec.ListarCantidadVenta()+1;            
            vr1.txtNumVenta.setText("VEN"+dato);                      
            Mostrar();            
        }
        else if(vr1.btnEliminarVenta==e.getSource()){
            int reply=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar?","CONFIRMAR",JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_NO_OPTION){
                VentaRealizadaCRUD ec= new VentaRealizadaCRUD();
                ec.Delete(VRE.num_ven);
            }
            Limpiar();
            //CONTADOR PARA EL CODIGO                    
            VentaRealizadaCRUD ec= new VentaRealizadaCRUD();            
            dato=ec.ListarCantidadVenta()+1;            
            vr1.txtNumVenta.setText("VEN"+dato);
            Mostrar();
        }
        else if(vr1.btnNuevo==e.getSource()){
            Limpiar();
            //CONTADOR PARA EL CODIGO
            VentaRealizadaCRUD ec= new VentaRealizadaCRUD();            
            dato=ec.ListarCantidadVenta()+1;            
            vr1.txtNumVenta.setText("VEN"+dato);
        }
    }
    public void EliminarTablas(){
        mt=new DefaultTableModel();
        mt.addColumn("CODPROD");
        mt.addColumn("NOMBRE");
        mt.addColumn("CANTIDAD");
        this.vr1.TablaVenta.setModel(mt);
        int eliminar=vr1.TablaVenta.getRowCount();
        for(int i=eliminar-1;i>=0;i--){
           this.mt.removeRow(i);
        }
    }
    public void Limpiar(){
        vr1.txtNumVenta.setText("");
        vr1.txtDNICliente.setText("");
        vr1.txtNombreCli.setText("");
        vr1.txtApellidoCli.setText("");
        vr1.txtCodEmpleado.setText("");
        vr1.txtNombreEmp.setText("");
        vr1.txtApellidooEmp.setText("");
        vr1.txtNumPedido.setText("");
        vr1.txtNumVenta.setText("");
        vr1.txtSubtotal.setText("");
        vr1.txtIGV.setText("");
        vr1.TXTtOTAL.setText("");
    }
    
    public void Mostrar(){
        MostrarConsulta mc = new MostrarConsulta();
        mc.MostrarVenta(vr1.TablaVenta);
    }
}
