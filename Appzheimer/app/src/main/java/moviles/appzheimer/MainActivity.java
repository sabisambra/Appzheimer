package moviles.appzheimer;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;

    /**
     *Metodo que crea la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textoBienvenida = (TextView) findViewById(R.id.textBienvenida);
        try
        {
            InputStream archivo = openFileInput(CrearUsuarioActivity.DATOSUSUARIO);
            if(archivo!=null)
            {
                InputStreamReader temp = new InputStreamReader(archivo);
                BufferedReader lector = new BufferedReader(temp);
                textoBienvenida.setText("Hola " + lector.readLine());
            }
        }
        catch(Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
    }

    /**
     * Metodo para crear el menu
     * @param menu el menu que se va a crear
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

    /**
     * Metodo para que los elementos del menu funciones
     * @param item el item que se selecciono
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if(id==R.id.action_logout)
        {
            Intent intent = new Intent(this,CrearUsuarioActivity.class);
            try
            {
                OutputStreamWriter impresora = new OutputStreamWriter(openFileOutput(CrearUsuarioActivity.DATOSUSUARIO,0));
                impresora.write("No hay sesion");
                impresora.close();
                deleteFile(FamiliaresActivity.DATOS_FAMILIARES);
            }
            catch (Exception e)
            {
                new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
            }
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metodo que inicia la actividad para ver los datos basicos del usuario
     * @param v la vista con los datos basicos
     */
    public void verDatosBasicos(View v)
    {
        Intent intent = new Intent(this,DatosBasicosActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo que sirve para reconocer la voz de una persona
     * @param v la vista que se va a mostrar
     */
    public void reconocerVoz(View v)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Voice Recognition Demo...");
        startActivityForResult(intent,REQUEST_CODE);
    }

    /**
     * Meotdo para poder intepretar lo que el usuario dijo
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            Log.d("Hola", "Hola");
        }
    }

    public void verFamiliares(View v)
    {
        Intent intent = new Intent(this,FamiliaresActivity.class);
        startActivity(intent);
    }


}
