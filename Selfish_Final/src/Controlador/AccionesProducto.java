
package Controlador;

import DAO.MostrarConsulta;
import DAO.ProductoCRUD;
import Modelo.Producto;
import Principal.Main;
import static Principal.Main.pr1;
import Vistas.ListaProducto;
import Vistas.Productos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


public class AccionesProducto implements ActionListener {
    private Productos pr1;
    Producto PR;
    int dato=0;
    int x=0;
    public AccionesProducto(Productos pr1){
        this.pr1=pr1;
        this.pr1.btnRegistrarProducto.addActionListener(this);
        this.pr1.btnBuscarProducto.addActionListener(this);
        this.pr1.btnModificarProducto.addActionListener(this);
        this.pr1.btnEliminarProducto.addActionListener(this);
        this.pr1.btnLimpiarProducto.addActionListener(this);
        this.pr1.btnMostrarProductos.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e){
        if(pr1.btnRegistrarProducto==e.getSource()){
            String cod_prod=pr1.txtCodProducto.getText();
            String nom_prod=pr1.txtNombreProducto.getText();
            double pre_prod=Double.parseDouble(pr1.txtPrecioProducto.getText());
            String des_prod=pr1.txtDescripcionProducto.getText();
            
            Producto P=new Producto(cod_prod,nom_prod,pre_prod,des_prod);
            Validar();
            if(x==0){
            ProductoCRUD ec= new ProductoCRUD();
            ec.Create(P);
            
            Limpiar();
                     
            dato=ec.ListarCantidadProducto()+1;            
            pr1.txtCodProducto.setText("PRO"+dato);
            }
        }
        else if(pr1.btnBuscarProducto==e.getSource()){
            String COD_PROD=JOptionPane.showInputDialog("Ingrese el codigo del producto, que solicita buscar");
            ProductoCRUD ec= new ProductoCRUD();
            PR=ec.Read(COD_PROD);
            if(PR!=null){
                pr1.txtCodProducto.setText(PR.cod_prod);
                pr1.txtNombreProducto.setText(PR.nom_prod);
                pr1.txtPrecioProducto.setText(String.valueOf(PR.pre_prod));
                pr1.txtDescripcionProducto.setText(PR.des_prod);
            }else{
                JOptionPane.showMessageDialog(null,"El codigo del producto no existe");
            }
        }
        else if(pr1.btnModificarProducto==e.getSource()){
            String cod_prod=pr1.txtCodProducto.getText();
            String nom_prod=pr1.txtNombreProducto.getText();
            double pre_prod=Double.parseDouble(pr1.txtPrecioProducto.getText());
            String des_prod=pr1.txtDescripcionProducto.getText();
            
            
            PR=new Producto(cod_prod,nom_prod,pre_prod,des_prod);
            ProductoCRUD ec= new ProductoCRUD();
            ec.Update(PR);
        }
        else if(pr1.btnEliminarProducto==e.getSource()){
            int reply=JOptionPane.showConfirmDialog(null,"Esta seguro de eliminar?","CONFIRMAR",JOptionPane.YES_NO_OPTION);
            if(reply==JOptionPane.YES_NO_OPTION){
                ProductoCRUD ec= new ProductoCRUD();
                ec.Delete(PR.cod_prod);
            }
        }
        else if(pr1.btnLimpiarProducto==e.getSource()){
            Limpiar();
            //Codigo automatico
            ProductoCRUD ec= new ProductoCRUD();            
            dato=ec.ListarCantidadProducto()+1;            
            pr1.txtCodProducto.setText("PRO"+dato);
        }
        else if(pr1.btnMostrarProductos==e.getSource()){
            Main.pr2=new ListaProducto();
            MostrarConsulta mc = new MostrarConsulta();
            mc.MostrarEnTablaProducto(Main.pr2.TablaProducto);
            Main.pr2.setVisible(true);
            Main.pr2.setLocationRelativeTo(null);
        }
    }
    public void Limpiar(){
        pr1.txtCodProducto.setText("");
        pr1.txtNombreProducto.setText("");
        pr1.txtPrecioProducto.setText("");
        pr1.txtDescripcionProducto.setText("");
    }
    public void Validar(){
    try{
        if(!pr1.txtCodProducto.getText().isEmpty()){
            if(!pr1.txtNombreProducto.getText().isEmpty()){
                if(!pr1.txtPrecioProducto.getText().isEmpty()){
                    if(!pr1.txtDescripcionProducto.getText().isEmpty()){
                    }else
                        {JOptionPane.showMessageDialog(this.pr1,"Ingrese la descripcion del producto", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                        x=1;}                 
                }else
                    {JOptionPane.showMessageDialog(this.pr1,"Registre el precio del Producto", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                    x=1;}            
            }else
                {JOptionPane.showMessageDialog(this.pr1,"Ingrese el nombre del Producto", "mensaje de error", JOptionPane.ERROR_MESSAGE);
                x=1;}
        }else
            {JOptionPane.showMessageDialog(this.pr1,"Ingrese codigo del producto", "mensaje de error", JOptionPane.ERROR_MESSAGE);
            x=1;}            
           
    }catch(Exception e)
    {
        JOptionPane.showMessageDialog(this.pr1,e.getMessage());
    }
    }
}
