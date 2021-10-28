
package Principal;


import Controlador.AccionesLogin;
import Controlador.AccionesPrincipal;
import DAO.MostrarConsulta;
import Vistas.CierreCajas;
import Vistas.Clientes;
import Vistas.Empleados;
import Vistas.FrmPrincipal;
import Vistas.ListaCliente;
import Vistas.ListaEmpleado;
import Vistas.ListaPedido;
import Vistas.ListaProducto;
import Vistas.Login;
import Vistas.Pedidos;
import Vistas.Productos;
import Vistas.VentasRealizadas;


public class Main {
    public static Empleados e1;
    public static ListaEmpleado e2;
    
    public static Clientes c1;
    public static ListaCliente c2;
    
    public static Pedidos p1;
    public static ListaPedido p2;
    
    public static Productos pr1;
    public static ListaProducto pr2;
    
    public static VentasRealizadas vr1;
    
    public static CierreCajas cc1;
       
    public static MostrarConsulta ml;
    
    public static FrmPrincipal fp;
    
    public static Login ls;    

    public static void main(String[] args) {
        ls=new Login();
        ls.setVisible(true);
        ls.setLocationRelativeTo(null);
        ls.setLocationRelativeTo(null);
        AccionesLogin s=new AccionesLogin(ls);
    }
}
