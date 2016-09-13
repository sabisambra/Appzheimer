package moviles.appzheimer;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CrearUsuarioActivity extends AppCompatActivity {

    public static final String DATOSUSUARIO = "appzheimerDatosPaciente.txt";

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
            archivo.close();
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
    }

    public void crearUsuario(View v)
    {
        Intent intent = new Intent(this, MainActivity.class);
        EditText nombre = (EditText) findViewById(R.id.nombrePersonaRegistro);
        EditText edad = (EditText) findViewById(R.id.edadPersonaRegistro);
        startActivity(intent);
    }
}
