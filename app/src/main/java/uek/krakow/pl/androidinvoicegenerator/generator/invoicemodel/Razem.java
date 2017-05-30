package uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="razem")
public class Razem {
    @Element(name = "brutto")
    public String brutto=" ";

    @Element(name = "VAT")
    public String vat=" ";


    @Element(name = "netto")
    public String netto=" ";


    @Element(name = "brutto_slownie")
    public String bruttoWords=" ";


    @Element(name = "w_tym_0")
    public Tax0 tax0;


    @Element(name = "w_tym_5")
    public Tax5 tax5;


    @Element(name = "w_tym_8")
    public Tax8 tax8;


    @Element(name = "w_tym_23")
    public Tax23 tax23;
}
