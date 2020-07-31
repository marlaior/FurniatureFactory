package personas;

import java.util.Date;
/**
 * Clase que describe objetos de tipo Empleado
 * Esta clase hereda de Persona
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Empleado extends Persona{
    
    protected int id_empleado;
    protected String puesto;
    protected Date fechaAlta;
    protected Date fechaBaja;
    protected double salarioBase;
    protected double comision;
    
    /**
     * Constructor de la clase Empleado
     */
    public Empleado(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif, String puesto, Double salarioBase){ 
        super(usuario, contraseña, nombre, apellidos, telefono, nif);
        this.id_empleado = id_empleado;
        this.puesto = puesto;
        this.fechaAlta = new Date();
        
    }
    
    // ***** Setters *****
    public void setSalarioBase(double salario){
        this.salarioBase = salario;    }
    
    public void setPuesto(String puesto){
        this.puesto = puesto;
    }
    public void setFechaAlta(){
        this.fechaAlta = new Date();
    }
    public void setComision(double comision){
        this.comision = comision;
    }
   public void setFechaBaja(){
       this.fechaBaja = new Date();
    }
    public void setIdEmpleado(){
    }
    
    // ***** getters *****
    public double getSalarioBase(){
        return this.salarioBase;
    }
    public double getComision(){
        return this.comision;
    }
    public Date getFechaBaja(){
        return this.fechaBaja;
    }
    public Date getFechaAlta(){
        return this.fechaAlta;
    }
    public String getPuesto(){
        return this.puesto;
    }
    public int getIdEmpleado(){
        return this.id_empleado;
    }
    
}
