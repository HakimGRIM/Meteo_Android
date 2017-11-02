package classique.meteo.rism.meteo;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

/**
 * Created by grim on 16/10/17.
 */

public class Asynchrone extends AsyncTask<List<City>, Integer, String>{

    //List<City> lst;
    /*public Asynchrone(List<City> list){
        this.lst = list;
    }*/

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(MainActivity.context, "Update Begin", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);
        // Mise à jour de la ProgressBar
        MainActivity.mProgressBar.setProgress(values[0]);
    }

    @Override
    protected String doInBackground(List<City>... cities) {
        int progress = 0;
        try{
            JSONResponseHandler json = new JSONResponseHandler();
            String uRL;

            for(City v : cities[0]){
                uRL = "https://query.yahooapis.com/v1/public/yql?q=select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\""+ v.toString() +"\")&format=json&env=store://datatables.org/alltableswithkeys";
                URL u = new URL(uRL);
                HttpURLConnection connect = (HttpURLConnection) u.openConnection();

                List<String> resultat = json.handleResponse(connect.getInputStream(), null);
                Log.d("MainActivity", resultat.toString());
                progress = progress + (100/cities[0].size());
                publishProgress(progress);

                v.setVitesse(Float.parseFloat(resultat.get(0).split(" ")[0]));
                v.setDirection(resultat.get(0).split(" ")[1].replace("(", ""));
                v.setTemperature(Float.parseFloat(resultat.get(1)));
                v.setPression(Float.parseFloat(resultat.get(2)));
                v.setDate(resultat.get(3));
            }
            //saveCityPref(MainActivity.listCity);

        } catch (ProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }

    return null;

    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(MainActivity.context, "Update Complete", Toast.LENGTH_SHORT).show();
    }

}
