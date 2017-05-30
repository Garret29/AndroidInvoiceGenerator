package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
    }


    public void toProvider(View view) {
        MyApp app = (MyApp) getApplication();
        Faktura faktura = app.getFaktura();
        //// TODO: 30.05.2017  
        faktura.id =""; //numer
        faktura.invoiceCity=""; //miasto
        faktura.invoiceDate=""; //data wystawienia
        faktura.invoiceShippingDate=""; //data dostawy
        faktura.paymentDate=""; //termin zaplaty
        faktura.paymentMethod=""; //sposób platnosci

        Intent intent = new Intent(this, ProviderFormActivity.class);
        startActivity(intent);
    }
}
