
package DAO;

import Modelo.Cliente;



public interface ImplementarCRUDCliente {
    public void Create(Cliente C);//insert
    public Cliente Read(String DNI);//select
    public void Update(Cliente C);//actualizar
    public void Delete(String DNI);//eliminacion 
    
    public Cliente ReadCliente(String DNI);
}
