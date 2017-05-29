<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method='html' version='1.0' encoding='UTF-8' indent='yes'/>
    <xsl:template match="/">
        <html>
            <body>
                <h2>Generate PDF from XML and XSLT StyleSheet Using iText, Flying Saucer and Java XSLT Transformer</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th align="left">Title</th>
                        <th align="left">Price</th>
                    </tr>
                    <xsl:for-each select="ConvertXMLtoPDF/TestData">
                        <tr>
                            <td><xsl:value-of select="Title"/></td>
                            <td><xsl:value-of select="Price"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>