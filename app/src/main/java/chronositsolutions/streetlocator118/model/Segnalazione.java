package chronositsolutions.streetlocator118.model;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import chronositsolutions.streetlocator118.DatabaseRequest;

/**
 * Created by Andrea on 13/05/2016.
 */
public class Segnalazione {

    private String IDSegnalazione;
    private String TestoSegnalazione;
    private String Civico;
    private String IDVia;
    private String IDOperatore;

    public Segnalazione(String IDSegnalazione, String testoSegnalazione, String civico, String IDVia, String IDOperatore) {

        this.IDSegnalazione = IDSegnalazione;
        this.TestoSegnalazione = testoSegnalazione;
        this.Civico = civico;
        this.IDVia = IDVia;
        this.IDOperatore = IDOperatore;

    }

    public String getIDSegnalazione() {

        return this.IDSegnalazione;

    }

    public void setIDSegnalazione(String IDSegnalazione) {

        this.IDSegnalazione = IDSegnalazione;

    }

    public void setTestoSegnalazione(String testoSegnalazione) {

        TestoSegnalazione = testoSegnalazione;

    }

    public String getCivico() {

        return Civico;

    }

    public void setCivico(String civico) {

        this.Civico = civico;

    }

    public String getIDVia() {

        return this.IDVia;

    }

    public void setIDVia(String IDVia) {

        this.IDVia = IDVia;

    }

    public String getIDOperatore() {

        return this.IDOperatore;

    }

    public void setIDOperatore(String IDOperatore) {

        this.IDOperatore = IDOperatore;

    }

    public String getTestoSegnalazione() {

        return this.TestoSegnalazione;

    }

    public static ArrayList<Segnalazione> getListSegnalazioniByIdVia( String IDViaIN ) throws ExecutionException, InterruptedException, JSONException {

        ArrayList<Segnalazione> result = null;

        // Cerco la via dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("segnalazioni.php", "idvia="+IDViaIN ).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        if ( !jsonResult.getString(0).equalsIgnoreCase("error")  ){

            result = new ArrayList<Segnalazione>();

            // Estraggo i dati che mi servono e creo un nuovo oggetto.
            // Essendo un array, ogni elemento dell'array è contenuto tra { elemento }
            // Aggiungo ogni elemento array dentro alla ArrayList

            for ( int i = 0; i < jsonResult.length(); i++  ){

                String IDSegnalazione = jsonResult.getJSONObject(i).getString("IDSegnalazione");
                String TestoSegnalazione = jsonResult.getJSONObject(i).getString("TestoSegnalazione");
                String NumCivico = jsonResult.getJSONObject(i).getString("NumCivico");
                String IDOperatore = jsonResult.getJSONObject(i).getString("IDOperatore");
                String IDVia = jsonResult.getJSONObject(i).getString("IDVia");
                result.add(new Segnalazione(IDSegnalazione, TestoSegnalazione, NumCivico, IDVia, IDOperatore));

            }

        }

        return result;

    }


    public Segnalazione getSegnalazione(){

        return this;

    }

    public static Segnalazione getSegnalazione( String IDSegnalazioneIN ) throws ExecutionException, InterruptedException, JSONException {

        Segnalazione result = null;

        // Cerco tutti i comuni dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("segnalazioni.php", "do=readsegnalazione&idsegnalazione="+IDSegnalazioneIN ).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        if ( !jsonResult.getString(0).equalsIgnoreCase("error") ){

            String IDSegnalazione = jsonResult.getJSONObject(0).getString("IDSegnalazione");
            String TestoSegnalazione = jsonResult.getJSONObject(0).getString("TestoSegnalazione");
            String NumCivico = jsonResult.getJSONObject(0).getString("NumCivico");
            String IDVia = jsonResult.getJSONObject(0).getString("IDVia");
            String IDOperatore = jsonResult.getJSONObject(0).getString("IDOperatore");

            result = new Segnalazione(IDSegnalazione, TestoSegnalazione, NumCivico, IDVia, IDOperatore);

        }

        return result;

    }

}
