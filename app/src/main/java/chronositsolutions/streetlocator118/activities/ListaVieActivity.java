package chronositsolutions.streetlocator118.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import chronositsolutions.streetlocator118.Comune;
import chronositsolutions.streetlocator118.R;
import chronositsolutions.streetlocator118.Via;
import chronositsolutions.streetlocator118.adapters.VieAdapter;

public class ListaVieActivity extends AppCompatActivity {

    private ArrayList<Via> listaVie;
    private ListView listView;
    private Intent intentIn;
    private Intent intentOut;
    private String IDIstatRicevuto;
    String nomeComuneRicevuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vie);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Creo l'azione di ricezione, intercettando l'evento scatenato dalla activity delle province
        intentIn = getIntent();

        // Ottengo la ListView da riempire
        listView = (ListView) findViewById(R.id.listViewVie);

        // Setto il listener per il click sulla singola via
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Via selectedVia = (Via) parent.getItemAtPosition(position);

                // Creo l'azione per apriinte
                intentOut = new Intent(ListaVieActivity.this, ListaSegnalazioniActivity.class);

                // Passo i parametri che servono alla activity per mostrare le vie relative ad un solo comune
                intentOut.putExtra("IDVia", selectedVia.getIdVia());
                intentOut.putExtra("NomeVia", selectedVia.getNome());
                startActivity(intentOut);

            }

        });

        // Ottengo il parametro passato alla activity
        IDIstatRicevuto = intentIn.getStringExtra("IDIstat");
        nomeComuneRicevuto = intentIn.getStringExtra("NomeComune");

        createListVie( IDIstatRicevuto );
        setTitle( nomeComuneRicevuto );

    }

    private void createListVie ( String IDIstat ){

        try {

            listaVie = Via.getListaVie( IDIstat );

            if ( listaVie == null ){

                listaVie = new ArrayList<Via>();

                new AlertDialog.Builder(ListaVieActivity.this)
                        .setTitle("Nessuna via trovata")
                        .setMessage("Mi dispiace, ma non ho trovato alcuna via per questo comune.")
                        .setCancelable(false)
                        .setPositiveButton("Chiudi", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                                finish();

                            }
                        }).create().show();

            } else {

                VieAdapter vieAdapter = null;

                vieAdapter = new VieAdapter(ListaVieActivity.this, R.layout.element_via_layout, listaVie);

                listView.setAdapter(vieAdapter);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
