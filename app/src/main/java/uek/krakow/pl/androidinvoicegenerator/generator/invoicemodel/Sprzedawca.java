package uek.krakow.pl.androidinvoicegenerator.generator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "sprzedawca")
public class Sprzedawca {
    @Element(name = "nazwa")
    public String providerName = " ";
    @Element(name = "ulica")
    public String providerStreet = " ";
    @Element(name = "dom")
    public String providerHouse = " ";
    @Element(name = "lokal")
    public String providerApartment = " ";
    @Element(name = "miasto")
    public String providerCity = " ";
    @Element(name = "kod")
    public String providerPostalCode = " ";
    @Element(name = "NIP_PESEL")
    public String providerNIP = " ";
    @Element(name = "rachunek")
    public String providerBankNumber = " ";
    @Element(name = "telefon")
    public String providerPhoneNumber = " ";
    @Element(name = "email")
    public String providerEmail = " ";
    @Element(name = "bank")
    public String providerBank = " ";
}
