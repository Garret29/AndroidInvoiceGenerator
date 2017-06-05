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
                <xsl:for-each select="faktura">
                    <p align="right">
                        <xsl:if test="miejscowosc_wystawienia !='' ">
                            <xsl:value-of select="miejscowosc_wystawienia" />
                        </xsl:if>
                        <xsl:if test="not(miejscowosc_wystawienia)">Miejscowość:___________</xsl:if>
                        <xsl:if test="miejscowosc_wystawienia = ''">Miejscowość:___________</xsl:if>
                        , data
                        wystawienia:

                        <xsl:if test="data_wystawienia !='' ">
                            <xsl:value-of select="data_wystawienia" />
                        </xsl:if>
                        <xsl:if test="not(data_wystawienia)">__________</xsl:if>
                        <xsl:if test="data_wystawienia = ''">__________</xsl:if>

                    </p>
                    <p align="right">Data dokonania/zakończenia dostawy towarów:
                        <xsl:if test="data_dostawy !='' ">
                            <xsl:value-of select="data_dostawy" />
                        </xsl:if>
                        <xsl:if test="not(data_dostawy)">__________</xsl:if>
                        <xsl:if test="data_dostawy = ''">__________</xsl:if>

                    </p>
                </xsl:for-each>
                <b>Sprzedawca:</b>

                <br />
                <xsl:for-each select="faktura/sprzedawca">
                    <xsl:if test="nazwa !='' ">
                        <xsl:value-of select="nazwa" />
                    </xsl:if>
                    <xsl:if test="not(nazwa)">Nazwa:__________</xsl:if>
                    <xsl:if test="nazwa = ''">Nazwa:__________</xsl:if>
                    <br />

                    Adres:
                    <br />
                    <xsl:if test="ulica !='' ">
                        <xsl:value-of select="ulica" />
                    </xsl:if>
                    <xsl:if test="not(ulica)">Ul.:___________</xsl:if>
                    <xsl:if test="ulica = ''">Ul.:___________</xsl:if>
                    ,
                    <xsl:if test="dom !='' ">
                        <xsl:value-of select="dom" />
                    </xsl:if>
                    <xsl:if test="not(dom)">dom:______</xsl:if>
                    <xsl:if test="dom = ''">dom:______</xsl:if>
                    , lok.
                    <xsl:if test="lokal !='' ">
                        <xsl:value-of select="lokal" />
                    </xsl:if>
                    <xsl:if test="not(lokal)">________</xsl:if>
                    <xsl:if test="lokal = ''">________</xsl:if>
                    <br />
                    <xsl:if test="miasto !='' ">
                        <xsl:value-of select="miasto" />;
                    </xsl:if>
                    <xsl:if test="not(miasto)">Miejscowość:____________</xsl:if>
                    <xsl:if test="miasto = ''">Miejscowość:____________</xsl:if>

                    <xsl:if test="kod !='' ">
                        <xsl:value-of select="kod" />
                    </xsl:if>
                    <xsl:if test="not(kod)">, kod pocztowy:________</xsl:if>
                    <xsl:if test="kod = ''">, kod pocztowy:________</xsl:if>
                    <br />

                    NIP:
                    <xsl:if test="NIP_PESEL !='' ">
                        <xsl:value-of select="NIP_PESEL" />
                    </xsl:if>
                    <xsl:if test="not(NIP_PESEL)">______________</xsl:if>
                    <xsl:if test="NIP_PESEL = ''">______________</xsl:if>
                    <br />

                    <xsl:if test="rachunek !='' ">
                        Rachunek:
                        <xsl:value-of select="rachunek" />
                    </xsl:if>
                    <xsl:if test="not(rachunek)">Nr. rachunku:___________________</xsl:if>
                    <xsl:if test="rachunek = ''">Nr. rachunku:___________________</xsl:if>
                    ,

                    <xsl:if test="bank !='' ">
                        <xsl:value-of select="bank" />
                    </xsl:if>
                    <xsl:if test="not(bank)">bank:__________</xsl:if>
                    <xsl:if test="bank = ''">bank:__________</xsl:if>
                    <br />

                    Telefon:
                    <xsl:if test="telefon !='' ">
                        <xsl:value-of select="telefon" />
                    </xsl:if>
                    <xsl:if test="not(telefon)">_____________</xsl:if>
                    <xsl:if test="telefon = ''">_____________</xsl:if>
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
                <xsl:for-each select="faktura/nabywca">
                    <b>Nabywca:</b>
                    <br />
                    <xsl:if test="nazwa !='' ">
                        <xsl:value-of select="nazwa" />
                    </xsl:if>
                    <xsl:if test="not(nazwa)">Nazwa:________________</xsl:if>
                    <xsl:if test="nazwa = ''">Nazwa:________________</xsl:if>
                    <br />
                    <xsl:if test="ulica !='' ">
                        Adres:
                        <xsl:value-of select="ulica" />
                    </xsl:if>
                    <xsl:if test="not(ulica)">Ulica:____________</xsl:if>
                    <xsl:if test="ulica = ''">Ulica:____________</xsl:if>
                    ,
                    <xsl:if test="dom !='' ">
                        <xsl:value-of select="dom" />
                    </xsl:if>
                    <xsl:if test="not(dom)">dom:______</xsl:if>
                    <xsl:if test="dom = ''">dom:______</xsl:if>
                    , lok.
                    <xsl:if test="lokal !='' ">
                        <xsl:value-of select="lokal" />
                    </xsl:if>
                    <xsl:if test="not(lokal)">________</xsl:if>
                    <xsl:if test="lokal = ''">________</xsl:if>
                    <br />
                    <xsl:if test="miasto !='' ">
                        <xsl:value-of select="miasto" />
                    </xsl:if>
                    <xsl:if test="not(miasto)">Miejscowość:___________</xsl:if>
                    <xsl:if test="miasto = ''">Miejscowość:___________</xsl:if>
                    ,
                    <xsl:if test="kod !='' ">
                        <xsl:value-of select="kod" />
                    </xsl:if>
                    <xsl:if test="not(kod)">kod pocztowy:________</xsl:if>
                    <xsl:if test="kod = ''">kod pocztowy:________</xsl:if>
                    <br />
                </xsl:for-each>
                <xsl:for-each select="faktura">
                    <h2>
                        <p align="center">Faktura VAT
                            <xsl:if test="numer !='' ">
                                <xsl:value-of select="numer" />
                            </xsl:if>
                            <xsl:if test="not(numer)">Tel.:_______________</xsl:if>
                            <xsl:if test="numer = ''">Tel.:_______________</xsl:if>
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
                    <xsl:for-each select="faktura/towary/towar">
                        <tr>
                            <td align="right">
                                <xsl:value-of select="liczba_porzadkowa" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="nazwa" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="ilosc" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="jednostka" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="cena_brutto_jednost" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="rabat" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="cena_brutto_jednost_po_rabacie" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="brutto" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="stawka_VAT" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="VAT" />
                            </td>
                            <td align="right">
                                <xsl:value-of select="netto" />
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
                                    <xsl:for-each select="faktura/razem">
                                        <td>Razem:</td>
                                        <td align="right">
                                            <xsl:value-of select="netto" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="brutto" />
                                        </td>
                                    </xsl:for-each>
                                </tr>

                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_0">
                                        <td>W tym:</td>
                                        <td align="right">
                                            <xsl:value-of select="netto" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">0%</td>
                                        <td align="right">
                                            <xsl:value-of select="brutto" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_5">
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="netto" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">5%</td>
                                        <td align="right">
                                            <xsl:value-of select="brutto" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_8">
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="netto" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">8%</td>
                                        <td align="right">
                                            <xsl:value-of select="brutto" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_23">
                                        <td align="right" />
                                        <td align="right">
                                            <xsl:value-of select="netto" />
                                        </td>
                                        <td align="right">
                                            <xsl:value-of select="VAT" />
                                        </td>
                                        <td align="right">23%</td>
                                        <td align="right">
                                            <xsl:value-of select="brutto" />
                                        </td>
                                    </xsl:for-each>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <xsl:for-each select="faktura/razem">
                    <p align="left">Należność ogółem:<xsl:value-of select="brutto" />zł
                    </p>
                    <br />
                    Należność ogółem słownie:
                    <xsl:if test="brutto_slownie !='' ">
                        <xsl:value-of select="brutto_slownie" />
                    </xsl:if>
                    <xsl:if test="not(brutto_slownie)">______________________________________</xsl:if>
                    <xsl:if test="brutto_slownie = ''">______________________________________</xsl:if>
                </xsl:for-each>
                <br />
                <br />
                <xsl:for-each select="faktura">
                    Sposób zapłaty:
                    <xsl:if test="sposob_platnosci !='' ">
                        <xsl:value-of select="sposob_platnosci" />
                    </xsl:if>
                    <xsl:if test="not(sposob_platnosci)">_______________</xsl:if>
                    <xsl:if test="sposob_platnosci = ''">_______________</xsl:if>
                    <br />
                    Termin zapłaty do:
                    <xsl:if test="termin_platnosci !='' ">
                        <xsl:value-of select="termin_platnosci" />
                    </xsl:if>
                    <xsl:if test="not(termin_platnosci)">_______________</xsl:if>
                    <xsl:if test="termin_platnosci = ''">_______________</xsl:if>
                </xsl:for-each>
                <br />

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>