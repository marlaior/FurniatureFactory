package personas;


/**
 * Clase que describe objetos de la clase Artesano.
 * Esta clase hereda de Empleado.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class Artesano extends Empleado{
    
    /**
     * Constructor de la clase Artesano
     */
        public Artesano(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif, String puesto, double salarioBase){
        super(usuario, contraseña, nombre, apellidos, telefono, nif, puesto, salarioBase);        
    }

}
