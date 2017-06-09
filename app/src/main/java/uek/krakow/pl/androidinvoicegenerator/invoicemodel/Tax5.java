package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "tax_5")
public class Tax5  implements Serializable {

    @Element(name = "gross")
    public double brutto;
    @Element(name = "VAT")
    public double VAT;
    @Element(name = "net")
    public double netto;

    public Tax5() {
    
    }
}
