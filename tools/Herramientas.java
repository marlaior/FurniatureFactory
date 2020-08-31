package tools;

import java.util.Scanner;

/**
 * Clase que implementa pequeñas funcionalidades que se repiten varias veces a lo largo de toda la aplicación.
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Herramientas{
    private static final SoutIF<String> PLN = (s) -> System.out.println(s);
    private static final SoutIF<String> P = (s) -> System.out.println(s);
    private static final SoutIF<String> PELN = (s) -> System.err.println(s);
    private static final Scanner scanner = new Scanner(System.in);
    
    public static void enterParaContinuar(){
        P.out("\nPulse Enter para continuar");
        scanner.nextLine();        
    }
    
    /**
     * Método que pide al usuario que confirme una decisión
     */
    public static boolean confirmarDecision(){
        
        boolean allRight = false;
        boolean retorno = false;
        do{
            P.out("\n¿Está seguro? (Y/n) ");
            String respuesta = scanner.nextLine();
            if(respuesta.equals("Y")){
                retorno = true;
                allRight = true;
            }else if (respuesta.equals("n")){
                retorno = false;
                allRight = true;
            }
        }while(!allRight);
        return retorno;
    }        
    
}
