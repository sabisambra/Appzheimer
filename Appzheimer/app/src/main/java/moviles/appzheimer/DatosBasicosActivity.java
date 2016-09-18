package moviles.appzheimer;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DatosBasicosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_basicos);
        TextView nombre = (TextView) findViewById(R.id.textNombreUsuarioDatosBasicos);
        TextView edad = (TextView) findViewById(R.id.textEdadDatosBasicos);
        try
        {
            InputStream archivo = openFileInput(CrearUsuarioActivity.DATOSUSUARIO);
            if(archivo!=null)
            {
                InputStreamReader temp = new InputStreamReader(archivo);
                BufferedReader lector = new BufferedReader(temp);
                nombre.setText(lector.readLine());
                edad.setText(lector.readLine());
            }
        }
        catch(Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
    }
}
