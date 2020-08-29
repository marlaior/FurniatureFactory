package tools;

//package tools;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DecimalFormat;
import personas.Persona;
import personas.Cliente;
import personas.Empleado;
import pedidos.Pedido;
import muebles.Mueble;

/**
 * Esta clase dispone de diversos métodos para mostrar información en forma de tablas con columnas que se adaptan al ancho de la información contenida.
 * 
 * @author Marcos Laiño
 */
public class Tabla{
    
    /**
     * Método que formatea las tablas para que las columnas se adapten al ancho del elemento de mayor tamaño.
     */
    private static String formatearTabla(List<List<String>> lista) {
        int[] maxLengths = new int[lista.get(0).size()];
    for (List<String> rowList : lista) { // Comprueba el ancho máximo de los valores de la lista
        for (int i = 0; i < rowList.size(); i++) {
            maxLengths[i] = Math.max(maxLengths[i], rowList.get(i).length());
        }
    }
        StringBuilder formatBuilder = new StringBuilder();
        for (int maxLength : maxLengths) { // Establece el ancho de las columnas de la tabla.
            formatBuilder.append("%-").append(maxLength + 4).append("s");
    }
        String format = formatBuilder.toString();
        StringBuilder result = new StringBuilder();
        for (List<String> row : lista) {
            result.append(String.format(format, row.toArray(new String[0]))).append("\n");
    }
    return result.toString();

    }
    /**
     * Metodo que muestra la lista de usuarios de la aplicación.
     * El objetivo de esta tabla es facilitar el testeo de la aplicación.
     */
    public static String listaUsuarios(ArrayList<Persona> personas) {
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("#", "usuario", "contraseña", "tipo de usuario"); // cabecera
        List<String> separators = Arrays.asList("--", "-------", "----------", "---------------");
        listas.add(headers);
        listas.add(separators);
        int pos = 1;
        for (Persona persona : personas) {
            if(persona instanceof Cliente){
                listas.add((List<String>) Arrays.asList(String.valueOf(pos), persona.getUsuario(), persona.getContrasena(), "Cliente"));
            }else{
                Empleado empleado = (Empleado)persona;
            listas.add((List<String>) Arrays.asList(String.valueOf(pos), persona.getUsuario(), persona.getContrasena(), empleado.getPuesto()));
                }
                pos++;
    }
    return formatearTabla(listas) + "\n";
    }
    /**
     * Metodo que muestra la lista de empleados de la fábrica.
     * Esta tabla la puede consultar el Jefe
     */
    public static String listaEmpleados(ArrayList<Empleado> empleados) {
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("ID Empleado", "nombre", "apellidos", "puesto", "fecha de alta", "fecha de baja"); // cabecera
        List<String> separators = Arrays.asList("-----------", "------", "---------", "------", "-------------", "-------------");
        listas.add(headers);
        listas.add(separators);
        for (Empleado empleadoAux : empleados) {
            String baja = (empleadoAux.getFechaBaja() == null) ? null : new SimpleDateFormat("dd/MM/yyyy").format(empleadoAux.getFechaBaja());
            listas.add((List<String>) Arrays.asList(
               String.valueOf(empleadoAux.getIdEmpleado()), 
               empleadoAux.getNombre(), 
               empleadoAux.getApellidos(), 
               empleadoAux.getPuesto(), 
               new SimpleDateFormat("dd/MM/yyyy").format(empleadoAux.getFechaAlta()), 
               (empleadoAux.getFechaBaja() == null) ? "null" : new SimpleDateFormat("dd/MM/yyyy").format(empleadoAux.getFechaBaja())
            ));            
        }
        return formatearTabla(listas) + "\n";
    }
    /**
     * Metodo que muestra la lista de empleados de la fábrica.
     * Esta tabla la puede consultar el Jefe
     */
    public static String listaClientes(ArrayList<Cliente> clientes) {
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("ID Cliente", "nombre", "apellidos", "NIF/CIF", "teléfono"); // cabecera
        List<String> separators = Arrays.asList("-----------", "------", "---------", "-------", "--------");
        listas.add(headers);
        listas.add(separators);
        for (Cliente clienteAux : clientes) {
            listas.add((List<String>) Arrays.asList(
               String.valueOf(clienteAux.getIdCliente()), 
               clienteAux.getNombre(), 
               (clienteAux.getApellidos() != null) ? clienteAux.getApellidos() : "", 
               clienteAux.getNif(),
               (clienteAux.getTelefono() != null) ? clienteAux.getTelefono() : ""
            ));            
        }
        return formatearTabla(listas) + "\n";
    }
    /**
     * Método que devuelve el catálogo de muebles
     */
    public static String catalogoMuebles() {
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("ID MODELO", "DESCRIPCIÓN MUEBLE", "PRECIO"); // cabecera
        List<String> separators = Arrays.asList("---------", "------------------", "------");
        listas.add(headers);
        listas.add(separators);
        listas.add((List<String>) Arrays.asList("01", "Mueble genérico", ""));
        listas.add((List<String>) Arrays.asList("02", "Mesa genérica", ""));
        listas.add((List<String>) Arrays.asList("03", "Mesa café básica", ""));
        listas.add((List<String>) Arrays.asList("04", "Mesa café de cristal", ""));
        listas.add((List<String>) Arrays.asList("05", "Mesa café de madera", ""));
        listas.add((List<String>) Arrays.asList("06", "Mesa de dormitorio", ""));
        listas.add((List<String>) Arrays.asList("07", "Mesa de comedor", ""));
        listas.add((List<String>) Arrays.asList("08", "Silla genérica", ""));
        listas.add((List<String>) Arrays.asList("09", "Silla de cocina", ""));
        listas.add((List<String>) Arrays.asList("10", "Silla de oficina básica", ""));
        listas.add((List<String>) Arrays.asList("11", "Silla de oficina con ruedas", ""));
        listas.add((List<String>) Arrays.asList("12", "Silla de oficina sin ruedas", ""));
        listas.add((List<String>) Arrays.asList("13", "Silla plegable", ""));
        
        return formatearTabla(listas) + "\n";
    }
    /**
     * Metodo que imprime una tabla con los pedidos de un cliente
     */
    public static String listaPedidosCliente(ArrayList<Pedido> pedidos) {
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("Nº PEDIDO", "ESTADO"); // cabecera
        List<String> separators = Arrays.asList("---------", "------");
        listas.add(headers);
        listas.add(separators);
        String estado = "En proceso";
        int terminado;
        int pendiente;
        for (Pedido auxPedido : pedidos) {
            terminado = 0;
            pendiente = 0;
            for(Mueble auxMueble : auxPedido.getMuebles()){
    
                if(auxMueble.getEstado().equals(Mueble.Estado.ENTREGADO)){
                    estado = "Entregado";
                    break;
                }else if(auxMueble.getEstado().equals(Mueble.Estado.TERMINADO)){
                    terminado++;
                }else if(auxMueble.getEstado().equals(Mueble.Estado.PENDIENTE)){
                    pendiente++;
                }
            }
            if (estado.equals("Entregado")){}
            else if(terminado == auxPedido.getMuebles().size()){
                estado = "Terminado";
            }else if(pendiente == auxPedido.getMuebles().size()){
                estado = "Pendiente";
            }else {
                estado = "En proceso";
            }
            listas.add((List<String>) Arrays.asList(""+auxPedido.getNumPedido(), estado));          
        }
        return formatearTabla(listas) + "\n";
    }
    /**
     * Método que imprime una tabla con los muebles de un pedido
     */
    public static String listaMueblesPedido(ArrayList<Mueble> muebles){
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("Nº SERIE", "DESCRIPCIÓN", "ESTADO", "IMPORTE"); // cabecera
        List<String> separators = Arrays.asList("--------", "-----------", "------", "-------");
        listas.add(headers);
        listas.add(separators);
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); // esta instrucción hará que se muestren los datos tipo double con dos decimales

        for (Mueble auxMueble : muebles) {
            listas.add((List<String>) Arrays.asList(
                String.valueOf(auxMueble.getNumSerie()),
                auxMueble.getDescripcionMueble(), 
                String.valueOf(auxMueble.getEstado()), 
                decimalFormat.format(auxMueble.getPrecio())));          
        }
        return formatearTabla(listas) + "\n";
    }
}
