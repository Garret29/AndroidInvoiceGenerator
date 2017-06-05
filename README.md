# AndroidInvoiceGenerator

## Autorzy:
- Szymon Piotrowski, 187825
- Kamil Przybycień, 187909

## Opis działania:
AndroidInvoiceGenerator jest to aplikacja służąca do generowania faktur w formacie pdf na telefonach z systemem operacyjnym Android.
Użytkownik po uzupełnieniu danych wybiera styl faktury w formacie xsl, a na wyjscu otrzymuje uzupełnioną fakturę
(obliczenia podatkowe są wykonywane automatycznie przez aplikacje)

## Funkcjonalności

+ Generowanie faktur pdf z podanych danych (wpisanych ręcznie, lub w formacie xml) w wybranym stylu (plik xsl)
+ Walidacja danych z api mojepaństwo.pl
+ Automatyczne wykonywanie obliczeń podatkowych

## Technologie

+ Android SDK
+ itext 5.5.10
+ JTidy r938
+ SimpleXML 2.7.1

## Dokumentacja

# Klasy:

+ pakiet invoicemodel - zawierający klasy reprezentujące fakturę oraz oznaczone adnotacjami Simple-XML w celu przeparsowania obiektu
  faktury na plik XML
+ pakiet generator - pakiet posiadający klasę PDFGenerator, która wykorzystując framework IText generuje plik pdf z podanych źródeł
+ pakiet tasks - zawierający klasę rozszerzającą AsyncTask, której celem jest pobranie pliku JSON z api mojepaństwo.pl
+ pakiet viewcontroller - pakiet będący zbiorem aktywnośći obsługujących interfejs użytkownika
