package moviles.appzheimer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import mundo.Familiar;

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
     * Arraylist donde se van a guardar los familiares del pariente
     */
    private ArrayList<Familiar> familiaresMundo;

    /**
     * ArrayList que va a contener los nombre de los familiares para alimentar el listview
     */
    private ArrayList<String> nombreFamiliares;


    /**
     * Metodo que crea la vista de los familiares
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familiares);
        familiaresMundo = new ArrayList<Familiar>();
        nombreFamiliares = new ArrayList<String>();
        try {
            InputStream archivo = openFileInput(DATOS_FAMILIARES);
            if (archivo != null) {
                InputStreamReader temp = new InputStreamReader(archivo);
                BufferedReader lector = new BufferedReader(temp);
                String linea = lector.readLine();
                while (linea != null) {
                    nombreFamiliares.add(linea);
                }
            } else {

            }
            archivo.close();
        } catch (Exception e) {
            new AlertDialog.Builder(this).setTitle("Error").setMessage(e.getMessage()).setNeutralButton("Cerrar", null).show();
        }

        //Se llena la lista de la interfaz
        familiares = (ListView) findViewById(R.id.ListFamiliares);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.labelLista, nombreFamiliares);
        familiares.setAdapter(adapter);
        /**familiares.setOnClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String familiarSeleccionado = ((TextView) view).getText().toString();
            }
        });*/
    }

    /**
     * Metodo para agregar un familiar
     */
    public void agregarFamiliar(View v) {
        Intent intent = new Intent(this, RegistrarFamiliarActivity.class);
        startActivity(intent);
    }

    /**
     * Metodo para ver el detalle del familiar seleccionado
     *
     * @param v
     */
    public void verFamiliar(View v) {

    }


}
