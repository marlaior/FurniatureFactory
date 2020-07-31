package personas;


/**
 * Clase que describe objetos de tipo ArtesanoPorHoras.
 * Esta clase hereda de Artesano.
 * 
 * @author Marcos Laíño Ordóñez. 
 */
public class ArtesanoPorHoras extends Artesano{
    private static final String  PUESTO_ARTESANO_POR_HORAS= "Artesano por horas·";    
    private static final double SALARIO_BASE_ARTESANO_POR_HORAS = 0;
    private static final double SALARIO_POR_HORA = 10;
    private int horasTrabajadas;
    private double salarioFinal;
    
    /**
     * Constructor de la clase ArtesanoEnPlantilla
     */
        public ArtesanoPorHoras(String usuario, String contraseña, String nombre, String apellidos, String telefono, String nif){
        super(usuario, contraseña, nombre, apellidos, telefono, nif, ArtesanoPorHoras.PUESTO_ARTESANO_POR_HORAS, ArtesanoPorHoras.SALARIO_BASE_ARTESANO_POR_HORAS);        
        this.horasTrabajadas = 0;
        this.salarioFinal = 0;
    }
    
    
    
    /**
     * Método que suma a la comisión del artesano en plantilla una comisión por cada mueble que termina.
     */
    public void setComisionMuebleTerminado(){
        this.comision = this.comision + 40; 
    }
    
    // ***** setters *****
    public void setHorasTrabajadas(int horas){
        this.horasTrabajadas += horas;
    }
    public void setSalarioFinal(){
        this.salarioFinal = this.horasTrabajadas * ArtesanoPorHoras.SALARIO_POR_HORA +  this.comision;
    }
    
    // ***** getters *****
    public int getHorasTrabajadas(){
        return this.horasTrabajadas;
    }

}
