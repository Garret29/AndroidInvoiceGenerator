package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Root(name = "faktura")
public class Faktura implements Serializable{

    @Element(name = "miejscowosc_wystawienia")
    public String invoiceCity;
    @Element(name = "data_wystawienia")
    public String invoiceDate;
    @Element(name = "data_dostawy")
    public String invoiceShippingDate;
    @Element(name = "termin_platnosci")
    public String paymentDate;
    @Element(name = "sposob_platnosci")
    public String paymentMethod;
    @Element(name = "numer")
    public String id;

    @Element(name = "sprzedawca")
    public Sprzedawca sprzedawca;

    @Element(name = "nabywca")
    public Nabywca nabywca;

    @ElementList(name="towary")
    public List<Towar> towary;

    @Element(name = "razem")
    public Razem razem;

    public Faktura() {
        invoiceCity = " ";
        invoiceDate = " ";
        invoiceShippingDate = " ";
        paymentDate = " ";
        paymentMethod = " ";
        id = " ";
        towary = new ArrayList<>();
    }
}
