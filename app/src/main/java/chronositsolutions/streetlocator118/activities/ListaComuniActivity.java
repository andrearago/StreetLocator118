package chronositsolutions.streetlocator118.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import chronositsolutions.streetlocator118.Comune;
import chronositsolutions.streetlocator118.Provincia;
import chronositsolutions.streetlocator118.R;
import chronositsolutions.streetlocator118.adapters.ComuniAdapter;

public class ListaComuniActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Comune> listaComuni;
    private String IDProvinciaRicevuto;
    private Intent intentIn;
    private Intent intentOut;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comuni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.listViewComuni);

        // Creo l'azione di ricezione, intercettando l'evento scatenato dalla activity delle province
        intentIn = getIntent();

        // Ricevo dalla activity chiamante l'id della provincia
        IDProvinciaRicevuto = intentIn.getStringExtra("IDProvincia");

        // Se ricevo l'IDProvincia allora creo la lista, altrimenti mostro errore
        try {

            createListaComuni( IDProvinciaRicevuto );

            // Setto il listener per onclick sugli eventi

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Comune selectedComune = (Comune) parent.getItemAtPosition(position);

                    // Creo l'azione per aprire una la activity delle vie
                    intentOut = new Intent(ListaComuniActivity.this, ListaVieActivity.class);

                    // Passo i parametri che servono alla activity per mostrare le vie relative ad un solo comune
                    intentOut.putExtra("IDIstat", selectedComune.getIdIstat());
                    intentOut.putExtra("NomeComune", selectedComune.getNomeComune());
                    startActivity(intentOut);

                }

            });

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();

        } catch (JSONException e) {

            e.printStackTrace();

        } catch (NullPointerException e){

            e.printStackTrace();
        }

    }

    private void createListaComuni( String IDProvincia ) throws InterruptedException, ExecutionException, JSONException {

        listaComuni = new ArrayList<Comune>();

        //Provincia provinciaSelezionata = Provincia.getProvinciaByID( IDProvincia );

        listaComuni = Comune.buildListaComuni( IDProvincia );

        if ( listaComuni != null ){

            ComuniAdapter listaAdapter = null;

            listaAdapter = new ComuniAdapter(ListaComuniActivity.this, R.layout.element_comuni_adapter, listaComuni);

            listView.setAdapter(listaAdapter);

        }

    }

}
