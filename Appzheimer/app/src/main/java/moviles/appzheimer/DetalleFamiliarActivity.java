package moviles.appzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
            
        }
    }
}
