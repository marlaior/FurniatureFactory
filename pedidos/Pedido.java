package pedidos;

import java.util.ArrayList;
import muebles.Mueble;
import personas.Cliente;


/**
 * Clase que almacena los pedidos de los clientes
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Pedido{
    private static int numPedido;
    private ArrayList<Mueble> muebles;
    private double precioTotal;
    private boolean isFinished;
    private Cliente cliente;
    
    /**
     * Constructor de la clase Pedido
     */
    public Pedido(){

    }
}
