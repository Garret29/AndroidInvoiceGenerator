package uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "w_tym_8")
public class Tax8 {

    @Element(name = "brutto")
    public String brutto=" ";
    @Element(name = "VAT")
    public String VAT=" ";
    @Element(name = "netto")
    public String netto=" ";

}
