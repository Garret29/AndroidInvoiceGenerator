package uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "nabywca")
public class Nabywca {
    @Element(name = "nazwa")
    public String buyerName=" ";
    @Element(name = "ulica")
    public String buyerStreet=" ";
    @Element(name = "dom")
    public String buyerHouse=" ";
    @Element(name = "lokal")
    public String buyerAppartment=" ";
    @Element(name = "miasto")
    public String buyerCity=" ";
    @Element(name = "kod")
    public String buyerPostalCode=" ";
}
