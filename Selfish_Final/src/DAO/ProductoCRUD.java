
package DAO;

import Modelo.Empleado;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ProductoCRUD  implements ImplementarCRUDProducto{
    Connection con;
    AccesoBD c;
    Statement st;
    ResultSet rs;
    
    public ProductoCRUD(){
        c = new AccesoBD();
        con=c.con;
    }

    @Override
    public void Create(Producto P) {
       try{
        st =con.createStatement();
        rs = st.executeQuery("INSERT INTO producto VALUES('"+P.cod_prod+"','"+P.nom_prod+"','"+P.pre_prod+"','"+P.des_prod+"');");
      
        }catch(Exception e){
         AccesoBD.Mensaje("Datos registrados correctamente ");
        }
    }

    @Override
    public Producto Read(String COD_PROD) {
        Producto prod=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT cod_prod,nom_prod,pre_prod,des_prod FROM producto WHERE cod_prod='"+COD_PROD+"';");
            if(rs.next()){
                String cod_prod=rs.getString(1);
                String nom_prod=rs.getString(2);
                double pre_prod=Double.parseDouble(rs.getString(3));
                String des_prod=rs.getString(4);
 
                prod=new Producto(cod_prod,nom_prod,pre_prod,des_prod);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return prod;
    }

    @Override
    public void Update(Producto P) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("UPDATE producto SET cod_prod='"+P.cod_prod+"', nom_prod='"+P.nom_prod+"',pre_prod="+P.pre_prod+", des_prod='"+P.des_prod+"' WHERE cod_prod='"+P.cod_prod+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
    }

    @Override
    public void Delete(String COD_PROD) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("DELETE FROM producto WHERE cod_prod='"+COD_PROD+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
        }
    }
    @Override
    public Producto ReadProducto(String COD_PROD){
        Producto prod=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT cod_prod,nom_prod FROM producto WHERE cod_prod='"+COD_PROD+"';");
            if(rs.next()){
                String cod_prod=rs.getString(1);
                String nom_prod=rs.getString(2);
 
                prod=new Producto(cod_prod,nom_prod);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return prod;
    }
    
    @Override
    public int ListarCantidadProducto(){
        PreparedStatement pr=null;
        try
        {
        st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT COUNT(cod_prod) AS num FROM producto");
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
