package personas;


/**
 * Clase que describe objetos de tipo Persona
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Persona{
    protected String usuario;
    protected String contraseña;
    protected String nombre;
    protected String apellido;
    protected String telefono;
    protected String nif;
    
    /**
     * Contructor de la clase Persona
     */
    public Persona(String usuario, String contraseña, String nombre, String apellido, String telefono, String nif){
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;    
        this.nif = nif;
    }
    
    // ***** setters *****
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApellido(String apellido){
        this.apellido = apellido;
    }
    public void setTelefono (String telefono){
        this.telefono = telefono;
    }
    
    // ***** getters *****
    public String getUsuario(){
        return this.usuario;
    }
    public String getContraseña(){
        return this.contraseña;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getApellidos(){
        return this.apellido;
    }
    public String getTelefono(){
        return this.telefono;
    }
}
