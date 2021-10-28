
package DAO;

import Modelo.Cliente;
import Modelo.Empleado;
import Modelo.Pedido;
import Modelo.Producto;
import Vistas.Pedidos;

public interface ImplementarCRUDPedido {
    public void Create(Pedido PE);//insert
    public Pedido Read(String NUM_PED);//select
    public void Update(Pedido PE);//actualizar
    public void Delete(String NUM_PED);//eliminacion
    
    public void CreateNumPed(Pedido PE);
    public void CreateProducto(Pedido PE);
    public void DeleteProducto(String COD_PROD);
    public int ListarCantidadPedido();
    
    //public Pedido ReadPrecio(String NUM_PED);
}
