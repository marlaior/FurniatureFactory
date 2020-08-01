package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo MesaCafeMadera
 * Esta clase hereda de MesaCafe
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class MesaCafeMadera extends MesaCafe{
    private static final String DESCRIPCION_MESACAFE_MADERA = "MESA CAFÉ DE MADERA";
    private static final double PRECIO_MESACAFE_MADERA = 20;
    private static final int ESTIMACION_MESACAFE_CRISTAL = 6;
    
    /**
     * Constructor de la clase MesaCafeMadera
     */
    public MesaCafeMadera(Cliente cliente){
        super(MesaCafeMadera.DESCRIPCION_MESACAFE_MADERA, cliente, 
        MesaCafeMadera.ESTIMACION_MESACAFE_CRISTAL, MesaCafeMadera.PRECIO_MESACAFE_MADERA);
    }
}
