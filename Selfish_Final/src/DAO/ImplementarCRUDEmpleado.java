
package DAO;

import Modelo.Empleado;

public interface ImplementarCRUDEmpleado {
    public void Create(Empleado E);//insert
    public Empleado Read(String COD_EMP);//select
    public void Update(Empleado E);//actualizar
    public void Delete(String COD_EMP);//eliminacion 
    
    public Empleado ReadEmpleado(String COD_EMP);
    public int ListarCantidadEMpleado();
    
}
