package edu.upc.eseiaat.pma.countrylistactivity;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static android.R.id.list;

public class CountryListActivity extends AppCompatActivity {
    private ArrayAdapter<String> adapter;
    private ArrayList<String> country_list;
    // Un objeto tipo tabla, es un contenedor secuencial... Puedo fer todo lo tipico

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);


        String[] countries = getResources().getStringArray(R.array.countries);

        country_list = new ArrayList<>(Arrays.asList(countries  ));

        adapter = new ArrayAdapter<>(this , android.R.layout.simple_list_item_1, country_list);


        ListView list = (ListView) findViewById(R.id.countrylist);
        // listas --> cuando la miras por la pantalla hay barios elementos
        // Los items de la lista los recicla tipo escaleras mecanicas
        // todo ListView TIENEN UN ADAPTADOR
        list.setAdapter(adapter);
        // el click
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View item, int pos, long id) {

                Toast.makeText(CountryListActivity.this, String.format("Has escogido = %s ",
                        country_list.get(pos)),
                        Toast.LENGTH_SHORT).show();
            }
        });

        // borrar

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CountryListActivity.this);
                builder.setTitle(R.string.confirmation);
                String sms = getResources().getString(R.string.Confim_sms);
                builder.setMessage(sms + " = " + country_list.get(position) + "!!!!");
                builder.setPositiveButton(R.string.Erase, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        country_list.remove(position);
                        adapter.notifyDataSetChanged(); // avisanos que han cambiado los datos
                    }
                });


                builder.setNegativeButton(android.R.string.cancel, null);
                builder.create().show();
                return true; // no ocurran los dos solo uno

            }
        });

    }
}
