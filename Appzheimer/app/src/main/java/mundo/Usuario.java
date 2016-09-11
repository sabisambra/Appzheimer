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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int pEdad) {
        edad = pEdad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date pFechaNacimiento) {
        fechaNacimiento = pFechaNacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String pDireccion) {
        direccion = pDireccion;
    }
}
