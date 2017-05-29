package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.Invoice;

public class MainActivity extends AppCompatActivity {

    private HashMap<String, Invoice> invoicesMap;
    private  ArrayList<String> invoices;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        forwardPort();

        invoices = new ArrayList<>();
        invoicesMap = new HashMap<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, invoices);
        ListView listView = (ListView) findViewById(R.id.list);
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


    private void forwardPort(){
        final JSch jsch = new JSch();
        Session session;
        try {
            session = jsch.getSession("s187825", "v-ie.uek.krakow.pl", 22);

            session.setPassword("qwerty12");

            final Properties config = new Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            session.connect();
            session.setPortForwardingL(5433, "sbazy.uek.krakow.pl", 5432);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

}
