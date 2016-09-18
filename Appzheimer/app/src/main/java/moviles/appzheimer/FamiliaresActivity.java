package moviles.appzheimer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class FamiliaresActivity extends AppCompatActivity {

    /**
     * Constante con el nombre del file donde se va a guardar la informacion de los familiares
     */
    public final static String DATOS_FAMILIARES = "AppzheimerFamiliares.txt";

    /**
     * El listview donde se van a mostrar los familiares
     */
    private ListView familiares;

    /**
     * Metodo que crea la vista de los familiares
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familiares);
    }

    /**
     * Metodo para agregar un familiar
     */
    public void agregarFamiliar(View v)
    {
        Intent intent = new Intent(this,RegistrarFamiliarActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo para ver el detalle del familiar seleccionado
     * @param v
     */
    public void verFamiliar(View v)
    {

    }

}
