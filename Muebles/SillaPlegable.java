package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo SillaPlegable
 * Esta clase hereda de Silla
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class SillaPlegable extends Silla{
    private static final String DESCRIPCION_SILLAPLEGABLE = "SILLA_PLEGABLE";
    private static final double PRECIO_SILLAPLEGABLE = 10;
    private static final int ESTIMACION_SILLAPLEGABLE= 5;
    
    /**
     * Constructor de la clase SillaPlegable
     */
    public SillaPlegable(Cliente cliente){
        super(SillaPlegable.DESCRIPCION_SILLAPLEGABLE, cliente, 
            SillaPlegable.ESTIMACION_SILLAPLEGABLE, SillaPlegable.PRECIO_SILLAPLEGABLE);
    }
}
