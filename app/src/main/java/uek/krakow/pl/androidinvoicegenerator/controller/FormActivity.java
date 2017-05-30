package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;

public class FormActivity extends AppCompatActivity {
    EditText ed_numerFaktury, ed_miejscowoscWystawienia, date_DataWystawienia, date_dataDostawy, ed_sposobZaplaty, ed_terimnZaplatyDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        ed_numerFaktury = (EditText) findViewById(R.id.ed_numerFaktury);
        ed_miejscowoscWystawienia = (EditText) findViewById(R.id.ed_miejscowoscWystawienia);
        date_DataWystawienia = (EditText) findViewById(R.id.date_DataWystawienia);
        date_dataDostawy = (EditText) findViewById(R.id.date_dataDostawy);
        ed_sposobZaplaty = (EditText) findViewById(R.id.ed_sposobZaplaty);
        ed_terimnZaplatyDo = (EditText) findViewById(R.id.ed_terimnZaplatyDo);
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
        faktura.paymentMethod=""; //sposób platnosci

        faktura.id = ed_numerFaktury.getText().toString();
        faktura.invoiceCity= ed_miejscowoscWystawienia.getText().toString();
        faktura.invoiceDate= date_DataWystawienia.getText().toString();
        faktura.invoiceShippingDate= date_dataDostawy.getText().toString();
        faktura.paymentDate= ed_terimnZaplatyDo.getText().toString();
        faktura.paymentMethod= ed_sposobZaplaty.getText().toString();

        Intent intent = new Intent(this, ProviderFormActivity.class);
        startActivity(intent);
    }
}
