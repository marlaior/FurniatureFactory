package fabrica;

import personas.Persona;
import personas.Cliente;
import personas.Empleado;
import personas.Artesano;
import personas.ArtesanoPorHoras;
import personas.ArtesanoEnPlantilla;
import pedidos.Pedido;
import muebles.Mueble;
import java.util.ArrayList;
import java.util.Scanner;
import tools.SoutIF;
import tools.Tabla;

/**
 * Clase que desplegará los menús por los que transitará toda la aplicación
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Menu{
    private static Persona usuarioLogueado = null;
    private static final SoutIF<String> P = (s) -> System.out.print(s);
    private static final SoutIF<String> PLN = (s) -> System.out.println(s);
    private static ArrayList<String> opciones;
    private static int eleccionUsuario;
    private static Scanner scanner;

    /**
     * Constructor de la clase menu
     */
    public Menu(){
        
        opciones = new ArrayList<String>();
        scanner = new Scanner(System.in);
        System.out.print('\u000C');
        PLN.out("   ************************");
        PLN.out("   *     Bienvenido a     *");
        PLN.out("   *  Furniture Factory   *");
        PLN.out("   ************************\n\n");
        tools.Herramientas.enterParaContinuar();      
        menuLogin();
    }
    /**
     * Método que identifica el tipo de usuario que se ha logueado y redirige la aplicación hacia el menú personalizado correspondiente,
     */
    private static void selectMenu(){
        switch(usuarioLogueado.getClass().getSimpleName()){
            case "ArtesanoPorHoras":
            menuArtesano();
            break;
            
            case "ArtesanoEnPlantilla":
            menuArtesano();
            break;
            
            case "Jefe":
            menuJefe();
            break;
            
            case "Comercial":
            menuComercial();
            break;
            
            case "Artesano":
            menuArtesano();
            break;
            
            case "ClienteEmpresa":
            menuCliente();
            break;
            
            case "ClientePersona":
            menuCliente();
            break;
            
            case "Cliente":
            menuCliente();
            break;
            
            default:
            menuLogin();            
        }        
    }    
    /**
     * Menú de entrada de la aplicación en el que el usuario debe loguearse.
     */
    private static void menuLogin(){
        ArrayList<Persona> listaUsuarios = Controlador.loadPersonas();
        String usuario;
        String contrasena;
        boolean existeUsuario = false;
        usuarioLogueado = null;

        System.out.print('\u000C');
        PLN.out(" LOGIN");
        PLN.out("=======");
        PLN.out("Lista de usuarios disponibles\n(se muestran esta lista únicamente para facilitar las pruebas)\n\n");
        PLN.out("\n" + tools.Tabla.listaUsuarios(listaUsuarios) + "\n");
        do{
            existeUsuario = false;
            P.out("USUARIO: ");
            usuario = scanner.nextLine();
            P.out("\nCONTRASEÑA: ");
            contrasena = scanner.nextLine();
            for(Persona auxPersona : listaUsuarios){
                if (auxPersona.getUsuario().equalsIgnoreCase(usuario)){
                    existeUsuario = true;
                    if(auxPersona.getContrasena().equals(contrasena)){
                        System.out.print('\u000C');
                        PLN.out("Te has logueado correctamente.");
                        usuarioLogueado = auxPersona;                        
                    }else PLN.out("Contraseña incorrecta.");
                    break;
                }                
            }
            if(!existeUsuario){
                    PLN.out("No existe usuario con ese nick. Inténtelo de nuevo.");
            }else{
                if(usuarioLogueado instanceof Empleado && ((Empleado)usuarioLogueado).getFechaBaja() != null){
                    PLN.out("Este usuario ya no forma parte de la plantilla de empleados de la fábrica.");
                    usuarioLogueado = null;
                    existeUsuario = false;
                }
            }
        }while(usuarioLogueado == null);
        selectMenu();
    }   
    
    // ******************************
    // *            JEFE            *
    // ******************************    
    
    /**
     * Menú principal de opciones para el usuario Jefe
     */
    private static void menuJefe(){
        opciones.clear();
        System.out.print('\u000C');

        PLN.out("MENÚ PRINCIPAL");
        PLN.out("==============");
        opciones.add("\n0 = Salir de la aplicación");
        opciones.add("1 = Cerrar sesión");
        opciones.add("2 = Perfil");
        opciones.add("3 = Gestión de empleados");
        opciones.add("4 = Gestion de pedidos");
    
        eleccionUsuario = elegirOpcion();
    
        switch (eleccionUsuario) {
            
            case 0: // el programa se cierra
            System.out.print('\u000C');
            PLN.out("Ha elegido finalizar el programa");
            PLN.out("Hasta pronto");
            PLN.out("\nPrograma finalizado");
            System.exit(0);
            break;
                      
            case 1: // cierra sesión y regresa al login
            usuarioLogueado = null;
            System.out.print('\u000C');
            menuLogin();
            break;
            
            case 3: // gestiona la lista de empleados
            Menu.menuGestionEmpleados();
            break;
            
            case 4: // gestiona la lista de pedidos
            Menu.menuGestionPedidosJefe();
            break;
        }        
    }
    /**
     * Método que crea un menu con el que el jefe puede consultar y gestionar la lista de pedidos de la fábrica
     */
    private static void menuGestionPedidosJefe(){
        opciones.clear();
        int numPedido = 0;
        System.out.print('\u000C');
        PLN.out("GESTIÓN DE PEDIDOS");
        PLN.out("==================");
        opciones.add("\n0 = Regresar al menú principal");
        opciones.add("1 = Consultar lista de pedidos");    
        opciones.add("2 = Asignar pedidos"); 
        eleccionUsuario = elegirOpcion();        
        switch (eleccionUsuario) {                        
            case 0: // regresamos al menú principal
            System.out.print('\u000C');
            menuJefe();
            break;            
            
            case 1: // Se muestra la lista completa de pedidos
            System.out.print('\u000C');
            PLN.out("LISTA COMPLETA DE PEDIDOS");
            PLN.out("=========================");
            if(Controlador.verListaCompletaPedidos()){
                numPedido = seleccionarNumPedido();
                if(numPedido == 0){
                    menuGestionPedidosJefe();
                }else{
                    menuListaCompletaPedidos(numPedido);
                }
            }else{
                PLN.out("\nLa fábrica todavía no ha recibido ningún pedido");
                tools.Herramientas.enterParaContinuar();
                menuGestionPedidosJefe();
            }
            break;
            
            case 2: // Se muestra la lista de pedidos no asignados y se da la opción de asignarlos a artesanos de la fábrica
            System.out.print('\u000C');
            PLN.out("LISTA DE PEDIDOS PENDIENTES DE ASIGNAR");
            PLN.out("======================================");
            if(Controlador.verListaPedidosNoAsignados()){
                PLN.out("\nLISTA DE ARTESANOS");
                PLN.out("==================");
                Controlador.listaArtesanos();                
                PLN.out("------------------");
                numPedido = seleccionarNumPedido();
                if(numPedido == 0){
                    menuGestionPedidosJefe();
                }else{
                    Controlador.asignarPedido(numPedido);
                    menuGestionPedidosJefe();
                }
            }else{
                PLN.out("\nNo hay ningún pedido sin asignar.");
                tools.Herramientas.enterParaContinuar();
                menuGestionPedidosJefe();
            }            
            break;
        }
    }
    /**
     * Menu de opciones sobre la ista completa de pedidos
     */
    private static void menuListaCompletaPedidos(int numPedido){
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();
        int numSerie = 0;
        Pedido pedido = null;
        Mueble mueble = null;
        for(Pedido auxPedido : pedidos){
            if(auxPedido.getNumPedido() == numPedido){
                pedido = auxPedido;
            }
        }
        System.out.print('\u000C');
        PLN.out("PEDIDO " + numPedido);
        PLN.out("===========");
        PLN.out("Cliente: " + pedido.getCliente().getNombre() + pedido.getCliente().getApellidos());
        PLN.out("Fecha pedido: " + pedido.getFechaCompra());
        Controlador.listaMueblesPedido(pedido);
        numSerie = Controlador.selectNumSerie(pedido);
        if(numSerie == 0){
            menuJefe();
        }else{
            for(Mueble auxMueble : pedido.getMuebles()){
                if(auxMueble.getNumSerie() == numSerie){
                    mueble = auxMueble;
                    break;
                }
            }
            if(mueble == null){
                System.out.print('\u000C');
                PLN.out("No es posible cargar los datos del pedido" + numSerie);
                tools.Herramientas.enterParaContinuar();
                menuJefe();
            }else{
                menuFichaMueble(mueble);
            }
        }        
    }
    /**
     * Método que despliega un menú de opciones para la ficha de un mueble
     */
    private static void menuFichaMueble(Mueble mueble){
        System.out.print('\u000C');
        mueble.fichaMueble();
        tools.Herramientas.enterParaContinuar();
        menuGestionPedidosJefe();     
    }    
    /**
     * Menú con el que el Jefe gestiona la lista de empleados
     */
    public static void menuGestionEmpleados(){
        System.out.print('\u000C');
        PLN.out("LISTA DE EMPLEADOS");
        PLN.out("==================\n");
        Controlador.verListaEmpleados();
        opciones.clear();
    
        PLN.out("\n\nOpciones");
        PLN.out("========");
        opciones.add("\n0 = Volver al menú principal");
        opciones.add("1 = Contratar nuevo empleado");
        opciones.add("2 = Despedir empleado");
    
        eleccionUsuario = elegirOpcion();
    
        switch (eleccionUsuario) {
            case 0: // regresamos al menú principal del jefe
            System.out.print('\u000C');
            menuJefe();
            break;

            case 1: // se crea un nuevo empleado
            PLN.out("\n\nNuevo empleado");
            PLN.out("==============");
            Controlador.crearEmpleado();
            break;

            case 2:
            PLN.out("\n\nDespedir empleado");
            PLN.out("==============");
            Controlador.despedirEmpleado();
            break;               
        }        
    }
    
    // ******************************
    // *          Comercial         *
    // ******************************    
    
    /**
     * Menú principal de opciones para el usuario Jefe
     */
    private static void menuComercial(){
        opciones.clear();
        System.out.print('\u000C');

        PLN.out("MENÚ PRINCIPAL");
        PLN.out("==============");
        opciones.add("\n0 = Salir de la aplicación");
        opciones.add("1 = Cerrar sesión");
        opciones.add("2 = Gestión de perfil");
        opciones.add("3 = Gestión de clientes");
        opciones.add("4 = Gestión de pedidos");
    
        eleccionUsuario = elegirOpcion();
    
        switch (eleccionUsuario) {
            case 0: // el programa se cierra
            System.out.print('\u000C');
            PLN.out("Ha elegido finalizar el programa");
            PLN.out("Hasta pronto");
            PLN.out("\nPrograma finalizado");
            System.exit(0);
            break;

            case 1: // cierra sesión y regresa al login
            usuarioLogueado = null;
            System.out.print('\u000C');
            menuLogin();
            break;

            case 3: // consulta la lista de clientes
            Menu.menuGestionClientes();
            break;
        }        
    }
    
    /**
     * Menú con el que el Comercial gestiona la lista de clientes
     */
    public static void menuGestionClientes(){
        System.out.print('\u000C');
        PLN.out("LISTA DE CLIENTES");
        PLN.out("=================\n");
        PLN.out("ID logueado = " + ((Empleado)usuarioLogueado).getIdEmpleado());
        Controlador.verListaClientesComercial(((Empleado)usuarioLogueado).getIdEmpleado());
        opciones.clear();
    
        PLN.out("\n\nOpciones");
        PLN.out("========");
        opciones.add("\n0 = Volver al menú principal");
        opciones.add("1 = Inscribir nuevo cliente");
    
        eleccionUsuario = elegirOpcion();
    
        switch (eleccionUsuario) {
            case 0: // regresamos al menú principal del jefe
            System.out.print('\u000C');
            menuJefe();
            break;

            case 1: // se crea un nuevo cliente
            PLN.out("\n\nFormulario nuevo cliente");
            PLN.out("========================");
            Controlador.crearCliente(((Empleado)usuarioLogueado).getIdEmpleado());
            break;   

        }        
    }
    
        
    // ******************************
    // *          ARTESANO          *
    // ******************************    
    
    /**
     * Menú principal de opciones para el usuario Artesano
     */
    private static void menuArtesano(){
        opciones.clear();
        System.out.print('\u000C');

        PLN.out("MENÚ PRINCIPAL");
        PLN.out("==============");
        opciones.add("\n0 = Salir de la aplicación");
        opciones.add("1 = Cerrar sesión");
        opciones.add("2 = Perfil");
        opciones.add("3 = Gestión de pedidos");
        if(usuarioLogueado instanceof ArtesanoPorHoras){
            opciones.add("4 = Información laboral");
        }
    
        eleccionUsuario = elegirOpcion();
    
        switch (eleccionUsuario) {
            
            case 0: // el programa se cierra
            System.out.print('\u000C');
            PLN.out("Ha elegido finalizar el programa");
            PLN.out("Hasta pronto");
            PLN.out("\nPrograma finalizado");
            System.exit(0);
            break;
                      
            case 1: // cierra sesión y regresa al login
            usuarioLogueado = null;
            System.out.print('\u000C');
            menuLogin();
            break;
            
            case 3: // gestiona la lista de pedidos asignados            
            menuGestionMueblesAsignados();
            break;
            
            case 4: // consulta información de caracter laboral del artesano contratado por horas
            // menuInformacionLaboral();
            break;
        }       
    }
    /**
     * Método que sirve para que el artesano gestione los pedidos que tiene asignados
     */
    private static void menuGestionMueblesAsignados(){
        Mueble mueble = null;
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();
        
        System.out.print('\u000C');

        PLN.out("MUEBLES ASIGNADOS");
        PLN.out("=================");
        Controlador.listaPedidosAsignados(((Empleado)usuarioLogueado).getIdEmpleado());
        int numSerie = seleccionaMuebleAsignado();
        if(numSerie == 0){
            menuArtesano();
        }else{
            for(Pedido auxPedido : pedidos){
                for(Mueble auxMueble : auxPedido.getMuebles()){
                    if(auxMueble.getNumSerie() == numSerie){
                        mueble = auxMueble;
                        break;
                    }
                }
                if(mueble!=null){
                    break;
                }
            }                   
        }
        switch(String.valueOf(mueble.getEstado())){
            
            case "ASIGNADO":
            menuMuebleAsignado(mueble);
            break;
            
            case "EN_CONSTRUCCION":
            menuMuebleEnConstruccion(mueble);
            break;
            
            case "PAUSADO":
            menuMueblePausado(mueble);
            break;
            
            case "TERMINADO":
            menuMuebleTerminado(mueble);
            break;
            
            case "ENTREGADO":
            menuMuebleTerminado(mueble);
            break;
            
        }
    }
    /**
     * Menu de opciones sobre un mueble en estado ASIGNADO
     */
    private static void menuMuebleAsignado(Mueble mueble){
        opciones.clear();
        PLN.out("\nOpciones:");
        PLN.out("---------");
        opciones.add("\n0 = Volver al menú principal");
        opciones.add("1 = Iniciar construcción");
    
        eleccionUsuario = elegirOpcion();
        switch(eleccionUsuario){
            case 0:
            menuArtesano();
            break;
            
            case 1:
            Controlador.iniciarConstruccionMueble(mueble.getNumSerie());
            menuArtesano();
            break;
        }
    }
    /**
     * Menu de opciones sobre un mueble en estado EN_CONSTRUCCION
     */
    private static void menuMuebleEnConstruccion(Mueble mueble){
        opciones.clear();
        PLN.out("\nOpciones:");
        PLN.out("---------");
        opciones.add("\n0 = Volver al menú principal");
        opciones.add("1 = Pausar construcción");
        opciones.add("2 = Finalizar construcción");
    
        eleccionUsuario = elegirOpcion();
        switch(eleccionUsuario){
            case 0:
            menuArtesano();
            break;
            
            case 1:
            Controlador.pausarConstruccionMueble(mueble.getNumSerie());
            menuArtesano();
            break;
            
            case 2:
            Controlador.finalizarConstruccionMueble(mueble.getNumSerie());
            menuArtesano();
            break;
        }
    }
    /**
     * Menu de opciones sobre un mueble en estado PAUSADO
     */
    private static void menuMueblePausado(Mueble mueble){
        opciones.clear();
        PLN.out("\nOpciones:");
        PLN.out("---------");
        opciones.add("\n0 = Volver al menú principal");
        opciones.add("1 = Reanudar construcción");
    
        eleccionUsuario = elegirOpcion();
        switch(eleccionUsuario){
            case 0:
            menuArtesano();
            break;
            
            case 1:
            Controlador.reanudarConstruccionMueble(mueble.getNumSerie());
            menuArtesano();
            break;
        }
    }
    /**
     * Menu de opciones sobre un mueble en estado TERMINADO o ENTREGADO (no tiene opciones)
     */
    private static void menuMuebleTerminado(Mueble mueble){
        tools.Herramientas.enterParaContinuar();
        menuArtesano();
    }
    
    // ******************************
    // *            CLIENTE         *
    // ******************************    
    
    /**
     * Menú principal de opciones para el usuario Cliente
     */
    private static void menuCliente(){
        opciones.clear();
        System.out.print('\u000C');
        PLN.out("MENÚ PRINCIPAL");
        PLN.out("==============");
        opciones.add("\n0 = Salir de la aplicación");
        opciones.add("1 = Cerrar sesión");
        opciones.add("2 = Perfil");
        opciones.add("3 = Tienda");
        opciones.add("4 = Mis pedidos");
    
        eleccionUsuario = elegirOpcion();
        System.out.print('\u000C');
    
        switch (eleccionUsuario) {
            case 0: // el programa se cierra
            PLN.out("Ha elegido finalizar el programa");
            PLN.out("Hasta pronto");
            PLN.out("\nPrograma finalizado");
            System.exit(0);
            break;

            case 1: // cierra sesión y regresa al login
            usuarioLogueado = null;
            menuLogin();
            break;

            case 3: // consulta el catálogo de muebles
            PLN.out("CATÁLOGO DE MUEBLES");
            PLN.out("===================");
            PLN.out(Tabla.catalogoMuebles());
            Controlador.comprarMuebles(((Cliente)usuarioLogueado).getIdCliente());
            menuCliente();
            break;

            case 4: // consulta el estado de los pedidos
            PLN.out("MIS PEDIDOS");
            PLN.out("===========");
            boolean tienePedidos = Controlador.consultaPedidosCliente(((Cliente)usuarioLogueado).getIdCliente());
            if (!tienePedidos){
                PLN.out("Todavía no tiene pedidos");
                tools.Herramientas.enterParaContinuar();
                menuCliente();
            }else{
                menuPedidosCliente();                    
            }
            break;
        }          
    }   
    /**
     * Método que muestra las opciones del cliente sobre su lista de pedidos
     */
    private static void menuPedidosCliente(){
        int numPedido = seleccionarNumPedidoCliente();
        if(numPedido != 0){
            Controlador.consultaListaMueblesPedido(numPedido, ((Cliente)usuarioLogueado).getIdCliente());     
            
        }
        tools.Herramientas.enterParaContinuar();
        menuCliente();
    }
    
    // ******************************
    // *   MÉTODOS DE USO GENERAL   *
    // ******************************    
    
    /**
     * Método que gestiona la elección de opciones en los menús por parte del usuario.
     */
    private static int elegirOpcion() {
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
     * Método para que el usuario seleccione un número de pedido
     */
    private static int seleccionarNumPedido() {        
        int num = -1;
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();        
        boolean allRight = false;
        P.out("Indique número de pedido (0 = Regresar): ");
        do {           
            try {
                num = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (num < 0) {
                    PLN.out("No existe ningún pedido con ese id.");
                } else if (num == 0){                   
                    allRight = true;
                }else{
                    for (Pedido auxPedido : pedidos){
                        if (num == auxPedido.getNumPedido()){
                            allRight = true;
                        }
                    }
                    if(!allRight){
                        PLN.out("No existe ningún pedido con ese id.");
                    }
                }
            } catch (Exception e) {
                PLN.out("Valor incorrecto.");
                scanner.nextLine();
            }
            if(!allRight) {
                P.out("Indique otro Id: ");
                num = -1;
            }
        } while (!allRight);
        return num;
    } 
    /**
     * Método para que el cliente seleccione un número de pedido (de uno de sus pedidos)
     */
    private static int seleccionarNumPedidoCliente() {        
        int num = -1;
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();
        ArrayList<Pedido> pedidosCliente = new ArrayList<Pedido>();
        
        boolean allRight = false;
        P.out("Indique número de pedido (0 = Regresar): ");
        do {           
            try {
                num = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (num < 0) {
                    PLN.out("No tiene ningún pedido con ese id.");
                } else if (num == 0){                   
                    allRight = true;
                }else{
                    for (Pedido auxPedido : pedidos){
                        if (num == auxPedido.getNumPedido() && auxPedido.getCliente().getIdCliente() == ((Cliente)usuarioLogueado).getIdCliente()){
                            allRight = true;
                        }
                    }
                    if(!allRight){
                        PLN.out("No tiene ningún pedido con ese id.");
                    }
                }
            } catch (Exception e) {
                PLN.out("Valor incorrecto.");
                scanner.nextLine();
            }
            if(!allRight) {
                P.out("Indique otro Id: ");
                num = -1;
            }
        } while (!allRight);
        return num;
    } 
    /**
     * Método que permite que el cliente seleccione el nº de serie de uno de los muebles que tiene asignados
     */
    private static int seleccionaMuebleAsignado(){
        int num = -1;
        ArrayList<Pedido> pedidos = Controlador.loadPedidos();
        ArrayList<Mueble> muebles = new ArrayList<Mueble>();
        boolean allRight = false;
        
        for(Pedido auxPedido : pedidos){
            for(Mueble auxMueble : auxPedido.getMuebles()){
                if(auxMueble.getArtesano() != null && auxMueble.getArtesano().getIdEmpleado() == ((Artesano)usuarioLogueado).getIdEmpleado()){
                    muebles.add(auxMueble);
                } else{
                    break;
                }
            }
        }        
        P.out("Indique nº de serie (0 = Regresar): ");
        do {           
            try {
                num = scanner.nextInt();
                scanner.nextLine(); // limpia pulsaciones residuales de cara a posibles sucesiones.
                if (num == 0){                   
                    allRight = true;
                }else{
                    for (Mueble auxMueble : muebles){
                        if (num == auxMueble.getNumSerie()){
                            allRight = true;
                        }
                    }
                }
            } catch (Exception e) {
                scanner.nextLine();
            }
            if(!allRight) {
                PLN.out("No tiene ningún pedido con ese id.");
                P.out("Indique otro Id: ");
                num = -1;
            }
        } while (!allRight);
        return num;
    }
        
}