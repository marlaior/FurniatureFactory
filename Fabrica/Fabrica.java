package fabrica;

/**
 * 
 * Clase principal que incluye el método main que sirve de entrada a la aplicación.
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Fabrica{
    public Fabrica(){
        Controlador.checkFicheroPersonas();
        Controlador.checkFicheroPedidos();        
        new Menu();
    }    
    public static void main (String [] args){
        Controlador.checkFicheroPersonas();
        Controlador.checkFicheroPedidos();        
        new Menu();
    }
}
