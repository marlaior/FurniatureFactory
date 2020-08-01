package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo MesaComedor
 * Esta clase hereda de Mesa
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class MesaComedor extends Mesa{
    private static final String DESCRIPCION_MESA_COMEDOR = "MESA COMEDOR";
    private static final double PRECIO_MESACOMEDOR = 150;
    private static final int ESTIMACION_MESACOMEDOR = 15;
    
     /**
     * Constructor de la clase MesaComedor
     */
    public MesaComedor(Cliente cliente){
        super(MesaComedor.DESCRIPCION_MESA_COMEDOR, cliente, MesaComedor.ESTIMACION_MESACOMEDOR, 
        MesaComedor.PRECIO_MESACOMEDOR);
    }
}
