package moviles.appzheimer;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

public class CrearUsuarioActivity extends AppCompatActivity {

    /**
     * Archivo donde se va a guardar la informacion basica del paciente
     */
    public static final String DATOSUSUARIO = "appzheimerDatosPaciente.txt";

    /**
     * El datepicker para seleccionar la fecha de nacimiento
     */
    private DatePicker datePicker;

    /**
     * El calendario para saber las fechas
     */
    private Calendar calendario;

    /**
     * El textview que se edita con la fecha de nacimiento
     */
    private TextView dateView;

    /**
     * Para guardar el anio el dia y el mes
     */
    private int year, month, day;

    /**
     * Metodo que crea la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_usuario);

        /**
         * Trata de leer el archivo con los datos del usuario para mantener la persistencia de la aplicacion
         */
        try{
            InputStream archivo = openFileInput(DATOSUSUARIO);
            if(archivo!=null)
                {
                InputStreamReader temp = new InputStreamReader(archivo);
                BufferedReader lector = new BufferedReader(temp);
                String linea = lector.readLine();
                if(!linea.equals("No hay sesion"))
                {
                    Intent intent = new Intent(this,MainActivity.class);
                    startActivity(intent);
                }
            }
            else{

            }
            archivo.close();
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }

        /**
         * La fecha de nacimiento sale como la fecha actual
         */
        dateView = (TextView) findViewById(R.id.fechaNacimientoEditable);
        calendario = Calendar.getInstance();
        year = calendario.get(Calendar.YEAR);
        month = calendario.get(Calendar.MONTH);
        day = calendario.get(Calendar.DAY_OF_MONTH);
        showDate(year, month+1, day);
    }

    /**
     * Metodo que configura el textview con la fecha actual
     * @param pYear el anio de la fecha
     * @param pMonth el mes de la fecha
     * @param pDay el dia de la fecha
     */
    private void showDate(int pYear, int pMonth, int pDay)
    {
        dateView.setText(new StringBuilder().append(pDay).append("/").append(pMonth).append("/").append(pYear));
    }

    /**
     * Metodo que sirve para crear el dialogo y cambiar la fecha de nacimiento
     * @param v
     */
    @SuppressWarnings("deprecation")
    public void ajustarFechaNacimiento(View v)
    {
        showDialog(999);
        Toast.makeText(getApplicationContext(),"ca",Toast.LENGTH_SHORT).show();
    }

    /**
     * Metodo que crea el dialogo con el calendario
     * @param id
     * @return
     */
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==999)
        {
            return new DatePickerDialog(this,myDateListener,year,month,day);
        }
        return null;
    }

    /**
     * Metodo que crear el date picker
     */
    private DatePickerDialog.OnDateSetListener myDateListener = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker arg0, int arg1, int arg2, int arg3)
        {
            showDate(arg1,arg2+1,arg3);
        }
    };

    /**
     * Metodo que crear el perfil del usuario
     * @param v el view que se presenta despues del proceso
     */
    public void crearUsuario(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        EditText nombre = (EditText) findViewById(R.id.nombrePersonaRegistro);
        EditText edad = (EditText) findViewById(R.id.edadPersonaRegistro);
        EditText direccion = (EditText) findViewById(R.id.direccionResidencia);
        String nombreUsuario = nombre.getText().toString();
        String edadUsuario = edad.getText().toString();
        String direccionUsuario = direccion.getText().toString();
        String fechaNacimieto = dateView.getText().toString();
        String lineaNueva = System.getProperty("line.separator");
        try{
            OutputStreamWriter impresora = new OutputStreamWriter(openFileOutput(DATOSUSUARIO,0));
            impresora.write(nombreUsuario);
            impresora.write(lineaNueva);
            impresora.write(edadUsuario);
            impresora.write(lineaNueva);
            impresora.write(fechaNacimieto);
            impresora.write(lineaNueva);
            impresora.write(direccionUsuario);
            impresora.close();
        }
        catch (Exception e) {
            Log.i("Archivo","No se ecribio");
        }
        intent.putExtra("Nombre usuario", nombreUsuario);
        startActivity(intent);
    }
}
