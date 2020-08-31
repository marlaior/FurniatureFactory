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
import personas.Artesano;
import personas.ArtesanoPorHoras;
import personas.ArtesanoEnPlantilla;
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
               (empleadoAux.getFechaBaja() == null) ? "---" : new SimpleDateFormat("dd/MM/yyyy").format(empleadoAux.getFechaBaja())
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
     * Método que muestra la lista de Artesanos que trabajan para la fábrica
     */
    public static String listaArtesanos(ArrayList<Artesano> artesanos){
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("Id empleado", "nombre", "apellidos", "contrato"); // cabecera
        List<String> separators = Arrays.asList("-----------", "------", "---------", "--------");
        listas.add(headers);
        listas.add(separators);
        for (Artesano auxArtesano : artesanos) {
            listas.add((List<String>) Arrays.asList(
               String.valueOf(auxArtesano.getIdEmpleado()), 
               auxArtesano.getNombre(), 
               auxArtesano.getApellidos(), 
               auxArtesano.getPuesto()
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
        listas.add((List<String>) Arrays.asList("01", "Mueble genérico", "50,00€"));
        listas.add((List<String>) Arrays.asList("02", "Mesa genérica", "250,00€"));
        listas.add((List<String>) Arrays.asList("03", "Mesa café básica", "275,00€"));
        listas.add((List<String>) Arrays.asList("04", "Mesa café de cristal", "325,00€"));
        listas.add((List<String>) Arrays.asList("05", "Mesa café de madera", "295,00€"));
        listas.add((List<String>) Arrays.asList("06", "Mesa de dormitorio", "310,00€"));
        listas.add((List<String>) Arrays.asList("07", "Mesa de comedor", "400,00€"));
        listas.add((List<String>) Arrays.asList("08", "Silla genérica", "150,00€"));
        listas.add((List<String>) Arrays.asList("09", "Silla de cocina", "180,00€"));
        listas.add((List<String>) Arrays.asList("10", "Silla de oficina básica", "230,00€"));
        listas.add((List<String>) Arrays.asList("11", "Silla de oficina con ruedas", "330,00€"));
        listas.add((List<String>) Arrays.asList("12", "Silla de oficina sin ruedas", "250,00€"));
        listas.add((List<String>) Arrays.asList("13", "Silla plegable", "160,00€"));
        
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
        String estado = "TERMINADO";
        for (Pedido auxPedido : pedidos) {
            
            for(Mueble auxMueble : auxPedido.getMuebles()){
                estado = "TERMINADO";
                if(auxMueble.getEstado().equals(Mueble.Estado.ENTREGADO)){
                    estado = "ENTREGADO";
                    break;
                }else if(auxMueble.getEstado().equals(Mueble.Estado.PENDIENTE)
                    || auxMueble.getEstado().equals(Mueble.Estado.ASIGNADO)
                    || auxMueble.getEstado().equals(Mueble.Estado.EN_CONSTRUCCION)
                    || auxMueble.getEstado().equals(Mueble.Estado.PAUSADO)){
                    estado = "EN PROCESO";
                    break;
                }
            }
            listas.add((List<String>) Arrays.asList(""+auxPedido.getNumPedido(), estado));          
        }
        return formatearTabla(listas) + "\n";
    }
    /**
     * Método que imprime una tabla con la lista de pedidos asignados al artesano
     */
    public static String listaMueblesAsignados(ArrayList<Pedido> pedidos){
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("Nº SERIE", "NºPEDIDO", "CLIENTE", "ESTADO"); // cabecera
        List<String> separators = Arrays.asList("--------", "--------", "-------", "------");
        listas.add(headers);
        listas.add(separators);
        String artesano = "";
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); // esta instrucción hará que se muestren los datos tipo double con dos decimales
        for (Pedido auxPedido : pedidos) {
            for(Mueble auxMueble : auxPedido.getMuebles()){   
                listas.add((List<String>) Arrays.asList(
                    String.valueOf(auxMueble.getNumSerie()),
                    String.valueOf(auxPedido.getNumPedido()),
                    auxPedido.getCliente().getNombre() + " " + auxPedido.getCliente().getApellidos(),
                    String.valueOf(auxMueble.getEstado()))); 
            }              
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
        double importeTotal = 0;

        for (Mueble auxMueble : muebles) {
            listas.add((List<String>) Arrays.asList(
                String.valueOf(auxMueble.getNumSerie()),
                auxMueble.getDescripcionMueble(), 
                String.valueOf(auxMueble.getEstado()), 
                decimalFormat.format(auxMueble.getPrecio())+"€"));  
            importeTotal += auxMueble.getPrecio();
        }
        listas.add(separators);
        listas.add((List<String>) Arrays.asList("", "", "", decimalFormat.format(importeTotal)));
        return formatearTabla(listas) + "\n";
    }
    /**
     * Metodo que imprime una tabla con los pedidos de un cliente
     */
    public static String listaPedidosJefe(ArrayList<Pedido> pedidos) {
        List<List<String>> listas = new ArrayList<>();
        List<String> headers = Arrays.asList("Nº PEDIDO", "CLIENTE", "ARTESANO", "ESTADO", "IMPORTE"); // cabecera
        List<String> separators = Arrays.asList("---------", "-------", "--------", "------", "-------");
        listas.add(headers);
        listas.add(separators);
        String estado = "TERMINADO";
        double importeTotal = 0;
        String artesano = "";
        DecimalFormat decimalFormat = new DecimalFormat("#.00"); // esta instrucción hará que se muestren los datos tipo double con dos decimales
        for (Pedido auxPedido : pedidos) {
            estado = "TERMINADO";
            for(Mueble auxMueble : auxPedido.getMuebles()){    
                if(auxMueble.getEstado().equals(Mueble.Estado.PENDIENTE)){
                    estado = "SIN ASIGNAR";
                    artesano = ""; 
                }else if(auxMueble.getEstado().equals(Mueble.Estado.EN_CONSTRUCCION) 
                    || (auxMueble.getEstado().equals(Mueble.Estado.PAUSADO))
                    || (auxMueble.getEstado().equals(Mueble.Estado.ASIGNADO))){
                    artesano = auxMueble.getArtesano().getNombre() + " " + auxMueble.getArtesano().getApellidos();
                    estado = "ASIGNADO";
                }else{
                    artesano = auxMueble.getArtesano().getNombre() + " " + auxMueble.getArtesano().getApellidos();
                }
                importeTotal += auxMueble.getPrecio();

            }

            listas.add((List<String>) Arrays.asList(
                String.valueOf(auxPedido.getNumPedido()),
                auxPedido.getCliente().getNombre() + " " + auxPedido.getCliente().getApellidos(),
                artesano,
                estado,
                decimalFormat.format(importeTotal)+"€"));   
        }
        return formatearTabla(listas) + "\n";
    }
    /**
     * Método que imprime una tabla con los datos de un mueble
     */    
    public static String datosMueble(Mueble mueble){
        
        List<List<String>> listas = new ArrayList<>();
        listas.add((List<String>) Arrays.asList("DESCRIPCIÓN", mueble.getDescripcionMueble()));  
        listas.add((List<String>) Arrays.asList("Nº DE SERIE", String.valueOf(mueble.getNumSerie())));  
        listas.add((List<String>) Arrays.asList("ESTADO", String.valueOf(mueble.getEstado())));  
        listas.add((List<String>) Arrays.asList("COMPRADOR", mueble.getCliente().getNombre() + " " + mueble.getCliente().getApellidos()));
        if(mueble.getArtesano() == null){
            listas.add((List<String>) Arrays.asList("ARTESANO: ", "SIN ASIGNAR"));
        }else{
            listas.add((List<String>) Arrays.asList("ARTESANO", mueble.getArtesano().getNombre() + " " + 
                mueble.getArtesano().getNombre()+"(Empleado nº" + mueble.getArtesano().getIdEmpleado() + ")")); 
        }
        listas.add((List<String>) Arrays.asList("FECHA DE COMPRA: ", String.valueOf(mueble.getFechaCompra())));
        if(mueble.getFechaEntrega() == null){
            listas.add((List<String>) Arrays.asList("FECHA DE ENTREGA: ", "NO ENTREGADO"));
        }else{
            listas.add((List<String>) Arrays.asList("FECHA DE ENTREGA: ", String.valueOf(mueble.getFechaEntrega())));
        }
        return formatearTabla(listas) + "\n";
    }
}
