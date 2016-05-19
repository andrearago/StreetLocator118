package chronositsolutions.streetlocator118.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import chronositsolutions.streetlocator118.Comune;
import chronositsolutions.streetlocator118.R;
import chronositsolutions.streetlocator118.Via;
import chronositsolutions.streetlocator118.adapters.SegnalazioniAdapter;
import chronositsolutions.streetlocator118.model.Segnalazione;

public class ListaSegnalazioniActivity extends AppCompatActivity {

    private ListView listView;
    private Intent intentIn;
    private Intent intentOut;
    private ArrayList<Segnalazione> listaSegnalazioni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_segnalazioni);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView = (ListView) findViewById(R.id.segnalazioniListView);

        if ( listView != null ){

            intentIn = getIntent();

            String idViaRicevuto = intentIn.getStringExtra("IDVia");
            final String nomeViaRicevuto = intentIn.getStringExtra("NomeVia");

            try {

               createListaSegnalazioni( idViaRicevuto );

                // Setto il listener per onclick sugli eventi

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        Segnalazione selectedSegnalazione = (Segnalazione) parent.getItemAtPosition(position);

                        // Creo l'azione per aprire una la activity della singola segnalazione
                        intentOut = new Intent(ListaSegnalazioniActivity.this, ViewSegnalazioneActivity.class);

                        // Passo i parametri che servono alla activity per mostrare le vie relative ad un solo comune
                        intentOut.putExtra("IDSegnalazione", selectedSegnalazione.getIDSegnalazione());
                        intentOut.putExtra("NumCivico", selectedSegnalazione.getCivico());
                        intentOut.putExtra("NomeVia", nomeViaRicevuto );
                        startActivity(intentOut);

                    }

                });

                setTitle( nomeViaRicevuto );

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    private void createListaSegnalazioni ( String IDVia ) throws InterruptedException, ExecutionException, JSONException {

        listaSegnalazioni = new ArrayList<Segnalazione>();

        Via viaSelezionata = Via.getViaById( IDVia );

        listaSegnalazioni = Segnalazione.getListSegnalazioniByIdVia(IDVia);

        if ( listaSegnalazioni != null ){

            SegnalazioniAdapter listaAdapter = null;

            listaAdapter = new SegnalazioniAdapter(ListaSegnalazioniActivity.this, R.layout.elemento_segnalazione_listview, listaSegnalazioni);

            listView.setAdapter(listaAdapter);

        }



    }

}
