
package DAO;

import Modelo.Cliente;
import Vistas.Clientes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class ClienteCRUD implements ImplementarCRUDCliente{
    Connection con;
    AccesoBD c;
    Statement st;
    ResultSet rs;
    
    public ClienteCRUD(){
        c = new AccesoBD();
        con=c.con;
    }

    @Override
    public void Create(Cliente C) {
        try{
        st =con.createStatement();
        rs = st.executeQuery("INSERT INTO cliente VALUES ('"+C.dni_cli+"','"+C.nom_cli+"','"+C.ape_cli+"','"+C.sex_cli+"','"+C.tel_cli+"','"+C.dir_cli+"','"+C.email_cli+"');");
      
        }catch(Exception e){
         AccesoBD.Mensaje("Datos registrados correctamente ");
        }
    }

    @Override
    public Cliente Read(String DNI) {
        Cliente cli=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT dni_cli,nom_cli,ape_cli,sex_cli,tel_cli,dir_cli, email_cli FROM cliente WHERE dni_cli='"+DNI+"'");
            if(rs.next()){
                String dni_cli=rs.getString(1);
                String nom_cli=rs.getString(2);
                String ape_cli=rs.getString(3);
                String sex_cli=rs.getString(4);
                String tel_cli=rs.getString(5);
                String dir_cli=rs.getString(6);
                String email_cli=rs.getString(7);
                cli=new Cliente(dni_cli,nom_cli,ape_cli,sex_cli,tel_cli,dir_cli,email_cli);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return cli;
    }

    @Override
    public void Update(Cliente C) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("UPDATE cliente SET dni_cli='"+C.dni_cli+"',nom_cli='"+C.nom_cli+"',ape_cli='"+C.ape_cli+"',sex_cli='"+C.sex_cli+"',tel_cli='"+C.tel_cli+"',dir_cli='"+C.dir_cli+"',email_cli='"+C.email_cli+"' WHERE dni_cli='"+C.dni_cli+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
    }

    @Override
    public void Delete(String DNI) {
        try{
            st=con.createStatement();
            rs=st.executeQuery("DELETE FROM cliente WHERE dni_cli='"+DNI+"';");
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Eliminado...");
        }
    }
  
    @Override
    public Cliente ReadCliente(String DNI) {
        Cliente cli=null;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT dni_cli,nom_cli,ape_cli FROM cliente WHERE dni_cli='"+DNI+"';");
            if(rs.next()){
                String dni_cli=rs.getString(1);
                String nom_cli=rs.getString(2);
                String ape_cli=rs.getString(3);
                cli=new Cliente(dni_cli,nom_cli,ape_cli);
            }
        }catch(Exception e){
            AccesoBD.Mensaje("Dato Guardados...");
        }
        return cli;
        
    }
}
