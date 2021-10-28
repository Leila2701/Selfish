
package Modelo;

public class Empleado {
    public String cod_emp;
    public String ape_emp;
    public String nom_emp;
    public String sex_emp;
    public String car_emp;
    public String dni_emp;
    public String tel_emp;
    public String email_emp;
    public Empleado(){}
    
    public Empleado(String cod_emp, String ape_emp, String nom_emp, String sex_emp, String car_emp, String dni_emp, String tel_emp, String email_emp) {
        this.cod_emp = cod_emp;
        this.ape_emp = ape_emp;
        this.nom_emp = nom_emp;
        this.sex_emp = sex_emp;
        this.car_emp = car_emp;
        this.dni_emp = dni_emp;
        this.tel_emp = tel_emp;
        this.email_emp = email_emp;
    }
    
    public Empleado(String cod_emp,String ape_emp,String nom_emp){
        this.cod_emp=cod_emp;
        this.ape_emp=ape_emp;
        this.nom_emp=nom_emp;        
    }
}
