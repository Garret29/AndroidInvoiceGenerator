package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "nabywca")
public class Nabywca  implements Serializable {
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
