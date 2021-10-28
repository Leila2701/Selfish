
package Modelo;

public class DocumentoPago {
    public String fec_doc_pag;
    public int num_doc_pag;
    public String dni_cli;
    public String nom_cli;
    public int num_ped;
    public String cod_trab;
    public String dir_cli;
    public String des_ped;
    public double sub_total;
    public double igv;
    public double total_pagar;
    public DocumentoPago(){}

    public DocumentoPago(String fec_doc_pag, int num_doc_pag, String dni_cli, String nom_cli, int num_ped, String cod_trab, String dir_cli, String des_ped, double sub_total, double igv, double total_pagar) {
        this.fec_doc_pag = fec_doc_pag;
        this.num_doc_pag = num_doc_pag;
        this.dni_cli = dni_cli;
        this.nom_cli = nom_cli;
        this.num_ped = num_ped;
        this.cod_trab = cod_trab;
        this.dir_cli = dir_cli;
        this.des_ped = des_ped;
        this.sub_total = sub_total;
        this.igv = igv;
        this.total_pagar = total_pagar;
    }
    
}
