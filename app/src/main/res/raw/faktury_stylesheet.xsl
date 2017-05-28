<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:s="http://www.stylusstudio.com/xquery">
    <xsl:template match="/">
        <fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
            <fo:layout-master-set>
                <fo:simple-page-master master-name="default-page" page-height="11in" page-width="8.5in" margin-left="0.6in" margin-right="0.6in" margin-top="0.79in" margin-bottom="0.79in">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="default-page">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block>
                        <fo:block text-align="left">
                            <fo:block>
                                <xsl:text>&#xA0;</xsl:text>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <fo:block>
                                        <fo:block text-align="right">
                                            <fo:block>
                                                <fo:inline font-size="12pt">
                                                    <xsl:value-of select="/faktura/miejscowosc_wystawienia"/>,data wystawienia:
                                                    <xsl:value-of select="/faktura/data_wystawienia"/>
                                                </fo:inline>
                                            </fo:block>
                                            <fo:block>
                                                <fo:inline font-size="12pt">
                                                    <xsl:value-of select="/faktura/tekst_dokumentu/data_konca"/>: 
                                                    <xsl:value-of select="/faktura/data_dostawy"/>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:block>
                                    </fo:block>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <xsl:text>&#xA0;</xsl:text>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="14pt" font-weight="bold">
                                    <xsl:text>Sprzedawca:</xsl:text>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/sprzedawca/nazwa"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">Adres: 
                                    <xsl:value-of select="/faktura/sprzedawca/ulica"/> 
                                    <xsl:value-of select="/faktura/sprzedawca/dom"/> 
                                    <xsl:value-of select="/faktura/sprzedawca/lokal"/>
                                    <xsl:text>, </xsl:text>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/sprzedawca/miasto"/> 
                                    <xsl:value-of select="/faktura/sprzedawca/kod"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">NIP: 
                                    <xsl:value-of select="/faktura/sprzedawca/NIP_PESEL"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">Rachunek: 
                                    <xsl:value-of select="/faktura/sprzedawca/rachunek"/> 
                                    <xsl:value-of select="/faktura/sprzedawca/bank"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">Telefon: 
                                    <xsl:value-of select="/faktura/sprzedawca/telefon"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">Email: 
                                    <xsl:value-of select="/faktura/nabywca"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <xsl:text>&#xA0;</xsl:text>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="14pt" font-weight="bold">
                                    <xsl:text>Nabywca:</xsl:text>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/nabywca/nazwa"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">Adres: 
                                    <xsl:value-of select="/faktura/nabywca/ulica"/> 
                                    <xsl:value-of select="/faktura/nabywca/dom"/> 
                                    <xsl:value-of select="/faktura/nabywca/lokal"/>
                                    <xsl:text>,</xsl:text>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/nabywca/miasto"/> 
                                    <xsl:value-of select="/faktura/nabywca/kod"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <xsl:text>&#xA0;</xsl:text>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <fo:block>
                                        <fo:block text-align="center">
                                            <fo:block>
                                                <fo:inline font-size="16pt" font-weight="bold">Faktura VAT 
                                                    <xsl:value-of select="/faktura/numer"/>
                                                </fo:inline>
                                            </fo:block>
                                        </fo:block>
                                    </fo:block>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <xsl:text>&#xA0;</xsl:text>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <fo:table width="100%" border-style="outset" border-width="2pt" background-repeat="repeat">
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-column/>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:text>L.p</xsl:text>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:text>Nazwa towaru/usługi</xsl:text>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/ilosc"/>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:text>J.m.</xsl:text>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:text>Cena jedn brutto.</xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:text>Rabat </xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:text>[%]</xsl:text>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/wartosc"/>
                                                            <xsl:text> jedn. brutto po rabacie</xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/wartosc"/>
                                                            <xsl:text> brutto</xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:text>Stawka VAT</xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:text>[%]</xsl:text>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/wartosc"/>
                                                            <xsl:text> VAT</xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block text-align="center">
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/wartosc"/>
                                                            <xsl:text> netto</xsl:text>
                                                        </fo:block>
                                                        <fo:block>
                                                            <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                        </fo:block>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/liczba_porzadkowa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/nazwa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/ilosc"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/jednostka"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/cena_brutto_jednost"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/rabat"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/cena_brutto_jednost_po_rabacie"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/brutto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/stawka_VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar1/netto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/liczba_porzadkowa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/nazwa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/ilosc"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/jednostka"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/cena_brutto_jednost"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/rabat"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/cena_brutto_jednost_po_rabacie"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/brutto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/stawka_VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar2/netto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/liczba_porzadkowa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/nazwa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/ilosc"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/jednostka"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/cena_brutto_jednost"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/rabat"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/cena_brutto_jednost_po_rabacie"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/brutto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/stawka_VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar3/netto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/liczba_porzadkowa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/nazwa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/ilosc"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/jednostka"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/cena_brutto_jednost"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/rabat"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/cena_brutto_jednost_po_rabacie"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/brutto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/stawka_VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar4/netto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/liczba_porzadkowa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/nazwa"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/ilosc"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/jednostka"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/cena_brutto_jednost"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/rabat"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/cena_brutto_jednost_po_rabacie"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/brutto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/stawka_VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/VAT"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/towary/towar5/netto"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <fo:table width="100%" border-style="outset" border-width="2pt" background-repeat="repeat">
                                        <fo:table-column/>
                                        <fo:table-body>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <xsl:value-of select="/faktura/tekst_dokumentu/wartosc"/> obliczone z cen bez podatku 
                                                        <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                            <fo:table-row>
                                                <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                    <fo:block>
                                                        <fo:table width="100%" border-style="outset" border-width="2pt" background-repeat="repeat">
                                                            <fo:table-column/>
                                                            <fo:table-column/>
                                                            <fo:table-column/>
                                                            <fo:table-column/>
                                                            <fo:table-body>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block/>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="center">
                                                                            <fo:block>Bez podatku 
                                                                                <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="center">
                                                                            <fo:block>VAT 
                                                                                <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                                                <xsl:text> (stawka)</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="center">
                                                                            <fo:block>Z podatkiem 
                                                                                <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:text>Razem:</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/netto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/VAT"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/brutto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:text>W tym:</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_0/netto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_0/VAT"/>
                                                                                <xsl:text> [0%]</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_0/brutto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block/>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_5/netto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_5/VAT"/>
                                                                                <xsl:text> [5%]</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_5/brutto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block/>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_8/netto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_8/VAT"/>
                                                                                <xsl:text> [8%]</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_8/brutto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                                <fo:table-row>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block/>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_23/netto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_23/VAT"/>
                                                                                <xsl:text> [23%]</xsl:text>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                    <fo:table-cell border-style="inset" border-width="2pt" padding="2pt" background-repeat="repeat" display-align="center">
                                                                        <fo:block text-align="right">
                                                                            <fo:block>
                                                                                <xsl:value-of select="/faktura/razem/w_tym_23/brutto"/>
                                                                            </fo:block>
                                                                        </fo:block>
                                                                    </fo:table-cell>
                                                                </fo:table-row>
                                                            </fo:table-body>
                                                        </fo:table>
                                                    </fo:block>
                                                </fo:table-cell>
                                            </fo:table-row>
                                        </fo:table-body>
                                    </fo:table>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/tekst_dokumentu/naleznosc"/>: 
                                    <xsl:value-of select="/faktura/razem/brutto"/>
                                    <xsl:value-of select="/faktura/tekst_dokumentu/waluta"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/tekst_dokumentu/naleznosc_slownie"/>: 
                                    <xsl:value-of select="/faktura/razem/netto_slownie"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <xsl:text>&#xA0;</xsl:text>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/tekst_dokumentu/sposob_zaplaty"/>: 
                                    <xsl:value-of select="/faktura/sposob_platnosci"/>
                                </fo:inline>
                            </fo:block>
                            <fo:block>
                                <fo:inline font-size="12pt">
                                    <xsl:value-of select="/faktura/tekst_dokumentu/termin_zaplaty"/>: 
                                    <xsl:value-of select="/faktura/termin_platnosci"/>
                                </fo:inline>
                            </fo:block>
                        </fo:block>
                    </fo:block>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>