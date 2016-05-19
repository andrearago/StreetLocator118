package chronositsolutions.streetlocator118.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import chronositsolutions.streetlocator118.Comune;
import chronositsolutions.streetlocator118.R;

/**
 * Created by Andrea on 12/05/2016.
 */
public class ComuniAdapter extends ArrayAdapter<Comune> {

    private int layoutResource;

    public ComuniAdapter(Context context, int layoutResource, ArrayList<Comune> comuni) {
        super(context, layoutResource, comuni);
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
        Comune comune = getItem(position);

        // Se il comune ottenuto non è vuoto

        if ( comune != null ){

            // Creo gli elementi in cui visualizzerò il contenuto dell'oggetto Comune
            TextView IDIStat = (TextView) view.findViewById(R.id.idistat);
            TextView NomeComune = (TextView) view.findViewById(R.id.nomecomune);
            //TextView IDProvincia = (TextView) v.findViewById(R.id.idistat);

            if ( IDIStat != null ){

                IDIStat.setText(comune.getIdIstat());

            }

            if ( NomeComune != null ){

                NomeComune.setText(comune.getNomeComune());

            }

        }

        return view;
    }


}
