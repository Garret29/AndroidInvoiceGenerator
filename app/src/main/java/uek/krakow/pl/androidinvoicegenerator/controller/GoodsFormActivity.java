package uek.krakow.pl.androidinvoicegenerator.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.itextpdf.awt.geom.CubicCurve2D;
import com.j256.ormlite.stmt.query.In;

import uek.krakow.pl.androidinvoicegenerator.MyApp;
import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Towar;

public class GoodsFormActivity extends AppCompatActivity {
    EditText ed_nazwaTowar, ed_iloscTowar, ed_jednostkaTowar, ed_cenaBruttTowar, ed_rabatTowar;
    String stawkaVat, idS;
    double cenaBruttoJednostPoRabacie, brutto, cenaVAT, cenaNETTO;
    static int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_form);

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
                Toast.makeText(GoodsFormActivity.this, parent.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                stawkaVat = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void addMoreGoods(View view) {
        MyApp app = (MyApp) getApplication();
        Faktura faktura = app.getFaktura();

        Towar towar = new Towar();
        towar.id = idS;
        towar.name = ed_nazwaTowar.getText().toString();
        towar.quantity = ed_iloscTowar.getText().toString();
        towar.unit = ed_jednostkaTowar.getText().toString();
        towar.priceBruttoOfUnit = ed_cenaBruttTowar.getText().toString();
        towar.discount = ed_rabatTowar.getText().toString();
        towar.vatValue = stawkaVat;

        cenaBruttoJednostPoRabacie = Double.parseDouble(ed_cenaBruttTowar.getText().toString()) * ((100 - Double.parseDouble(ed_rabatTowar.getText().toString())) / 100);
        brutto = Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie;
        cenaVAT = brutto * (Double.parseDouble(stawkaVat) / 100);
        cenaNETTO = brutto - cenaVAT;

        towar.priceBruttoOfUnitAfterDiscount = Double.toString(cenaBruttoJednostPoRabacie);
        towar.priceBrutto = Double.toString(brutto);
        towar.vat = Double.toString(cenaVAT);
        towar.priceNetto = Double.toString(cenaNETTO);

        faktura.towary.add(towar);
        Intent intent = new Intent(this, GoodsFormActivity.class);
        startActivity(intent);
    }

    public void toSummary(View view) {
        MyApp app = (MyApp) getApplication();
        Faktura faktura = app.getFaktura();

        Towar towar = new Towar();
        towar.id = idS;
        towar.name = ed_nazwaTowar.getText().toString();
        towar.quantity = ed_iloscTowar.getText().toString();
        towar.unit = ed_jednostkaTowar.getText().toString();
        towar.priceBruttoOfUnit = ed_cenaBruttTowar.getText().toString();
        towar.discount = ed_rabatTowar.getText().toString();
        towar.vatValue = stawkaVat;

        cenaBruttoJednostPoRabacie = Double.parseDouble(ed_cenaBruttTowar.getText().toString()) * ((100 - Double.parseDouble(ed_rabatTowar.getText().toString())) / 100);
        brutto = Double.parseDouble(ed_iloscTowar.getText().toString()) * Double.parseDouble(ed_cenaBruttTowar.getText().toString());
        if (Integer.parseInt(stawkaVat) != 0) {
            cenaVAT = (Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie) * (Double.parseDouble(stawkaVat) / 100);
        } else {
            cenaVAT = Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie;
        }

        cenaNETTO = Double.parseDouble(ed_iloscTowar.getText().toString()) * cenaBruttoJednostPoRabacie - cenaVAT;

        towar.priceBruttoOfUnitAfterDiscount = Double.toString(cenaBruttoJednostPoRabacie);
        towar.priceBrutto = Double.toString(brutto);
        towar.vat = Double.toString(cenaVAT);
        towar.priceNetto = Double.toString(cenaNETTO);

        faktura.towary.add(towar);
        Intent intent = new Intent(this, SummaryFormActivity.class);
        startActivity(intent);
    }
}
