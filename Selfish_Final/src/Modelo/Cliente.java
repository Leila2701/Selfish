
package Modelo;

public class Cliente {
   public String dni_cli;
   public String nom_cli;
   public String ape_cli;
   public String sex_cli;
   public String tel_cli;
   public String dir_cli;
   public String email_cli;
   public Cliente(){}

    public Cliente(String dni_cli, String nom_cli, String ape_cli, String sex_cli, String tel_cli, String dir_cli, String email_cli) {
        this.dni_cli = dni_cli;
        this.nom_cli = nom_cli;
        this.ape_cli = ape_cli;
        this.sex_cli = sex_cli;
        this.tel_cli = tel_cli;
        this.dir_cli = dir_cli;
        this.email_cli = email_cli;
    }

    public Cliente(String dni_cli, String nom_cli, String ape_cli) {
        this.dni_cli=dni_cli;
        this.nom_cli=nom_cli;
        this.ape_cli=ape_cli;
    }
   
}
