
package DAO;

import Modelo.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class EmpleadoCRUD implements ImplementarCRUDEmpleado{
    Connection con;
    AccesoBD c;
    Statement st;
    ResultSet rs;
    
    public EmpleadoCRUD(){
        c = new AccesoBD();
        con=c.con;
    }

    @Override
    public void Create(Empleado E) {
        try{
        st =con.createStatement();
        rs = st.executeQuery("INSERT INTO empleado VALUES('"+E.cod_emp+"','"+E.nom_emp+"','"+E.ape_emp+"','"+E.sex_emp+"','"+E.dni_emp+"','"+E.tel_emp+"','"+E.email_emp+"','"+E.car_emp+"')");
      
    }catch(Exception e){
         AccesoBD.Mensaje("Datos registrados correctamente ");
    }
    }

    @Override
    public Empleado Read(String COD_EMP) {
        Empleado emp=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT cod_emp,ape_emp,nom_emp,sex_emp,cargo_emp,dni_emp,tel_emp,email_emp FROM empleado WHERE cod_emp='"+COD_EMP+"';");
            if(rs.next()){
                String cod_emp=rs.getString(1);
                String ape_emp=rs.getString(2);
                String nom_emp=rs.getString(3);
                String sex_emp=rs.getString(4);
                String car_emp=rs.getString(5);
                String dni_emp=rs.getString(6);
                String tel_emp=rs.getString(7);
                String email_emp=rs.getString(8);
                emp=new Empleado(cod_emp,ape_emp,nom_emp,sex_emp,car_emp,dni_emp,tel_emp,email_emp);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return emp;
    }

    @Override
    public void Update(Empleado E) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("UPDATE empleado SET  cod_emp='"+E.cod_emp+"',ape_emp='"+E.ape_emp+"',nom_emp='"+E.nom_emp+"', sex_emp='"+E.sex_emp+"',cargo_emp='"+E.car_emp+"',dni_emp='"+E.dni_emp+"', tel_emp='"+E.tel_emp+"', email_emp='"+E.email_emp+"' WHERE cod_emp='"+E.cod_emp+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
    }

    @Override
    public void Delete(String COD_EMP) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("DELETE FROM empleado WHERE cod_emp='"+COD_EMP+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
        }
    }
    public Empleado ReadEmpleado(String COD_EMP){
        Empleado emp=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT cod_emp,ape_emp,nom_emp FROM empleado WHERE cod_emp='"+COD_EMP+"';");
            if(rs.next()){
                String cod_emp=rs.getString(1);
                String nom_emp=rs.getString(3);
                String ape_emp=rs.getString(2);
                emp=new Empleado(cod_emp,nom_emp,ape_emp);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return emp;
        
    }
    
    @Override
    public int ListarCantidadEMpleado(){
     PreparedStatement pr=null;
        try
        {
        st = con.createStatement(); // crea el espacio para la consulta
           //ejecuta la consulta en un String
           rs = st.executeQuery("select count(cod_emp) AS num from empleado");
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
