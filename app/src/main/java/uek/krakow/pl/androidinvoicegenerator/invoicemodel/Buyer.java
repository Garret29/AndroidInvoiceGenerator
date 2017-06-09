package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "buyer")
public class Buyer implements Serializable {
    @Element(name = "name")
    public String buyerName;
    @Element(name = "street")
    public String buyerStreet;
    @Element(name = "house")
    public String buyerHouse;
    @Element(name = "apartment")
    public String buyerAppartment;
    @Element(name = "city")
    public String buyerCity;
    @Element(name = "postal_code")
    public String buyerPostalCode;

    public Buyer() {
        buyerName = "";
        buyerStreet = "";
        buyerHouse = "";
        buyerAppartment = "";
        buyerCity = "";
        buyerPostalCode = "";
    }
}
