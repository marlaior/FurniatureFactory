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
    /**
     * Método que imprime la lista completa de pedidos
     */
    public static boolean verListaCompletaPedidos(){
        ArrayList<Pedido> pedidos = loadPedidos();
        boolean tienePedidos = false;
        if(pedidos.size() != 0){
            PLN.out(tools.Tabla.listaPedidosJefe(pedidos));
            tienePedidos = true;
        }
        return tienePedidos;            
    }
    /**
     * Método que imprime la lista de pedidos no asignados a ningún artesano (todos sus muebles estarán en estado PENDIENTE)
     */
    public static boolean verListaPedidosNoAsignados(){
        boolean tienePedidos = false;
        ArrayList<Pedido> pedidos = loadPedidos();
        ArrayList<Pedido> pedidosNoAsignados = new ArrayList<Pedido>();
        ArrayList<Mueble> muebles = new ArrayList<Mueble>();
        for (Pedido auxPedido : pedidos){
            muebles = auxPedido.getMuebles();
            for(Mueble auxMueble : muebles){
                if(auxMueble.getEstado().equals(Mueble.Estado.PENDIENTE)){
                    pedidosNoAsignados.add(auxPedido);
                }
                break; // basta con consultar el estado de un solo mueble del pedido.
            }           
        }
        if(pedidosNoAsignados.size() != 0){
            PLN.out(tools.Tabla.listaPedidosJefe(pedidosNoAsignados));
            tienePedidos = true;
        }
        return tienePedidos;            
    }
    /**
     * Método que permite asignar la construcción de todos los muebles de un pedido a un artesano
     */
    public static void asignarPedido(int numPedido){
        ArrayList<Pedido> pedidos = loadPedidos();
        ArrayList<Persona> personas = loadPersonas();
        Pedido pedido = null;
        Artesano artesano = null;
        int idArtesano = 0;
        for(Pedido auxPedido : pedidos){
            if(auxPedido.getNumPedido() == numPedido){
                pedido = auxPedido;
                break;
            }
        }
        if(pedido == null){
            PLN.out("No se han podido cargar los datos del pedido nº" + numPedido);            
        }else{
            idArtesano = seleccionarArtesano();
            for(Persona auxPersona : personas){
                if(auxPersona instanceof Artesano){
                    if(((Artesano)auxPersona).getIdEmpleado() == idArtesano){
                        for(Mueble auxMueble : pedido.getMuebles()){
                            auxMueble.setArtesano((Artesano)auxPersona);
                            auxMueble.setEstado(Mueble.Estado.ASIGNADO);
                            auxMueble.setAnotaciones(
                            new Date() +
                            "\n\tAsignada la construcción al artesano " + ((Artesano)auxPersona).getNombre() + " " + ((Artesano)auxPersona).getApellidos());
                            PLN.out("Se ha asignado el mueble con nº de serie " + auxMueble.getNumSerie() + 
                                " al artesano " + ((Artesano)auxPersona).getNombre() + " " + ((Artesano)auxPersona).getApellidos());
                        }
                    }
                }
            } 
            savePedidos(pedidos);           
        }
        tools.Herramientas.enterParaContinuar();                
    }
    /**
     * Método que imprime la lista completa de artesanos que trabajan para la fábrica
     */
    public static void listaArtesanos(){
        ArrayList<Persona> personas = loadPersonas();
        ArrayList<Artesano> artesanos = new ArrayList<Artesano>();
        for(Persona auxPersona : personas){
            if(auxPersona instanceof Artesano){
                artesanos.add((Artesano)auxPersona);
            }
        }
        PLN.out(tools.Tabla.listaArtesanos(artesanos));
    }
    /**
     * Método que imprime la lista de muebles de un pedido pasado por parámetro
     */
    public static void listaMueblesPedido(Pedido pedido){
        ArrayList<Mueble> mueble = pedido.getMuebles();
        PLN.out(tools.Tabla.listaMueblesPedido(mueble));
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
    
                // ********** Menu Artesano **********
                
    /**
     * Método que muestra la lista de pedidos que tiene asignado el artesano cuyo id se pasa como argumento
     */
    public static void listaPedidosAsignados(int idArtesano){
        ArrayList<Persona> personas = loadPersonas();
        ArrayList<Pedido> pedidos = loadPedidos();
        ArrayList<Pedido> pedidosAsignados = new ArrayList<Pedido>();
        boolean tienePedidosAsignados = false;
        // comprobamos los pedidos asignados al usuario y los añadimos a un arraylist
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                if(auxMueble.getArtesano() != null && auxMueble.getArtesano().getIdEmpleado() == idArtesano){
                    pedidosAsignados.add(auxPedido);
                    break;
                }
            }
        }        
        if(pedidosAsignados.size() != 0){
            tienePedidosAsignados = true;
            PLN.out(tools.Tabla.listaMueblesAsignados(pedidosAsignados));
        }      
    }
    /**
     * Método que inicia la construccion del mueble cuyo id se pasa por parametro
     */
    public static void iniciarConstruccionMueble(int numSerie){
        ArrayList<Pedido> pedidos = loadPedidos();
        boolean muebleIniciado = false;
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                if(auxMueble.getNumSerie() == numSerie){
                    auxMueble.setEstado(Mueble.Estado.EN_CONSTRUCCION);
                    auxMueble.setAnotaciones(
                        "\n"+new Date() +
                        "\n\t" + auxMueble.getArtesano().getNombre() + " " + auxMueble.getArtesano().getApellidos() + " inició la construcción.\n"
                    );
                    muebleIniciado = true;
                    PLN.out("Se ha iniciado la construcción del mueble con nº de serie " + auxMueble.getNumSerie());
                    tools.Herramientas.enterParaContinuar();
                    savePedidos(pedidos);
                    break;
                }
            }
            if(muebleIniciado){ // si ya se ha encontrado e iniciado el mueble que buscamos, salimos del bucle.
                break;
            }
        }
    }
    /**
     * Método que pausa la construccion de un mueble cuyo id se pasa por parámetro
     */
    public static void pausarConstruccionMueble(int numSerie){
        ArrayList<Pedido> pedidos = loadPedidos();
        boolean mueblePausado = false;
        String comentario = escribeComentario();
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                if(auxMueble.getNumSerie() == numSerie){
                    auxMueble.setEstado(Mueble.Estado.PAUSADO);
                    auxMueble.setAnotaciones(
                        "\n" + new Date() +
                        "\n\t" + auxMueble.getArtesano().getNombre() + " " + auxMueble.getArtesano().getApellidos() + " pausó la construcción del mueble." +
                        "\n\tComentario del artesano: " + comentario + "\n"
                    );
                    mueblePausado = true;
                    PLN.out("Se ha pausado la construcción del mueble con nº de serie " + auxMueble.getNumSerie());
                    tools.Herramientas.enterParaContinuar();
                    savePedidos(pedidos);
                    break;
                }
            }
            if(mueblePausado){ // si ya se ha encontrado e iniciado el mueble que buscamos, salimos del bucle.
                break;
            }
        }
    } 
    /**
     * Método que reanuda la construcción de un mueble cuyo id se pasa por parámetro
     */
    public static void reanudarConstruccionMueble(int numSerie){
        ArrayList<Pedido> pedidos = loadPedidos();
        boolean muebleReanudado = false;
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                if(auxMueble.getNumSerie() == numSerie){
                    auxMueble.setEstado(Mueble.Estado.EN_CONSTRUCCION);
                    auxMueble.setAnotaciones(
                        "\n" + new Date() +
                        "\n\t" + auxMueble.getArtesano().getNombre() + " " + auxMueble.getArtesano().getApellidos() + " ha reanudado la construcción del mueble." + "\n"
                    );
                    muebleReanudado = true;
                    PLN.out("Se ha reanudado la construcción del mueble con nº de serie " + auxMueble.getNumSerie());
                    tools.Herramientas.enterParaContinuar();
                    savePedidos(pedidos);
                    break;
                }
            }
            if(muebleReanudado){ // si ya se ha encontrado e iniciado el mueble que buscamos, salimos del bucle.
                break;
            }
        }
    } 
    /**
     * Método que finaliza la construcción de un mueble cuyo id se pasa por parámetro
     */
    public static void finalizarConstruccionMueble(int numSerie){
        ArrayList<Pedido> pedidos = loadPedidos();
        boolean muebleFinalizado = false;
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                if(auxMueble.getNumSerie() == numSerie){
                    auxMueble.setEstado(Mueble.Estado.TERMINADO);
                    auxMueble.setAnotaciones(
                        "\n" + new Date() +
                        "\n\t" + auxMueble.getArtesano().getNombre() + " " + auxMueble.getArtesano().getApellidos() + " finalizó la construcción del mueble." + "\n"
                    );
                    muebleFinalizado = true;
                    PLN.out("Se ha finalizado la construcción del mueble con nº de serie " + auxMueble.getNumSerie());
                    tools.Herramientas.enterParaContinuar();
                    savePedidos(pedidos);
                    break;
                }
            }
            if(muebleFinalizado){ // si ya se ha encontrado e iniciado el mueble que buscamos, salimos del bucle.
                break;
            }
        }
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
        Mueble nuevoMueble = null;
        int cantidad = 0;
        int idMueble = -1; // id del modelo de mueble en el catálogo
        int numSerie = 1;
        
        for (Cliente clienteAux : clientes){
            if (clienteAux.getIdCliente() == id_cliente){
                cliente = clienteAux;
                break;
            }
        }
        // comprobamos cuantos muebles se han construido para calcular el nº de serie del siguiente mueble
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                numSerie++;
            }
        }      
        do{
            idMueble = seleccionarIdMueble(); 
            if(idMueble == 0){
                PLN.out("Ha elegido finalizar el pedido.");
                boolean salir = tools.Herramientas.confirmarDecision();                    
                if(salir){
                    pedidoTerminado = true;
                }
            }else{       
                cantidad = setCantidad();          
                switch (idMueble){    
                    case 1:
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new Mueble(cliente);
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        }                                                            
                        break;
                    case 2:
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new Mesa(cliente); 
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        }                                                
                        break;
                    case 3:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new MesaCafe(cliente);   
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 4:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new MesaCafeCristal(cliente);  
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 5:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new MesaCafeMadera(cliente);
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 6:                         
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new MesaDormitorio(cliente); 
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 7:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new MesaComedor(cliente);  
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 8:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new Silla(cliente);  
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 9:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new SillaCocina(cliente);  
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 10:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new SillaOficina(cliente);  
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 11:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new SillaOficinaConRuedas(cliente);  
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 12:                         
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new SillaOficinaSinRuedas(cliente);
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;
                    case 13:                        
                        for (int i = 0; i < cantidad; i++){
                            nuevoMueble = new SillaPlegable(cliente);
                            nuevoMueble.setNumSerie(numSerie);                        
                            numSerie++;
                            muebles.add(nuevoMueble);
                        } 
                        break;         
                }       
            }
        }while(!pedidoTerminado);
                
        if (muebles.size() > 0) {
            Pedido nuevoPedido = new Pedido(muebles, cliente);
            PLN.out("Creado pedido nº " + nuevoPedido.getNumPedido());
            pedidos.add(nuevoPedido);
            savePedidos(pedidos);
            pedidos = loadPedidos();
            for(Pedido auxPedido : pedidos){
                PLN.out("Nº Pedido = "+auxPedido.getNumPedido());
            }            
        }
    }
    /**
     * Método que permite consultar los pedidos del cliente cuyo id se pasa por parámetro
     * El método consulta la lista de pedidos del cliente y devuelve true o false según tenga o no tenga pedidos.
     * Además, en caso de tener pedidos, imprime una tabla con la lista de pedidos y su estado actual.
     */
    public static boolean consultaPedidosCliente(int id_cliente){
        ArrayList<Cliente> clientes = loadClientes();
        ArrayList<Pedido> pedidos = loadPedidos();
        ArrayList<Pedido> pedidosCliente = new ArrayList<Pedido>();
        Cliente cliente = null;
        boolean tienePedidos = false;
        
        for (Cliente clienteAux : clientes){
            if (clienteAux.getIdCliente() == id_cliente){
                cliente = clienteAux;
                break;
            }
        }
        for (Pedido auxPedido : pedidos){
            if (auxPedido.getCliente().getIdCliente() == id_cliente){
                pedidosCliente.add(auxPedido);                
            }
        }
        if (pedidosCliente.size() == 0){
            tienePedidos = false;
        }else{
            PLN.out(tools.Tabla.listaPedidosCliente(pedidosCliente));
            tienePedidos = true;
        }
        return tienePedidos;
    }
    /**
     * Método que muestra la lista de muebles de un pedido cuyo id se pasa como parámetro
     */
    public static void consultaListaMueblesPedido(int numPedido, int id_cliente){
        ArrayList<Pedido> pedidos = loadPedidos();
        Pedido pedido = null;
        for (Pedido auxPedido : pedidos){
            if(auxPedido.getNumPedido() == numPedido && auxPedido.getCliente().getIdCliente() == id_cliente){
                pedido = auxPedido;
                break;
            }
            
        }
        if (pedido == null){
            PLN.out("No se han encontrado datos");
        }else{
            System.out.print('\u000C');
            PLN.out("\nPEDIDO Nº " + pedido.getNumPedido());
            PLN.out("===============");
            PLN.out(tools.Tabla.listaMueblesPedido(pedido.getMuebles()));                
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
     * Método para que el usuario indique un id de empleado
     */
    public static int seleccionarIdEmpleado() {        
        ArrayList<Persona> personas = loadPersonas();
        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        for(Persona auxPersona : personas){
            if(auxPersona instanceof Empleado){
                empleados.add((Empleado)auxPersona);
            }
        }
        int id = -1;
        boolean allRight = false;
        P.out("Indique el id del empleado: ");
        do {           
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                PLN.out("id = "+id);
                for(Empleado auxEmpleado : empleados){
                    if(id == auxEmpleado.getIdEmpleado()){                  
                        allRight = true;
                    }
                }
            } catch (Exception e) {
                id = -1;
                scanner.nextLine();
            }
            if(!allRight) {
                PLN.out("No hay ningún empleado con ese id.");
                P.out("Indique otro Id: ");
            }
        } while (!allRight);
        return id;
    }    
    /**
     * Método para que el usuario indique un id de empleado
     */
    public static int seleccionarArtesano() {        
        ArrayList<Persona> personas = loadPersonas();
        ArrayList<Artesano> artesanos = new ArrayList<Artesano>();
        for(Persona auxPersona : personas){
            if(auxPersona instanceof Artesano){
                artesanos.add((Artesano)auxPersona);
            }
        }
        int id = -1;
        boolean allRight = false;
        P.out("Indique el id del artesano para asignarle el pedido: ");
        do {           
            try {
                id = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                PLN.out("id = "+id);      
                for(Artesano auxArtesano : artesanos){
                    if(id == auxArtesano.getIdEmpleado()){                            
                        allRight = true;
                    }
                }                
            } catch (Exception e) {
                id = -1;
                scanner.nextLine();
            }
            if(!allRight) {
                PLN.out("No hay ningún artesano con ese id.");
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
        P.out("Cantidad: ");
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
    /**
     * Método que permite que el usuario seleccione el numero de serie de un mueble de un pedido pasado por parámetro
     */
    public static int selectNumSerie(Pedido pedido){
        int numSerie = -1;
        boolean allRight = false;
        P.out("Numero de serie (0 = Regresar al menú principal): ");
        do {           
            try {
                numSerie = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (numSerie == 0) {
                    allRight = true;
                }else {         
                    for(Mueble auxMueble : pedido.getMuebles()){
                        if(numSerie == auxMueble.getNumSerie()){
                            allRight = true;
                        }
                    }                    
                }
            } catch (Exception e) {
                PLN.out("Valor incorrecto.");
                numSerie = -1;
                scanner.nextLine();
            }
            if(!allRight) {
                PLN.out("No hay muebles con ese id. Inténtelo de nuevo");
            }
        } while (!allRight);
        return numSerie;
    }
    /**
     * Método que permite que el usuario escriba un comentario
     */
    private static String  escribeComentario(){
        String comentario = "";
        do{
            try{
                P.out("Motivo = ");
                comentario = scanner.nextLine();
                if(comentario.trim().equals("")){
                    comentario = "";
                    PLN.out("El comentario no puede estar vacío");
                }
            }catch(Exception e){
                comentario = "";
                scanner.nextLine();
            }
        }while(comentario.equals(""));
        return comentario;      
    }
}