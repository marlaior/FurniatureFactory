package tools;


/**
 * Interfaz para sustituir los métodos print de la clase System.out por expresiones lambda que (opino) permiten un código más limpio.
 * 
 * @author Marcos Laíño Ordóñez
 */

public interface SoutIF<T>{

    void out(T writeSomething);
}
