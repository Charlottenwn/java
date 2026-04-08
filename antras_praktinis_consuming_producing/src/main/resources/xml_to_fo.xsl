<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:fo="http://www.w3.org/1999/XSL/Format"
                exclude-result-prefixes="fo">
    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <fo:root>
            <fo:layout-master-set>
                <fo:simple-page-master master-name="A4" page-height="29.7cm" page-width="21cm" margin="2cm">
                    <fo:region-body/>
                </fo:simple-page-master>
            </fo:layout-master-set>
            <fo:page-sequence master-reference="A4">
                <fo:flow flow-name="xsl-region-body">
                    <fo:block font-size="18pt" font-weight="bold" margin-bottom="1cm">
                        Library Borrowers Report
                    </fo:block>
                    <fo:table border="1pt solid black" width="100%">
                        <fo:table-column column-width="10%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="20%"/>
                        <fo:table-column column-width="50%"/>
                        <fo:table-header>
                            <fo:table-row background-color="#f2f2f2">
                                <fo:table-cell padding="5pt"><fo:block>ID</fo:block></fo:table-cell>
                                <fo:table-cell padding="5pt"><fo:block>First Name</fo:block></fo:table-cell>
                                <fo:table-cell padding="5pt"><fo:block>Last Name</fo:block></fo:table-cell>
                                <fo:table-cell padding="5pt"><fo:block>Borrowed Books</fo:block></fo:table-cell>
                            </fo:table-row>
                        </fo:table-header>
                        <fo:table-body>
                            <xsl:for-each select="map/entry[@key='people']/list/person">
                                <fo:table-row>
                                    <fo:table-cell padding="5pt"><fo:block><xsl:value-of select="id"/></fo:block></fo:table-cell>
                                    <fo:table-cell padding="5pt"><fo:block><xsl:value-of select="first_name"/></fo:block></fo:table-cell>
                                    <fo:table-cell padding="5pt"><fo:block><xsl:value-of select="last_name"/></fo:block></fo:table-cell>
                                    <fo:table-cell padding="5pt">
                                        <fo:block>
                                            <xsl:for-each select="books_borrowed/book_borrowed">
                                                <fo:block>- <xsl:value-of select="book/book_name"/> by <xsl:value-of select="book/author"/>
                                                    (Borrowed: <xsl:value-of select="borrow_start_date"/>, Due: <xsl:value-of select="borrow_expire_date"/>)
                                                </fo:block>
                                            </xsl:for-each>
                                        </fo:block>
                                    </fo:table-cell>
                                </fo:table-row>
                            </xsl:for-each>
                        </fo:table-body>
                    </fo:table>
                </fo:flow>
            </fo:page-sequence>
        </fo:root>
    </xsl:template>
</xsl:stylesheet>