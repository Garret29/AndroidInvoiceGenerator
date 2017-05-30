package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Nabywca;

public class BuyerFormActivity extends AppCompatActivity {
    EditText ed_nazwaNabyw, ed_ulicaNabyw, ed_domNabyw, ed_lokalNabyw, ed_miejscowoscNabyw, ed_kodNabyw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_form);

        ed_nazwaNabyw = (EditText) findViewById(R.id.ed_nazwaNabyw);
        ed_ulicaNabyw = (EditText) findViewById(R.id.ed_ulicaNabyw);
        ed_domNabyw = (EditText) findViewById(R.id.ed_domNabyw);
        ed_lokalNabyw = (EditText) findViewById(R.id.ed_lokalNabyw);
        ed_miejscowoscNabyw = (EditText) findViewById(R.id.ed_miejscowoscNabyw);
        ed_kodNabyw = (EditText) findViewById(R.id.ed_kodNabyw);

    }

    public void toGoods(View view) {
        MyApp myApp = (MyApp) getApplication();
        Faktura faktura = myApp.getFaktura();
        Nabywca nabywca = new Nabywca();

        nabywca.buyerAppartment=ed_lokalNabyw.getText().toString();
        nabywca.buyerCity=ed_miejscowoscNabyw.getText().toString();
        nabywca.buyerHouse=ed_domNabyw.getText().toString();
        nabywca.buyerName=ed_nazwaNabyw.getText().toString();
        nabywca.buyerPostalCode=ed_kodNabyw.getText().toString();
        nabywca.buyerStreet=ed_ulicaNabyw.getText().toString();

        faktura.nabywca=nabywca;
        Intent intent = new Intent(this, GoodsFormActivity.class);
        startActivity(intent);
    }
}
