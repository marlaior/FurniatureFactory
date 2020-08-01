package muebles;

import personas.Cliente;
import personas.Artesano; 

/**
 * Clase que describe objetos de tipo SillaOficinaSinRuedas
 * Esta clase hereda de SillaOficina
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class SillaOficinaSinRuedas extends SillaOficina{
    private static final String DESCRIPCION_SILLAOFICINA_SINRUEDAS = "SILLA DE OFICINA SIN RUEDAS";
    private static final double PRECIO_SILLAOFICINA_SINRUEDAS = 20;
    private static final int ESTIMACION_SILLAOFICINA_SINRUEDAS = 9;
    /**
     * Constructor de la clase SillaOficinaSinRuedas
     */
    public SillaOficinaSinRuedas(Cliente cliente){
        super(SillaOficinaSinRuedas.DESCRIPCION_SILLAOFICINA_SINRUEDAS, cliente, 
        SillaOficinaSinRuedas.ESTIMACION_SILLAOFICINA_SINRUEDAS, SillaOficinaSinRuedas.PRECIO_SILLAOFICINA_SINRUEDAS);
    }
}
