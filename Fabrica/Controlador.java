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
import personas.*;
import muebles.*;

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
    
    // ***** FICHEROS CLIENTES *****
    
    /**
     * Crea el archivo de datos en el que se va a almacenar el array-list de clientes
     */
    private static void createArchivoClientes() { 
        try {
            FICHERO_CLIENTES.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FICHERO_CLIENTES));
            outputStream.writeObject(new ArrayList<Cliente>());
            outputStream.close();
        } catch (Exception e) {
            PELN.out("No existe el archivo de datos y no se ha podido crear uno nuevo");
            PELN.out("Inténtelo de nuevo");
        }
    }
    /**
     * Método que permite guardar en un fichero el arraylist de clientes que se le pasa por parámetro.
     */
    public static void saveClientes(ArrayList<Cliente> clientes){
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
     * Método que comprueba que exista los archivos de datos.
     * En caso de que no exista el archivo, lo crea y genera unos datos iniciales para pruebas.
     */
    public static void checkFicheroClientes() {
        if (!FICHERO_CLIENTES.exists()) {
            createArchivoClientes();
            initialData();
        }
    }
    
    // ***** FICHEROS EMPLEADOS *****
    
    /**
     * Crea el archivo de datos en el que se va a almacenar el array-list de empleados
     */
    private static void createArchivoEmpleados() { 
        try {
            FICHERO_EMPLEADOS.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FICHERO_EMPLEADOS));
            outputStream.writeObject(new ArrayList<Empleado>());
            outputStream.close();
        } catch (Exception e) {
            PELN.out("No existe el archivo de datos y no se ha podido crear uno nuevo");
            PELN.out("Inténtelo de nuevo");
        }
    }
    /**
     * Método que permite guardar en un fichero el arraylist de empleados que se le pasa por parámetro.
     */
    public static void saveEmpleados(ArrayList<Empleado> empleados){
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
     * Método que comprueba que exista los archivos de datos.
     * En caso de que no existan estos archivos, los crea con datos iniciales para poder realizar las pruebas.
     */
    public static void checkFicheroEmpleados() {
        if (!FICHERO_EMPLEADOS.exists()) {
            createArchivoEmpleados();
        }
    }    
       
    // ***** FICHEROS MUEBLES *****
    
    /**
     * Crea el archivo de datos en el que se va a almacenar el array-list de muebles
     */
    private static void createArchivoMuebles() { 
        try {
            FICHERO_MUEBLES.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FICHERO_MUEBLES));
            outputStream.writeObject(new ArrayList<Mueble>());
            outputStream.close();
        } catch (Exception e) {
            PELN.out("No existe el archivo de datos y no se ha podido crear uno nuevo");
            PELN.out("Inténtelo de nuevo");
        }
    }
    /**
     * Método que permite guardar en un fichero el arraylist de empleados que se le pasa por parámetro.
     */
    public static void saveMuebles(ArrayList<Mueble> muebles){
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
     * Método que devuelve un arraylist con todos los muebles guardados en el fichero.
     */
    private static ArrayList<Mueble> loadMuebles() {
        ArrayList<Mueble> muebles = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RUTAFICHERO_MUEBLES));
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
     * Método que comprueba que exista los archivos de datos.
     * En caso de que no existan estos archivos, los crea con datos iniciales para poder realizar las pruebas.
     */
    public static void checkFicheroMuebles() {
        if (!FICHERO_MUEBLES.exists()) {
            createArchivoMuebles();
        }
    }    


    /**
     * Método que genera datos iniciales con los que testear la aplicación
     */
    public static void initialData() {
    
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();		
        empleados.add(new Jefe("bruce", "pass", "Bruce", "Dickinson", "666-123456", "12345678A"));
        empleados.add(new Jefe("steve", "pass", "Steve", "Harris", "666-123457", "12345679B"));
        empleados.add(new Comercial("page", "pass", "Jimmy", "Page", "666-123458", "12345680C", "Londres"));
        empleados.add(new Comercial("plant", "pass", "Robert", "Plant", "666-123459", "12345681D", "Mancester"));
        empleados.add(new ArtesanoEnPlantilla("james", "pass", "James", "Hetfield", "666-123460", "12345682E"));
        empleados.add(new ArtesanoEnPlantilla("lars", "pass", "Lars", "Ulrich", "666-123461", "12345683F"));
        empleados.add(new ArtesanoPorHoras("cliff", "pass", "Cliff", "Burton", "666-123462", "12345684G"));
        empleados.add(new ArtesanoPorHoras("trujillo", "pass", "Robert", "Trujillo", "666-123463", "12345686H"));
        empleados.add(new ArtesanoPorHoras("kirk", "pass", "Kirk", "Hammett", "666-123464", "12345687I"));
        saveEmpleados(empleados);
        
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();		
        clientes.add(new ClientePersona("lemmy", "pass", "Lemmy", "Kilmister", "666-123465", "12345688J", 3));
        clientes.add(new ClientePersona("dio", "pass", "Ronnie", "James", "666-123466", "12345689K", 3));
        clientes.add(new ClientePersona("ozzy", "pass", "Ozzy", "James", "666-123467", "12345690L", 4));
        clientes.add(new ClientePersona("iommi", "pass", "Tony", "Iommi", "666-123468", "12345691M", 4));
        clientes.add(new ClienteEmpresa("ghost", "pass", "Ghost B.C.", "666-123469", "A12345692", 3, "Tobias Forge"));
        clientes.add(new ClienteEmpresa("ok", "pass", "Ok Computer", "666-123470", "A12345693", 3, "Thom York"));
        clientes.add(new ClienteEmpresa("rubber", "pass", "Rubber Factory", "666-123471", "A12345694", 4, "Dan Auerbach"));
        clientes.add(new ClienteEmpresa("fabfour", "pass", "The Fab Four", "666-123472", "A12345695", 4, "Richard Starkey"));		
        saveEmpleados(empleados);
        
        ArrayList<Mueble> muebles = new ArrayList<Mueble>();
        saveMuebles(muebles);
    }
	
    
}