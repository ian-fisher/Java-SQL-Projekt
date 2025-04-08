INSERT INTO Produkt (ProduktNr, Name, Preis) VALUES
(1, 'Meiers Milch', 3.49),
(2, 'Schokolade', 0.99),
(3, 'Döner', 99.99);

INSERT INTO Kategorie (Name, SteuerklasseProzent) VALUES
('Milchprodukte', 7),
('Süßigkeiten', 44),
('Vorgearbeitetes Futter', 42);

INSERT INTO Filiale (FilialeNr, Name, Strasse, Nr, OrtNr) VALUES
(1, 'Kaufland am Fluss', 'Flussstraße', '54', 10275),
(2, 'Edeka Rasenblau', 'Görlander Parkstraße', '12', 18362),
(3, 'Lidl Oberfeld', 'Unterstraße', '78', 23984);

INSERT INTO Kasse (KassenNr, FilialeNr) VALUES
(1, 2),
(2, 2),
(3, 1);

INSERT INTO Mitarbeiter (MitarbeiterNr, Vorname, Name, FilialeNr) VALUES
(1, 'Gertrude', 'Scholz', 3),
(2, 'Tilo', 'Mueller', 1),
(3, 'Klaus', 'Pankratov', 2);

INSERT INTO Einkauf (EinkaufNr, Uhrzeit, Datum, RabattProzent, KassenNr) VALUES
(1, '00:34:00', '2023-06-01', 0.0, 3),
(2, '15:45:00', '2023-07-06', 1.0, 1),
(3, '19:59:00', '2023-07-23', 0.33, 2);

INSERT INTO Kundenkonto (KontoNr, Bonuspunkte) VALUES
(1, 55),
(2, 0),
(3, 66263425384);

INSERT INTO Bestellung (BestellungNr, Datum, FilialeNr) VALUES
(1, '2025-01-04', 2),
(2, '2024-06-30', 1),
(3, '2008-06-12', 3);

INSERT INTO Lieferant (LieferantNr, Name, Strasse, Nr, OrtNr) VALUES
(1, 'Döner an der Ecke', 'Ringelstraße', '69', 420),
(2, 'Molkas Milchkühe', 'Bayrische Alp', '99988', 8033),
(3, 'Nestle', 'Stuhlstraße', '100', 9121929);

INSERT INTO EC_Zahlung (EC_ZahlungNr, IBAN, Betrag, InhaberVorname, InhaberName, KartenNr) VALUES
(1, 'DE1245363', 666, 'Jana', 'Jahr', '1'),
(2, 'DE9865373', 35, 'Meier', 'Müller', '2'),
(3, 'DE8436831', 2, 'Gerald', 'Eis', '3');

INSERT INTO Enthaelt (EinkaufNr, ProduktNr, Menge, Steuer, Rabatt_Prozent, Rabatt_Zahl) VALUES
(1, 2, 2, 44, 0, 0.00),
(1, 3, 1, 42, 0, 0.00),
(2, 1, 1, 7, 10, 0.35),
(3, 2, 5, 44, 5, 0.25);

