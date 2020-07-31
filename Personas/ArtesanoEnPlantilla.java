package personas;


/**
 * Clase que describe objetos de tipo ArtesanoEnPlantilla.
 * Esta clase hereda de Artesano.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class ArtesanoEnPlantilla extends Artesano{
    private static final String  PUESTO_ARTESANO_EN_PLANTILLA= "Artesano en plantilla·";    
    private static final double SALARIO_BASE_ARTESANO_EN_PLANTILLA = 1000;
    
    /**
     * Constructor de la clase ArtesanoEnPlantilla
     */
        public ArtesanoEnPlantilla(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif){
        super(usuario, contraseña, nombre, apellidos, telefono, nif, ArtesanoEnPlantilla.PUESTO_ARTESANO_EN_PLANTILLA, ArtesanoEnPlantilla.SALARIO_BASE_ARTESANO_EN_PLANTILLA);        
    }
    
    /**
     * Método que suma a la comisión del artesano en plantilla una comisión por cada mueble que termina.
     */
    public void setComisionMuebleTerminado(){
        this.comision = this.comision + 20; 
    }

}
