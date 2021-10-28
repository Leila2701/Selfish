/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import DAO.ClienteCRUD;
import DAO.EmpleadoCRUD;
import DAO.MostrarConsulta;
import DAO.PedidoCRUD;
import DAO.ProductoCRUD;
import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Pedido;
import Modelo.Producto;
import Principal.Main;
import static Principal.Main.p1;
import Vistas.Clientes;
import Vistas.ListaPedido;
import Vistas.Pedidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class AccionesPedido implements ActionListener{
    private Pedidos p1;
    Pedido PED;
    Cliente CL;
    Empleado EM;
    Producto PR;
    DefaultTableModel mt;
    int dato=0;
    int x=0;
    public AccionesPedido(Pedidos p1){
        this.p1=p1;
        this.p1.btnModificarPedido.addActionListener(this);
        this.p1.btnEliminarPedido.addActionListener(this);
        this.p1.btnLimpiarPedido.addActionListener(this);
        this.p1.btnMostrarPedido.addActionListener(this);
        
        this.p1.btnRegistroPed.addActionListener(this);
        
        this.p1.btnBuscarCliPed.addActionListener(this);
        this.p1.btnBuscarEmpPed.addActionListener(this);
        
        this.p1.btnBuscarProducDetPed.addActionListener(this);
        this.p1.btnAgregarProducto.addActionListener(this);
        this.p1.btnLimpiarProducto.addActionListener(this);
        
    }  
    public void actionPerformed(ActionEvent e){
        if(p1.btnRegistroPed==e.getSource()){
            String num_ped=p1.txtNumPedido.getText();
            String dni_cli=p1.jtxtDNICliPed.getText();
            String cod_prod=p1.txtCodEmpPed.getText();
            Pedido PE=new Pedido(num_ped,dni_cli,cod_prod);
            Validar();
            if(x==0){
            PedidoCRUD ec= new PedidoCRUD();
            ec.CreateNumPed(PE);
            Limpiar();}
        }
        else if(p1.btnBuscarCliPed==e.getSource()){
            String DNI_CLI=JOptionPane.showInputDialog("Ingrese DNI a buscar, que solicita buscar");
            ClienteCRUD ec=new ClienteCRUD();
            CL=ec.ReadCliente(DNI_CLI);
            if(CL!=null){
                p1.jtxtDNICliPed.setText(CL.dni_cli);
                p1.txtNombreCliPed.setText(CL.nom_cli);
                p1.txtApellidoCliPed.setText(CL.ape_cli);
            }else{
                JOptionPane.showMessageDialog(null,"El cliente no existe");
            }
                       
        }
        else if(p1.btnBuscarEmpPed==e.getSource()){
            String COD_EMP=JOptionPane.showInputDialog("Ingrese codigo del empleado a buscar, que solicita buscar");
            EmpleadoCRUD ec=new EmpleadoCRUD();
            EM=ec.ReadEmpleado(COD_EMP);
            if(EM!=null){
                p1.txtCodEmpPed.setText(EM.cod_emp);
                p1.txtApellidoEmpPed.setText(EM.ape_emp);
                p1.txtNombreEmpPed.setText(EM.nom_emp);                
            }else{
                JOptionPane.showMessageDialog(null,"El empleado no existe");
            }
        }
        else if(p1.btnBuscarProducDetPed==e.getSource()){
            String COD_PROD=JOptionPane.showInputDialog("Ingrese codigo del producto a buscar, que solicita buscar");
            ProductoCRUD ec=new ProductoCRUD();
            PR=ec.ReadProducto(COD_PROD);
            if(PR!=null){
                p1.txtCodProdPed.setText(PR.cod_prod);
                p1.txtNombreProdPed.setText(PR.nom_prod);
            }else{
                JOptionPane.showMessageDialog(null,"El empleado no existe");
            }
        }
        else if(p1.btnAgregarProducto==e.getSource()){
            String num_ped=p1.txtNumPedido.getText();
            String cod_prod=p1.txtCodProdPed.getText();
            int cant_prod=Integer.parseInt(p1.txtCantProdPed.getText());
            
            Pedido PE=new Pedido(num_ped,cod_prod,cant_prod);
            PedidoCRUD ec= new PedidoCRUD();
            ec.CreateProducto(PE);
            
            LimpiarProducto();
            
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaDetallePedido(p1.TablaDetallePedido,p1.txtNumPedido.getText());         
        }
        else if(p1.btnModificarPedido==e.getSource()){
            String NUM_PED=JOptionPane.showInputDialog("Ingrese el numero de pedido que dese agrgar mas productos");
            PedidoCRUD ec= new PedidoCRUD();
            PED=ec.Read(NUM_PED);
            if(PED!=null){
                p1.txtNumPedido.setText(PED.num_ped);
                p1.jtxtDNICliPed.setText(PED.dni_cli);
                p1.txtNombreCliPed.setText(PED.nom_cli);
                p1.txtApellidoCliPed.setText(PED.ape_cli);
                p1.txtCodEmpPed.setText(PED.cod_emp);
                p1.txtApellidoEmpPed.setText(PED.ape_emp);
                p1.txtNombreEmpPed.setText(PED.nom_emp);
            }else{
                JOptionPane.showMessageDialog(null,"El numero del pedido no existe");
            }
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaDetallePedido(p1.TablaDetallePedido,p1.txtNumPedido.getText());         
        }
        else if(p1.btnLimpiarProducto==e.getSource()){
            LimpiarProducto();
        }
        else if(p1.btnLimpiarPedido==e.getSource()){
            p1.txtNumPedido.setText("");
            p1.jtxtDNICliPed.setText("");
            p1.txtNombreCliPed.setText("");
            p1.txtApellidoCliPed.setText("");
            p1.txtCodEmpPed.setText("");
            p1.txtApellidoEmpPed.setText("");
            p1.txtNombreEmpPed.setText("");
            p1.txtCodProdPed.setText("");
            p1.txtNombreProdPed.setText("");
            p1.txtCantProdPed.setText("");
          
            EliminarTablas();
            //CONTADOR PARA EL CODIGO
            PedidoCRUD ec= new PedidoCRUD();            
            dato=ec.ListarCantidadPedido()+1;            
            p1.txtNumPedido.setText("PED"+dato);            
        }
        else if(p1.btnMostrarPedido==e.getSource()){
            Main.p2=new ListaPedido();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaPedido(Main.p2.TablaPedido);
            Main.p2.setVisible(true);
            Main.p2.setLocationRelativeTo(null);         
        }
        else if(p1.btnEliminarPedido==e.getSource()){
            int reply=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar?","CONFIRMAR",JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_NO_OPTION){
                PedidoCRUD ec= new PedidoCRUD();
                ec.Delete(PED.num_ped);
            }
            Limpiar();
            EliminarTablas();
            //CONTADOR PARA EL CODIGO
            PedidoCRUD ec= new PedidoCRUD();            
            dato=ec.ListarCantidadPedido()+1;            
            p1.txtNumPedido.setText("PED"+dato);
            
        }
    }
    
    public void EliminarTablas()
    {
        mt=new DefaultTableModel();
        mt.addColumn("CODPROD");
        mt.addColumn("NOMBRE");
        mt.addColumn("CANTIDAD");
        this.p1.TablaDetallePedido.setModel(mt);
        int eliminar=p1.TablaDetallePedido.getRowCount();
        for(int i=eliminar-1;i>=0;i--){
           this.mt.removeRow(i);
        }
    }
    public void LimpiarProducto(){
        p1.txtCodProdPed.setText("");
        p1.txtNombreProdPed.setText("");
        p1.txtCantProdPed.setText("");
    }
    
    public void Limpiar(){
        p1.txtNumPedido.setText("");
        p1.jtxtDNICliPed.setText("");
        p1.txtNombreCliPed.setText("");
        p1.txtApellidoCliPed.setText("");
        p1.txtCodEmpPed.setText("");
        p1.txtNombreEmpPed.setText("");
        p1.txtApellidoEmpPed.setText("");
    }
    
    public void Validar(){
    try{
        if(!p1.txtNumPedido.getText().isEmpty()){
            if(!p1.jtxtDNICliPed.getText().isEmpty()){
                if(!p1.txtNombreCliPed.getText().isEmpty()){
                    if(!p1.txtApellidoCliPed.getText().isEmpty()){
                        if(!p1.txtCodEmpPed.getText().isEmpty()){
                            if(!p1.txtNombreEmpPed.getText().isEmpty()){
                                if(!p1.txtApellidoEmpPed.getText().isEmpty()){
                                }else{JOptionPane.showMessageDialog(this.p1,"Ingrese los Apellidos del Empleado", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                                x=1;}                  
                            }else
                                {JOptionPane.showMessageDialog(this.p1,"Ingrese los Nombres del Empleado", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                                x=1;}  
                        }else
                            {JOptionPane.showMessageDialog(this.p1,"Ingrese el Codigo del empleado", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                            x=1;}  
                    }else
                        {JOptionPane.showMessageDialog(this.p1,"Ingrese los Apellidos del cliente", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                        x=1;}                 
                }else
                    {JOptionPane.showMessageDialog(this.p1,"Ingrese los Nombres del Cliente", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                    x=1;}            
            }else
                {JOptionPane.showMessageDialog(this.p1,"Ingrese el DNI del Cliente", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                x=1;}
        }else
            {JOptionPane.showMessageDialog(this.p1,"Ingrese el numero del Pedido", "mensaje de error", JOptionPane.ERROR_MESSAGE);
            x=1;}            
           
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(this.p1,e.getMessage());
    }
    }     
}
