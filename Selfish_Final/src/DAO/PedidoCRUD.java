
package DAO;

import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Pedido;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PedidoCRUD implements ImplementarCRUDPedido{
    Connection con;
    AccesoBD c;
    Statement st;
    ResultSet rs;
    
    public PedidoCRUD(){
        c = new AccesoBD();
        con=c.con;
    } 

    @Override
    public void Create(Pedido PE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pedido Read(String NUM_PED) {
        Pedido ped=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT p.num_ped,p.dni_cli,c.nom_cli,c.ape_cli,p.cod_emp,e.ape_emp,e.nom_emp FROM pedido p INNER JOIN cliente c ON p.dni_cli=c.dni_cli INNER JOIN empleado e ON p.cod_emp=e.cod_emp WHERE p.num_ped='"+NUM_PED+"';");
            if(rs.next()){
                String num_ped=rs.getString(1);
                String dni_cli=rs.getString(2);
                String nom_cli=rs.getString(3);
                String ape_cli=rs.getString(4);
                String cod_emp=rs.getString(5);
                String ape_emp=rs.getString(6);
                String nom_emp=rs.getString(7);
 
                ped=new Pedido(num_ped,dni_cli,nom_cli,ape_cli,cod_emp,ape_emp,nom_emp);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return ped;
    }

    @Override
    public void Update(Pedido PE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(String NUM_PED) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("EXECUTE us_eliminarpedido '"+NUM_PED+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
        }
    }
    
    @Override
    public void CreateNumPed(Pedido PE){
        try{
        st =con.createStatement();
        rs = st.executeQuery("INSERT INTO pedido VALUES('"+PE.num_ped+"',GETDATE(),'"+PE.dni_cli+"','"+PE.cod_emp+"');");
      
        }catch(Exception e){
         AccesoBD.Mensaje("Datos registrados correctamente ");
        }
    }

    @Override
    public void CreateProducto(Pedido PE) {
        try{
        st =con.createStatement();
        rs = st.executeQuery("INSERT INTO detalle_pedido VALUES('"+PE.num_ped+"','"+PE.cod_prod+"',"+PE.cant_prod+");");
      
        }catch(Exception e){
         AccesoBD.Mensaje("Datos registrados correctamente ");
        }
    }

    @Override
    public void DeleteProducto(String COD_PROD) {//NO EJECUTA
        try{
            st=con.createStatement();
            rs=st.executeQuery("DELETE FROM detalle_pedido WHERE cod_prod='"+COD_PROD+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
        }
    }
       
    @Override
    public int ListarCantidadPedido(){
        PreparedStatement pr=null;
        try
        {
        st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT COUNT(num_ped) AS num FROM pedido");
           if(rs.next()){
               return rs.getInt("num");
            }else {
                return 0;
            } 
        
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
            return 0;
        }
    }

 
    
    
}
