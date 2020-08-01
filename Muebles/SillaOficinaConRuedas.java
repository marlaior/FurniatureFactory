package muebles;

import personas.Cliente;
import personas.Artesano; 

/**
 * Clase que describe objetos de tipo SillaOficinaConRuedas
 * Esta clase hereda de SillaOficina
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class SillaOficinaConRuedas extends SillaOficina{
    private static final String DESCRIPCION_SILLAOFICINA_CONRUEDAS = "SILLA DE OFICINA CON RUEDAS";
    private static final double PRECIO_SILLAOFICINA_CONRUEDAS = 100;
    private static final int ESTIMACION_SILLAOFICINA_CONRUEDAS = 15;
    /**
     * Constructor de la clase SillaOficinaConRuedas
     */
    public SillaOficinaConRuedas(Cliente cliente){
        super(SillaOficinaConRuedas.DESCRIPCION_SILLAOFICINA_CONRUEDAS, cliente, 
        SillaOficinaConRuedas.ESTIMACION_SILLAOFICINA_CONRUEDAS, SillaOficinaConRuedas.PRECIO_SILLAOFICINA_CONRUEDAS);
    }
}
