package chronositsolutions.streetlocator118;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;

import chronositsolutions.streetlocator118.activities.ListaProvinceActivity;

/**
 * Created by andrea on 06/05/16.
 */
public class DatabaseRequest extends AsyncTask<String, Void, String> {

    /* AsyncTask: è un classe di Android che consente di eseguire delle operazioni in un thread secondario
    Facciamo questo perchè non è possibile effettuare connessioni in un thread principale
     */

    String result = null;

    @Override
    protected String doInBackground(String... params) { //Serie di parametri

        // Preparo la richiesta in POST
        // Si aspetta la richiesta in formato GET (quindi con & ?)

        // NB: La class StandardCharsets non è disponibile in tutte le versioni di Android. Come risolvo?
        byte[] postData = params[1].getBytes( );
        int postLength = postData.length; // calcolo la lunghezza

        URL url = null;
        try {

            // Compongo l'url con la pagina da visitare
            url = new URL("http://streetlocator118.altervista.org/platform/"+params[0]);

        } catch (MalformedURLException e) {

            e.printStackTrace();

        }

        HttpURLConnection urlConnection = null;
        try {

            urlConnection = urlConnection = (HttpURLConnection) url.openConnection();

            // Setto la connessione con metodo POST

            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod( "POST" );
            urlConnection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty( "charset", "utf-8");
            urlConnection.setRequestProperty("Content-Length", Integer.toString( postLength ) );
            urlConnection.setUseCaches( false );

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scrivo i dati POST
        try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {

            wr.write( postData );

        } catch (IOException e) {

            e.printStackTrace();

        }

        // Leggo il testo prodotto dalla pagina

        BufferedReader bufferedReader = null;
        try {

            bufferedReader = new BufferedReader( new InputStreamReader( urlConnection.getInputStream() ) );

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            result = bufferedReader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
        }

        urlConnection.disconnect();

        return result;
    }
}
