package chronositsolutions.streetlocator118;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.zip.CheckedOutputStream;

public class Comune {
    private String idIstat;
    private String nomeComune;
    private String idProvincia;
    private static ArrayList<Comune> listaComuni = null;

    public Comune(String IDIstat, String Nome, String IDProvincia) {

        this.idIstat = IDIstat;
        this.nomeComune = Nome;
        this.idProvincia = IDProvincia;

    }

    public static ArrayList<Comune> buildListaComuni ( String IDProvinciaIn ) throws JSONException, ExecutionException, InterruptedException {

        listaComuni = new ArrayList<Comune>();

        // Cerco tutti i comuni dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("comuni.php", "do=listcomunibyprovincia&idprovincia="+IDProvinciaIn).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        // Estraggo i dati che mi servono e creo un nuovo oggetto.
        // Essendo un array, ogni elemento dell'array è contenuto tra { elemento }
        // Aggiungo ogni elemento array dentro alla ArrayList

        for ( int i = 0; i < jsonResult.length(); i++  ){

            String IDIstat = jsonResult.getJSONObject(i).getString("IDIstat");
            String NomeComune = jsonResult.getJSONObject(i).getString("Nome");
            String IDProvincia = jsonResult.getJSONObject(i).getString("IDProvincia");
            listaComuni.add(new Comune(IDIstat, NomeComune, IDProvincia));

        }

        return listaComuni;

    }

    public static ArrayList<Comune> getListaComuni (){

        return listaComuni;

    }

    public String getIdIstat() {

        return this.idIstat;
    }

    public void setIdIstat(String idIstat) {

        this.idIstat = idIstat;
    }

    public String getNomeComune() {

        return this.nomeComune;
    }

    public String getNomeComune(String IDIStat) throws JSONException, ExecutionException, InterruptedException {

        String nomeResult = null;

        // Cerco tutti i comuni dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("comuni.php", "do=readcomune&idistat"+IDIStat).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        nomeResult = jsonResult.getJSONObject(0).getString("Nome");

        return nomeResult;
    }

    public void setNomeComune(String comune) {

        this.nomeComune = comune;

    }

    public String getIdProvincia() {

        return this.idProvincia;
    }

    public void setIdProvincia(String idProvincia) {

        this.idProvincia = idProvincia;
    }
}
