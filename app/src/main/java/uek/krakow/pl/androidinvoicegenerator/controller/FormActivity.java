package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        Faktura faktura = new Faktura();

        faktura.id = ed_numerFaktury.getText().toString();
        faktura.invoiceCity= ed_miejscowoscWystawienia.getText().toString();
        faktura.invoiceDate= date_DataWystawienia.getText().toString();
        faktura.invoiceShippingDate= date_dataDostawy.getText().toString();
        faktura.paymentDate= ed_terimnZaplatyDo.getText().toString();
        faktura.paymentMethod= ed_sposobZaplaty.getText().toString();

        Intent intent = new Intent(this, ProviderFormActivity.class);
        intent.putExtra("faktura", faktura);
        startActivity(intent);
    }
}
