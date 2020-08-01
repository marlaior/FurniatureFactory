package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo Silla
 * Esta clase hereda de Mueble
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class Silla extends Mueble{
     private static final String DESCRIPCION_SILLA = "SILLA GENÉRICA";
    private static final double PRECIO_SILLA = 100;
    private static final int ESTIMACION_SILLA = 3;
    /**
     * Contructores de la clase Silla
     */
    public Silla(String descripcion_mueble, Cliente cliente, int tiempoEstimado, double precio){
        super(descripcion_mueble, cliente, tiempoEstimado, precio + Silla.PRECIO_SILLA);
    }
    public Silla(Cliente cliente){
        super(Silla.DESCRIPCION_SILLA, cliente, Silla.ESTIMACION_SILLA, Silla.PRECIO_SILLA);
    }
}
