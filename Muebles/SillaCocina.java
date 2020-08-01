package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo SillaCocina
 * Esta clase hereda de Silla
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class SillaCocina extends Silla{
    private static final String DESCRIPCION_SILLACOCINA = "SILLA_COCINA";
    private static final double PRECIO_SILLACOCINA = 30;
    private static final int ESTIMACION_SILLACOCINA= 9;
    
    /**
     * Constructor de la clase SillaCocina
     */
    public SillaCocina(Cliente cliente){
        super(SillaCocina.DESCRIPCION_SILLACOCINA, cliente, 
            SillaCocina.ESTIMACION_SILLACOCINA, SillaCocina.PRECIO_SILLACOCINA);
    }
}
