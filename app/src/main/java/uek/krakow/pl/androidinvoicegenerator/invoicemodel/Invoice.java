package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Root(name = "invoice")
public class Invoice implements Serializable{

    @Element(name = "invoice_city")
    public String invoiceCity;
    @Element(name = "invoice_date")
    public String invoiceDate;
    @Element(name = "shipment_date")
    public String invoiceShippingDate;
    @Element(name = "payment_date")
    public String paymentDate;
    @Element(name = "payment_method")
    public String paymentMethod;
    @Element(name = "id")
    public String id;

    @Element(name = "provider")
    public Provider provider;

    @Element(name = "buyer")
    public Buyer buyer;

    @ElementList(name="goods")
    public List<Good> goods;

    @Element(name = "summary")
    public Summary summary;

    public Invoice() {
        invoiceCity = "";
        invoiceDate = "";
        invoiceShippingDate = "";
        paymentDate = "";
        paymentMethod = "";
        id = "";
        goods = new ArrayList<>();
    }
}
