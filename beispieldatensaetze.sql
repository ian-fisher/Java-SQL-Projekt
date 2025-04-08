INSERT INTO Kategorie (Name, SteuerklasseProzent) VALUES
('Milchprodukte', 7),
('Suessigkeiten', 44),
('Vorgearbeitetes Futter', 42);

INSERT INTO Produkt (ProduktNr, Name, Preis, KategorieName) VALUES
(1, 'Meiers Milch', 3.49, 'Milchprodukte'),
(2, 'Schokolade', 0.99, 'Suessigkeiten'),
(3, 'Doener', 99.99, 'Vorgearbeitetes Futter');

INSERT INTO Ort (PLZ, Name) VALUES
(10115, 'Berlin-Mitte'),
(18362, 'Platz'),
(23984, 'Frankreich'),
(91219, 'Litauen');

INSERT INTO Filiale (FilialeNr, Name, Strasse, Nr, PLZ) VALUES
(1, 'Kaufland am Fluss', 'Flussstrasse', 54, 10115),
(2, 'Edeka Rasenblau', 'Goerlander Parkstrasse', 12, 18362),
(3, 'Lidl Oberfeld', 'Unterstrasse', 78, 23984);

INSERT INTO Lieferant (LieferantNr, Name, Strasse, Nr, PLZ) VALUES
(1, 'Döner an der Ecke', 'Ringelstraße', '69', 10115),
(2, 'Molkas Milchkühe', 'Bayrische Alp', '99988', 23984),
(3, 'Nestle', 'Stuhlstraße', '100', 91219);

INSERT INTO Kasse (KassenNr, FilialeNr) VALUES
(1, 2),
(2, 3),
(3, 1);

INSERT INTO Mitarbeiter (MitarbeiterNr, Vorname, Name, FilialeNr) VALUES
(1, 'Gertrude', 'Scholz', 3),
(2, 'Tilo', 'Mueller', 1),
(3, 'Klaus', 'Pankratov', 2);

INSERT INTO Kundenkonto (KontoNr, Bonuspunkte) VALUES
(1, 55),
(2, 0),
(3, 6626);

INSERT INTO Bestellung (BestellungNr, Jahr, Monat, Tag, FilialeNr) VALUES
(1, 2025,01,04, 2),
(2, 2024,06,30, 1),
(3, 2008,06,12, 3);

INSERT INTO EC_Zahlung (EC_ZahlungNr, IBAN, Betrag, InhaberVorname, InhaberName, KartenNr) VALUES
(1, 'DE1245363', 666, 'Jana', 'Jahr', '1'),
(2, 'DE9865373', 35, 'Meier', 'Müller', '2'),
(3, 'DE8436831', 2, 'Gerald', 'Eis', '3');

INSERT INTO enthaelt (EinkaufNr, ProduktNr, Menge, Steuer) VALUES
(1, 2, 2, 44),
(1, 3, 1, 42),
(2, 1, 1, 7),
(3, 2, 5, 44);

INSERT INTO Einkauf (EinkaufNr, Uhrzeit, Jahr, Monat, Tag, RabattProzent, KassenNr) VALUES
(1, '00:34:00', 2023,06,01, 0.0, 3),
(2, '15:45:00', 2023,07,06, 1.0, 1),
(3, '19:59:00', 2023,07,23, 0.33, 2);

