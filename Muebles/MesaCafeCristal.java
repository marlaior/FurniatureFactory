package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo MesaCafeCristal
 * Esta clase hereda de MesaCafe
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class MesaCafeCristal extends MesaCafe{
    private static final String DESCRIPCION_MESACAFE_CRISTAL = "MESA CAFÉ DE CRISTAL";
    private static final double PRECIO_MESACAFE_CRISTAL = 50;
    private static final int ESTIMACION_MESACAFE_CRISTAL = 12;
    
    /**
     * Constructor de la clase MesaCafeCristal
     */
    public MesaCafeCristal(Cliente cliente){
        super(MesaCafeCristal.DESCRIPCION_MESACAFE_CRISTAL, cliente, 
        MesaCafeCristal.ESTIMACION_MESACAFE_CRISTAL, MesaCafeCristal.PRECIO_MESACAFE_CRISTAL);
    }
}
