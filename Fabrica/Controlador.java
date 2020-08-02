package fabrica;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import tools.SoutIF;
import personas.Cliente;
import personas.Empleado;
import muebles.Mueble;

/**
 * Clase que controla las distintas operaciones sobre ficheros
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Controlador{
    private static final SoutIF<String> PLN = (s) -> System.out.println(s);
    private static final SoutIF<String> P = (s) -> System.out.println(s);
    private static final SoutIF<String> PELN = (s) -> System.err.println(s);
    private static final String RUTAFICHERO_CLIENTES = "clientes.dat";
    private static final String RUTAFICHERO_EMPLEADOS = "empleados.dat";
    private static final String RUTAFICHERO_MUEBLES = "muebles.dat";
    private static final File FICHERO_CLIENTES = new File(RUTAFICHERO_CLIENTES);
    private static final File FICHERO_EMPLEADOS = new File(RUTAFICHERO_EMPLEADOS);
    private static final File FICHERO_MUEBLES = new File(RUTAFICHERO_MUEBLES);

    /**
     * Método que permite guardar en un fichero el arraylist de clientes que se le pasa por parámetro.
     */
    public void saveClientes(ArrayList<Cliente> clientes){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTAFICHERO_CLIENTES));
            outputStream.writeObject(clientes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            PELN.out("Error al intentar guardar los datos");
            PELN.out("Inténtelo de nuevo.");
        }
    }
    /**
     * Método que permite guardar en un fichero el arraylist de empleados que se le pasa por parámetro.
     */
    public void saveEmpleados(ArrayList<Empleado> empleados){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTAFICHERO_EMPLEADOS));
            outputStream.writeObject(empleados);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            PELN.out("Error al intentar guardar los datos");
            PELN.out("Inténtelo de nuevo.");
        }
    }
    /**
     * Método que permite guardar en un fichero el arraylist de empleados que se le pasa por parámetro.
     */
    public void saveMuebles(ArrayList<Mueble> muebles){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTAFICHERO_MUEBLES));
            outputStream.writeObject(muebles);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            PELN.out("Error al intentar guardar los datos");
            PELN.out("Inténtelo de nuevo.");
        }
    }
    /**
     * Método que devuelve un arraylist con todos los clientes guardados en el fichero.
     */
    private static ArrayList<Cliente> loadClientes() {
        ArrayList<Cliente> clientes = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RUTAFICHERO_CLIENTES));
            clientes = (ArrayList<Cliente>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            clientes = new ArrayList<Cliente>();
            PELN.out("Error al importar el fichero");
            e.printStackTrace();
            e.getMessage();
        }
        return clientes;
    }
    /**
     * Método que devuelve un arraylist con todos los empleados guardados en el fichero.
     */
    private static ArrayList<Empleado> loadEmpleados() {
        ArrayList<Empleado> empleados = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RUTAFICHERO_EMPLEADOS));
            empleados = (ArrayList<Empleado>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            empleados = new ArrayList<Empleado>();
            PELN.out("Error al importar el fichero");
            e.printStackTrace();
            e.getMessage();
        }
        return empleados;
    }
    /**
     * Método que devuelve un arraylist con todos los muebles guardados en el fichero.
     */
    private static ArrayList<Mueble> loadMuebles() {
        ArrayList<Mueble> muebles = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RUTAFICHERO_CLIENTES));
            muebles = (ArrayList<Mueble>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            muebles = new ArrayList<Mueble>();
            PELN.out("Error al importar el fichero");
            e.printStackTrace();
            e.getMessage();
        }
        return muebles;
    }
    /**
     * Método que genera datos iniciales con los que testear la aplicación
     */
}