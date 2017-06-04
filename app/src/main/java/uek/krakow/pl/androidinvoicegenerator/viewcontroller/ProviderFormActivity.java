package uek.krakow.pl.androidinvoicegenerator.viewcontroller;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Sprzedawca;
import uek.krakow.pl.androidinvoicegenerator.tasks.GetFromApiTask;
import uek.krakow.pl.androidinvoicegenerator.viewcontroller.interfaces.Updatable;

public class ProviderFormActivity extends AppCompatActivity implements Updatable {
    private EditText ed_NIPDost, ed_nazwaDost, ed_ulicaDost, ed_domDost, ed_lokalDost, ed_miejscowoscDost, ed_kodDost, ed_rachunekDost, ed_bankDost, ed_telefonDost, ed_emailDost;
    String pustePole = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_form);
        getSupportActionBar().setTitle("Krok 2 z 5");

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

    private boolean niePuste(String pole){
        String NIEPUSTE_PATTERN = "^\\S.*$";
        Pattern pattern = Pattern.compile(NIEPUSTE_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean NIP(String pole){
        String NIP_PATTERN = "^[0-9]{10}$";
        Pattern pattern = Pattern.compile(NIP_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean kodPocztowy(String pole){
        String KOD_PATTERN = "^\\d{2}-\\d{3}$";
        Pattern pattern = Pattern.compile(KOD_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean rachunekBankowy(String pole){
        String RACHUNEK_PATTERN = "^\\d{26}$|^\\d{2}\\s\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}\\s\\d{4}$";
        Pattern pattern = Pattern.compile(RACHUNEK_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean email(String pole){
        String EMAIL_PATTERN = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}\\b";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean telefon(String pole){
        String TELEFON_PATTERN = "^(?:\\(?\\+?48)?(?:[-\\.\\(\\)\\s]*(\\d)){9}\\)?$";
        Pattern pattern = Pattern.compile(TELEFON_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }

    public void toBuyer(View view) {
        if(niePuste(ed_NIPDost.getText().toString()) && !NIP(ed_NIPDost.getText().toString())){
            ed_NIPDost.setError("Wymagane 10 cyfr");
        }else if(niePuste(ed_kodDost.getText().toString()) && !kodPocztowy(ed_kodDost.getText().toString())){
            ed_kodDost.setError("Wymagane forma 00-000");
        }else if(niePuste(ed_rachunekDost.getText().toString()) && !rachunekBankowy(ed_rachunekDost.getText().toString())){
            ed_rachunekDost.setError("Wymagane 26 cyfr w postaci CCAAAAAAAABBBBBBBBBBBBBBBB lub CC AAAA AAAA BBBB BBBB BBBB BBBB");
        }else if(niePuste(ed_telefonDost.getText().toString()) && !telefon(ed_telefonDost.getText().toString())){
            ed_telefonDost.setError("Niepoprawny numer telefonu");
        }else if(niePuste(ed_emailDost.getText().toString()) && !email(ed_emailDost.getText().toString())){
            ed_emailDost.setError("Forma niepoprawna! Wymagane forma aaa@bbb.com");
        }else if (!niePuste(ed_NIPDost.getText().toString()) || !niePuste(ed_nazwaDost.getText().toString()) || !niePuste(ed_ulicaDost.getText().toString()) || !niePuste(ed_domDost.getText().toString()) || !niePuste(ed_lokalDost.getText().toString()) || !niePuste(ed_miejscowoscDost.getText().toString()) || !niePuste(ed_kodDost.getText().toString()) || !niePuste(ed_rachunekDost.getText().toString()) || !niePuste(ed_bankDost.getText().toString()) || !niePuste(ed_telefonDost.getText().toString()) || !niePuste(ed_emailDost.getText().toString())){
            if (!niePuste(ed_NIPDost.getText().toString())) {
                ed_NIPDost.setError("Puste pole. Wymagane 10 cyfr");
                pustePole +="NIP,\n";
            }
            if(!niePuste(ed_nazwaDost.getText().toString())){
                ed_nazwaDost.setError("Puste pole");
                pustePole +="Nazwa,\n";
            }
            if (!niePuste(ed_ulicaDost.getText().toString())) {
                ed_ulicaDost.setError("Puste pole");
                pustePole +="Ulica,\n";
            }
            if (!niePuste(ed_domDost.getText().toString())) {
                ed_domDost.setError("Puste pole");
                pustePole +="Numer domu,\n";
            }
            if (!niePuste(ed_lokalDost.getText().toString())) {
                ed_lokalDost.setError("Puste pole");
                pustePole +="nr lokalu,\n";
            }
            if (!niePuste(ed_miejscowoscDost.getText().toString())) {
                ed_miejscowoscDost.setError("Puste pole");
                pustePole +="Miejscowość,\n";
            }
            if (!niePuste(ed_kodDost.getText().toString())) {
                ed_kodDost.setError("Puste pole. Wymagane forma 00-000");
                pustePole +="Kod pocztowy,\n";
            }
            if (!niePuste(ed_rachunekDost.getText().toString())) {
                ed_rachunekDost.setError("Puste pole");
                pustePole +="Nr bankowy,\n";
            }
            if (!niePuste(ed_bankDost.getText().toString())) {
                ed_bankDost.setError("Puste pole");
                pustePole +="Bank,\n";
            }
            if (!niePuste(ed_telefonDost.getText().toString())) {
                ed_telefonDost.setError("Puste pole");
                pustePole +="Nr telefonu,\n";
            }
            if (!niePuste(ed_emailDost.getText().toString())) {
                ed_emailDost.setError("Puste pole");
                pustePole +="Email,\n";
            }
            AlertDialog.Builder a_builder = new AlertDialog.Builder(ProviderFormActivity.this);
            a_builder.setMessage("Nie wszystkie pola zostały uzupełnione:\n"+pustePole+"czy chcesz kontynuować mimo to?")
                    .setCancelable(false)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            przejdzDalej();
                        }
                    })
                    .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            pustePole="";
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Ostrzeżenie");
            alert.show();
        }else{
            przejdzDalej();
        }

    }

    public void przejdzDalej(){
        Faktura faktura = (Faktura) this.getIntent().getSerializableExtra("faktura");

        Sprzedawca sprzedawca = new Sprzedawca();
        sprzedawca.providerApartment = ed_lokalDost.getText().toString();
        sprzedawca.providerBankNumber = ed_rachunekDost.getText().toString();
        sprzedawca.providerCity = ed_miejscowoscDost.getText().toString();
        sprzedawca.providerEmail = ed_emailDost.getText().toString();
        sprzedawca.providerHouse = ed_domDost.getText().toString();
        sprzedawca.providerNIP = ed_NIPDost.getText().toString();
        sprzedawca.providerName = ed_nazwaDost.getText().toString();
        sprzedawca.providerStreet = ed_ulicaDost.getText().toString();
        sprzedawca.providerPostalCode = ed_kodDost.getText().toString();
        sprzedawca.providerPhoneNumber = ed_telefonDost.getText().toString();
        sprzedawca.providerBank = ed_bankDost.getText().toString();


        faktura.sprzedawca = sprzedawca;

        Intent intent = new Intent(this, BuyerFormActivity.class);
        intent.putExtra("faktura", faktura);
        startActivity(intent);

    }

    public void zapisz(){
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
        pe.apply();
        Toast.makeText(this, "Zapisano dane", Toast.LENGTH_SHORT).show();
    }

    public void zapiszDoPamieci(View view) {
        SharedPreferences p = getSharedPreferences("Dostawca", MODE_PRIVATE);
        String lokal = p.getString("lokalDost", "");
        String rachunek = p.getString("rachunekDost", "");
        String miejscowosc = p.getString("miejscowoscDost", "");
        String email = p.getString("emailDost", "");
        String dom = p.getString("domDost", "");
        String NIP = p.getString("NIPDost", "");
        String ulica = p.getString("ulicaDost", "");
        String kod = p.getString("kodDost", "");
        String telefon = p.getString("telefonDost", "");
        String bank = p.getString("bankDost", "");
        String nazwa = p.getString("nazwaDost", "");
        if (!p.getBoolean("zapisano", false)) {
            zapisz();
        } else {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(ProviderFormActivity.this);
            a_builder.setMessage("W pamięci znajdują się następujące dane:\n" +
                    "Nazwa: "+nazwa+"\n" +
                    "Nip: "+NIP+"\n" +
                    "Ulica: "+ulica+"\n" +
                    "Nr budynku: "+dom+"\n" +
                    "Lokal: "+lokal+"\n" +
                    "Miejscowość: "+miejscowosc+"\n" +
                    "Kod pocztowy: "+kod+"\n" +
                    "Nr rachunku: "+rachunek+"\n" +
                    "Bank: "+bank+"\n" +
                    "Nr telefonu: "+telefon+"\n" +
                    "Email: "+email+"\n" +
                    "Czy chcesz je nadpisać?")
                    .setCancelable(false)
                    .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            zapisz();
                        }
                    })
                    .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = a_builder.create();
            alert.setTitle("Ostrzeżenie");
            alert.show();

        }
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

    public void pobierzDaneGUS(View view){
        if(!NIP(ed_NIPDost.getText().toString())){
            ed_NIPDost.setError("Wymagane 10 cyfr");
        }else {
            GetFromApiTask task = new GetFromApiTask(this);
            task.execute(ed_NIPDost.getText().toString());
        }
    }

    @Override
    public void update(JSONObject jsonObject) {
        try {
            String nazwa = jsonObject.getString("krs_podmioty.nazwa");
            String ulica = jsonObject.getString("krs_podmioty.adres_ulica");
            String numerBudynku = jsonObject.getString("krs_podmioty.adres_numer");
            String numerLokalu = jsonObject.getString("krs_podmioty.adres_lokal");
            String miejscowosc = jsonObject.getString("krs_podmioty.adres_miejscowosc");
            String kod = jsonObject.getString("krs_podmioty.adres_kod_pocztowy");
            String mail = jsonObject.getString("krs_podmioty.email");

            if (nazwa != null) {
                this.getEd_nazwaDost().setText(nazwa);
            }
            if (ulica != null) {
                this.getEd_ulicaDost().setText(ulica);
            }
            if (numerBudynku != null) {
                this.getEd_domDost().setText(numerBudynku);
            }
            if (numerLokalu != null) {
                this.getEd_lokalDost().setText(numerLokalu);
            }
            if (miejscowosc != null) {
                this.getEd_miejscowoscDost().setText(miejscowosc);
            }
            if (kod != null) {
                this.getEd_kodDost().setText(kod);
            }
            if (mail != null) {
                this.getEd_emailDost().setText(mail);
            }
        } catch (JSONException e) {
            e.printStackTrace();
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
