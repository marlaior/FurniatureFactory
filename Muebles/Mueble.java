package muebles;

import java.io.Serializable;
import personas.Persona;
import personas.Artesano;
import personas.Cliente;
import java.util.Date;


/**
 * Clase que describe objetos de tipo Mueble
 * 
 * @author Marcos Laíño Ordóñez 
 */
public class Mueble implements Serializable{
    private static final long serialVersionUID = 1L;
    protected enum Estado { PENDIENTE, ASIGNADO, EN_CONSTRUCCION, PAUSADO, TERMINADO, ENTREGADO };
    protected Estado estado;
    protected int id_mueble;
    protected String descripcion_mueble;
    protected Cliente cliente;
    protected Artesano artesano;
    protected int tiempoEstimado; //estimacion de nº de horas de construcción
    protected String anotaciones;
    protected double precio;
    protected Date fechaCompra;
    protected Date fechaEntrega;
    
    /**
     * Constructor de la clase Mueble
     */
    public Mueble(String descripcion_mueble, Cliente cliente, int tiempoEstimado, double precio){
        this.estado = Mueble.Estado.PENDIENTE;
        this.descripcion_mueble = descripcion_mueble;
        this.cliente = cliente;
        this.artesano = artesano;
        this.tiempoEstimado = tiempoEstimado;
        this.precio = precio;
        this.fechaCompra = new Date();
        this.anotaciones = "Datos Cliente\n"
            + "**********\n"
            + this.cliente.getNombre() + this.cliente.getApellidos() + "\n"
            + "tlfno: " + this.cliente.getTelefono() + "\n\n"
            + "PEDIDO\n"
            + "**********\n"
            + "Tipo Mueble = " + this.descripcion_mueble + "\n"
            + "Fecha de compra = " + this.fechaCompra + "\n"
            + "Precio = " + this.precio + "\n\n";
    }
    
    // ***** setters *****
    public void setEstado(Estado estado){
        this.estado = estado;
    }
    public void setDescripcionMueble(String descripcion_mueble){
        this.descripcion_mueble = descripcion_mueble;
    }
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }
    public void setArtesano(Artesano artesano){
        this.artesano = artesano;
    }
    public void setTiempoEstimado(int tiempoEstimado){
        this.tiempoEstimado = tiempoEstimado;
    }
    public void setAnotaciones(String anotaciones){
        this.anotaciones.concat(anotaciones);
    }
    
    // ***** getters *****
    public int getIdMueble(){
        return this.id_mueble;
    }
}
