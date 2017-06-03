package uek.krakow.pl.androidinvoicegenerator.viewcontroller;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import uek.krakow.pl.androidinvoicegenerator.R;
import uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel.Faktura;

public class FormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    EditText ed_numerFaktury, ed_miejscowoscWystawienia, ed_sposobZaplaty;
    TextView date_DataWystawienia, date_dataDostawy, date_terimnZaplatyDo;
    String terimnZaplatyDo = "Ustaw datę", dataDostawy = "Ustaw datę", dataWystawienia = "Ustaw datę";
    int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        getSupportActionBar().setTitle("Krok 1 z 5");

        ed_numerFaktury = (EditText) findViewById(R.id.ed_numerFaktury);
        ed_miejscowoscWystawienia = (EditText) findViewById(R.id.ed_miejscowoscWystawienia);
        ed_sposobZaplaty = (EditText) findViewById(R.id.ed_sposobZaplaty);
        date_DataWystawienia = (TextView) findViewById(R.id.date_dataWystawienia);
        date_dataDostawy = (TextView) findViewById(R.id.date_dataDostawy);
        date_terimnZaplatyDo = (TextView) findViewById(R.id.date_terimnZaplatyDo);

    }

    private void pustyNumer() {
        AlertDialog alertDialog = new AlertDialog.Builder(FormActivity.this).create();
        alertDialog.setTitle("Ostrzeżenie");
        alertDialog.setMessage("Proszę podać numer faktury");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void setDataWystawienia(View v) {
        id = 1;
        DialogFragment newFragment1 = new DatePickerFragment();
        newFragment1.show(getSupportFragmentManager(), "datePicker");
    }

    public void setDataDostawy(View v) {
        id = 2;
        DialogFragment newFragment2 = new DatePickerFragment();
        newFragment2.show(getSupportFragmentManager(), "datePicker");
    }

    public void setTerimnZaplaty(View v) {
        id = 3;
        DialogFragment newFragment3 = new DatePickerFragment();
        newFragment3.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DatePickerFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),
                    (DatePickerDialog.OnDateSetListener)
                            getActivity(), year, month, day);
        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (id == 1) {
            dataWystawienia = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
            date_DataWystawienia.setText(dataWystawienia);
        } else if (id == 2) {
            dataDostawy = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
            date_dataDostawy.setText(dataDostawy);
        } else {
            terimnZaplatyDo = Integer.toString(dayOfMonth) + "/" + Integer.toString(month + 1) + "/" + Integer.toString(year);
            date_terimnZaplatyDo.setText(terimnZaplatyDo);
        }

    }


    public void toProvider(View view) {
        if (TextUtils.isEmpty(ed_numerFaktury.getText().toString()) || ed_numerFaktury.getText().toString().equals(" ")) {
            pustyNumer();
        } else {
            Faktura faktura = new Faktura();
            faktura.id = ed_numerFaktury.getText().toString();
            faktura.invoiceCity = ed_miejscowoscWystawienia.getText().toString();
            faktura.invoiceDate = dataWystawienia;
            faktura.invoiceShippingDate = dataDostawy;
            faktura.paymentDate = terimnZaplatyDo;
            faktura.paymentMethod = ed_sposobZaplaty.getText().toString();

            Intent intent = new Intent(this, ProviderFormActivity.class);
            intent.putExtra("faktura", faktura);
            startActivity(intent);
        }
    }


}

