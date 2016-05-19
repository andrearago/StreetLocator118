package chronositsolutions.streetlocator118;

import android.provider.ContactsContract;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Via {

    private String idVia;
    private String nome;
    private String idIstat;
    private String indicazioniStradali;

    public Via() {
        // Necessario solo per i metodi statici
    }

    public Via(String idVia, String nome, String idIstat, String indicazioniStradali) {

        this.idVia = idVia;
        this.nome = nome;
        this.idIstat = idIstat;
        this.indicazioniStradali = indicazioniStradali;

    }

    public Via(String IDVia) throws JSONException, ExecutionException, InterruptedException {

        // Cerco la via dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("vie.php", "do=readvia&idvia="+IDVia).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        // Estraggo i dati che mi servono e creo un nuovo oggetto

        this.idVia = jsonResult.getJSONObject(0).getString("IDVia");
        this.nome = jsonResult.getJSONObject(0).getString("NomeVia");
        this.idIstat = jsonResult.getJSONObject(0).getString("IDIstat");
        this.indicazioniStradali = jsonResult.getJSONObject(0).getString("IndicazioniStradali");


    }

    public static Via getViaById( String IDVia ) throws JSONException, ExecutionException, InterruptedException {

        Via result = null;

        // Cerco la via dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("vie.php", "do=readvia&idvia="+IDVia).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        // Estraggo i dati che mi servono e creo un nuovo oggetto
        String idVia = jsonResult.getJSONObject(0).getString("IDVia");
        String NomeVia = jsonResult.getJSONObject(0).getString("NomeVia");
        String idIstat = jsonResult.getJSONObject(0).getString("IDIstat");
        String indicazioniStradali = jsonResult.getJSONObject(0).getString("IndicazioniStradali");

        result = new Via(idVia, NomeVia, idIstat, indicazioniStradali);

        return result;
    }

    public static ArrayList<Via> getListaVie () throws ExecutionException, InterruptedException, JSONException {

        // Creo la lista di vie vuota
        ArrayList<Via> result = new ArrayList<Via>();

        // Cerco la via dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("vie.php", "").get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        // Estraggo i dati che mi servono e creo un nuovo oggetto.
        // Essendo un array, ogni elemento dell'array è contenuto tra { elemento }
        // Aggiungo ogni elemento array dentro alla ArrayList

        for ( int i = 0; i < jsonResult.length(); i++  ){

            String IDVia = jsonResult.getJSONObject(i).getString("IDVia");
            String NomeVia = jsonResult.getJSONObject(i).getString("NomeVia");
            String IDIstat = jsonResult.getJSONObject(i).getString("IDIstat");
            String IndicazioniStradali = jsonResult.getJSONObject(i).getString("IndicazioniStradali");
            result.add(new Via(IDVia, NomeVia, IDIstat, IndicazioniStradali));

        }

        return result;
    }

    public static ArrayList<Via> getListaVie ( String IDIstatIN ) throws ExecutionException, InterruptedException, JSONException {

        // Creo la lista di vie vuota
        ArrayList<Via> result = null;

        // Cerco la via dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("vie.php", "idistat="+IDIstatIN ).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        if ( !jsonResult.getString(0).equalsIgnoreCase("error")  ){

            result = new ArrayList<Via>();

            // Estraggo i dati che mi servono e creo un nuovo oggetto.
            // Essendo un array, ogni elemento dell'array è contenuto tra { elemento }
            // Aggiungo ogni elemento array dentro alla ArrayList

            for ( int i = 0; i < jsonResult.length(); i++  ){

                String IDVia = jsonResult.getJSONObject(i).getString("IDVia");
                String NomeVia = jsonResult.getJSONObject(i).getString("NomeVia");
                String IDIstat = jsonResult.getJSONObject(i).getString("IDIstat");
                String IndicazioniStradali = jsonResult.getJSONObject(i).getString("IndicazioniStradali");
                result.add(new Via(IDVia, NomeVia, IDIstat, IndicazioniStradali));

            }

        }

        return result;
    }

    public String getIndicazioniStradali() {

        return this.indicazioniStradali;
    }

    public void setIndicazioniStradali(String indicazioniStradali) {

        this.indicazioniStradali = indicazioniStradali;
    }

    public String getIdVia() {
        return idVia;
    }

    public void setIdVia(String idVia) {
        this.idVia = idVia;
    }

    public String getNome() {

        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdIstat() {
        return idIstat;
    }

}
