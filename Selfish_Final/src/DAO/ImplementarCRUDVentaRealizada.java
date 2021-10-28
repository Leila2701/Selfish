
package DAO;

import Modelo.VentaRealizada;

public interface ImplementarCRUDVentaRealizada {
    public void Create(VentaRealizada VR);//insert
    public VentaRealizada Read(String NUM_VEN);//select
    public void Update(VentaRealizada VR);//actualizar
    public void Delete(String NUM_VEN);//eliminacion
    
    public int ListarCantidadVenta();
    public VentaRealizada ReadPrecio(String NUM_PED);
}
