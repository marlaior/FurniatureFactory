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
import pedidos.*;

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
    private static final String RUTAFICHERO_PEDIDOS = "pedidos.dat";
    private static final File FICHERO_PERSONAS = new File(RUTAFICHERO_PERSONAS);
    private static final File FICHERO_PEDIDOS = new File(RUTAFICHERO_PEDIDOS);
    
    // ********** GESTIÓN DE ARCHIVOS **********
    
    /**
     * Crea el archivo de datos en el que se va a almacenar el arraylist de personas
     */
    public static void createArchivoPersonas() { 
        try {
            FICHERO_PERSONAS.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FICHERO_PERSONAS));
            outputStream.writeObject(new ArrayList<Persona>());
            outputStream.close();
        } catch (Exception e) {
            PELN.out("No existe el archivo de personas y no se ha podido crear uno nuevo");
            PELN.out("Inténtelo de nuevo");
        }
    }
    /**
     * Crea el archivo de datos en el que se va a almacenar el arraylist de pedidos
     */
    public static void createArchivoPedidos() { 
        try {
            FICHERO_PEDIDOS.createNewFile();
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(FICHERO_PEDIDOS));
            outputStream.writeObject(new ArrayList<Pedido>());
            outputStream.close();
        } catch (Exception e) {
            PELN.out("No existe el archivo de pedidos y no se ha podido crear uno nuevo");
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
     * Método que permite guardar en un fichero el arraylist de pedidos que se le pasa por parámetro.
     */
    public static void savePedidos(ArrayList<Pedido> pedidos){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(RUTAFICHERO_PEDIDOS));
            outputStream.writeObject(pedidos);
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
     * Método que devuelve un arraylist con todas las personas guardadas en el fichero.
     */
    public static ArrayList<Pedido> loadPedidos() {
        ArrayList<Pedido> pedidos = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(RUTAFICHERO_PEDIDOS));
            pedidos = (ArrayList<Pedido>) inputStream.readObject();
            inputStream.close();
        } catch (Exception e) {
            pedidos = new ArrayList<Pedido>();
            PELN.out("Error al importar el fichero");
            e.printStackTrace();
            e.getMessage();
        }
        return pedidos;
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
     * Método que devuelve un arraylist con todos los clientes guardados en el fichero.
     */
    public static ArrayList<Cliente> loadClientes() {
        ArrayList<Persona> personas = loadPersonas();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (Persona auxPersona : personas){
            if(auxPersona instanceof Cliente){
                clientes.add((Cliente)auxPersona);
            }
        }
        return clientes;
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
     * Método que comprueba que exista los archivos de datos.
     * En caso de que no existan estos archivos, los crea.
     */
    public static void checkFicheroPedidos() {
        if (!FICHERO_PEDIDOS.exists()) {
            createArchivoPedidos();
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
        
        //añadimos al arraylist objetos de tipo Cliente
        personas.add(new ClientePersona("lemmy", "pass", "Lemmy", "Kilmister", "666-123465", "12345688J", 3));
        personas.add(new ClientePersona("dio", "pass", "Ronnie", "James", "666-123466", "12345689K", 3));
        personas.add(new ClientePersona("ozzy", "pass", "Ozzy", "James", "666-123467", "12345690L", 4));
        personas.add(new ClientePersona("iommi", "pass", "Tony", "Iommi", "666-123468", "12345691M", 4));
        personas.add(new ClienteEmpresa("ghost", "pass", "Ghost B.C.", "666-123469", "A12345692", 3, "Tobias Forge"));
        personas.add(new ClienteEmpresa("ok", "pass", "Ok Computer", "666-123470", "A12345693", 3, "Thom York"));
        personas.add(new ClienteEmpresa("rubber", "pass", "Rubber Factory", "666-123471", "A12345694", 4, "Dan Auerbach"));
        personas.add(new ClienteEmpresa("fabfour", "pass", "The Fab Four", "666-123472", "A12345695", 4, "Richard Starkey"));
        
        //creamos el arraylist de pedidos(vacío) y lo añadimos al fichero
        ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
        savePedidos(pedidos);
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
               Menu.menuGestionEmpleados();
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
        Menu.menuGestionEmpleados();   
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
        Menu.menuGestionEmpleados();
    }
    
        // ********** Menu Comercial **********
    
    /**
     * Método que permite al Comercial consultar la lista de todos los clientes a su cargo.
     */
    public static void verListaClientesComercial(int id_comercial){
        ArrayList<Cliente> clientes = loadClientes();
        ArrayList<Cliente> clientesComercial = new ArrayList<Cliente>();
        for (Cliente auxCliente : clientes){
            if(auxCliente.getIdComercial() == id_comercial){
                clientesComercial.add(auxCliente);
            }
        }
        if(clientesComercial.size()==0){
            PLN.out("Su lista de clientes está vacía.\n¿Quiere inscribir su primer cliente?.");
        }
        else{
            PLN.out(tools.Tabla.listaClientes(clientesComercial));             
        }        
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
     * Método que permite al comercial inscribir un nuevo cliente a través de un formulario.
     */
    public static void crearCliente(int idComercial){
        int tipoCliente = tipoCliente();
        if (tipoCliente != 0){
            ArrayList<Persona> personas = loadPersonas();
            boolean existe = false;        
            boolean correcto = false;            
            Cliente nuevoCliente = null;   
            String nombre = "";
            if (tipoCliente == 1){
                P.out("\nRAZÓN SOCIAL: ");
                nombre = setDato("Razón social", false,  0, 0, 15);
            }else{
                P.out("\nNOMBRE: ");
                nombre = setDato("nombre", false,  0, 0, 15);
            }
            String apellido = "";
            if (tipoCliente != 1){
                P.out("\nAPELLIDO: ");        
                apellido = setDato("apellido", true,  0, 0, 15);                
            }       
            P.out("\nALIAS DE USUARIO: ");
            String usuario = "";
            do{
                usuario = setDato("nick", false, 0, 0, 15);
                existe = comprobarNick(usuario);
            } while (existe);
            String nif = "";
            if (tipoCliente == 1){
                P.out("\nC.I.F.: ");
                nif = setDato("C.I.F.", false, 9, 0, 0);
            }else{
                P.out("\nN.I.F.: ");
                nif = setDato("N.I.F", false, 9, 0, 0);
            }
            P.out("\nTELÉFONO: ");
            String telefono = setDato("teléfono", true, 0, 0, 0);
            String contacto = "";
            if (tipoCliente == 1){
                P.out("\nPERSONA DE CONTACTO EN LA EMPRESA: ");        
                contacto = setDato("persona de contacto", true,  0, 0, 15);                
            }
            if(tipoCliente == 1){
                nuevoCliente = new ClienteEmpresa(usuario, "pass", nombre, telefono, nif, idComercial, contacto);
            }else{
                nuevoCliente = new ClientePersona(usuario, "pass", nombre, apellido, telefono, nif, idComercial);
            }
            personas.add(nuevoCliente);
            savePersonas(personas);
        }
        Menu.menuGestionClientes();        
    }
    /**
     * Método que permita que el usuario establezca el tipo de cliente que quiere inscribir
     */
    private static boolean tipoCLiente(){
        boolean isEmpresa = false;
        boolean correcto = false;
        String respuesta = "";
        PLN.out("¿Es el cliente una Empresa o una persona física?");
        P.out("'e' = Empresa / 'p' = Persona física");
        do{
            try{
                respuesta = scanner.nextLine();
                if (respuesta.equalsIgnoreCase("e")){
                    correcto = true;
                    isEmpresa = true;
                }else if(respuesta.equalsIgnoreCase("p")){
                    correcto = true;
                    isEmpresa = false;
                }else correcto = false;
            }catch(Exception e){
                PLN.out("El dato introducido no es correcto. Inténtelo de nuevo.");
                correcto = false;
            }            
        }while(!correcto);
        return isEmpresa;
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
    // ********** Menu cliente **********
    /**
     * Método que diseña un formulario de compra de muebles por parte de un cliente
     */
    public static void comprarMuebles(int id_cliente){
        boolean pedidoTerminado = false;
        ArrayList<Cliente> clientes = loadClientes();
        ArrayList<Pedido> pedidos = loadPedidos();
        ArrayList<Mueble> muebles = new ArrayList<Mueble>();
        Cliente cliente = null;
        
        for (Cliente clienteAux : clientes){
            if (clienteAux.getIdCliente() == id_cliente){
                cliente = clienteAux;
                break;
            }
        }      
        do{
            int idMueble = seleccionarIdMueble();
            int cantidad = 0;
      
            switch (idMueble){
                case 0: 
                    PLN.out("¿Finalizar el pedido?");
                    boolean salir = tools.Herramientas.confirmarDecision();                    
                    if(salir){
                        pedidoTerminado = true;
                    }
                    break;
                case 1:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new Mueble(cliente));                        
                        }                    
                    }                    
                    break;
                case 2:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new Mesa(cliente));                        
                        }                    
                    }
                    break;
                case 3:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new MesaCafe(cliente));                        
                        }                    
                    }
                    break;
                case 4:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new MesaCafeCristal(cliente));                        
                        }                    
                    }
                    break;
                case 5:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new MesaCafeMadera(cliente));                        
                        }                    
                    }
                    break;
                case 6:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new MesaDormitorio(cliente));                        
                        }                    
                    }
                    break;
                case 7:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new MesaComedor(cliente));                        
                        }                    
                    }
                    break;
                case 8:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new Silla(cliente));                        
                        }                    
                    }
                    break;
                case 9:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new SillaCocina(cliente));                        
                        }                    
                    }
                    break;
                case 10:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new SillaOficina(cliente));                        
                        }                    
                    }
                    break;
                case 11:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new SillaOficinaConRuedas(cliente));                        
                        }                    
                    }
                    break;
                case 12:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new SillaOficinaSinRuedas(cliente));                        
                        }                    
                    }
                    break;
                case 13:
                    cantidad = setCantidad();
                    if (cantidad != 0){
                        for (int i = 0; i < cantidad; i++){
                            muebles.add(new SillaPlegable(cliente));                        
                        }                    
                    }
                    break;         
            }       
        }while(!pedidoTerminado);
        if (muebles.size() > 0) {
            Pedido pedido = new Pedido(muebles, cliente);
            pedidos.add(pedido);
            savePedidos(pedidos);
        }
    }
    
    // ********** Métodos generales **********
    
    /**
     * Método que gestiona la elección de una opción del menú por parte del usuario, retornando la opción elegida
     */    
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
    /**
     * Método para que el usuario indique si el cliente con el que está trabajando es una persona física o una empresa.
     * Se admiten tres valores 1= empresa, 2 = persona física y 0 = cancelar todo el proceso.
     */    
    private static int tipoCliente(){
        int tipo = -1;
        PLN.out("Indique el tipo de cliente");
        do {           
            try {
                P.out("0 = CANCELAR / 1 = EMPRESA / 2 = PERSONA: ");
                tipo = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                
                if (tipo != 0 && tipo != 1 && tipo != 2) {
                    tipo = -1;
                }
            } catch (Exception e) {
                tipo = -1;
            }
            if(tipo != 0 && tipo != 1 && tipo != 2) {
                PLN.out("\nOpción incorrecta. Inténtelo de nuevo.");
            }
        } while (tipo != 0 && tipo != 1 && tipo != 2);
        return tipo;        
    }
    /**
     * Método para que el usuario indique un id de empleado32wedx
     */
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
    /**
     * Método para que el usuario indique un id de mueble
     */
    public static int seleccionarIdMueble() {        
        int id = -1;
        boolean allRight = false;
        P.out("Indique el id del mueble (0 = Terminar pedido): ");
        do {           
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (id < 0 || id > 13) {
                    PLN.out("No hay muebles con ese id.");
                } else {                   
                    allRight = true;
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
    /**
     * Método que sirve para que el cliente indique la cantidad de muebles que quiere comprar
     */
    public static int setCantidad(){
        int cantidad = 0;
        boolean allRight = false;
        P.out("Cantidad (0: ");
        do {           
            try {
                cantidad = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (cantidad < 0) {
                    PLN.out("No se permiten cantidades negativas");
                }else {                   
                    allRight = true;
                }
            } catch (Exception e) {
                PLN.out("Valor incorrecto.");
                cantidad = -1;
                scanner.nextLine();
            }
            if(!allRight) {
                P.out("Inténtelo de nuevo: ");
            }
        } while (!allRight);
        return cantidad;
    }

}