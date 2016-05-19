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
import chronositsolutions.streetlocator118.Via;

/**
 * Created by Andrea on 12/05/2016.
 */
public class VieAdapter extends ArrayAdapter<Via> {

    private int layoutResource;

    public VieAdapter(Context context, int layoutResource, ArrayList<Via> vie) {
        super(context, layoutResource, vie);
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
        Via via = getItem(position);

        // Se il comune ottenuto non è vuoto

        if ( via != null ){

            // Creo gli elementi in cui visualizzerò il contenuto dell'oggetto Comune
            TextView NomeVia = (TextView) view.findViewById(R.id.nomevia);
            ///TextView Distanza = (TextView) view.findViewById(R.id.distanza);
            //TextView IDProvincia = (TextView) v.findViewById(R.id.idistat);

            if ( NomeVia != null ){

                NomeVia.setText(via.getNome());

            }

        }

        return view;
    }


}
