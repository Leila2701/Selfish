
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class AccesoBD {
    public Connection con;
    public AccesoBD(){
    try{
        //1.Driver de conexion
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2.Se realiza la conexion
        con = DriverManager.getConnection(
              "jdbc:sqlserver://localhost;databaseName=Cevicheria","sa","pinky");
        //Mensaje("Conexion a BD, OK!!!");
       }catch(Exception e){           
          Mensaje("ERROR en la conexion a la BD "+e);
       }
   }       
   public static void Mensaje(String msj){
       JOptionPane.showMessageDialog(null,msj);
   }
}
