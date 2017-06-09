package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name="summary")
public class Summary implements Serializable {
    @Element(name = "gross")
    public double gross;

    @Element(name = "VAT")
    public double vat;


    @Element(name = "net")
    public double net;

    @Element(name = "gross_words")
    public String grossWords;


    @Element(name = "w_tym_0")
    public Tax0 tax0;


    @Element(name = "w_tym_5")
    public Tax5 tax5;


    @Element(name = "w_tym_8")
    public Tax8 tax8;


    @Element(name = "w_tym_23")
    public Tax23 tax23;

    public Summary() {
        grossWords = "";
    }
}
