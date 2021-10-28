
package DAO;

import Modelo.Producto;


public interface ImplementarCRUDProducto {
    public void Create(Producto P);//insert
    public Producto Read(String COD_PROD);//select
    public void Update(Producto P);//actualizar
    public void Delete(String COD_PROD);//eliminacion
    
    public Producto ReadProducto(String COD_PROD);
    public int ListarCantidadProducto();
}
