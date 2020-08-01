package muebles;

import personas.Cliente;
import personas.Artesano;

/**
 * Clase que describe objetos de tipo MesaDormitorio
 * Esta clase hereda de Mesa
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class MesaDormitorio extends Mesa{
    private static final String DESCRIPCION_MESA_DORMITORIO = "MESA DORMITORIO";
    private static final double PRECIO_MESADORMITORIO = 60;
    private static final int ESTIMACION_MESADORMITORIO = 11;
    
    /**
     * Constructor de la clase MesaDormitorio
     */
    public MesaDormitorio(Cliente cliente){
        super(MesaDormitorio.DESCRIPCION_MESA_DORMITORIO, cliente, MesaDormitorio.ESTIMACION_MESADORMITORIO, 
        MesaDormitorio.PRECIO_MESADORMITORIO);
    }
}
