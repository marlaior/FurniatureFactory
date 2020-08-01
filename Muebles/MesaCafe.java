package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo MesaCafe
 * Esta clase hereda de Mesa
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class MesaCafe extends Mesa{
    private static final String DESCRIPCION_MESACAFE = "MESA CAFÉ";
    private static final double PRECIO_MESACAFE = 25;
    private static final int ESTIMACION_MESACAFE = 8;
    
    /**
     * Constructores de la clase MesaCafe
     */
    public MesaCafe(String descripcion_mueble, Cliente cliente, int tiempoEstimado, double precio){
        super(descripcion_mueble, cliente, tiempoEstimado, precio + MesaCafe.PRECIO_MESACAFE);
    }
    public MesaCafe(Cliente cliente){
        super(MesaCafe.DESCRIPCION_MESACAFE, cliente, MesaCafe.ESTIMACION_MESACAFE, MesaCafe.PRECIO_MESACAFE);
    }
}
