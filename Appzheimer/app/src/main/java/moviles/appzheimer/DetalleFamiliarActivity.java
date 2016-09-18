package moviles.appzheimer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import mundo.Familiar;

public class DetalleFamiliarActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_familiar);
        Intent i = getIntent();
        String nombreIntent = i.getStringExtra("Nombre");
        String relacionIntent = i.getStringExtra("Relacion");
        String imagenIntent = i.getStringExtra("Imagen");
        TextView nombre = (TextView) findViewById(R.id.textNombreDetalle);
        nombre.setText(nombreIntent);
        TextView parentesco = (TextView) findViewById(R.id.textRelacionDetalle);
        parentesco.setText(relacionIntent);
        TextView imagen = (TextView) findViewById(R.id.textImagenDetalle);
        if (imagenIntent.equals("No hay imagen disponible"))
        {
            imagen.setText(imagenIntent);
        }
        else
        {
            imagen.setVisibility(View.INVISIBLE);
        }
        ImageView imagenJPG = (ImageView) findViewById(R.id.imagenFamiliarDetalle);
        Bitmap foto = BitmapFactory.decodeFile(imagenIntent);
        imagenJPG.setImageBitmap(foto);
    }

    public void volverFamiliares(View v)
    {
        Intent intent = new Intent(this, FamiliaresActivity.class);
        startActivity(intent);
    }
}
