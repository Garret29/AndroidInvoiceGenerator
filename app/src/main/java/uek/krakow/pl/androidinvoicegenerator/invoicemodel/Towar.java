package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "towar")
public class Towar  implements Serializable {
    @Element(name = "liczba_porzadkowa")
    public int id;
    @Element(name = "nazwa")
    public String name;
    @Element(name = "ilosc")
    public String quantity;
    @Element(name = "jednostka")
    public String unit;
    @Element(name = "cena_brutto_jednost")
    public String priceBruttoOfUnit;
    @Element(name = "rabat")
    public String discount;
    @Element(name = "cena_brutto_jednost_po_rabacie")
    public String priceBruttoOfUnitAfterDiscount;
    @Element(name = "brutto")
    public String priceBrutto;
    @Element(name = "stawka_VAT")
    public String vatValue;
    @Element(name = "VAT")
    public String vat;
    @Element(name = "netto")
    public String priceNetto;

    public Towar() {
        name = "";
        quantity = "";
        unit = "";
        priceBruttoOfUnit = "";
        discount = "";
        priceBruttoOfUnitAfterDiscount = "";
        priceBrutto = "";
        vatValue = "";
        vat = "";
        priceNetto = "";
    }
}
