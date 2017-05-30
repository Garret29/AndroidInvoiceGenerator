package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.j256.ormlite.stmt.query.In;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Towar;

public class GoodsFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_form);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_stawkaVAT);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(GoodsFormActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addMoreGoods(View view){
        MyApp app = (MyApp) getApplication();
        Faktura faktura = app.getFaktura();
        // TODO: 30.05.2017  
        Towar towar = new Towar();
        towar.id="";
        //itd.


        faktura.towary.add(towar);
        Intent intent = new Intent(this, GoodsFormActivity.class);
        startActivity(intent);
    }

    public void toSummary(View view) {
        MyApp app = (MyApp) getApplication();
        Faktura faktura = app.getFaktura();
        //// TODO: 30.05.2017  
        Towar towar = new Towar();
        towar.id="";
        //itd.

        faktura.towary.add(towar);
        Intent intent = new Intent(this, SummaryFormActivity.class);
        startActivity(intent);
    }
}
