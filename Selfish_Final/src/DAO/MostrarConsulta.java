
package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MostrarConsulta {
    Connection con;
    Statement st;
    ResultSet rs;
    AccesoBD c;
    public MostrarConsulta(){
        c=new AccesoBD(); //crea la conexion
        con=c.con; // actualizamos la conexion
    }
    public void MostrarEnTablaEmpleado(JTable tabla){
        String[] encab1 = {"CODEMP","NOMBRE","APELLIDO","SEXO","DNI","TELEFONO","EMAIL","CARGO"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT*FROM empleado");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3),
                     rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
    }
    }
    public void MostrarEnTablaCliente(JTable tabla){
        String[] encab1 = {"DNI","NOMBRE","APELLIDO","SEXO","TELEFONO","DIRECCION","EMAIL"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT*FROM cliente");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3),
                     rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
    }
    }
    public void MostrarEnTablaProducto(JTable tabla){
        String[] encab1 = {"CODPROD","NOMBRE","PRECIO","DESCRIPCION"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT*FROM producto");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3),
                     rs.getString(4)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
    }
    }
    public void MostrarEnTablaDetallePedido(JTable tabla,String NUM_PED){
        String[] encab1 = {"CODPROD","NOMBRE","CANTIDAD"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT d.cod_prod,p.nom_prod,d.cant_prod FROM detalle_pedido d INNER JOIN producto p ON d.cod_prod=p.cod_prod WHERE d.num_ped='"+NUM_PED+"';");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
        }
    }
    public void MostrarEnTablaPedido(JTable tabla){
        String[] encab1 = {"NUM_PED","CLIENTE","COD_EMP","FECHA","TOTAL"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("select dp.num_ped,CONCAT(c.nom_cli ,' ', c.ape_cli),p.cod_emp,p.fec_ped,sum(pr.pre_prod*dp.cant_prod) from detalle_pedido dp\n" +
                                "inner join pedido p\n" +
                                "on p.num_ped=dp.num_ped\n" +
                                "INNER JOIN cliente c \n" +
                                "ON c.dni_cli=p.dni_cli\n" +
                                "INNER JOIN producto pr \n" +
                                "ON dp.cod_prod=pr.cod_prod\n" +
                                "GROUP BY dp.num_ped,c.nom_cli,c.ape_cli,p.cod_emp,p.fec_ped");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
    }
    }
    public void MostrarVenta(JTable tabla){
        String[] encab1 = {"NUM.VENTA","FECHA","DNI_CLI","COD_EMP","NUM_PED","SUBTOTAL","IGV","TOTAL"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT*FROM venta");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
                              rs.getString(6),rs.getString(7),rs.getString(8)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
    }
    }
    public void MostrarEnTablaCierrePedido(JTable tabla,String FECHA){
        String[] encab1 = {"NUM.PED","CLIENTE","COD.EMP","FECHA","TOTAL"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("select dp.num_ped,CONCAT(c.nom_cli ,' ', c.ape_cli),p.cod_emp,p.fec_ped,sum(pr.pre_prod*dp.cant_prod) \n" +
                                "from detalle_pedido dp\n" +
                                "inner join pedido p\n" +
                                "on p.num_ped=dp.num_ped\n" +
                                "INNER JOIN cliente c \n" +
                                "ON c.dni_cli=p.dni_cli\n" +
                                "INNER JOIN producto pr \n" +
                                "ON dp.cod_prod=pr.cod_prod\n" +
                                "WHERE p.fec_ped='"+FECHA+"'\n" +
                                "GROUP BY dp.num_ped,c.nom_cli,c.ape_cli,p.cod_emp,p.fec_ped");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
        }
    }
    public void MostrarEnTablaCierreVenta(JTable tabla,String FECHA){
        String[] encab1 = {"COD.EMP","TOTAL VENTA","NUMERO DE PEDIDOS"};
        DefaultTableModel mt = new DefaultTableModel(null,encab1);
        tabla.setModel(mt); 
    int contador=0;
    try{
           st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT cod_emp,SUM(total),count(num_ven)\n" +
                                "FROM venta\n" +
                                "WHERE fec_doc_pag='"+FECHA+"'\n" +
                                "GROUP BY cod_emp");
           while(rs.next()){ // recorre por filas
               contador++;
               Object[] fila={rs.getString(1),rs.getString(2),rs.getString(3)};
               mt.addRow(fila);
           }
        }catch(Exception e){
        AccesoBD.Mensaje("Error al cargar data "+e);
        }
    }
}
