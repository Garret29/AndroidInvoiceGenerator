<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>
    <xsl:template match="/">
        <html>
            <head>
                <meta charset="UTF-8"/>
            </head>
            <body style="font-family:Roboto">
                <xsl:for-each select="faktura">
                    <p align="right"><xsl:value-of select="miejscowosc_wystawienia"/>, data wystawienia:
                        <xsl:value-of select="data_wystawienia"/>
                    </p>
                    <p align="right">Data dokonania/zakończenia dostawy towarów:
                        <xsl:value-of select="data_dostawy"/>
                    </p>
                </xsl:for-each>
                <b>Sprzedawca:</b>

                <br/>
                <xsl:for-each select="faktura/sprzedawca">
                    <xsl:value-of select="nazwa"/>
                    <br/>
                    Adres: <xsl:value-of select="ulica"/>, <xsl:value-of select="dom"/>, lok. <xsl:value-of select="lokal"/>
                    <br/>
                    <xsl:value-of select="miasto"/> <xsl:value-of select="kod"/>
                    <br/>
                    NIP: <xsl:value-of select="NIP_PESEL"/>
                    <br/>
                    Rachunek: <xsl:value-of select="rachunek"/>; <xsl:value-of select="bank"/>
                    <br/>
                    Telefon: <xsl:value-of select="telefon"/>
                    <br/>
                    Email: <xsl:value-of select="email"/>
                    <br/>

                </xsl:for-each>
                <br/>
                <br/>
                <xsl:for-each select="faktura/nabywca">
                    <b>Nabywca:</b>
                    <br/>
                    <xsl:value-of select="nazwa"/>
                    <br/>
                    Adres: <xsl:value-of select="ulica"/>, <xsl:value-of select="dom"/>, lok. <xsl:value-of select="lokal"/>
                    <br/>
                    <xsl:value-of select="miasto"/> <xsl:value-of select="kod"/>
                    <br/>
                </xsl:for-each>
                <xsl:for-each select="faktura">
                    <h2><p align="center">Faktura VAT <xsl:value-of select="numer"/></p></h2>
                </xsl:for-each>
                <table border="3" align="center" width="400">
                    <tr>
                        <th align="center">L.p</th>
                        <th align="center">Nazwa towaru/usługi</th>
                        <th align="center">Ilość</th>
                        <th align="center">J.m.</th>
                        <th align="center">Cena jedn.
                            <br/> brutto
                            <br/> [zł]</th>
                        <th align="center">Rabat
                            <br/> [%]</th>
                        <th align="center">Cena jedn.
                            <br/> brutto po rabacie
                            <br/> [zł]</th>
                        <th align="center">Wartość brutto
                            <br/> [zł]</th>
                        <th align="center">Stawka VAT
                            <br/> [%]</th>
                        <th align="center">Wartość VAT
                            <br/> [zł]</th>
                        <th align="center">Wartość
                            <br/> netto
                            <br/> [zł]</th>
                    </tr>
                    <xsl:for-each select="faktura/towary/towar">
                        <tr>
                            <td align="right"><xsl:value-of select="liczba_porzadkowa"/> </td>
                            <td align="right"><xsl:value-of select="nazwa"/> </td>
                            <td align="right"><xsl:value-of select="ilosc"/> </td>
                            <td align="right"><xsl:value-of select="jednostka"/> </td>
                            <td align="right"><xsl:value-of select="cena_brutto_jednost"/> </td>
                            <td align="right"><xsl:value-of select="rabat"/> </td>
                            <td align="right"><xsl:value-of select="cena_brutto_jednost_po_rabacie"/> </td>
                            <td align="right"><xsl:value-of select="brutto"/> </td>
                            <td align="right"><xsl:value-of select="stawka_VAT"/> </td>
                            <td align="right"><xsl:value-of select="VAT"/> </td>
                            <td align="right"><xsl:value-of select="netto"/> </td>
                        </tr>
                    </xsl:for-each>
                </table>

                <br/>
                <br/>
                <table border="3" >
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
                            <table border="3" align="center">
                                <tr>
                                    <th> </th>
                                    <td align="center">Bez podatku
                                        <br/> [zł]</td>
                                    <td align="center">VAT<br/>[zł]</td>
                                    <td align="center">Stawka
                                        <br/> VAT</td>
                                    <td align="center">Z podatkiem
                                        <br/> [zł]</td>
                                </tr>

                                <tr>
                                    <xsl:for-each select="faktura/razem">
                                        <td>Razem:</td>
                                        <td align="right"><xsl:value-of select="netto"/></td>
                                        <td align="right"><xsl:value-of select="VAT"/></td>
                                        <td align="right"/>
                                        <td align="right"><xsl:value-of select="brutto"/></td>
                                    </xsl:for-each>
                                </tr>

                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_0">
                                        <td>W tym:</td>
                                        <td align="right"><xsl:value-of select="netto"/></td>
                                        <td align="right"><xsl:value-of select="VAT"/></td>
                                        <td align="right">0%</td>
                                        <td align="right"><xsl:value-of select="brutto"/></td>
                                    </xsl:for-each>
                                </tr>
                                <tr >
                                    <xsl:for-each select="faktura/razem/w_tym_5">
                                        <td align="right"/>
                                        <td align="right"><xsl:value-of select="netto"/></td>
                                        <td align="right"><xsl:value-of select="VAT"/></td>
                                        <td align="right">5%</td>
                                        <td align="right"><xsl:value-of select="brutto"/></td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_8">
                                        <td align="right"/>
                                        <td align="right"><xsl:value-of select="netto"/></td>
                                        <td align="right"><xsl:value-of select="VAT"/></td>
                                        <td align="right">8%</td>
                                        <td align="right"><xsl:value-of select="brutto"/></td>
                                    </xsl:for-each>
                                </tr>
                                <tr>
                                    <xsl:for-each select="faktura/razem/w_tym_23">
                                        <td align="right"/>
                                        <td align="right"><xsl:value-of select="netto"/></td>
                                        <td align="right"><xsl:value-of select="VAT"/></td>
                                        <td align="right">23%</td>
                                        <td align="right"><xsl:value-of select="brutto"/></td>
                                    </xsl:for-each>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>

                <xsl:for-each select="faktura/razem">
                    <a align="left">Należność ogółem: <xsl:value-of select="brutto"/>zł</a>
                    <br/>
                    Należność ogółem słownie: <xsl:value-of select="brutto_slownie"/>
                </xsl:for-each>
                <br/>
                <br/>
                <xsl:for-each select="faktura">
                    Sposób zapłaty: <xsl:value-of select="sposob_platnosci"/>
                    <br/>
                    Termin zapłaty do: <xsl:value-of select="termin_platnosci"/>
                </xsl:for-each>
                <br/>

            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>