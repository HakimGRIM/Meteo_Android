package classique.meteo.rism.meteo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CityActivity extends AppCompatActivity {

    private TextView ville;
    private TextView pays;
    private TextView vent;
    private TextView direction;
    private TextView temperature;
    private TextView pression;
    private TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        ville = (TextView) findViewById(R.id.idVille);
        pays = (TextView) findViewById(R.id.idPays);
        vent = (TextView) findViewById(R.id.idVent);
        direction = (TextView) findViewById(R.id.idDirection);
        temperature = (TextView) findViewById(R.id.idTempérature);
        pression = (TextView) findViewById(R.id.idPression);
        date = (TextView) findViewById(R.id.idDate);

        Intent intent = getIntent();

        if (intent != null) {
            City objet = (City) intent.getSerializableExtra("Det");
            ville.setText("Ville    " + objet.getVille());
            pays.setText("Pays    " + objet.getPays());
            vent.setText("Vent    " + objet.getVitesse());
            direction.setText("Direction    " + objet.getDirection());
            temperature.setText("Température    " + objet.getTemperature());
            pression.setText("Pression    " + objet.getPression());
            date.setText("Date  " + objet.getDate());

        }
    }
}
