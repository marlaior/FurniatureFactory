package Personas;


/**
 * Clase que describe objetos de tipo Persona
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Persona{
    protected String usuario;
    protected String contraseña;
    protected String nombre;
    protected String apellidos;
    protected String telefono;
    
    /**
     * Contructor de la clase Persona
     */
    public Persona(String usuario, String contraseña, String nombre, String apellidos, String telefono){
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        
    }

}
