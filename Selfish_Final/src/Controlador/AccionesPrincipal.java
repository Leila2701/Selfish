
package Controlador;

import ClasesReporteadores.GenerarReporte;
import DAO.EmpleadoCRUD;
import DAO.MostrarConsulta;
import DAO.PedidoCRUD;
import DAO.ProductoCRUD;
import DAO.VentaRealizadaCRUD;
import Principal.Main;
import static Principal.Main.c1;
import static Principal.Main.cc1;
import static Principal.Main.e1;
import static Principal.Main.p1;
import static Principal.Main.pr1;
import static Principal.Main.vr1;
import Vistas.CierreCajas;
import Vistas.Clientes;
import Vistas.Empleados;
import Vistas.FrmPrincipal;
import Vistas.ListaCliente;
import Vistas.ListaEmpleado;
import Vistas.ListaPedido;
import Vistas.ListaProducto;
import Vistas.Pedidos;
import Vistas.Productos;
import Vistas.VentasRealizadas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;
import javax.swing.JOptionPane;


public class AccionesPrincipal implements ActionListener{
    private FrmPrincipal fp;
    int dato=0;
     public AccionesPrincipal(FrmPrincipal fp){
         this.fp=fp;
         fp.jmnRegistroCliente.addActionListener(this);
         fp.jmnRegistroEmpleado.addActionListener(this);
         fp.jmnRegistroPedido.addActionListener(this);
         fp.jmnRegistroProducto.addActionListener(this);
         fp.jmnListaEmpleados.addActionListener(this);
         fp.jmnListaClientes.addActionListener(this);
         fp.jmnListaProductos.addActionListener(this);
         fp.jmnListaPedidos.addActionListener(this);
         fp.jmnVentasRealizadas.addActionListener(this);
         fp.jmnCierreCaja.addActionListener(this);
         fp.jmnImprimir.addActionListener(this);
     }
     public static AccionesEmpleado ace;
     public static AccionesCliente acc;
     public static AccionesPedido acp;
     public static AccionesProducto acpr;
     public static AccionesVentasRealizada acvr;
     public static AccionesCierreCaja accc;

     
     public void actionPerformed(ActionEvent e){
         if(fp.jmnRegistroEmpleado== e.getSource()){
            e1 = new Empleados();
            e1.setVisible(true);
            e1.setLocationRelativeTo(null);
            e1.setResizable(false);
            ace = new AccionesEmpleado(e1);
            //CONTADOR PARA EL CODIGO
            EmpleadoCRUD ec= new EmpleadoCRUD();            
            dato=ec.ListarCantidadEMpleado()+1;            
            e1.txtCodEmpleado.setText("EMP"+dato);
             
        }
         else if(fp.jmnListaEmpleados==e.getSource()){
            Main.e2=new ListaEmpleado();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaEmpleado(Main.e2.TablaEmpleados);
            Main.e2.setVisible(true);
            Main.e2.setLocationRelativeTo(null);
            Main.e2.setResizable(false);
         }
        else if(fp.jmnRegistroCliente==e.getSource()){
            c1 = new Clientes();
            c1.setVisible(true);
            c1.setLocationRelativeTo(null);
            acc = new AccionesCliente(c1);
        }
        else if(fp.jmnListaClientes==e.getSource()){
            Main.c2=new ListaCliente();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaCliente(Main.c2.TablaCliente);
            Main.c2.setVisible(true);
            Main.c2.setLocationRelativeTo(null);
            Main.c2.setResizable(false);
        }
        else if(fp.jmnRegistroProducto==e.getSource()){
             pr1=new Productos();
             pr1.setVisible(true);
             pr1.setLocationRelativeTo(null);
             pr1.setResizable(false);
             acpr=new AccionesProducto(pr1);
             //CONTADOR PARA EL CODIGO
            ProductoCRUD ec= new ProductoCRUD();            
            dato=ec.ListarCantidadProducto()+1;            
            pr1.txtCodProducto.setText("PROD"+dato);
        }
        else if(fp.jmnListaProductos==e.getSource()){
            Main.pr2=new ListaProducto();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaProducto(Main.pr2.TablaProducto);
            Main.pr2.setVisible(true);
            Main.pr2.setLocationRelativeTo(null);
            Main.pr2.setResizable(false);
        }
        else if(fp.jmnRegistroPedido==e.getSource()){
            p1=new Pedidos();
            p1.setVisible(true);
            p1.setLocationRelativeTo(null);
            p1.setResizable(false);
            acp=new AccionesPedido(p1);
            //CONTADOR PARA EL CODIGO
            PedidoCRUD ec= new PedidoCRUD();            
            dato=ec.ListarCantidadPedido()+1;            
            p1.txtNumPedido.setText("PED"+dato);
        }
        else if(fp.jmnListaPedidos==e.getSource()){
            Main.p2=new ListaPedido();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaPedido(Main.p2.TablaPedido);
            Main.p2.setVisible(true);
            Main.p2.setLocationRelativeTo(null);
            Main.p2.setResizable(false);
        }
        else if(fp.jmnVentasRealizadas==e.getSource()){
            vr1=new VentasRealizadas();
            vr1.setVisible(true);
            vr1.setLocationRelativeTo(null);
            vr1.setResizable(false);
            acvr=new AccionesVentasRealizada(vr1);
            //CONTADOR PARA EL CODIGO
            VentaRealizadaCRUD ec= new VentaRealizadaCRUD();            
            dato=ec.ListarCantidadVenta()+1;            
            vr1.txtNumVenta.setText("VEN"+dato);
            //Mostrar Venta
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarVenta(vr1.TablaVenta);
        }
        else if(fp.jmnCierreCaja==e.getSource()){
            cc1=new CierreCajas();
            cc1.setVisible(true);
            cc1.setLocationRelativeTo(null);
            cc1.setResizable(false);
            accc=new AccionesCierreCaja(cc1);
            
        }
        else if(fp.jmnImprimir==e.getSource()){
            GenerarReporte nr;
            nr=new GenerarReporte();
            String NUM_VEN=JOptionPane.showInputDialog("Ingresar el numero de venta: ");
            nr.MostrarReporte(NUM_VEN);
        }
     }
}
