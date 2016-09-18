package mundo;

/**
 * Created by Santiago on 9/18/2016.
 */
public class Familiar {

    /**
     * El nombre del familiar
     */
    private String nombre;

    /**
     * El parentesco del familiar
     */
    private String parentesco;

    /**
     * La ruta con la foto del familiar
     */
    private String rutaImagen;

    /**
     * Metodo constructor sin parametros
     */
    public Familiar()
    {

    }

    /**
     * Metodo constructor con parametros
     */
    public Familiar(String pNombre, String pParentesco, String pRuta)
    {
        nombre = pNombre;
        parentesco = pParentesco;
        rutaImagen = pRuta;
    }

    /**
     * Metodo que retorna el nombre del familiar
     * @return el nombre del familiar
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Metodo que cambia el nombre del familiar por el nombre dado por parametro
     * @param pNombre el nuevo nombre
     */
    public void setNombre(String pNombre) {
        nombre = pNombre;
    }

    /**
     * Metodo que retorna el parentesco del familiar
     * @return el parentesco del familiar
     */
    public String getParentesco() {
        return parentesco;
    }

    /**
     * Metodo que cambia el parentesco por el valor dado como parametro
     * @param pParentesco el nuevo parentesco
     */
    public void setParentesco(String pParentesco) {
        parentesco = pParentesco;
    }

    /**
     * Metodo que retorna la ruta de la imagen del familiar
     * @return la ruta de la imagen
     */
    public String getRutaImagen() {
        return rutaImagen;
    }

    /**
     * Metodo que cambia la ruta de imagen del familiar
     * @param pRutaImagen
     */
    public void setRutaImagen(String pRutaImagen) {
        rutaImagen = pRutaImagen;
    }
}
