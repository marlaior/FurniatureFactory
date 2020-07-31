package personas;


/**
 * Clase que describe objetos de tipo ClientePersona.
 * Esta clase hereda de Cliente.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class ClientePersona extends Cliente{
    
    public ClientePersona(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif, Comercial comercial){
        super(usuario, contraseña, nombre, apellidos, telefono, nif, comercial);
    
    }

}
