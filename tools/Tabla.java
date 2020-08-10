package tools;

//package tools;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import personas.Persona;
import personas.Cliente;
import personas.Empleado;

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
	       "null"//new SimpleDateFormat("dd/MM/yyyy").format(empleadoAux.getFechaBaja())
	    ));            
	}
	return formatearTabla(listas) + "\n";
    }
}
