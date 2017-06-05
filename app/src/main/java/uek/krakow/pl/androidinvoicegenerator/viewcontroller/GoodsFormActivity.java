package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Towar;

public class GoodsFormActivity extends AppCompatActivity {
    EditText ed_nazwaTowar, ed_iloscTowar, ed_jednostkaTowar, ed_cenaBruttTowar, ed_rabatTowar;
    String stawkaVat, idS;
    double cenaBruttoJednostPoRabacie = 0, brutto = 0, cenaVAT = 0, cenaNETTO = 0, nettoTAX = 0, bruttoTAX = 0, vatTAX = 0, nettoRazemTAX = 0, bruttoRazemTAX = 0, vatRazemTAX = 0;
    static int id = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_form);
        getSupportActionBar().setTitle("Krok 4 z 5");

        Spinner spinner = (Spinner) findViewById(R.id.spinner_stawkaVAT);
        id++;
        idS = Integer.toString(id);
        ed_nazwaTowar = (EditText) findViewById(R.id.ed_nazwaTowar);
        ed_iloscTowar = (EditText) findViewById(R.id.ed_iloscTowar);
        ed_jednostkaTowar = (EditText) findViewById(R.id.ed_jednostkaTowar);
        ed_cenaBruttTowar = (EditText) findViewById(R.id.ed_cenaBruttTowar);
        ed_rabatTowar = (EditText) findViewById(R.id.ed_rabatTowar);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stawkaVat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Log.d("hehe", "creation");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("hehe", "resume");
    }

    public void addMoreGoods(View view) {
        boolean walidacja = true;
        if (TextUtils.isEmpty(ed_nazwaTowar.getText().toString()) || TextUtils.isEmpty(ed_cenaBruttTowar.getText().toString()) || TextUtils.isEmpty(ed_rabatTowar.getText().toString()) || TextUtils.isEmpty(ed_jednostkaTowar.getText().toString()) || TextUtils.isEmpty(ed_iloscTowar.getText().toString())) {
            Toast.makeText(this, "Uzupełnij wszystkie dane", Toast.LENGTH_SHORT).show();
        } else {
            if (!niePuste(ed_nazwaTowar.getText().toString())){
                ed_nazwaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja=false;
            } if (!poPrzecinku(ed_iloscTowar.getText().toString())) {
                ed_iloscTowar.setError("Błędna liczba");
                walidacja=false;
            } if (!niePuste(ed_jednostkaTowar.getText().toString())) {
                ed_jednostkaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja=false;
            } if (!poPrzecinku(ed_cenaBruttTowar.getText().toString())) {
                ed_cenaBruttTowar.setError("Błędna liczba");
                walidacja=false;
            } if (!calkowita(ed_rabatTowar.getText().toString())) {
                ed_rabatTowar.setError("Wymagana liczba całkowita");
                walidacja=false;
            }
            if (walidacja) {
                Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");

                //Obliczenie ceny brutto jedno., netto, vat, brutto dla każdego towaru
                double cenaBruttoJednostPoRabacieI = Math.round((Double.parseDouble(ed_cenaBruttTowar.getText().toString()) * ((100 - Double.parseDouble(ed_rabatTowar.getText().toString())) / 100)) * 100);

                cenaBruttoJednostPoRabacie = cenaBruttoJednostPoRabacieI / 100;

                double bruttoI = Math.round((Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie) * 100);
                brutto = bruttoI / 100;

                double cenaNETTOI = Math.round((brutto / ((100 + Double.parseDouble(stawkaVat)) / 100)) * 100);
                cenaNETTO = cenaNETTOI / 100;

                double cenaVATI = Math.round(bruttoI - cenaNETTOI);
                cenaVAT= cenaVATI/100;

                //Przypisanie danych towaru do pól dokumentu faktury
                Towar towar = new Towar();
                towar.id = Integer.toString(faktura.towary.size()+1);
                towar.name = ed_nazwaTowar.getText().toString();
                towar.quantity = ed_iloscTowar.getText().toString();
                towar.unit = ed_jednostkaTowar.getText().toString();
                towar.priceBruttoOfUnit = ed_cenaBruttTowar.getText().toString();
                towar.discount = ed_rabatTowar.getText().toString();
                towar.vatValue = stawkaVat;
                towar.priceBruttoOfUnitAfterDiscount = Double.toString(cenaBruttoJednostPoRabacie);
                towar.priceBrutto = Double.toString(brutto);
                towar.vat = Double.toString(cenaVAT);
                towar.priceNetto = Double.toString(cenaNETTO);

                //Uzupełnienie tabeli "Razem", pól wspólnych dla wszystkich produktów
                // TODO  tabelka razem podaje miliony monet po przcinku
                if(id ==1){
                    faktura.razem.netto = Double.toString(cenaNETTO);
                    faktura.razem.brutto = Double.toString(brutto);
                    faktura.razem.vat = Double.toString(cenaVAT);
                }else {
                    nettoRazemTAX = Double.parseDouble(faktura.razem.netto);
                    bruttoRazemTAX = Double.parseDouble(faktura.razem.brutto);
                    vatRazemTAX = Double.parseDouble(faktura.razem.vat);
                    faktura.razem.netto = Double.toString(cenaNETTO + nettoRazemTAX);
                    faktura.razem.brutto = Double.toString(brutto + bruttoRazemTAX);
                    faktura.razem.vat = Double.toString(cenaVAT + vatRazemTAX);
                }

                //Uzupełnienie tabeli "Razem" faktury ze względu na stawkę VAT poszczególnych produktów
                switch (Integer.parseInt(stawkaVat)) {
                    case 0:
                        nettoTAX = Double.parseDouble(faktura.razem.tax0.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax0.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax0.VAT);
                        faktura.razem.tax0.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax0.brutto = Double.toString(brutto + bruttoTAX);
                        faktura.razem.tax0.VAT = Double.toString(cenaVAT + vatTAX);
                        break;
                    case 5:
                        nettoTAX = Double.parseDouble(faktura.razem.tax5.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax5.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax5.VAT);
                        faktura.razem.tax5.brutto = Double.toString(brutto + bruttoTAX);
                        faktura.razem.tax5.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax5.VAT += cenaVAT;
                        break;
                    case 8:
                        nettoTAX = Double.parseDouble(faktura.razem.tax8.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax8.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax8.VAT);
                        faktura.razem.tax8.brutto += brutto;
                        faktura.razem.tax8.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax8.VAT += cenaVAT;
                        break;
                    case 23:
                        nettoTAX = Double.parseDouble(faktura.razem.tax23.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax23.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax23.VAT);
                        faktura.razem.tax23.brutto = Double.toString(brutto + bruttoTAX);
                        faktura.razem.tax23.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax23.VAT = Double.toString(cenaVAT + vatTAX);
                        break;
                }


                faktura.towary.add(towar);
                Intent intent = new Intent(this, GoodsFormActivity.class);
                intent.putExtra("faktura", faktura);
                startActivity(intent);
            }
        }
    }

    private boolean poPrzecinku(String pole) {
        String DZIESIETNA_PATTERN = "^\\d+\\.?\\d{0,2}$";
        Pattern pattern = Pattern.compile(DZIESIETNA_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }

    private boolean calkowita(String pole) {
        String CALKOWITA_PATTERN = "^\\d+$";
        Pattern pattern = Pattern.compile(CALKOWITA_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }
    private boolean niePuste(String pole){
        String NIEPUSTE_PATTERN = "^\\S.*$";
        Pattern pattern = Pattern.compile(NIEPUSTE_PATTERN);
        Matcher matcher = pattern.matcher(pole);
        return matcher.matches();
    }

    public void toSummary(View view) {
        boolean walidacja = true;
        if (TextUtils.isEmpty(ed_nazwaTowar.getText().toString()) || TextUtils.isEmpty(ed_cenaBruttTowar.getText().toString()) || TextUtils.isEmpty(ed_rabatTowar.getText().toString()) || TextUtils.isEmpty(ed_jednostkaTowar.getText().toString()) || TextUtils.isEmpty(ed_iloscTowar.getText().toString())) {
            Toast.makeText(this, "Uzupełnij wszystkie dane", Toast.LENGTH_SHORT).show();
        } else {
            if (!niePuste(ed_nazwaTowar.getText().toString())){
                ed_nazwaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja=false;
            } if (!poPrzecinku(ed_iloscTowar.getText().toString())) {
                ed_iloscTowar.setError("Błędna liczba");
                walidacja=false;
            } if (!niePuste(ed_jednostkaTowar.getText().toString())) {
                ed_jednostkaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja=false;
            } if (!poPrzecinku(ed_cenaBruttTowar.getText().toString())) {
                ed_cenaBruttTowar.setError("Błędna liczba");
                walidacja=false;
            } if (!calkowita(ed_rabatTowar.getText().toString())) {
                ed_rabatTowar.setError("Wymagana liczba całkowita");
                walidacja=false;
            }
            if (walidacja)  {
                //Obliczenie ceny brutto jedno., netto, vat, brutto dla każdego towaru
                double cenaBruttoJednostPoRabacieI = Math.round((Double.parseDouble(ed_cenaBruttTowar.getText().toString()) * ((100 - Double.parseDouble(ed_rabatTowar.getText().toString())) / 100)) * 100);
                cenaBruttoJednostPoRabacie = cenaBruttoJednostPoRabacieI / 100;

                double bruttoI = Math.round((Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie) * 100);
                brutto = bruttoI / 100;

                double cenaNETTOI = Math.round((brutto / ((100 + Double.parseDouble(stawkaVat)) / 100)) * 100);
                cenaNETTO = cenaNETTOI / 100;

                double cenaVATI = Math.round(bruttoI - cenaNETTOI);
                cenaVAT= cenaVATI/100;

                //Przypisanie danych towaru do pól dokumentu faktury
                Faktura faktura = (Faktura) getIntent().getSerializableExtra("faktura");
                Towar towar = new Towar();
                towar.id = idS;
                towar.name = ed_nazwaTowar.getText().toString();
                towar.quantity = ed_iloscTowar.getText().toString();
                towar.unit = ed_jednostkaTowar.getText().toString();
                towar.priceBruttoOfUnit = ed_cenaBruttTowar.getText().toString();
                towar.discount = ed_rabatTowar.getText().toString();
                towar.vatValue = stawkaVat;
                towar.priceBruttoOfUnitAfterDiscount = Double.toString(cenaBruttoJednostPoRabacie);
                towar.priceBrutto = Double.toString(brutto);
                towar.vat = Double.toString(cenaVAT);
                towar.priceNetto = Double.toString(cenaNETTO);

                //Uzupełnienie tabeli "Razem", pól wspólnych dla wszystkich produktów
                if(id ==1){
                    faktura.razem.netto = Double.toString(0);
                    faktura.razem.brutto = Double.toString(0);
                    faktura.razem.vat = Double.toString(0);
                }
                nettoRazemTAX = Double.parseDouble(faktura.razem.netto);
                bruttoRazemTAX = Double.parseDouble(faktura.razem.brutto);
                vatRazemTAX = Double.parseDouble(faktura.razem.vat);
                faktura.razem.netto = Double.toString(cenaNETTO + nettoRazemTAX);
                faktura.razem.brutto = Double.toString(brutto + bruttoRazemTAX);
                faktura.razem.vat = Double.toString(cenaVAT + vatRazemTAX);

                //Uzupełnienie tabeli "Razem" faktury ze względu na stawkę VAT poszczególnych produktów
                switch (Integer.parseInt(stawkaVat)) {
                    case 0:
                        nettoTAX = Double.parseDouble(faktura.razem.tax0.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax0.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax0.VAT);
                        faktura.razem.tax0.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax0.brutto = Double.toString(brutto + bruttoTAX);
                        faktura.razem.tax0.VAT = Double.toString(cenaVAT + vatTAX);
                        break;
                    case 5:
                        nettoTAX = Double.parseDouble(faktura.razem.tax5.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax5.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax5.VAT);
                        faktura.razem.tax5.brutto = Double.toString(brutto + bruttoTAX);
                        faktura.razem.tax5.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax5.VAT += cenaVAT;
                        break;
                    case 8:
                        nettoTAX = Double.parseDouble(faktura.razem.tax8.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax8.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax8.VAT);
                        faktura.razem.tax8.brutto += brutto;
                        faktura.razem.tax8.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax8.VAT += cenaVAT;
                        break;
                    case 23:
                        nettoTAX = Double.parseDouble(faktura.razem.tax23.netto);
                        bruttoTAX = Double.parseDouble(faktura.razem.tax23.brutto);
                        vatTAX = Double.parseDouble(faktura.razem.tax23.VAT);
                        faktura.razem.tax23.brutto = Double.toString(brutto + bruttoTAX);
                        faktura.razem.tax23.netto = Double.toString(cenaNETTO + nettoTAX);
                        faktura.razem.tax23.VAT = Double.toString(cenaVAT + vatTAX);
                        break;
                }


                faktura.towary.add(towar);
                Intent intent = new Intent(this, SummaryFormActivity.class);
                intent.putExtra("faktura", faktura);
                Log.d("hehe", faktura.id + "heheheheeh");
                startActivity(intent);
            }
        }
    }
}
