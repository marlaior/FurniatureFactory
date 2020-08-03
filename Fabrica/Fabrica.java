package fabrica;


/**
 * 
 * Clase principal que incluye el método main que sirve de entrada a la aplicación.
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Fabrica{
    
    public static void main (String ... args){
        Controlador.checkFicheroClientes();
        Controlador.checkFicheroMuebles();
        Controlador.checkFicheroEmpleados();
    }
}
