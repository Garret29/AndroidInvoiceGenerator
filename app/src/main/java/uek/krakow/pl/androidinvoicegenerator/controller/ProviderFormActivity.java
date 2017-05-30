package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.controller.BuyerFormActivity;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Sprzedawca;

public class ProviderFormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_form);
    }

    public void toBuyer(View view) {
        MyApp app = (MyApp) getApplication();
        Faktura faktura = app.getFaktura();

        Sprzedawca sprzedawca = new Sprzedawca();
        // TODO: 30.05.2017
        sprzedawca.providerApartment="";
        sprzedawca.providerBankNumber="";
        sprzedawca.providerCity="";
        sprzedawca.providerEmail="";
        sprzedawca.providerHouse="";
        sprzedawca.providerNIP="";
        sprzedawca.providerName="";
        sprzedawca.providerStreet="";
        sprzedawca.providerPostalCode="";
        sprzedawca.providerPhoneNumber="";
        sprzedawca.providerBank="";

        faktura.sprzedawca=sprzedawca;
        Intent intent = new Intent(this, BuyerFormActivity.class);
        startActivity(intent);
    }
}
