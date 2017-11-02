package classique.meteo.rism.meteo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddCityActivity extends AppCompatActivity {

    City ajout;
    private EditText ville;
    private EditText pays;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        ville = (EditText) findViewById(R.id.city);
        pays = (EditText) findViewById(R.id.country);
        btn = (Button) findViewById(R.id.sauvgarde);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajout = new City(ville.getText().toString(), pays.getText().toString());
                Intent intent = new Intent();
                intent.putExtra("City", ajout);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
