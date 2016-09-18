package moviles.appzheimer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
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

public class FamiliaresActivity extends ActionBarActivity {

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
                linea = lector.readLine();
                int cantidadLineas = 1;
                Familiar fam = new Familiar();
                while(linea!=null && linea!="")
                {
                    //Se lee un nombre
                    if(cantidadLineas==1)
                    {
                        nombreFamiliares.add(linea);
                        fam.setNombre(linea);
                        cantidadLineas++;
                    }
                    //Se lee el parentesco
                    else if (cantidadLineas==2)
                    {
                        cantidadLineas++;
                        fam.setParentesco(linea);
                    }
                    //Se esta leyendo la ruta de una imagen
                    else
                    {
                        cantidadLineas = 1;
                        fam.setRutaImagen(linea);
                        familiaresMundo.add(fam);
                    }
                    Log.i("Lee:", linea);
                    linea = lector.readLine();
                }
            }
            else
            {

            }
            archivo.close();
        } catch (Exception e) {
            new AlertDialog.Builder(this).setTitle("Informacion").setMessage("En el momento no tienes familiares registrados").setNeutralButton("Cerrar", null).show();
        }

        //Se llena la lista de la interfaz
        familiares = (ListView) findViewById(R.id.ListFamiliares);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.lista_item, R.id.labelLista, nombreFamiliares);
        familiares.setAdapter(adapter);
        familiares.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Log.i("Seleccion:", position+"");
                Familiar familiarSeleccionado = familiaresMundo.get(position);
                Intent j = new Intent(getApplicationContext(),DetalleFamiliarActivity.class);
                j.putExtra("Nombre",familiarSeleccionado.getNombre());
                j.putExtra("Relacion",familiarSeleccionado.getParentesco());
                j.putExtra("Imagen",familiarSeleccionado.getRutaImagen());
                startActivity(j);
            }
        });
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

    public void volverMenuPrincipal(View v)
    {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }


}
