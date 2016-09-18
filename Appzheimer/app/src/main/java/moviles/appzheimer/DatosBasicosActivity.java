package moviles.appzheimer;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

public class DatosBasicosActivity extends ActionBarActivity {

    /**
     * El calendario para poder traer los datos de la fecha
     */
    private Calendar calendario;

    private String diasSemana[];

    private String meses[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_basicos);
        TextView nombre = (TextView) findViewById(R.id.textNombreUsuarioDatosBasicos);
        TextView edad = (TextView) findViewById(R.id.textEdadDatosBasicos);
        TextView direccion = (TextView) findViewById(R.id.textDireccionDatosBasicos);
        TextView diaFecha = (TextView) findViewById(R.id.textDiaFechaDatosBasicos);
        TextView mesFecha = (TextView) findViewById(R.id.textMesDatosBasicos);
        try
        {
            InputStream archivo = openFileInput(CrearUsuarioActivity.DATOSUSUARIO);
            if(archivo!=null)
            {
                InputStreamReader temp = new InputStreamReader(archivo);
                BufferedReader lector = new BufferedReader(temp);
                nombre.setText(lector.readLine());
                edad.setText(lector.readLine());
                //Esta linea contiene la fecha de nacimiento del paciente
                lector.readLine();
                direccion.setText(lector.readLine());
                lector.close();
            }
        }
        catch(Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
        diasSemana = new String[7];
        diasSemana[0] = "Domingo";
        diasSemana[1] = "Lunes";
        diasSemana[2] = "Martes";
        diasSemana[3] = "Miercoles";
        diasSemana[4] = "Jueves";
        diasSemana[5] = "Viernes";
        diasSemana[6] = "Sabado";
        calendario = Calendar.getInstance();
        Log.i("Dia",calendario.get(Calendar.DAY_OF_WEEK)+"");
        Log.i("Dia",calendario.get(Calendar.YEAR)+"");
        diaFecha.setText(diasSemana[calendario.get(Calendar.DAY_OF_WEEK)-1] + " " + calendario.get(Calendar.DAY_OF_MONTH));
        meses = new String[12];
        meses[0] = "Enero";
        meses[1] = "Febrero";
        meses[2] = "Marzo";
        meses[3] = "Abril";
        meses[4] = "Mayo";
        meses[5] = "Junio";
        meses[6] = "Julio";
        meses[7] = "Agosto";
        meses[8] = "Septiembre";
        meses[9] = "Octubre";
        meses[10] = "Noviembre";
        meses[11] = "Diciembre";
        mesFecha.setText(meses[calendario.get(Calendar.MONTH)]);
    }

    /**
     * Metodo para volver a la ventanta principal
     * @param v la vista principal
     */
    public void volverMenuPrinciapl(View v)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
