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

import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return  true;
    }

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
            }
            catch (Exception e)
            {
                new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
            }
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void verDatosBasicos(View v)
    {

    }

    public void reconocerVoz(View v)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Voice Recognition Demo...");
        startActivityForResult(intent,REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK)
        {
            Log.d("Hola", "Hola");
        }
    }


}
