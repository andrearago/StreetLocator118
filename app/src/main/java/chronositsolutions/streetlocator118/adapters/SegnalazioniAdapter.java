package chronositsolutions.streetlocator118.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import chronositsolutions.streetlocator118.R;
import chronositsolutions.streetlocator118.model.Segnalazione;

/**
 * Created by Andrea on 13/05/2016.
 */
public class SegnalazioniAdapter extends ArrayAdapter<Segnalazione> {

private int layoutResource;

public SegnalazioniAdapter(Context context, int layoutResource, ArrayList<Segnalazione> segnalazioni) {
        super(context, layoutResource, segnalazioni);
        this.layoutResource = layoutResource;
        }

        @Override
    public View getView(int position, View convertView, ViewGroup parent){

        // Ottengo una view dove visualizzare l'oggetto
        View view = convertView;

        if (view == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            view = layoutInflater.inflate(layoutResource, null);
        }

        // Ottengo il singolo oggetto Comune da visualizzare
        Segnalazione segnalazione = getItem(position);

        // Se il comune ottenuto non è vuoto

        if ( segnalazione != null ){

            // Creo gli elementi in cui visualizzerò il contenuto dell'oggetto Comune
            TextView TestoSegnalazione = (TextView) view.findViewById(R.id.testosegnalazione);
            TextView NumCivico = (TextView) view.findViewById(R.id.civico);
            //TextView IDProvincia = (TextView) v.findViewById(R.id.idistat);

            if ( TestoSegnalazione != null ){

                //TestoSegnalazione.setText(segnalazione.getTestoSegnalazione().substring(0, 40)+"...");
                TestoSegnalazione.setText(segnalazione.getIDSegnalazione());

            }

            if ( NumCivico != null ){

                NumCivico.setText(segnalazione.getCivico());

            }

        }

        return view;
    }


}
