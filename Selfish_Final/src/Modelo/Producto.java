
package Modelo;


public class Producto {
    public String cod_prod;
    public String nom_prod;
    public double pre_prod;
    public String des_prod;
   
    public Producto(){}

    public Producto(String cod_prod, String nom_prod, double pre_prod, String des_prod) {
        this.cod_prod = cod_prod;
        this.nom_prod = nom_prod;
        this.pre_prod = pre_prod;
        this.des_prod = des_prod;
    }
    
    public Producto(String cod_prod,String nom_prod){
        this.cod_prod=cod_prod;
        this.nom_prod=nom_prod;
    }
    
}
