package moviles.appzheimer;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class RegistrarFamiliarActivity extends AppCompatActivity {

    /**
     * Constante para poderr tomar fotos
     */
    static final int REQUEST_IMAGE_CAPTURE = 1;

    /**
     * El image view para el preview de la imagen
     */
    private ImageView imagen;

    /**
     * Ruta de la imagen tomada
     */
    private String rutaimagen;

    /**
     * Metodo que crea la vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_familiar);
        imagen = (ImageView) findViewById(R.id.imagenFamiliarNuevo);
        rutaimagen = "No hay imagen disponible";
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
            Uri fotoNueva = data.getData();
            imagen.setImageBitmap(imageBitmap);
            rutaimagen = getPath(fotoNueva);
        }
    }

    public String getPath(Uri uri)
    {
        // just some safety built in
        if( uri == null ) {
            // TODO perform some logging or show user feedback
            return null;
        }
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if( cursor != null ){
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        // this is our fallback here
        return uri.getPath();
    }

    /**
     * Metodo que sirve para registrar el familiar nuevo
     * @param v
     */
    public void registrarFamiliar(View v)
    {
        String lineaNueva = System.getProperty("line.separator");
        Intent intent = new Intent(this,FamiliaresActivity.class);
        try
        {
            OutputStreamWriter impresora = new OutputStreamWriter(openFileOutput(FamiliaresActivity.DATOS_FAMILIARES, MODE_APPEND));
            EditText nombre = (EditText) findViewById(R.id.editNombreFamiliarNuevo);
            EditText parentesco = (EditText) findViewById(R.id.editParentescoFamiliarNuevo);
            impresora.write(nombre.getText().toString());
            impresora.write(lineaNueva);
            impresora.write(parentesco.getText().toString());
            impresora.write(rutaimagen);
            impresora.close();
        }
        catch (Exception e)
        {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
        startActivity(intent);
    }


}
