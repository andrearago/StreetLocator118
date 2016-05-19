package chronositsolutions.streetlocator118.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import chronositsolutions.streetlocator118.R;
import chronositsolutions.streetlocator118.Via;
import chronositsolutions.streetlocator118.model.Segnalazione;

public class ViewSegnalazioneActivity extends AppCompatActivity {

    private Intent intentIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_segnalazione);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        intentIn = getIntent();

        String nomeViaRicevuto = intentIn.getStringExtra("NomeVia");
        String numCivicoRicevuto = intentIn.getStringExtra("NumCivico");

        String IDSegnalazioneRicevuto = intentIn.getStringExtra("IDSegnalazione");

        Segnalazione segnalazioneDaVisualizzare = null;

        try {

            segnalazioneDaVisualizzare = Segnalazione.getSegnalazione( IDSegnalazioneRicevuto );
            TextView nomeVia = (TextView) findViewById(R.id.segnalazioneNomeVia);
            TextView numCivico = (TextView) findViewById(R.id.segnalazioneNumCivico);
            TextView testoSegnalazione = (TextView) findViewById(R.id.segnalazioneTestoSegnalazione);

            nomeVia.setText( nomeViaRicevuto );
            numCivico.setText( segnalazioneDaVisualizzare.getCivico() );
            testoSegnalazione.setText( segnalazioneDaVisualizzare.getTestoSegnalazione() );

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
