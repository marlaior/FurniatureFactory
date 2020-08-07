package personas;

import java.util.Date;
import java.util.ArrayList;
import personas.Persona;
import fabrica.Controlador;

/**
 * Clase que describe objetos de tipo Empleado
 * Esta clase hereda de Persona
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Empleado extends Persona{
    
    
    protected static int num_empleados;
    protected int id_empleado;
    protected String puesto;
    protected Date fechaAlta;
    protected Date fechaBaja;
    protected double salarioBase;
    protected double comision;
    
    /**
     * Constructor de la clase Empleado
     */
    public Empleado(String usuario, String contrasena, String nombre, String apellidos, String telefono, String nif, String puesto, Double salarioBase){ 
        super(usuario, contrasena, nombre, apellidos, telefono, nif);
        this.puesto = puesto;
        this.fechaAlta = new Date();        
        setIdEmpleado();
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
        /*ArrayList<Persona> personas = Controlador.loadPersonas();
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        int id = 1;
        for (Persona auxPersona : personas){
            if (auxPersona instanceof Empleado){
                id++;
            }
        }
        this.id_empleado = id; */
        num_empleados++;
        this.id_empleado = num_empleados;
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
