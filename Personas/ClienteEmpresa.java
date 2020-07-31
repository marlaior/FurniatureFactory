package personas;


/**
 * Clase que describe objetos de tipo ClienteEmpresa.
 * Esta clase hereda de Cliente.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class ClienteEmpresa extends Cliente{
    private String nombreContactoEmpresa;
    private int descuento;
    private static final int DESCUENTO_EMPRESAS = 10;
    /**
     * Constructor de la clase ClienteEmpresa
     */
    public ClienteEmpresa(String usuario, String contraseña, String nombre, String telefono, String nif, Comercial comercial, String nombreContactoEmpresa){
        super(usuario, contraseña, nombre, null, telefono, nif, comercial);
        this.nombreContactoEmpresa = nombreContactoEmpresa;
        this.descuento = ClienteEmpresa.DESCUENTO_EMPRESAS;
    }
    
    // ***** setters *****
    public void setDescuento(int descuento){
        this.descuento = descuento;
    }
    public void nombreContactoEmpresa (String nombreContactoEmpresa){
        this.nombreContactoEmpresa = nombreContactoEmpresa;
    }
    
    // ***** getters *****
    public int getDescuento(){
        return this.descuento;
    }
    public String nombreContactoEmpresa(){
        return this.nombreContactoEmpresa;
    }  
        
}
