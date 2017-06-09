package uek.krakow.pl.androidinvoicegenerator.invoicemodel;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

@Root(name = "provider")
public class Provider implements Serializable {
    @Element(name = "name")
    public String providerName;
    @Element(name = "street")
    public String providerStreet;
    @Element(name = "house")
    public String providerHouse;
    @Element(name = "apartment")
    public String providerApartment;
    @Element(name = "city")
    public String providerCity;
    @Element(name = "postal_code")
    public String providerPostalCode;
    @Element(name = "NIP_PESEL")
    public String providerNIP;
    @Element(name = "bank_number")
    public String providerBankNumber;
    @Element(name = "phone")
    public String providerPhoneNumber;
    @Element(name = "email")
    public String providerEmail;
    @Element(name = "bank")
    public String providerBank;

    public Provider() {
        providerName = "";
        providerStreet = "";
        providerHouse = "";
        providerApartment = "";
        providerCity = "";
        providerPostalCode = "";
        providerNIP = "";
        providerBankNumber = "";
        providerPhoneNumber = "";
        providerEmail = "";
        providerBank = "";
    }
}
