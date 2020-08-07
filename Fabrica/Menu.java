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
    private static int eleccion;

    public Menu(){
        opciones = new ArrayList<String>();
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
            PLN.out("Te has logueado como Jefe");
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
        Scanner teclado = new Scanner(System.in);
        String usuario;
        String contrasena;
        boolean existeUsuario = false;
        usuarioLogueado = null;
        System.out.flush();
        PLN.out("   ************************");
        PLN.out("   *  Furniture Factory   *");
        PLN.out("   ************************\n\n");

        PLN.out("Lista de usuarios disponibles\n(se muestran los usuarios disponibles únicamente para facilitar las pruebas)\n\n");
        PLN.out("\n" + tools.Tabla.listaUsuarios(listaUsuarios) + "\n");
        do{
            existeUsuario = false;
            P.out("USUARIO: ");
            usuario = teclado.nextLine();
            P.out("\nCONTRASEÑA: ");
            contrasena = teclado.nextLine();
            for(Persona auxPersona : listaUsuarios){
                if (auxPersona.getUsuario().equalsIgnoreCase(usuario)){
                    existeUsuario = true;
                    if(auxPersona.getContrasena().equals(contrasena)){
                        PLN.out("Contraseña correcta");
                        usuarioLogueado = auxPersona;                        
                    }else PLN.out("Contraseña incorrecta");
                    break;
                }                
            }
            if(!existeUsuario){
                    PLN.out("No existe usuario con ese nick");
            }
        }while(usuarioLogueado == null);
        selectMenu();
    }   
}
