package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo SillaOficina
 * Esta clase hereda de Silla
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class SillaOficina extends Silla{
    private static final String DESCRIPCION_SILLAOFICINA = "SILLA_OFICINA";
    private static final double PRECIO_SILLAOFICINA = 80;
    private static final int ESTIMACION_SILLAOFICINA= 7;
    
    /**
     * Constructores de la clase SillaOficina
     */
    public SillaOficina(String descripcion_mueble, Cliente cliente, int tiempoEstimado, double precio){
        super(descripcion_mueble, cliente, tiempoEstimado, precio + SillaOficina.PRECIO_SILLAOFICINA);
    }
    public SillaOficina(Cliente cliente){
        super(SillaOficina.DESCRIPCION_SILLAOFICINA, cliente, 
            SillaOficina.ESTIMACION_SILLAOFICINA, SillaOficina.PRECIO_SILLAOFICINA);
    }
}
