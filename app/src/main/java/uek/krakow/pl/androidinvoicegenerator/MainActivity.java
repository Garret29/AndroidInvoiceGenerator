package uek.krakow.pl.androidinvoicegenerator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

import uek.krakow.pl.androidinvoicegenerator.generator.Invoice;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Invoice> invoicesMap;
    private  ArrayList<String> invoices;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        invoices = new ArrayList<>();
        invoicesMap = new HashMap<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, invoices);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }

    public void add(View view) {
        Intent intent = new Intent(this, FormActivity.class);
        startActivity(intent);
    }

    public void goToPrefs(View view){
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public ArrayList<String> getInvoices() {
        return invoices;
    }


}
