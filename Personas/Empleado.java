package Personas;

import java.util.Date;
/**
 * Clase que describe objetos de tipo Empleado
 * Esta clase hereda de Persona
 * 
 * @author Marcos Laíño Ordóñez
 */
public class Empleado extends Persona{
    protected static int id_empleado;
    protected String puesto;
    protected Date fechaAlta;
    protected Date fechaBaja;
    protected double salarioBase;

}
