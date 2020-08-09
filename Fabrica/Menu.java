package fabrica;

import personas.Persona;
import personas.Cliente;
import personas.Empleado;
import java.util.ArrayList;
import java.util.Scanner;
import tools.SoutIF;

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
        PLN.out("Pulse enter para continuar");
        scanner.nextLine();        
        menuLogin();
    }
    /**
     * Método que identifica el tipo de usuario que se ha logueado y redirige la aplicación hacia el menú personalizado correspondiente,
     */
    private static void selectMenu(){
        switch(usuarioLogueado.getClass().getSimpleName()){
            case "ArtesanoPorHoras":
                PLN.out("Te has logueado como Artesano por horas");
                break;
            case "ArtesanoEnPlantilla":
                PLN.out("Te has logueado como Artesano en plantilla");
                break;
            case "Jefe":
                menuJefe();
                break;
            case "Comercial":
                PLN.out("Te has logueado como Comercial");
                break;
            case "Artesano":
                PLN.out("Te has logueado como Artesano");
                break;
            case "Empleado":
                PLN.out("Te has logueado como Empleado");
                break;
            case "ClienteEmpresa":
                PLN.out("Te has logueado como Cliente tipo empresa");
                break;
            case "ClientePersona":
                PLN.out("Te has logueado como Cliente tipo persona");
                break;
            case "Cliente":
                PLN.out("Te has logueado como Cliente");
                break;
            case "Persona":
                PLN.out("Te has logueado como Persona");
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
            }
        }while(usuarioLogueado == null);
        selectMenu();
    }   
    /**
     * Menú principal de opciones para el usuario Jefe
     */
    public static void menuJefe(){
        opciones.clear();
        System.out.print('\u000C');

	PLN.out("MENÚ PRINCIPAL");
	PLN.out("==============");
	opciones.add("\n0 = Salir de la aplicación");
	opciones.add("1 = Cerrar sesión");
	opciones.add("2 = Perfil");
	opciones.add("3 = Empleados");
	opciones.add("4 = Pedidos");

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
	    case 3: // consulta la lista de empleados
	       Menu.menuJefeEmpleados();
	       break;
	}        
    }
    
    /**
     * Menú con el que el Jefe gestiona la lista de empleados
     */
    public static void menuJefeEmpleados(){
        System.out.print('\u000C');
	PLN.out("LISTA DE EMPLEADOS");
	PLN.out("==================\n");
	Controlador.verListaEmpleados();
        opciones.clear();

	PLN.out("\n\nOpciones");
	PLN.out("========");
	opciones.add("\n0 = Volver al menú principal");
	opciones.add("1 = Contratar un nuevo empleado");

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

	       
	}        
    }
    /**
     * Método que gestiona la elección de opciones en los menús por parte del usuario.
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
}
