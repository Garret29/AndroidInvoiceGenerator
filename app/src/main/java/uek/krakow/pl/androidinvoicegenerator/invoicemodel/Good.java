package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "good")
public class Good implements Serializable {
    @Element(name = "id")
    public int id;
    @Element(name = "name")
    public String name;
    @Element(name = "quantity")
    public double quantity;
    @Element(name = "unit")
    public String unit;
    @Element(name = "gross_price_per_unit")
    public double priceBruttoOfUnit;
    @Element(name = "discount")
    public double discount;
    @Element(name = "gross_price_discount")
    public double priceBruttoOfUnitAfterDiscount;
    @Element(name = "gross_price")
    public double priceBrutto;
    @Element(name = "vat_value")
    public String vatValue;
    @Element(name = "VAT")
    public double vat;
    @Element(name = "net_price")
    public double netPrice;

    public Good() {
        name = "";
        unit = "";
        vatValue = "";    
    }
}
