package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Nabywca;

public class BuyerFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyer_form);
    }

    public void toGoods(View view) {
        MyApp myApp = (MyApp) getApplication();
        Faktura faktura = myApp.getFaktura();
        Nabywca nabywca = new Nabywca();
        //// TODO: 30.05.2017
        nabywca.buyerAppartment="";
        nabywca.buyerCity="";
        nabywca.buyerHouse="";
        nabywca.buyerName="";
        nabywca.buyerPostalCode="";
        nabywca.buyerStreet="";

        faktura.nabywca=nabywca;
        Intent intent = new Intent(this, GoodsFormActivity.class);
        startActivity(intent);
    }
}
