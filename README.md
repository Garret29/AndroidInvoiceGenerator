# AndroidInvoiceGenerator

## Contributors:
- Szymon Piotrowski [Garret29](https://github.com/Garret29)
- Kamil Przybycień [kamil21180](https://github.com/kamil21180)

## Description:
AndroidInvoiceGenerator is application made to generate pdf output representing an invoice for mobile devices with Android.

## Functionalities

+ Generating pdf invoices with data from form or from xml file.
+ Validation of typed data via mojepanstwo.pl api
+ Automatic calculations for: discounts, gross value and taxes
+ Sharing invoice

## Technologies

+ Android SDK
+ itext 5.5.10
+ JTidy r938
+ SimpleXML 2.7.1

## Documentation

### Packages:

+ package invoicemodel - package which contains classes representing invoice annotated for xml parsing;
+ package generator - package with PDFGenerator class which generates pdf output from sources;
+ package tasks - package with tasks (e.g. AsyncTask for connecting with mojepanstwo.pl api);
+ package viewcontroller - package with activities;
