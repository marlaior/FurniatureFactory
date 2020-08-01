package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo Mesa
 * Esta clase hereda de Mueble
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class Mesa extends Mueble{
    private static final String DESCRIPCION_MESA = "MESA GENÉRICA";
    private static final double PRECIO_MESA = 200;
    private static final int ESTIMACION_MESA = 5;
    /**
     * Contructores de la clase Mesa
     */
    public Mesa(String descripcion_mueble, Cliente cliente, int tiempoEstimado, double precio){
        super(descripcion_mueble, cliente, tiempoEstimado, precio + Mesa.PRECIO_MESA);
    }
    public Mesa(Cliente cliente){
        super(Mesa.DESCRIPCION_MESA, cliente, Mesa.ESTIMACION_MESA, Mesa.PRECIO_MESA);
    }
    
}
