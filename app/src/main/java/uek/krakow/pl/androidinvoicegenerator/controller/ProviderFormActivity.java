package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Sprzedawca;

public class ProviderFormActivity extends AppCompatActivity {
    private EditText ed_NIPDost, ed_nazwaDost, ed_ulicaDost, ed_domDost, ed_lokalDost, ed_miejscowoscDost, ed_kodDost, ed_rachunekDost, ed_bankDost, ed_telefonDost, ed_emailDost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_form);
        getSupportActionBar().setTitle("Sprzedawca");

        ed_NIPDost = (EditText) findViewById(R.id.ed_NIPDost);
        ed_nazwaDost = (EditText) findViewById(R.id.ed_nazwaDost);
        ed_ulicaDost = (EditText) findViewById(R.id.ed_ulicaDost);
        ed_domDost = (EditText) findViewById(R.id.ed_domDost);
        ed_lokalDost = (EditText) findViewById(R.id.ed_lokalDost);
        ed_miejscowoscDost = (EditText) findViewById(R.id.ed_miejscowoscDost);
        ed_kodDost = (EditText) findViewById(R.id.ed_kodDost);
        ed_rachunekDost = (EditText) findViewById(R.id.ed_rachunekDost);
        ed_bankDost = (EditText) findViewById(R.id.ed_bankDost);
        ed_telefonDost = (EditText) findViewById(R.id.ed_telefonDost);
        ed_emailDost = (EditText) findViewById(R.id.ed_emailDost);


    }

    public void toBuyer(View view) {
        Faktura faktura = (Faktura) this.getIntent().getSerializableExtra("faktura");

        Sprzedawca sprzedawca = new Sprzedawca();
        sprzedawca.providerApartment=ed_lokalDost.getText().toString();
        sprzedawca.providerBankNumber=ed_rachunekDost.getText().toString();
        sprzedawca.providerCity=ed_miejscowoscDost.getText().toString();
        sprzedawca.providerEmail=ed_emailDost.getText().toString();
        sprzedawca.providerHouse=ed_domDost.getText().toString();
        sprzedawca.providerNIP=ed_NIPDost.getText().toString();
        sprzedawca.providerName=ed_nazwaDost.getText().toString();
        sprzedawca.providerStreet=ed_ulicaDost.getText().toString();
        sprzedawca.providerPostalCode=ed_kodDost.getText().toString();
        sprzedawca.providerPhoneNumber=ed_telefonDost.getText().toString();
        sprzedawca.providerBank=ed_bankDost.getText().toString();


        faktura.sprzedawca=sprzedawca;

        Intent intent = new Intent(this, BuyerFormActivity.class);
        intent.putExtra("faktura", faktura);
        startActivity(intent);
    }

    public void zapiszDoPamieci(View view) {
        SharedPreferences p = getSharedPreferences("Dostawca", MODE_PRIVATE);
        SharedPreferences.Editor pe = p.edit();

        pe.putBoolean("zapisano", true);
        pe.putString("lokalDost", ed_lokalDost.getText().toString());
        pe.putString("rachunekDost", ed_rachunekDost.getText().toString());
        pe.putString("miejscowoscDost", ed_miejscowoscDost.getText().toString());
        pe.putString("emailDost", ed_emailDost.getText().toString());
        pe.putString("domDost", ed_domDost.getText().toString());
        pe.putString("NIPDost", ed_NIPDost.getText().toString());
        pe.putString("nazwaDost", ed_nazwaDost.getText().toString());
        pe.putString("ulicaDost", ed_ulicaDost.getText().toString());
        pe.putString("kodDost", ed_kodDost.getText().toString());
        pe.putString("telefonDost", ed_telefonDost.getText().toString());
        pe.putString("bankDost", ed_bankDost.getText().toString());
        pe.commit();

        Toast.makeText(this, "Zapisano dane", Toast.LENGTH_SHORT).show();



    }

    public void wczytajZPamieci(View view) {
        SharedPreferences p = getSharedPreferences("Dostawca", MODE_PRIVATE);
        if (p.getBoolean("zapisano", false)){
            ed_lokalDost.setText(p.getString("lokalDost", ""));
            ed_rachunekDost.setText(p.getString("rachunekDost", ""));
            ed_miejscowoscDost.setText(p.getString("miejscowoscDost", ""));
            ed_emailDost.setText(p.getString("emailDost", ""));
            ed_domDost.setText(p.getString("domDost", ""));
            ed_NIPDost.setText(p.getString("NIPDost", ""));
            ed_ulicaDost.setText(p.getString("ulicaDost", ""));
            ed_kodDost.setText(p.getString("kodDost", ""));
            ed_telefonDost.setText(p.getString("telefonDost", ""));
            ed_bankDost.setText(p.getString("bankDost", ""));
            ed_nazwaDost.setText(p.getString("nazwaDost", ""));
            Toast.makeText(this, "Wczytano dane", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Brak zapisanych danych!", Toast.LENGTH_SHORT).show();
        }

    }

    public EditText getEd_NIPDost() {
        return ed_NIPDost;
    }

    public void setEd_NIPDost(EditText ed_NIPDost) {
        this.ed_NIPDost = ed_NIPDost;
    }

    public EditText getEd_nazwaDost() {
        return ed_nazwaDost;
    }

    public void setEd_nazwaDost(EditText ed_nazwaDost) {
        this.ed_nazwaDost = ed_nazwaDost;
    }

    public EditText getEd_ulicaDost() {
        return ed_ulicaDost;
    }

    public void setEd_ulicaDost(EditText ed_ulicaDost) {
        this.ed_ulicaDost = ed_ulicaDost;
    }

    public EditText getEd_domDost() {
        return ed_domDost;
    }

    public void setEd_domDost(EditText ed_domDost) {
        this.ed_domDost = ed_domDost;
    }

    public EditText getEd_lokalDost() {
        return ed_lokalDost;
    }

    public void setEd_lokalDost(EditText ed_lokalDost) {
        this.ed_lokalDost = ed_lokalDost;
    }

    public EditText getEd_miejscowoscDost() {
        return ed_miejscowoscDost;
    }

    public void setEd_miejscowoscDost(EditText ed_miejscowoscDost) {
        this.ed_miejscowoscDost = ed_miejscowoscDost;
    }

    public EditText getEd_kodDost() {
        return ed_kodDost;
    }

    public void setEd_kodDost(EditText ed_kodDost) {
        this.ed_kodDost = ed_kodDost;
    }

    public EditText getEd_rachunekDost() {
        return ed_rachunekDost;
    }

    public void setEd_rachunekDost(EditText ed_rachunekDost) {
        this.ed_rachunekDost = ed_rachunekDost;
    }

    public EditText getEd_bankDost() {
        return ed_bankDost;
    }

    public void setEd_bankDost(EditText ed_bankDost) {
        this.ed_bankDost = ed_bankDost;
    }

    public EditText getEd_telefonDost() {
        return ed_telefonDost;
    }

    public void setEd_telefonDost(EditText ed_telefonDost) {
        this.ed_telefonDost = ed_telefonDost;
    }

    public EditText getEd_emailDost() {
        return ed_emailDost;
    }

    public void setEd_emailDost(EditText ed_emailDost) {
        this.ed_emailDost = ed_emailDost;
    }



}
