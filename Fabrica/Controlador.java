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
       private static final String RUTAFICHERO_PERSONAS = "personas.dat";
    private static final String RUTAFICHERO_MUEBLES = "muebles.dat";
    private static final File FICHERO_PERSONAS = new File(RUTAFICHERO_PERSONAS);
    private static final File FICHERO_MUEBLES = new File(RUTAFICHERO_MUEBLES);
    
    // ********** GESTIÓN DE ARCHIVOS **********
    
    /**
     * Crea el archivo de datos en el que se va a almacenar el array-list de personas
     */
    public static void createArchivoPersonas() { 
        try {
            FICHERO_PERSONAS.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FICHERO_PERSONAS));
            outputStream.writeObject(new ArrayList<Persona>());
            outputStream.close();
        } catch (Exception e) {
            PELN.out("No existe el archivo de datos y no se ha podido crear uno nuevo");
            PELN.out("Inténtelo de nuevo");
        }
    }
    /**
     * Método que permite guardar en un fichero el arraylist de personas que se le pasa por parámetro.
     */
    public static void savePersonas(ArrayList<Persona> personas){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTAFICHERO_PERSONAS));
            outputStream.writeObject(personas);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            PELN.out("Error al intentar guardar los datos");
            PELN.out("Inténtelo de nuevo.");
        }
    }
    /**
     * Método que devuelve un arraylist con todas las personas guardadas en el fichero.
     */
    public static ArrayList<Persona> loadPersonas() {
        ArrayList<Persona> personas = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RUTAFICHERO_PERSONAS));
            personas = (ArrayList<Persona>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            personas = new ArrayList<Persona>();
            PELN.out("Error al importar el fichero");
            e.printStackTrace();
            e.getMessage();
        }
        return personas;
    }
    /**
     * Método que comprueba que exista los archivos de datos.
     * En caso de que no existan estos archivos, los crea con datos iniciales para poder realizar las pruebas.
     */
    public static void checkFicheroPersonas() {
        if (!FICHERO_PERSONAS.exists()) {
            createArchivoPersonas();
            initialData();
        }
    }     
    /**
     * Crea el archivo de datos en el que se va a almacenar el array-list de muebles
     */
    public static void createArchivoMuebles() { 
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
    public static ArrayList<Mueble> loadMuebles() {
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
    
        ArrayList<Persona> personas = new ArrayList<Persona>();		
        personas.add(new Jefe("bruce", "pass", "Bruce", "Dickinson", "666-123456", "12345678A"));
        personas.add(new Jefe("steve", "pass", "Steve", "Harris", "666-123457", "12345679B"));
        personas.add(new Comercial("page", "pass", "Jimmy", "Page", "666-123458", "12345680C", "Londres"));
        personas.add(new Comercial("plant", "pass", "Robert", "Plant", "666-123459", "12345681D", "Mancester"));
        personas.add(new ArtesanoEnPlantilla("james", "pass", "James", "Hetfield", "666-123460", "12345682E"));
        personas.add(new ArtesanoEnPlantilla("lars", "pass", "Lars", "Ulrich", "666-123461", "12345683F"));
        personas.add(new ArtesanoPorHoras("cliff", "pass", "Cliff", "Burton", "666-123462", "12345684G"));
        personas.add(new ArtesanoPorHoras("trujillo", "pass", "Robert", "Trujillo", "666-123463", "12345686H"));
        personas.add(new ArtesanoPorHoras("kirk", "pass", "Kirk", "Hammett", "666-123464", "12345687I"));
  	
        personas.add(new ClientePersona("lemmy", "pass", "Lemmy", "Kilmister", "666-123465", "12345688J", 3));
        personas.add(new ClientePersona("dio", "pass", "Ronnie", "James", "666-123466", "12345689K", 3));
        personas.add(new ClientePersona("ozzy", "pass", "Ozzy", "James", "666-123467", "12345690L", 4));
        personas.add(new ClientePersona("iommi", "pass", "Tony", "Iommi", "666-123468", "12345691M", 4));
        personas.add(new ClienteEmpresa("ghost", "pass", "Ghost B.C.", "666-123469", "A12345692", 3, "Tobias Forge"));
        personas.add(new ClienteEmpresa("ok", "pass", "Ok Computer", "666-123470", "A12345693", 3, "Thom York"));
        personas.add(new ClienteEmpresa("rubber", "pass", "Rubber Factory", "666-123471", "A12345694", 4, "Dan Auerbach"));
        personas.add(new ClienteEmpresa("fabfour", "pass", "The Fab Four", "666-123472", "A12345695", 4, "Richard Starkey"));		
        savePersonas(personas);
        
        ArrayList<Mueble> muebles = new ArrayList<Mueble>();
        saveMuebles(muebles);
        
        // ********** LOGIN **********
        
    }
	
    
}