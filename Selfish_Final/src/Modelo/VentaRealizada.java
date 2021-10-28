
package Modelo;

public class VentaRealizada {
    public String num_ven;
    public String fec_ven;
    public String dni_cli;
    public String nom_cli;
    public String ape_cli;
    public String cod_emp;
    public String ape_emp;
    public String nom_emp;
    public String num_ped;
    public double sub_total;
    public double igv;
    public double total;
    public VentaRealizada(){}

    public VentaRealizada(String num_ven, String fec_ven, String dni_cli, String nom_cli, String ape_cli, String cod_emp, String ape_emp, String nom_emp, String num_ped, double sub_total, double igv, double total) {
        this.num_ven = num_ven;
        this.fec_ven = fec_ven;
        this.dni_cli = dni_cli;
        this.nom_cli = nom_cli;
        this.ape_cli = ape_cli;
        this.cod_emp = cod_emp;
        this.ape_emp = ape_emp;
        this.nom_emp = nom_emp;
        this.num_ped = num_ped;
        this.sub_total = sub_total;
        this.igv = igv;
        this.total = total;
    }

    public VentaRealizada(String num_ven, String dni_cli, String nom_cli, String ape_cli, String cod_emp, String ape_emp, String nom_emp, String num_ped, double sub_total, double igv, double total) {
        this.num_ven = num_ven;
        this.dni_cli = dni_cli;
        this.nom_cli = nom_cli;
        this.ape_cli = ape_cli;
        this.cod_emp = cod_emp;
        this.ape_emp = ape_emp;
        this.nom_emp = nom_emp;
        this.num_ped = num_ped;
        this.sub_total = sub_total;
        this.igv = igv;
        this.total = total;
    }
    public VentaRealizada(String num_ped,double sub_total,double igv, double total){
        this.num_ped=num_ped;
        this.sub_total=sub_total;
        this.igv=igv;
        this.total=total;
    }

    public VentaRealizada(String num_ven, String dni_cli, String cod_emp, String num_ped, double sub_total, double igv, double total) {
        this.num_ven=num_ven;
        this.num_ped=num_ped;
        this.dni_cli=dni_cli;
        this.cod_emp=cod_emp;
        this.num_ped=num_ped;
        this.sub_total=sub_total;
        this.igv=igv;
        this.total=total;
    }

    
    
    
}
