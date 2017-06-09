<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    version="1.0">
    <xsl:output
        encoding='UTF-8'
        indent='yes'
        method='html'
        version='1.0' />
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8" />
            </head>
            <body style="font-family:Roboto">
                <xsl:for-each select="invoice">
                    <p align="right">
                        <xsl:if test="invoice_city !='' ">
                            <xsl:value-of select="invoice_city" />
                        </xsl:if>
                        <xsl:if test="not(invoice_city)">Miejscowość:___________</xsl:if>
                        <xsl:if test="invoice_city = ''">Miejscowość:___________</xsl:if>
                        , data
                        wystawienia:

                        <xsl:if test="invoice_date !='' ">
                            <xsl:value-of select="invoice_date" />
                        </xsl:if>
                        <xsl:if test="not(invoice_date)">__________</xsl:if>
                        <xsl:if test="invoice_date = ''">__________</xsl:if>

                    </p>
                    <p align="right">Data dokonania/zakończenia dostawy towarów:
                        <xsl:if test="shipment_date !='' ">
                            <xsl:value-of select="shipment_date" />
                        </xsl:if>
                        <xsl:if test="not(shipment_date)">__________</xsl:if>
                        <xsl:if test="shipment_date = ''">__________</xsl:if>

                    </p>
                </xsl:for-each>
                <b>Sprzedawca:</b>

                <br />
                <xsl:for-each select="invoice/provider">
                    <xsl:if test="name !='' ">
                        <xsl:value-of select="name" />
                    </xsl:if>
                    <xsl:if test="not(name)">Nazwa:__________</xsl:if>
                    <xsl:if test="name = ''">Nazwa:__________</xsl:if>
                    <br />

                    Adres:
                    <br />
                    <xsl:if test="street !='' ">
                        <xsl:value-of select="street" />
                    </xsl:if>
                    <xsl:if test="not(street)">Ul.:___________</xsl:if>
                    <xsl:if test="street = ''">Ul.:___________</xsl:if>
                    ,
                    <xsl:if test="house !='' ">
                        <xsl:value-of select="house" />
                    </xsl:if>
                    <xsl:if test="not(house)">dom:______</xsl:if>
                    <xsl:if test="house = ''">dom:______</xsl:if>
                    , lok.
                    <xsl:if test="apartment !='' ">
                        <xsl:value-of select="apartment" />
                    </xsl:if>
                    <xsl:if test="not(apartment)">________</xsl:if>
                    <xsl:if test="apartment = ''">________</xsl:if>
                    <br />
                    <xsl:if test="city !='' ">
                        <xsl:value-of select="city" />;
                    </xsl:if>
                    <xsl:if test="not(city)">Miejscowość:____________</xsl:if>
                    <xsl:if test="city = ''">Miejscowość:____________</xsl:if>

                    <xsl:if test="postal_code !='' ">
                        <xsl:value-of select="postal_code" />
                    </xsl:if>
                    <xsl:if test="not(postal_code)">, kod pocztowy:________</xsl:if>
                    <xsl:if test="postal_code = ''">, kod pocztowy:________</xsl:if>
                    <br />

                    NIP:
                    <xsl:if test="NIP_PESEL !='' ">
                        <xsl:value-of select="NIP_PESEL" />
                    </xsl:if>
                    <xsl:if test="not(NIP_PESEL)">______________</xsl:if>
                    <xsl:if test="NIP_PESEL = ''">______________</xsl:if>
                    <br />

                    <xsl:if test="bank_number !='' ">
                        Rachunek:
                        <xsl:value-of select="bank_number" />
                    </xsl:if>
                    <xsl:if test="not(bank_number)">Nr. rachunku:___________________</xsl:if>
                    <xsl:if test="bank_number = ''">Nr. rachunku:___________________</xsl:if>
                    ,

                    <xsl:if test="bank !='' ">
                        <xsl:value-of select="bank" />
                    </xsl:if>
                    <xsl:if test="not(bank)">bank:__________</xsl:if>
                    <xsl:if test="bank = ''">bank:__________</xsl:if>
                    <br />

                    Telefon:
                    <xsl:if test="phone !='' ">
                        <xsl:value-of select="phone" />
                    </xsl:if>
                    <xsl:if test="not(phone)">_____________</xsl:if>
                    <xsl:if test="phone = ''">_____________</xsl:if>
                    <br />
                    Email:
                    <xsl:if test="email !='' ">
                        <xsl:value-of select="email" />
                    </xsl:if>
                    <xsl:if test="not(email)">___________________</xsl:if>
                    <xsl:if test="email = ''">___________________</xsl:if>
                    <br />

                </xsl:for-each>
                <br />
                <br />
                <xsl:for-each select="invoice/buyer">
                    <b>Nabywca:</b>
                    <br />
                    <xsl:if test="name !='' ">
                        <xsl:value-of select="name" />
                    </xsl:if>
                    <xsl:if test="not(name)">Nazwa:________________</xsl:if>
                    <xsl:if test="name = ''">Nazwa:________________</xsl:if>
                    <br />
                    <xsl:if test="street !='' ">
                        Adres:
                        <xsl:value-of select="street" />
                    </xsl:if>
                    <xsl:if test="not(street)">Ulica:____________</xsl:if>
                    <xsl:if test="street = ''">Ulica:____________</xsl:if>
                    ,
                    <xsl:if test="house !='' ">
                        <xsl:value-of select="house" />
                    </xsl:if>
                    <xsl:if test="not(house)">house:______</xsl:if>
                    <xsl:if test="house = ''">house:______</xsl:if>
                    , lok.
                    <xsl:if test="apartment !='' ">
                        <xsl:value-of select="apartment" />
                    </xsl:if>
                    <xsl:if test="not(apartment)">________</xsl:if>
                    <xsl:if test="apartment = ''">________</xsl:if>
                    <br />
                    <xsl:if test="city !='' ">
                        <xsl:value-of select="city" />
                    </xsl:if>
                    <xsl:if test="not(city)">Miejscowość:___________</xsl:if>
                    <xsl:if test="city = ''">Miejscowość:___________</xsl:if>
                    ,
                    <xsl:if test="postal_code !='' ">
                        <xsl:value-of select="postal_code" />
                    </xsl:if>
                    <xsl:if test="not(postal_code)">postal_code pocztowy:________</xsl:if>
                    <xsl:if test="postal_code = ''">postal_code pocztowy:________</xsl:if>
                    <br />
                </xsl:for-each>
                <xsl:for-each select="invoice">
                    <h2>
                        <p align="center">Faktura VAT
                            <xsl:if test="id !='' ">
                                <xsl:value-of select="id" />
                            </xsl:if>
                            <xsl:if test="not(id)">_______________</xsl:if>
                            <xsl:if test="id = ''">_______________</xsl:if>
                        </p>
                    </h2>
                </xsl:for-each>
                <table
                    align="center"
                    border="3"
                    width="400">
                    <tr>
                        <th align="center">L.p</th>
                        <th align="center">Nazwa towaru/usługi</th>
                        <th align="center">Ilość</th>
                        <th align="center">J.m.</th>
                        <th align="center">Cena jedn.
                            <br />
                            brutto
                            <br />
                            [zł]
                        </th>
                        <th align="center">Rabat
                            <br />
                            [%]
                        </th>
                        <th align="center">Cena jedn.
                            <br />
                            brutto po rabacie
                            <br />
                            [zł]
                        </th>
                        <th align="center">Wartość brutto
                            <br />
                            [zł]
                        </th>
                        <th align="center">Stawka VAT
                            <br />
                            [%]
                        </th>
                        <th align="center">Wartość VAT
                            <br />
                            [zł]
                        </th>
                        <th align="center">Wartość
                            <br />
                            netto
                            <br />
                            [zł]
                        </th>
                    </tr>
                    <xsl:for-each select="invoice/goods/good">
                        <tr>
                            <td align="right">
                                <xsl:value-of select="id" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="name" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="quantity" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="unit" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="gross_price_per_unit" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="rabat" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="gross_price_discount" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="gross_price" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="vat_value" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="VAT" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="net_price" />
                            </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <br />
                <br />
                <table border="3">
                    <tr>
                        <td>
                            <table>
                                <tr>
                                    <th align="left">Wartości obliczone z cen bez podatku [zł]</th>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table
                                align="center"
                                border="3">
                                <tr>
                                    <th />
                                    <td align="center">Bez podatku
                                        <br />
                                        [zł]
                                    </td>
                                    <td align="center">VAT<br />[zł]
                                    </td>
                                    <td align="center">Stawka
                                        <br />
                                        VAT
                                    </td>
                                    <td align="center">Z podatkiem
                                        <br />
                                        [zł]
                                    </td>
                                </tr>

                                <tr>
                                    <xsl:for-each select="invoice/summary">
                                        <td>Razem:</td>
                                        <td align="right">
                                            <xsl:value-of select="net" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="gross" />
                                        </td>
                                    </xsl:for-each>
                                </tr>

                                <tr>
                                    <xsl:for-each select="invoice/summary/tax_0">
                                        <td>W tym:</td>
                                        <td align="right">
                                            <xsl:value-of select="net" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">0%</td>
                                        <td align="right">
                                            <xsl:value-of select="gross" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="invoice/summary/tax_5">
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="net" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">5%</td>
                                        <td align="right">
                                            <xsl:value-of select="gross" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="invoice/summary/tax_8">
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="net" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">8%</td>
                                        <td align="right">
                                            <xsl:value-of select="gross" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="invoice/summary/tax_23">
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="net" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">23%</td>
                                        <td align="right">
                                            <xsl:value-of select="gross" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <xsl:for-each select="invoice/summary">
                    Należność ogółem:<xsl:value-of select="gross" />zł
                    <br />
                    Należność ogółem słownie:
                    <xsl:if test="gross_words !='' ">
                        <xsl:value-of select="gross_words" />
                    </xsl:if>
                    <xsl:if test="not(gross_words)">______________________________________</xsl:if>
                    <xsl:if test="gross_words = ''">______________________________________</xsl:if>
                </xsl:for-each>
                <br />
                <br />
                <xsl:for-each select="invoice">
                    Sposób zapłaty:
                    <xsl:if test="payment_method !='' ">
                        <xsl:value-of select="payment_method" />
                    </xsl:if>
                    <xsl:if test="not(payment_method)">_______________</xsl:if>
                    <xsl:if test="payment_method = ''">_______________</xsl:if>
                    <br />
                    Termin zapłaty do:
                    <xsl:if test="payment_date !='' ">
                        <xsl:value-of select="payment_date" />
                    </xsl:if>
                    <xsl:if test="not(payment_date)">_______________</xsl:if>
                    <xsl:if test="payment_date = ''">_______________</xsl:if>
                </xsl:for-each>
                <br />

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>