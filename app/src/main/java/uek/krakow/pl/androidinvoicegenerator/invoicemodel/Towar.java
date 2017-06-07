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

    public double quantity;
    @Element(name = "jednostka")
    public String unit;
    @Element(name = "cena_brutto_jednost")
    public double priceBruttoOfUnit;
    @Element(name = "rabat")
    public double discount;
    @Element(name = "cena_brutto_jednost_po_rabacie")
    public double priceBruttoOfUnitAfterDiscount;
    @Element(name = "brutto")
    public double priceBrutto;
    @Element(name = "stawka_VAT")
    public String vatValue;
    @Element(name = "VAT")
    public double vat;
    @Element(name = "netto")
    public double priceNetto;

    public Towar() {
        name = "";
        unit = "";
        vatValue = "";    
    }
}
