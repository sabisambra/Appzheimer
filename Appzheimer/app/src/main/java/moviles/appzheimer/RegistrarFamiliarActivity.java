package moviles.appzheimer;

import android.content.Context;
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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
            imagen.setImageBitmap(imageBitmap);
            Uri tempUri = getImageUri(getApplicationContext(),imageBitmap);
            File finalFile = new File(getRealPathFromURI(tempUri));
            rutaimagen = finalFile.getAbsolutePath();
            Log.i("NombreFile",finalFile.getAbsolutePath());
        }
    }

    public Uri getImageUri(Context inContext, Bitmap imagenP)
    {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        imagenP.compress(Bitmap.CompressFormat.JPEG,100,bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(),imagenP,"TituloImagen",null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri URI)
    {
        Cursor cursor = getContentResolver().query(URI,null,null,null,null);
        cursor.moveToFirst();
        int indice = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(indice);
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
            Log.i("Poraqui paso:", "jajaja");
            OutputStreamWriter impresora = new OutputStreamWriter(openFileOutput(FamiliaresActivity.DATOS_FAMILIARES, MODE_APPEND));
            EditText nombre = (EditText) findViewById(R.id.editNombreFamiliarNuevo);
            EditText parentesco = (EditText) findViewById(R.id.editParentescoFamiliarNuevo);
            impresora.write(lineaNueva);
            impresora.write(nombre.getText().toString());
            impresora.write(lineaNueva);
            impresora.write(parentesco.getText().toString());
            impresora.write(lineaNueva);
            impresora.write(rutaimagen);
            impresora.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }
        startActivity(intent);
    }


}
