package pedidos;

import java.io.Serializable;
import java.util.ArrayList;
import muebles.Mueble;
import personas.Cliente;
import fabrica.Controlador;


/**
 * Clase que almacena los pedidos de los clientes
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Pedido implements Serializable{
    private static int numPedido;
    private ArrayList<Mueble> muebles;
    private Cliente cliente;
    
    /**
     * Constructor de la clase Pedido
     */
    public Pedido(ArrayList<Mueble> muebles, Cliente cliente){
        setNumPedido();
        this.muebles = muebles;
        this.cliente = cliente;
    }
    
    // *********** getters ***********
    public int getNumPed(){
        return this.numPedido;
    }
    public ArrayList<Mueble> getMuebles(){
        return this.muebles;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    
    // *********** getters ***********
    
    public void setNumPedido(){
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();
        this.numPedido = pedidos.size() + 1;
    }
}
