package classique.meteo.rism.meteo;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private List<City> metVille = new ArrayList<City>();
    private ArrayAdapter<City> adapter = null;

    static ProgressBar mProgressBar;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.listView);
        mProgressBar = (ProgressBar) findViewById(R.id.pBAsync);

        context = getApplicationContext();

        metVille.add(new City("Paris", "France"));
      /*  metVille.add(new City("Oslo", "Norvège"));
        metVille.add(new City("Port-au-Prince", "Haiti"));
        metVille.add(new City("Alger", "Algérie"));
        metVille.add(new City("Belgrade", "Serbie"));
        metVille.add(new City("Kiev", "Ukraine"));
        metVille.add(new City("Mouscou", "Russie"));*/


        adapter = new ArrayAdapter<City>(MainActivity.this,
                android.R.layout.simple_list_item_1, metVille);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, CityActivity.class);
                intent.putExtra("Det", metVille.get(position));
                startActivity(intent);
            }
        });

        registerForContextMenu(mListView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            Intent intent = new Intent(MainActivity.this, AddCityActivity.class);
            //intent.putExtra("Det", metVille.get(position));
            startActivityForResult(intent, 250);
        }

        else if(item.getItemId() == R.id.actualiser) {
            Asynchrone calcul=new Asynchrone();
            calcul.execute(this.metVille);
        }

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.setHeaderTitle("Context Menu");
        menu.add(0, v.getId(), 0, "Supprimer");//groupID, itemID, Order, title
        //menu.add(0, v.getId(), 0, "Action 2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getTitle()=="Supprimer"){
            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            this.metVille.remove(this.adapter.getItem(menuInfo.position));
            //this.saveCityPtef(this.metVille);
            this.adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Removed Successfull", Toast.LENGTH_SHORT).show();

        }
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 250 && resultCode == RESULT_OK) {
            this.metVille.add((City) data.getSerializableExtra("City"));
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "City Added", Toast.LENGTH_SHORT).show();
        }
    }

}
