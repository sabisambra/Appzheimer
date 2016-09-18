package moviles.appzheimer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_familiares);
    }
}
