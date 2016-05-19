package chronositsolutions.streetlocator118.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import chronositsolutions.streetlocator118.Comune;
import chronositsolutions.streetlocator118.Provincia;
import chronositsolutions.streetlocator118.R;
import chronositsolutions.streetlocator118.adapters.ProvinciaAdapter;

public class ListaProvinceActivity extends AppCompatActivity {

    private ArrayList<Provincia> listaProvince;
    private ListView listView;
    private Intent intentOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_province);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Ottengo i riferimenti alla ListView
        listView = (ListView) findViewById(R.id.provinceListView);

        // Creo l'azione per aprire una la activity delle vie
        intentOut = new Intent(ListaProvinceActivity.this, ListaComuniActivity.class);

        // Setto il listener per onclick sugli eventi

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Provincia selectedProvincia = (Provincia) parent.getItemAtPosition(position);

                // Passo i parametri che servono alla activity per mostrare le vie relative ad un solo comune
                intentOut.putExtra("IDProvincia", selectedProvincia.getIdProvincia());
                startActivity(intentOut);

            }

        });

        try {

            createListaProvince();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void createListaProvince () throws InterruptedException, ExecutionException, JSONException {

        listaProvince = new ArrayList<Provincia>();

        listaProvince = Provincia.getListaProvince();

        if ( listaProvince != null ){

            ProvinciaAdapter listaAdapter = null;

            listaAdapter = new ProvinciaAdapter(ListaProvinceActivity.this, R.layout.elemento_provincia_listview, listaProvince);

            listView.setAdapter(listaAdapter);

        }

    }

}
