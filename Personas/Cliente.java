package personas;

/**
 * Clase que describe objetos de tipo Cliente.
 * Esta clase hereda de Persona.
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Cliente extends Persona{
    
    protected int id_cliente;
    protected Comercial comercial;
    
    /**
     * Constructor de la clase Cliente
     */
    public Cliente(String usuario, String contraseña, String nombre, String apellidos, 
        String telefono, String nif, Comercial comercial){
        super(usuario, contraseña, nombre, apellidos, telefono, nif);
        this.comercial = comercial;
    }
    
    // ***** setters *****
    public void setIdCliente(){
    }
    public void setComercial(Comercial comercial){
        this.comercial = comercial;
    }
    // ***** getters *****
    public int getIdCliente(){
        return this.id_cliente;
    }
    public Comercial getComercial(){
        return this.comercial;
    }

    
}
