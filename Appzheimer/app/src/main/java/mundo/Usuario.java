package mundo;

import java.util.Date;

/**
 * Created by Santiago on 9/11/2016.
 */
public class Usuario {
    
    /**
     * La edad del usuario
     */
    private int edad;

    /**
     * El nombre del usuario
     */
    private String nombre;

    /**
     * La fecha de nacimiento del usuario
     */
    private Date fechaNacimiento;

    /**
     * Direccion de la casa
     */
    private String direccion;

    /**
     * Metodo que crea un nuevo usuario
     * @param pEdad la edad del usuario
     * @param pNombre el nombre del usuario
     * @param pFechaNacimiento la fecha de nacimiento del usuario
     * @param pDireccion la direccion de la casa del usuario
     */
    private Usuario(int pEdad, String pNombre, Date pFechaNacimiento, String pDireccion)
    {
        edad = pEdad;
        nombre = pNombre;
        fechaNacimiento = pFechaNacimiento;
        direccion = pDireccion;
    }

    /**
     * Metodo constructor sin parametros
     */
    private Usuario()
    {

    }

    /**
     * Metodo que retorna la edad del usuario
     * @return la edad del usuario
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Metodo que cambia la edad del usuario
     * @param pEdad la edad del usuario
     */
    public void setEdad(int pEdad) {
        edad = pEdad;
    }

    /**
     * Metodo que retorna el nombre del usuario
     * @return el nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del usuario
     * @param pNombre el nombre del usuario
     */
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    /**
     * Metodo que retorna la fecha de nacimiento del usuario
     * @return la fecha de nacimiento del usuario
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Metodo que cambia la fecha de nacimiento del usuario
     * @param pFechaNacimiento
     */
    public void setFechaNacimiento(Date pFechaNacimiento) {
        fechaNacimiento = pFechaNacimiento;
    }

    /**
     * Metodo que retorna la direccion del usuario
     * @return la direccion del usuario
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Metodo que cambia la direccion del usuario
     * @param pDireccion la direccion del usuario
     */
    public void setDireccion(String pDireccion) {
        direccion = pDireccion;
    }
}
