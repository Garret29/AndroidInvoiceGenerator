package uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "towar")
public class Towar {
    @Element(name = "liczba_porzadkowa")
    public String id=" ";
    @Element(name = "nazwa")
    public String name=" ";
    @Element(name = "ilosc")
    public String quantity=" ";
    @Element(name = "jednostka")
    public String unit=" ";
    @Element(name = "cena_brutto_jednost")
    public String priceBruttoOfUnit=" ";
    @Element(name = "rabat")
    public String discount=" ";
    @Element(name = "cena_brutto_jednost_po_rabacie")
    public String priceBruttoOfUnitAfterDiscount=" ";
    @Element(name = "brutto")
    public String priceBrutto=" ";
    @Element(name = "stawka_VAT")
    public String vatValue=" ";
    @Element(name = "VAT")
    public String vat=" ";
    @Element(name = "netto")
    public String priceNetto=" ";
}
