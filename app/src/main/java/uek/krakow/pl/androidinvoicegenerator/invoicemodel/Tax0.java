package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "w_tym_0")
public class Tax0  implements Serializable {

    @Element(name = "brutto")
    public String brutto;
    @Element(name = "VAT")
    public String VAT;
    @Element(name = "netto")
    public String netto;

    public Tax0() {
        brutto = " ";
        VAT = " ";
        netto = " ";
    }
}
