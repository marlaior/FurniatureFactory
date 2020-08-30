package pedidos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import muebles.Mueble;
import personas.Cliente;
import fabrica.Controlador;


/**
 * Clase que almacena los pedidos de los clientes
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Pedido implements Serializable{
    private static final long serialVersionUID = 1L;
    private int numPedido;
    private ArrayList<Mueble> muebles;
    private Cliente cliente;
    private Date fechaCompra;
    
    /**
     * Constructor de la clase Pedido
     */
    public Pedido(ArrayList<Mueble> muebles, Cliente cliente){
        this.muebles = muebles;
        this.cliente = cliente;
        this.fechaCompra = new Date();
        setNumPedido();
    }
    
    // *********** getters ***********
    
    public int getNumPedido(){
        return this.numPedido;
    }
    public ArrayList<Mueble> getMuebles(){
        return this.muebles;
    }
    public Cliente getCliente(){
        return this.cliente;
    }
    public Date getFechaCompra(){
        return this.fechaCompra;
    }
    
    // *********** setters ***********
    
    public void setNumPedido(){
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();
        this.numPedido = pedidos.size() + 1;
    }
}
