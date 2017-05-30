package uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "w_tym_5")
public class Tax5  implements Serializable {

    @Element(name = "brutto")
    public String brutto=" ";
    @Element(name = "VAT")
    public String VAT=" ";
    @Element(name = "netto")
    public String netto=" ";

}
