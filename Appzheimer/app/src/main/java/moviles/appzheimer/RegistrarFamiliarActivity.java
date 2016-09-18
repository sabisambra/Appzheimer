package moviles.appzheimer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;

public class RegistrarFamiliarActivity extends AppCompatActivity {

    /**
     * Constante para poderr tomar fotos
     */
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imagen;

    /**
     * Metodo que crea la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_familiar);
        imagen = (ImageView) findViewById(R.id.imagenFamiliarNuevo);
    }

    /**
     * Metodo para poder comenzar la captura de foto del familiar
     * @param v
     */
    public void agregarFotoFamiliar(View v)
    {
        tomarFoto();
    }

    /**
     * Metodo que permite tomar la foto desde la aplicacion
     */
    public void tomarFoto()
    {
        Intent tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(tomarFotoIntent.resolveActivity(getPackageManager())!=null)
        {
            startActivityForResult(tomarFotoIntent,REQUEST_IMAGE_CAPTURE);
        }
    }

    /**
     * Metodo para poder manipular la informacion de la foto
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(requestCode==REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imagen.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Metodo que sirve para registrar el familiar nuevo
     * @param v
     */
    public void registrarFamiliar(View v)
    {
        Intent intent = new Intent(this,FamiliaresActivity.class);
        try
        {
            OutputStreamWriter impresora = new OutputStreamWriter(openFileOutput(FamiliaresActivity.DATOS_FAMILIARES, MODE_APPEND));
            EditText nombre = (EditText) findViewById(R.id.editNombreFamiliarNuevo);
            EditText parentesco = (EditText) findViewById(R.id.editParentescoFamiliarNuevo);
            impresora.write(nombre.getText().toString());
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
        startActivity(intent);
    }


}
