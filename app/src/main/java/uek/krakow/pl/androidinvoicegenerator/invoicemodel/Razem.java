package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name="razem")
public class Razem implements Serializable {
    @Element(name = "brutto")
    public double brutto;

    @Element(name = "VAT")
    public double vat;


    @Element(name = "netto")
    public double netto;


    @Element(name = "brutto_slownie")
    public String bruttoWords;


    @Element(name = "w_tym_0")
    public Tax0 tax0;


    @Element(name = "w_tym_5")
    public Tax5 tax5;


    @Element(name = "w_tym_8")
    public Tax8 tax8;


    @Element(name = "w_tym_23")
    public Tax23 tax23;

    public Razem() {
        bruttoWords = "";
    }
}
