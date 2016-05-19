package chronositsolutions.streetlocator118.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import chronositsolutions.streetlocator118.Provincia;
import chronositsolutions.streetlocator118.R;

/**
 * Created by Andrea on 12/05/2016.
 */
public class ProvinciaAdapter extends ArrayAdapter<Provincia> {

    private int layoutResource;

    public ProvinciaAdapter(Context context, int layoutResource, ArrayList<Provincia> province) {
        super(context, layoutResource, province);
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
        Provincia provincia  = getItem(position);

        // Se il comune ottenuto non è vuoto

        if ( provincia != null ){

            // Creo gli elementi in cui visualizzerò il contenuto dell'oggetto Comune
            TextView NomeProvincia = (TextView) view.findViewById(R.id.nomeprovincia);
            TextView Sigla = (TextView) view.findViewById(R.id.sigla);
            //TextView IDProvincia = (TextView) v.findViewById(R.id.idistat);

            if ( NomeProvincia != null ){

                NomeProvincia.setText(provincia.getNome());

            }

            if ( Sigla != null ){

                Sigla.setText(provincia.getSigla());

            }

        }

        return view;
    }


}
