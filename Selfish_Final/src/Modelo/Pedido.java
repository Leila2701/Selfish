
package Modelo;

public class Pedido {
    public String num_ped;
    public String fec_ped;
    public String dni_cli;
    public String nom_cli;
    public String ape_cli;
    public String cod_emp;
    public String ape_emp;
    public String nom_emp;
    public String cod_prod;
    public String nom_prod;
    public int cant_prod;
    public Pedido(){}

    public Pedido(String num_ped, String fec_ped, String dni_cli, String nom_cli, String ape_cli, String cod_emp, String ape_emp, String nom_emp, String cod_prod, String nom_prod, int cant_prod) {
        this.num_ped = num_ped;
        this.fec_ped = fec_ped;
        this.dni_cli = dni_cli;
        this.nom_cli = nom_cli;
        this.ape_cli = ape_cli;
        this.cod_emp = cod_emp;
        this.ape_emp = ape_emp;
        this.nom_emp = nom_emp;
        this.cod_prod = cod_prod;
        this.nom_prod = nom_prod;
        this.cant_prod = cant_prod;
    }
    public Pedido(String num_ped,String cod_prod,int cant_prod){
        this.num_ped=num_ped;
        this.cod_prod=cod_prod;
        this.cant_prod=cant_prod;
    }
    public Pedido(String num_ped,String dni_cli,String cod_emp){
        this.num_ped=num_ped;
        //this.fec_ped = fec_ped;
        this.dni_cli=dni_cli;
        this.cod_emp=cod_emp;
    }
    public Pedido(String num_ped,String dni_cli,String nom_cli,String ape_cli,String cod_emp,String ape_emp,String nom_emp){
        this.num_ped=num_ped;
        this.dni_cli=dni_cli;
        this.nom_cli=nom_cli;
        this.ape_cli=ape_cli;
        this.cod_emp=cod_emp;
        this.ape_emp=ape_cli;
        this.nom_emp=nom_emp;
    }
}
