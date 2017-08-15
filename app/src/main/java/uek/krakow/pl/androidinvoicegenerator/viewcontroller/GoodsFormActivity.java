package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.inject.Inject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Invoice;
import uek.krakow.pl.androidinvoicegenerator.invoicemodel.Good;

public class GoodsFormActivity extends AppCompatActivity {
    EditText ed_nazwaTowar, ed_iloscTowar, ed_jednostkaTowar, ed_cenaBruttTowar, ed_rabatTowar;
    String stawkaVat, idS;

    double cenaBruttoJednostPoRabacie = 0, brutto = 0, cenaVAT = 0, cenaNETTO = 0;
    static int id = 0;
    boolean dodajWiecej;

    @Inject
    Invoice invoice;



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

    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
    }

    public void addMoreGoods(View view) {
        boolean walidacja = true;
        if (TextUtils.isEmpty(ed_nazwaTowar.getText().toString()) || TextUtils.isEmpty(ed_cenaBruttTowar.getText().toString()) || TextUtils.isEmpty(ed_rabatTowar.getText().toString()) || TextUtils.isEmpty(ed_jednostkaTowar.getText().toString()) || TextUtils.isEmpty(ed_iloscTowar.getText().toString())) {
            Toast.makeText(this, "Uzupełnij wszystkie dane", Toast.LENGTH_SHORT).show();
        } else {
            if (!niePuste(ed_nazwaTowar.getText().toString())) {
                ed_nazwaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja = false;
            }
            if (!poPrzecinku(ed_iloscTowar.getText().toString())) {
                ed_iloscTowar.setError("Błędna liczba");
                walidacja = false;
            }
            if (!niePuste(ed_jednostkaTowar.getText().toString())) {
                ed_jednostkaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja = false;
            }
            if (!poPrzecinku(ed_cenaBruttTowar.getText().toString())) {
                ed_cenaBruttTowar.setError("Błędna liczba");
                walidacja = false;
            }
            if (!calkowita(ed_rabatTowar.getText().toString()) || Integer.parseInt(ed_rabatTowar.getText().toString())>100) {
                ed_rabatTowar.setError("Wymagana liczba całkowita nie większa niż 100");
                walidacja = false;
            }
            if (walidacja) {

                dodajWiecej=true;
                obliczenia();

            }
        }
    }


    public void obliczenia(){
        //Obliczenie ceny gross jedno., net, vat, gross dla każdego towaru
        double cenaBruttoJednostPoRabacieI = Math.round((Double.parseDouble(ed_cenaBruttTowar.getText().toString()) * ((100 - Double.parseDouble(ed_rabatTowar.getText().toString())) / 100)) * 100);

        cenaBruttoJednostPoRabacie = cenaBruttoJednostPoRabacieI / 100;

        double bruttoI = Math.round((Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie) * 100);
        brutto = bruttoI / 100;

        double cenaNETTOI = Math.round((brutto / ((100 + Double.parseDouble(stawkaVat)) / 100)) * 100);
        cenaNETTO = cenaNETTOI / 100;

        double cenaVATI = Math.round(bruttoI - cenaNETTOI);
        cenaVAT = cenaVATI / 100;

        //Przypisanie danych towaru do pól dokumentu faktury
        Good good = new Good();
        good.id = invoice.goods.size() + 1;
        good.name = ed_nazwaTowar.getText().toString();
        good.quantity = Double.parseDouble(ed_iloscTowar.getText().toString());
        good.unit = ed_jednostkaTowar.getText().toString();
        good.priceBruttoOfUnit = Double.parseDouble(ed_cenaBruttTowar.getText().toString());
        good.discount = Double.parseDouble(ed_rabatTowar.getText().toString());
        good.vatValue = stawkaVat;
        good.priceBruttoOfUnitAfterDiscount = cenaBruttoJednostPoRabacie;
        good.priceBrutto = brutto;
        good.vat = cenaVAT;
        good.netPrice = cenaNETTO;

        //Uzupełnienie tabeli "Summary", pól wspólnych dla wszystkich produktów
        // TODO  tabelka summary podaje miliony monet po przcinku

        double br1=(brutto + invoice.summary.gross)*100;
        double br2=Math.round(br1);
        invoice.summary.gross =br2/100 ;

        double nr1=(cenaNETTO + invoice.summary.net)*100;
        double nr2=Math.round(nr1);
        invoice.summary.net =nr2/100 ;

        double vr1=( cenaVAT + invoice.summary.vat)*100;
        double vr2=Math.round(vr1);
        invoice.summary.vat =vr2/100 ;

        //Uzupełnienie tabeli "Summary" faktury ze względu na stawkę VAT poszczególnych produktów
        switch (Integer.parseInt(stawkaVat)) {
            case 0:
                double b1=(brutto + invoice.summary.tax0.brutto )*100;
                double b2=Math.round(b1);
                invoice.summary.tax0.brutto =b2/100 ;

                double n1=(cenaNETTO + invoice.summary.tax0.netto )*100;
                double n2=Math.round(n1);
                invoice.summary.tax0.netto =n2/100 ;

                double v1=( cenaVAT + invoice.summary.tax0.VAT)*100;
                double v2=Math.round(v1);
                invoice.summary.tax0.VAT =v2/100 ;
                break;
            case 5:
                b1 = (brutto + invoice.summary.tax5.brutto) * 100;
                b2 = Math.round(b1);
                invoice.summary.tax5.brutto =b2/100 ;

                n1 = (cenaNETTO + invoice.summary.tax5.netto) * 100;
                n2 = Math.round(n1);
                invoice.summary.tax5.netto =n2/100 ;

                v1 = (cenaVAT + invoice.summary.tax5.VAT) * 100;
                v2 = Math.round(v1);
                invoice.summary.tax5.VAT =v2/100 ;
                break;
            case 8:
                b1 = (brutto + invoice.summary.tax8.brutto) * 100;
                b2 = Math.round(b1);
                invoice.summary.tax8.brutto =b2/100 ;

                n1 = (cenaNETTO + invoice.summary.tax8.netto) * 100;
                n2 = Math.round(n1);
                invoice.summary.tax8.netto =n2/100 ;

                v1 = (cenaVAT + invoice.summary.tax8.VAT) * 100;
                v2 = Math.round(v1);
                invoice.summary.tax8.VAT =v2/100 ;
                break;
            case 23:
                b1 = (brutto + invoice.summary.tax23.brutto) * 100;
                b2 = Math.round(b1);
                invoice.summary.tax23.brutto =b2/100 ;

                n1 = (cenaNETTO + invoice.summary.tax23.netto) * 100;
                n2 = Math.round(n1);
                invoice.summary.tax23.netto =n2/100 ;

                v1 = (cenaVAT + invoice.summary.tax23.VAT) * 100;
                v2 = Math.round(v1);
                invoice.summary.tax23.VAT =v2/100 ;
                break;
        }
        invoice.goods.add(good);
        if(dodajWiecej) {
            Intent intent = new Intent(this, GoodsFormActivity.class);
            startActivity(intent);
        }else {
            Intent intent = new Intent(this, SummaryFormActivity.class);
            startActivity(intent);
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

    private boolean niePuste(String pole) {
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
            if (!niePuste(ed_nazwaTowar.getText().toString())) {
                ed_nazwaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja = false;
            }
            if (!poPrzecinku(ed_iloscTowar.getText().toString())) {
                ed_iloscTowar.setError("Błędna liczba");
                walidacja = false;
            }
            if (!niePuste(ed_jednostkaTowar.getText().toString())) {
                ed_jednostkaTowar.setError("Pole tekstowe nie może zaczynać się od spacji");
                walidacja = false;
            }
            if (!poPrzecinku(ed_cenaBruttTowar.getText().toString())) {
                ed_cenaBruttTowar.setError("Błędna liczba");
                walidacja = false;
            }
            if (!calkowita(ed_rabatTowar.getText().toString()) || Integer.parseInt(ed_rabatTowar.getText().toString())>100) {
                ed_rabatTowar.setError("Wymagana liczba całkowita nie większa niż 100");
                walidacja = false;
            }
            if (walidacja) {
                obliczenia();
            }
        }
    }
}
