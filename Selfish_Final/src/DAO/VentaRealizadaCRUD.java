
package DAO;

import Modelo.VentaRealizada;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class VentaRealizadaCRUD implements ImplementarCRUDVentaRealizada{
    Connection con;
    AccesoBD c;
    Statement st;
    ResultSet rs;
    
    public VentaRealizadaCRUD(){
        c = new AccesoBD();
        con=c.con;
    }

    @Override
    public void Create(VentaRealizada VR) {
        try{
        st =con.createStatement();
        rs = st.executeQuery("INSERT INTO venta VALUES('"+VR.num_ven+"',GETDATE(),'"+VR.dni_cli+"','"+VR.cod_emp+"','"+VR.num_ped+"',"+VR.sub_total+","+VR.igv+","+VR.total+");");
      
        }catch(Exception e){
         AccesoBD.Mensaje("Datos registrados correctamente ");
        }
    }

    @Override
    public VentaRealizada Read(String NUM_VEN) {
        VentaRealizada ven=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT v.num_ven,v.dni_cli,c.nom_cli,c.ape_cli,v.cod_emp,e.ape_emp,e.nom_emp,v.num_ped,v.sub_total,v.igv,v.total\n" +
                                "FROM venta v\n" +
                                "INNER JOIN cliente c\n" +
                                "ON v.dni_cli=c.dni_cli\n" +
                                "INNER JOIN empleado e\n" +
                                "ON v.cod_emp=e.cod_emp\n" +
                                "WHERE num_ven='"+NUM_VEN+"'");
            if(rs.next()){
                String num_ven=rs.getString(1);
                String dni_cli=rs.getString(2);
                String nom_cli=rs.getString(3);
                String ape_cli=rs.getString(4);
                String cod_emp=rs.getString(5);
                String ape_emp=rs.getString(6);
                String nom_emp=rs.getString(7);
                String num_ped=rs.getString(8);
                double sub_total=Double.parseDouble(rs.getString(9));
                double igv=Double.parseDouble(rs.getString(10));
                double total=Double.parseDouble(rs.getString(11));
 
                ven=new VentaRealizada(num_ven,dni_cli,nom_cli,ape_cli,cod_emp,ape_emp,nom_emp, num_ped,sub_total, igv, total);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return ven;
    }

    @Override
    public void Update(VentaRealizada VR) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void Delete(String NUM_VEN) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("DELETE FROM venta where num_ven='"+NUM_VEN+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
        }
    }
    @Override
    public int ListarCantidadVenta(){
         PreparedStatement pr=null;
        try
        {
        st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("SELECT COUNT(num_ven) AS num FROM venta");
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
    @Override
    public VentaRealizada ReadPrecio(String NUM_PED){
        VentaRealizada ven=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT dp.num_ped,\n" +
                                "SUM(pr.pre_prod*dp.cant_prod) AS 'SUB.TOTAL', \n" +
                                "SUM(pr.pre_prod*dp.cant_prod*0.18) AS 'IGV', \n" +
                                "SUM(pr.pre_prod*dp.cant_prod)+SUM(pr.pre_prod*dp.cant_prod*0.18) AS 'TOTAL'\n" +
                                "FROM detalle_pedido dp\n" +
                                "INNER JOIN producto pr\n" +
                                "ON dp.cod_prod=pr.cod_prod\n" +
                                "WHERE dp.num_ped='"+NUM_PED+"'\n" +
                                "GROUP BY dp.num_ped");
            if(rs.next()){
                String num_ped=rs.getString(1);
                double sub_total=Double.parseDouble(rs.getString(2));
                double igv=Double.parseDouble(rs.getString(3));
                double total=Double.parseDouble(rs.getString(4));
 
                ven=new VentaRealizada(num_ped,sub_total,igv,total);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return ven;
    }
}
