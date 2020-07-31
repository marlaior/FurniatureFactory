package personas;


/**
 * Clase que describe objetos de tipo Jefe.
 * Esta clase hereda de Empleado.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class Jefe extends Empleado{
    
    private static final String  PUESTO_JEFE= "Jefe·";    
    private static final double SALARIO_BASE_JEFE = 2000;
    
    /**
     * Constructor de la clase Jefe
     */
    public Jefe(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif){
        super(usuario, contraseña, nombre, apellidos, telefono, nif, Jefe.PUESTO_JEFE, Jefe.SALARIO_BASE_JEFE);        
    }
    
    
        
        

}
