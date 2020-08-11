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
import java.util.Scanner;
import java.util.Date;
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
    private static final Scanner scanner = new Scanner(System.in);
    private static ArrayList<String> opciones = new ArrayList<String>();
    private static int eleccionUsuario;
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
     * Método que devuelve un arraylist con todos los empleados guardados en el fichero.
     */
    public static ArrayList<Empleado> loadEmpleados() {
        ArrayList<Persona> personas = loadPersonas();
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        for (Persona auxPersona : personas){
            if(auxPersona instanceof Empleado){
                empleados.add((Empleado)auxPersona);
            }
        }
        return empleados;
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
        
        //añadimos al arraylist objetos de tipo Empleado
        personas.add(new Jefe("bruce", "pass", "Bruce", "Dickinson", "666-123456", "12345678A"));
        personas.add(new Jefe("steve", "pass", "Steve", "Harris", "666-123457", "12345679B"));
        personas.add(new Comercial("page", "pass", "Jimmy", "Page", "666-123458", "12345680C", "Londres"));
        personas.add(new Comercial("plant", "pass", "Robert", "Plant", "666-123459", "12345681D", "Mancester"));
        personas.add(new ArtesanoEnPlantilla("james", "pass", "James", "Hetfield", "666-123460", "12345682E"));
        personas.add(new ArtesanoEnPlantilla("lars", "pass", "Lars", "Ulrich", "666-123461", "12345683F"));
        personas.add(new ArtesanoPorHoras("cliff", "pass", "Cliff", "Burton", "666-123462", "12345684G"));
        personas.add(new ArtesanoPorHoras("trujillo", "pass", "Robert", "Trujillo", "666-123463", "12345686H"));
        personas.add(new ArtesanoPorHoras("kirk", "pass", "Kirk", "Hammett", "666-123464", "12345687I"));
        
        //añadimos al arraylist objetos de tipo Empleado
        personas.add(new ClientePersona("lemmy", "pass", "Lemmy", "Kilmister", "666-123465", "12345688J", 3));
        personas.add(new ClientePersona("dio", "pass", "Ronnie", "James", "666-123466", "12345689K", 3));
        personas.add(new ClientePersona("ozzy", "pass", "Ozzy", "James", "666-123467", "12345690L", 4));
        personas.add(new ClientePersona("iommi", "pass", "Tony", "Iommi", "666-123468", "12345691M", 4));
        personas.add(new ClienteEmpresa("ghost", "pass", "Ghost B.C.", "666-123469", "A12345692", 3, "Tobias Forge"));
        personas.add(new ClienteEmpresa("ok", "pass", "Ok Computer", "666-123470", "A12345693", 3, "Thom York"));
        personas.add(new ClienteEmpresa("rubber", "pass", "Rubber Factory", "666-123471", "A12345694", 4, "Dan Auerbach"));
        personas.add(new ClienteEmpresa("fabfour", "pass", "The Fab Four", "666-123472", "A12345695", 4, "Richard Starkey"));
        
        //creamos el arraylist de muebles(vacío) y lo añadimos al fichero
        ArrayList<Mueble> muebles = new ArrayList<Mueble>();
        saveMuebles(muebles);
    }
    
    // ********** Menu Jefe **********
    
    /**
     * Método que permite al Jefe consultar la lista de todos los empleados con los datos más relevantes
     */
    public static void verListaEmpleados(){
        ArrayList<Empleado> empleados = loadEmpleados();
        PLN.out(tools.Tabla.listaEmpleados(empleados));        
    } 
    /**
     * Método que permite al jefe "contratar" a través de un formulario.
     */
    public static void crearEmpleado(){
        ArrayList<Persona> personas = loadPersonas();
        boolean existe = false;
        Empleado nuevoEmpleado = null;
        P.out("\nNOMBRE: ");
        String nombre = setDato("nombre", false,  0, 0, 15);
        P.out("\nAPELLIDO: ");        
        String apellidos = setDato("apellido", true,  0, 0, 15);
        P.out("\nALIAS DE USUARIO: ");
        String usuario = "";
        do{
            usuario = setDato("nick", false, 0, 0, 15);
            existe = comprobarNick(usuario);
        } while (existe);
        P.out("\nCONTRASEÑA INICIAL: ");
        String contrasena = setDato("password", false, 0, 4, 15);
        P.out("\nNIF/CIF: ");
        String nif = setDato("NIF/CIF", false, 9, 0, 0);
        P.out("\nTELÉFONO: ");
        String telefono = setDato("teléfono", true, 0, 0, 0);
        P.out("\nPUESTO: ");
        opciones.add("\n0 = Cancelar");
        opciones.add("1 = Jefe");
        opciones.add("2 = Comercial");
        opciones.add("3 = Artesano en plantilla");
        opciones.add("4 = Artesano por horas");  
        int eleccionUsuario = elegirOpcion();    
        switch (eleccionUsuario) {
            case 0:
               System.out.print('\u000C');
               PLN.out("LISTA DE EMPLEADOS");
               PLN.out("==================\n");
               Controlador.verListaEmpleados();
               Menu.menuJefeEmpleados();
            case 1:
                nuevoEmpleado = new Jefe(usuario, contrasena, nombre, apellidos, telefono, nif);
                break;
            case 2:
                String zona = setDato("ZONA: ", false, 0, 0, 0);
                nuevoEmpleado = new Comercial(usuario, contrasena, nombre, apellidos, telefono, nif, zona);
                break;        
            case 3:
                nuevoEmpleado = new ArtesanoEnPlantilla(usuario, contrasena, nombre, apellidos, telefono, nif);
                break;
            case 4:
                nuevoEmpleado = new ArtesanoPorHoras(usuario, contrasena, nombre, apellidos, telefono, nif);
        }  
        personas.add(nuevoEmpleado);
        savePersonas(personas);
        Menu.menuJefeEmpleados();   
    }
    /**
     * Método con el que el jefe despide a un empleado.
     * Despedir un empleado supone que la fecha de baja deja de ser nula y tiene dos consecuencias inmediatas:
     *  1) El empleado despedido no puede loguearse en la aplicación.
     *  2) Si el empleado es un Artesano los muebles asignados a este usuario quedan desasignados.
     *      Y si el empleado es un comercial, los clientes asignados a este comercial quedan con el campo id_comercial como nulo.
     *  Como condición, un jefe no puede despedirse a si mismo.
     */
    public static void despedirEmpleado(){
        ArrayList<Persona> personas = loadPersonas();
        int id = -1;
        boolean existe = false;
        id = seleccionarIdEmpleado();
        for (Persona auxPersona : personas){
            if (auxPersona instanceof Empleado){                
                if (((Empleado)auxPersona).getIdEmpleado() == id){
                    if(((Empleado)auxPersona).getFechaBaja() != null){
                        PLN.out("Este empleado ya no formaba parte de tu plantilla");
                    }
                    else{
                        boolean confirmar = tools.Herramientas.confirmarDecision();
                        if (confirmar){
                            ((Empleado)auxPersona).setFechaBaja();
                            personas.add(auxPersona);
                            savePersonas(personas);
                            PLN.out("El empleado ya no forma parte de la plantilla");
                        }else{
                            PLN.out("Acción cancelada por el usuario");
                        }
                    }
                    existe = true;
                    break;
                }                    
            }
        }                     
        if(!existe){
            PLN.out("No existe un usuario con ese Id.");
        }
        tools.Herramientas.enterParaContinuar();
        Menu.menuJefeEmpleados();
    }
    /**
     * Método que comprueba si el nick que pasamos como parámetro corresponde a algún usuario existente
     */
    private static boolean comprobarNick(String usuario){
        ArrayList<Persona> personas = loadPersonas();
        boolean existe = false;
        for(Persona auxPersona : personas){
            if (usuario.equalsIgnoreCase(auxPersona.getUsuario())){
                existe = true;
                P.out("\nYa existe un usuario con ese nick.\n\nIndique otro nick: ");
                break;
            }
        }
        return existe;
    }
    /**
     * Método que permite que el usuario establezca el valor de un atributo estableciendo unos límites al valor.
     */
    private static String setDato(String campo, boolean nulo, int exacto, int min, int max){
        String valor = "";
        boolean right = false;
        do{
            try {
                valor = scanner.nextLine();
                if (!nulo && valor.trim().equals("")) {
                    PLN.out("El " + campo + " del usuario no puede estar vacío");
                } else if (exacto != 0 && valor.length() != exacto) {
                    PLN.out("El " + campo + " del usuario debe tener exactamente " + exacto + " caracteres");
                }else if (max != 0 && valor.length() > max) {
                    PLN.out("El " + campo + " del usuario no puede superar los " + max + " caracteres");
                } else if (min != 0 && valor.length() < min) {
                    PLN.out("El " + campo + " del usuario no puede tener menos de " + min + " caracteres");
                }else {
                    right = true;
                }
            } catch (Exception e) {
                PLN.out("6");
                PLN.out("Valor incorrecto.");
                scanner.nextLine();
            }
            if (!right) {
                P.out("\nIndique otro " + campo +": ");
            }
        } while (!right);        
        return valor;
    }
    
    public static int elegirOpcion() {
        for (String string : opciones) {
            PLN.out(string);
        }
        eleccionUsuario = -1;
        boolean allRight = false;
        PLN.out("Escoja una opción: ");
        do {
            try {
                eleccionUsuario = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (eleccionUsuario < 0) {
                    PLN.out("No existen opciones con números negativos");
                } else if (eleccionUsuario >= opciones.size()) {
                    PLN.out("No hay ninguna opción con ese número");
                } else {
                    allRight = true;
                }
            } catch (Exception e) {
                PLN.out("Valor incorrecto.");
                eleccionUsuario = -1;
                scanner.nextLine();
            }
            if (!allRight) {
                P.out("\nVuelva a escoger una opción: ");
            }
        } while (!allRight);
        return eleccionUsuario;
    }
    public static int seleccionarIdEmpleado() {        
        ArrayList<Persona> personas = loadPersonas();
        int id = -1;
        boolean allRight = false;
        P.out("Indique el id del empleado: ");
        do {           
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                PLN.out("id = "+id);
                if (id < 0) {
                    PLN.out("No existen Ids negativos");
                } else {                   
                    allRight = true;
                    PLN.out("allRight = " + allRight);
                }
            } catch (Exception e) {
                PLN.out("Valor incorrecto.");
                id = -1;
                scanner.nextLine();
            }
            if(!allRight) {
                P.out("Indique otro Id: ");
            }
        } while (!allRight);
        return id;
    }    
}