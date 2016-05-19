package chronositsolutions.streetlocator118;


import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Provincia {



    private String idProvincia;
    private String nome;
    private String sigla;

    public Provincia() {

    }

    public Provincia(String idProvincia, String nomeProvincia, String siglaProvincia) {

        this.idProvincia = idProvincia;
        this.nome = nomeProvincia;
        this.sigla = siglaProvincia;

    }

    public static ArrayList<Provincia> getListaProvince () throws ExecutionException, InterruptedException, JSONException {

        // ArrayList di oggetti province
        ArrayList<Provincia> result = null;

        String requestResult = new DatabaseRequest().execute("province.php","").get();

        JSONArray jsonResult = new JSONArray( requestResult );

        if ( !jsonResult.getString(0).equalsIgnoreCase("error") ){

            // ["error","Nessuna via trovata"]
            // [getString[0], getString[1]

            result = new ArrayList<Provincia>();

            for (int i = 0; i < jsonResult.length(); i++ ){

                String IDProvincia = jsonResult.getJSONObject(i).getString("IDProvincia");
                String NomeProvincia = jsonResult.getJSONObject(i).getString("Nome");
                String Sigla = jsonResult.getJSONObject(i).getString("Sigla");

                result.add( new Provincia(IDProvincia, NomeProvincia, Sigla) );

            }

        }

        return result;

    }

    /**
     * Consente di ottenere un oggetto Provincia tramite il suo ID.
     * Ogni volta richiede al database le informazioni.
     * @param ProvinciaID
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     * @throws JSONException
     */
    public static Provincia getProvinciaByID(String ProvinciaID) throws ExecutionException, InterruptedException, JSONException {

        Provincia result = null;

        // Cerco tutti i comuni dal database e ottengo la string in formato JSON
        String requestResult = new DatabaseRequest().execute("province.php", "do=readprovincia&idprovincia="+ProvinciaID).get();

        // Creo un oggetto JSON partendo dalla stringa ottenuta dal server
        JSONArray jsonResult = new JSONArray( requestResult );

        // Estraggo i dati che mi servono e creo un nuovo oggetto.
        // Essendo un array, ogni elemento dell'array è contenuto tra { elemento }
        // Aggiungo ogni elemento array dentro alla ArrayList

        String IDProvincia = jsonResult.getJSONObject(0).getString("IDProvincia");
        String NomeProvincia = jsonResult.getJSONObject(0).getString("Nome");
        String SiglaProvincia = jsonResult.getJSONObject(0).getString("Sigla");
        result = new Provincia(IDProvincia, NomeProvincia, SiglaProvincia);


        return result;

    }

    public String getIdProvincia() {
        return this.idProvincia;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSigla() {
        return this.sigla;
    }

    public void setIdProvincia(String idProvincia) {
        this.idProvincia = idProvincia;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
