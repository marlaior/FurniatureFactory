package personas;


/**
 * Clase que describe objetos de tipo Comercial.
 * Esta clase hereda de Empleado.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class Comercial extends Empleado{


    private static final String  PUESTO_COMERCIAL= "Comercial·";    
    private static final double SALARIO_BASE_COMERCIAL = 1500;
    private String zona;
    
    /**
     * Constructor de la clase Comercial
     */
        public Comercial(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif, String zona){
       super(usuario, contraseña, nombre, apellidos, telefono, nif, Comercial.PUESTO_COMERCIAL, Comercial.SALARIO_BASE_COMERCIAL);  
       this.zona = zona;
    }
    
    // ***** setters *****
    public void setZona(String zona){
        this.zona = zona;
    }
    
    // ***** getters *****
    public String getZona(){
        return this.zona;
    }
}
